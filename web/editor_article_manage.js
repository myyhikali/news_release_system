var eid ;

window.onload = function(){
    var cookies = document.cookie.split("; ");
    
    var win = window;
    while(win != win.top){
        win = win.top;
    }                   
    for(var i=0;i<cookies.length;i++){
        var name = cookies[i].split("=")[0];
        if(name==="level")
        {
        var level = parseInt(cookies[i].split("=")[1]);
        
        if(level!=1)
            win.location.href="login.html";
        }
        if(name==="uid")
            eid = parseInt(cookies[i].split("=")[1]);
    }
    if(eid == undefined)
        win.location.href="login.html";
    else{
        getEditorArticles();
    }
}

function changeArticleState(aid,state){
    $.ajax({
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

function getEditorArticles(){
    $.ajax({
        type: "GET",  
        url: "http://localhost:10080/editor/"+eid+"/article" ,
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

var app = new Vue({
    el:"#page-wrapper",
    data:{
        articles:[]
    }
})

$('#publishModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget)
    var aid = button.data('aid') 

    document.querySelector('#allow').onclick=function(event){
        changeArticleState(aid,"published");
    }
})

$('#deleteModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget)
    var aid = button.data('aid') 

    document.querySelector('#allow').onclick=function(event){
        changeArticleState(aid,"deleted");
    }
})