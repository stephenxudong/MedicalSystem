var name;
var nameb;
var namec;
var name2;
var addtop=-20;
var undertop=500;
var quesNum=1;//用于设置当前的问题的id
var currentQuesId;
var roo=70;
var nodes;//节点内容
var model;//用来存储节点关系
var backcolor=new Array("#febcbd","#fb9d9d","#ff7b7a","#e86c6c","#ff7b7a","#fb9d9d","#febcbd","#ffcfcf","#ffdbdb","#ffcfcf");
function getNodeAndModelbyModelId(model_id)
{
    var _ques=new Object();
    _ques.model_id = model_id;//暂时都是一个模型
    _ques.msg="first";
    $.ajax({
        async:false,
        type:'post',
        url:"/dddemo/dataTrans/transmit/",
        contentType:'application/json;charset=utf-8',
        data:JSON.stringify(_ques),
        success:function(data){
            var parsedata = jQuery.parseJSON(data);
            nodes = parsedata.nodes;
            model = parsedata.model;
        }
    });
    generateNext(this);
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
        data = findNext(answer_node_id)
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
    $(".ci").hide();
    scrollToEnd();
}


 function findNext(id)
{
    var temp = new Array;
    getRequeirNodes(temp,id);
    var rtn = new Array();
    if(id==0)
        rtn.push(selectFromNodes(0));
    for(var i = 0;i<temp.length;i++)
    {
        var node =selectFromNodes(temp[i]);
        rtn.push(node);
    }
    //第二个节点也是问题，那么只需要返回一个即可
    if(rtn.length>1&&(rtn[1].node_type.indexOf("question")>=0))
    {
        var nodes1 = new Array();
        nodes1.push(rtn[0]);
        return nodes1;
    }
    else return rtn;
}

function selectFromNodes(id)
{
    if(id==0)
        return nodes[0];//返回当前model中id最小的节点
    for(var i = 0;i<nodes.length;i++)
        if(nodes[i].node_id==id)
            return nodes[i];
    return null;
}

function getRequeirNodes(list, id){

    var efirst=model[id].firstEdge;

    if(efirst!=null){
        list.push(efirst.nodeNumber);
        if(efirst.nextEdge==null)
        {
            getRequeirNodes(list,efirst.nodeNumber);
        }
        while((efirst=efirst.nextEdge)!=null){
            list.push(efirst.nodeNumber);
        }
    }
}

function scrollToEnd() {
    var h = $(document).height() - $(window).height();
    $(document).scrollTop(h);
}

//锁定已经点击的问题
function lockAndRelease(self)
{
    $(self).attr("disabled","true");
    $("#a28").parent().siblings().find(".thisone").attr("disabled","false");

}

function addChoiceQues(ques,ans){

    var answerText=new Array();
    currentQuesId=ques.node_id;

    var lengths="";

    for(var a=0;a<ans.length;a++) {
        var xx=-(a+1)*15-5;
        answerText[a]="<div class=\"on1\" id='c"+a+"' style='border: 3px solid "+backcolor[a]+"; color: "+backcolor[a]+";top: -20%'>\n" +
            "  <label for=\"a"+ans[a].node_id+"\" style='position:relative;'>"+
            "<span style='opacity: 0'>啦啦啦啦啦啦</span>"+ans[a].content+"<span style='opacity: 0'>啦啦啦啦啦啦</span>"+"</label>\n" +
            "<input type=\"radio\" name=\"thisques"+quesNum+"\" style='position: relative ' id='a"+ans[a].node_id+ "' class=\"thisone\" " +
            "onclick='generateNext(this);getthiss(this);thisclick();thatclick();clicklalala();scrollToEnd();roundright();roundandround();'/>\n" +
            "  </div>"
        lengths+=answerText[a];
    }
    var nextone=
        "<div class=\"on3\" id=\"t"+quesNum+"\">\n" +
        "<div class='beforeturn'>" +
        "<img  src=\"../static/image/head.png\"  style='position: absolute'>" +
        "  <div class=\"on2\">\n"+ lengths+
        "</div>\n"+
        "    </div>\n" +
        "<div style='position: absolute;width: 100%;height: 30%;background-color: #ff7b7a;opacity: 1' class='on6'>" +
        "  <p id=\"q"+quesNum+"\" class=\"in1\"> "+quesNum+":"+" "+ques.content+"</p>\n" +
        "</div>" +
        "</div>"
    $(".on6:even").css("background-color",'#ffa188');
    var x=quesNum-1;
    name2='t'+quesNum;
    namec='q'+quesNum;
    name ='t'+x;
    nameb='q'+x;
    quesNum++;
    addtop=addtop+35;
    $("body").append(nextone);
}


function addBlankQues(ques){
    var answerText= "<div>\n" +
        "    <textarea name=\"options\"  id =\"a"+ques.node_id+"\" style=\"position:relative;width:100%;height:170px;top:5px;border: 3px solid #ff7b7a; \" ></textarea>\n" +
        "    <button onclick='generateNext(this);getthiss(this);thisclick();thatclick();clicklalala();scrollToEnd();roundright();roundandround();' style=\"position: relative;width: 100%;top:7px;color: #ff7b7a;border: 3px solid #ff7b7a;\">提交</button>\n" +
        "</div>";
    currentQuesId=ques.node_id;
    var nextone=
        "<div class=\"on3\" id=\"t"+quesNum+"\">\n" +
        "<div class='beforeturn'>" +
        "  <div class=\"on2\" >\n"+ answerText+
        "</div>\n"+
        "    </div>\n" +
        "<div style='position: absolute;width: 100%;height: 30%;background-color: #ff7b7a;opacity: 1' class='on6'>" +
        "  <p id=\"q"+quesNum+"\" class=\"in1\"> "+quesNum+":"+" "+ques.content+"</p>\n" +
        "</div>" +
        "    </div>";
    $(".on6:even").css("background-color",'#ffa188');
    var x=quesNum-1;
    name2='t'+quesNum;
    name ='t'+x;
    nameb='q'+x;
    namec='q'+quesNum;
    quesNum++;
    addtop=addtop+35;
    $("body").append(nextone);
}

//改变按钮样式
function getthiss(self){
    var bordercol=$(self).parent().css('border-color');
    $(self).parent().animate({},function(){$(self).parent().css({"background-color":bordercol,"color":"white"},"slow")});
    $(self).parent().siblings("div").animate({},function(){$(self).parent().siblings("div").css({"background-color":"white","color":"#FB9D9D"},"slow")});
    upanswer();
}

//新产生表单位置
function thatclick() {
    undertop+=35;
    var nexttoptxt=undertop+'px';
    // var nextundertxt=undertop+60+'px'
    $("#"+name2+"").css("top",nexttoptxt);
    $("#in3").css("top",nexttoptxt);
}

//选项动画
function downanswer(){
    $("#"+name2+"").find(".on1").animate({top:'-15%'});
    $("#"+name2+"").find(".on1").animate({top:'1%'});
    for(var i=0;i<7;i++){
        var theans="c"+i;
        var thean=(i+1)*16+1+"%";
        $("#"+name2+"").find("#"+theans+"").nextAll().animate({top:thean});
    }
}
//选项动画2
function upanswer(){
    $("#"+name+"").find(".on1").animate({top:'-15%'},"fast");
}

//放大图片
function clicklalala() {
    var nexttoptxt2=undertop-410;
    var nexttopend=nexttoptxt2+'px';
    $("#"+name2+"").animate({
        top:nexttopend,
        width: '80%',
        height:'60%',
        fontSize:'15px'
    });
    downanswer();
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
    });
    $("#"+name+"").addClass("clickits");
    $("#"+name+"").children(".beforeturn").children(".on2").children(".on1").children(".thisone").attr("onclick","getthiss(this);" +
        "roundright();"+"newdown(this);");
    $("#"+name+"").children(".on6").attr("onclick","down(this)");
    $("#"+nameb+"").animate({
        top:'3%',
        fontSize:'13px'
    });
}
//转轮右转
function roundright() {
    var roo2=roo+"deg";
    $(".imgs2").animate({},function(){
        $(".imgs2").css('transform',"rotate("+roo2+")")
    });
    roo+=70;
}

//转轮左转
function roundleft() {
    var roo2=-roo-80+"deg";
    $(".imgs2").animate({},function(){
        $(".imgs2").css('transform',"rotate("+roo2+")")
    });
    roo+=70;
}

function getQuesLength(nodelist) {
    var length=0;
    for(var i=0;i<nodelist.length;i++)
        if(nodelist[i].node_type.indexOf("question")>=0)
            length++;
    return length;
}

//做题量
function roundandround() {
    if(quesNum<=getQuesLength(nodes))
    {   var roo3=quesNum*180/getQuesLength(nodes);
        var roo6=parseInt(roo3)+"deg";
        $(".imgs3").animate({},function(){
            $(".imgs3").css('transform',"rotate("+roo6+")")
        });
        var roo4=quesNum*100/getQuesLength(nodes);
        var roo5=parseInt(roo4)+"%";
        $(".downpic").find("p").text(roo5);}
}

//下放图片
function down(self){
    var topadd2=addtop+500+'px';
    $(self).removeAttr("onclick");

    $(self).parent().siblings(".clickit").find(".on1").animate({top:'-15%'},"fast");

    $(self).parent().siblings(".clickit").animate({top:'-=70px',width: '150px', height:'180px', fontSize:'15px'});
    $(self).parent().siblings(".clickit").nextAll().animate({top:'-=500px'},'fast');

    $(self).parent().siblings(".clickit").removeClass("clickit");
    $(self).parent().addClass("clickit");
    $(self).parent().siblings(".on3").children(".on6").attr("onclick","down(this);");

    roundleft();
    $(self).parent().nextAll().animate({top:'+=500px'},'fast');
    $(self).parent().animate({top:'+=70px',width: '80%', height:'60%', fontSize:'20px'});


    //downanswer();
    $(self).parent().find(".on1").animate({top:'-15%'});
    $(self).parent().find(".on1").animate({top:'1%'});
    for(var i=0;i<7;i++){
        var theans="c"+i;
        var thean=(i+1)*16+1+"%";
        $(self).parent().find("#"+theans+"").nextAll().animate({top:thean});
    }



    $("#"+name2+"").animate({
        top:topadd2,
        width:'150px',
        height:'180px',
        fontSize:'50%',
        boder:'3px'
    });
    $("#"+namec+"").animate({
        top:'3%',
        fontSize:'13px'
    });
    $("#"+name2+"").find(".on6").attr("onclick","clicklastone(this);");
}

//修改之后
function newdown(self){
    // $("#"+name2+"").animate({
    //     top:'+=70px',
    //     width: '300px',
    //     height:'360px',
    //     fontSize:'15px',
    //     background: '100% 100%',
    // });
    clicklastone();
    $(self).parent().parent().parent().parent().animate({top:'-=70px',width: '150px', height:'180px', fontSize:'15px'});
    $(self).parent().parent().parent().parent().nextAll().animate({top:'-=500px'},'fast');
    $(self).parent().parent().parent().parent().children(".on6").removeAttr("onclick");
    $(self).parent().parent().parent().parent().removeClass("clickit");
}
//点击最后一个
function clicklastone(self){
    $(self).removeAttr("onclick");
    $(self).parent().siblings(".clickit").animate({top:'-=70px',width: '150px', height:'180px', fontSize:'15px'});
    $(self).parent().siblings(".clickit").nextAll().animate({top:'-=500px'},'fast');
    $(self).parent().siblings(".clickit").removeClass("clickit");
    $("#"+name2+"").animate({
        top:'+=70px',
        width: '80%',
        height:'60%',
        fontSize:'20px',
        background: '100% 100%',
    });
    $("#"+name2+"").find(".beforeturn").children(".on2").animate({
        fontSize:'16px',
    });
}


