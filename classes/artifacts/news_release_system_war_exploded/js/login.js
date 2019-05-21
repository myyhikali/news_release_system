//http://localhost:10080/user/checklogin

$.ajaxSetup({
    complete : function(XMLHttpRequest, textStatus) {    
    // 通过XMLHttpRequest取得响应头，REDIRECT      
    var redirect = XMLHttpRequest.getResponseHeader("REDIRECT");//若HEADER中含有REDIRECT说明后端想重定向    
    alert(redirect);
    if (redirect == "REDIRECT") {  
        var win = window;      
        while (win != win.top){    
            win = win.top;    
        }  
        win.location.href= XMLHttpRequest.getResponseHeader("CONTEXTPATH");

    }  
}
});