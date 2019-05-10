<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.springframework.web.context.request.SessionScope"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>用户管理</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/common.css">
		<script type="text/javascript" src="js/common.js"></script>
	    <script type="text/javascript" src="dwr/util.js"></script>
	    <script type="text/javascript" src="dwr/engine.js"></script>		
		<script type="text/javascript">


//单击check框事件，控制按钮的可用/不可用状态
	function oneSelect(obj) {
		var elements=document.getElementsByTagName("input");
		var SelAll=document.getElementById("SelectAll");
		var iCount=0;//总数
		var iCheck=0;//选 中总数	
		var objDel=document.getElementById("imgDel");//删除		
		for (i=0; i<elements.length; i++) {
			if (elements[i].type=="checkbox"  && elements[i].name == "departIDs") {
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
		if(iCheck>0){//有记录被选中
			$("imgDel").disabled = false;
			$("imgDel").src="images/del.gif";
		}else{//无记录被选中
			$("imgDel").disabled = true;
			$("imgDel").src="images/del3.gif";
		}		
	}


	//全选
	function allSelect(obj) {
		var elements=document.getElementsByTagName("input");
		var checkedNum = 0;
		for (i=0; i<elements.length; i++) {
			if (elements[i].type=="checkbox" && elements[i].name == 'departIDs') {
				elements[i].checked = obj.checked;
				checkedNum++;
			}
		}
		if(obj.checked && checkedNum>0){
			$("imgDel").disabled = false;
			$("imgDel").src="images/del.gif";
		}else{//无记录被选中
			$("imgDel").disabled = true;
			$("imgDel").src="images/del3.gif";
		}	
	}

	
	//处理单击事件
var rowId="";   //保存上一次点击行“tr”的ID；
var rowColor="";   //保存上一次点击行的颜色
function clickRow(obj)
{
	if(document.getElementById(rowId)==null){//第一次点击处理
		rowId=obj.id;	//保存被点击行的ID
		rowColor=obj.style.backgroundColor;//保存被点击行的颜色
		obj.style.backgroundColor='#a3c9ff';
	}else{
		document.getElementById(rowId).style.backgroundColor=rowColor;
		obj.style.backgroundColor='#a3c9ff';
		rowId = obj.id;
	}	 
}



//////////////////单击事件////////////////////////

//打开新增对话框
	function showAdd() {
		var returnValue = window.showModalDialog(				
			"<%=basePath%>GLYWH/departmentItem.jsp",
			"",
			"dialogWidth=360px;dialogHeight:200px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;resizable=no;");
		if(returnValue==1){
			window.location.reload();
			}
	}

	//双击查看著录信息
	function showItem(rowObj) {		
		var ID = rowObj.id;	
		var returnValue = window.showModalDialog(
				"GLYWH/findDepartmentInfoByID.action?ID="+ID,
				"",
						"dialogWidth=360px;dialogHeight:200px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;resizable=no;");
		if(returnValue==1){
			window.location.reload();
		}
    }

     //单击批量删除
	 function clickBatchDel(){
		 targetForm = document.forms["showForm"];
		 targetForm.action="GLYWH/deleteDepartmentInfos.action";
		 targetForm.submit();		 
	}



</script>

<style type="text/css">
/*设置标题底色*/
.bgTitle {
	background-color: #a3c9ff;
	height: 25px;
}

/*设置表格顶部框底色*/
.borderTop {
	border-top: #104da6 1px solid;
	border-left: #104da6 1px solid;
	border-right: #104da6 1px solid;
}

body {
	height: 100%;
	color: #000000;
	font-size: 12px;
	margin: 0;
	background-color: White;
}

/*表头*/
.tableTitle {
	font-weight: bold;
	font-size:13px;
	text-align: left;
	padding: 4px 0 0 5px;
}

.text {
	font-size: 12px;
}

.tableHead {
	font-weight: bold;
	text-align: center;
	padding: 4px 0 0 0px;
}

/*设置被选行的颜色*/
.selectRowColor {
	background-color: #a4caef;
}
</style>
	</head>
	<body style="overflow: scroll;" >
		<!--文件级管理（默认）-->
		<table width="100%" style="margin: 0px;" cellspacing="0"
			cellpadding="0">
			<tr>
				<td> 
					<!-- 按钮 -->
					<input type="image" id="imgAdd" src="images/new.gif" onclick="showAdd();"
						onmouseover="changeImage(this,'new2.gif')"
						onmouseout="changeImage(this,'new.gif')" />
					<input type="image" id="imgDel" src="images/del3.gif" 
						onmouseover="changeImage(this,'del2.gif')"
						onmouseout="changeImage(this,'del.gif')"  disabled="disabled"  onclick="clickBatchDel()"/>
							
				</td>
				<td>
					<div id="location"
						style="margin-right: 2px; width: 280px; display: inline-block; float: right; margin-top: 5px; color: blue; text-align: right;">
						<font style="font-size: 12px;"><b>当前位置：</b>管理与维护&nbsp;&gt;&gt;&nbsp;&nbsp;用户管理</font>
					</div>
				</td>
			</tr>
		</table>
	
		<form name="showForm" style="margin: 0;padding: 0;">		
		
		<table  cellpadding="0" cellspacing="0"
			style="display: block; padding: 0; margin: 0; width: 100%">			
			<tr>
				<td>
					<table width="100%" cellspacing="0" cellpadding="0"
						style="margin: 0px;">
						<tr class="bgTitle">
							<td style="height: 25px" class="borderTop">
								<table style="width: 100%; height: 25px" cellspacing="0"
									cellpadding="0">
									<tr>
										<td>
											<div class="tableTitle">
												用户列表
											</div>
										</td>
										<td align="right" class="text">
											<label style="margin-right: 4px" id="rsInfo">
												共${recordSize}条记录
											</label>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	
	
		<table id="showTable" width="100%"
			style="margin: 0px; border: #104da6 1px solid;font-size:12px;" cellspacing="1px"
			cellpadding="0px">				
				<tr class="bgTitle">
					<th width="35px">
						选择
					</th>
					<th style="width: 30px;">
						序号
					</th>
					<th>
						部门名称
					</th>
					<th>
						备注信息
					</th>
				</tr>			
			<s:iterator value="#request.departmentInfos" id="id" status="rows">
				<s:if test="#rows.odd==true">
					<tr bgcolor="#eef5ff" id="<s:property value="ID"/>"  onclick="clickRow(this)" ondblclick="showItem(this)">
				</s:if>
				<s:else>
					<tr bgcolor="#e0edff"  id="<s:property value="ID"/>" onclick="clickRow(this)" ondblclick="showItem(this)">
				</s:else>	
					<td><input type="checkbox" name="departIDs" onclick="oneSelect(this)" value="<s:property value="ID" />"> </td>
					<td align="center"><s:property value="#rows.index+1"/> </td>	
					<td><s:property value="name"/></td>	
		           	<td><s:property value="remark"/></td>
		           				       
				</tr>
			</s:iterator>			
		</table>
	</form>
	
		<table width="100%" style="font-size: 12px;">
			 <tr>
			    <td>
			    	<input type="checkbox" id="SelectAll" accesskey="S"
						name="SelectAll" onclick="allSelect(this)" title="选中/取消 所有记录(S)" />
					<label for="SelectAll" style="font-size:12px;">
						全选
					</label>
			    </td>				
			</tr>
		</table>	
	</body>

</html>
