package example.uitl;

import example.pojo.medicalCase.Case;
import example.pojo.medicalCase.GynaecologyCase;

import java.sql.Date;
import java.util.HashMap;

public class GenerateCase {
    public Case generateCase(HashMap<String,String> map)
    {
        Case c = null;
        String model_id = map.get("model_id");
        switch(model_id)
        {
            case "妇科":
                c = genGynCase(map);
        }
        return c;
    }


    public GynaecologyCase genGynCase(HashMap<String,String> map)
    {
        GynaecologyCase c = new GynaecologyCase();
        c.setCase_id(map.get("case_id"));
        c.setMain_case(map.get("main_case"));
        c.setDate(Date.valueOf(map.get("date")));
        return c;

    }
}
