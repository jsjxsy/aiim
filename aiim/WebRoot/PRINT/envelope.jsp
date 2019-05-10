<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/JXGL/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>封皮</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/print.js" charset="GBK"></script>
	<link rel="stylesheet" type="text/css" href="css/print.css" />
	
<style type="text/css">
	body{font-size: 18px;text-align: center;}
	table td{font-size: 22px;font-weight:bold;border: 0;}
	/*div边框*/
	.divFrame{width:600px; height:800px;margin-top:20px;visibility:visible;border:thin 1px solid;}
	/*封皮头部*/
	.envelope_top{float:left;margin-left:20px;margin-top:8px;visibility:visible;text-align:left;width:420px;}
	/*封皮底部*/
	.envelope_bottom{float:left;margin-left:20px;margin-top:300px; visibility:visible;text-align:left;width:500px;}
	
	.td_boder {border-bottom:thick 1px solid;border-bottom-color: black;}
	.td_letter {letter-spacing: 20px;width: 180px;}
</style>
  </head>
  
  <body>
  <div class="noprint" align="center"><input type="button" onclick="printPage()" value="打印"></div>
  <div class="divFrame">
	  <div class="envelope_top">
		  <table width="100%">
		  		<tr>
		  			<td width="180px;">档<span style="width:115px;">&nbsp;</span>号:</td>
		  			<td class="td_boder">${archivesInfo.archivesID}</td>
		  		</tr>
		  		<tr>
		  			<td>档案馆（室）号:</td>
		  			<td class="td_boder">${archivesInfo.archivesFondsID}</td>
		  		</tr>
		  		<tr>
		  			<td>缩<span style="width:46px;">&nbsp;</span>微<span style="width:46px;">&nbsp;</span>号:</td>
		  			<td class="td_boder">&nbsp;</td>
		  		</tr>
		  	</table>
	  </div>
  	<div style="margin-top: 200px;font-size: 60px;font-weight: bold;">东北大学</div>
  	<div class="envelope_bottom">
		  <table width="100%">
		  		<tr height="100px;" valign="top">
		  			<td class="td_letter" >案卷题名</td>
		  			<td class="td_boder">${archivesInfo.title}</td>
		  		</tr>
		  		<tr>
		  			<td class="td_letter">编制单位</td>
		  			<td class="td_boder">${archivesInfo.departmentName}</td>
		  		</tr>
		  		<tr>
		  			<td class="td_letter">编制日期</td>
		  			<td class="td_boder">${archivesInfo.formationDate}</td>
		  		</tr>
		  		<tr>
		  			<td id="sd1" class="td_letter">保管期限</td>
		  			<td class="td_boder">${archivesInfo.retentionPeriodText}</td>
		  		</tr>
		  		<tr>
	  				<td style="letter-spacing:106px;">密级</td>
	  				<td class="td_boder">${archivesInfo.secrecyText}</td>
	  			</tr>
		  	</table>
	  </div>
  </div>
  </body>
</html>
