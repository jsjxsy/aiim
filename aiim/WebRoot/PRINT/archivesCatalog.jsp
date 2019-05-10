<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.orifound.aiim.entity.ArchivesTypeDataItem"%>
<%@page import="com.orifound.aiim.entity.ArchivesInfo"%>
<%@page import="com.orifound.aiim.entity.FieldValue"%>
<%@page import="com.orifound.aiim.entity.PrintPageSet"%>
<%@page import="com.orifound.aiim.entity.ViewTitle"%>
<%@page import="com.orifound.aiim.web.util.StringTool"%>

<%@ include file="/JXGL/taglib.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

List<ArchivesTypeDataItem> viewDataItems = (List<ArchivesTypeDataItem>)request.getAttribute("viewDataItems");
List<ArchivesInfo> archivesInfos = (List<ArchivesInfo>)request.getAttribute("archivesInfos");
PrintPageSet pageSet = (PrintPageSet)request.getAttribute("printPageSet");
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><%=pageSet.getTitle() %></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/print.css" />
	
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/print.js" charset="GBK"></script>
	
	<script type="text/javascript">
		//js  打印控制
		function printPage() {
			var header = new HeaderFooter('<%= pageSet.getHeader().getViewPosition()%>','<%= pageSet.getHeader().getViewContent()%>');
			var footer = new HeaderFooter('<%= pageSet.getFooter().getViewPosition()%>','<%= pageSet.getFooter().getViewContent()%>');
			
			
			var margin_left = '<%= pageSet.getMargin_left()/pageSet.getMarginUnit()%>';
			var margin_top = '<%= pageSet.getMargin_top()/pageSet.getMarginUnit()%>';
			var margin_right = '<%= pageSet.getMargin_right()/pageSet.getMarginUnit()%>';
			var margin_bottom = '<%= pageSet.getMargin_bottom()/pageSet.getMarginUnit()%>';
			var pageNumber = '<%= pageSet.getPageNumberId()%>';
			var outlineBorder = '<%= pageSet.getOutlineBorderId()%>';
			var pageNumberContent = '<%= pageSet.getPageNumberContent()%>';
			var rowHeight = '<%= pageSet.getRowHeight()%>';
			var titleHeight = '<%= pageSet.getTitleHeight()%>';

			//构建打印控制对象
			var printPageSet=new PrintPageSet(header, footer, margin_left, margin_top, margin_right, margin_bottom,
					rowHeight,titleHeight, pageNumber,pageNumberContent, outlineBorder, 'tablePrint', 'catalogTitle');

			printPageSet.init();
			window.print();
		}

		$(function(){
			//设置行高、栏高
			var rowHeight = '<%= pageSet.getRowHeight()%>';
			var titleHeight = '<%= pageSet.getTitleHeight()%>';
			setTableHeight('tablePrint', rowHeight, 'catalogTitle', titleHeight);
			//设置每页打印高度
			var pageHeight = new Number(800);
			//分页打印设置
			paginationPrint(pageHeight);
		});
	</script>
  </head>
  
<body>
<div class="noprint" align="center">
<input type="button" onclick="printPage()" value="打印">
</div>
<h3 id="catalogTitle" align="center"><%=pageSet.getTitle() %></h3>
<table id="tablePrint" class="tablePrint" style="font-size: 12px;width: 600px;" cellspacing="1px" cellpadding="0px" border="1">
	<thead>
          <tr>
          	<th>序号</th>
			<%
			for(ArchivesTypeDataItem dataItem : viewDataItems){
				  out.print("<th>"+dataItem.getDisplayText()+"</th>");
			}
			%>
	    <tr>
	</thead>
		
	<tbody id="printPage">
   	    <%
   	     ArchivesInfo archivesInfo = null;
   	     if(archivesInfos != null && archivesInfos.size()>=1){
   	        for(int i =0;i<archivesInfos.size();i++){
   	    	    archivesInfo  = (ArchivesInfo)archivesInfos.get(i);
   	    	
				out.print("<tr>");
   	    	    out.print("<td>"+(i+1)+"</td>");
			
   	    	    for(ArchivesTypeDataItem dataItem : viewDataItems){
   	    	    	FieldValue fieldValue = archivesInfo.getRowFieldsValues().get(dataItem.getColumnName());
   	    		   if(fieldValue == null){
   	    			  out.print("<td>&nbsp;</td>");
   	    		   }else{
   	    			   if(StringTool.checkNull(fieldValue.getValue())) {
   	    					out.print("<td "+((ArchivesTypeDataItem)dataItem).getKeyInCol()+">"+fieldValue.getValue()+"</td>");   
   	    			   } else {
   	    				out.print("<td "+((ArchivesTypeDataItem)dataItem).getKeyInCol()+">&nbsp;</td>");
   	    			   }
				   }
			    }
   	    	 	out.print("</tr>");
   	         }
   	      }
   	    %>
	</tbody>
	</table>
</body>
</html>