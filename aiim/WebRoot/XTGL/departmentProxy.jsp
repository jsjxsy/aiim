<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>部门代理列表</title>
<base href="<%=basePath%>" target="_self"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
<meta http-equiv="description" content="This is my page"/>
<link rel="stylesheet" type="text/css" href="css/common.css">
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" >

//改变图片背景颜色
function changeImage(obj,img) {
	if (obj) {
		obj.src="images/"+img;
	}
}

//改变按扭背景颜色
function changeBgColor(objImage,bgcolor)
{
	objImage.style.background=bgcolor;
}

function showAdd(UserID)
{
	var returnValue = window.showModalDialog("userChargeDepartmentManageAction_findUserUnChargeDepartmentByUserID.action?UserID="+UserID,
	window,
	"dialogWidth=400px;dialogHeight:400px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:1;location:yes; resizable=no;location=n");
	if(returnValue == 1){
		document.getElementById("department").submit();
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

<body>
<form action="XTGL/userChargeDepartmentManageAction_findUserChargeDepartmentByUserID.action" name="department" id="department" method="post">
	<input  type="hidden" name="UserID" id = "UserID" value="<s:property value="#request.UserID"/>"/>
</form>
		<table width="100%" cellspacing="0" cellpadding="0"
			style="margin: 0px;">
			<tr class="bgTitle">
				<td style="height: 25px" class="borderTop">
					<table style="width: 100%; height: 25px" cellspacing="0"
						cellpadding="0">
						<tr>
							<td>
								<label class="tableTitle">
									用户代理—部门
								</label>
							</td>
							<td align="right" class="text">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>

		<table id="showTable" width="100%" style="margin:0px; border: #104da6 1px solid;" cellspacing="1px" cellpadding="0px" >						
						<thead class="tableHead">
							<tr class="bgTitle">
								<th>序号</th>
								<th>部门名称</th>
								<th>部门备注</th>
							<th>操作</th>
							</tr>
						</thead>
						<tbody>
						<s:iterator var="userChargeDepartmentInfo"  value="#request.userChargeDepartmentInfos" status="d">
							<tr  bgcolor="#e0edff" id="row1">
							  <td><s:property value="#d.index+1"/></td>
								<td><s:property value="#userChargeDepartmentInfo.value.Name"/></td>
								<td><s:property value="#userChargeDepartmentInfo.value.Remark"/></td>
							<td><a href="XTGL/userChargeDepartmentManageAction_deleteUserChargeDepartment.action?ID=<s:property value="#userChargeDepartmentInfo.value.ID"/>&UserID=<s:property value="#userChargeDepartmentInfo.value.UserID"/>">移除</a></td>
							</tr>
						</s:iterator>
						</tbody>
					</table>
			</tbody>
	  </table>
	  
		<table width="100%" style="font-size: 12px;">
			<tr align="center">
				<td>
					<input type="button" name="add" value="添加" onclick="showAdd(<s:property value="#request.UserID"/>)" />
				    &nbsp;&nbsp;&nbsp;
					<input type="button" name="close" value="关闭" onclick="javascript:window.close()"/>
				</td>
			</tr>
		</table>
		
</body>
</html>
