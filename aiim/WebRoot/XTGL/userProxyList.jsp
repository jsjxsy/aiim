<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.orifound.aiim.entity.UserInfo" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<UserInfo> userInfos =  (List<UserInfo>)request.getAttribute("userInfos");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<base href="<%=basePath%>" target="_self"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">  
<title>用户代理</title>
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
	//objImage.style.backgroundColor=bgcolor;
	objImage.style.background=bgcolor;
}

function showAdd(UserID)
{
	var returnValue = window.showModalDialog(
	"userInfoManageAction_findAllUserUnchargeUserInfos.action?UserID="+UserID,
	window,
	"dialogWidth=600px;dialogHeight:400px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:1; resizable=no;");
	if(returnValue == 1){
		document.getElementById("userProxy").submit();
		}
}
//简单查询
function showfind()
{
	document.getElementById('findDiv').style.display=(document.getElementById('findDiv').style.display=='none')? 'block':'none';
}

function oneSelect(obj) {
	var elements=document.getElementsByTagName("input");
	var SelAll=document.getElementById("SelectAll");
	var iCount=0;//总数
	var iCheck=0;//选 中总数
	var objDel=document.getElementById("imgDel");//删除
	for (i=0; i<elements.length; i++) {
		if (elements[i].type=="checkbox" && elements[i].id != "SelectAll") {
			iCount++;
			if (elements[i].checked==true) {
				iCheck++;
			}
		}
	}
	if (iCount==iCheck && iCount>0) {//设置全选 状态
		SelAll.checked=true;
	}
	else {
		SelAll.checked=false;
	}
	objDel.disabled=(iCheck==0);
	changePic();
}



//全选时,控制按钮的可用/不可用状态控制
function allSelect(obj) {
		var elements=document.getElementsByTagName("input");
		var iCount=0;
		
		for (i=0; i<elements.length; i++) {
			if (elements[i].type=="checkbox" && elements[i].id != obj.id) {
				iCount++;
				elements[i].checked = obj.checked;
			}
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
<form action="XTGL/userInfoManageAction_findUserChargeUserInfosByUserID.action" id="userProxy" name="userProxy" >
 <input type="hidden" id="UserID" name="UserID" value="${UserID}"/>
</form>
		<table width="100%" cellspacing="0" cellpadding="0" style="margin:0px;">
			<tr class="bgTitle">
				<td style="height:25px" class="borderTop">
					<table style="width:100%;height:25px" cellspacing="0" cellpadding="0">
				            <tr>
			                  <td>
		                        <label class="tableTitle">代理用户管理—代理用户列表</label></td>
				                <td align="right"  class="text" >
				                </td>							                	
				            </tr>
				    </table>
				</td>
			</tr>
		</table>	
								
		<table id="showTable" width="100%" style="margin:0px; border: #104da6 1px solid;" cellspacing="1px" cellpadding="0px" >						
			<thead class="tableHead">
				<tr class="bgTitle">
					<th width="68">序号</th>
					<th width="200">用户名</th>								
					<th width="200">真实姓名</th>
					<th width="200">证件号码</th>
					<th width="200">操作</th>
				</tr>
			</thead>
			<tbody>
			<% for(int i=0;i<userInfos.size();i++){
				UserInfo userInfo = (UserInfo) userInfos.get(i);
				out.println("<tr  bgcolor=\"#e0edff\" id=\"row1\"  >");
				out.println("<td>"+(i+1)+"</td>");
				out.println("<td>"+userInfo.getUserName()+"</td>");
				out.println("<td>"+userInfo.getRealName()+"</td>");
				out.println("<td>"+userInfo.getIDCardNo()+"</td>");
				out.println("<td><a href='XTGL/userChargeUserManageAction_deleteUserChargeUser.action?ID="+userInfo.getID()+"&UserID="+userInfo.getUserID()+"'>移除</a></td>");
				out.println("</tr>");
			} 
			 %>
			</tbody>
	  </table>
			<table width="100%">
			<tr align="center">
				<td height="26" colspan="2">
						 <td><input type="button" name="add" value="添加"  onclick="showAdd(${requestScope.UserID })"/>&nbsp;&nbsp;
					&nbsp;<input name="close" type="button"  value="关闭" onclick="javascript:window.close()"/></td>
				</td>
			</tr>
			</table>
</body>

</html>
