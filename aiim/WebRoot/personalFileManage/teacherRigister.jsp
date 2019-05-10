<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>教职工登记</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/common.js"></script>
	<link rel="stylesheet" type="text/css" href="css/common.css"/>
	<script type="text/javascript" >
	//单击批量登记按钮
	function inputDat()
	{
		document.getElementById('showBody').style.display='block';
		//document.getElementById('updateBtn').disabled=false;
	}
	</script>
</head>
<body style="overflow: scroll;">
<table width="100%" style="margin:0px; font-size:12px;" cellspacing="0" cellpadding="0">
	<tr>
		<td>
			<input type="image" src="images/print.gif" style="margin-left:3px;" onmouseover="changeImage(this,'print2.gif')" onmouseout="changeImage(this,'print.gif')" onclick="alert('打印中...')"/>
		</td>
		<td>
			<div style="margin-right:2px; display:inline-block; width:300px; color:blue; float:right; margin-top:5px;" align="right">
						<font style="font-size:12px;font-weight:bold;">当前位置：</font>转出管理&nbsp;&gt;&gt;&nbsp;教职工登记</div >
		</td>
	</tr>	
</table>
	
<table width="100%" style="margin:0px; font-size:12px;" cellspacing="0" cellpadding="0">
    <tr>
       <td>
         <fieldset>
           <form action="JZGDAGL/importTeacherInfo.action" method="post" enctype="multipart/form-data">
           <table align="center" style="font-size: 12px;">
	            <tr>
			      <td align="center">
			        <input type="radio" name="importType" value="1" checked/>教职工
			        <input type="radio" name="importType" value="2" checked="checked"/>博士后
			      </td>
			    </tr>
			    <tr>
			      <td>
			        <hr/>
			      </td>
			    </tr>
				<tr >
					<td align="center">
						<span>选择文件&nbsp;&nbsp;</span><input type="file" name="excel">&nbsp;&nbsp;<input type="submit" value="导    入">&nbsp;&nbsp;<%--<input type="button" value="打印条码"> --%>
					</td>
				</tr>
           </table>
           </form>
         </fieldset>
       </td>
    </tr>
	<%--<tr style="height:5px;">
		<td></td>
	</tr>
	<tr>
		<td>
			<table class="tabletop" width="100%">
				<tr>
	                <td class="tableTitle" id="tableTitle">档案&nbsp;&nbsp;<label id="archivesTypeNameText"></label></td>
	                <td align="right" class="text">第${dataPageInfo.currentPage}页 共${dataPageInfo.pageCount}页 共${dataPageInfo.rowCount}条记录</td>							                	
	            </tr>
			</table>			
		</td>			
	</tr>
	<tr>
		<td>
			<table id="showTable" width="100%" style="margin:0px; border: #104da6 1px solid; font-size:12px;" cellspacing="1px" cellpadding="0px" >						
				<thead class="tableHead">
					<tr>
						<th style="width:30px;">序号</th>	
						<s:if test="#request.personType==3">
							<th>工资号</th>
						</s:if>		
						<s:else>
							<th>学号</th>
						</s:else>
						<th>姓名</th>
						<th>姓别</th>
						<th>年龄</th>
						<th>专业</th>
						<th>年度</th>		
					</tr>
				</thead>
				<tbody  id="showBody" >
					<s:iterator value="#request.storeAddressInfos"	status="rowstatus">
						<s:if test="#rowstatus.odd==true">
							<tr bgcolor="#eef5ff" style="height: 20px;" id="<s:property value="NBXH" />" >
						</s:if>
						<s:else>
							<tr bgcolor="#e0edff" style="height: 20px;" id="<s:property value="archivesBoxBarcode" />" >
						</s:else>
						<td align="center"><s:property value="#rowstatus.index+1" /></td>
						<td><s:property value="archivesBoxBarcode" /></td>
						<td><s:property value="storeAddressID" /></td>
						<td><s:property value="storeAddressFullName" /></td>
						<td><s:property value="putTime" /></td>					
						</tr>
					</s:iterator>			
				</tbody>
			</table>
		</td>
	</tr>		
--%></table>
${message }
	</body>
</html>
