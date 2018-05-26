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
    <title>illreport</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0,  user-scalable=no">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="../static/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="../static/js/transData.js"></script>
    <style>
        img{
            width: 100%;
            height: 100%;
        }
        .on5{
            position: absolute;
            top: 37%;
            left:0;
            right:0;
            width: 23%;
            height: 63%;
        }
        .afterturn{
            display: none;
        }

        .change{
            transform:rotateX(180deg);
            -ms-transform:rotateX(180deg); /* IE 9 */
            -webkit-transform:rotateX(180deg);
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
            perspective: 1000px;
            -webkit-perspective: 1000px;
            margin-left: auto;
            margin-right: auto;
            position: absolute;
            top: 370px; left: 0; bottom: 0; right: 0;
            width:150px;
            height:180px;
            /*transition: 0.5s;*/
            transform-style: preserve-3d;
            /*border:3px solid #e9d0ce;*/
            font-family: 时尚中黑简体;
        }
        .on2{
            position: absolute;
            left:3%;
            width: 100%;
            top:40%;
            height: 57%;
            /*background-color: #ffbdbc;*/
            /*opacity: 0.3;*/
            padding: 1%;
            display: flex;
            flex-direction:column;
            overflow: hidden;


        }
        .on3 .on1{
            position: relative;
            left: -35%;
            width:100%;
            height:15%;
            /*background-color: #ffffcc;*/
            border: 2px solid #ffffcc;
            margin:auto;

            text-align: right;
            line-height: 150%;
            border-radius:20px;
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
            color: #011935;
        }
        .thisone{
            opacity: 0;
        }
        .active{
            background-color: #ffffcc;
        }
    </style>
    <script type="text/javascript">
        //初始按钮
        $(document).ready(function(){
            $(".qqqq").click(function(){
                generate(this);
                thatclick();clicklalala();
                $(".qqqq").fadeOut("fast");
            });
        });
        localStorage.setItem("user","zhh");
    </script>

</head>
<body style="background-color: #996666;">
<div class="bigone">
    <div class="on3" id="in3">
        <div style="position:absolute;height:62.5%;">
            <img src="../static/image/before.png" >
        </div>
        <!--<div class="on4">-->
        <!--<div class="on5">-->
        <!--<img src="./img/arm.png">-->
        <!--</div>-->
        <!--</div>-->
    </div>
</div>
<div class="qqqq" style="background-color: rgba(255,235,204,0.2);
      font-size: 400%;font-family: 微软雅黑;text-align:center;padding-top:5%;position: absolute;margin-left: auto;margin-right: auto;width:100%;
       height:34%;top: 33%; left: 0; bottom: 0; right: 0;">点我开始</div>
</body>
</html>
