<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<base href="<%=basePath%>" target="_self"/>   
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
<title>添加角色</title>

    <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.form.js"></script>
	

   <script type="text/javascript" src="dwr/util.js"></script>
   <script type="text/javascript" src="dwr/engine.js"></script>
	<script type="text/javascript" src="dwr/interface/UserRoleManageDWR.js"></script>	
	<script type="text/javascript" >
	
	  $(document).ready(function(){
	    $("#OK").bind("click",function(){
	    	var formMap = dwr.util.getValues("saveRole");
	    	if(check() == true){
	    		UserRoleManageDWR.addUserRole(formMap,callback);
		    	}
	    });
	    $("#cancel").bind("click",function(){
	       window.close();
	    });
	    
	  });

	  function callback(data){
		  if(data){
			  window.returnValue = 1;
			  alert("添加角色成功！");
			  window.close();
			 }
		  else{
			alert（”添加角色失败！“）；
		  }
		}

	  function check(){
		  var name=document.getElementById("name").value;
		  
		         // 不为空的文本框的ID
		      	if (name == ""){
		           	alert("角色名称不能为空!");
		  		 	document.getElementById("name").focus();
		           return false;
		          }else{
		  		 return true;
		  		 }
		  }
 </script>
<style type="text/css">
#inputs input,select,textarea{
	background-color:;
}
</style>

</head>

<body style="margin:0px; background-color:#f9f9f9;">
		<form action="XTGL/userRoleManageAction_addUserRole.action" id="saveRole" name="saveRole" method="post">
			<table id="inputs" width="250"
				style="height: 100%;  font-size: 15px;"
				cellpadding="0" cellspacing="0">
				<tr>
					<td height="17" colspan="2">
						<div align="center">
							<strong>添加角色信息</strong>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						角色
					</td>
					<td>
						<input type="text" name="name" id="name" />
					</td>
				</tr>
				<tr>
					<td>
						备注
					</td>
					<td>
						<input type="text" name="remark"  id="remark"  />
					</td>
				</tr>
				<tr align="center">
					<td height="21" colspan="2">
						<input type="button" name="OK" id="OK" value="提交" />
						&nbsp;&nbsp;&nbsp;
						<input type="button" id="cancel" name="cancel" value="取消"/>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
