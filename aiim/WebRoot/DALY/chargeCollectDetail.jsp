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
    
    <title>收费汇总明细信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
	<script type="text/javascript" src="js/common.js"></script>

  </head>
  
  <body>
	<table width="100%" style="margin:0px;" align="center">
		       <tr>
			       <td>
				   </td>
				   <td>
				   	  <div style="margin-right:2px; width:230px; float:right; color:blue; font-size:12px;"><font style="font-weight:bold;">当前位置：</font>出证登记&nbsp;>>&nbsp;收费汇总明细信息</div >					
				   </td>
		       </tr>
	</table>
		
				<table class="tabletop" width="100%">
					<tr>
		                <td class="tableTitle">收费汇总明细</td>
		                <td align="right"  class="text" >
							<label style="margin-right:4px" id="rsInfo"></label>
						</td>							                	
		            </tr>
				</table>	
				<table id="showTable" cellpadding="0px" cellspacing="1px">						
					<thead class="tableHead">
						<tr>
							<th>出证类型</th>								
							<th>分数 </th>
							<th>出证序列号</th>
							<th>学号</th>
							<th>是否加急</th>
							<th>是否出证完成</th>
							<th>证明文件上传日期</th>
							<th>证明文件名称</th>
						</tr>
					</thead>
					<tbody>
						<tr bgcolor="#e0edff" id="row">
							<td><s:property value="#request.archivesCertificateInfo.name"/></td>
							<td><s:property value="#request.archivesCertificateInfo.total"/></td>
							<td><s:property value="#request.archivesCertificateInfo.certificateSN"/></td>
							<td><s:property value="#request.archivesCertificateInfo.xH"/></td>
							<td>
							<s:if test="#request.archivesCertificateInfo.expressFlag">
								是
							</s:if>
							<s:else>
								否
							</s:else>
							</td>
							<td>
							<s:if test="#request.archivesCertificateInfo.dealedFlag">
								是
							</s:if>
							<s:else>
								否
							</s:else>
							</td>
							<td><s:date name="#request.archivesCertificateInfo.fileUploadDate" format="yyyy年MM月dd日"/></td>
							<td><s:property value="#request.archivesCertificateInfo.certificateFileName"/></td>
						</tr>
					</tbody>
				</table>
  </body>
</html>
