<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.orifound.aiim.entity.PaperTransferBatchesArchvTypeDetail"%>
<%@page import="com.orifound.aiim.entity.ArchivesType"%>
<%@page import="com.orifound.aiim.entity.SystemInitializer"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	    <base href="${basePath}">
	    <sx:head/>
		<title>移交清单</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="css/common.css"/>
	 
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/jquery.form.js"></script>
		<script type="text/javascript" src="js/dateTool.js"></script>
		
		<script type="text/javascript">
		window.returnValue = 1;
		//移交清单
		function transferList() {
			if(document.getElementById("archivesTypeID").value == "") {
				alert("请选择分类！");
				return;
			}
			var url = '/aiim/PRINT/catalogPrintAction_switchCatalog.action?batNo=${paperTransferBatch.batNo}&archivesTypeID=' + document.getElementById("archivesTypeID").value + '&type=${requestScope.type}&stateType=' + <%=request.getAttribute("stateType")%> + '&catalogTypeID=11';
			showWinModalDialogScroll(url,'700px','500px');
		}
		
		function setSum(sum){
			document.getElementById("sum").innerHTML = sum;
	    }

	    function changeArchivesTypeIDValue(archivesTypeID){
	    	document.getElementById("archivesTypeID").value = archivesTypeID;
		 }

	  //案卷目录
		function archivesCatalog() {
			if(document.getElementById("archivesTypeID").value == "") {
				alert("请选择分类！");
				return;
			}
			var url = '/aiim/PRINT/catalogPrintAction_printConfig.action?catalogTypeID=1&archivesTypeID=' + document.getElementById("archivesTypeID").value + '&batNo=${paperTransferBatch.batNo}&depaermentName=YWZD';
			showWinModalDialogScroll(url,'700px','500px');
		}
			 
		</script>
	</head>

	<body>
	   <input type="hidden" name="deptType" value="${requestScope.deptType}"/>
	   <input type="hidden" id="archivesTypeID"/>
	   <form action="YJGL/YJJSAction_confirmTransferPaper.action" id="form1">
	     <input type="hidden" value="${paperTransferBatch.batNo}" name="paperTransferBatch.batNo"/>
	     <input type="hidden" value="${requestScope.deptType}" name="deptType"/>
	   </form>
	    <table>
	      <tr>
	        <td>
	         &nbsp;&nbsp;<input type="button" class="button" value="打印移交清单" title="点击打印移交清单！" onClick="transferList()"/>
	         <s:if test="#request.type == 'yj'">
	            &nbsp;&nbsp;<sx:submit cssClass="button" value="确认移交" formId="form1" targets="div1" title="点击以确认移交该批次！" loadingText="正在移交，请稍后........." showLoadingText="true"></sx:submit>
	         </s:if>
	         <s:if test="#request.deptType == 'YWZDS'">
	         	&nbsp;&nbsp;<input type="button" class="button" value="打印案卷归档目录" title="点击打印案卷归档目录！" onClick="archivesCatalog()"/>
	         </s:if>
	        </td>
	        <td><sx:div id="div1" cssStyle="font-size:12px; color:red; float:right; width:200px;"></sx:div></td>
	      </tr>
	    </table>
		<table style="width: 100%;">
			<tr>
				<!--左框（移交类型列表）-->
				<td style="width: 200px; height: 600px; vertical-align: top; border: 1px #104da6 solid;">
				    <table class="tabletop" width="100%">
						<tr>
			                <td class="tableTitle">批次：${paperTransferBatch.batNo}</td>
			            </tr>
					</table>
					<table style="width: 100%;" cellpadding="0px;" cellspacing="1px;">
					<% 
					    String fullName = "";
						Map paperTransferBatchesArchvTypeDetails = (Map)request.getAttribute("paperTransferBatchesArchvTypeDetails");
					    Map archivesTypes = (Map)request.getAttribute("archivesTypes");
					    Integer key = 0;
					    int i=0;
					    for(Object obj : paperTransferBatchesArchvTypeDetails.values()){
					    	key = ((PaperTransferBatchesArchvTypeDetail)obj).getArchivesTypeID();
					    	fullName = SystemInitializer.getInstance().getPlaneArchivesTypes().get(key).getFullName();
					    	if(i == 0){
					    		request.setAttribute("archivesTypeID",key);
					    	}
					    	out.println("<tr style=\"background-color: #e0edff; font-size: 12px; height: 20px;\"  id=\""+key+"\" onclick=\"changeArchivesTypeIDValue('" + key + "')\">");
					    	out.println("<td><a href=\"YJGL/YJJSAction_findArchivesInfosByArchivesTypeAndBatNo.action?batNo="+request.getAttribute("batNo")+"&archivesTypeID="+key+"&type="+request.getAttribute("type")+"&stateType="+request.getAttribute("stateType")+"&deptType="+request.getAttribute("deptType")+"\" target=\"qdRight\">"+((ArchivesType)archivesTypes.get(key)).getFullName()+"(<label id=\"sum"+key+"\">"+((PaperTransferBatchesArchvTypeDetail)obj).getTransferTotal()+"</label>)</td>");
					    	out.println("</tr>");
					    	i++;
					    }
					%> 
					</table>
				</td>
				<!--右框（该类型清单列表）-->
				<td style="height: 400px; vertical-align: top; border: thin #104da6 solid;" id="">
				 <table class="tabletop" width="100%">
					<tr>
						<td class="tableTitle">档案列表—<%=fullName %></td>
						<td align="right" class="text">共<label id="sum"></label>条</td>
					</tr>
				 </table>
			     <iframe src="YJGL/YJJSAction_findArchivesInfosByArchivesTypeAndBatNo.action?batNo=${paperTransferBatch.batNo}&archivesTypeID=${requestScope.archivesTypeID}&type=${requestScope.type}&deptType=${requestScope.deptType}&stateType=${requestScope.stateType}" name="qdRight" width="100%" height="100%" frameborder="0" scrolling="yes">
			      
			     </iframe>
				</td>
			</tr>
		</table>
	</body>
</html>
