
window.onload =  function(){
    $.ajax({
        type: "GET",//方法类型   
        url: "http://localhost:10080/columns" ,//url
        dataType: "json",//预期服务器返回
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
    el:"#wrapper",
    data:{
        columns:[],
        news:[]
    },
    methods:{
        chooseColumns:function(event){
            event.srcElement.attributes.cid.nodeValue;
            console.log(event);
            // $.ajax({
            //     type: "GET",//方法类型   
            //     url: "http://localhost:10080/columns/"+event ,//url
            //     dataType: "json",//预期服务器返回
            //     success: function (result,status,xhr) {
            //         console.log(result);    
            //         app.columns=result;
            //     },
            //     error : function(e) {
            //         console.log(e);
            //         alert("异常！");
            //     }
            // })
        }
    }
})