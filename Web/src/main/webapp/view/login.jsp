<%--
  Created by IntelliJ IDEA.
  User: stephen
  Date: 2018/5/26
  Time: 下午3:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0,  user-scalable=no">
    <script src="https://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

    <title>login</title>
    <link rel="stylesheet" type="text/css" href="../static/css/styleslogin.css">
    <script type="text/javascript" src="../static/js/login.js"></script>
</head>
<body>
<div class="htmleaf-container">
    <div class="wrapper">
        <div class="container">
            <h1>智能诊疗系统</h1>


        <form id="sign_up_form" name="login_form" class="form" action="/demo/userlogin/login" method="post">
            <input type="text" placeholder="输入身份证号" id="ident" name="login_form_IdCard" onfocus="" onblur="checkident()"/>
            <span id="span1" style="font-size: 5px; font-weight: bold ">&nbsp</span>
            <input type="text" placeholder="手机号" id="iphone" name="login_form_phoneNumber" onfocus="" onblur="checkphone()" />
            <span id="span2" style="font-size: 5px; color: crimson">&nbsp</span>
            <div class=".divv" style="width: 250px; margin: 0 auto 10px auto;">
                <input type="text" placeholder="验证码" id="icheck" name="validation_code" style="width:120px; display: inline-block" onblur="checkthis()" >
                <button type="button" id="button1" style="width: 120px" onclick="sendMessages();getValiCode()"><span class="buttonfont">获取验证码</span></button>
            </div>
            <button type="submit" id="login-button" style="font-weight: 500" onclick="finalSubmit()">登陆</button>
        </form>
        </div>
            <ul class="bg-bubbles">
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
            </ul>
        </div>
    </div>
    <script>
        $('#login-button').click(function (event) {
            event.preventDefault();
            $('form').fadeOut(500);
            $('.wrapper').addClass('form-success');
            $('h1').text("欢迎使用");
        });
    </script>


</div><!-- /container -->

//原代码
<%--<script>--%>
    <%--(function() {--%>
        <%--// trim polyfill : https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String/Trim--%>
        <%--if (!String.prototype.trim) {--%>
            <%--(function() {--%>
                <%--// Make sure we trim BOM and NBSP--%>
                <%--var rtrim = /^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g;--%>
                <%--String.prototype.trim = function() {--%>
                    <%--return this.replace(rtrim, '');--%>
                <%--};--%>
            <%--})();--%>
        <%--}--%>

        <%--[].slice.call( document.querySelectorAll( 'input.input__field' ) ).forEach( function( inputEl ) {--%>
            <%--// in case the input is already filled..--%>
            <%--if( inputEl.value.trim() !== '' ) {--%>
                <%--classie.add( inputEl.parentNode, 'input--filled' );--%>
            <%--}--%>

            <%--// events:--%>
            <%--inputEl.addEventListener( 'focus', onInputFocus );--%>
            <%--inputEl.addEventListener( 'blur', onInputBlur );--%>
        <%--} );--%>

        <%--function onInputFocus( ev ) {--%>
            <%--classie.add( ev.target.parentNode, 'input--filled' );--%>
        <%--}--%>

        <%--function onInputBlur( ev ) {--%>
            <%--if( ev.target.value.trim() === '' ) {--%>
                <%--classie.remove( ev.target.parentNode, 'input--filled' );--%>
            <%--}--%>
        <%--}--%>
    <%--})();--%>


    <%--function finalSubmit() {--%>
        <%--var form = $("#sign_up_form");--%>
        <%--form.submit();--%>
    <%--}--%>

<%--</script>--%>
</body>
</html>

