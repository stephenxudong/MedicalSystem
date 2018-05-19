var name;
var nameb;
var name2;
var addtop=10;
var undertop=370;
var quesNum=1;//用于设置当前的问题的id
var currentQuesId;
function scrollToEnd() {
    var h = $(document).height() - $(window).height();
    $(document).scrollTop(h);
}

//封装成json的数据


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
        _ques.user="zhh";
        _ques.IdtId="123456123456123456";
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
        _ques.user="zhh";

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
        _ques.user="zhh";
    }

    passAndGet(_ques);
}

function passAndGet(_ques) {
    $.ajax({
        async:false,
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
    currentQuesId=ques.node_id;

    var lengths="";

    for(var a=0;a<ans.length;a++) {
        answerText[a]="<div class=\"on1\" >\n" +
            "  <label for=\"a"+ans[a].node_id+"\" style='position:relative;left: -15px'>"+ans[a].content+"</label>\n" +
            "<input type=\"radio\" name=\"thisques"+quesNum+"\" style='position: absolute' id='a"+ans[a].node_id+ "' class=\"thisone\" onclick='generate(this);getthiss(this);thisclick();thatclick();clicklalala();scrollToEnd();'/>\n" +
            "  </div>"
        lengths+=answerText[a];
    }

    var nextone=
        "<div class=\"on3\" id=\"t"+quesNum+"\">\n" +
        "<div class='beforeturn'><img src=\"../static/image/head.png\" style='position: absolute'><img src='../static/image/body.png' style='position: absolute'>\n"+
        "  <p id='q"+ques.node_id+"' class=\"in1\"> "+quesNum+":"+" "+ques.content+"</p>\n" +
        "  <div class=\"on2\">\n"+ lengths+
        "</div>\n"+
        "    <div class=\"on5\">\n" +
        "    <img src=\"../static/image/arm.png\">\n" +
        "    </div>\n" +
        "    </div>\n" +
        "<div class='afterturn'style='z-index: 2;'>"+
        "    <div style=\"position:absolute;height:62.5%;\" style='position: absolute'>\n" +
        "    <img src=\"../static/image/before.png\" >\n" +
        "    </div>"+
        "    </div>\n" +
        "</div>"
    var x=quesNum-1;
    name2='t'+quesNum;
    name ='t'+x;
    nameb='q'+x;
    quesNum++;
    $("body").append(nextone);
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


//改变按钮样式
function getthiss(self){
    $(self).parent().addClass("active");
    $(self).parent().siblings("div").removeClass("active");
}

//新产生表单位置
function thatclick() {
    undertop+=35;
    var nexttoptxt=undertop+'px';
    // var nextundertxt=undertop+60+'px'
    $("#"+name2+"").css("top",nexttoptxt);
    $("#in3").css("top",nexttoptxt);
}

//放大图片
function clicklalala() {
    var nexttoptxt2=undertop-360;
    var nexttopend=nexttoptxt2+'px';
    $("#"+name2+"").animate({
        top:nexttopend,
        width: '300px',
        height:'360px',
        fontSize:'15px',
        background: '100% 100%',

    });
}

//缩小图片缩小标题
function thisclick() {
    var topadd=addtop+'px';
    $("#"+name+"").animate({
        top:topadd,
        width:'150px',
        height:'180px',
        fontSize:'50%',
        boder:'3px'
        // background: '011935'
    });
    $("#"+nameb+"").animate({
        top:'3%',
        fontSize:'13px',
    });
    addtop=addtop+35;
}

