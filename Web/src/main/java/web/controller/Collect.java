package web.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import web.bulidMedicalCase.Convert;
import web.dao.GynaecologyCaseMapper;
import web.pojo.medicalCase.Case;
import web.pojo.medicalCase.GynaecologyCase;
import web.uitl.SFactory;
import interaction.doctorIDTransfer.CheckDoctorID;
import interaction.doctorIDTransfer.CheckDoctorIDImpl;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.json.JsonObjectBuilder;
import java.util.HashMap;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = "/collect")
public class Collect {
    private SqlSession session;
    private static final Log logger = LogFactory.getLog(Collect.class);
    @RequestMapping(value = "/quesAndAns",method = RequestMethod.POST)
    public String collect(@RequestBody HashMap<String,Object> map){
        logger.info("============collect final information===========");
        Case c = Convert.convert(map);
        session = SFactory.getSqlSession();
        if(c instanceof GynaecologyCase){
            GynaecologyCaseMapper mapper = session.getMapper(GynaecologyCaseMapper.class);
            mapper.insertGynaecologyCase((GynaecologyCase) c);//存储
            session.commit();
            session.close();
            CheckDoctorID checkdoctorid  = new CheckDoctorIDImpl((GynaecologyCase) c);
            checkdoctorid.CheckCurrentDoctorID();

        }
        JSONObject obj = new JSONObject();
        obj.put("msg","ok");
        return obj.toJSONString();

    }
}
