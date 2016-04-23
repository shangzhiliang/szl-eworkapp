
<%@ page contentType="text/html;charset=GB2312" language="java" %>
<html>
<head>
    <title>测试</title>
    <script src="/js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function(){
            $(".vc").click(function(){
                $("#vc").attr("src","/VerifCode?"+Math.random());
            });
            /*setInterval(function(){
                $("#vc").attr("src","/VerifCode?"+Math.random());
            },500);*/
        });

    </script>
</head>
<body>
<img id="vc" class="vc" src='/VerifCode' alt="">
<a class="vc" href="#">看不清?换一张</a>
<br/>
<form action="/testvccode" method="post">
    <input type="text" name="vc" />
    <input type="submit" value="提交" />
</form>
</body>
</html>