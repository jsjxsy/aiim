<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>卷内目录</title>
	<script type="text/javascript" src="js/common.js"></script>
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<script type="text/javascript">
	  function showFilesInfo(NBXH,archivesTypeID){
	      alert(NBXH+" "+archivesTypeID);
	  } 
	</script>
</head>
<body>
	<input type="hidden" name="preSelectRow" id="preSelectRow" />
	<table width="100%" style="margin:0px;" align="center">
		<tr>
			<td align="center" height="25px">
				<table class="tabletop" width="100%">
					<tr>
		                <td class="tableTitle">移交批次—所有</td>
		                <td align="right" class="text">第${dataPageInfo.currentPage}页 共${dataPageInfo.pageCount}页 共${dataPageInfo.rowCount}条记录</td>							                	
		            </tr>
				</table>			
				<table id="showTable" width="100%" style="margin:0px; border: #104da6 1px solid;" cellspacing="1px" cellpadding="0px" >						
					<thead class="tableHead">
						<tr>
							<th>序号</th>
							<th>题名</th>								
							<th>责任者</th>
							<th>密级</th>
							<th>保管期限</th>
							<th>形成日期</th>
							<th>页数</th>
							<th align="center">原文</th>
							<th align="center">操作</th>
						</tr>
					</thead>
					<tbody>
						<tr  bgcolor="#e0edff" id="row01" onclick="selectRow(this)">
							<td class="style1" style="height: 20px">001</td>
							<td>测试题名1</td>							
							<td>党办</td>							
							<td>公开</td>
							<td>长期</td>
							<td>2010-03-05</td>
							<td>12</td>
							<td align="center"><a href="javascript:ywgl()">2</a></td>
							<td align="center">
							  <a href="javascript:showFilesInfo(1,1);">查看</a>
							</td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
	</table>			
</body>
</html>
