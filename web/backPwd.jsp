<%--
  Created by IntelliJ IDEA.
  User: baby sorry time
  Date: 2019/12/1
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        var xhr = new XMLHttpRequest();
        window.onload = function () {
            //找到到我们的UI控件
            var password = document.getElementsByName("password")[0];
            var againpwd=document.getElementsByName("againpwd")[0];
            againpwd.onblur=function(){
                var againpwd=againpwd.value;
                var msg=document.getElementById("msg3");
                if(againpwd==""){
                    msg.innerHTML = "×"
                    msg.style.color = "red"
                } else {
                    msg.innerHTML=""
                    msg.style.color="green"
                }
                xhr.open("post", "${pageContext.request.contextPath}/login?againpwd=" + againpwd + "&$time=" + new Date());
                xhr.send(null);
            }

            //设置监听
            password.onblur=function(){
                var pwd=password.value;
                var msg=document.getElementById("msg2");
                if(pwd==""){
                    msg.innerHTML = "×"
                    msg.style.color = "red"
                } else {
                    msg.innerHTML="√"
                    msg.style.color="green"
                }
                xhr.open("post", "${pageContext.request.contextPath}/login?password=" + pwd + "&$time=" + new Date());
                xhr.send(null);
            }
        }
    </script>
</head>
<body>
<form action="/backpwd" method="post">
    <input type="password" name="password" placeholder="请输入密码">
    <span id="msg2"></span><br/>
    <input type="password" name="againpwd" placeholder="请再次输入密码">
    <span id="msg3"></span><br/>
    <input type="submit" value="找回密码">
</form>
</body>
</html>
