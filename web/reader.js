
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
};

var app = new Vue({
    el:"#wrapper",
    data:{
        columns:[],
        news:[]
    },
    methods:{
        chooseColumns:function(event){
            console.log(event.target.attributes[0].nodeValue);
            console.log("uname",columns[0].uname);   
            $.ajax({
                type: "GET",//方法类型   
                url: "http://localhost:10080/columns/"+event.target.attributes[0].nodeValue ,//url
                dataType: "json",//预期服务器返回
                success: function (result,status,xhr) {
                    console.log(result); 
                    news = result;
                },
                error : function(e) {
                    console.log(e);
                    alert("异常！");
                }
            })
        },

        updateArticles:function(){
            console.log(event.target.attributes[0].nodeValue);
            $.ajax({
                type: "GET",//方法类型   
                url: "http://localhost:10080/columns/"+event.target.attributes[0].nodeValue ,//url
                dataType: "json",//预期服务器返回
                success: function (result,status,xhr) {
                    console.log(result); 
                    news = result;   
                },
                error : function(e) {
                    console.log(e);
                    alert("异常！");
                }
            })
        }
    }
})