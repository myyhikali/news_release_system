window.onload = getArticleModifies();

function getArticleModifies(){
    $.ajax({
        xhrFields: {
            withCredentials: true
        },
        type: "GET",
        url: "http://localhost:10080/manage/articlemodify/"+window.localStorage.getItem("aid") ,
        dataType: "json",
        success: function (result,status,xhr) {
            console.log(result);
            app.articlemodifies=result;
            window.localStorage.removeItem("aid");
        },
        error : function(e) {
            console.log(e);
            alert("异常！");
        }
    })
}

var app = new Vue({
    el:"#page-wrapper",
    data:{
        articlemodifies:[]
    },
    methods:{

    }
});
