<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/JXGL/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta http-equiv="content-type" content="text/html; charset=UTF-8"></meta>
    <base href="<%=basePath%>" target="_self">
    
    <title>目录打印设置</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/Styles.css" type="text/css" rel="stylesheet" />
	<link href="css/common.css" type="text/css" rel="stylesheet" />

	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/jquery.form.js"></script>
	<script type="text/javascript" src="js/dateTool.js"></script>
	<STYLE type=text/css>
		.dotline {BORDER-BOTTOM-STYLE: dotted; BORDER-LEFT-STYLE: dotted; BORDER-RIGHT-STYLE: dotted; BORDER-TOP-STYLE: dotted}
		
		.inputStyle {width: 10px;}
	</STYLE>
  </head>
  
  <script type="text/javascript">
  	//页面顶部、底部显示设置
  	function topBottomView(flag) {
  	  	if(flag == 'top') {
  	  	  	var topViewFlag = $('#topViewFlag');
  	  		$('#topViewName').attr('disabled',!topViewFlag.attr('checked'));
  	  		$('#topViewPlace').attr('disabled',!topViewFlag.attr('checked'));
	  	}

  	  	if(flag == 'bottom') {
  	  		var bottomViewFlag = $('#bottomViewFlag');
  	  		$('#bottomViewName').attr('disabled',!bottomViewFlag.attr('checked'));
	  		$('#bottomViewPlace').attr('disabled',!bottomViewFlag.attr('checked'));
	  	}
  	}

  	
  </script>
<body>
<s:form action="catalogPrintAction_switchCatalog" namespace="/PRINT">
	<!-- 档案类型id -->
	<s:hidden id="archivesTypeID" name="archivesTypeID" />
	<!-- 目录打印模板id -->
	<s:hidden id="catalogTypeID" name="catalogTypeID" />
	<!-- 批次号 -->
	<s:hidden id="batNo" name="batNo" />
	<!-- 盒条码 -->
	<s:hidden id="boxBarcode" name="boxBarcode" />
	<!-- 公文类型id -->
	<s:hidden id="officialArchivesTypeID" name="officialArchivesTypeID" />
	<!-- 是否归档 -->
	<s:hidden id="isArchived" name="isArchived" />
	<!-- 内部序号 -->
	<s:hidden id="NBXH" name="NBXH" />
	<!-- 打印案卷目录 部门名称 -->
	<s:hidden id="NBXH" name="depaermentName" />
	
    <h2 align="center">${catalogType.name}打印</h2>
	<hr class=dotline color=#000000 size=1>
	<table>
		 <tr>
			<td width="300px" valign="top">
				<fieldset>
					<legend>自定义打印数据项</legend>
						<table cellpadding="0" cellspacing="0" style="width:100%;font-size: 12px;">
							<s:iterator value="catalogDataItems" status="stau">
								<tr><td>
									<input type="checkbox" id="item<s:property value="#stau.count"/>" name="columnName" value="<s:property value="value.columnName"/>" checked="checked">
									<label for="item<s:property value="#stau.count"/>" <s:if test="value.displayLength>=1">style="width:<s:property value="value.displayLength"/>px;"</s:if>><s:property value="value.displayText"/></label> 
								</td></tr>
							</s:iterator>
						</table>
				</fieldset>
			</td>
			<td width="450px" valign="top">
				<fieldset>
					<legend>打印设置选项</legend>
						<table cellpadding="0" cellspacing="0" style="width:100%;font-size: 12px;" border="0">
							<tr><td>
								<s:checkbox id="topViewFlag" name="printPageSet.header.viewFlag" onclick="topBottomView('top')" />
								<label for="topViewFlag">在页面顶部显示文字</label>
							</td></tr>
							<tr><td>
								显示内容:<s:textfield id="topViewName" name="printPageSet.header.viewContent" value="东北大学" cssStyle="width:190px;" disabled="true"/>
							</td></tr>
							<tr><td>
								显示位置:<s:select id="topViewPlace" list="printPageSet.header.viewPlace" name="printPageSet.header.viewPosition" 
													listKey="key" listValue="value" disabled="true"/>
							</td></tr>
							<tr><td>
								<s:checkbox id="bottomViewFlag" name="printPageSet.footer.viewFlag" onclick="topBottomView('bottom')"/>
								<label for="bottomViewFlag">在页面底部显示文字</label>
							</td></tr>
							<tr><td>
								显示内容:<s:textfield id="bottomViewName" name="printPageSet.footer.viewContent" value="东北大学" cssStyle="width:190px;" disabled="true"/>
							</td></tr>
							<tr><td>
								显示位置:<s:select id="bottomViewPlace" list="printPageSet.footer.viewPlace" name="printPageSet.footer.viewPosition" 	
													listKey="key" listValue="value" disabled="true" theme="simple"/>
							</td></tr>
							<tr><td>
								显示标题:<input type="text" id="title" name="printPageSet.title" value='${catalogType.name}' style="width: 190px"/>
							</td></tr>
							<tr><td>
								页码设置:<s:select id="portraitId" list="printPageSet.pageNumber" 
													name="printPageSet.pageNumberId" listKey="key" listValue="value" theme="simple"/>
							</td></tr>
							<tr><td>
								选择行高:<s:textfield id="rowHeight" name="printPageSet.rowHeight" cssStyle="width:40px;" theme="simple"/>像素&nbsp;&nbsp;
								标题栏高:<s:textfield id="titleHeight" name="printPageSet.titleHeight" cssStyle="width:40px;" theme="simple"/>像素
							</td></tr>
							<tr><td>
								左边距&nbsp;&nbsp;&nbsp;:<s:textfield id="margin_left" name="printPageSet.margin_left" cssStyle="width:40px;" theme="simple"/>毫米&nbsp;&nbsp;
								右边距&nbsp;&nbsp;&nbsp;:<s:textfield id="margin_right" name="printPageSet.margin_right" cssStyle="width:40px;" theme="simple"/>毫米
							</td></tr>
							<tr><td>
								上边距&nbsp;&nbsp;&nbsp;:<s:textfield id="margin_top" name="printPageSet.margin_top" cssStyle="width:40px;" theme="simple"/>毫米&nbsp;&nbsp;
								下边距&nbsp;&nbsp;&nbsp;:<s:textfield id="titleHeight" name="printPageSet.margin_bottom" cssStyle="width:40px;" theme="simple"/>毫米
							</td></tr>
							<tr><td>
								纸张大小方向:(打印前，请先在浏览器(IE)的页面设置(菜单:文件->页面设置)进行纸张及打印纸走向。)
							</td></tr>
							<tr height="36px" align="center"><td>
								<input type="submit" value="打印预览">
							</td></tr>
						</table>
				</fieldset>
			</td>
		</tr>
	</table>
</s:form>
</body>
</html>
