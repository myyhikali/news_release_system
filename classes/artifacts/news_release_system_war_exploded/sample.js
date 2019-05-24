window.onload =  function(){
    $.ajax({
        type: "GET",//方法类型
        url: "http://localhost:10080/article/read/231" ,//url
        dataType: "json",//预期服务器返回
        success: function (result,status,xhr) {
            console.log(result);

            app.article=result;
            // app.article.content=html(app.article.content).text();
            // app.article.content = _.unescape(app.article.content);
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
    },
    methods:{
        // chooseColumns:function(event){
        //     console.log(event.target.attributes[0].nodeValue);
        //     getArticles(event.target.attributes[0].nodeValue);
        // }
    }
});
