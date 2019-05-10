<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.orifound.aiim.entity.UserInfo" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<UserInfo> userInfos = (List<UserInfo>)request.getAttribute("userInfos");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>账户信息</title>
		<link rel="stylesheet" type="text/css" href="css/common.css">
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" >
//又击查看著录信息
function showRole(UserID)
{
	window.showModalDialog("userInfoManageAction_findAllUserRolesByUserID.action?UserID="+UserID,
			window,
			"dialogWidth=700px;dialogHeight:500px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;resizable=no;");

}


//简单查询
function showfind()
{
	document.getElementById('findDiv').style.display=(document.getElementById('findDiv').style.display=='none')? 'block':'none';
}

//打开新增对话框
function showAdd()
{
	var returnValue = window.showModalDialog("userInfoManageAction_addUserInfo.action",
			 "newwindow",
			"dialogWidth=350px;dialogHeight:400px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");
	if(returnValue==1){
		window.location.reload();
	}
}

//修改对话框
function showUpdate(UserID){
	var returnValue = window.showModalDialog("userInfoManageAction_modifyUserInfo.action?UserID="+UserID,
			 "newwindow",
			"dialogWidth=350px;dialogHeight:400px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");
	if(returnValue==1){
		window.location.reload();
	}
}
//单击批量删除
function clickBatchDel(){
	 if(confirm("确认删除？")){
        $("#uform").ajaxSubmit({
       	url:"XTGL/userInfoManageAction_deleteUserInfos.action",
		    success:function(data){
			    document.conditionForm.submit();
		    },
		    error:function(XMLHttpRequest, textStatus){alert(textStatus+","+XMLHttpRequest.status);}	     
	     });
    }			 		 
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

function changePic() {		
	var objDel=document.getElementById("imgDel");//删除
	if (objDel.disabled==true) {
		objDel.src="images/del3.gif";
	}
	else {
		objDel.src="images/del.gif";
	}
}

//全选时,控制按钮的可用/不可用状态控制
function allSelect(obj) {
		var elements=document.getElementsByTagName("input");
		var iCount=0;
		var objDel=document.getElementById("imgDel");//删除
		
		var objDel=document.getElementById("imgDel");			
		for (i=0; i<elements.length; i++) {
			if (elements[i].type=="checkbox" && elements[i].id != obj.id) {
				iCount++;
				elements[i].checked = obj.checked;
			}
		}
		objDel.disabled=!obj.checked;
		changePic();
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


		<table id="userInfo" cellpadding="0" cellspacing="0"
			style="display: block; padding: 0; margin: 0; width: 100%">
			<tr>
				<td>
					<table width="100%" style="margin: 0px;" cellspacing="0"
						cellpadding="0">
						<tr>
							<td>
								<input type="image" id="imgAdd" src="images/new.gif"
									onclick="showAdd();" onmouseover="changeImage(this,'new2.gif')"
									onmouseout="changeImage(this,'new.gif')" />
								<input type="image" id="imgDel" src="images/del3.gif"
									onmouseover="changeImage(this,'del2.gif')"
									onmouseout="changeImage(this,'del.gif')" disabled="disabled"
									onclick="clickBatchDel()" />
								<input type="image" src="images/find.gif"
									onmouseover="changeImage(this,'find2.gif')"
									onmouseout="changeImage(this,'find.gif')"
									onclick="showfind()" alt="显示查询(Q)" />
								<div
									style="margin-right: 2px; display: block; width: 280px; float: right; margin-top: 5px; color: blue; text-align: right;">
									<font style="font-size: 12px; font-weight: bold;">当前位置：</font>账户信息&nbsp;&gt;&gt;&nbsp;&nbsp;用户管理
								</div>
							</td>
						</tr>
					</table>
					<form action="XTGL/userInfoManageAction_findAllUsers.action" id="conditionForm" name="conditionForm">
					  <fieldset id="findDiv" style="display: none; width: 100%; margin-top: 3px; height: 150px;">
						<table class="findTB" style="font-size: 12px; display: block;" align="center">
							<input type="hidden" name="dataPageInfo.currentPage"
						      id="currentPage" value="1">
							<tr style="height: 26px;" align="right">
								<td>
									证件号码
								</td>
								<td align="left">
									<input type="text" id="IDCardNo" name="IDCardNo"
										style="width: 310px;" />
								</td>
							</tr>

							<tr style="height: 26px" align="right">
								<td>
									真实姓名
								</td>
								<td align="left">
									<input type="text" id="RealName" name="RealName"
										style="width: 310px;" />
								</td>
							</tr>
							
							<tr>
				<td height="26">
					职务
				</td>
				<td>
					<select name="DutyID" id="DutyID">
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
							
			<tr style="height: 40px;">
				<td></td>
				<td align="left">
					<input type="submit" value="" class="submitButton" onmouseover="changeSubmitBgImage(this,'search2.gif')" onmouseout="changeSubmitBgImage(this,'search.gif')" onclick="javascript:document.getElementById('findDiv').style.display='none';"/>
				</td>
			</tr>
		</table>
		</fieldset>
		</form>

		<table width="100%" cellspacing="0" cellpadding="0" style="margin:0px;">
						<tr class="bgTitle">
							<td style="height:25px" class="borderTop">
								<table style="width:100%;height:25px" cellspacing="0" cellpadding="0">
							            <tr>
						                  <td>
					                        <label class="tableTitle">账户信息—用户管理</label></td>
							                <td align="right"  class="text" >
							                	<label style="margin-right:4px" id="rsInfo">第${dataPageInfo.currentPage}页 共${dataPageInfo.pageCount}页 共${dataPageInfo.rowCount}条记录</label>
							                </td>							                	
							            </tr>
							    </table>
							</td>
						</tr>
					</table>				
			
		
				<form  name="uform" method="post" id="uform" style="margin: 0;padding: 0;">
					<table id="showTable" width="100%" style="margin:0px; border: #104da6 1px solid;" cellspacing="1px" cellpadding="0px" >						
						<thead class="tableHead">
							<tr class="bgTitle">
								<th width="40" height="22">选择</th>								
								<th width="40">序号</th>
								<th width="80">用户名</th>								
								<th width="130">真实姓名</th>
								<th width="100">证件号码</th>
								<th width="100">部门名称</th>
								<th width="140">职位</th>
								<th width="100">电子邮件</th>
								<th width="120">电话</th>
							<th width="161">操纵</th>
							</tr>
						</thead>
						<tbody>
						<%
						 for(int i=0;i<userInfos.size();i++) {
							 UserInfo  userInfo = (UserInfo)userInfos.get(i);
							out.println("<tr  bgcolor=\"#e0edff\" id=\"row1\">");
							out.println("<td align=\"center\"><input type=\"checkbox\" name=\"userIDs\" value="+userInfo.getUserID()+" onclick=\"oneSelect(this)\" /></td>");
							out.println("<td>"+(i+1)+"</td>");
							out.println("<td>"+userInfo.getUserName()+"</td>");
							out.println("<td>"+userInfo.getRealName()+"</td>");
							out.println("<td>"+userInfo.getIDCardNo()+"</td>");
							if(userInfo.getDepartmentName() == null){
								out.println("<td></td>");
							}else{
								out.println("<td>"+userInfo.getDepartmentName()+"</td>");
							}
							if(userInfo.getDutyName() == null){
								out.println("<td></td>");
							}else{
								out.println("<td>"+userInfo.getDutyName()+"</td>");
							}
							if(userInfo.getEmail() == null){
								out.println("<td></td>");
							}else{
								out.println("<td>"+userInfo.getEmail()+"</td>");
							}
							if(userInfo.getTel() == null){
								out.println("<td></td>");
							}else{
								out.println("<td>"+userInfo.getTel()+"</td>");
							}
							out.println("<td align=\"center\"><a href=\"javascript:showUpdate("+userInfo.getUserID()+")\">修改</a> &nbsp;<a href=\"javascript:showRole("+userInfo.getUserID()+")\">角色</a> </td>");
							out.println("</tr>");
						}
						%>
						</tbody>
				  </table>
			</form>
			
			<table width="100%" style="font-size: 12px;">
			<tr>
				<td>
					&nbsp;&nbsp;&nbsp;
					<input type="checkbox" id="SelectAll" accesskey="S"  name="SelectAll" onclick="allSelect(this)" title="选中/取消 所有记录(S)"  /><label for="SelectAll">全选</label>
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
