<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <base href="<%=basePath%>">
	
	<title>打回原因</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/common.css">
	
	<style type="text/css">
	  body{
		font-size:12px;
	}
	</style>
	
	<script type="text/javascript">
	   function submitCause(){
	      if(document.getElementById("cause").value == ""){
	         alert("请输入打回原因！");
	      }else{
	           window.returnValue = document.getElementById("cause").value;
	           window.close();
	      }
	   }
	   function cancel(){
	      window.returnValue = null;
	      window.close();
	   }
	</script>
</head>

<body>
	 <fieldset style="margin: 5px;">
		<table align="center" width="95%" style="margin-top:10px; font-size:12px;">
		  <tr>
		    <td style="width: 80px">打回原因：</td>
		    <td>
		      <textarea style="width:350px;height:150px;" id="cause"></textarea>
		    </td>
		  </tr>
		  <tr>
		    <td colspan="2" height="15px"></td>
		  </tr>
		  <tr>
		    <td colspan="2" align="center">
		      <input type="button" value="提      交" class="button" onclick="submitCause()"/>&nbsp;&nbsp;&nbsp;&nbsp;
		      <input type="button" value="取      消" class="button" onclick="cancel()"/>
		    </td>
		  </tr>
		</table>
	</fieldset>
</body>
</html>
