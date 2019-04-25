<%--
  Created by IntelliJ IDEA.
  User: stephen
  Date: 2018/5/18
  Time: 上午9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>病例问卷</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0,  user-scalable=no">
    <script src="https://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

    <link rel="stylesheet" type="text/css" href="../static/css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="../static/css/default.css">
    <link rel="stylesheet" type="text/css" href="../static/css/styles.css">
    <!--[if IE]>
    <script src="http://libs.useso.com/js/html5shiv/3.7/html5shiv.min.js"></script>
    <![endif]-->

    <script type="text/javascript" src="../static/js/HZRecorder.js"></script>
    <script type="text/javascript" src="../static/js/speakcheck.js"></script>
    <script type="text/javascript" src="../static/js/genQuestion.js"></script>
    <script type="text/javascript" src="../static/js/transData.js"></script>
    <link rel="stylesheet" type="text/css" href="../static/css/casecss.css">


</head>
<body onload="firstques()">

<div class="all">
    <div class="modal fade" id="firstquestion" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
        <div class="modal-dialog" role="document" style="top:100px">
            <div class="modal-content">
                <div class="modal-header">
                    <!--<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>-->
                    <h4 class="modal-title" id="myModalLabel">请填写主要症状</h4>
                </div>
                <div class="modal-body answer">
                    <textarea class="form-control" id="firstanswer"  rows="8"></textarea>
                </div>
                <div class="modal-footer tiankong">
                    <audio autoplay></audio>
                    <div id="recordingslist"></div>
                    <div>
                        <button type="button" class="btn btn-default fadeoutt"   onclick="clicktalk(); ">语音输入</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal" onclick='getNodeAndModelbyModelId("1");firout()'>确认</button>
                    </div>
                    <div class="fadeinthis">
                        <span class="spannn">长按录音</span>
                        <button class="click1" onfocus="startcheck()"
                                onblur="endcheck(this)">
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="endModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel2">确认病例</h4>
            </div>
            <div class="modal-body" id="showend">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">返回修改</button>
                <button type="button" class="btn btn-primary">提交</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>
