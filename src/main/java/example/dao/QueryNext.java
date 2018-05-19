package example.dao;

import example.pojo.Node;
import example.service.GynaecologyLogic;
import example.sqlsession.SFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class QueryNext {
    static GynaecologyLogic g = new GynaecologyLogic();
    static SqlSession session = null;
    private static NodeMapper mapper;

    public QueryNext()
    {


    }
    public static List<Node> findNext(int id)
    {
        session = SFactory.getSqlSession();
        mapper = session.getMapper(NodeMapper.class);
        List<Integer> temp = new ArrayList<>();
        g.findNodesById(temp,id);
        List<Node> nodes = new ArrayList<>();
        try {
            if(id==0)
                nodes.add(mapper.findByNodeId(0));
            for(int t:temp){
                Node node = mapper.findByNodeId(t);
                nodes.add(node);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(nodes.size()>1&&(nodes.get(1).getNode_type().indexOf("question")>=0))//第二个节点也是问题，那么只需要返回一个即可
        {
            List<Node> nodes1 = new ArrayList<>();
            nodes1.add(nodes.get(0));
            return nodes1;
        }
        else return nodes;
    }

}
