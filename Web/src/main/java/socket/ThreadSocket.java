package socket;

import ThreadPool.SocketPool;
import com.alibaba.fastjson.JSONObject;
import example.dao.Doctor_accountMapper;
import example.dao.GynaecologyCaseMapper;
import example.pojo.Doctor_account;
import example.pojo.medicalCase.GynaecologyCase;
import infoHandler.RequestHandler;
import infoHandler.ResponseEnum;
import infoHandler.susInfo;
import infolocker.AESEncode;
import test.SFactory;
import java.io.*;
import java.net.Socket;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

class ThreadSocket implements Runnable {

    private ThreadPoolExecutor threadPoolExecutor;
    private Socket threadSocket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private boolean loginState;
    private String doctorID;
    private String heartBeatStr = "!@";
    HashSet<String> doctorIdSet = susInfo.getDoctorIDSet();
    private Doctor_accountMapper doctorHandler = SFactory.getSqlSession().getMapper(Doctor_accountMapper.class);
    private GynaecologyCaseMapper medicalHandler = SFactory.getSqlSession().getMapper(GynaecologyCaseMapper.class); //todo

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
                        doctorID = requestArray[1];
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
        Doctor_account selectedDoctor = doctorHandler.findByDoctorId(usefulValues[1]);
        if (selectedDoctor != null && selectedDoctor.getDoctor_password().equals(usefulValues[2])) {
            loginState = true;
            return "true";
        }
        return "false";//todo 医生是否在线
    }

    public String checkAppState() {
        //检查有无上传的新的病例或者没有被下载的
//        if(doctorIdSet.contains(doctorID)) {
//            List<String> caseIDList = medicalHandler.checkAppState(doctorID, "0");
//            JSONObject obj = new JSONObject();
//            if (caseIDList.size() != 0) {
//                obj.put("caseID", caseIDList);
//            }
//            synchronized (caseIDList)
//            {
//                doctorIdSet.remove(doctorID);
//            }
//            return obj.toString();
//        }
        return "failed";
    }

}
