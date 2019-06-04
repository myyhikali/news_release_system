//http://localhost:10080/user/checklogin

function login(){
    $.ajax({
            xhrFields: {
                withCredentials: true
            },
            type: "POST",//方法类型   
            url: "http://localhost:10080/user/checklogin" ,//url
            dataType: "json",//预期服务器返回的数据类型
            data: $('#form1').serialize(),
            success: function (result,status,xhr) {
                console.log(result);    
                if (result.status == "succeed") {
                    // alert("SUCCESS");
                    var win = window;
                    while(win != win.top){
                        win = win.top;
                    }
                    win.location.href = xhr.getResponseHeader("CONTEXTPATH");
                }
                ;
            },
            error : function(e) {
                console.log(e);
                alert("异常！");
            }
        });
}