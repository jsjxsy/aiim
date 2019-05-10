<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>查看库房下一级结构</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

 <script type="text/javascript">
	function showKFSBDY()
	{
		//storeroomStructureDefinition.jsp
			window.showModalDialog("KFGL/findWholeStoreroomStructure.action","newwindow","dialogWidth=600px;dialogHeight:450px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");

	}

//改变图片背景颜色
function changeImage(obj,img) {
	if (obj) {
		obj.src="images/"+img;
	}
}

</script>


</head>

<body style="margin:0">
<table width="100%" style="margin:0px; font-size:12px;" cellspacing="0" cellpadding="0">
	<tr>
		<td>
			<input type="image" style="margin-left: 3px;" src="images/back.gif" onmouseover="changeImage(this,'back2.gif')" onmouseout="changeImage(this,'back.gif')" onclick=" window.history.back();"/> 
		</td>
		<td>
			<div style="margin-right:2px; display:inline-block; width:300px; color:blue; float:right; margin-top:5px;" align="right">
						<font style="font-size:12px;font-weight:bold;">当前位置：</font>库房管理&nbsp;&gt;&gt;&nbsp;库房设施管理</div >
		</td>
	</tr>	
</table>
<div style="width: 60%">
<table cellpadding="2px;" cellspacing="" width="80%"  style="margin-left:10px;border-collapse:collapse; width:100%;font-size: 14px;" border="1" bordercolor="white" >
	<s:iterator status="rowStatus" value="#request.storeroomStructures" >
		<tr>
		<td align="right" >
			<s:property value="fullName" />
			
		</td>
		<td >
			<div 
			<s:if test="endFlag==false">	 
				onclick="location.href='<%=basePath%>KFGL/storeroomManageAction_showSubStructureByID.action?storeroomStructureID=<s:property value="ID" />'"		
			</s:if> style="width:600px; height:40px;<s:if test="endFlag==false">cursor:pointer;</s:if>vertical-align:middle; border:1px #104da6  solid;">			
				<div style=" 
				<s:if test="usedSize*100/totalSize<80">background-color:#7d9be3;</s:if>
				<s:else>background-color:#EEC900;</s:else> display:inline;width:<s:property value="usedSize*100/totalSize" />%;height: 100%"></div>
			</div>
		</td>
		<td>
			<s:property value="usedSize" />/<s:property value="totalSize" />
		</td>
	</tr>	
	</s:iterator>
</table>
</div>
</body>

</html>
