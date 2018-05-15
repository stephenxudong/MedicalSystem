package example.service;

import java.util.ArrayList;
import java.util.List;

import example.dao.node_edgeMapper;
import example.pojo.Node_edge;
import example.sqlsession.SFactory;
import org.apache.ibatis.session.SqlSession;

public class GynaecologyLogic {
    private SqlSession session;
    private node_edgeMapper mapper;
    //链表顶点
    private class ENode{
        //邻接顶点的序号
        int nodeNumber;
        //下一个边结点
        ENode nextEdge;
    }
    //表顶点
    private class VNode {
        //顶点序号
        int verNum;
        //链表头指针
        ENode firstEdge;
    }
    //顶点结点的数组
    private VNode[] head;
    //边结点的数组
    private ENode[] edge;

    public GynaecologyLogic(){

//        //初始化所有顶点，标号为0-11
//        for(int i=0;i<12;i++){
//            head[i]=new VNode();
//            head[i].verNum=i;
//        }
//        for(int a=0;a<13;a++){
//            edge[a]=new ENode();
//        }
//        head[0].firstEdge=edge[0];
//        edge[0].nodeNumber=1;
//        edge[0].nextEdge=edge[1];
//
//
//        edge[1].nodeNumber=2;
//        edge[1].nextEdge=edge[2];
//
//        edge[2].nodeNumber=3;
//        edge[2].nextEdge=null;
//
//        head[1].firstEdge=edge[3];
//        edge[3].nodeNumber=4;
//        edge[3].nextEdge=null;
//
//        head[2].firstEdge=edge[4];
//        edge[4].nodeNumber=4;
//        edge[4].nextEdge=null;
//
//        head[3].firstEdge=edge[5];
//        edge[5].nodeNumber=7;
//        edge[5].nextEdge=null;
//
//        head[4].firstEdge=edge[6];
//        edge[6].nodeNumber=5;
//        edge[6].nextEdge=edge[7];
//
//        edge[7].nodeNumber=6;
//        edge[7].nextEdge=null;
//
//        head[4].firstEdge=edge[6];
//        edge[6].nodeNumber=5;
//        edge[6].nextEdge=edge[7];
//
//        head[5].firstEdge=null;
//
//        head[6].firstEdge=edge[8];
//        edge[8].nodeNumber=7;
//        edge[8].nextEdge=null;
//
//        head[7].firstEdge=edge[9];
//        edge[9].nodeNumber=8;
//        edge[9].nextEdge=edge[10];
//
//        edge[10].nodeNumber=9;
//        edge[10].nextEdge=edge[11];
//
//        edge[11].nodeNumber=10;
//        edge[11].nextEdge=edge[12];
//
//        edge[12].nodeNumber=11;
//        edge[12].nextEdge=null;
//
//        head[8].firstEdge=null;
//
//        head[9].firstEdge=null;
//
//        head[10].firstEdge=null;
//
//        head[11].firstEdge=null;

        session = SFactory.getSqlSession();
        mapper = session.getMapper(node_edgeMapper.class);
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

    public void construct(List<Node_edge> nodeEdges)
    {
        this.head = new VNode[nodeEdges.size()];

        for(int i=0;i<nodeEdges.size();i++)
        {
            head[i]=new VNode();
            head[i].verNum = Integer.valueOf(nodeEdges.get(i).getNode_id());
            String[] arcs = nodeEdges.get(i).getEdges().split("#");//定义的分隔符
            ENode[] eNodes = new ENode[arcs.length];
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