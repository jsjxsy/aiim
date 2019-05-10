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
		<title>利用查询</title>
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
							  <font style="font-size: 12px;"><b>当前位置：</b>利用管理&nbsp;&gt;&gt;&nbsp;&nbsp;利用查询</font>
						    </div>
						</td>
				  </tr>
				  </table>
				</td>
			</tr>
			<tr>			
			<s:if test="#request.dataPageInfo.rowCount<1">
			 	 <td align="center" id="find" style="display: block; width: 100%;" >
			  </s:if>
			  <s:else>
			  	<td align="center" style="display: none; width: 100%;"  id="find">
			  </s:else>				
				   <form action="DALY/archivesUseAction_findArchivesInfoUseList.action" id="conditionForm" name="conditionForm" method="post" style="margin: 0;padding: 0;">
				    <input type="hidden" name="dataPageInfo.currentPage" id="currentPage"/>
					<fieldset>
						<table border="0">
							<tr>
								<td class="text">档号</td>
								<td >
									<input name="archivesUseInfoQueryCondition.archivesID" type="text" value="${archivesUseInfoQueryCondition.archivesID}" />
								</td>
							</tr>
							<tr>
								<td class="text">题名</td>
								<td >
									<input name="archivesUseInfoQueryCondition.title" type="text" value="${archivesUseInfoQueryCondition.title}"/>
								</td>
							</tr>
							<tr>
								<td class="text">利用人</td>
								<td >
									<input name="archivesUseInfoQueryCondition.userRealName" type="text" value="${archivesUseInfoQueryCondition.userRealName}"/>
								</td>
							</tr>
							<tr>
								<td class="text">利用人证件号</td>
								<td >
									<input name="archivesUseInfoQueryCondition.iDCardNo" type="text" value="${archivesUseInfoQueryCondition.IDCardNo}"/>
								</td>
							</tr>
							<tr>
								<td class="text">利用人部门</td>
								<td >
									<input name="archivesUseInfoQueryCondition.userDepartment" type="text" value="${archivesUseInfoQueryCondition.userDepartment}" />
								</td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td class="text"><span class="text">利用方式</span></td>
								<td >
								 <s:select name="archivesUseInfoQueryCondition.useWayID" id="archivesUseWays"  list="#{1:'借档',2:'查档'}" theme="simple" cssStyle="width:150px;" listKey="key" listValue="value"  headerKey="0" headerValue="请选择..." />
								<!--  <s:select name="archivesUseInfoQueryCondition.useWayID" cssStyle="width:150px;"   id="archivesUseWays" theme="simple" list="#request.archivesUseWays" headerKey="0" headerValue="请选择..." listKey="iD" listValue="name"></s:select>  
									<select name="archivesUseInfoQueryCondition.useWayID" style="width:150px">
										<option value="0">请选择...</option>
										<option value="1">借档</option>
										<option value="2">查档</option>
									</select>-->
								</td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td class="text">
									<span class="text">利用目的</span>
								</td>
								<td >								 
								  <s:select name="archivesUseInfoQueryCondition.purposeID" cssStyle="width:150px;"  id="archivesUsePurpose" theme="simple" list="#request.archivesUsePurposes" headerKey="0" headerValue="请选择..." listKey="iD" listValue="purpose"></s:select>
								</td>
							</tr>
							<tr>
								<td class="text">利用日期</td>
								<td>
									<input name="archivesUseInfoQueryCondition.useDateBegin" type="text" value="<s:date name='archivesUseInfoQueryCondition.useDateBegin' format='yyyy-MM-dd'/>" id="promise_datestart" />
									<img src="images/dropdownTime.gif" style="CURSOR: pointer;"onclick="PopUpCalendar('promise_datestart',true)" />
								</td>
								<td class="text">到</span></td>
								<td>
									<input name="archivesUseInfoQueryCondition.useDateEnd" value="<s:date name='archivesUseInfoQueryCondition.useDateEnd' format='yyyy-MM-dd'/>" type="text"id="promise_dateend" />
									<img src="images/dropdownTime.gif" style="CURSOR: pointer;"onclick="PopUpCalendar('promise_dateend',true)" />
								</td>
							</tr>
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
								<th width="30px;">序号</th>
								<th>档号</th>
								<th>题名</th>
								<th>利用人</th>
								<th>利用人证件号</th>
								<th>利用人部门</th>
								<th>利用方式</th>
								<th>利用目的</th>
								<th>利用日期	</th>
								<th>归还日期</th>
								<!-- <th>经办人</th> -->
								<th>归还状态</th>
							</tr>
						</thead>
						<tbody>
						<s:iterator value="#request.archivesUseOutInfos" status="status">
								<s:if test="(#status.index+1)%2==0">
									<s:set name="color" value="'#e0edff'" scope="page"></s:set>
								</s:if>
								<s:else>
									<s:set name="color" value="'#eef5ff'" scope="page"></s:set>
								</s:else>
								<tr bgcolor="${pageScope.color}" id="row<s:property value="#status.index+1"/>" onclick="selectRow(this)" >
									<td align="center" height="20px"><s:property value="#status.index+1"/></td>
									<td><s:property value="archivesID"/></td>
									<td><s:property value="title"/></td>
									<td><s:property value="archivesUseRegister.archivesUsePersonInfo.name"/></td>
									<td><s:property value="archivesUseRegister.archivesUsePersonInfo.iDCardNo"/></td>
									<td><s:property value="archivesUseRegister.archivesUsePersonInfo.department"/></td>
									<s:if test="borrowFlag==false">
										<td style="color: red;">查档</td>
									</s:if>
									<s:else>
										<td>借档</td>
									</s:else>
									<td><s:property value="archivesUseRegister.purposeText"/></td>
									<td><s:date name="archivesUseRegister.useDate" format="yyyy-MM-dd"/></td>
									<td><s:date name="returnDate" format="yyyy-MM-dd"/></td>								
									<!-- <td align="center"><a href="javascript:showDJXX('<s:property value="ID"/>')">详细</a></td> -->
									<s:if test="returnDate!=null">
										<td>归还</td>
									</s:if>
									<s:else>
										<td style="color: red;">未归还</td>
									</s:else>
									
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
