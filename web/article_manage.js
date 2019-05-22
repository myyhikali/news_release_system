window.onload = getArticlesPublished();

function getArticlesPublished(){
    $.ajax({
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

var app = new Vue({
    el:"#page-wrapper",
    data:{
        articles:[]
    }
})

$('#allowModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget)
    var aid = button.data('aid') // Extract info from data-* attributes

    var modal = $(this);
    
    console.log(modal.find('.modal-footer #allow'));
})
