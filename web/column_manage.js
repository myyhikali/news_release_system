
window.onload = getColumns();

function getColumns(){
    $.ajax({
        xhrFields: {
            withCredentials: true
        },
        type: "GET",  
        url: "http://localhost:10080/columns" ,
        dataType: "json",
        success: function (result,status,xhr) {
            console.log(result);    
            app.columns=result;
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
        columns:[]
    },
    methods:{
    
    }
})

$('#myModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget)
    var cid = button.data('cid') // Extract info from data-* attributes
    var cname = button.data('cname')

    var modal = $(this);
    console.log(modal); 

    modal.find('.modal-body a').text(cid);
    modal.find('.modal-body input').val(cname);
})

function saveChanges(element){
    var model = $('#myModal');
    var cname = model.find(".modal-body input").val();
    var cid = model.find(".modal-body a").text();

    console.log(document.cookie); 

    $.ajax({
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        type: "PUT",  
        url: "http://localhost:10080/manage/modifycolumn?cid="+cid+"&cname="+cname,
        dataType: "json",
        success: function (result,status,xhr) {
            if(result.status=="succeed")
            {
                console.log(result);  
                getColumns();
            }
        },
        error : function(e) {
            console.log(e);
            alert("异常！");
        }
    })
}

function addColumn(){
    var model = $("#myModal2");
    var cname = model.find(".modal-body input").val();
    console.log(cname);
    $.ajax({
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        type: "POST",  
        url: "http://localhost:10080/manage/addcolumn?cname="+cname,
        dataType: "json",
        success: function (result,status,xhr) {
            if(result.status=="succeed")
            {
                console.log(result);  
                getColumns();
            }
            else if(result.status=="failed"){
                alert(result.content);
            }
        },
        error : function(e) {
            console.log(e);
            alert("异常！");
        }
    })
}

$('#myModal1').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget);
    var cname = button.data('cname');
    var cid = button.data('cid');

    var modal = $(this);
    modal.find('.modal-body h3').text("确定要删除\""+cname+"\"栏目吗？");
    modal.find('.modal-body a').text(cid);
})

function deleteColumn(){
    var model = $("#myModal1");
    
    console.log(model[0]);
    var cid = model.find(".modal-body a").text();
    $.ajax({
        xhrFields: {
            withCredentials: true
        },
        type: "DELETE",  
        url: "http://localhost:10080/manage/deletecolumn?cid="+cid,
        dataType: "json",
        success: function (result,status,xhr) {
            if(result.status=="succeed")
            {
                console.log(result);  
                getColumns();
            }
            else if(result.status=="failed"){
                alert(result.content);
            }
        },
        error : function(e) {
            console.log(e);
            alert("异常！");
        }
    })
}
