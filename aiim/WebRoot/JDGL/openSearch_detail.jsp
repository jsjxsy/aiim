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
    
    <title>公开鉴定详情</title>
    
		<link href="css/Styles.css" type="text/css" rel="stylesheet" />
		<link href="css/Login.css" type="text/css" rel="stylesheet" />
		<link href="css/common.css" type="text/css" rel="stylesheet" />
		
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="Cache-Control" content="no-cache,  must-revalidate" />
		<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="js/popup.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript" src="js/jquery.form.js"></script>
		
		<style type="text/css">
		  	.tableHead{font-weight:bold; text-align:center; padding:4px 0 0 0px;}
			.tableTitle {font-weight:bold; text-align:left; padding:4px 0 0 5px;}
			/*设置标题底色*/
			.bgTitle { background-color:#a3c9ff;height:25px;}
			/*设置表格顶部框底色*/
			.borderTop {border-top:#104da6 1px solid;	border-left:#104da6 1px solid;	border-right:#104da6 1px solid;}
			.tableHead{font-weight:bold; text-align:center; padding:4px 0 0 0px;}
			.tbody tr{background-color:#e0edff;}
		</style>

  </head>
	<body class="bg_color" style="margin-top:4px">
        	<div style="width:100%;overflow:auto; height:100%;">
				<table align="center" class="back_border" width="98%" style="height:80px;" cellpadding="0" cellspacing="0">
				    <tr>
				        <td class="bg_title bg_title4" align="center">公开鉴定详细信息</td>
				    </tr>
				    <tr>
				        <td>
				            <table width="100%">
				              	<tr>
						        	<td align="right">档号:</td>
						        	<td>${appraisalPublicDetail.archivesID}</td>
						        </tr>
						        <tr>
						        	<td align="right">题名:</td>
						        	<td>${appraisalPublicDetail.title}</td>
						        </tr>
						        <tr>
						        	<td align="right">鉴定人:</td>
						        	<td>${appraisalPublicDetail.appraisalPersion}</td>
						        </tr>
						        <tr>
						        	<td align="right">公开日期:</td>
						        	<td><fmt:formatDate value="${appraisalPublicDetail.appraisalDate}" type="date"/>
						        	</td>
						        </tr>
						         <tr>
						        	<td align="right">公开依据:</td>
						        	<td><textarea id="content" readonly="readonly" name="content" rows="5" style="width:90%;">${appraisalPublicDetail.appraisalReason}</textarea></td>
						        </tr>
						      </table>
				        </td>
				    </tr>
				</table>
		</div>
	</body>
</html>