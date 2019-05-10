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
    
    <title>学生档案著录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="css/common.css">
	
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" >
	function showAdd(nbxh){
		//window.location.href = "/aiim/personalFileManage/dlg_AddDoc.jsp?nbxh="+nbxh;
		//window.location.href = "/aiim/XSDAGL/studentFileManageAction_finDoc.action?nbxh="+nbxh;
		var returnValue = window.showModalDialog(				
			"/aiim/XSDAGL/studentFileManageAction_finDoc.action?nbxh="+nbxh,
			window,
			"dialogWidth=500px;dialogHeight:400px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;resizable=no;");
		if(returnValue==1){
			document.conditionForm.submit();
		}
    }
	</script>
</head>
<body style="overflow: scroll;">
<input id="preSelectRow" name="preSelectRow" type="hidden">
<table width="100%" style="margin:0px; font-size:12px;" cellspacing="0" cellpadding="0">
	<tr>
		<%--<td>
			<input type="image" src="images/print.gif" style="margin-left:3px;" onmouseover="changeImage(this,'print2.gif')" onmouseout="changeImage(this,'print.gif')" onclick="alert('打印中...')"/>
		</td>
		--%><td>
			<div style="margin-right:2px; display:inline-block; width:300px; color:blue; float:right; margin-top:5px;" align="right">
						<font style="font-size:12px;font-weight:bold;">当前位置：</font>学生档案管理&nbsp;&gt;&gt;&nbsp;学生档案著录</div >
		</td>
	</tr>	
</table>
<form name="conditionForm" action="XSDAGL/studentFileManageAction_findStudentRigister.action" method="post" style="margin: 0;padding: 0;">	
	<input type="hidden" name="dataPageInfo.currentPage" id="currentPage" value="1"><!-- 当前页码 -->
	<input type="hidden" id="xyName" name="xyName" value="${requestScope.xyName }">
</form>	
<table width="100%" style="margin:0px; font-size:12px;" cellspacing="0" cellpadding="0">
	<tr>
		<td>
			<table class="tabletop" width="100%">
				<tr>
	                <td class="tableTitle" id="tableTitle">档案&nbsp;&nbsp;<label id="archivesTypeNameText">${requestScope.xyName }</label></td>
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
						<th>学号</th>
						<th>姓名</th>
						<th>姓别</th>
						<th>专业</th>
						<th>班级</th>
						<th>入学年度</th>
						<th>毕业年度</th>	
						<th>操作</th>		
					</tr>
				</thead>
				<tbody  id="showBody" >
					<s:iterator value="#request.studentInfos" status="rowstatus">
						<s:if test="#rowstatus.odd">
							<tr bgcolor="#eef5ff" style="height: 20px;" id="<s:property value="NBXH" />" onclick="selectRow(this)">
						</s:if>
						<s:else>
							<tr bgcolor="#e0edff" style="height: 20px;" id="<s:property value="NBXH" />" onclick="selectRow(this)">
						</s:else>
						<td align="center"><s:property value="#rowstatus.index+1" /></td>
						<td><s:property value="studentId" /></td>
						<td><s:property value="studentName" /></td>
						<td><s:property value="sex" /></td>
						<td><s:property value="specialty" /></td>
						<td><s:property value="grade" /></td>
						<td><s:property value="startSchoolYear" /></td>	
						<td><s:property value="finishSchoolYear" /></td>
						<td align="center"><a href="javascript:showAdd('<s:property value="NBXH" />')">著录卷内文件</a></td>					
						</tr>
					</s:iterator>			
				</tbody>
			</table>
			<table width="100%" style="font-size: 12px;">
				 <tr>
				    <td></td>
					<td align="right" style=" width: 100px; vertical-align: bottom;">
					   <s:if test="#request.dataPageInfo.previousState=='enable'" >
							<a href="javascript:pageTurning('conditionForm','1')" style="text-decoration: none;">
							   <image src="images/firsts.gif" onmouseover="changeImage(this,'firsts1.gif')" onmouseout="changeImage(this,'firsts.gif')" alt="第一页"/>
							</a>
							<a href="javascript:pageTurning('conditionForm',${dataPageInfo.currentPage-1})" style="text-decoration: none;">	
							   <image src="images/previouss.gif" onmouseover="changeImage(this,'previouss1.gif')" onmouseout="changeImage(this,'previouss.gif')" alt="上一页"/>
							</a>
						</s:if>
						<s:elseif test="#request.dataPageInfo.previousState =='disable'">
						   <image src="images/firsts2.gif" alt="已经是第一页"/>
						   <image src="images/previouss2.gif" alt="已经是上一页"/>
						</s:elseif>
						<s:if test="#request.dataPageInfo.nextState=='enable'">
							<a href="javascript:pageTurning('conditionForm',${dataPageInfo.currentPage+1})" style="text-decoration: none;">
							   <image src="images/nexts.gif" onmouseover="changeImage(this,'nexts1.gif')" onmouseout="changeImage(this,'nexts.gif')" alt="下一页"/>
							</a>
							<a href="javascript:pageTurning('conditionForm',${dataPageInfo.pageCount})" style="text-decoration: none;">
							    <image src="images/lasts.gif" onmouseover="changeImage(this,'lasts1.gif')" onmouseout="changeImage(this,'lasts.gif')" alt="最后一页"/>
						    </a>
					    </s:if>
					    <s:if test="#request.dataPageInfo.nextState=='disable'">
						   <image src="images/nexts2.gif" alt="已经是最后一页"/>
						   <image src="images/lasts2.gif" alt="已经是最后一页"/>
					    </s:if>
					</td>
					<td style="width: 70px;font-size: 12px;">	
						转到第<input type="text" name="gotoPage" style="width:18px; height:18px"/>页
					</td>
					<td style="width: 15px; vertical-align: bottom;">
						<input type="image" src="images/gos.gif" onmouseover="changeImage(this,'gos2.gif')" onmouseout="changeImage(this,'gos.gif')" onclick="gotoPage('conditionForm')"/>                                           
					</td>
				</tr>
			</table>		
		</td>
	</tr>		
</table>
	</body>
</html>
