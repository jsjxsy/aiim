<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.orifound.aiim.entity.ArchivesTypeDataItem"%>
<%@page import="com.orifound.aiim.entity.OfficialArchivesInfo"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Map dataItems = (Map)request.getAttribute("dataItems");
List officialArchivesInfos = (List)request.getAttribute("officialArchivesInfos");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>公文</title>
    
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
    //打开日期输入页面
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
			var retval = window.showModalDialog(url, "", "dialogWidth:197px; dialogHeight:210px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no;help=no; "  );
			if( retval != null ){
				obj.value = retval;
			}
		 }
		//打开原文管理页面
		function ywgl(nbxh) {
			var officialArchivesTypeID = $("#officialArchivesTypeID").val();
			window.showModalDialog(
							"<%=basePath%>GWGL/ywgl2.jsp?officialArchivesTypeID="+officialArchivesTypeID+"&NBXH="+nbxh,
							window,
							"dialogWidth:590px; dialogHeight:430px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");
		}
    </script>
  </head>
  
  <body>
  <input type="hidden" name="preSelectRow" id="preSelectRow" />
			<table width="100%" style="margin: 0px;" align="center">
			<tr>
			  <td>
			  <input type="image" src="images/find.gif"
						onmouseover="changeImage(this,'find2.gif')"
						onmouseout="changeImage(this,'find.gif')" onclick="showfind(this)" alt="显示查询(Q)"/>	
				</td>
				<td>
					<div
						style="margin-right: 2px; width: 200px; float: right; color: blue; font-size: 12px;">
						<font style="font-weight: bold;">当前位置：</font>电子公文&nbsp;>>&nbsp;公文
					</div>
				</td>
			</tr>
		</table>
		<form action="GWGL/officeDocAction_findOfficialArchivesInfosByCondition.action" method="post"
			name="conditionForm" style="margin: 0px; padding: 0px;">
			
			<fieldset id="find" style="display: none;">
				<table class="findTB" style="font-size: 12px; display: block;"
					align="center">
					<input type="hidden" name="dataPageInfo.currentPage"
						id="currentPage" value="1">
					<!-- 当前页码 -->
					<input type="hidden" id="officialArchivesTypeID" name="officialArchivesTypeID"
						value="${requestScope.officialArchivesTypeID }">
					<input type="hidden" id="FormationDepartmentID" name="FormationDepartmentID"
						value="${requestScope.FormationDepartmentID}">
					${requestScope.htmlCode }
					<tr style="height: 40px;">
						<td></td>
						<td align="left">
							<input type="submit" value="" class="submitButton"
								onmouseover="changeSubmitBgImage(this,'search2.gif')"
								onmouseout="changeSubmitBgImage(this,'search.gif')"
								onclick="javascript:document.getElementById('findDiv').style.display='none';" />
						</td>
					</tr>
				</table>
			</fieldset>
		</form>			
						
					<form  name="djform" method="post" id="djform" style="margin: 0;padding: 0;">
				    <input type="hidden" id="officialArchivesTypeID" name="officialArchivesTypeID" value="${requestScope.officialArchivesTypeID }"/>
					<table  id="showTable" cellpadding="0px" cellspacing="1px">	
					       <thead class="tableHead">			
								<tr>
									<th style="width: 30px;">序号</th>
									
									<%
									for(Object dataItem : dataItems.values()){
									   out.print("<th>"+((ArchivesTypeDataItem)dataItem).getDisplayText() +"</th>");
									}
									%>
									<th align="center" style="width: 70px">原文</th>
								</tr>
							</thead>
											<%
							OfficialArchivesInfo officialArchivesInfo = null;
					   	     if(officialArchivesInfos != null){
					   	        for(int i =0;i<officialArchivesInfos.size();i++){
					   	    	    officialArchivesInfo  = (OfficialArchivesInfo)officialArchivesInfos.get(i);
					   	    	
					   	    	    if(i%2 == 0){    		
					   	    		    out.println("<tr bgcolor=\"#eef5ff\" id=\""+officialArchivesInfo.getNBXH()+"\" >");   	    		
					   	    	    }else{
					   	    		    out.println("<tr bgcolor=\"#e0edff\"  id=\""+officialArchivesInfo.getNBXH()+"\" >");
					   	    	    }
				
					   	    	    out.println("<td >"+(i+1)+"</td>");
							
					   	    	    for(Object dataItem : dataItems.values()){
					   	    		   if(officialArchivesInfo.getRowFieldsValues().get(((ArchivesTypeDataItem)dataItem).getColumnName()).getValue() == null){
					   	    			  out.println("<td></td>");
					   	    		   }else{
									      out.println("<td>"+officialArchivesInfo.getRowFieldsValues().get(((ArchivesTypeDataItem)dataItem).getColumnName()).getValue() +"</td>");
									   }
								    }
					   	    	  out.print("<td><a href=\"javascript:ywgl("+officialArchivesInfo.getNBXH()+")\">查看</a></td></tr>");
						   	       
					   	         }
					   	      }
					   	    %>	
				
					
					
					<table width="100%" style="font-size: 12px;">
						 <tr>
						    <td>
						      &nbsp;&nbsp;&nbsp;
						    </td>
							<td align="right" style="width: 100px; vertical-align: bottom;">
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
