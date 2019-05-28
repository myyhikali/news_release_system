
window.onload =  function(){
    $.ajax({
        xhrFields: {
            withCredentials: true
        },
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
        xhrFields: {
            withCredentials: true
        },
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
                var time = document.createElement("td");

                title.textContent = result[i].title;
                content.textContent = result[i].content;
                content.style = "max-width: 200px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;";
                editor.textContent = result[i].uname;
                time.textContent = formatTime(result[i].time);


                t.appendChild(title);
                t.appendChild(content);
                t.appendChild(editor);
                t.appendChild(time);
                t.onclick = showArticle(result[i].aid);
                table.appendChild(t);
            }
        },
        error : function(e) {
            console.log(e);
            alert("异常！");
        }
    })
};
function formatTime(t)
{
    var time = new Date(t);
    var y = time.getFullYear();
    var m = time.getMonth()+1;
    var d = time.getDate();
    var h = time.getHours();
    var mm = time.getMinutes();
    var s = time.getSeconds();
    return y+'-'+m+'-'+d+' '+h+':'+mm+':'+s;
}

function showArticle (aid) {
    return function(evnet) {
        console.log(aid);
        window.localStorage.setItem("aid",aid);
        window.location.href = "sample.html";
    }

}


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

    }
})
