<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/JXGL/taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>移交清单打印</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/print.css" />
	
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/print.js"></script>
	
	<script type="text/javascript">

		//分页打印控制
		function paginationPrint(pageHeight) {
			pageHeight = pageHeight-40;
			//记录表格目前的高度
			var tableHeight = new Number(0);
			//循环tbody 获取表格的总宽度
			$('#printPage > tr').each(function(index){
				tableHeight = tableHeight + $(this).attr('offsetHeight');
<%--				alert('tableHeight='+tableHeight);--%>
				//控制每个表格每页高度、其中第一页高度小50(去除标题高度)
				if((tableHeight >= pageHeight && index>=1) || (index==0 && tableHeight>=(pageHeight-50))) {
					//设置当列以下换页
<%--					alert('tr='+$(this).attr('id'));--%>
					$(this).addClass('pageNext');
					//设置下一列加top边框
					$(this).addClass('trBorder');
					
					//清空表格目前的高度
					tableHeight = 0;
				}
			});
		}

		//设置打印页面分页
		$(function(){
			//设置每页打印高度
			var pageHeight = new Number(800);
			//分页打印设置
			paginationPrint(pageHeight);
		});

	</script>
	
  </head>
  
<body>
<div class="noprint" align="center">
<input type="button" onclick="printPage();" value="打印">
</div>

<table class="tablePrint" style="font-size: 12px;width: 600px;" cellspacing="1px" cellpadding="0px" border="1">
			<thead>
			    <tr>
			        <th>序号</th>
					<th>档号</th>
					<th>题名</th>
					<th>密级</th>
					<th>保管期限</th>
					<th>形成日期</th>
				</tr>
			</thead>
			<tbody id="printPage">
			 <s:iterator value="#request.paperTransferBatchesDetails" status="status">
				<tr id="row<s:property value="#status.count"/>">
				    <td style="height: 20px" align="center">
						<s:property value="#status.count"/>
					</td>
					<td style="height: 20px" align="center">
						<s:if test="archivesID == null">&nbsp;</s:if>
						<s:else><s:property value="archivesID"/></s:else>
					</td>
					<td class="archivesinfoTitle">
						<s:property value="title"/>
					</td>
					<td><s:property value="secrecyText"/></td>
					<td><s:property value="retentionPeriodText"/></td>
					<td><s:property value="formationYear"/></td>
				</tr>
			 </s:iterator>
            </tbody>
		</table>
</body>
</html>