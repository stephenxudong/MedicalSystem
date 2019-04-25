var InterValObj; //timer变量，控制时间
var count = 60; //间隔函数，1秒执行
var curCount; //当前剩余秒数
var codeLength = 4; //验证码长度

function sendMessages() {
    curCount = count;
    var phone=$('#iphone').val();
    if(checkphone()){
        return;
    }
    if(phone != "") {
        $('#button1').html("<span class=\"buttonfont\">"+"<span style='color: #dc4a5f'>"+curCount +"</span>"+ "秒内输入"+"</span>");
        InterValObj = window.setInterval(SetRemainTimes, 1000); //启动计时器，1秒执行一次
    }else {
        alert("手机号码不能为空！");
    }

}
//timer处理函数
function SetRemainTimes() {
    if(curCount == 0) {
        window.clearInterval(InterValObj); //停止计时器
        $('#button1').html("<span class=\"buttonfont\">重新发送</span>")
    }
    else{
        curCount--;
        $('#button1').html("<span class=\"buttonfont\">"+"<span style='color: #dc4a5f'>"+curCount +"</span>"+ "秒内输入"+"</span>");
    }
    }

function getValiCode()
{
        var phone = $("#iphone").val();
        var info = new Object();
        info.phone=phone;
        $.ajax({
            async:false,
            type:'post',
            url:"/demo/sendValCode/getCode/",
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



//检查身份证
function checkident() {
    var curr=$('#ident');
    if(curr.val().trim()==''){$('#span1').text("请输入身份证号");$('#span1').css('color','#8c2645');}
    else if(curr.val().trim()!='') {
        var myreg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
        if (!myreg.test(curr.val())){
            $('#span1').text("请输入正确的身份证号！");
            $('#span1').css('color','#8c2645');
        }
        else {
            $('#span1').text("输入正确");
            $('#span1').css('color','#D3DCD7');
        }
    }
}
//检查手机号
function checkphone() {
    var curr=$('#iphone');
    if(curr.val().trim()==''){$('#span2').text("请输入手机号");$('#span2').css('color','#8c2645');return true;}
    else if(curr.val().trim()!=''){
        var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
        if (!myreg.test(curr.val())){
            $('#span2').text("请输入正确的11位手机号！");
            $('#span2').css('color','#8c2645');
            return true;
        }
        else {
            $('#span2').text("输入正确");
            $('#span1').css('color','#D3DCD7');
            return false;
        }
    }
}
//验证码检查
function checkthis() {
    var curr=$('#icheck');
    if(curr.val().trim()==''){alert("请输入验证码")}
}
