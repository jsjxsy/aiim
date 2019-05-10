<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<title>添加用户</title>

    <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.form.js"></script>
	

   <script type="text/javascript" src="dwr/util.js"></script>
   <script type="text/javascript" src="dwr/engine.js"></script>
	<script type="text/javascript" src="dwr/interface/UserInfoManageDWR.js"></script>	
	<script type="text/javascript" >
	
	  $(document).ready(function(){
		 
	    $("#OK").bind("click",function(){
	    	if(check() == true){
		    	var UserID=${userInfo.userID}
	    		 var password=document.getElementById("password").value;
	    		UserInfoManageDWR.modifyPassword(UserID,password,callback);
		    	}
	    });
	    $("#cancel").bind("click",function(){
	       window.close();
	    });
	    
	  });

  function callback(data){
	
	  if(data){
		  window.returnValue = 1;
		  alert("修改用户密码成功！");
		  window.close();
		 }
	  else{
		alert（”修改用户密码失败！“）；
	  }
	 }

	  
 function check(){
 var password=document.getElementById("password").value;
 var confirmUserPWD=document.getElementById("confirmUserPWD").value;
        // 不为空的文本框的ID
     	if(password == ""){
 		 	alert("密码不能为空!");
 			 document.getElementById("password").focus();
          return false;
 		 }else if(confirmUserPWD == ""){
 		 	alert("确认密码不能为空!");
 		 	document.getElementById("confirmUserPWD").focus();
          return false;
 		 }else if(confirmUserPWD != password){
 			alert("密码和确认密码不一致!");
 		 	document.getElementById("password").focus();
 		 }else{
 			 return true;
 	 	 }
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
	font-size:12px;
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
</style>

</head>

<body style="margin:0px; background-color:#f9f9f9;">
		<input  type="hidden" id="userID" name="userID" value="<s:property value="#request.userInfo.UserID"/>"/>
		<table id="inputs" width="100%"
			style="height: 100%; margin-left: 50px; font-size: 15px;"
			cellpadding="0" cellspacing="0">
			<tr>
				<td height="26" colspan="2">
					<div align="center">
						<strong>修改用户密码</strong>
					</div>
				</td>
			</tr>
			<tr>
				<td width="70" height="26">
					用户名
					<font color="#FF0000">*</font>
				</td>
				<td>
					<input type="text" id="userName" name="userName" value="<s:property value="#request.userInfo.UserName"/>" disabled="disabled"/>
				</td>
			</tr>
			<tr>
				<td height="26">
					密码
					<font color="#FF0000">*</font>
				</td>
				<td>
					<input type="password" id="userPWD" name="userPWD"  value="<s:property value="#request.userInfo.UserPWD"/>"/>
				</td>
			</tr>
			<tr>
				<td height="26">
					新密码
					<font color="#FF0000">*</font>
				</td>
				<td>
					<input type="password" name="password" id="password" value=""/>
				</td>
			</tr>
			<tr>
				<td height="26">
					确认密码
					<font color="#FF0000">*</font>
				</td>
				<td>
					<input type="password" name="confirmUserPWD" id="confirmUserPWD" value=""/>
				</td>
			</tr>
			<tr>
				<td>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
				<td>
					<input type="button" name="OK" id="OK" value="提交"/>
					&nbsp;&nbsp; &nbsp;&nbsp;
					<input type="button" name="cancel"  id="cancel" value="取消" />
				</td>
			</tr>
		</table>
</html>
