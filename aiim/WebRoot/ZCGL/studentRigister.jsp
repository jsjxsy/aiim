<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>学生登记</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="dwr/interface/StoreroomManageAction.js"></script>
    <script type="text/javascript" src="dwr/util.js"></script>
    <script type="text/javascript" src="dwr/engine.js"></script>
 		<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" >
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
		//'#e0edff';
		obj.style.backgroundColor='#a3c9ff';
		rowId = obj.id;
	}	 
}

//验证输入不为空
function validateInput(){
	var archivesBoxBarcode = document.getElementById('archivesBoxBarcode').value;
	var storeAddressID = document.getElementById('storeAddressID').value;
	var re = /^\d*$/;
	if(archivesBoxBarcode==''){
		$("strResult").innerText= '盒条码 不为空！';
		$("strResult").style.color='red';
		return false;
	}else if(!re.test(archivesBoxBarcode)){		
		$("strResult").innerText= '盒条码 必须是数字！';
		$("strResult").style.color='red';
		return false;
	}else if(storeAddressID==''){
		$("strResult").innerText= '设备条码不为空！';
		$("strResult").style.color='red';
		return false;
	}else if(!re.test(storeAddressID)){		
		$("strResult").innerText= '设备条码 必须是数字！';
		$("strResult").style.color='red';
		return false;
	}else{
		document.addForm.submit();
		}	
}




//改变图片背景颜色
function changeImage(obj,img) {
	if (obj) {
		obj.src="images/"+img;
	}
}

//加入移交清单
function addToList(){
	var rowObjs=document.getElementsByName('chek');
	var i = 0;
	for(i=0 ;i<rowObjs.length;i++)
	{	
		if(rowObjs[i].checked==true)
			document.getElementById(rowObjs[i].value).style.display='none';
	}
	
	
}


//打开新增对话框
function showAdd()
{
	window.showModalDialog("Item.html","newwindow","dialogWidth=700px;dialogHeight:500px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");
}

//打开移交清单
function openYJQD()
{
	window.showModalDialog("YJQD_view.htm","newwindow","dialogWidth=1000px;dialogHeight:600px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");
}

//单击批量登记按钮
function inputDat()
{
	document.getElementById('showBody').style.display='block';
	//document.getElementById('updateBtn').disabled=false;
}



//打开简易查询页面
function dlgFind()
{
	window.showModalDialog("DlgFind.htm","newwindow","dialogWidth=600px;dialogHeight:350px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");

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
	font-size:13px;
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

table tr{
	height:20px;
}


</style>
</head>
<body style="overflow: scroll;">
<table width="100%" style="margin:0px; font-size:12px;" cellspacing="0" cellpadding="0">
	<tr>
		<td>
			<input type="image" src="images/print.gif" style="margin-left:3px;" onmouseover="changeImage(this,'print2.gif')" onmouseout="changeImage(this,'print.gif')" onclick="alert('打印中...')"/>
		</td>
		<td>
			<div style="margin-right:2px; display:inline-block; width:300px; color:blue; float:right; margin-top:5px;" align="right">
						<font style="font-size:12px;font-weight:bold;">当前位置：</font>转出管理&nbsp;&gt;&gt;&nbsp;学生登记</div >
		</td>
	</tr>	
</table>
	
<table width="100%" style="margin:0px; font-size:12px;" cellspacing="0" cellpadding="0">
	<tr >
		<td>
			<span>选择文件:</span><input type="file" >&nbsp;&nbsp;<input type="button" value="上传">&nbsp;&nbsp;<input type="button" value="打印条码"> 
		</td>
	</tr>
	<tr style="height:2px;">
		<td></td>
	</tr>
	<tr>
		<td>
			<table width="100%" cellspacing="0" cellpadding="0" style="margin:0px; font-size:12px;">
				<tr class="bgTitle">
					<td style="height:25px" class="borderTop">
						<table style="width:100%;height:25px" cellspacing="0" cellpadding="0">
					            <tr>
					                <td>
					                    <font style="font-size:13px;font-weight:bold">档案</font>
					                </td>
					                <td align="right"  class="text" >
					                	<label style="margin-right:4px" id="rsInfo"> 共<span style="color:blue;font-weight:bold; font-size:13px;">${recordCount}</span>条记录</label>
					                </td>							                	
					            </tr>
					    </table>
					</td>
				</tr>
			</table>				
		</td>			
	</tr>
	<tr>
		<td>
			<table id="showTable" width="100%" style="margin:0px; border: #104da6 1px solid; font-size:12px;" cellspacing="1px" cellpadding="0px" >						
				<thead class="tableHead">
					<tr class="bgTitle">
						<th style="width:30px;">序号</th>	
						
						<s:if test="#request.personType==3">
						
							<th>工资号</th>
						</s:if>		
						<s:else>
							<th>学号</th>
						</s:else>
						<th>姓名</th>
						<th>姓别</th>
						<th>年龄</th>
						<th>专业</th>
						<th>年度</th>		
					</tr>
				</thead>
				<tbody  id="showBody" >
				<s:iterator value="#request.storeAddressInfos"	status="rowstatus">
					<s:if test="#rowstatus.odd==true">
						<tr bgcolor="#eef5ff" style="height: 20px;"
							id="<s:property value="NBXH" />" >
					</s:if>
					<s:else>
						<tr bgcolor="#e0edff" style="height: 20px;"
							id="<s:property value="archivesBoxBarcode" />" >
					</s:else>
					<td align="center">
						<s:property value="#rowstatus.index+1" />
					</td>
					<td>
						<s:property value="archivesBoxBarcode" />
					</td>
					<td>
						<s:property value="storeAddressID" />
					</td>
					<td>
						<s:property value="storeAddressFullName" />
					</td>
					<td>
						<s:property value="putTime" />
					</td>					
					
					</tr>
				</s:iterator>			
				
				</tbody>
			</table>
		</td>
	</tr>		
</table>
	</body>
</html>
