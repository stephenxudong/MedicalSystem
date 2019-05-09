package web.controller;
import web.dao.Patient_accountMapper;
import web.pojo.Patient_account;
import org.apache.commons.logging.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.apache.ibatis.session.SqlSession;
import test.SFactory;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/userlogin")
public class UserLogin {
    private static final Log logger = LogFactory.getLog(UserLogin.class);

    @RequestMapping(value = "/login")
    public String register(@RequestParam("login_form_IdCard") String login_form_IdCard,
                           @RequestParam("login_form_phoneNumber") String login_form_phoneNumber,
                           @RequestParam("validation_code")String validation_code,
                           HttpSession session){
        logger.info("login方法调用中");
        SqlSession sqlSession = SFactory.getSqlSession();
        Patient_accountMapper mapper = sqlSession.getMapper(Patient_accountMapper.class);
        Patient_account user=mapper.selectByIdentiId(login_form_IdCard);
        if(user==null){
            sqlSession.commit();
            sqlSession.close();
            return "login";
        }
        String code = (String)session.getAttribute("validationCode");
        if(code.equals(validation_code) &&
                user.getPatient_phone_number().equals(login_form_phoneNumber))
        {
            sqlSession.commit();
            sqlSession.close();
            return "illcase";
        }
        else{
            return "cannot_login";
        }
//        else{
//            if(user.getPassword().equals(login_form_password)){
//                session1.commit();
//                session1.close();
//                model.addAttribute("user",user);
//                session.setAttribute("user",user);
//                return "fir";
//            }
//            else{
//                //logger.info(user.getPassword());
//                logger.info(login_form_password);
//                session1.commit();
//                session1.close();
//                return "product";
//            }
//
//      }

    }
}
