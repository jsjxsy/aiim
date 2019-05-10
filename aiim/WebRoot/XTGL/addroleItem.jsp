<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>" target="_parent">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加角色</title>
    <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.form.js"></script>
   <script type="text/javascript" src="dwr/util.js"></script>
   <script type="text/javascript" src="dwr/engine.js"></script>
	<script type="text/javascript" src="dwr/interface/UserRolesInfoManageDWR.js"></script>
<script type="text/javascript" >
$(document).ready(function(){
	  $("#OK").bind("click",function(){
		 var  RoleIDS = new Array();
		 RoleIDS = getAllCheckedRoleIDS();
		 if(RoleIDS.length <= 0){
				alert("请选择要添加的角色!");
			 }else{
			var userID=${requestScope.UserID};	 
	  		UserRolesInfoManageDWR.addUserRolesInfoByUserID(RoleIDS,userID,callback);
		   }
	  });
	  $("#cancel").bind("click",function(){
	     window.close();
	  });
});

function callback(data){
	if(data){
	  window.returnValue = 1;
	 //dialogArguments.location.reload(true);
	 //dialogArguments.location.href=dialogArguments.location.href;
	  alert("为用户添加角色成功！");
	  	window.close();
	 }else{
		alert（”为用户添加角色失败！“）；
	}
}

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

function oneSelect(obj) {
	var elements=document.getElementsByTagName("input");
	var SelAll=document.getElementById("SelectAll");
	var iCount=0;//总数
	var iCheck=0;//选 中总数
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
	}else {
		SelAll.checked=false;
	}
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

//获取所有已经选择的角色
function getAllCheckedRoleIDS(){
	var RoleIDS =new Array(); 
	var elements=document.getElementsByTagName("input");
	for (i=0; i<elements.length; i++) {
		if (elements[i].type=="checkbox" && elements[i].name == 'RoleIDS' && elements[i].checked==true) {
			RoleIDS.push(elements[i].value);
		}
	  }	
	  return RoleIDS;
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
			  <div style="margin-right:2px; display:block; width:280px; float:right; margin-top:5px; color:blue; text-align:right;"><font style="font-size:12px;font-weight:bold;">当前位置：</font>账户信息&nbsp;&gt;&gt;用户所属角色</div >			  </td>
			</tr>
			</table>
						
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
		<table id="showTable" width="100%"
			style="margin: 0px; border: #104da6 1px solid;" cellspacing="1px"
			cellpadding="0px">
			<thead class="tableHead">
				<tr class="bgTitle" align="center">
					<th>
						选择
					</th>
					<th>
						序号
					</th>
					<th>
						角色名称
					</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator var="userRole" value="#request.userRoles" status="u">
					<tr bgcolor="#e0edff" id="row1" align="center">
						<td>
							<input type="checkbox" name="RoleIDS" onclick="oneSelect(this)" value="<s:property value="ID"/>" />
						</td>
						<td>
							<s:property value="#u.index+1" />
						</td>
						<td>
							<s:property value="Name" />
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>

		<table width="100%" style="font-size: 12px;">
						<tr>
							<td>
								<input type="checkbox" id="SelectAll" accesskey="S"
									name="SelectAll" onclick="allSelect(this)" title="选中/取消 所有记录(S)" />
								<label for="SelectAll">全选</label>
							</td>
						</tr>
					</table>
							
					<table width="100%">
						 <tr>
						   <td><div align="center">
						       <input name="OK" id="OK" type="button" value="提交" />
						       &nbsp;&nbsp;						    &nbsp;
						      <input type="button"  name="cancel" id="cancel"  value="关闭" onclick="javascript:window.close()"/>
						     </div>
							</td>
						</tr>
					</table>
</body>

</html>
