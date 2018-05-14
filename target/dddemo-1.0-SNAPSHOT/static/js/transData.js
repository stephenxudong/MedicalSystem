function scrollToEnd() {
    var h = $(document).height() - $(window).height();
    $(document).scrollTop(h);
}

//封装成json的数据
var quesNum=1;//用于设置当前的问题的id
var currentQuesId;
function generate(self) {
    var _ques = new Object();
    if(quesNum==1)
    {
        _ques.model_id = "1";//暂时都是一个模型
        _ques.ques_index = 0;
        _ques.answer_node_id="0";
        _ques.msg="not first one";
        _ques.ques_node_id="0";
    }
    else if(quesNum != 1)
    {
        _ques.model_id = "1";//暂时都是一个模型
        _ques.ques_index = (quesNum-1);
        //node_id 需要是后台传递数据的时候的id
        _ques.answer_node_id=$(self).attr("id").substr(1);
        _ques.msg="not first one";
        _ques.ques_node_id=$("#q"+currentQuesId.toString()).attr("id").substr(1);
    }
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
                if(nodes[i].node_type=="question")
                    question = nodes[i];
                if(nodes[i].node_type=="answer")
                    answers.push(nodes[i]);

            }
            addcase(question,answers);

        }
    })

    $(".ci").hide();
    scrollToEnd();
}

function addcase(ques,ans){
    var answerText=new Array();
    for(var a=0;a<ans.length;a++) {
        answerText[a]= "<div class=\"btn-group\" data-toggle=\"buttons\" >\n" +
            "  <label class=\"btn btn-primary active\">\n" +
            "    <input type=\"radio\" name=\"options\" id='a"+ans[a].node_id+ "' onclick='generate(this)' autocomplete=\"off\">"+ans[a].content+
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


