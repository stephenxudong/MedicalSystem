package web.bulidMedicalCase;

import com.mchange.v2.collection.MapEntry;
import web.dao.Patient_accountMapper;
import web.pojo.Patient_account;
import web.pojo.medicalCase.Case;
import web.pojo.medicalCase.GynaecologyCase;
import javafx.util.Pair;
import org.apache.ibatis.session.SqlSession;
import test.SFactory;

import java.text.SimpleDateFormat;
import java.util.*;

public class Convert {
    static SqlSession session;
    static Integer uid = 1;
    public static Case convert(HashMap<String,Object> map)
    {
        Case gcase = null;
        session = SFactory.getSqlSession();
        Patient_accountMapper mapper = session.getMapper(Patient_accountMapper.class);
        String model_id = (String) map.get("model_id");
        switch(model_id)
        {
            case "1"://妇科
                gcase = new GynaecologyCase();
                List<Map<String,String>> pairs = ( List<Map<String,String>> )map.get("current_ques_ans");
                String currentCase = "";
                for (int i = 0; i < pairs.size() ; i++) {
                    String tmp = "";
                    tmp = tmp + pairs.get(i).get("question").trim()+" : ";
                    tmp = tmp + pairs.get(i).get("answer").trim() +";";
                    currentCase+=tmp;
                }
                gcase.setPresent_history(currentCase);
                gcase.setMain_case((String)map.get("main_case"));
                synchronized (uid){
                    String id = String.valueOf(System.currentTimeMillis()).substring(4);
                    gcase.setCase_id(id);
                    uid++;
                }
                gcase.setDate(new Date());
                /**
                ((GynaecologyCase) gcase).setBearing_history();
                ((GynaecologyCase) gcase).setFamily_history();
                ((GynaecologyCase) gcase).setBearing_history();
                ((GynaecologyCase) gcase).setMarital_history();
                ((GynaecologyCase) gcase).setPersonal_case();
                ((GynaecologyCase) gcase).setMenstruation_history();
                **/
                break;
        }
        String idCardId = (String)map.get("idCardId");
        Patient_account patient = mapper.selectByIdentiId(idCardId);
        //设置病人信息

        gcase.setPatient_identification_id(idCardId);
        gcase.setPatient_name(patient.getPatient_name());
        gcase.setPatient_phone_number(patient.getPatient_phone_number());
        gcase.setPatient_account_id(patient.getPatient_account_id());
        //TODO 从数据库根据身份证号查出来病人的信息，写在gcase中，返回给后台
        //TODO 订单信息（此项病例对应的医生的id等）需要从医院获得
        gcase.setDoctor_account_id("80224");
        gcase.setStatus("false");

        return gcase;
    }


}
