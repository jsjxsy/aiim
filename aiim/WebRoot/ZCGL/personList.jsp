<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>人员列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/common.css"/>

  </head>  
  <body>
  <center>转递单位：北京AA公司</center>
    <table id="showTable" width="100%"
						style="margin: 0px; border: #104da6 1px solid; font-size: 12px;"
						cellspacing="1px" cellpadding="0px">
						<thead class="tableHead">
							<tr class="bgTitle">
								<th style="width: 30px;">
									序号
								</th>								
								<s:if test="#request.personType==3">
									<th>工资号</th>
								</s:if>		
								<s:else>
									<th>学号</th>
								</s:else>
								<th>
									姓名
								</th>
								<th>
									姓别
								</th>
								<th>
									专业
								</th>
								<th>
									年度
								</th>
								
							</tr>
						</thead>
						<tbody>
						<tr bgcolor="#eef5ff" style="height: 20px;"
										id="22" onclick="clickRow(this)" ondblclick="showItem(this)">
								<td style="widtd: 30px;">
									1
								</td>
								<td>
									6404010107
								</td>
								<td>
									陈源
								</td>
								<td>
									男
								</td>
								<td>
									计算机
								</td>
								<td>
									2006
								</td>
								
							</tr>
							
							<tr bgcolor="#e0edff" style="height: 20px;"
										id="23" onclick="clickRow(this)" ondblclick="showItem(this)">
								<td style="widtd: 30px;">
									2
								</td>
								<td>
									6404010107
								</td>
								<td>
									高园
								</td>
								<td>
									女
								</td>
								<td>
									工商管理
								</td>
								<td>
									2006
								</td>								
							</tr>
							
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
									<s:property value="archivesID" />
								</td>
								<td>
									<s:property value="title" />
								</td>
								<td>
									<s:property value="storeStatus" />
								</td>
								<td>
									<s:property value="storeAddressFullName" />
								</td>
								<td>
									<s:property value="archivesBoxBarcode" />
								</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
  </body>
</html>
