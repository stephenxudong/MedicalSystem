package test;
import example.pojo.Node;
import org.apache.ibatis.session.SqlSession;
import example.pojo.User;
import example.service.GynaecologyLogic;

import java.util.ArrayList;
import java.util.List;

public class UserTest {
    static GynaecologyLogic g=new GynaecologyLogic();
    public static void main(String[] args) {
           SqlSession session = SFactory.getSqlSession();
        List<Integer> temp = new ArrayList<>();
           g.findNodesById(temp,3);
           List<Node> nodes = new ArrayList<>();
         for(int t:temp){
            Node node = session.selectOne("mapper.node.findByNodeId", t);
            nodes.add(node);
         }
            User user = session.selectOne("mapper.node.findByNodeId", 7);
            session.commit();
            System.out.println(user.getUsername());
         User user1;
         SqlSession session1 = SFactory.getSqlSession();
          user1=session1.selectOne("mapper.UserMapper.findByName","ANNA");
        session1.commit();
        System.out.println(user1.getPassword());
        session1.close();
            session.close();

    }
}
