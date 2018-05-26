<%--
  Created by IntelliJ IDEA.
  User: stephen
  Date: 2018/5/18
  Time: 上午9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <title>illCase</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0,  user-scalable=no">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <%--<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>--%>
    <script src="../static/js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="../static/js/transData.js"></script>
    <style>
        img{
            width: 100%;
            height: 100%;
        }
        .on5{
            position: absolute;
            top: 30%;
            left:0;
            right:0;
            width: 23%;
            height: 70%;
            background-color: azure;
        }
        .afterturn{
            display: none;
        }
        .beforeturn,.afterturn{
            backface-visibility: hidden;
            position: absolute;
            height: 100%;
            width: 100%;
            top: 0;
            left: 0;
        }
        .on3{
            margin-left: auto;
            margin-right: auto;
            position: absolute;
            background-color: rgba(255,255,255,0);
            top: 430px; left: 0; bottom: 0; right: 0;
            width:150px;
            height:180px;
            font-family: "微软雅黑 Light";
            font-size: 30px;
            font-weight: bold;
            color: white;
        }
        .on2{
            position: absolute;
            -webkit-justify-content: center;
            justify-content: center;
            height: 70%;
            width: 100%;
            top:30%;
            flex-direction:column;
        }
        .on1{
            position: absolute;
            width:100%;
            height:15%;
            font-weight: normal;
            font-size: 16px;
            background-color: #fffde9;
            text-align: center;
            line-height: 200%;
            overflow: hidden;
            box-sizing: border-box;
        }

        .in2{
            position:absolute;
            top: 30%;
            width:100%;
            height:3px;
            background-color: #e9b9b9;
        }
        .in1{
            position: absolute;
            left: 5%;
            top:20%;
            font-size: 20px;
        }
        .thisone{
            opacity: 0;
        }
        .active{
            color:#ffffff;
        }
        .downpic{
            position: fixed;
            width: 200%;
            height: 100%;
            left: -50%;
            top: 50%;
            z-index: 7;
            pointer-events: none;
            text-align: center;
        }
        .imgs2{
            position: absolute;
            max-height: 400px;
            max-width: 400px;
            margin: auto;
            top:0;
            left: 0;
            bottom: 0;
            right: 0;
            transition: all 1s;
        }
        .imgs3{
            position: absolute;
            max-height: 400px;
            max-width: 400px;
            margin: auto;
            top:0;
            left: 0;
            bottom: 0;
            right: 0;
            transition: all 2s;
        }
        .clickit{
            z-index: 3;
        }
    </style>
    <script type="text/javascript">
        //初始按钮
        $(document).ready(function(){
            $(".qqqq").click(function(){
                //generate(this);
                getNodeAndModelbyModelId("1");//1代表妇科
                thatclick();
                clicklalala();
                roundright();
                $(".qqqq").hide();
            });
        });
        localStorage.setItem("user","zhh");
    </script>

</head>
<body style="background-color: #e9e9e9;">
<div class="bigone" style="height: 0px">
    <div class="on3" id="in3">
    </div>
</div>

<div class="downpic">
    <img class="imgs3" src="../static/image/round3.png">
    <img class='imgs2' src="../static/image/round12.png">
    <p style="color: #c1523a;font-size: 40px;opacity: 0.6;z-index: 9;font-weight: bolder;margin-top: 35%;">0%</p>
</div>
<div class="qqqq" style="background-color: rgba(255,235,204,0.2);
      font-size: 50px;font-family: 微软雅黑;text-align:center;padding-top:5%;position: absolute;margin-left: auto;margin-right: auto;width:100%;
       height:34%;top: 33%; left: 0; bottom: 0; right: 0;">点我开始</div>
</body>
</html>
