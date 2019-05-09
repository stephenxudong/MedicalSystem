package interaction.socket;

import interaction.infoHandler.RequestHandler;
import interaction.threadPool.SocketPool;
import web.dao.Doctor_accountMapper;
import web.pojo.Doctor_account;
import interaction.infoHandler.ResponseEnum;
import interaction.infolocker.AESEncode;
import interaction.writeHandler.WriteHandler;
import interaction.writeHandler.WriterHandlerImpl;
import org.apache.ibatis.session.SqlSession;
import test.SFactory;
import java.io.*;
import java.net.Socket;
import java.util.Timer;
import java.util.concurrent.ThreadPoolExecutor;

class ThreadSocket implements Runnable {

    private ThreadPoolExecutor threadPoolExecutor;
    private Socket threadSocket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private boolean loginState;
    private String doctorID;
    private WriteHandler writeHandler;
    private SqlSession session = SFactory.getSqlSession();
    private Doctor_accountMapper doctorHandler = session.getMapper(Doctor_accountMapper.class);


    public ThreadSocket(Socket socket) {
        threadSocket = socket;
        loginState = false;
    }

    @Override
    public void run(){
        try {
            reader = new BufferedReader(new InputStreamReader(threadSocket.getInputStream())); //从socket里面读入
            writer = new BufferedWriter(new OutputStreamWriter(threadSocket.getOutputStream()));
            writeHandler = new WriterHandlerImpl(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int len;
        char[] buf = new char[1025];
        try {
            String totalReceivedStr = "";
            while(threadSocket.isConnected()){ //后又阻塞在这等待客户端发消息
                if ((len = reader.read(buf)) != -1) { //接收到了数据
                    String receivedStr = new String(buf, 0, len);
                    totalReceivedStr += receivedStr;
                    if(receivedStr.endsWith("#"))
                    {
                        strSplit(totalReceivedStr);
                        totalReceivedStr = "";
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            disconnect(); //关闭流,socket和线程
        }
    }

    //未完成命令的链接
    public void strSplit(String totalReceivedStr)
    {
        String[] requests = totalReceivedStr.split("#");
        for(int i = 0; requests.length - i >= 1; i++)
        {
            loginHandler(requests[i]);
            System.out.println(requests[i]);
        }
    }

    //登录处理函数
    public void loginHandler(String receivedStr)
    {
        String decodeStr = AESEncode.aesDecrypt(receivedStr); //获得解密的请求数据
        System.out.println(decodeStr);
        String[] requestArray = decodeStr.split("#"); //获得切分出来的数组
        if (decodeStr.equals("$HEART_BEAT$")); //接受心跳包
        else if(requestArray[0].equals("$LOGIN$")) //登录
        {
            doctorID = requestArray[1];
            String sendMess = AESEncode.aesEncrypt(ResponseEnum.$LOGIN_STATE$.toString() + loginResponse(requestArray)) + "#";
            writeHandler.write(sendMess);
        }
        else { //数据处理线程，减少try catch块中代码量
            threadPoolExecutor = new SocketPool().getRequestHandlerPool();
            threadPoolExecutor.execute(new Thread(new RequestHandler(requestArray, writeHandler, loginState, doctorID)));
        }
    }

    //登录响应函数
    public String loginResponse(String[] usefulValues)
    {
        Doctor_account selectedDoctor = doctorHandler.findByDoctorId(usefulValues[1]);
        if(selectedDoctor != null)
        {
            if(selectedDoctor.getDoctor_password().equals(usefulValues[2]))
            {
                if(selectedDoctor.getStatus().equals("false"))
                {
                    if(!loginState) TimerCreater(); //注册timer函数
                    loginState = true;
                    doctorHandler.loginChangeStatus(doctorID, "true");
                    session.commit(true);
                    return "#true";
                }
                else
                    return "#false#AlreadyOnline";
            }
            else
                return "#false#PasswordError";
        }
        else
            return "#fasle#NoAccount";
    }

    //循环检测注册函数
    public void TimerCreater()
    {
        Timer timer = new Timer();
        timer.schedule(new TimerCheck(writeHandler, doctorID), 1000, 3000);
    }

    //关闭线程
    public void disconnect(){
        try {
            stateChange();
            reader.close();
            writer.close();
            threadSocket.close();
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //关闭会话和修改登录状态
    public void stateChange()
    {
        if(loginState == true) {
            doctorHandler.loginChangeStatus(doctorID, "false");
            session.commit(true);
            session.close();
        }
    }
}
