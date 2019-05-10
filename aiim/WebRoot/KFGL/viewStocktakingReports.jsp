<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>" target="_self">
    
    <title>查看库房盘点报告</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/common.css">
	
	<script type="text/javascript" src="dwr/interface/StoreroomManageAction.js"></script>

    <script type="text/javascript" src="dwr/engine.js"></script>
 	<script type="text/javascript" src="js/common.js"></script>
 	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/jquery.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">

	
	function PopUpCalendar(ctrlobj,type)
	{
		var url;
		var obj=document.getElementById(ctrlobj);
		if (obj==null) {
			return;
		}
		var obj1=obj;
		showx=obj1.offsetLeft+window.screenLeft;
		showy=obj1.offsetTop+window.screenTop+20;
		while (obj1=obj1.offsetParent) {
			showx+=obj1.offsetLeft;
			showy+=obj1.offsetTop;
		}
		if (type==true) {
			url="<%=basePath%>js/CalendarWithFormat.html";
		}
		else {
			url="<%=basePath%>js/CalendarWithOutFormat.html"
		}
		var retval = window.showModalDialog(url, "", "dialogWidth:197px; dialogHeight:210px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no;help=no; "  );
		if( retval != null ){
			obj.value = retval;
		}
	}	

	//单击关闭按钮
	function clickClose(){
		//将操作标识返回到父页面， 让父页面根据此标识刷新页面
		returnValue = ('true'=='<s:property value="#request.successFlg"/>');
		window.close();
	}
	
	$(document).ready(function(){
		$("#row1").click();
		});
	
	
	function init(){	
		//$("#row1").click();
	}
	
	//验证输入
	function validate(){	
		document.forms["inputForm"].submit();
	}
	</script>
	<STYLE type="text/css">
	 A:link	{	
	text-decoration:	none;
	color:	black;
	}	
		
A:visited	{	
	text-decoration:	none;
	color:	black;
	}	
		
A:active	{	
	text-decoration:	none;
	color:	black;
	}	
		
A:hover	{	
	text-decoration:	underline;
	color:	red;
	}
	</STYLE>

  </head> 
   <body  onload="init()">
   <table width="60%" align="center" border="0">
		<tr >
	
			<td><span style="font-size:14px;">当前盘点的库房:</span>&nbsp;&nbsp;${stocktakingInfo.storeroomName }</td>
			
			<td><span style="font-size:14px;">当前状态:</span>&nbsp;&nbsp;<s:if test="#request.stocktakedFlag!=true">未盘点</s:if><s:else>已盘点</s:else>   </td>
			<td><span style="font-size:14px;">盘点的时间:</span>&nbsp;&nbsp; <s:date name="#request.stocktakingInfo.stocktakingDate" format="yyyy-MM-dd"/></td>
		</tr>
	</table>
	<hr style="margin: 0;padding: 0"/>
	<center>
	<table width="100%" cellpadding="3px" border="1" style="border-collapse: collapse; color: blue;">
		<tr height="30px;">
			<td align="center" style="background-color:#eef5ff;" ><a id="row1" href="<%=basePath%>KFGL/storeroomManageAction_findStockReportArchivesCount.action?stocktakingId=${stocktakingId}" target="viewReportPage">档案数量情况</a></td>
			<td align="center" style="background-color:#eef5ff;" ><a id="row2" href="<%=basePath%>KFGL/storeroomManageAction_findStockReportSystemNotExist.action?stocktakingId=${stocktakingId}" target="viewReportPage">系统中档案不在架情况</a></td>
			<td align="center" style="background-color:#eef5ff;" ><a id="row3" href="<%=basePath%>KFGL/storeroomManageAction_findStockReportPaperNotExist.action?stocktakingId=${stocktakingId}" target="viewReportPage">实物档案不在架情况</a></td>
			<td align="center" style="background-color:#eef5ff;" ><a id="row4" href="<%=basePath%>KFGL/storeroomManageAction_findStockReportAddressNotMatch.action?stocktakingId=${stocktakingId}" target="viewReportPage">上架位置不匹配的档案情况</a></td>
			<td align="center" style="background-color:#eef5ff;" ><a id="row5" href="<%=basePath%>KFGL/storeroomManageAction_findStockReportArchivesBoxNotMatch.action?stocktakingId=${stocktakingId}" target="viewReportPage">档案装盒不匹配情况</a></td>
		</tr>
	</table>
	</center>

	<iframe name="viewReportPage" src="<%=basePath%>KFGL/storeroomManageAction_findStockReportArchivesCount.action?stocktakingId=${stocktakingId}" width="100%" height="90%" frameborder="0" style="overflow: auto;" >
	
	</iframe>
盘点报告
  </body>
</html>
