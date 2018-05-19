package test;
import example.dao.GynaecologyCaseMapper;
import example.pojo.Node;
import example.pojo.medicalCase.GynaecologyCase;
import org.apache.ibatis.session.SqlSession;
import example.service.GynaecologyLogic;

import java.util.ArrayList;
import java.util.List;

public class UserTest {
    //static GynaecologyLogic g=new GynaecologyLogic();
    public static void main(String[] args) {
        SqlSession session= null;
        GynaecologyCase gynaecologyCase = null;
           try
           {
               session = SFactory.getSqlSession();
               List<Integer> temp = new ArrayList<>();
               GynaecologyCaseMapper mapper = session.getMapper(GynaecologyCaseMapper.class);
                gynaecologyCase = mapper.findByCaseId("1");
                List<String> ggcase = mapper.selectUndeliveredGynaecologyCase("1");
//                ggcase.setCase_id("2");
//                ggcase.setDoctor_account_id("1");
//                ggcase.setPatient_account_id("1");
//                ggcase.setBearing_history("one child");
//                ggcase.setStatus("false");
//                ggcase.setMain_case("no some case");
//                ggcase.setPresent_history("none present");
//                ggcase.setPast_history("no no past");
//                ggcase.setPersonal_case("一点小问题");
//                mapper.insertGynaecologyCase(ggcase);
                session.commit();
           }catch (Exception e)
           {
               e.printStackTrace();
           }finally {
               session.close();
           }

           //g.findNodesById(temp,3);
           List<Node> nodes = new ArrayList<>();
//         for(int t:temp){
//            Node node = session.selectOne("mapper.NodeMapper.findByNodeId", t);
//            nodes.add(node);
//         }
//            User user = session.selectOne("mapper.NodeMapper.findByNodeId", 7);
//            session.commit();
//            System.out.println(user.getUsername());
//         User user1;
//         SqlSession session1 = SFactory.getSqlSession();
//          user1=session1.selectOne("mapper.UserMapper.findByName","ANNA");
//        session1.commit();
//        System.out.println(user1.getPassword());
//        session1.close();
//            session.close();

    }
}
