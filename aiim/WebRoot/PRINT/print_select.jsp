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
    
    <title>打印目录选择</title>
    
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

<script type="text/javascript">

	function printConfig(catalogTypeID) {
		$('#catalogTypeID').val(catalogTypeID);
		$('form').submit();
		var url = 'PRINT/catalogPrintAction_printConfig.action?catalogTypeID='+catalogTypeID;
	}

	//移交清单
	function transferList(catalogTypeID) {
<%--		$.ajax({--%>
<%--		   type: "POST",--%>
<%--		   url: "PRINT/catalogPrintAction_printConfig.action",--%>
<%--		   data: "batNo=20100810002&archivesTypeID=2&type=yj&stateType=3&deptType=YWZDS",--%>
<%--		   success: function(msg){--%>
<%--		     alert( "Data Saved: " + msg );--%>
<%--		   }--%>
<%--		});--%>

		$('#catalogTypeID').val(catalogTypeID);
		$('form').attr('action','PRINT/catalogPrintAction_switchCatalog.action');
		$('form').submit();
		showWinModalDialogScroll();
	}

	//案卷目录
	function archivesCatalog() {
		var url = 'catalogPrintAction_printConfig.action?catalogTypeID=1&archivesTypeID=2&batNo=20100810002&depaermentName=YWZD';
		showWinModalDialogScroll(url,'700px','500px');
	}

	//盒内目录
	function boxCatalog() {
		var url = 'catalogPrintAction_printConfig.action?catalogTypeID=2&boxBarcode=123&archivesTypeID=2';
		showWinModalDialogScroll(url,'700px','500px');
	}

	//卷内目录
	function fileCatalog() {
		var url = 'catalogPrintAction_printConfig.action?catalogTypeID=3&isArchived=y&NBXH=30&archivesTypeID=2';
		showWinModalDialogScroll(url,'700px','500px');
	}

	//公文目录
	function officialArchives() {
		var url = 'catalogPrintAction_printConfig.action?catalogTypeID=4&officialArchivesTypeID=1&archivesTypeID=2';
		showWinModalDialogScroll(url,'700px','500px');
	}

	//移交清单
	function transferList() {
		var url = 'catalogPrintAction_switchCatalog.action?batNo=20100810002&archivesTypeID=2&type=yj&stateType=3&catalogTypeID=11';
		showWinModalDialogScroll(url,'700px','500px');
	}

	//备考表
	function appendixTablePrint() {
		var url = 'catalogPrintAction_switchCatalog.action?catalogTypeID=5';
		showWinModalDialogScroll(url,'700px','500px');
	}

	//封皮
	function envelopePrint() {
		var url = 'catalogPrintAction_switchCatalog.action?catalogTypeID=5&archivesTypeID=2&NBXH=30';
		showWinModalDialogScroll(url,'700px','500px');
	}
</script>
  </head>
  
  <body>
  <s:form action="catalogPrintAction_printConfig" namespace="/PRINT">
  	<s:hidden name="archivesTypeID" value="2"/>
	<s:hidden id="catalogTypeID" name="catalogTypeID" />
	<s:hidden id="officialArchivesTypeID" name="officialArchivesTypeID" value="1"/>
	
	<s:hidden id="batNo" name="batNo" value="20100810002"/>
	<s:hidden id="stateType" name="stateType" value="3"/>
	<s:hidden id="boxBarcode" name="boxBarcode" value="123"/>
	<!-- 是否归档 -->
	<s:hidden id="isArchived" name="isArchived" value="y"/>
	<!-- 内部序号 -->
	<s:hidden id="NBXH" name="NBXH" value="30"/>
	  <table>
	  	<tr><td><a href="javascript:archivesCatalog();">案卷目录</a></td></tr>
	  	<tr><td><a href="javascript:boxCatalog();">盒内目录</a></td></tr>
	  	<tr><td><a href="javascript:fileCatalog();">卷内目录</a></td></tr>
	  	<tr><td><a href="javascript:officialArchives();">公文目录</a></td></tr>
	  	<tr><td><a href="javascript:transferList();">移交清单</a></td></tr>
	  	<tr><td><a href="javascript:appendixTablePrint();">备考表</a></td></tr>
	  	<tr><td><a href="javascript:envelopePrint();">封皮</a></td></tr>
	  	<tr><td><a href="javascript:transferList(7);">调卷单</a></td></tr>
	  </table>
  </s:form>
  </body>
</html>
