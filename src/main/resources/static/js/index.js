function login() {
    //var jsonObj = {id: $("#id").val(),name:"abcd",age:"123"};
    $.ajax({
        async : true,
        url: "/getUser",
        data:{
            username: $("#username").val()
        },
        //data:JSON.stringify(jsonObj),  传入json数据
        type: "POST",
        // dataType:"json",//返回数据类型
        // headers:{"Content-Type":"application/json"},  传入json数据时需设置请求头
        success:function(data){
            console.info(data)
            alert("test：" + data.username);
        },
        error:function(err){
            alert('连接失败:' + err);
        }
    });
}