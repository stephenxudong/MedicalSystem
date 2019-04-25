var quesnum=1;

var questionname="张洪瀚的儿子是谁";
var answername=new Array("侯云","陈晓恒","廖旭东","王李翰","李光耀");

function firstques(){
    $('#firstquestion').modal('show');
}
//对准过去的题
$(window).scroll(function(){
    var pwhat=parseInt(($(window).scrollTop() + ($(window).height())/4)/360)+1;
    var thisp='p'+pwhat;
    $("#"+thisp+"").css({opacity:'1'});
    $("#"+thisp+"").siblings().css({opacity:'0.4'});
})

function addChoiceQues(ques,ans){
    currentQuesId = ques.node_id;
    var idd='p'+quesnum;
    var answerText=new Array();
    var lengths="";
    if(ans.length<5) {
        for(var i=0;i<ans.length;i++){
            answerText[i]="<li>\n" +
                "<label>\n" +
                "<input type=\"radio\" id=\"a"+ans[i].node_id+"\"  name=\"q"+quesnum+"\"  class=\"option-input radio\" onclick='generateNext(this);changeViewnext(this)'/>\n" +
                "<span class='spanit'>"+ans[i].content+"</span>" +
                "</label>\n" +
                "</li>"
            lengths+=answerText[i];
        }
    }
    else {
        for(var i=0;i<ans.length;i++){
            answerText[i]="<li style='float: left;\n" +
                " width: 50%;'>\n" +
                "<label>\n" +
                "<input type=\"radio\" id=\"a"+ans[i].node_id+"\"  name=\"q"+quesnum+"\"  class=\"option-input radio\" onclick='generateNext(this);changeViewnext(this)'/>\n" +
                "<span class='spanit'style='margin-left: 0px; font-size: 16px' >"+ans[i].content+"</span>" +
                "</label>\n" +
                "</li>"
            lengths+=answerText[i];
        }
    }

    var nextone="<div class=\"part choiceques\" id=\""+idd+"\">\n" +
        "        <div id=\"q"+quesnum+"\" class=\"question\">\n" +quesnum+ "."+ ques.content +
        "        </div>\n" +
        "        <hr>\n" +
        "        <div class=\"answer\">\n" +
        "            <ul>\n"+lengths+
        "</ul>\n" +
        "        </div>\n" +
        "    </div>"
    $(".all").append(nextone);
    quesnum++;
}
function addBlankQues(ques){
    var idd='p'+quesnum;
    currentQuesId = ques.node_id;
    var nextone="<div class=\"part blankques\" id=\""+idd+"\">\n" +
        "<div id=\"q"+quesnum+"\"  class=\"question\">\n" +quesnum+ "."+ ques.content +
        "</div>\n" +
        "    <hr>\n" +
        "    <div class=\"answer answertext\">\n" +
        "        <textarea class=\"form-control\" rows=\"7\" ></textarea>\n" +
        "    </div>\n" +
        "    <hr class=\"hr2\">\n" +
        "    <div class=\"tiankong\">\n" +
        "        <audio autoplay></audio>\n" +
        "        <div id=\"recordingslist\"></div>\n" +
        "        <div>\n" +
        "            <button type=\"button\" class=\"btn btn-default fadeoutt\"   onclick=\"clicktalk(); \">语音输入</button>\n" +
        "            <button type=\"button\" class=\"btn btn-default\" onclick=\"generateNext(this);changeViewnext(this)\">确认</button>\n" +
        "        </div>\n" +
        "        <div class=\"fadeinthis\">\n" +
        "            <span class=\"spannn\">长按录音</span>\n" +
        "            <button class=\"click1\" onfocus=\"changevieww1();startRecording()\"\n" +
        "                    onblur=\"changevieww2();stopRecording();uploadAudio();fillblank(self)\">\n" +
        "            </button>\n" +
        "        </div>\n" +
        "</div>\n" +
        "</div>";
    $(".all").append(nextone);
    quesnum++;
}

//产生新题时的指向下一题
function changeViewnext(self){
    $("html,body").delay(300).animate({scrollTop:$(self).parents(".part").next().offset().top},600);
    $(self).parents(".part").next().siblings().delay(300).animate({opacity:'0.4'},100);
    $(self).parents(".part").next().delay(400).animate({opacity:'1'},400);
}
//指向本题
function changeView(self){
    $("html,body").delay(600).animate({scrollTop:$(self).offset().top},300);
    $(self).siblings().css("opacity","0.4");
    $(self).css("opacity","1")
}
function firout() {
    $('#p1').animate({opacity:'1'},300);
}


function generateNext(self)
{
    //lockAndRelease(self);
    var type = self.nodeName;
    var data;
    var answer_node_id;
    if(quesNum==1)
    {
        data = findNext(0);
    }
    else if(type.toLowerCase()=="button")//表示是填空类型
    {
        answer_node_id=currentQuesId;
        data = findNext(answer_node_id);
    }
    else
    {
        //node_id 需要是后台传递数据的时候的id
        answer_node_id=$(self).attr("id").substr(1);
        data = findNext(answer_node_id);
    }
    var question;
    var answers = new Array();
    for(var i = 0;i<data.length;i++)
    {
        if(data[i].node_type.indexOf("question")>=0)//是一个问题
            question = data[i];
        if(data[i].node_type=="answer")
            answers.push(data[i]);
    }
    if(answers.length>0) addChoiceQues(question,answers);//表示是普通的选择型问题
    else if(question.node_type=="blank_question") addBlankQues(question);
        }

