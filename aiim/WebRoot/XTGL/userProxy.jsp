<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.orifound.aiim.entity.UserInfo" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<UserInfo> userInfos =  (List<UserInfo>)request.getAttribute("userInfos");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>文件著录</title>
 	 <base href="<%=basePath%>" />
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

//简单查询
function showfind()
{
	document.getElementById('findDiv').style.display=(document.getElementById('findDiv').style.display=='none')? 'block':'none';
}

//改变按扭背景颜色
function changeBgColor(objImage,bgcolor)
{
	objImage.style.background=bgcolor;
}

function showProxyUser(UserID)
{
	window.showModalDialog(
	"userInfoManageAction_findUserChargeUserInfosByUserID.action?UserID="+UserID,
	window,
	"dialogWidth=400px;dialogHeight:300px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:1;resizable=no;");
}

function showProxyDepartment(UserID)
{
	window.showModalDialog(
	"userChargeDepartmentManageAction_findUserChargeDepartmentByUserID.action?UserID="+UserID,
	window,
	"dialogWidth=400px;dialogHeight:300px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:1;location:yes; resizable=no;");
}

/*翻页函数
url 请求的url地址
formName 查询条件的form
currentPage 当前页
 */
function pageTurning(formName, currentPage) {
	document.getElementById("currentPage").value = currentPage;
	document.forms[formName].submit();
}
/*
跳到某一页
 */
function gotoPage(formName) {
	var gotoPage = document.getElementById("gotoPage").value;
	pageTurning(formName, gotoPage);
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
		<table width="100%" style="margin:0px;" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<input type="image" src="images/find.gif" onmouseover="changeImage(this,'find2.gif')" onmouseout="changeImage(this,'find.gif')" onclick="showfind()" />					
			         <div style="margin-right:2px; display:block; width:280px; float:right; margin-top:5px; color:blue; text-align:right;"><font style="font-size:12px;font-weight:bold;">当前位置：</font>用户代理&gt;&gt;&nbsp;&nbsp;用户</div >			  
			    </td>
			</tr>
		</table>
			
				<form action="XTGL/userRolesInfoManageAction_UserProxyList.action" id="conditionForm" name="conditionForm">
				 <div id="findDiv" style="display:none; width:100%;margin-top:3px; height:150px;" align="center">
			       <table class="findTB">
			       	<input type="hidden" name="dataPageInfo.currentPage" id="currentPage" value="1">
					<tr style="height: 26px;" align="right">
						<td>
						证件号码
						</td>
						<td align="left">
							<input type="text" id="IDCardNo"  name="IDCardNo" style="width:310px;" />
						</td>
					</tr>
					
					<tr style="height: 26px" align="right">
						<td>
						真实姓名 
						</td>
						<td align="left"><input type="text"  id="RealName"  name="RealName" style="width: 310px;"/></td>
					</tr>
					
					<tr style="height: 26px" align="left">
						<td height="26">
					部门名称
					</td>
					<td>
					<select name="DepartmentID" id="DepartmentID">
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
					
			         <tr style="height:40px;">
			         	<td></td>
			           <td align="left"><input type="image" src="images/search.gif" onmouseover="changeImage(this,'search2.gif')" onmouseout="changeImage(this,'search.gif')"  onclick="javascript:document.getElementById('findDiv').style.display='none'; document.getElementById('showResult').style.display='block';"/><label style="width:100px;">&nbsp;</label></td>
			         </tr>
			       </table></div>
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
									用户代理—用户
								</label>
							</td>
							<td align="right" class="text">
								<label style="margin-right:4px" id="rsInfo">第${dataPageInfo.currentPage}页 共${dataPageInfo.pageCount}页 共${dataPageInfo.rowCount}条记录</label>
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
					<th width="200">部门</th>
					<th width="161">操作</th>
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
				out.println("<td>"+userInfo.getDepartmentName()+"</td>");
				out.println("<td align=\"center\"> <a href=\"javascript:showProxyUser("+userInfo.getUserID()+")\">用户代理</a> <a href=\"javascript:showProxyDepartment("+userInfo.getUserID()+")\">部门代理</a></td>");
				out.println("</tr>");
			} 
			 %>
			</tbody>
	  </table>
		<table width="100%" style="font-size: 12px;">
			<tr>
				<td>
					&nbsp;&nbsp;&nbsp;
				</td>
				<td align="right" style="width: 100px; vertical-align: bottom;">
					<s:if test="#request.dataPageInfo.previousState=='enable'">
						<a href="javascript:pageTurning('conditionForm','1')"
							style="text-decoration: none;"> <image
								src="images/firsts.gif"
								onmouseover="changeImage(this,'firsts1.gif')"
								onmouseout="changeImage(this,'firsts.gif')" alt="第一页" /> </a>
						<a
							href="javascript:pageTurning('conditionForm',${dataPageInfo.currentPage-1})"
							style="text-decoration: none;"> <image
								src="images/previouss.gif"
								onmouseover="changeImage(this,'previouss1.gif')"
								onmouseout="changeImage(this,'previouss.gif')" alt="上一页" /> </a>
					</s:if>
					<s:elseif test="#request.dataPageInfo.previousState =='disable'">
						<image src="images/firsts2.gif" alt="已经是第一页" />
						<image src="images/previouss2.gif" alt="已经是上一页" />
					</s:elseif>
					<s:if test="#request.dataPageInfo.nextState=='enable'">
						<a
							href="javascript:pageTurning('conditionForm',${dataPageInfo.currentPage+1})"
							style="text-decoration: none;"> <image src="images/nexts.gif"
								onmouseover="changeImage(this,'nexts1.gif')"
								onmouseout="changeImage(this,'nexts.gif')" alt="下一页" /> </a>
						<a
							href="javascript:pageTurning('conditionForm',${dataPageInfo.pageCount})"
							style="text-decoration: none;"> <image src="images/lasts.gif"
								onmouseover="changeImage(this,'lasts1.gif')"
								onmouseout="changeImage(this,'lasts.gif')" alt="最后一页" /> </a>
					</s:if>
					<s:if test="#request.dataPageInfo.nextState=='disable'">
						<image src="images/nexts2.gif" alt="已经是最后一页" />
						<image src="images/lasts2.gif" alt="已经是最后一页" />
					</s:if>
				</td>
				<td style="width: 100px; font-size: 12px;">
					转到第
					<input type="text" name="gotoPage" id="gotoPage"
						style="width: 18px; height: 18px" />
					页
				</td>
				<td style="width: 20px; vertical-align: bottom;">
					<input type="image" src="images/gos.gif"
						onmouseover="changeImage(this,'gos2.gif')"
						onmouseout="changeImage(this,'gos.gif')"
						onclick="gotoPage('conditionForm')" />
				</td>
			</tr>
		</table>
</body>

</html>
