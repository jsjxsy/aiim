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
    
    <title>用户管理</title>
    
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
	$("userID").value = 0;
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
	 targetForm = document.forms["userInfoForm"];
	 if(updateFlag){//更新
		 targetForm.action="GLYWH/updateUserInfo.action";
	 }else{//添加
		 targetForm.action="GLYWH/saveUserInfo.action";
     }
	 
	 targetForm.submit();
	
	changePic();
}





//初始化
function init(){
	if($("userID").value==""){//用户ID为空则编辑不可用
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
<form name="userInfoForm" >
	<input type="hidden" id="userID" name="userID" value="<s:property value="userID"/>"> 
	<table style="margin-left: 17px; margin-top: 5px;">
		<tr>
			<td>用户名</td>
			<td width="160px;"><input type="text" value="<s:property value="userName"/> " id="userName" name="userName"  style="width:150px;background-color: white"/></td>
			<td>姓名</td>
			<td><input type="text"  id="realName" value="<s:property value="realName"/> " name="realName" style="width:150px; background-color: white"/></td>
		</tr>
		<tr>
			<td>密码</td>
			<td width="160px;"><input id="userPWD"  type="password" style="width:150px;background-color: white"></td>	
			<td>确认密码</td>
			<td><input id="confirmUserPWD" type="password" style="width:150px;background-color: white"></td>		
		</tr>	
							
		<tr>
			<td>部门</td>
			<td width="160px;"><input value="<s:property value="departmentID"/>" id="departmentID" type="text" name="departmentID" style="width:150px;background-color: white"/></td>
			<td>职务</td>
			<td><input id="dutyID" value="<s:property value="dutyID"/>" type="text" name="dutyID" style="width:150px;background-color: white"/></td>
		</tr>
		
		<tr>
	 		<td>证件类型</td>
			<td width="160px;"><input id="iDCardTypeID" value="<s:property value="iDCardTypeID"/>" type="text" name="iDCardTypeID" style="width:150px;background-color: white"/></td>
	 		<td>证件号码</td>
	 		<td><input id="iDCardNo" type="text" value="<s:property value="iDCardNo"/>" name="iDCardNo" style="width:150px;background-color: white"/></td>
		</tr>
		
		<tr>	
	 		<td>电话</td>
	 		<td width="160px;"><input id="tel" value="<s:property value="tel"/>" type="text" name="tel" style="width:150px;background-color: white"/></td>
	 		<td>电子邮箱</td>
	 		<td><input id="email" type="text" value="<s:property value="email"/>" name="email" style="width:150px;background-color: white"/></td>
		</tr>
		<tr>
			<td>地址</td>
	 		<td colspan="3"> <input id="address" value="<s:property value="address"/>" name="address" type="text" style="width: 382px;background-color: white"> </td>
		</tr>
	</table>
</form>
  </body>
</html>
