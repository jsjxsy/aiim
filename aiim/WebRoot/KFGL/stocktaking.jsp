<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>" target="_self">
    
    <title>库房盘点</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="dwr/interface/StoreroomManageAction.js"></script>

    <script type="text/javascript" src="dwr/engine.js"></script>
 	<script type="text/javascript" src="js/common.js"></script>
 	<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" >


//改变图片背景颜色
function changeImage(obj,img) {
	if (obj) {
		obj.src="images/"+img;
	}
}

//单击库房盘点
function clickStocktaking(){
	var stocktakingId = $("#stocktakingId").val();
	alert(stocktakingId);
	StoreroomManageAction.executeStocktaking(stocktakingId,{callback:clickStocktakingBack,exceptionHandler:function(message){alert(message);}})
}

//单击库房盘点的回调函数
function clickStocktakingBack(data){
	alert(data);
}
</script>

<style type="text/css">
/*设置标题底色*/
.bgTitle { background-color:#a3c9ff;height:25px;}
/*设置表格顶部框底色*/
.borderTop {	border-top:#104da6 1px solid;	border-left:#104da6 1px solid;	border-right:#104da6 1px solid;}
body 
{
	height:100%;
	color: #000000; 
	font-size:13px;
	margin:0; 
	background-color:White;
}
/*表头*/
.tableTitle {font-weight:bold; text-align:left; padding:4px 0 0 5px;}
.text{ font-size:9pt;}
.tableHead{
	font-weight:bold; text-align:center; padding:4px 0 0 0px;
}
/*
#showTable tbody tr:hover{
	background-color:#a3c9ff;
}
*/
/*设置被选行的颜色*/
.selectRowColor{
	background-color:#a4caef;
}

table tr{
	height:20px;
}


</style>
</head>
<body style="overflow: scroll;">
	<table width="60%" align="center" border="0">
		<tr >
			<td><span style="font-size:14px;">当前盘点的库房:</span>&nbsp;&nbsp;${stocktakingInfo.storeroomName }</td>
			<td><span style="font-size:14px;">当前状态:</span>&nbsp;&nbsp;<s:if test="#request.stockTakedFlag!=true">未盘点</s:if><s:else>已盘点</s:else> </td>
			
		</tr>
	</table>
	<hr/>
	
<table width="100%" style="margin:0px; font-size:12px;" cellspacing="0" cellpadding="0">
	<tr >
		<td>		
			 <form id="upLoadForm" name="upLoadForm" action="KFGL/storeroomManageAction_uploadStocktakingData.action" method="POST" enctype="multipart/form-data" style="text-align: center;"> 
		   	  <input type="hidden" id="stocktakingId" name="stocktakingId" value="${stocktakingId}">  
		   	     选择文件 <input type="file" name="upload" size="30"/>		        
		     <input type="submit" value="上传 "/>
		     <input type="button" value="开始盘点" onclick="clickStocktaking()" style="margin-left: 10px;">
			</form>
		</td>
	</tr>	
</table>
	<s:if test="#request.uploadFlag==true"><center style="font-size: 15px;color: blue;">上传成功！文件大小${fileSize }Byter。</center></s:if>
	</body>
</html>
