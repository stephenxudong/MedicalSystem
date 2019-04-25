package example.bulidMedicalCase;

import example.dao.GynaecologyCaseMapper;
import example.dao.Patient_accountMapper;
import example.pojo.Patient_account;
import example.pojo.medicalCase.Case;
import example.pojo.medicalCase.GynaecologyCase;
import javafx.util.Pair;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import test.SFactory;

import java.util.HashMap;
import java.util.List;

public class Convert {
    static SqlSession session;

    public static Case convert(HashMap<String,Object> map)
    {
        Case gcase = null;
        session = SFactory.getSqlSession();
        Patient_accountMapper mapper = session.getMapper(Patient_accountMapper.class);
        String model_id = (String) map.get("model_id");
        switch(model_id)
        {
            case "0"://妇科
                gcase = new GynaecologyCase();
                List<Pair<String,String>> pairs = (List<Pair<String,String>>)map.get("current_ques_ans");
                String currentCase = "";
                for (Pair<String,String> pair:pairs)
                {
                    String tmp = pair.getKey()+": "+pair.getValue();
                    currentCase += tmp;
                }

                gcase.setPresent_history(currentCase);
                gcase.setMain_case((String)map.get("main_case"));
                //gcase.setCase_id(uuid());
                //gcase.setDate();
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
        gcase.setStatus("false");

        return gcase;
    }


}
