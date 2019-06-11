window.onload = getArticlesPublished();

function getArticlesPublished(){
    $.ajax({
        xhrFields: {    
            withCredentials: true
        },
        type: "GET",  
        url: "http://localhost:10080/manage/article" ,
        dataType: "json",
        success: function (result,status,xhr) {
            console.log(result);    
            app.articles=result;
        },
        error : function(e) {
            console.log(e);
            alert("异常！");
        }
    })
}

function changeArticleState(aid,state){
    $.ajax({
        xhrFields: {
            withCredentials: true
        },
        type: "PUT",  
        url: "http://localhost:10080/manage/article/"+aid+"/"+state ,
        dataType: "json",
        success: function (result,status,xhr) {
            console.log(result); 
            getArticlesPublished(); 
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
        articles:[]
    },
    methods:{
        setAid: function (message) {
            // alert(message);
            window.localStorage.setItem("aid",message);
            window.location.href="article_modify_note.html";
        }
    }
});

$('#allowModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget);
    var aid = button.data('aid'); // Extract info from data-* attributes

    document.querySelector('#allow').onclick=function(event){
        changeArticleState(aid,"checked");
    }
})

$('#rejectModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget)
    var aid = button.data('aid') // Extract info from data-* attributes

    document.querySelector('#reject').onclick=function(event){
        changeArticleState(aid,"returned");
    }
})

$('#deleteModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget)
    var aid = button.data('aid') // Extract info from data-* attributes

    document.querySelector('#delete').onclick=function(event){
        changeArticleState(aid,"deleted");
    }
})

