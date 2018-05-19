package infoHandler;

import infolocker.AESEncode;
import test.SFactory;
import com.alibaba.fastjson.JSONObject;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class RequestHandler implements Runnable{
    private static medical_caseMapper databaseHandler = SFactory.getSqlSession().getMapper(medical_caseMapper.class);
    private BufferedWriter writer;
    private String[] requestArray;
    private String doctorID;
    private boolean loginState;

    public RequestHandler(String[] requestArray, BufferedWriter writer, boolean loginState, String doctorID)
    {
        this.requestArray = requestArray;
        this.writer = writer;
        this.loginState = loginState;
        this.doctorID = doctorID;
    }

    @Override
    public void run() {
        String returnMess = matchRes(requestArray[0], getValues(requestArray)); //取得有效值部分,执行返回操作
        returnMess = AESEncode.aesDecrypt(returnMess); //加密
        try {
            writer.write(returnMess);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Thread.currentThread().interrupt();
        }
    }

    public String[] getValues(String[] requestArray) //切分数组，提取有效值
    {
        String[] jsonValues = new String[requestArray.length - 1];
        for (int i = 0; i < requestArray.length; i++)
        {
            if (i == 0) continue;
            jsonValues[i - 1] = requestArray[i];
        }
        return jsonValues;
    }

    //匹配读入的指令
    public String matchRes(String requestMess, String[] usefulValues) {

        String rentunMess = "";
        switch (requestMess) {
            case "$UPDATE$":
                rentunMess = ResponseEnum.$UPDATE_STATE$.toString() + updateResponse(usefulValues);
                break;
            case "$REFRESH_DIR$":
                rentunMess = ResponseEnum.$RES_DIR$.toString() + refreshDir(doctorID);
                break;
            case "$REFRESH_CONTENT$":
                rentunMess = ResponseEnum.$RES_CONTENT$.toString() + refreshContent(usefulValues);
                break;
            case "$RECEIVE_STATE$":
                refreshReportStat(usefulValues);
                break;
        }
        return rentunMess;
    }

    public String updateResponse(String[] usefulValues)
    {
        if (!loginState)
            return "FuckYou，hacker";


        //todo 长度为1，放入的只有json键值对
        //todo 数据库保存，返回字符串，成功为"true"，失败为"false"
        return "";
    }

    public String refreshDir(String doctorID)
    {
        if (!loginState)
            return "FuckYou，hacker";

        List<String> caseIDLsit = databaseHandler.refreshDir(doctorID);
        JSONObject obj = new JSONObject();
        obj.put("caseID", caseIDLsit);
        return obj.toString();
    }

    public String refreshContent(String[] usefulValues)
    {
        if (!loginState)
            return "FuckYou，hacker";

        //函数目的：响应手动刷新病历的请求，把参数中的病历号对应的病历返回
        JSONObject obj = (JSONObject) JSONObject.parse(usefulValues[0]);
        List<String> caseIDList = (List<String>) obj.get("caseID");
        //todo 返回客户端索要的病历内容
        //todo 返回内容需要封装为string

        return "";
    }

    public void refreshReportStat(String[] usefulValues)
    {
        //把数据库中对应的病历状态设置为已查看
        JSONObject obj = (JSONObject) JSONObject.parse(usefulValues[0]);
        List<String> caseIDList = (List<String>) obj.get("caseID");
        for (String caseID : caseIDList)
            databaseHandler.refreshReportStat("1", caseID);
    }
}
