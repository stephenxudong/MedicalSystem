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
        $(function(){
            var _question="外阴瘙痒";
            var _answer=new Array("重","中","轻");
            var _ques = new Object();
            _ques.question_content = _question;
            _ques.question_type = "normal";
            _ques.answer_type = "singleselection";
            _ques.model_id = "1";
            _ques.node_id = "2";
            _ques.question_answer = _answer;

            alert(JSON.stringify(_ques));

            $.ajax({
                type:'post',
                url:"/dddemo/dataTrans/transmitCurrent/",
                contentType:'application/json;charset=utf-8',
                data:JSON.stringify(_ques),
                success:function(data){
                    alert(data);
                    var thecase=data.result;
                    var data1=eval('('+thecase+')');
                }

            })
        });

        var cases = new Array("外阴瘙痒:","重","中","轻");
        var questionnumber=1;
        var answernumber=1;
        function addcase(que,ans){
            var illarray = new Array();
            for(var i=0;i<cases.length;i++)
                illarray[i]=cases[i];
            var nextcase = new Object();
            nextcase.casedata=illarray;
            var answerone=new Array();
            for(var a=0;a<nextcase.casedata.length-1;a++) {
                answerone[a]= "<div class=\"btn-group\" data-toggle=\"buttons\" >\n" +
                    "  <label class=\"btn btn-primary active\">\n" +
                    "    <input type=\"radio\" name=\"options\" id='a" +ans+ "' onclick='qqq()' autocomplete=\"off\">"+nextcase.casedata[a+1]+
                    "  </label>\n" +
                    "</div>";
                ans++;
            }
            var nextone="<div id='t"+que+"'>\n" +
                "  <p id='q"+que+"'>"+"第"+que+"题"+" "+nextcase.casedata[0]+"</p>\n" +answerone+
                "</div>";
            $(".content").append(nextone);
            que++;
            answernumber=ans+nextcase.casedata.length;
            questionnumber=que;
        }
        function qqq() {
            addcase(questionnumber,answernumber);
            $(".ci").hide();
            scrollToEnd();
        }

    </script>

</head>


<body>

<button class="ci" onclick="qqq()">点我开始</button>
<div class="content"></div>


</body>
<script>

</script>
</html>

