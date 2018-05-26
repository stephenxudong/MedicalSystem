package example.service;

import java.util.List;

import example.dao.Node_edgeMapper;
import example.pojo.ENode;
import example.pojo.Node_edge;
import example.pojo.VNode;
import example.uitl.SFactory;
import org.apache.ibatis.session.SqlSession;

public class GynaecologyLogic {
    private SqlSession session;
    private Node_edgeMapper mapper;
    //链表顶点

    public VNode[] getHead() {
        return head;
    }

    //顶点结点的数组
    private VNode[] head;


    public GynaecologyLogic(){
        session = SFactory.getSqlSession();
        mapper = session.getMapper(Node_edgeMapper.class);
        List<Node_edge> nodeEdges = mapper.selectAllNode();
        construct(nodeEdges);
    }

    /**
     * 请务必保证传入用来储存编号的list是已经初始化的
     * @param list 储存需要显示在下一题的问题和答案的编号
     * @param id 本题回答的编号
     */
    public void findNodesById(List<Integer> list,int id){

        ENode efirst=head[id].firstEdge;

        if(efirst!=null){
            list.add(efirst.nodeNumber);
            if(efirst.nextEdge==null)
            {
                findNodesById(list,efirst.nodeNumber);
            }
            while((efirst=efirst.nextEdge)!=null){
                list.add(efirst.nodeNumber);
            }

        }

    }

    public int  findNextBlankQues(int id){
        return head[id].firstEdge.nodeNumber;

    }

    public void construct(List<Node_edge> nodeEdges)
    {
        this.head = new VNode[nodeEdges.size()];

        for(int i=0;i<nodeEdges.size();i++)
        {
            head[i]=new VNode();
            head[i].verNum = Integer.valueOf(nodeEdges.get(i).getNode_id());
            String[] arcs = nodeEdges.get(i).getEdges().split("#");//定义的分隔符
            ENode[] eNodes = new ENode[arcs.length];
            if(arcs[0].equals("null"))return;
            for (int j = 0; j <arcs.length ; j++)
                eNodes[j] = new ENode();
            for (int j = 0; j <arcs.length ; j++) {
                eNodes[j].nodeNumber = Integer.valueOf(arcs[j]);
                if(j!=(arcs.length-1))
                    eNodes[j].nextEdge = eNodes[j+1];
                if(j==arcs.length-1)
                    eNodes[j].nextEdge=null;

            }
            head[i].firstEdge = eNodes[0];//第一条边和node连起来
        }
    }

}


