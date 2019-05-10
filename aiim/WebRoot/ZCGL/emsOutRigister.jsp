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
    
    <title>去向登记</title>
    
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


//改变图片背景颜色
function changeImage(obj,img) {
	if (obj) {
		obj.src="images/"+img;
	}
}


//打开关联页面
function showGL()
{//var sResult=prompt("what is your name","");
	window.showModalDialog("<%=basePath%>ZCGL/emsRelevance.jsp","newwindow","dialogWidth=800px;dialogHeight:500px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");
}

//查看转递到该地址的所有人员列表
function showPersonList()
{
	window.showModalDialog("<%=basePath%>ZCGL/personList.jsp","newwindow","dialogWidth=800px;dialogHeight:500px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");
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
		<td><!-- 
			<input type="image" src="images/print.gif" style="margin-left:3px;" onmouseover="changeImage(this,'print2.gif')" onmouseout="changeImage(this,'print.gif')" onclick="alert('打印中...')"/>
			 -->
			 <input type="button" value="打印快递单" onclick="alert('打印...');">
			  <input type="button" value="转出单关联" onclick="showGL()">
		</td>
		<td>
			<div style="margin-right:2px; display:inline-block; width:300px; color:blue; float:right; margin-top:5px;" align="right">
						<font style="font-size:12px;font-weight:bold;">当前位置：</font>转出管理&nbsp;&gt;&gt;&nbsp;EMS转出登记</div >
		</td>
	</tr>	
</table>
	
<table width="100%" style="margin:0px; font-size:12px;" cellspacing="0" cellpadding="0">
	
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
						<th style="width: 30px;"></th>
						<th>序号</th>
						<th>编号</th>
						<th>转递单位</th>
						<th>详细地址</th>	
						<th>操作</th>	
					</tr>
				</thead>
				<tbody  id="showBody" >
				<tr id="23443" bgcolor="#eef5ff" style="height: 20px;">
					<td><input type="checkbox"> </td>
					<td>1</td>
					<td>324324</td>
					<td>北京AA公司</td>
					<td>北京海淀区板井路...</td>	
					<td><a href="javascript:showPersonList()">查看人员名单</a> </td>
				</tr>
				<tr id="1234" bgcolor="#e0edff" style="height: 20px;">
					<td><input type="checkbox"> </td>
					<td>2</td>
					<td>324324</td>
					<td>北京BB公司</td>
					<td>北京海淀区板井路...</td>	
					<td><a href="javascript:showPersonList()">查看人员名单</a></td>
				</tr>
				
				
				
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
