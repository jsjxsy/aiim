<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>" target="_self">
    
    <title>部门管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="dwr/util.js"></script>
    <script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript">
//全局变量 
var updateFlag =false;

//通过检查按钮的disable属性改变图片颜色
function changePic() {		
	var objAdd=document.getElementById("imgAdd");//新增
	var objEdit=document.getElementById("imgEdit");//编辑
	var objSave=document.getElementById("imgSave");	//保存
	
	if (objAdd.disabled==true) {	
		objAdd.src="images/new3.gif";
	}else {
		objAdd.src="images/new.gif";
	}

	if (objEdit.disabled==true) {	
		objEdit.src="images/edit3.gif";
	}else {
		objEdit.src="images/edit.gif";
	}

	if (objSave.disabled==true) {	
		objSave.src="images/save3.gif";
	}else {
		objSave.src="images/save.gif";
	}
}



//单击增加
function clickAdd(){
	$("imgAdd").disabled = true;
	$("imgEdit").disabled = true;
	$("imgSave").disabled = false;
	$("executeResult").innerText ="";
	$("ID").value = 0;
	changePic();
}

//单击修改
function clickEdit(){
	$("imgEdit").disabled = true;
	$("imgAdd").disabled = true;
	$("imgSave").disabled = false;
	updateFlag = true;
	changePic();
}

//单击保存
function clickSave(){
	$("imgSave").disabled = true;
	$("imgAdd").disabled = false;
	$("imgEdit").disabled = false;
	 targetForm = document.forms["departmentInfoForm"];
	 if(updateFlag){//更新
		 targetForm.action="GLYWH/updateDepartmentInfo.action";
	 }else{//添加
		 targetForm.action="GLYWH/saveDepartmentInfo.action";
     }
	 
	 targetForm.submit();
	
	changePic();
}





//初始化
function init(){
	if($("ID").value==""){//用户ID为空则编辑不可用
		$("imgEdit").disabled = true;
		$("imgAdd").disabled = true;
		$("imgSave").disabled = false;
	}else{//ID不为空时则编辑可用
		$("imgEdit").disabled = false;
	}
	changePic();
}

</script>

  </head> 
  <body style="background-color: #f9f9f9; padding-top: 2px;" onload="init()">
  
	<input type="image" id="imgAdd" src="images/new.gif" style="margin-left: 2px;" onclick="clickAdd();"
		onmouseover="changeImage(this,'new2.gif')"
		onmouseout="changeImage(this,'new.gif')" />	
	<input type="image" id="imgEdit" src="images/edit.gif" 
		onmouseover="changeImage(this,'edit2.gif')"
		onmouseout="changeImage(this,'edit.gif')" onclick="clickEdit()"/>	
	<input type="image" id="imgSave" src="images/save3.gif" 
		onmouseover="changeImage(this,'save2.gif')"
		onmouseout="changeImage(this,'save.gif')"  disabled="disabled"  onclick="clickSave()"/>
	<label style="color: blue; font-size: 12px;" id="executeResult">${message}</label>
<form name="departmentInfoForm" >
	<input type="hidden" id="ID" name="ID" value="<s:property value="ID"/>"> 
	<table style="margin-left: 17px; margin-top: 5px;">
		<tr>
			<td>部门名称</td>
			<td><input type="text" value="<s:property value="name"/> " id="name" name="name"  style="width:220px;background-color: white"/></td>
			
		</tr>
		<tr>
			<td>备注</td>
			<td ><textarea rows="4" cols="25" id="remark" name="remark" style="background-color: white" ><s:property value="remark"/></textarea></td>	
		
		</tr>								
	
	</table>
</form>
  </body>
</html>
