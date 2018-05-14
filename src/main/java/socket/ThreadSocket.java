package socket;

import ThreadPool.SocketPool;
import infoHandler.RequestHandler;
import infoHandler.ResponseEnum;
import locker.AESEncode;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

class ThreadSocket implements Runnable {

    private ThreadPoolExecutor threadPoolExecutor;
    private Socket threadSocket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private boolean loginState;
    private int doctorID;
    private String heartBeatStr = "!@";

    public ThreadSocket(Socket socket) {
        threadSocket = socket;
        loginState = false;
    }

    @Override
    public void run(){
        try {
            reader = new BufferedReader(new InputStreamReader(threadSocket.getInputStream())); //从socket里面读入
            writer = new BufferedWriter(new OutputStreamWriter(threadSocket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int len;
        char[] buf = new char[4096];
        try {
            while(threadSocket.isConnected()){ //后又阻塞在这等待客户端发消息
                if ((len = reader.read(buf)) != -1) { //接收到了数据
                    String receivedStr = new String(buf, 0, len);
                    String decodeStr = AESEncode.aesDecrypt(receivedStr); //获得解密的请求数据
                    String[] requestArray = decodeStr.split("#"); //获得切分出来的数组
                    if (decodeStr.equals(heartBeatStr)); //接受心跳包
                    else if(requestArray[0].equals("$LOGIN$")) //登录
                    {
                        doctorID = Integer.parseInt(requestArray[1]);
                        writer.write(ResponseEnum.$LOGIN_STATE$.toString() + loginResponse(requestArray));
                    }
                    else { //数据处理线程，减少try catch块中代码量
                        threadPoolExecutor = new SocketPool().getRequestHandlerPool();
                        threadPoolExecutor.execute(new Thread(new RequestHandler(requestArray, writer, loginState, doctorID)));
                    }
                }
                String appoinFlushState = checkAppState(); //检查有无没有被下载的病历信息
                if(!(appoinFlushState.equals("failed"))){
                     if (writer == null)
                        writer.write(ResponseEnum.$NEW_RECORD_REMIND$.toString() + appoinFlushState);
                     writer.flush();
                 }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            disconnect(); //关闭流,socket和线程
        }
    }

    //关闭线程
    public void disconnect(){
        try {
            reader.close();
            writer.close();
            threadSocket.close();
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String loginResponse(String[] usefulValues)
    {
        String returnStr = "false";
        //todo usefulvalues[1] = 用户名, usefualValues[2] = 密码;
        //todo 查数据库, 如果可以登录, 将其值改为true，
        //todo 随便改，最后返回字符串，成功为true，失败为false
        //todo 更改loginstate的值
        if (loginState)
            returnStr = "true";
        return returnStr;
    }

    public String checkAppState() {
        //todo 检查有无上传的新的病例或者没有被下载的
        //todo 返回病历号json数组的tostring
        //todo 成功返回病历号，失败就返回failed字符串
        return "failed";
    }

}
