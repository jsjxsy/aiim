<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>角色信息</title>
<link rel="stylesheet" type="text/css" href="css/common.css"/>
		<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript"src="dwr/interface/UserRoleManageDWR.js"></script>
<script type="text/javascript" src="dwr/engine.js"></script>

<script type="text/javascript" >
//改变图片背景颜色
function changeImage(obj,img) {
	if (obj) {
		obj.src="images/"+img;
	}
}
function showAdd()
{
	var returnValue =window.showModalDialog("newRole.jsp",
			window,
			"dialogWidth=300px;dialogHeight:100px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");
	if(returnValue == 1){
	 window.location.reload();
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
function showUpdate(ID){
	var returnValue = window.showModalDialog("userRoleManageAction_modifyUserRole.action?ID="+ID,
			window,
			"dialogWidth=300px;dialogHeight:100px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");
	if(returnValue == 1){
		window.location.reload();
	}
}
//单击批量删除
//单击批量删除
function clickBatchDel(){
	 if(confirm("确认删除？")){
		 $("#rform").submit();
		 /*
        $("#rform").ajaxSubmit({
		    success:function(data){
		    alert("删除成功!");
		    },
		    error:function(XMLHttpRequest, textStatus){alert(textStatus+","+XMLHttpRequest.status);}	     
	     });
	     */
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
	}else {
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


function userAdd(ID)
{
	window.showModalDialog(
			"userRolesInfoManageAction_findAllUserRolesByRoleID.action?ID="+ID,
			"newwindow",
			"dialogWidth=700px;dialogHeight:500px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");
}

function getAllCheckedRoleID(){
	var RoleIDS =new Array(); 
	var elements=document.getElementsByTagName("input");
	for (i=0; i<elements.length; i++) {
		if (elements[i].type=="checkbox" && elements[i].name == 'IDs' && elements[i].checked==true) {
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


<table id="rolesInfo" cellpadding="0" cellspacing="0" 
	style=" display:block; padding:0; margin:0; width:100%" >
	<tr>
		<td>		
		<table width="100%" style="margin:0px;" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<input type="image" id="imgAdd" src="images/new.gif"
									onclick="showAdd();" onmouseover="changeImage(this,'new2.gif')"
									onmouseout="changeImage(this,'new.gif')" />
					<input type="image" id="imgDel" src="images/del3.gif"
									onmouseover="changeImage(this,'del2.gif')"
									onmouseout="changeImage(this,'del.gif')" disabled="disabled"
									onclick="clickBatchDel()" />
					<div style="margin-right:2px; display:block; width:280px; float:right; margin-top:5px; color:blue; text-align:right;">
					<font style="font-size:12px;font-weight:bold;">当前位置：</font>角色信息&nbsp;&gt;&gt;&nbsp;&nbsp;角色</div >
				</td>
			</tr>
		</table>
		</td>
		</tr>
		</table>
		<table width="100%" cellspacing="0" cellpadding="0" style="margin:0px;">
			<tr class="bgTitle">
				<td style="height:25px" class="borderTop">
					<table style="width:100%;height:25px" cellspacing="0" cellpadding="0">
				            <tr>
			                  <td>
		                        <label class="tableTitle">角色信息—角色</label></td>
				                 <td align="right"  class="text" >
								     <label style="margin-right:4px" id="rsInfo"></label>
								  </td>						                	
				            </tr>
				    </table>
				</td>
			</tr>
		</table>
			<form id="rform" name="rform" method="post" action="XTGL/userRoleManageAction_delUserRoles.action" style="margin: 0px;padding: 0px">
			  <table id="showTable" width="100%" style="margin:0px; border: #104da6 1px solid;" cellspacing="1px" cellpadding="0px" >						
				<thead class="tableHead">
				<tr class="bgTitle">
                    <th width="40">选择</th>
                    <th>序号</th>
                    <th>角色名称</th>
                    <th>备注</th>
                  <th>操作</th>
                  </tr>
                </thead>
                <tbody>
                  <s:iterator var="userRoles" value="#request.userRoles" status="u">
                    <tr  bgcolor="#e0edff">
                     <td align="center"><input name="ids" type="checkbox" id="ids"  onclick="oneSelect(this)"  value="<s:property value="ID"/>"/></td>
                     <td> <s:property value="#u.index+1"/></td>
               	 	 <td><s:property value="Name"/></td>
               	 	  <td><s:property value="Remark"/></td>
                     <td align="center"><a href="javascript:showUpdate(<s:property value="ID"/>)"> 修改</a>&nbsp;<a href="javascript:userAdd(<s:property value="ID"/>)"> 用户</a></td>
                   </tr>
                </s:iterator>
                </tbody>
              </table>
			</form>
					<table width="100%" style="font-size: 12px;">
						<tr>
							<td>
								<input type="checkbox" id="SelectAll" accesskey="S"
									name="SelectAll" onclick="allSelect(this)"
									title="选中/取消 所有记录(S)" />
								<label for="SelectAll">
									全选
								</label>
							</td>
						</tr>
						<tr>
							<td>
							</td>
							<td>
							</td>
						</tr>
					</table>
</body>
</html>
