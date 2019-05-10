<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" target="_self">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta   http-equiv="pragma"   content="no-cache">
<title>添加角色</title>
  <script type="text/javascript" src="dwr/util.js"></script>
   <script type="text/javascript" src="dwr/engine.js"></script>
	<script type="text/javascript" src="dwr/interface/UserRolesInfoManageDWR.js"></script>
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

function showRoles(UserID)
{

	var returnValue = window.showModalDialog(
			"userRolesInfoManageAction_findRoleInfosNotInUserID.action?UserID="+UserID+"&Rand="+Math.random(),
			 window,
			"dialogWidth=400px;dialogHeight:400px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:1;resizable=no;center=yes");
	if(returnValue == 1){
		document.getElementById("role").submit();
		}
}

function delUserRolesInfo(ID){
	UserRolesInfoManageDWR.delUserRoles(ID,callback);
}

function callback(data){
	if(data){
	  alert("为用户删除角色成功！");
	  window.location.reload();
	 }else{
		alert（”为用户删除角色失败！“）；
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

<form action="XTGL/userInfoManageAction_findAllUserRolesByUserID.action" id="role" name="role" >
 <input type="hidden" id="UserID" name="UserID" value="<s:property value="#request.UserID"/>"/>
</form>
<!--文件级管理（默认）-->
<table id="DA_WJ" cellpadding="0" cellspacing="0" style=" display:block; padding:0; margin:0; width:100%" >
	<tr>
		<td>		
		  <table width="100%" style="margin:0px;" cellspacing="0" cellpadding="0">
			<tr>
			  <div style="margin-right:2px; display:block; width:280px; float:right; margin-top:5px; color:blue; text-align:right;"><font style="font-size:12px;font-weight:bold;">当前位置：</font>账户信息&nbsp;&gt;&gt;用户所属角色</div >			  </td>
			</tr>
			<tr>
			  <td align="center">
			    <div id="findDiv" style="display:none; width:100%;margin-top:3px; height:100px;">
			      <fieldset>
			       <table class="findTB"  >
			         <tr style=" height:12px;">
						<td>
						</td>
					</tr>
					
					<tr style="height: 26px;" align="right">
						<td>
						角色名称
						</td>
						<td align="left">
							<input type="text" style="width:310px;" />
						</td>
					</tr>
					
			         <tr style="height:40px;">
			         	<td></td>
			           <td align="left"><input type="image" src="images/search.gif" onmouseover="changeImage(this,'search2.gif')" onmouseout="changeImage(this,'search.gif')"  onclick="javascript:document.getElementById('findDiv').style.display='none'; document.getElementById('showResult').style.display='block';"/><label style="width:100px;">&nbsp;</label></td>
			         </tr>
			       </table>
			     </fieldset>
			    </div>
			  </td>
			</tr>

			<tr>
				<td>
					<table width="100%" cellspacing="0" cellpadding="0" style="margin:0px;">
						<tr class="bgTitle">
							<td style="height:25px" class="borderTop">
								<table style="width:100%;height:25px" cellspacing="0" cellpadding="0">
							            <tr>
						                  <td>
					                        <label class="tableTitle">账户信息—用户所属角色</label></td>
						                  <td align="right"  class="text" >
					                	    <label style="margin-right:4px" id="rsInfo"></label></td>							                	
							            </tr>
							    </table>
							</td>
						</tr>
					</table>				
				</td>			
			</tr>
			<tr>
			  <td>	 <table id="showTable" width="100%" style="margin:0px; border: #104da6 1px solid;" cellspacing="1px" cellpadding="0px" >
                <thead class="tableHead">
                  <tr class="bgTitle" align="center">
                    <th>序号</th>
                    <th>角色名称</th>
                  <th>操作</th>
                  </tr>
                </thead>
                <tbody>
                <s:iterator var="userRolesInfo" value="#request.userRolesInfos" status="u">
                   <tr  bgcolor="#e0edff" id="row1" align="center">
	                   <td> <s:property value="#u.index+1"/></td>
	               	 	 <td><s:property value="Name"/></td>
	               	   <td><a href="XTGL/userInfo_userRolesInfoManageAction_delUserRoles.action?ID=<s:property value="ID"/>&UserID=<s:property value="UserID"/>">移除</a></td>
                  </tr>
                </s:iterator>
                </tbody>
              </table></td>
			</tr>
			
			<tr>
				<td>
					<table width="100%">
						 <tr>
						   <td><div align="center">
						       <input name="Submit" type="button" value="添加"  onclick="showRoles(<s:property value="#request.UserID"/>)"/>
						       &nbsp;&nbsp;						    &nbsp;
						      <input type="button"  value="关闭" onclick="javascript:window.close()"/>
						     </div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		
			
	  </table>	  </td>
	</tr>
</table>


</body>

</html>
