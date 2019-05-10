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
    
    <title>文档中心</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/common.css"/>
    <script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.form.js"></script>
    <script type="text/javascript">
      //打开新增对话框
		function addTemplate()
		{
			window.showModalDialog("officialTemplateAction_addTemplate.action",self,"dialogWidth=540px;dialogHeight:150px;center=yes;help=no;resizable=no;status=no;scroll=no;");
		}
		//单击批量删除
		function delTemplate()
		{
			
				if (confirm("确认删除？")) {
					$("#delForm").ajaxSubmit( {
						url : "GWGL/officialTemplateAction_deleteOfficialTemplate.action",
						success : function(data) {
							alert(data);
							//document.conditionForm.submit();
						},
						error : function(XMLHttpRequest, textStatus) {
							alert(textStatus + "," + XMLHttpRequest.status);
						}
					});
				}
		}
		//改变图片背景颜色
		function changeImage(obj,img) {
			if (obj) {
				obj.src="images/"+img;
			}
		}
		
		$(document).ready(function(){
			    $("#del").bind("click",function(){
			       $('#delForm').ajaxSubmit({
			        beforeSubmit:function(){
			        
			        }, 
			        success:function(data){
			           alert(data);
			           document.conditionForm.submit();
			        },
			        error:function(XMLHttpRequest, textStatus){alert("删除失败！ "+textStatus+","+XMLHttpRequest.status);}
			    });
		    });
		 });

<%--		//处理单击事件--%>
<%--		var rowId="";   //保存上一次点击行“tr”的ID；--%>
<%--		var rowColor="";   //保存上一次点击行的颜色--%>
<%--		function clickRow1(obj)--%>
<%--		{--%>
<%--			if(document.getElementById(rowId)==null){//第一次点击处理--%>
<%--				rowId=obj.id;	//保存被点击行的ID--%>
<%--				rowColor=obj.style.backgroundColor;//保存被点击行的颜色--%>
<%--				obj.style.backgroundColor='#a3c9ff';--%>
<%--			}else{--%>
<%--				document.getElementById(rowId).style.backgroundColor=rowColor;--%>
<%--				//'#e0edff';--%>
<%--				obj.style.backgroundColor='#a3c9ff';--%>
<%--				rowId = obj.id;--%>
<%--			}--%>
<%--			 --%>
<%--		}--%>
<%----%>
<%--		//双击查看著录信息--%>
<%--		function showItem(rowObj) {--%>
<%--		    var obj = new Object();	--%>
<%--		   --%>
<%--		    var NBXHS = new Array(rowObj.id);--%>
<%--		     --%>
<%--			obj.archivesTypeID = $("#archivesTypeID").val();--%>
<%--			obj.operationType="edit";--%>
<%--			obj.fileType = 0;//文件级管理 0为文件 1为案卷 2为添加卷内--%>
<%--			obj.NBXHS = NBXHS;--%>
<%--			--%>
<%--			var returnValue = window.showModalDialog(				--%>
<%--				"<%=basePath%>GWGL/GWGL_templateItem.html",--%>
<%--				obj,--%>
<%--				"dialogWidth=700px;dialogHeight:500px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;resizable=no;");--%>
<%--			if(returnValue==1){--%>
<%--				document.conditionForm.submit();--%>
<%--			}--%>
<%--	    }--%>
	    
    </script>
  </head>

	<body>
		<input type="hidden" name="preSelectRow" id="preSelectRow" />
		<table width="100%" style="margin: 0px;" align="center">
			<tr>
				<td>
					<table width="100%" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td>
								<input type="image" id="imgAdd" src="images/new.gif"
									onclick="addTemplate()" onmouseover="changeImage(this,'new2.gif')"
									onmouseout="changeImage(this,'new.gif')" />
							</td>
							<td>
								<div
									style="margin-right: 2px; width: 200px; float: right; color: blue; font-size: 12px;">
									<font style="font-weight: bold;">当前位置：</font>公文管理&nbsp;>>&nbsp;公文模板
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<fieldset>
						<form
							action="GWGL/officialTemplateAction_findOfficialTemplateByName.action"
							method="post" name="conditionForm" id="conditionForm"
							style="margin: 0; padding: 0;">
							<input type="hidden" name="dataPageInfo.currentPage"
								id="currentPage" value="1">
							<input type="hidden" name="type"
								value="<s:property value="#request.type"/>">
							<table class="findTB">
								<tr style="height: 26px;" align="left">
									<td class="text">
										公文名称
									</td>
									<td>
										<input id="title" name="title" type="text"
											style="width: 150px;" />
									</td>
									<td>
										<input type="submit" class="button" value="查   询"
											style="margin-left: 5px;" />
									</td>
								</tr>
							</table>
						</form>
					</fieldset>
				</td>
			</tr>
			<tr>
				<td align="center" height="25px">
					<table class="tabletop" width="100%">
						<tr>
							<td class="tableTitle">
								公文模板—公告
							</td>
							<td align="right" class="text">
<%--								第${dataPageInfo.currentPage}页 共${dataPageInfo.pageCount}页--%>
<%--								共${dataPageInfo.rowCount}条记录--%>
							</td>
						</tr>
					</table>
					<table id="showTable" cellpadding="0px" cellspacing="1px">
						<thead class="tableHead">
							<tr>
								<th style="width: 30px;">
									序号
								</th>
								<th>
									公文名称
								</th>
								<th>
									创建者
								</th>
								<th>
									建立时间
								</th>
								<th>
									备注
								</th>
								<th align="center" style="width: 35px">
									操作
								</th>
							</tr>
						</thead>
						<tbody>
							<form
								action="GWGL/officialTemplateAction_deleteOfficialTemplate.action"
								method="post" name="delForm" id="delForm"
								style="margin: 0; padding: 0;">
								<input type="hidden" name="type" />
								<s:if test="#request.paperTransferBatches == null">
									<tr bgcolor="#eef5ff">
										<td align="center" colspan="5"
											style="color: red; font-size: 12px;">
											${requestScope.message }
										</td>
									</tr>
								</s:if>
								<s:else>
									<tr bgcolor="#eef5ff">
										<td align="center" style="color: red; font-size: 12px;"
											colspan="5">
											没有数据！
										</td>
									</tr>
								</s:else>
							
								<s:iterator var="officialTemplate" status="rowstatus"
									value="#request.officialTemplates">
									<tr bgcolor="#eef5ff" id="row1" status="rowstatus">
										<td>
											<s:property value="#rowstatus.index+1" />
										</td>
										<td>
											<s:property value="title" />
										</td>
										<td>
											<s:property value="provider" />
										</td>
										<td>
											<s:date name="createDate" format="yyyy-MM-dd" />
										</td>
										<td>
											<s:property value="remark"/>
										</td>
										<td align="center" style="width: 35px">
											<a href="GWGL/downloadFile.action?ID=<s:property value="ID"/>" target="_blank">下载</a>
										</td>
									</tr>
								</s:iterator>
							</form>
						</tbody>
					</table>
					<table width="100%" style="font-size: 12px;">
						<tr>
							<td>
								&nbsp;&nbsp;&nbsp;
							</td>
							<td align="right" style="width: 100px; vertical-align: bottom;">
								<s:if test="#request.dataPageInfo.previousState=='enable'">
									<a href="javascript:pageTurning('conditionForm','1')"
										style="text-decoration: none;"> <image
											src="images/firsts.gif"
											onmouseover="changeImage(this,'firsts1.gif')"
											onmouseout="changeImage(this,'firsts.gif')" alt="第一页" /> </a>
									<a
										href="javascript:pageTurning('conditionForm',${dataPageInfo.currentPage-1})"
										style="text-decoration: none;"> <image
											src="images/previouss.gif"
											onmouseover="changeImage(this,'previouss1.gif')"
											onmouseout="changeImage(this,'previouss.gif')" alt="上一页" /> </a>
								</s:if>
								<s:elseif test="#request.dataPageInfo.previousState =='disable'">
									<image src="images/firsts2.gif" alt="已经是第一页" />
									<image src="images/previouss2.gif" alt="已经是上一页" />
								</s:elseif>
								<s:if test="#request.dataPageInfo.nextState=='enable'">
									<a
										href="javascript:pageTurning('conditionForm',${dataPageInfo.currentPage+1})"
										style="text-decoration: none;"> <image
											src="images/nexts.gif"
											onmouseover="changeImage(this,'nexts1.gif')"
											onmouseout="changeImage(this,'nexts.gif')" alt="下一页" /> </a>
									<a
										href="javascript:pageTurning('conditionForm',${dataPageInfo.pageCount})"
										style="text-decoration: none;"> <image
											src="images/lasts.gif"
											onmouseover="changeImage(this,'lasts1.gif')"
											onmouseout="changeImage(this,'lasts.gif')" alt="最后一页" /> </a>
								</s:if>
								<s:if test="#request.dataPageInfo.nextState=='disable'">
									<image src="images/nexts2.gif" alt="已经是最后一页" />
									<image src="images/lasts2.gif" alt="已经是最后一页" />
								</s:if>
							</td>
							<td style="width: 80px; font-size: 12px;">
								转到第
								<input type="text" name="gotoPage"
									style="width: 18px; height: 18px" />
								页
							</td>
							<td style="width: 15px; vertical-align: bottom;">
								<input type="image" src="images/gos.gif"
									onmouseover="changeImage(this,'gos2.gif')"
									onmouseout="changeImage(this,'gos.gif')"
									onclick="gotoPage('conditionForm')" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>
