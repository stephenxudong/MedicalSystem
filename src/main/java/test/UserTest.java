package test;
import example.dao.doctor_accountMapper;
import example.dao.medical_caseMapper;
import example.dao.node;
import example.pojo.Node;
import example.pojo.doctor_account;
import example.pojo.medical_case;
import org.apache.ibatis.session.SqlSession;
import example.service.GynaecologyLogic;

import java.util.ArrayList;
import java.util.List;

public class UserTest {
    static GynaecologyLogic g=new GynaecologyLogic();
    public static void main(String[] args) {
           SqlSession session = SFactory.getSqlSession();
//        doctor_accountMapper n = session.getMapper(doctor_accountMapper.class);
//        doctor_account nn=n.loginResponse("陈晓恒","123456");
//        if(nn!=null)
//            System.out.println(nn.getDoctor_name());

//        medical_caseMapper n = session.getMapper(medical_caseMapper.class);
//        List<medical_case>nn=n.refreshDir("1");
//        if(nn!=null){
//            for(medical_case t:nn)
//            System.out.println(t.getDoctor_account_id());
//        }

//        medical_caseMapper n = session.getMapper(medical_caseMapper.class);
//        n.updateResponse("AAA","1","1");


            session.commit();
            session.close();

    }
}
