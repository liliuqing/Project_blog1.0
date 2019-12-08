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
            var email = document.getElementsByName("email")[0];
            var password = document.getElementsByName("password")[0];
            var password2 = document.getElementsByName("password2")[0];
            email.onblur=function(){
                var pwd=email.value;
                var msg=document.getElementById("msg1");
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
            password2.onblur=function(){
                var pwd=password2.value;
                var msg=document.getElementById("msg3");
                if(pwd==""){
                    msg.innerHTML = "×"
                    msg.style.color = "red"
                } else {
                    msg.innerHTML=""
                    msg.style.color="green"
                }
                xhr.open("post", "${pageContext.request.contextPath}/login?password=" + pwd + "&$time=" + new Date());
                xhr.send(null);
            }
        }
    </script>
</head>
<body>
<form action="/regist" method="post">
    <input type="text" name="email" placeholder="请输入邮箱">
    <span id="msg1"></span><br/>
    <input type="password" name="password" placeholder="请输入密码">
    <span id="msg2"></span><br/>
    <input type="password" name="password2" placeholder="请再次输入密码">
    <span id="msg3"></span><br/>
    <input type="submit" value="注册">
</form>
</body>
</html>
