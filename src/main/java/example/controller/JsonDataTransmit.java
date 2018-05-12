package example.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.*;
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

import example.pojo.*;

@Controller
@RequestMapping("/dataTrans")
public class JsonDataTransmit {

    private static final Log logger = LogFactory.getLog(JsonDataTransmit.class);

    //String改成服务器返回的类型
    //answerMap类型改成想要的，自定义对象也可以List<A>或者A a
    //前端ajax注意事项如下
    //contentType: 'application/json', 这句不加出现415错误:Unsupported Media Type
    //data: JSON.stringify(obj), 以json字符串方式传递
    @RequestMapping(value="/transmitCurrent",method= RequestMethod.POST )
    public
    @ResponseBody String uplad(@RequestBody  model_node current,HttpServletResponse response
                         )throws Exception{
        response.setCharacterEncoding("UTF-8");
        JSONObject temp=new JSONObject();
        //如果是json字符串
        //利用answeMap和temp生成想要的数据

        //如果是json字符数组
        logger.info("=====================================");
        logger.info("current node is "+ current.toString());
        logger.info("=====================================");
        model_node node = new model_node();//query next node
        question_answer question_answer = new question_answer();//answer related
        node.setQuestion_content("外阴瘙痒");
        node.setAnswer_type("singleselection");
        List<question_answer> answers = new ArrayList<>();
        question_answer answer = new question_answer();
        answer.setAnswer("轻");
        question_answer answer1 = new question_answer();
        answer1.setAnswer("重");
        answers.add(answer);
        answers.add(answer1);

        node.setQuestion_answer(answers);
        temp.put("data",node);

        temp.put("status","1");
        temp.put("msg","zhh is my son");
        logger.info(temp.toString());
        return temp.toJSONString();
    }

//    @RequestMapping(value="/transmitCurrent",method= RequestMethod.POST)
//    public @ResponseBody
//    HashMap<String,Object> getData(@RequestBody model_node current)
//    {
//
//        //保存当前的问题和答案，需要维护一个list
//        //list.add(question)
//
//        // 获得下一个问题，可以把当前问题作为参数，查找数据库
//
//        // if(this is the last question)map.put("msg","this is the last");
//        // else
//        map.put("msg","");
//
//        return map;
//
//    }

}
