
// window.onload = function (ev) {
//     var cookies = document.cookie.split("; ");
//
//     var win = window;
//     while(win != win.top){
//         win = win.top;
//     }
//     for(var i=0;i<cookies.length;i++){
//         var name = cookies[i].split("=")[0];
//         if(name==="level")
//         {
//             app.level = parseInt(cookies[i].split("=")[1]);
//         }
//
//     }
//     // if(app.level == '')
//     //     win.location.href="reader.html";
// }
//
// var app = new Vue({
//     el:"#side-menu",
//     data:{
//         level:'',
//     },
//     methods:{
//
//     }
// })