var recorder;
var audio = document.querySelector('audio');
function startRecording() {
    HZRecorder.get(function(rec) {
        recorder = rec;
        recorder.start();
    }, {
        sampleBits: 16,
        sampleRate: 16000
    });
}
function stopRecording() {
    recorder.stop();
    var blob = recorder.getBlob();
    var url = URL.createObjectURL(blob);
    var div = document.createElement('div');
    var au = document.createElement('audio');
    var hf = document.createElement('a');
    au.controls = true;
    au.src = url;
    hf.href = url;
    hf.download = new Date().toISOString() + '.wav';
    hf.innerHTML = hf.download;
    div.appendChild(au);
    div.appendChild(hf);
    recordingslist.appendChild(div);
}
function playRecording() {
    recorder.play(audio);
}
function uploadAudio() {
    //  return recorder.upload("", function(state, e) {
    //     switch(state) {
    //         case 'uploading':
    //             //var percentComplete = Math.round(e.loaded * 100 / e.total) + '%';
    //             break;
    //         case 'ok':
    //             //alert(e.target.responseText);
    //             alert("上传成功");
    //             break;
    //         case 'error':
    //             alert("上传失败");
    //             break;
    //         case 'cancel':
    //             alert("上传被取消");
    //             break;
    //     }
    // });
    var strr="爸爸爸爸爸爸爸爸";
    return strr;
}

function endcheck(self){
    changevieww2();
    stopRecording();
    uploadAudio();
    var blankstring=uploadAudio();
    $(self).parent().parent().siblings(".answer").find("textarea").val(blankstring);
}

function startcheck(){
    changevieww1();
    startRecording()
}


function changevieww1() {
    $('.click1').addClass("click2").removeClass("click1");

}
function changevieww2() {
    $('.click2').addClass("click1").removeClass("click2");

}
function clicktalk() {
    $('.fadeoutt').fadeOut(function aa() {
        $('.fadeinthis').fadeIn();
    });
}