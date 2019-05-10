<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>统计报表结果--公文登记情况</title>
<link rel="stylesheet" type="text/css" href="css/common.css">
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
function print()
{
	window.showModalDialog(
			"printReportResultOfficialArchivesInput.action",
			 window,
			"dialogWidth=700px;dialogHeight:400px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:1;location:yes; resizable=no;location=n");
}
</script>
<style type="text/css">
/*设置标题底色*/
.bgTitle 
{ 
background-color:#a3c9ff;
height:25px;
}
/*设置表格顶部框底色*/
.borderTop {	
border-top:#104da6 1px solid;	
border-left:#104da6 1px solid;	
border-right:#104da6 1px solid;
}
body 
{
	height:100%;
	color: #000000; 
	font-size:12px;
	margin:0; 
	background-color:White;
}
/*表头*/
.tableTitle {
font-weight:bold; 
text-align:left; 
padding:4px 0 0 5px;}
.text{ font-size:9pt;}
.tableHead{
	font-weight:bold; 
	text-align:center; 
	padding:4px 0 0 0px;
}
/*设置被选行的颜色*/
.selectRowColor{
	background-color:#a4caef;
}
</style>
</head>
<body>
		<table id="userInfo" cellpadding="0" cellspacing="0"
			style="display: block; padding: 0; margin: 0; width: 100%">
			<tr>
				<td>
					<table width="100%" style="margin: 0px;" cellspacing="0"
						cellpadding="0">
						<tr>
							<td>
								<input type="image" src="images/printing.gif" 
								onmouseover="changeImage(this,'printing2.gif')" 
								onmouseout="changeImage(this,'printing.gif')" 
								onclick="print()" />
								<div style="margin-right: 2px; display: block; width: 280px; float: right; margin-top: 5px; color: blue; text-align: right;">
									<font style="font-size: 12px; font-weight: bold;">当前位置：</font>统计报表结果&nbsp;&gt;&gt;&nbsp;&nbsp;公文登记情况
								</div>
							</td>
						</tr>
					</table>
					

					<table width="100%" cellspacing="0" cellpadding="0" style="margin:0px;">
						<tr class="bgTitle">
							<td style="height:25px" class="borderTop">
								<table style="width:100%;height:25px;" cellspacing="0" cellpadding="0">
							            <tr>
						                  <td>
					                        <label class="tableTitle"></label>
					                	    <div align="center">
					                	      <label style="margin-right:4px;font-weight: bold;font-size:<s:property value="#request.reportPrintSettings[0].titleFontSize"/>px;" id="rsInfo">
					                	      <s:property value="#request.reportResultOfficialArchivesInputs[0].reportTitle"/></label>
				                	        </div></td>
					                    </tr>
							    </table>
							</td>
						</tr>
					</table>				
			
		
					<table id="showTable" width="100%" style="margin:0px; border: #104da6 1px solid;" cellspacing="1px" cellpadding="0px" >						
						<thead class="tableHead">
							<tr class="bgTitle">
								<th width="33.3%">部门名称</th>								
								<th width="33.3%">公文档案分类名称 </th>
								<th width="*%">总件数</th>
							</tr>
						</thead>
						<tbody>
						<s:iterator var="reportResultOfficialArchivesInput" value="#request.reportResultOfficialArchivesInputs">
							<tr bgcolor="#e0edff" id="row1" style="font-size: <s:property value="#request.reportPrintSettings[0].tableFontSize"/>px;">
								<td height="<s:property value="#request.reportPrintSettings[0].tableRowHeight"/>">
								<s:property value="departmentName"/></td>
								<td><s:property value="archivesTypeName"/></td>
								<td><s:property value="pieceCount"/></td>
							</tr>		
						</s:iterator>
						</tbody>
				  </table>
<table width="100%">
 	 <tr>
 	 	<td align="right">
 	 <input type="button"  value="Excel导出" name="excel" id="excel" onclick="javascript:window.location.href='excel_exportReportResultOfficialArchivesInputExcel.action'"/>
		</td>
	</tr>
</table>
</body>
</html>
