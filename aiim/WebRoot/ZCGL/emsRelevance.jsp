<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>EMS转出关联</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script type="text/javascript">
	//关联转递号：弹框，提示输入EMS编号
	function inputID()
	{
		window.showModalDialog("<%=basePath%>ZCGL/inputID.jsp","newwindow","dialogWidth=300px;dialogHeight:30px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");
		
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
  <!-- 
<table width="100%" style="margin:0px; font-size:12px;" cellspacing="0" cellpadding="0">
	<tr>
		<td>
			<input type="image" src="images/print.gif" style="margin-left:3px;" onmouseover="changeImage(this,'print2.gif')" onmouseout="changeImage(this,'print.gif')" onclick="alert('打印中...')"/>
			
			 <input type="button" value="打印快递单" onclick="alert('打印...');">
			  <input type="button" value="转出单关联" onclick="showGL()">
			   
		</td>
		<td>
			<div style="margin-right:2px; display:inline-block; width:300px; color:blue; float:right; margin-top:5px;" align="right">
						<font style="font-size:12px;font-weight:bold;">当前位置：</font>转出管理&nbsp;&gt;&gt;&nbsp;学生登记</div >
		</td>
	</tr>	
</table>
	-->
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
					                    <font style="font-size:13px;font-weight:bold">转出地址</font>
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
						
						<th width="30px">序号</th>
						<th>编号</th>
						<th>转递单位</th>
						<th>详细地址</th>	
						<th>操作</th>	
					</tr>
				</thead>
				<tbody  id="showBody" >
				<tr id="23443" bgcolor="#eef5ff" style="height: 20px;">
					
					<td>1</td>
					<td>324324</td>
					<td>北京AA公司</td>
					<td>北京海淀区板井路...</td>	
					<td><a href=javascript:inputID()>关联</a> </td>
				</tr>
				<tr id="1234" bgcolor="#e0edff" style="height: 20px;">
					
					<td>2</td>
					<td>324324</td>
					<td>北京BB公司</td>
					<td>北京海淀区板井路...</td>	
					<td><a href="javascript:inputID()">关联</a></td>
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

