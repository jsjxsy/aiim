<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.orifound.aiim.entity.UserInfo"%>
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
<base href="<%=basePath%>" target="_self">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<title>向角色中添加用户</title>
<link rel="stylesheet" type="text/css" href="css/common.css">
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="dwr/interface/UserRolesInfoManageDWR.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" >
if (typeof window['DWRUtil'] == 'undefined') 
    window.DWRUtil = dwr.util; 
  $(document).ready(function(){
    $("#OK").bind("click",function(){
    	addUserRolesInfo();
    });
    $("#cancel").bind("click",function(){
       window.close();
    });
    
  });
//进行归档
function addUserRolesInfo(){
	var UserIDS =new Array(); 
	UserIDS=getAllCheckedUserID();
	var roleID = $("#roleID").val();
	if(UserIDS.length <= 0){
		alert("请选择用添加的用户!");
		}else{
		 UserRolesInfoManageDWR.addUserRolesInfoByRoleID(UserIDS,roleID,addUserRolesInfoCallBack);
			}
   }

 function addUserRolesInfoCallBack(data){
     if(data==true){
    	 window.returnValue = 1;
         alert("添加用户成功！");
         window.close();
         }else{
		alert("添加用户失败");
       }
 }
 
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

function getAllCheckedUserID(){
	var UserIDS =new Array(); 
	var elements=document.getElementsByTagName("input");
	for (i=0; i<elements.length; i++) {
		if (elements[i].type=="checkbox" && elements[i].name == 'UserIDS' && elements[i].checked==true) {
			UserIDS.push(elements[i].value);
		}
	  }	
	  return UserIDS;
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
<table id="DA_WJ" cellpadding="0" cellspacing="0" style=" display:block; padding:0; margin:0; width:100%" >
	<tr>
		<td>		
		<table width="100%" style="margin:0px;" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<input type="image" src="images/find.gif"
									onmouseover="changeImage(this,'find2.gif')"
									onmouseout="changeImage(this,'find.gif')"
									onclick="showfind()" alt="显示查询(Q)" />				
					<div style="margin-right:2px; display:block; width:280px; float:right; margin-top:5px; color:blue; text-align:right;"><font style="font-size:12px;font-weight:bold;">当前位置：</font>用户角色信息&nbsp;&gt;&gt;添加用户列表</div >			  </td>
			</tr>
	     </table>
	     
	     <form action="XTGL/userRolesInfoManageAction_findUserInfosNotInRoleID.action" id="conditionForm" name="conditionForm">
	     	<input type="hidden" id="RoleID" name="RoleID" value="<s:property value="#request.RoleID"/>" />
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
		                        <label class="tableTitle">角色管理—添加用户列表</label></td>
				                <td align="right"  class="text" >
				                	<label style="margin-right:4px" id="rsInfo">第${dataPageInfo.currentPage}页 共${dataPageInfo.pageCount}页 共${dataPageInfo.rowCount}条记录</label>
				                </td>							                	
				            </tr>
				    </table>
				</td>
			</tr>
		</table>
		
		<table id="showTable" width="100%" style="margin:0px; border: #104da6 1px solid;" cellspacing="1px" cellpadding="0px" >						
		<input type="hidden" name="roleID" id="roleID" value="<s:property value="#request.RoleID"/>" />
			<thead class="tableHead">
				<tr class="bgTitle">
					<th width="43" height="22">选择</th>								
					<th width="68">序号</th>
					<th width="115">用户名</th>								
					<th width="106">真实姓名</th>
					<th width="106">证件号码</th>
				</tr>
			</thead>
			<tbody>
			<% for(int i=0;i<userInfos.size();i++){
				UserInfo userInfo = (UserInfo) userInfos.get(i);
				out.println("<tr  bgcolor=\"#e0edff\" id=\"row1\"  >");
					out.println("<td align=\"center\"><input type=\"checkbox\" id=\"UserIDS\" name=\"UserIDS\" value=\""+userInfo.getUserID()+"\"/></td>");
				out.println("<td>"+(i+1)+"</td>");
				out.println("<td>"+userInfo.getUserName()+"</td>");
				out.println("<td>"+userInfo.getRealName()+"</td>");
				out.println("<td>"+userInfo.getIDCardNo()+"</td>");
				out.println("</tr>");
			} 
			 %>
			</tbody>
	  </table>
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
			<tr>
           <td colspan="2"><div align="center">
                         <input type="button" name="OK" id="OK" value="确定" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                         <input type="button" id="cancel" name="cancel" value="取消" />
	       </div></td>
			</tr>
		</table>
</body>

</html>
