<%@ page contentType="text/html; charset=GBK"%> 
<html> 
  <head> 
    <title>test image code</title> 
<script type="text/javascript">
    function changidateCode(obj) {
        //获取当前的时间作为参数，无具体意义
        var timenow = new Date().getTime();
        //每次请求需要一个不同的参数，否则可能会返回同样的验证码
        //据说和浏览器的缓存机制有关系，不太明白，照做吧
        obj.src="login/getAuthcode.action?d="+timenow;
    }
</script>
   </head>
   <body>
     <img src="login/getAuthcode.action" onclick="changidateCode(this)"/>
     <a href="login/getAuthcode.action">fdf</a>
 </body>
</html> 
