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
            var email = document.getElementsByName("email")[0];
            var password = document.getElementsByName("password")[0];
            var image=document.getElementsByName("image")[0];
            image.onblur=function(){
                var ima=image.value;
                var msg=document.getElementById("msg3");
                if(ima==""){
                    msg.innerHTML = "×"
                    msg.style.color = "red"
                } else {
                    msg.innerHTML=""
                    msg.style.color="green"
                }
                xhr.open("post", "${pageContext.request.contextPath}/login?image=" + ima + "&$time=" + new Date());
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
            email.onblur = function () {
                //拿到内容
                var emali = email.value;

                var msg = document.getElementById("msg1");
                if (emali == "") {
                    msg.innerHTML = "×"
                    msg.style.color = "red"
                } else {
                    msg.innerHTML = "√"
                    msg.style.color = "green"
                }
                xhr.open("post", "${pageContext.request.contextPath}/login?email=" + emali + "&$time=" + new Date());
                xhr.send(null);
            }
        }
    </script>
</head>
<body>
<form action="/login" method="post">
    <input type="text" name="email" placeholder="请输入邮箱">
    <span id="msg1"></span><br/>
    <input type="password" name="password" placeholder="请输入密码">
    <span id="msg2"></span><br/>
   <input type="text" name="image" placeholder="请输入验证码">
    <span id="msg3"></span><br/>
    <img src="/verifyCodeServlet"><br/>
    <input type="button" value="看不清? 换一张." id="btn"><br/>
    <input type="submit" value="登录">
    <button type="button"><a href="regist.jsp">注册</a></button><br/>
    <button type="button"><a href="backPwd.jsp">忘记密码</a></button><br/>
</form>
<script type="text/javascript">
    document.getElementById("btn").onclick = function () {
        // 获取img元素
        // 为了让浏览器发送请求到servlet, 所以一定要改变src
        document.getElementsByTagName("img")[0].src =
            "/verifyCodeServlet?time=" + new Date().getTime();
    }
</script>
</body>
</html>
