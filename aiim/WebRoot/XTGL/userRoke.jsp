<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" target="_self">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<title>角色管理—用户列表</title>
<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" >
//改变图片背景颜色
function changeImage(obj,img) {
	if (obj) {
		obj.src="images/"+img;
	}
}
//又击查看著录信息
function showRole()
{
	window.showModalDialog("roles.htm","newwindow","dialogWidth=700px;dialogHeight:500px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");

}
//简单查询
function find()
{
	document.getElementById('findDiv').style.display=(document.getElementById('findDiv').style.display=='none')? 'block':'none';
}

//打开新增对话框
function showAdd(RoleID)
{
	var returnValue=window.showModalDialog(
			"userRolesInfoManageAction_findUserInfosNotInRoleID.action?RoleID="+RoleID,
			window,
			"dialogWidth=700px;dialogHeight=400px;center=yes;help=no;resizable=no;status=no;scroll=no;");
	if(returnValue == 1){
		document.getElementById("user").submit();
	}
}

//改变按扭背景颜色
function changeBgColor(objImage,bgcolor)
{
	objImage.style.background=bgcolor;
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

<body>
<form action="XTGL/userRolesInfoManageAction_findAllUserRolesByRoleID.action" id="user" name="user" >
 <input type="hidden" id="ID" name="ID" value="<s:property value="#request.rolesID"/>"/>
</form>
		<table width="100%" style="margin: 0px;" cellspacing="0"
			cellpadding="0">
			<tr>
				<td>
					<div
						style="margin-right: 2px; display: block; width: 280px; float: right; margin-top: 5px; color: blue; text-align: right;">
						<font style="font-size: 12px; font-weight: bold;">当前位置：</font>角色信息&nbsp;&gt;&gt;&nbsp;&nbsp;角色用户列表
					</div>
				</td>
			</tr>
		</table>

		<table width="100%" cellspacing="0" cellpadding="0"
			style="margin: 0px;">
			<tr class="bgTitle">
				<td style="height: 25px" class="borderTop">
					<table style="width: 100%; height: 25px" cellspacing="0"
						cellpadding="0">
						<tr>
							<td>
								<label class="tableTitle">
									角色管理—用户列表
								</label>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>

		<table id="showTable" width="100%"
			style="margin: 0px; border: #104da6 1px solid;" cellspacing="1px"
			cellpadding="0px">
			<thead class="tableHead">
				<tr class="bgTitle">
					<th width="20%">
						序号
					</th>
					<th width="20%">
						用户名
					</th>
					<th width="20%">
						真是姓名
					</th>
					<th width="20%">
						证件号码 
					</th>
					<th width="20%">
						操作
					</th>
				</tr>
			</thead>
		</tbody>
		<s:iterator var="userRolesInfo" value="#request.userRolesInfos" status="ur">
			<tr>
			<td>
			<s:property value="#ur.index+1"/>
			</td>
			<td>
			<s:property value="UserName"/>
			</td>
			<td>
			<s:property value="RealName"/>
			</td>
			<td>
			<s:property value="IDCardNo"/>
			</td>
			<td>
			   <a href="XTGL/userRole_userRolesInfoManageAction_delUserRoles.action?ID=<s:property value="ID"/>&RoleID=<s:property value="RolesID"/>">移除</a>
		    </td>
			</tr>
		</s:iterator>
			</table>
			
			<table width="100%">
			<tr align="center">
				<td height="26" colspan="2">
						<input type="button" name="add" value="添加" onclick="showAdd(<s:property value="#request.rolesID"/>)" />&nbsp;&nbsp;
						<input type="button" name="close" value="关闭" onclick="window.close()"/>
				</td>
			</tr>
			</table>
</body>

</html>
