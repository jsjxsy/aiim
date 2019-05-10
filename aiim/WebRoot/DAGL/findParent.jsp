<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.orifound.aiim.entity.ArchivesInfo"%>
<%@page import="com.orifound.aiim.entity.FieldValue"%>
<%@page import="com.orifound.aiim.entity.ArchivesTypeDataItem"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ucl" uri="/myTag"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	 Map dataItems = (Map)request.getAttribute("dataItems");
	 List archivesInfos = (List)request.getAttribute("archivesInfos");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>" target="_self">
		<title>插卷</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

        <link rel="stylesheet" type="text/css" href="css/common.css">
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="js/jquery.form.js"></script>
		
		<script type="text/javascript" src="dwr/interface/ArchivesInfoManageDWR.js"></script>
	    <script type="text/javascript" src="dwr/engine.js"></script>		
		<script type="text/javascript">

		// 打开日期输入页面
		function PopUpCalendar(ctrlobj,type)
		{
			var url;
			var obj=document.getElementById(ctrlobj);
			if (obj==null) {
				return;
			}
			var obj1=obj;
			showx=obj1.offsetLeft+window.screenLeft;
			showy=obj1.offsetTop+window.screenTop+20;
			while (obj1=obj1.offsetParent) {
				showx+=obj1.offsetLeft;
				showy+=obj1.offsetTop;
			}
			if (type==true) {
				url="<%=basePath%>js/CalendarWithFormat.html";
			}
			else {
				url="<%=basePath%>js/CalendarWithOutFormat.html"
			}
			var retval = window.showModalDialog(url,"", "dialogWidth:197px; dialogHeight:210px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no;help=no; "  );
			if( retval != null ){
				obj.value = retval;
			}
		}

		 $(document).ready(function(){
			 <%
		       if(archivesInfos == null){
		    	   out.print("$(\"#find\").css(\"display\",\"block\");");
		    	   out.print("$(\"#find\").attr(\"alt\",\"隐藏查询(Q)\");");
		       }else if(archivesInfos.size() == 0){
		    	   out.print("$(\"#find\").css(\"display\",\"block\");");
		    	   out.print("$(\"#find\").attr(\"alt\",\"隐藏查询(Q)\");");
		       }else{
		    	   out.print("$(\"#find\").attr(\"alt\",\"显示查询(Q)\");");
		    	   out.print("$(\"#find\").css(\"display\",\"none\");");
		       }
		   %>
		   getArchivesTypeByID();
		 });
		
		// 获得档案类型对象
		function getArchivesTypeByID(){
		    ArchivesInfoManageDWR.getArchivesTypeByID($("#archivesTypeID").val(),getArchivesTypeByIDCallBack);
		}
		function getArchivesTypeByIDCallBack(data){
		    $("#archivesTypeNameText").text(data.fullName+"("+data.fullCode+")");
		}

		function chose(parentNBXH){
			window.returnValue = parentNBXH;
			window.close();
		}
	</script>

	</head>
   
	<body>
	<input type="hidden" id="preSelectRow" name="preSelectRow"/>
	     <table  cellpadding="0" cellspacing="0" align="center" style="display: block; padding: 0; margin: 0; width: 99%">			
			<tr>
				<td>
				   <input type="image" src="images/find.gif" onmouseover="changeImage(this,'find2.gif')" onmouseout="changeImage(this,'find.gif')" onclick="showfind(this)" alt="显示查询(Q)" style="margin-top: 3px;"/>	
					<form name="conditionForm" action="DAGL/archivesInfoManageAction_findArchivesInfosByConditionForChaJuan.action" method="post" style="margin: 0;padding: 0;">
					<%--<input type="hidden" name="fileType" id="fileType" value="1"><!-- 文件类型0：文件 1：案卷 2：卷内 -->--%>
					<input type="hidden" name="dataPageInfo.currentPage" id="currentPage" value="1"><!-- 当前页码 -->
					<input type="hidden" id="archivesTypeID" name="archivesTypeID" value="${requestScope.archivesTypeID }">
					  <fieldset id="find" style="display: block;">		
						<table class="findTB" style="font-size: 12px; display: block;" align="center">
						    <%=request.getAttribute("htmlCode") %>
						    <tr style="height: 40px;">
								<td></td>
								<td align="left">
									<input type="submit" value="" class="submitButton" onmouseover="changeSubmitBgImage(this,'search2.gif')" onmouseout="changeSubmitBgImage(this,'search.gif')"/>
								</td>
							</tr>
						</table>
					  </fieldset>
					</form>	
					<table class="tabletop" width="100%">
						<tr>
			                <td class="tableTitle" id="tableTitle">档案&nbsp;&nbsp;<label id="archivesTypeNameText"></label></td>
			                <td align="right" class="text">第${dataPageInfo.currentPage}页 共${dataPageInfo.pageCount}页 共${dataPageInfo.rowCount}条记录</td>							                	
			            </tr>
					</table>
					<table  id="showTable" cellpadding="0px" cellspacing="1px">	
					       <thead class="tableHead">			
								<tr>
									<%--<th width="35px">选择</th>  --%>
									<th style="width: 30px;">序号</th>
									<%
									for(Object dataItem : dataItems.values()){
									   out.print("<th>"+((ArchivesTypeDataItem)dataItem).getDisplayText() +"</th>\n");
									}
									%>
									<th align="center">操作</th>
								</tr>
							</thead>
						   	<tbody>
						   	   <%
						   	     ArchivesInfo archivesInfo = null;
						   	     if(archivesInfos != null){
						   	        for(int i =0;i<archivesInfos.size();i++){
						   	    	    archivesInfo  = (ArchivesInfo)archivesInfos.get(i);
						   	    	
						   	    	    if(i%2 == 0){    		
						   	    		    out.print("<tr bgcolor=\"#eef5ff\" id=\""+archivesInfo.getNBXH()+"\"  onclick=\"selectRow(this)\">");   	    		
						   	    	    }else{
						   	    		    out.print("<tr bgcolor=\"#e0edff\"  id=\""+archivesInfo.getNBXH()+"\" onclick=\"selectRow(this)\">");
						   	    	    }

						   	    	    //out.print("<td align=\"center\"><input type=\"checkbox\" name=\"NBXHS\" value=\""+archivesInfo.getNBXH() +"\" onclick=\"oneSelect(this)\"></td>");
						   	    	    out.print("<td hight=\"20px\">"+(i+1)+"</td>");
								
						   	    	    for(Object dataItem : dataItems.values()){
						   	    		   if(archivesInfo.getRowFieldsValues().get(((ArchivesTypeDataItem)dataItem).getColumnName()).getValue() == null){
						   	    			  out.print("<td></td>");
						   	    		   }else{
										      out.print("<td>"+archivesInfo.getRowFieldsValues().get(((ArchivesTypeDataItem)dataItem).getColumnName()).getValue() +"</td>");
										   }
									    }
						   	    	    out.print("<td align=\"center\"><a onclick=\"javascript:chose("+archivesInfo.getNBXH()+")\" href=\"javascript:function(){return false;}\">选择</a></td></tr>\n");
						   	         }
						   	      }
						   	    %>
						   	</tbody>	
					</table>

					<table width="100%" style="font-size: 12px;">
						 <tr>
						    <td>
						      
							</td>
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
