function register(){
    $.ajax({
        xhrFields: {
            withCredentials: true
        },
        type: "POST",//方法类型
        url: "http://localhost:10080/user/register" ,//url
        dataType: "json",//预期服务器返回的数据类型
        data: $('#registermassage').serialize(),
        success: function (result,status,xhr) {
            console.log(result);
            if (result.status === "succeed") {
                alert("SUCCESS");
                var win = window;
                while(win != win.top){
                    win = win.top;
                }
                win.location.href = xhr.getResponseHeader("CONTEXTPATH");
            }
            else if(result.status === "username is null or pwd not equal pwd1"){
                alert('username is null or pwd not equal pwd1');
            }

            else{
                alert("username is already exist");
            }
        },
        error : function(e) {
            console.log(e);
            alert("异常！");
        }
    });
}