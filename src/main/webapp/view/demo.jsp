<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>illreport</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0,  user-scalable=no">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="../static/js/jquery-3.3.1.min.js"></script>
    <script>
        function scrollToEnd(){
            var h = $(document).height()-$(window).height();
            $(document).scrollTop(h);
        }
    </script>
    <script type="text/javascript">
        //封装成json的数据

        //var cases = new Array("外阴瘙痒:","重","中","轻");
        var quesNum=1;//用于设置当前的问题的id
        var currentModeNodeId = 1;//保存后台传来的node_id


        function generate() {
            var _ques = new Object();
            var currId = "q"+quesNum.toString();
            alert(currId);

            if(quesNum != 1)
            {
                //选择器的一点bug
                alert("ques!=1 we enter");
                var selector = "#q"+(quesNum-1).toString();
                alert(selector);
                //当前的quesNum指向的问题还没有产生，所以需要先减一
                alert($(selector).html());
                _ques.question_content = $(selector).html().split(" ")[1];
                alert(_ques.question_content);
                _ques.model_id = "1";
                _ques.node_id = currentModeNodeId;
                _ques.question_answer =$(this).html();
                alert("currrent answer is "+_ques.question_answer);
            }

            if(quesNum==1) _ques.msg="first question";
            else _ques.msg="not first one";
            alert(JSON.stringify(_ques));
            $.ajax({
                type:'post',
                url:"/dddemo/dataTrans/transmitCurrent/",
                contentType:'application/json;charset=utf-8',
                data:JSON.stringify(_ques),
                success:function(data){
                    alert(data);
                    var parsedata = jQuery.parseJSON(data);
                    /** 接下来获取下一题需要的信息
                     * 后台返回的是jsonString
                     * data：model_node（包含问题和答案等）
                     * status:一般都是1，表示成功
                     * msg：如果是查询不到题了（也就是刚刚传递给后台最后一题），那么后台设置msg为"no more question"
                     */
                    if(data.msg=="no more question")
                    {
                        //code to trans all question and answer,then return from this function
                        return;
                    }
                    var question = parsedata.data.question_content;
                    var answer = parsedata.data.question_answer;
                    var ans = new Array();
                    for(var i = 0;i<answer.length;i++)
                        ans.push(answer[i].answer);
                    alert("this is question"+question.toString());
                    currentModeNodeId=question.node_id;
                    alert("this is answer"+ ans.toString());
                    addcase(question,ans);

                }

            })

            $(".ci").hide();
            scrollToEnd();
        }

        function addcase(ques,ans){
            alert("add next question");
            var answerText=new Array();
            for(var a=0;a<ans.length;a++) {
                answerText[a]= "<div class=\"btn-group\" data-toggle=\"buttons\" >\n" +
                    "  <label class=\"btn btn-primary active\">\n" +
                    "    <input type=\"radio\" name=\"options\" id='a" +quesNum+ "' onclick='generate()' autocomplete=\"off\">"+ans[a]+
                    "  </label>\n" +
                    "</div>";
            }
            var nextone="<div id='t"+quesNum+"'>\n" +
                "  <p class='question' id='q"+quesNum+"'>"+"第"+quesNum+"题"+" "+ques+"</p>\n" +answerText+
                "</div>";
            $(".content").append(nextone);
            quesNum++;
        }

    </script>

</head>


<body>

<button class="ci" onclick="generate()">点我开始</button>
<div class="content"></div>


</body>
<script>

</script>
</html>

