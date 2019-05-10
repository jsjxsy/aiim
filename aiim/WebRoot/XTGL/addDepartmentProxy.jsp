<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<link rel="stylesheet" type="text/css" href="css/common.css">
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript"src="dwr/interface/UserChargeDepartmentManageDWR.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript">


if (typeof window['DWRUtil'] == 'undefined') 
    window.DWRUtil = dwr.util; 
  $(document).ready(function(){
    $("#OK").bind("click",function(){
    	saveUserChargeUserInfo();
    });
    $("#cancel").bind("click",function(){
       window.close();
    });
    
  });
//进行归档
function saveUserChargeUserInfo(){
	var DepartmentIDS =new Array(); 
	DepartmentIDS=getAllCheckedDepartmentID();
	var UserID = ${UserID};
	if(DepartmentIDS.length <= 0){
		alert("请选择要添加的用户代理!");
		}else{
			UserChargeDepartmentManageDWR.saveUserChargeDepartment(DepartmentIDS,UserID,saveUserChargeUserInfoCallBack);
		}
   }

 function saveUserChargeUserInfoCallBack(data){
     if(data==true){
    	 window.returnValue = 1;
         alert("添加部门成功！");
         window.close();
         }else{
		alert("添加部门失败");
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

	function getAllCheckedDepartmentID(){
		var DepartmentIDS =new Array(); 
		var elements=document.getElementsByTagName("input");
		for (i=0; i<elements.length; i++) {
			if (elements[i].type=="checkbox" && elements[i].name == 'DepartmentIDS' && elements[i].checked==true) {
				DepartmentIDS.push(elements[i].value);
			}
		  }	
		  return DepartmentIDS;
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
								<label style="margin-right: 4px" id="rsInfo">
									第1页 共3页 共35条记录
								</label>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>

		<table id="showTable" width="100%" style="margin:0px; border: #104da6 1px solid;" cellspacing="1px" cellpadding="0px" >						
						<thead class="tableHead">
							<tr class="bgTitle">
								<th width="35">选择</th>
								<th>序号</th>
								<th>部门名称</th>
								<th>部门备注</th>
							</tr>
						</thead>
						<tbody>
						<s:iterator var="userChargeDepartmentInfo"  value="#request.userChargeDepartmentInfos" status="d">
							<tr  bgcolor="#e0edff" id="row1">
							   <td><input type="checkbox" name="DepartmentIDS" id="DepartmentIDS" value="<s:property value="DepartmentID"/>" /></td>
							   <td><s:property value="#d.index+1"/></td>
								<td><s:property value="Name"/></td>
								<td><s:property value="Remark"/></td>
							</tr>
						</s:iterator>
						</tbody>
						<tr>
						  <td>
						&nbsp;&nbsp;&nbsp;
						<input type="checkbox" id="SelectAll" accesskey="S"  name="SelectAll" onclick="allSelect(this)" title="选中/取消 所有记录(S)"  /><label for="SelectAll">全选</label>
						</td>
						</tr>
					</table>
			</tbody>
	  </table>
	  
		<table width="100%" style="font-size: 12px;">
			<tr align="center">
				<td>
					  <input type="button" name="OK" id="OK" value="确定" />
				    &nbsp;&nbsp;&nbsp;
					<input type="button" name="cancel" id="cancel" value="取消" /></td>
				</td>
			</tr>
		</table>
		
</body>

</html>
