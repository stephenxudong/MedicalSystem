
var currentQuesId;
var nodes;//节点内容
var model;//用来存储节点关系
var allcase = new Array();

function getNodeAndModelbyModelId(model_id)
{
    var _ques=new Object();
    _ques.model_id = model_id;//暂时都是一个模型
    sessionStorage.setItem("model_id",model_id);
    _ques.msg="first";
    $.ajax({
        async:false,
        type:'post',
        url:"/demo/dataTrans/transmit/",
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
    if(currentQuesId && nodes[currentQuesId].comment=="last_ques")
    {
        $('#endModal').modal('show');
        showallques();
        return;
    }
    if(quesnum==1)
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
        if(nodes[answer_node_id].comment=="last_ques")
        {
            $('#endModal').modal('show');
            showallques();
            return;
        }
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


 function findNext(id)
{
    var temp = new Array;
    getRequeirNodes(temp,id);
    var rtn = new Array();
    if(id==-1)
        rtn.push(selectFromNodes(-1));
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
function showallques() {
    var quesfir=$("#myModalLabel").text();
    var ansfir=$("#firstanswer").val();
    onecase=new Object();
    onecase.question=quesfir;
    onecase.answer=ansfir;
    allcase.push(onecase);

    var showfirs="<div class=\"showit\">\n" +
        "<span class=\"que\">\n" +
        quesfir +
        "</span>\n" +
        "<span class=\"ans\">\n" +
        ansfir +
        "</span>\n" +
        "</div>"

    $('#showend').append(showfirs);


    for(var j=1;j<quesnum;j++){
        onecase=new Object();
        var quess=$("#q"+j+"").text();
        var anss;
        var checkit=$("#p"+j+"").attr("class");
        if(checkit=="part blankques")
            anss = $("#p"+j+"").find("textarea").val();
        else
            anss = $("input[name=\"q"+j+"\"]:checked").siblings("span").text();
        if(j<10)
            quess = quess.substring(3);
        else
            quess = quess.substring(4);
        var showthem="<div class=\"showit\">\n" +
            "<span class=\"que\" >\n" +
            quess +
            "</span>\n" +
            "<span class=\"ans\" id=\"que"+j+"\" onclick=\"viewchange("+j+")\">\n" +
            anss +
            "</span>\n" +
            "</div>"
        $('#showend').append(showthem);
        onecase.question=quess;
        onecase.answer=anss;
        allcase.push(onecase);

    }
}
function viewchange(chan){
    $("#endModal").modal('hide');
    var jia="p"+chan;
    $("html,body").delay(300).animate({scrollTop:$("#"+jia+"").offset().top},600);
}


function caseSubmit()
{
    var caseInfo = new Object();
    caseInfo.main_case = allcase[0].answer;//第一个是主诉
    caseInfo.current_ques_ans = allcase.slice(1);
    caseInfo.model_id = sessionStorage.getItem("model_id");
    caseInfo.idCardId = sessionStorage.getItem("idCardId");
    if(allcase.length==0)
        return;
    else
        $.ajax({
            async:false,
            type:'post',
            url:"/demo/collect/quesAndAns/",
            contentType:'application/json;charset=utf-8',
            data:JSON.stringify(caseInfo),
            success:function(data){
                var info = jQuery.parseJSON(data);
                alert(info.msg);
            }
        });
}







