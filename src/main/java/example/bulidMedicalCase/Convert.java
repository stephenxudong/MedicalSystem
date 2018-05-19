package example.bulidMedicalCase;

import example.pojo.medicalCase.GynaecologyCase;
import javafx.util.Pair;
import java.util.HashMap;
import java.util.List;

public class Convert {

    public static GynaecologyCase convert(HashMap<String,Object> map)
    {
        GynaecologyCase gcase = new GynaecologyCase();
        String idCardId = (String)map.get("idCardId");
        //TODO 从数据库根据身份证号查出来病人的信息，卸载gcase中，返回给后台
        //TODO 订单信息（此项病例对应的医生的id等）需要从医院获得
        List<Pair<String,String>> pairs = (List<Pair<String,String>>)map.get("current_ques_ans");
        String currentCase = "";
        for (Pair<String,String> pair:pairs)
        {
            String tmp = pair.getKey()+": "+pair.getValue();
            currentCase += tmp;
        }
        gcase.setPresent_history(currentCase);
        gcase.setMain_case((String)map.get("main_case"));
        return gcase;
    }


}
