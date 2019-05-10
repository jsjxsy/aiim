<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
<title>修改角色</title>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/interface/UserRoleManageDWR.js"></script>	
<script type="text/javascript" >
  $(document).ready(function(){
	    $("#OK").bind("click",function(){
	    	var formMap = dwr.util.getValues("updateRole");
	    	if(check() == true){
	    		UserRoleManageDWR.updateUserRole(formMap,callback);
		    	}
	    });
	    $("#cancel").bind("click",function(){
	       window.close();
	    });
	    
	  });

	  function callback(data){
		  if(data){
			  window.returnValue = 1;
			  alert("修改角色成功！");
			  window.close();
			 }
		  else{
			alert（”修改角色失败！“）；
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
		<form action="" method="post" id="updateRole" name="updateRole" >
		<input type="hidden" id="iD" name="iD" value="<s:property value="#request.userRole.ID"/>"/>
			<table id="inputs" width="250" style="font-size: 15px;" cellpadding="0" cellspacing="0" border="1">
				<tr align="center">
					<td colspan="2">
							<strong>修改角色信息</strong>
					</td>
				</tr>
				<tr>
					<td  width="80" align="right">
						角色
					</td>
					<td>
						<input type="text" id="name" name="name" value="<s:property value="#request.userRole.Name"/>"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						备注
					</td>
					<td>
						<input type="text" id="remark" name="remark" value="<s:property value="#request.userRole.Remark"/>" />
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<input type="button" id="OK" name="OK" value="提交" />&nbsp;&nbsp;&nbsp;
						<input type="button" id="cancel" name="cancel" value="取消"/>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
