var file;
var aid;


window.onload =  function(){
    if (window.localStorage.getItem("aid")!==null){
        aid=window.localStorage.getItem("aid");
        $.ajax({
            xhrFields: {
                withCredentials: true
            },
            type: "GET",
            url: "http://localhost:10080/editor/savedarticle/"+aid,
            dataType: "json",
            success: function (result,status,xhr) {
                console.log(result);
                app.articles=result;
                document.getElementById("title").value=result.title;
                app.cid = result.cid;
                $('#content').summernote('code', result.content1);
                getPicByAid(window.localStorage.getItem("aid"));
                window.localStorage.removeItem("aid");
            },
            error : function(e) {
                console.log(e);
                alert("异常！");
            }
        })
    }


    $.ajax({
        type: "GET",//方法类型
        url: "http://localhost:10080/columns" ,//url
        xhrFields: {
            withCredentials: true
        },
        dataType: "json",//预期服务器返回
        success: function (result,status,xhr) {
            console.log(result);
            app.columns=result;
        },
        error : function(e) {
            console.log(e);
            alert("异常！");
        }
    });

    document.querySelector("#uploadfile").addEventListener("change",function (event) {
        console.log(event);
        file = event.target.files[0];
        console.log(file.name);

        if(document.querySelector("#uploadpicture")==null)
        {
            var upbtn = document.createElement("button");
            upbtn.type = "button";
            upbtn.tabIndex = "500";
            upbtn.id = "uploadpicture";
            upbtn.className = "btn btn-default btn-secondary fileinput-upload fileinput-upload-button";
            var logo = document.createElement("i");
            logo.className="glyphicon glyphicon-upload";
            var txt = document.createElement("span");
            txt.className="hidden-xs";
            txt.textContent = "提交";
            upbtn.appendChild(logo);
            upbtn.appendChild(txt);
            upbtn.addEventListener("click",function (event) {
                event.preventDefault();

                var formData = new FormData();
                formData.append("file", file);
                console.log(formData);

                $.ajax({
                    type: "POST",//方法类型
                    url: 'http://localhost:10080/editor/uploadpicture' ,//url
                    xhrFields: {
                        withCredentials: true
                    },
                    processData : false,
                    data: formData,
                    contentType: false,
                    success: function (result,status,xhr) {
                        console.log(result);
                    },
                    error : function(e) {
                        console.log(e);
                        alert("异常！");
                    }
                })
            });
            $(".file-input")[0].appendChild(upbtn);
        }
    })
};
function getPicByAid(aid) {
            var imageDiv = $("#image")[0];
            imageDiv.innerText = '';
            var div = document.createElement("div");
            var img = document.createElement("img");
            img.src = 'http://localhost:10080/article/picture/'+aid;
            img.className = 'img img-responsive';
            div.appendChild(img);
            imageDiv.appendChild(div);
}

function save(state){
    var title=document.getElementById("title").value;
    var content=$('#content').summernote('code');
    var request_url;
    if(aid==undefined)
        request_url="http://localhost:10080/editor/save/"+app.cid+"/?state="+ state+"&title="+title+"&filename="+file.name;
    else
        request_url="http://localhost:10080/editor/modify/"+app.cid+"/"+aid+"?state="+ state+"&title="+title+"&filename="+file.name;

    $.ajax({
        xhrFields: {
            withCredentials: true
        },
        type: "POST",//方法类型
        url: request_url,//url
        dataType: "json",//预期服务器返回的数据类型
        data: JSON.stringify(new result("succeed",content) ),
        contentType: "application/json",
        success: function (result,status,xhr) {
            console.log(result);
            if (result.status === "succeed") {
                alert("SUCCESS");
                var win = window;
                while(win != win.top){
                    win = win.top;
                }
                win.location.href="editsuccess.html"
                // win.location.href = xhr.getResponseHeader("CONTEXTPATH");
            }
            else{
                alert("标题不能为空且文章内容不能为空");
            }
        },
        error : function(e) {
            console.log(e);
            alert("异常！");
        }
    });
}

function result(status,content){
    this.status = status;
    this.content = content;

}

function  getFile(event) {
    console.log(event);
    this.file = event.target.files[0];
    console.log(this.file);
}

function submit(event) {
    //阻止元素发生默认的行为
    console.log(event);
    event.preventDefault();
    var formData = new FormData();
    formData.append("file", this.file);
    axios.post('http://localhost:10080/editor/uploadpicture', formData)
        .then(function (response) {
            alert(response.data);
            console.log(response);
            window.location.reload();
        })
        .catch(function (error) {
            alert("上传失败");
            console.log(error);
            window.location.reload();
        });
}

var app = new Vue({
    el:"#page-wrapper",
    data:{
        columns:[],
        cid:''
    },
    methods:{
        getColumnSelected:function(){
            console.log(this.cid) ;
        }
    }
});
