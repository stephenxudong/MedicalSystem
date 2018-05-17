function scrollToEnd() {
    var h = $(document).height() - $(window).height();
    $(document).scrollTop(h);
}

//封装成json的数据
var quesNum=1;//用于设置当前的问题的id
var currentQuesId;
function passAndGet(_ques) {
    $.ajax({
        type:'post',
        url:"/dddemo/dataTrans/transmitCurrent/",
        contentType:'application/json;charset=utf-8',
        data:JSON.stringify(_ques),
        success:function(data){
            var parsedata = jQuery.parseJSON(data);
            /** 接下来获取下一题需要的信息
             * 后台返回的是jsonString
             * data：model_node（包含问题和答案等）
             * status:一般都是1，表示成功
             * msg：如果是查询不到题了（也就是刚刚传递给后台最后一题），那么后台设置msg为"no more question"
             */
            if(parsedata.msg=="last_ques")
            {
                //code to then return from this function
                //需要改变一个generate函数，只传递最后回答题目的信息，不在请求数据，msg设置为"over"
                return;
            }
            var nodes = parsedata.data;
            var question;
            var answers = new Array();
            for(var i = 0;i<nodes.length;i++)
            {
                if(nodes[i].node_type.indexOf("question")>=0)//是一个问题
                    question = nodes[i];
                if(nodes[i].node_type=="answer")
                    answers.push(nodes[i]);

            }
            if(answers.length>0) addChoiceQues(question,answers);//表示是普通的选择型问题
            else if(question.node_type=="blank_question") addBlankQues(question);


        }
    })

    $(".ci").hide();
    scrollToEnd();
}

function addChoiceQues(ques,ans){
    var answerText=new Array();
    for(var a=0;a<ans.length;a++) {
        answerText[a]= "<div class=\"btn-group\" data-toggle=\"buttons\" >\n" +
            "  <label class=\"btn btn-primary active\">\n" +
            "    <input type=\"radio\" name=\"options\" id='a"+ans[a].node_id+ "' onclick='generate(this)' autocomplete=\"off\">"
            +ans[a].content+
            "  </label>\n" +
            "</div>";
    }
    currentQuesId=ques.node_id;
    var nextone="<div id='t"+quesNum+"'>\n" +
        "  <p class='question' id='q"+ques.node_id+"'>"+"第"+quesNum+"题"+" "+ques.content+"</p>\n" +answerText+
        "</div>";
    $(".content").append(nextone);
    quesNum++;
}


function addBlankQues(ques){
    var answerText= "<div>\n" +
        "    <textarea name=\"options\"  id =\"a"+ques.node_id+"\" style=\"width:200px;height:80px;\" ></textarea>\n" +
        "    <button onclick='generate(this)' style=\"position: absolute;left: 30px;top:110px\">点击此处提交</button>\n" +
        "</div>";
    currentQuesId=ques.node_id;
    var nextone="<div id='t"+quesNum+"'>\n" +
        "  <p class='question' id='q"+ques.node_id+"'>"+"第"+quesNum+"题"+" "+ques.content+"</p>\n" +answerText+
        "</div>";
    $(".content").append(nextone);
    quesNum++;
}

function generate(self) {
    var _ques = new Object();
    var type = self.nodeName;
    if(quesNum==1)
    {
        _ques.model_id = "1";//暂时都是一个模型
        _ques.ques_index = 0;
        _ques.answer_node_id="0";
        _ques.msg="first one";
        _ques.ques_node_id="0";
        _ques.ques_content=null;
        _ques.answer_content=null;
    }
    else if(type.toLowerCase()=="button")//表示是填空类型
    {
        _ques.model_id = "1";//暂时都是一个模型
        _ques.ques_index = (quesNum-1);
        //node_id 需要是后台传递数据的时候的id
        _ques.answer_node_id=currentQuesId;
        _ques.msg="not first one";
        _ques.ques_content=$("#q"+currentQuesId.toString()).html().split(" ")[1];
        _ques.answer_content=$("#a"+currentQuesId).val();

    }
    else
    {
        _ques.model_id = "1";//暂时都是一个模型
        _ques.ques_index = (quesNum-1);
        //node_id 需要是后台传递数据的时候的id
        _ques.answer_node_id=$(self).attr("id").substr(1);
        _ques.ques_content=$("#q"+currentQuesId.toString()).html().split(" ")[1];
        _ques.answer_content=$(self).parent().text().replace(/\s+/g,"");
        _ques.msg="not first one";
    }

    passAndGet(_ques);
}