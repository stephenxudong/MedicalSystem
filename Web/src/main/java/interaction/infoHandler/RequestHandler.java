package interaction.infoHandler;

import com.alibaba.fastjson.JSONArray;
import org.apache.ibatis.session.SqlSession;
import org.springframework.test.context.jdbc.Sql;
import web.dao.GynaecologyCaseMapper;
import web.pojo.IdAndMtime;
import web.pojo.medicalCase.GynaecologyCase;
import interaction.infolocker.AESEncode;
import interaction.writeHandler.WriteHandler;
import test.SFactory;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RequestHandler implements Runnable{
    private SqlSession session = SFactory.getSqlSession();
    private GynaecologyCaseMapper caseHandler = null;
    private WriteHandler writeHandler;
    private String[] requestArray;
    private String doctorID;
    private boolean loginState;

    public RequestHandler(String[] requestArray, WriteHandler writeHandler, boolean loginState, String doctorID)
    {
        this.requestArray = requestArray;
        this.writeHandler = writeHandler;
        this.loginState = loginState;
        this.doctorID = doctorID;
        this.caseHandler = session.getMapper(GynaecologyCaseMapper.class);
    }

    @Override
    public void run() {
        String returnMess = AESEncode.aesEncrypt(matchRes(requestArray[0], getValues(requestArray))) + "#"; //取得有效值部分,执行返回操作
        System.out.println(returnMess);
        writeHandler.write(returnMess);
        Thread.currentThread().interrupt();
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
                rentunMess = ResponseEnum.$UPDATE_STATE$.toString()+ "#" + updateResponse(usefulValues);
                break;
            case "$GET_ALL_DIR$":
                rentunMess = ResponseEnum.$ALL_DIR$.toString() + "#" + refreshDir(doctorID);
                break;
            case "$GET_CONTENT$":
                rentunMess = ResponseEnum.$CONTENT$.toString() + "#" + refreshContent(usefulValues);
                break;
            case "$RECEIVE_DIR$":
                refreshReportStat(usefulValues);
                break;
        }
        return rentunMess;
    }



    public String updateResponse(String[] usefulValues)
    {
        if (!loginState)
            return "FuckYou，hacker";

        GynaecologyCase gynaecologyCase = JSONObject.parseObject(usefulValues[0], GynaecologyCase.class);
        gynaecologyCase.setStatus("false");
        caseHandler.updateGynaecologyCase(gynaecologyCase);
        session.commit();
        return "true";
    }

    public String refreshDir(String doctorID)
    {
        if (!loginState)
            return "FuckYou，hacker";

        List<IdAndMtime> multiList = caseHandler.selectIdAndMtime(doctorID);
        List<String> caseDir = new ArrayList<>();

        for(IdAndMtime info : multiList){
            caseDir.add(info.getCase_id()+"$"+info.getMtime());
        }

        JSONObject obj = new JSONObject();
        JSONArray listArray = new JSONArray();
        listArray.addAll(caseDir);
        obj.clear();
        obj.put("caseID",listArray);
        return obj.toString();
    }

    public String refreshContent(String[] usefulValues)
    {
        if (!loginState)
            return "FuckYou，hacker";

        //函数目的：响应手动刷新病历的请求，把参数中的病历号对应的病历返回
        JSONObject obj = (JSONObject) JSONObject.parse(usefulValues[0]);
        List<String> caseIDList = (List<String>) obj.get("caseID");
        List<GynaecologyCase> gynaecologyCaseList = new ArrayList<>();
        for (String caseID : caseIDList){
            gynaecologyCaseList.add(caseHandler.findByCaseId(caseID));
        }
        JSONArray listArray = new JSONArray();
        listArray.addAll(gynaecologyCaseList);
        obj.clear();
        obj.put("caseContent",listArray);
        return obj.toString();
    }

    public void refreshReportStat(String[] usefulValues)
    {
        //把数据库中对应的病历状态设置为已查看
        JSONObject obj = (JSONObject) JSONObject.parse(usefulValues[0]);
        List<String> caseIDList = (List<String>) obj.get("caseID");
        for (String caseID : caseIDList)
            caseHandler.updateGynaecologyCaseStatus(caseID);
        session.commit();

    }
}
