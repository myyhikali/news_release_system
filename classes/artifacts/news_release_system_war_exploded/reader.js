
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
    getArticles(1);
};

function getArticles(cid){
    $.ajax({
        type: "GET",//方法类型
        url: "http://localhost:10080/columns/"+cid ,//url
        dataType: "json",//预期服务器返回
        success: function (result,status,xhr) {
            var table = document.querySelector("#readertable");
            table.innerText = "";

            for(var i =0;i<result.length;i++){
                console.log(result[i]);
                var t = document.createElement("tr");
                var title = document.createElement("td");
                var content = document.createElement("td");
                var editor = document.createElement("td");

                title.textContent = result[i].title;
                content.textContent = result[i].content;
                editor.textContent = result[i].eid;

                t.appendChild(title);
                t.appendChild(content);
                t.appendChild(editor);
                table.appendChild(t);
            }
        },
        error : function(e) {
            console.log(e);
            alert("异常！");
        }
    })
};


var app = new Vue({
    el:"#app",
    data:{
        columns:[]
    },
    methods:{
        chooseColumns:function(event){
            console.log(event.target.attributes[0].nodeValue);
            getArticles(event.target.attributes[0].nodeValue);
        },
        showArticle:function (event) {
            var button = $(event.relatedTarget);
            console.log(button);
        }
    }
})
