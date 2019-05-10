<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>去向登记</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="css/common.css"/>
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.form.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$("#import").ajaxForm({
				beforeSubmit:function(){alert("sdfsf")},
				success:function(data){
					alert(data);
					window.location.reload();
				},
				error:function(XMLHttpRequest, textStatus){alert(textStatus+","+XMLHttpRequest.status);}
		    });
		}); 
	</script>
</head>
<body style="overflow: scroll;">
<input type="hidden" name="preSelectRow" id="preSelectRow" />
  <table width="100%" style="margin:0px; font-size:12px;" cellspacing="0" cellpadding="0">
	<tr>
		<td>
			<input type="image" src="images/print.gif" style="margin-left:3px;" onmouseover="changeImage(this,'print2.gif')" onmouseout="changeImage(this,'print.gif')" onclick="alert('打印中...')"/>
		</td>
		<td>
			<div style="margin-right:2px; display:inline-block; width:300px; color:blue; float:right; margin-top:5px;" align="right">
				<font style="font-size:12px;font-weight:bold;">当前位置：</font>转出管理&nbsp;&gt;&gt;&nbsp;去向登记
			</div >
		</td>
	</tr>	
</table>
<form name="conditionForm" action="XSDAGL/studentFileManageAction_findMoveOutInfo.action" method="post" style="margin: 0;padding: 0;">	
	
	<input type="hidden" name="dataPageInfo.currentPage" id="currentPage" value="1"><!-- 当前页码 -->
	<input type="hidden" id="moveOutWay" name="moveOutWay" value="0">
</form>	
<table width="100%" style="margin:0px; font-size:12px;" cellspacing="0" cellpadding="0">
    <tr>
       <td>
         <fieldset>
           <form action="XSDAGL/moveOutRegister.action" method="post" id="import" name="import" enctype="multipart/form-data">
           <table align="center" style="font-size: 12px;">
	            <tr>
			      <td align="center">
			        <input type="radio" name="importType" value="6" checked/>博士生
			        <input type="radio" name="importType" value="7"/>硕士生
			        <input type="radio" name="importType" value="8"/>本科生
			      </td>
			    </tr>
			    <tr>
			      <td>
			        <hr/>
			      </td>
			    </tr>
				<tr >
					<td align="center">
						<span>选择文件&nbsp;&nbsp;</span><input type="file" name="excel">&nbsp;&nbsp;<input type="submit" value="导    入">&nbsp;&nbsp;<%--<input type="button" value="打印条码"> --%>
					</td>
				</tr>
           </table>
           </form>
         </fieldset>
       </td>
    </tr>
</table>
${message}
	</body>
</html>
