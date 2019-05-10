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
    
    <title>位置与状态查询</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" >
//双击查看著录信息
function showItem()
{
	window.showModalDialog("Item_show.html","newwindow","dialogWidth=700px;dialogHeight:500px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");
 
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

//显示高级查询
function showADFind(){
  window.showModalDialog("dlgAdvanceFind.htm","newwindow","dialogWidth=500px;dialogHeight=280px;center=yes;scroll=no;status=no;resizable=no;help=no;location=no");
}

//简单查询
function find()
{
	document.getElementById('findDiv').style.display=(document.getElementById('findDiv').style.display=='none')? 'block':'none';
}

//显示状态
function showState(state)
{
	if(state=='out'){
		window.showModalDialog("KF_QueryState.htm","newwindow","dialogWidth=560px;dialogHeight=240px;center=yes;scroll=no;status=no;resizable=no;help=no;location=no");
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
<body style="overflow: scroll;">
		<table width="100%" style="margin: 0px;" cellspacing="0"
			cellpadding="0">
			<tr>
				<td>
					<input type="image" src="images/find.gif"
						onmouseover="changeImage(this,'find2.gif')"
						onmouseout="changeImage(this,'find.gif')" onclick="find()" />
					<input type="image" src="images/print.gif"
						onmouseover="changeImage(this,'print2.gif')"
						onmouseout="changeImage(this,'print.gif')"
						onclick="alert('打印中...')" />	
						</td>
						<td>					
					<div id="location"
						style="margin-right: 2px; width: 280px; display: inline-block; float: right; margin-top: 5px; color: blue; text-align: right;">
						<font style="font-size: 12px;"><b>当前位置：</b>库房管理&nbsp;&gt;&gt;&nbsp;&nbsp;位置与状态查询</font>
					</div>
				</td>
			</tr>
		</table>
			
		<table width="100%" style="margin: 0px;" cellspacing="0"  cellpadding="0">
			<tr>
				<td align="center">
					<div id="findDiv"
						style="display:  <s:if test="#request.recordSize>0">none;</s:if><s:else>block;</s:else> width: 100%; margin-top: 10px;">
						<form action="<%=basePath%>KFGL/storeroomManageAction_findLocAndStatus.action" style="margin: 0;padding: 0">
							<table class="findTB">
								<tr>
									<td>
										条&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码
									</td>
									<td align="left">
										<input type="text" name="archivesBarcode" id="archivesBarcode"
											style="width: 200px" value="${archivesBarcode}"/>
									</td>
								</tr>
								<tr>
									<td>
										档&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号
									</td>
									<td align="left">
										<input type="text" name="archivesID" id="archivesID"
											style="width: 200px" value="${archivesID}"/>
									</td>
								</tr>
								<tr>
									<td>
										题&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名
									</td>
									<td align="left">
										<input type="text" name="title" id="title"
											style="width: 200px" value="${title}"/>
									</td>
								</tr>
								<tr style="height: 40px;">
									<td></td>
									<td align="left">
										<input type="submit" value=" 查询 " style="margin-left: 65px;"/>
										<label style="width: 100px;">
											&nbsp;
										</label>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</td>
			</tr>
			</table>
			
			<s:if test="#request.recordSize==0"><center>
				<SPAN style="color: red; ">没有满足条件的结果！</SPAN>
			</center></s:if>
			<table width="100%" style="margin: 0px; display:<s:if test="#request.recordSize>0">block;</s:if><s:else>none;</s:else> " cellspacing="0"  cellpadding="0">
			<tr id="showResult" style="">
				<td>
					<table width="100%" cellspacing="0" cellpadding="0"
						style="margin: 0px;">
						<tr class="bgTitle">
							<td style="height: 25px" class="borderTop">
								<table style="width: 100%; height: 25px" cellspacing="0"
									cellpadding="0">
									<tr>
										<td>
											<label class="tableTitle">
												档案
											</label>
										</td>
										<td align="right" class="text">
											<label style="margin-right: 4px" id="rsInfo">
												共<span style="color:blue; font-weight:bold;">${recordSize}</span>条记录
											</label>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>

					<table id="showTable" width="100%"
						style="margin: 0px; border: #104da6 1px solid; font-size: 12px;"
						cellspacing="1px" cellpadding="0px">
						<thead class="tableHead">
							<tr class="bgTitle">
								<th style="width: 30px;">
									序号
								</th>
								<th>
									条形码
								</th>
								<th>
									档号
								</th>
								<th>
									题名
								</th>								
								<th>
									盒位置
								</th>
								<th>
									盒条码
								</th>
								<th>
									状态
								</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="#request.storeroomArchivesInfos"
								status="rowstatus">
								<s:if test="#rowstatus.odd==true">
									<tr bgcolor="#eef5ff" style="height: 20px;"
										id="<s:property value="NBXH" />" onclick="clickRow(this)">
								</s:if>
								<s:else>
									<tr bgcolor="#e0edff" style="height: 20px;"
										id="<s:property value="NBXH" />" onclick="clickRow(this)">
								</s:else>
								<td align="center">
									<s:property value="#rowstatus.index+1" />
								</td>
								<td>
									<s:property value="archivesBarcode"/>
								</td>
								<td>
									<s:property value="archivesID" />
								</td>
								<td>
									<s:property value="title" />
								</td>								
								<td>
									<s:if test="tag==null">
									未上架
									</s:if>
									<s:property value="tag" />
								</td>
								<td>
									<s:property value="archivesBoxBarcode" />
								</td>
								<td>
									<s:property value="storeStatus" />
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