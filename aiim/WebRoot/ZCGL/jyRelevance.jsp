<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>机要转出关联</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/common.css"/>
	<script type="text/javascript">
	//关联转递号：弹框，提示输入机要编号
	function inputID()
	{
		window.showModalDialog("<%=basePath%>ZCGL/inputID.jsp","newwindow","dialogWidth=300px;dialogHeight:30px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");
	}
	</script>
  </head>  
  <body style="overflow: scroll;">
<table width="100%" style="margin:0px; font-size:12px;" cellspacing="0" cellpadding="0">
	
	<tr style="height:2px;">
		<td></td>
	</tr>
	<tr>
		<td>
			<table width="100%" cellspacing="0" cellpadding="0" style="margin:0px; font-size:12px;">
				<tr class="bgTitle">
					<td style="height:25px" class="tabletop">
						<table style="width:100%;height:25px" cellspacing="0" cellpadding="0">
					            <tr>
					                <td>
					                    <font style="font-size:13px;font-weight:bold">转出地址</font>
					                </td>
					                <td align="right"  class="text" >
					                	<label style="margin-right:4px" id="rsInfo"> 共<span style="color:blue;font-weight:bold; font-size:13px;">${recordCount}</span>条记录</label>
					                </td>							                	
					            </tr>
					    </table>
					</td>
				</tr>
			</table>				
		</td>			
	</tr>
	<tr>
		<td>
			<table id="showTable" width="100%" style="margin:0px; border: #104da6 1px solid; font-size:12px;" cellspacing="1px" cellpadding="0px" >						
				<thead class="tableHead">
					<tr class="bgTitle">						
						<th style="width: 30px;"></th>
						<th>序号</th>
						<th>编号</th>
						<th>转递单位</th>
						<th>详细地址</th>
						<th>转递号</th>	
						<th>操作</th>	
					</tr>
				</thead>
				<tbody  id="showBody" >
				<tr id="23443" bgcolor="#eef5ff" style="height: 20px;">
					<td><input type="checkbox"> </td>
					<td>1</td>
					<td>324324</td>
					<td>北京AA公司</td>
					<td>北京海淀区板井路...</td>	
					<td>132658815</td>
					<td><a href="javascript:inputID()">关联</a> </td>
				</tr>
				<tr id="1234" bgcolor="#e0edff" style="height: 20px;">
					<td><input type="checkbox"> </td>
					<td>2</td>
					<td>324324</td>
					<td>北京BB公司</td>
					<td>北京海淀区板井路...</td>	
					<td></td>
					<td><a href="javascript:inputID()">关联</a></td>
				</tr>
				
				
				
				<s:iterator value="#request.storeAddressInfos"	status="rowstatus">
					<s:if test="#rowstatus.odd==true">
						<tr bgcolor="#eef5ff" style="height: 20px;"
							id="<s:property value="NBXH" />" >
					</s:if>
					<s:else>
						<tr bgcolor="#e0edff" style="height: 20px;"
							id="<s:property value="archivesBoxBarcode" />" >
					</s:else>
					<td align="center">
						<s:property value="#rowstatus.index+1" />
					</td>
					<td>
						<s:property value="archivesBoxBarcode" />
					</td>
					<td>
						<s:property value="storeAddressID" />
					</td>
					<td>
						<s:property value="storeAddressFullName" />
					</td>
					<td>
						<s:property value="putTime" />
					</td>					
					
					</tr>
				</s:iterator>			
				
				</tbody>
			</table>
		</td>
	</tr>		
</table>
	</body>
</html>
