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
    
    <title>转出详细信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/common.css"/>
	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.form.js"></script>
	<SCRIPT type="text/javascript">
	 //得到表格的一行
	 function getRow(table,array){
	    var $ths = table.children("thead").children().children();
	    var $tr = $("<tr></tr>");
	    for(var i=0;i<$ths.length;i++){
	       $tr.append("<td>"+array[i]+"</td>");
	    }
	    return $tr;
	 }

	 function updateMoveOutWay(moveOutWay,id){
		 $.ajax({
		   type: "post",
		   url: "/aiim/XSDAGL/studentFileManageAction_updateMoveOutWay.action",
		   data: "moveOutWay="+moveOutWay+"&id="+id,
		   success: function(data){
		     alert("成功！");
		     window.returnValue = 1;
			 window.close();
		   }
		}); 
     }
	</SCRIPT>
	
	
	
	
<style type="text/css">
body{
	font-size:14px;
	background-color:#f9f9f9;
}
</style>

</head>

<body>
<!-- 
<input type="image" ID="imgSave" src="images/image_save.gif"
	onmouseover="changeImage(this,'image_save2.gif')"
	onmouseout="changeImage(this,'image_save.gif')" onclick="clickSave()" />
	 -->
<fieldset style="width: 100%; margin-top: 10px;margin-left: 5px; margin-right: 5px; ">
	<legend>转出地址信息</legend>
	<table cellpadding="0" cellspacing="0" width="100%" style="margin-left:8px;font-size:12px">
		<tr height="30px">
			<td>单位名称：</td>
			<td><s:property value="#request.moveOutInfo.companyName"/></td>
			<td>单位所在地：</td>
			<td><s:property value="#request.moveOutInfo.companyAddr"/></td>				
		</tr>
		<tr height="30px">
			<td>邮寄部门：</td>
			<td><s:property value="#request.moveOutInfo.mailingCompany"/></td>
			<td>邮寄地址：</td>
			<td><s:property value="#request.moveOutInfo.mailingAddr"/></td>				
		</tr>
	</table>
</fieldset>
<fieldset style="width: 100%; margin-top: 10px;margin-left: 5px; margin-right: 5px; ">
	<legend>人员列表</legend>	
	<div style="overflow: auto; width: 100%;height: 220px;">
	<table style="width: 100%;font-size:12px;border: #104da6 1px solid; " cellpadding="0" cellspacing="1px;">
		<thead>
			<tr class="tableHead">
				<th style="width: 30px;">序号</th>
				<th>学号</th>
				<th>姓名</th>
				<th>专业</th>	
			</tr>
		</thead>
		<tbody>
	 	  <s:iterator value="#request.moveOutInfo.studentInfos"	status="rowstatus">
				<s:if test="#rowstatus.odd==true">
					<tr bgcolor="#eef5ff" height="20px;">
				</s:if>
				<s:else>
				    <tr bgcolor="#e0edff" height="20px;">
				</s:else>
				<td align="center"><s:property value="#rowstatus.index+1" /></td>
				<td><s:property value="studentId" /></td>
				<td><s:property value="studentName"/></td>
				<td><s:property value="specialty"/></td>				
				</tr>
			</s:iterator>	
		</tbody>
	</table>
	</div>
</fieldset>

<center><input type="button" value="EMS转递" onclick="updateMoveOutWay('1','<s:property value="#request.moveOutInfo.id"/>')"/>&nbsp;&nbsp;&nbsp;&nbsp;
       <input type="button" value="机要转递" onclick="updateMoveOutWay('2','<s:property value="#request.moveOutInfo.id"/>')"/>
</center>
</body>

</html>
