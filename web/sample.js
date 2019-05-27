window.onload =  function(){
    var aid = window.localStorage.getItem("aid");
    $.ajax({
        type: "GET",//方法类型
        url: "http://localhost:10080/article/read/"+aid ,//url
        dataType: "json",//预期服务器返回
        success: function (result,status,xhr) {
            app.article=result;
            document.getElementById("content").innerHTML=app.article.content1;
        },
        error : function(e) {
            console.log(e);
            alert("异常！");
        }
    })
};
var app = new Vue({
    el:"#article",
    data:{
        article:[]
    }
});
