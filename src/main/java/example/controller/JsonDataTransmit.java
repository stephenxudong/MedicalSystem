package example.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import example.service.GynaecologyLogic;
import org.apache.commons.logging.*;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import example.sqlsession.SFactory;
import example.pojo.*;

@Controller
@RequestMapping("/dataTrans")
public class JsonDataTransmit {
    SqlSession session = null;
    private HashMap<String,Object> quesAndAns = new HashMap<>();//用来存储每个题回答的问题编号和答案编号
    private static final Log logger = LogFactory.getLog(JsonDataTransmit.class);
    static GynaecologyLogic g=new GynaecologyLogic();

    @RequestMapping(value="/transmitCurrent",method= RequestMethod.POST )
    public
    @ResponseBody String upload(@RequestBody  Map<String,Object> current,HttpSession session
    )throws Exception{
        JSONObject temp=new JSONObject();
        //前端是创建json(current)的时候，只需要提供当前的回答的编号（在后台对应的）
        logger.info("=====================================");
        logger.info("current node is "+ current.toString());
        logger.info("=====================================");
        int ques_index=(int) current.get("ques_index");
        String ques_id = (String) current.get("ques_node_id");
        //此项id也就是编号，通过这个编号在模型中查找下一个问题和答案
        String ans_id = (String)current.get("answer_node_id");
        if(ques_index!=0)
        {
            //将每一题的问题和答案存到一个hashmap中
            quesAndAns.put("q"+String.valueOf(ques_index),Integer.valueOf(ques_id));
            quesAndAns.put("a"+String.valueOf(ques_index),Integer.valueOf(ans_id));
        }
        if(current.get("msg").equals("over"))//前段表示是最后一个问题和回答
            return null;
        List<Node> nodes = findNext(Integer.valueOf(ans_id));
        temp.put("data",nodes);
        temp.put("status","1");
        if((nodes.size()>1&&nodes.get(0).getComment().equals("last_ques"))||nodes.size()==0)
            temp.put("msg","last_ques");
        else temp.put("msg","zhh is my son");
        logger.info(temp.toString());
        return temp.toJSONString();
    }

    public List<Node>findNext(int id)
    {
        List<Integer> temp = new ArrayList<>();
        g.findNodesById(temp,id);
        List<Node> nodes = new ArrayList<>();
        session = SFactory.getSqlSession();
        if(id==0)
            nodes.add(session.selectOne("mapper.node.findByNodeId", 0));
        for(int t:temp){
            Node node = session.selectOne("mapper.node.findByNodeId", t);
            nodes.add(node);
        }
        return nodes;
    }


}
