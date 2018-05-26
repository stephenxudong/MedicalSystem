package example.dao;

import example.pojo.Node;
import example.service.GynaecologyLogic;
import example.uitl.SFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class QueryNext {
    static GynaecologyLogic g = new GynaecologyLogic();
    static SqlSession session = null;
    private NodeMapper mapper;
    private List<Node> modelNodes = new ArrayList<>();


    public QueryNext(int model_id)
    {
        session = SFactory.getSqlSession();
        mapper = session.getMapper(NodeMapper.class);
        //对于每个模型，只查询一次数据库
        if(modelNodes.size()==0)modelNodes = mapper.selectNodesByModelId(model_id);
    }
    public List<Node> getModelNodes() {
        return modelNodes;
    }

    public void setModelNodes(List<Node> modelNodes) {
        this.modelNodes = modelNodes;
    }

    public  List<Node> findNext(int id)
    {
        List<Integer> temp = new ArrayList<>();
        g.findNodesById(temp,id);
        List<Node> nodes = new ArrayList<>();
        try {
            if(id==0)
                nodes.add(find(0));
            for(int t:temp){
                Node node =find(t);
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
    private Node find(int id)
    {
        if(id==0)
            return modelNodes.get(0);//返回当前model中id最小的节点
        for(Node node:modelNodes)
        {
            if(node.getNode_id().equals(String.valueOf(id)))
                return node;
        }
        return null;
    }

}
