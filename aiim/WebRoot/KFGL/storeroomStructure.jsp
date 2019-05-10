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
    
    <title>库房设施管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

 <script type="text/javascript">
	//KF_definition.htm
		function showKFSBDY()
		{
			//storeroomStructureDefinition.jsp
				window.showModalDialog("<%=basePath%>KFGL/storeroomManageAction_findWholeStoreroomStructure.action","newwindow","dialogWidth=600px;dialogHeight:450px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");
	
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
			<!-- <input type="image" src="images/kfdefine.gif" onmouseover="changeImage(this,'kfdefine2.gif')" onmouseout="changeImage(this,'kfdefine.gif')" onclick="showKFSBDY()"/>&nbsp; -->
		</td>
		<td>
			<div style="margin-right:2px; display:inline-block; width:300px; color:blue; float:right; margin-top:5px;" align="right">
						<font style="font-size:12px;font-weight:bold;">当前位置：</font>库房管理&nbsp;&gt;&gt;&nbsp;库房设施管理</div >
		</td>
	</tr>	
</table>

<table cellpadding="" cellspacing="10px;">
	<tr style="height:15px;">
		<td>
			&nbsp;
		</td>
	</tr>
	<tr>
	
		<s:iterator value="#request.storeRooms"
			status="rowstatus">	
			<td>
				<!-- <input type="image" src="images/archives.gif" onclick="location.href='<%=basePath%>KFGL/storeroomManageAction_findColumnByRoomID.action?roomID=<s:property value="ID" />&roomName=<s:property value="name" />'"/> -->
				<input type="image" src="images/archives.gif" onclick="location.href='<%=basePath%>KFGL/storeroomManageAction_showSubStructureByID.action?storeroomStructureID=<s:property value="ID" />'"/>
				<div align="center"><s:property value="name" /><br/>(<s:property value="usedSize" />/<s:property value="totalSize" />)</div>
			</td>
		</s:iterator>		
	</tr>
</table>
</body>
</html>
