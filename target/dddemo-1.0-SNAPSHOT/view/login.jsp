<%--
  Created by IntelliJ IDEA.
  User: stephen
  Date: 2018/5/26
  Time: 下午3:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh" class="no-js">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登陆表单</title>
    <link rel="stylesheet" type="text/css" href="../static/css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="../static/css/fonts/font-awesome-4.2.0/css/font-awesome.min.css" />
    <link rel="stylesheet" type="text/css" href="../static/css/demo.css" />
    <link rel="stylesheet" type="text/css" href="../static/css/component.css" />
    <!--[if IE]>
    <script src="http://libs.useso.com/js/html5shiv/3.7/html5shiv.min.js"></script>
    <![endif]-->
    <script type="text/javascript" src="../static/js/jquery-3.3.1.min.js"></script>
</head>
<body>
<div class="container" >


    <section class="content bgcolor-5">
        <form id="sign_up_form" name="login_form" class="form" action="/dddemo/userlogin/login" method="post">
            <h2>患者登陆</h2>
            <span class="input input--minoru">
					<input class="input__field input__field--yoko" type="text" id="input-16" name="login_form_IdCard"/>
					<label class="input__label input__label--yoko" for="input-16">
						<span class="input__label-content input__label-content--yoko">身份证号</span>
					</label>
            </span>
            <span class="input input--minoru">
					<input class="input__field input__field--yoko" type="text" id="input-17" name="login_form_phoneNumber"/>
					<label class="input__label input__label--yoko" for="input-17">
						<span class="input__label-content input__label-content--yoko">手机号</span>
					</label>
            </span>
            <span class="input input--minoru">
					<input class="input__field input__field--yoko" type="text" id="input-18" name="validation_code"/>
					<label class="input__label input__label--yoko" for="input-18">
						<span class="input__label-content input__label-content--yoko">验证码</span>
					</label>
            </span>
            <input type="button" style="position:relative" onclick="getValiCode()" value="点击发送验证码">
            <input type="button" id="login_form_submit" onclick="finalSubmit()" value="提交"  >
        </form>
    </section>

</div><!-- /container -->
<script src="../static/js/classie.js"></script>
<script>
    (function() {
        // trim polyfill : https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String/Trim
        if (!String.prototype.trim) {
            (function() {
                // Make sure we trim BOM and NBSP
                var rtrim = /^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g;
                String.prototype.trim = function() {
                    return this.replace(rtrim, '');
                };
            })();
        }

        [].slice.call( document.querySelectorAll( 'input.input__field' ) ).forEach( function( inputEl ) {
            // in case the input is already filled..
            if( inputEl.value.trim() !== '' ) {
                classie.add( inputEl.parentNode, 'input--filled' );
            }

            // events:
            inputEl.addEventListener( 'focus', onInputFocus );
            inputEl.addEventListener( 'blur', onInputBlur );
        } );

        function onInputFocus( ev ) {
            classie.add( ev.target.parentNode, 'input--filled' );
        }

        function onInputBlur( ev ) {
            if( ev.target.value.trim() === '' ) {
                classie.remove( ev.target.parentNode, 'input--filled' );
            }
        }
    })();


    function finalSubmit() {
        var form = $("#sign_up_form");
        form.submit();
    }
    function getValiCode()
    {
        if($("#input-16").val()==null) {
            alert("请输入身份证号");
            return;
        }

        else if($("#input-17").val()==null){
            alert("请输入手机号");
            return;
        }
        else{
            var phone = $("#input-17").val();
            var info = new Object();
            info.phone=phone;
            $.ajax({
                async:false,
                type:'post',
                url:"/dddemo/sendValCode/getCode/",
                contentType:'application/json;charset=utf-8',
                data:JSON.stringify(info),
                success:function(data){
                    var msg = data;
                    if(msg=="OK")
                        alert("信息已发送，请查收");
                    else
                        alert("请重试");
                }
            });
        }
    }




</script>
</body>
</html>

