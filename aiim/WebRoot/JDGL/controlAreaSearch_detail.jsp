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
    
    <title>划控鉴定详情</title>
    
		<link href="css/Styles.css" type="text/css" rel="stylesheet" />
		<link href="css/Login.css" type="text/css" rel="stylesheet" />
		<link href="css/common.css" type="text/css" rel="stylesheet" />
		
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="Cache-Control" content="no-cache,  must-revalidate" />
		<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="js/popup.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript" src="js/jquery.form.js"></script>
		
		<style>  
		.roleList td{border:1px solid #FFFFFF;height:20px;border-width:1px 0 0 1px; }
		.bgTitle1 {background-color:#a3c9ff; 
			 height:23px; 
			 color:#00337d; 
			 border-bottom:#104da6 1px solid; 
			 text-align: center;
 		}
		.bgTitle1 th{border:#FFFFFF 1px solid;border-width:1px 0 0 1px;}
		</style>

  </head>
	<body class="bg_color" style="margin-top:4px">
        	<div style="width:100%;overflow:auto; height:100%;">
				<table align="center" class="back_border" width="98%" style="height:80px;" cellpadding="0" cellspacing="0">
				    <tr>
				        <td class="bg_title bg_title4" align="center">划控鉴定详细信息</td>
				    </tr>
				    <tr>
				        <td>
				            <table width="100%" border="0">
				              	<tr>
						        	<td align="right">档号:</td>
						        	<td>${appraisalUseScopesDetail.archivesID}</td>
						        </tr>
						        <tr>
						        	<td align="right">题名:</td>
						        	<td>${appraisalUseScopesDetail.title}</td>
						        </tr>
						        <tr>
						        	<td align="right">鉴定人:</td>
						        	<td>${appraisalUseScopesDetail.appraisalPersion}</td>
						        </tr>
						        <tr>
						        	<td align="right">鉴定日期:</td>
						        	<td><fmt:formatDate value="${appraisalUseScopesDetail.appraisalDate}" type="date"/></td>
						        </tr>
						         <tr>
						        	<td align="right">划控依据:</td>
						        	<td><textarea id="content" readonly="readonly" name="content" rows="5" style="width:90%;">${appraisalUseScopesDetail.appraisalReason}</textarea></td>
						        </tr>
						        <tr>
						        	<td colspan="2">
						        <table width="355px" style="margin:0px;font-size: 12px;margin-left: 90px" cellspacing="0" cellpadding="0">
									<tr id="tr_title">
										<td>
											<table class="tabletop" width="100%">
												<tr>
									                <td class="tableTitle">划控鉴定授权角色列表</td>
									            </tr>
											</table>
										</td>			
									</tr>
									<tr>
										<td>
							        		<table id="showTable" width="100%" style="margin:0px; border: #104da6 1px solid;font-size: 12px;" cellspacing="0px" cellpadding="0px">
								        		<thead class="tableHead">
													<tr class="bgTitle1">
														<th style="width:20%;">序号</th>
														<th style="width:80%;">角色名称</th>
													</tr>
												</thead>
												<tbody id="roleList" class="roleList">
													 <c:forEach items="${appraisalUseScopesDetail.roleNames}" var="role" varStatus="stau">
											        	 <tr>
												        	<td align="right">${stau.count}</td>
												        	<td>${role}</td>
												        </tr>
											        </c:forEach>
												</tbody>
											</table>
										</td>
									</tr>
								</table>
						        	</td>
						        </tr>
						      </table>
				        </td>
				    </tr>
				</table>
		</div>
	</body>
</html>