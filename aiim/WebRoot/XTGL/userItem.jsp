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
	    	var formMap = dwr.util.getValues("saveUser");
	    	 var userName=document.getElementById("userName").value;
	    	if(check() == true){
		    	//先检查用户名是否唯一
	    		UserInfoManageDWR.checkUserName(userName,callback2);
	     		function callback2(data2){
	     			  var count = data2.value;
	     			  if(count > 0){
	     				  alert("用户名已经存在，请重新填写");
	     			}else{
						//当用户名唯一时，检查证件号是否唯一
						 var iDCardNo=document.getElementById("iDCardNo").value;
	     				UserInfoManageDWR.checkIDCardNo(iDCardNo,callback3);
	     	 	 		function callback3(data3){
	     	 	 			var count = data3.value;
	     	 	 		  if(count > 0){
	     	 	 			  alert("证件号已经存在，请重新填写！");
	     	 	 			 }else{
		     	 	 			//当证件号唯一时才才执行保存方法
	     	 	 				UserInfoManageDWR.saveUserInfo(formMap,callback);
		     	 	 		}
	     	 	 		}
		     		}
	     	   }
	    	
		    }	
	    });
	    $("#cancel").bind("click",function(){
	       window.close();
	    });
	    
	  });

  function callback(data){
	  if(data){
		  window.returnValue = 1;
		  alert("添加用户成功！");
		  window.close();
		 }
	  else{
		alert（”添加用户失败！“）；
	  }
}
  //验证是否为数字
  function checkNumber(String)
  { 
      var Letters = "1234567890-";
      var i;
      var c;

      if(String.length == 0){
    	  return true;
      }
      for( i = 0; i < String.length; i ++ )
      {
           c = String.charAt( i );
        if (Letters.indexOf( c ) !=-1)
        {
        	 return true;
        }else {
        	 alert("电话号码必需为数字!");
     		 document.getElementById("tel").focus();
             return false;
         }
      }
 }

  function verifyAddress(obj) 
  { 
  　var email = obj.value; 
 　var pattern = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
  if(email==''){
		// alert("邮件地址不能为空"); 
		 return true; 
	}else{
		var flag = pattern.test(email); 
         if(!flag){
        	 alert("邮件格式不对"); 
     	 　　　return false;
             }
	}
} 
    
 function check(){
 var userName=document.getElementById("userName").value;
 var userPWD=document.getElementById("userPWD").value;
 var confirmUserPWD=document.getElementById("confirmUserPWD").value;
 var realName=document.getElementById("realName").value;
 var iDCardNo=document.getElementById("iDCardNo").value;
 var pFlag = true; 
        // 不为空的文本框的ID
     	if (userName == ""){
          	alert("用户名不能为空!");
 		 	document.getElementById("userName").focus();
           pFlag = false;
         }
         if(userPWD == ""){
 	  		alert("密码不能为空!");
	 		document.getElementById("userPWD").focus();
	 		pFlag = false;
 		 }else if(confirmUserPWD == ""){
 		 	alert("确认密码不能为空!");
 		 	document.getElementById("confirmUserPWD").focus();
 		 	pFlag = false;
 		 }else if(confirmUserPWD != userPWD ){
 		 	alert("密码和确认密码不一样!");
 		 	document.getElementById("confirmUserPWD").focus();
 		 	pFlag = false;
 		 }else if(realName == ""){
 		 	alert("真实姓名不能为空!");
 		 	document.getElementById("realName").focus();
 		 	pFlag = false;
 		 }
		 if(iDCardNo == "" ){
 	 		alert("证件号不能为空！");
 	 		document.getElementById("iDCardNo").focus();
 	 		pFlag = false;	 
 	 	 }
	 	 return pFlag;
 }


 

</script>
<style type="text/css">
#inputs input,select,textarea{
	background-color:;
}
</style>

</head>

<body style="margin:0px; background-color:#f9f9f9;">
	<form action="XTGL/userInfoManageAction_saveUserInfo.action" method="post" name="saveUser" id="saveUser">
		<input  type="hidden" id="UserID" name="UserID" value="#request.userInfo.UserID"/>
		<table id="inputs" width="100%"
			style="height: 100%; margin-left: 50px; font-size: 15px;"
			cellpadding="0" cellspacing="0">
			<tr>
				<td height="26" colspan="2">
					<div align="center">
						<strong>添加用户信息</strong>
					</div>
				</td>
			</tr>
			<tr>
				<td width="70" height="26">
					用户名
					<font color="#FF0000">*</font>
				</td>
				<td>
					<input type="text" id="userName" name="userName" value=""/>
				</td>
			</tr>
			<tr>
				<td height="26">
					密码
					<font color="#FF0000">*</font>
				</td>
				<td>
					<input type="password" id="userPWD" name="userPWD"  value=""/>
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
				<td height="26">
					真实姓名
					<font color="#FF0000">*</font>
				</td>
				<td>
					<input type="text"  id="realName" name="realName" value=""/>
				</td>
			</tr>
			<tr>
				<td height="26">
					职务
				</td>
				<td>
					<select name="dutyID" id="dutyID">
						<option value="">
							&lt;--请选择--&gt;
						</option>
						<s:iterator var="duty" value="#request.dutys" >
							<option value="<s:property value="ID"/>">
								<s:property value="Name"/>
							</option>
						</s:iterator>
					</select>
				</td>
			</tr>
			<tr>
				<td height="26">
					部门名称
				</td>
				<td>
					<select name="departmentID" id="departmentID">
						<option value="">
							&lt;--请选择--&gt;
						</option>
						<s:iterator var="department" value="#request.departmentInfos">
							<option value="<s:property value="ID"/>">
								<s:property value="Name"/>
							</option>
						</s:iterator>
					</select>
				</td>
			</tr>
			<tr>
				<td height="26">
					证件号码
					<font color="#FF0000">*</font>
				</td>
				<td>
					<input type="text" name="iDCardNo"  id="iDCardNo" value=""/>
				</td>
			</tr>
			<tr>
				<td height="26">
					电子邮件
				</td>
				<td>
					<input type="text" name="email"  id="email" value="" onblur="verifyAddress(this)"/>
				</td>
			</tr>
			<tr>
				<td height="34">
					电话
				</td>
				<td>
					<input type="text" name="tel" id="tel" onblur="checkNumber(this.value)" value=""/>
				</td>
			</tr>
			<tr>
				<td height="34">
					地址
				</td>
				<td>
					<input type="text" name="address" id="address" value=""/>
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
	</form>
	</body>
</html>
