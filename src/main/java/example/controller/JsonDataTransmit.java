package example.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.LinkedHashMap;

@Controller
public class JsonDataTransmit {


    /**
    @RequestMapping(value="/transmitCurrent",method= RequestMethod.POST)
    public @ResponseBody
    HashMap<String,Object> getData(@RequestBody Question question)
    {

        //保存当前的问题和答案，需要维护一个list
        //list.add(question)

        // 获得下一个问题，可以把当前问题作为参数，查找数据库
        Node node = new Node();//query next node
        Question_answer question_answer = new Question_Answer();//answer related
        HashMap<String,Object> map = new LinkedHashMap<>();
        // 封装在map中返回
        Question question = new Question();
        question.setQues(node.getQuestion);
        question.setAnswer(question_answer);
        map.put("data",question);

        map.put("status","1");
        // if(this is the last question)map.put("msg","this is the last");
        // else
        map.put("msg","");

        return map;

    }
    */
}
