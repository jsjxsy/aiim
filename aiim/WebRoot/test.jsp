<%@ page contentType="text/html; charset=GBK"%> 
<html> 
  <head> 
    <title>test image code</title> 
<script type="text/javascript">
    function changidateCode(obj) {
        //��ȡ��ǰ��ʱ����Ϊ�������޾�������
        var timenow = new Date().getTime();
        //ÿ��������Ҫһ����ͬ�Ĳ�����������ܻ᷵��ͬ������֤��
        //��˵��������Ļ�������й�ϵ����̫���ף�������
        obj.src="login/getAuthcode.action?d="+timenow;
    }
</script>
   </head>
   <body>
     <img src="login/getAuthcode.action" onclick="changidateCode(this)"/>
     <a href="login/getAuthcode.action">fdf</a>
 </body>
</html> 
