<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("basePath",basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <base href="<%=basePath%>">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>查询在线申请单</title>
		<link rel="stylesheet" type="text/css" href="css/common.css">
		<script type="text/javascript" src="js/common.js"></script>
	    <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
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
				url="${basePath}js/CalendarWithFormat.html";
			}
			else {
				url="${basePath}js/CalendarWithOutFormat.html"
			}
			var retval = window.showModalDialog(url, "", "dialogWidth:197px; dialogHeight:210px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no;help=no; "  );
			if( retval != null ){
				obj.value = retval;
			}
		}	

		//打开新增对话框
		function showAdd()
		{
			window.showModalDialog("Item.html","newwindow","dialogWidth=700px;dialogHeight:500px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");
		}
		
		//显示高级查询
		function showADFind(){
		  window.showModalDialog("dlgAdvanceFind.htm","newwindow","dialogWidth=500px;dialogHeight=280px;center=yes;scroll=no;status=no;resizable=no;help=no;location=no");
		}
		
		//显示查询
		function showFind(){
		  document.getElementById('find').style.display='block';
		}
		function showBorrow(){
		  window.showModalDialog("dlgBorrow1.htm","newwindow","dialogWidth=500px;dialogHeight=520px;center=yes;scroll=no;status=no;resizable=no;help=no;location=no");
		}

		//单击查询按钮，提交表单
		function subQuery(){
			document.forms["conditionForm"].submit();		
		}

		//显示在线利用申请单的详细信息
		function showRequestDetail(id){
			alert(id);
			 returnValue = window.showModalDialog("<%=basePath%>DALY/archivesUseAction_showRequestDetailListByRequestID.action?requestID="+id,"newwindow","dialogWidth=1000px;dialogHeight=450px;center=yes;scroll=no;status=no;resizable=no;help=no;location=no");
		}
		
		$(document).ready(function(){
			
		});
</script>

</head>
	<body>
		<input type="hidden" name="preSelectRow" id="preSelectRow" />
		<table width="100%" style="margin: 0px;" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					 <table width="100%" cellpadding="0" cellspacing="0" border="0">
			       <tr>
				       <td>
					   <!--  <input type="image" src="images/DelA.gif" onmouseover="changeImage(this,'DelA2.gif')" onmouseout="changeImage(this,'DelA.gif')" onclick="document.tableForm.submit();"/> -->
							<input type="image" style="margin-left: 2px;" src="images/find.gif" alt="显示查询(Q)" onmouseover="changeImage(this,'find2.gif')" onmouseout="changeImage(this,'find.gif')" onclick="return showfind(this);"/>
						</td>
						<td>
							<div id="location" style="margin-right: 2px; width: 280px; display: inline-block; float: right; margin-top: 0px; color: blue; text-align: right;">
							  <font style="font-size: 12px;"><b>当前位置：</b>利用管理&nbsp;&gt;&gt;&nbsp;&nbsp;查询在线申请单</font>
						    </div>
						</td>
				  </tr>
				  </table>
				</td>
			</tr>
			<tr>			
			<s:if test="#request.dataPageInfo.rowCount>0">
				<td align="center" style="display: none; width: 100%;"  id="find">			 	 
			  </s:if>
			  <s:else>
			  	<td align="center" id="find" style="display: block; width: 100%;" >
			  </s:else>				
				   <form action="DALY/archivesUseAction_findOnlineArchivesUseRequesList.action" id="conditionForm" name="conditionForm" method="post" style="margin: 0;padding: 0;">
				    <input type="hidden" name="dataPageInfo.currentPage" id="currentPage"/>
					<fieldset>
						<table border="0">	
						<!-- 
							<tr>
								<td class="text">申请单编号</td>
								<td >
									<input name="archivesUseRequestQueryCondition.iD" type="text" value="${archivesUseRequestQueryCondition.ID}"/>
								</td>
							</tr>
							 -->
							<tr>
								<td class="text">姓名</td>
								<td >
									<input name="archivesUseRequestQueryCondition.userRealName" type="text" value="${archivesUseRequestQueryCondition.userRealName}"/>
								</td>
							</tr>
							<tr>
								<td class="text">证件号</td>
								<td >
									<input name="archivesUseRequestQueryCondition.iDCardNo" type="text" value="${archivesUseRequestQueryCondition.IDCardNo}"/>
								</td>
							</tr>
							<!-- 
							<tr>
								<td class="text">单位</td>
								<td >
									<input name="archivesUseRequestQueryCondition.userDepartment" type="text" value="${archivesUseRequestQueryCondition.userDepartment}" />
								</td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td class="text">申请时间</td>
								<td>
									<input name="archivesUseRequestQueryCondition.requestTimeBegin" type="text" value="<s:date name='archivesUseRequestQueryCondition.requestTimeBegin' format='yyyy-MM-dd'/>" id="promise_datestart" />
									<img src="images/dropdownTime.gif" style="CURSOR: pointer;"onclick="PopUpCalendar('promise_datestart',true)" />
								</td>
								<td class="text">到</td>
								<td>
									<input name="archivesUseRequestQueryCondition.requestTimeEnd" value="<s:date name='archivesUseRequestQueryCondition.requestTimeEnd' format='yyyy-MM-dd'/>" type="text"id="promise_dateend" />
									<img src="images/dropdownTime.gif" style="CURSOR: pointer;"onclick="PopUpCalendar('promise_dateend',true)" />
								</td>
							</tr>
							 -->
							<tr>
							   <td></td>
							  <td>
							   <img  onclick="subQuery()" id="imgFind" src="images/search.gif" style="border-width: 0px;" />
							  </td>
							  <td></td>
							  <td></td>
							</tr>
						</table>
					</fieldset>
					</form>
				</td>
			</tr>
			<tr>
				<td>
					<table class="tabletop" width="100%">
						<tr>
			                <td class="tableTitle">利用记录</td>
			                <td align="right" class="text">第${dataPageInfo.currentPage}页 共${dataPageInfo.pageCount}页 共${dataPageInfo.rowCount}条记录</td>							                	
			            </tr>
					</table>
					<table id="showTable" width="100%" style="margin: 0px; border: #104da6 1px solid;" cellspacing="1px" cellpadding="0px">
						<thead class="tableHead">
							<tr class="bgTitle">
								<th style="width: 30px;">								
									序号
								</th>
								<th>
									姓名
								</th>
								<th>
									证件号码
								</th>
								<th>
									单位
								</th>
								<th>
									E_mail
								</th>					
								<th>
									申请时间
								</th>	
								<th>
									操作
								</th>			
							</tr>
						</thead>
						<tbody>
						<s:iterator value="#request.archivesUseRequests" status="status">
								<s:if test="(#status.index+1)%2==0">
									<s:set name="color" value="'#e0edff'" scope="page"></s:set>
								</s:if>
								<s:else>
									<s:set name="color" value="'#eef5ff'" scope="page"></s:set>
								</s:else>
								<tr bgcolor="${pageScope.color}" id="row<s:property value="#status.index+1"/>" onclick="selectRow(this)" >
									<td align="center" height="20px"><s:property value="#status.index+1"/></td>
									
									<td><s:property value="userInfo.realName"/></td>
									<td><s:property value="userInfo.iDCardNo"/></td>									
									<td><s:property value="userDepartment"/></td>									
									<td><s:property value="userInfo.email"/></td>
									<td><s:date name="requestTime" format="yyyy-MM-dd"/></td>																	
									<td align="center"><a href="javascript:showRequestDetail('<s:property value="ID"/>')">详细</a></td> 
									
									
								</tr>							
							</s:iterator>							
						</tbody>
					</table>
				</td>
			</tr>
			<tr>
				<td>
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







