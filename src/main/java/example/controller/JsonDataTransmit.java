package example.controller;

import com.alibaba.fastjson.JSONObject;
import example.bulidMedicalCase.Convert;
import example.dao.QueryNext;
import javafx.util.Pair;
import org.apache.commons.logging.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;
import example.pojo.*;

@Controller
@RequestMapping("/dataTrans")
public class JsonDataTransmit {
    private HashMap<String,HashMap<String,Object>> userInfo = new HashMap<>();
    //用username作为key来存储quesAndAns

    private static final Log logger = LogFactory.getLog(JsonDataTransmit.class);


    @RequestMapping(value="/transmitCurrent",method= RequestMethod.POST )
    public
    @ResponseBody String upload(@RequestBody  Map<String,Object> current,HttpSession session
    )throws Exception{
        logger.info("==================================================================");
        logger.info("served by Thread "+Thread.currentThread().getName());
        logger.info("current NodeMapper is "+ current.toString());
        logger.info("==================================================================");
        JSONObject temp=new JSONObject();
        int ques_index=(int) current.get("ques_index");
        //此项id也就是编号，通过这个编号在模型中查找下一个问题和答案
        String ans_id = (String)current.get("answer_node_id");
        String ques_content = (String)current.get("ques_content");
        String answer_content = (String)current.get("answer_content");
        String currentUser = (String)current.get("user");
        if(ques_index==0)
        {
            String identificationId = (String)current.get("IdtId");
            Pair<String,String> pair = new Pair<>("identificationId",identificationId);
            userInfo.get("user").put("idCardId",identificationId);
        }
        if(ques_index!=0)
        {
//            ReturnAnswer retrunAnswer = new ReturnAnswer();
//            retrunAnswer.setQues_content(ques_content);
//            retrunAnswer.setAnswer_content(answer_content);
//            retrunAnswer.setQues_index(ques_index);
            if(userInfo.get(currentUser)==null)
            {

                HashMap<String,Object> currentUserInfo = new HashMap<>();//储存用户信息的map
                userInfo.put(currentUser,currentUserInfo);
            }
            else if(userInfo.get(currentUser).get("current_ques_ans")==null)
            {
                //用来存储每个题回答的问题编号和答案编号
                List<Pair<String,String>> pairs = new ArrayList<>();
                userInfo.get(currentUser).put("current_ques_ans",pairs);
            }
            userInfo.get(currentUser).put(ques_content,answer_content);
        }
        if(current.get("msg").equals("over"))//前段表示是最后一个问题和回答
        {
            HashMap<String,Object> currentUserInfo = userInfo.get(currentUser);
            Convert.convert(currentUserInfo);//传输给处理病历的类，处理之后存数据库
            return null;
        }
        List<Node> nodes = QueryNext.findNext(Integer.valueOf(ans_id));
        temp.put("data",nodes);
        temp.put("status","1");
        if((nodes.size()>1&&nodes.get(0).getComment().equals("last_ques"))||nodes.size()==0)
            temp.put("msg","last_ques");
        else temp.put("msg","zhh is my son");
        logger.info(temp.toString());
        return temp.toJSONString();
    }

    private class ReturnAnswer
    {
        private String answer_content;
        private String ques_content;
        private int ques_index;

        public String getAnswer_content() {
            return answer_content;
        }

        public void setAnswer_content(String answer_content) {
            this.answer_content = answer_content;
        }

        public String getQues_content() {
            return ques_content;
        }

        public void setQues_content(String ques_content) {
            this.ques_content = ques_content;
        }

        public int getQues_index() {
            return ques_index;
        }

        public void setQues_index(int ques_index) {
            this.ques_index = ques_index;
        }


    }


}

