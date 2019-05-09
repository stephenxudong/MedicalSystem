package interaction.socket;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.SqlSession;
import web.dao.GynaecologyCaseMapper;
import interaction.infolocker.AESEncode;
import interaction.doctorIDTransfer.AddIDFunc;
import interaction.infoHandler.ResponseEnum;
import interaction.writeHandler.WriteHandler;
import test.SFactory;
import web.pojo.IdAndMtime;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TimerTask;

class TimerCheck extends TimerTask {

    private HashSet<String> doctorIdSet = AddIDFunc.getDoctorIDSet();
    private SqlSession session = null;
    private GynaecologyCaseMapper medicalHandler;
    private WriteHandler writeHandler;
    private String doctorID;

    public TimerCheck(WriteHandler writeHandler, String doctorID)
    {
        super();
        this.writeHandler = writeHandler;
        this.doctorID = doctorID;
    }

    @Override
    public void run() {
        String appoinFlushState = checkAppState(); //检查有无没有被下载的病历信息
        if(!(appoinFlushState.equals("failed"))){
            String returnMess = AESEncode.aesEncrypt(ResponseEnum.$NEW_DIR$.toString() +"#"+ appoinFlushState) + "#";
            writeHandler.write(returnMess);
        }
    }

    public String checkAppState() {
        //检查有无上传的新的病例或者没有被下载的
        session = SFactory.getSqlSession();
        medicalHandler = session.getMapper(GynaecologyCaseMapper.class);
        if(doctorIdSet.contains(doctorID)) {
            List<IdAndMtime> multiList = medicalHandler.selectUndeliveredGynaecologyCase(doctorID);
            List<String> caseDir = new ArrayList<>();


            for(IdAndMtime info : multiList){
                caseDir.add(info.getCase_id()+"$"+info.getMtime());
            }

            JSONObject obj = new JSONObject();
            JSONArray listArray = new JSONArray();
            listArray.addAll(caseDir);
            obj.clear();
            obj.put("caseID",listArray);
            synchronized (multiList)
            {
                doctorIdSet.remove(doctorID);
            }
            return obj.toString();
        }
        return "failed";
    }
}
