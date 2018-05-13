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
    private static final Log logger = LogFactory.getLog(JsonDataTransmit.class);
    static GynaecologyLogic g=new GynaecologyLogic();
    //String改成服务器返回的类型
    //answerMap类型改成想要的，自定义对象也可以List<A>或者A a
    //前端ajax注意事项如下
    //contentType: 'application/json', 这句不加出现415错误:Unsupported Media Type
    //data: JSON.stringify(obj), 以json字符串方式传递
    @RequestMapping(value="/transmitCurrent",method= RequestMethod.POST )
    public
    @ResponseBody String uplad(@RequestBody  Map<String,Object> current,HttpServletResponse response
                         )throws Exception{
        response.setCharacterEncoding("UTF-8");
        JSONObject temp=new JSONObject();
        //前端是创建json(current)的时候，只需要提供当前的回答的编号（在后台对应的）
        logger.info("=====================================");
        logger.info("current node is "+ current.toString());
        logger.info("=====================================");
        int id = (int)current.get("id");//此项id也就是编号，通过这个编号在模型中查找下一个问题和答案
        List<Node> nodes = findNext(id);
        temp.put("data",nodes);

        temp.put("status","1");
        temp.put("msg","zhh is my son");
        logger.info(temp.toString());
        return temp.toJSONString();
    }

    public List<Node>findNext(int id)
    {
        List<Integer> temp = new ArrayList<>();
        g.findNodesById(temp,id);
        List<Node> nodes = new ArrayList<>();
        session = SFactory.getSqlSession();
        for(int t:temp){
            Node node = session.selectOne("mapper.node.findByNodeId", t);
            nodes.add(node);
        }
        return nodes;
    }


}
