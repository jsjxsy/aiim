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
    
    <title>去向登记</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="css/common.css"/>
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>

	<script type="text/javascript" >
	//处理单击事件
	var rowId="";   //保存上一次点击行“tr”的ID；
	var rowColor="";   //保存上一次点击行的颜色
	function clickRow(obj)
	{
		if(document.getElementById(rowId)==null){//第一次点击处理
			rowId=obj.id;	//保存被点击行的ID
			rowColor=obj.style.backgroundColor;//保存被点击行的颜色
			obj.style.backgroundColor='#a3c9ff';	
		}else{
			document.getElementById(rowId).style.backgroundColor=rowColor;
			//'#e0edff';
			obj.style.backgroundColor='#a3c9ff';
			rowId = obj.id;
		}	 
	}

	//改变图片背景颜色
	function changeImage(obj,img) {
		if (obj) {
			obj.src="images/"+img;
		}
	}
	
	//打开关联页面
	function showGL()
	{//var sResult=prompt("what is your name","");
		window.showModalDialog("<%=basePath%>ZCGL/jyRelevance.jsp","newwindow","dialogWidth=800px;dialogHeight:500px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");
	}
	
	//查看转递到该地址的所有人员列表
	function showPersonList()
	{
		window.showModalDialog("<%=basePath%>ZCGL/personList.jsp","newwindow","dialogWidth=800px;dialogHeight:500px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");
	}
	
	//关联转递号：弹框，提示输入EMS编号
	function inputID(id)
	{
		var returnValue = window.showModalDialog("<%=basePath%>ZCGL/inputID.jsp","newwindow","dialogWidth=300px;dialogHeight:30px;center=yes;help=no;resizable=no;status=no;scroll=no");
		if(returnValue != null){
			$.ajax({
			   type: "post",
			   url: "/aiim/XSDAGL/studentFileManageAction_updateSN.action",
			   data: "SN="+returnValue+"&id="+id,
			   success: function(data){
			     alert("成功！");
			     window.location.reload();
			   }
			}); 
		}
	}
	</script>
</head>
<body style="overflow: scroll;">
<input type="hidden" name="preSelectRow" id="preSelectRow" />
<table width="100%" style="margin:0px; font-size:12px;" cellspacing="0" cellpadding="0">
	<tr>
		<td><%-- 
			<input type="image" src="images/print.gif" style="margin-left:3px;" onmouseover="changeImage(this,'print2.gif')" onmouseout="changeImage(this,'print.gif')" onclick="alert('打印中...')"/>
			 --%>
			 <input type="button" value="打印机要单" onclick="alert('打印...');">
			 <%-- <input type="button" value="转出单关联" onclick="showGL()"> --%>
		</td>
		<td>
			<div style="margin-right:2px; display:inline-block; width:300px; color:blue; float:right; margin-top:5px;" align="right">
						<font style="font-size:12px;font-weight:bold;">当前位置：</font>转出管理&nbsp;&gt;&gt;&nbsp;机要转出登记</div >
		</td>
	</tr>	
</table>
<form name="conditionForm" action="XSDAGL/studentFileManageAction_findMoveOutInfo.action" method="post" style="margin: 0;padding: 0;">
	<input type="hidden" id="archivesTypeID" name="archivesTypeID" value="${archivesTypeID}">	
	<input type="hidden" name="dataPageInfo.currentPage" id="currentPage" value="1"><!-- 当前页码 -->
	<input type="hidden" id="moveOutWay" name="moveOutWay" value="2">
	编号范围:<input type="text" id="minNum" name="minNum" value="${minNum}"/>到<input type="text" id="maxNum" name="maxNum" value="${maxNum}"/>
	<input type="submit" value="查询" class="button"/>
</form>		
<table width="100%" style="margin:0px; font-size:12px;" cellspacing="0" cellpadding="0">
	
	<tr style="height:2px;">
		<td></td>
	</tr>
	<tr>
		<td>
		    <table class="tabletop" width="100%">
				<tr>
	                <td class="tableTitle" id="tableTitle">转出信息&nbsp;&nbsp;<label id="archivesTypeNameText"></label></td>
	                <td align="right" class="text">第${dataPageInfo.currentPage}页 共${dataPageInfo.pageCount}页 共${dataPageInfo.rowCount}条记录</td>						                	
	            </tr>
			</table>
			<table id="showTable" width="100%" style="margin:0px; border: #104da6 1px solid; font-size:12px;" cellspacing="1px" cellpadding="0px" >						
				<thead class="tableHead">
					<tr class="bgTitle" >
						<th style="width:30px;">序号</th>
						<th>编号</th>
						<th>序列号</th>						
						<th>单位名称</th>
						<th>单位所在地</th>
						<th>档案邮寄部门</th>
						<th>档案邮寄地址</th>
						<th>联系电话</th>
						<th>档案数量</th>	
						<th>操作</th>			
					</tr>
				</thead>
				<tbody  id="showBody" >
				<s:iterator value="#request.moveOutInfos"	status="rowstatus">
					<s:if test="#rowstatus.odd==true">
						<tr bgcolor="#eef5ff" style="height: 20px;" id="<s:property value="id" />" title="双击查看详细信息" ondblclick="showDetail(this)" onclick="selectRow(this)">
					</s:if>
					<s:else>
						<tr bgcolor="#e0edff" style="height: 20px;" id="<s:property value="id" />" title="双击查看详细信息" ondblclick="showDetail(this)" onclick="selectRow(this)">
					</s:else>
					<td align="center"><s:property value="#rowstatus.index+1" /></td>
					<td><s:property value="id" /></td>
					<td><s:property value="SN" /></td>
					<td><s:property value="companyName" /></td>
					<td><s:property value="companyAddr" /></td>
					<td><s:property value="mailingCompany" /></td>
					<td><s:property value="mailingAddr" /></td>
					<td><s:property value="phone" /></td>
					<td><s:property value="totalArchives" /></td>
					<td align="center"><a href="javascript:inputID('<s:property value="id"/>')">关联转出单序列号</a></td>					
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
