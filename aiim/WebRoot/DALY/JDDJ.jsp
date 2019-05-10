<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("basePath",basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
    <base href="<%=basePath%>">
    
    <title>借档登记</title>
    	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" type="text/css" href="css/common.css">
	<script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
    
    <script type="text/javascript" src="dwr/interface/ArchivesUseAction.js"></script>
    <script type="text/javascript" src="dwr/util.js"></script>
    <script type="text/javascript" src="dwr/engine.js"></script>
    
    <script type="text/javascript" >
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
		//弹出借档登记窗口
		function showBorrow(){
		  returnValue = window.showModalDialog("<%=basePath%>DALY/archivesUseAction_findDefaultDataForDJ.action?useType=JD","newwindow","dialogWidth=550px;dialogHeight=450px;center=yes;scroll=no;status=no;resizable=no;help=no;location=no");
		  if(returnValue==1){//如果新增了档案利用登记信息则刷新页面
			  window.document.location.reload();
		  }
		}

		//查看登记信息
		function showDJXX(registerID){
			alert(registerID);
		   window.showModalDialog("<%=basePath%>DALY/archivesUseAction_findArchivesUseRegisterByID.action?useType=JD&registerID="+registerID,"newwindow","dialogWidth=550px;dialogHeight=450px;center=yes;scroll=no;status=no;resizable=no;help=no;location=no");
		}
		

/////// ---DWR---  ///////	
	//添加利用人信息
	function addUserPersonInfo(){
		var archivesUsePersonInfo;
		archivesUsePersonInfo={
				name:'daitao',
				iDCardNo:32,
				iDCardTypeID:3,
				department:'233',
				tel:'343434',
				email:'adf@126.com',
				areaID:1
	     	    	};
		ArchivesUseAction.addArchivesUsePersonInfo(archivesUsePersonInfo,addUserPersonInfoBack);
	}
	//添加利用人信息
	function addUserPersonInfoBack(data){
		alert(data);
	}

	//单击查询按钮，提交表单
	function subQuery(){
		document.forms["conditionForm"].submit();		
	}
	</script>
	</head>
<body>
		<input type="hidden" name="preSelectRow" id="preSelectRow" />
		<table width="100%" style="margin:0px;" cellspacing="0" cellpadding="0">
			<tr>
			  <td>   
			     <table width="100%" cellpadding="0" cellspacing="0" border="0">
			       <tr>
				       <td>
				       		<!-- 
						    <input type="image" id="del" src="images/DelA.gif" onmouseover="changeImage(this,'DelA2.gif')" onmouseout="changeImage(this,'DelA.gif')" onclick="document.tableForm.submit();"/>
						     -->
							<input type="image" src="images/find.gif" alt="显示查询(Q)" onmouseover="changeImage(this,'find2.gif')" onmouseout="changeImage(this,'find.gif')" onclick="return showfind(this);"/>
							<input type="image" src="images/JDDJ.png" onmouseover="changeImage(this,'JDDJ1.png')" onmouseout="changeImage(this,'JDDJ.png')" onclick="showBorrow();"/>												
						</td>
						 
						<td>
							<div id="location" style="margin-right: 2px; width: 280px; display: inline-block; float: right; margin-top: 0px; color: blue; text-align: right;">
							  <font style="font-size: 12px;"><b>当前位置：</b>利用管理&nbsp;&gt;&gt;&nbsp;&nbsp;借档登记</font>
						    </div>												
						</td>
				  </tr>
			   </table>		
			</tr>
			<tr>
			  <td align="center" id="find" style="display:none;width:100%;">			     
			      
			     <fieldset>
				     <form action="DALY/archivesUseAction_findUseList.action" method="post" name="conditionForm" id="conditionForm" style="margin: 0px; padding: 0px;">
				      <input type="hidden" name="useType" value="JD"/>
				      <input type="hidden" name="dataPageInfo.currentPage" id="currentPage" value="1">
				       <table class="findTB">
				         <tr>
				           <td align="right" class="text">借档人</td>
				           <td align="left"><input type="text" name="archivesUseRegisterQueryCondition.userRealName" value="${archivesUseRegisterQueryCondition.userRealName}"/></td>
				         </tr>
				         <tr>
				           <td align="right" class="text">证件号</td>
				           <td align="left"><input type="text" name="archivesUseRegisterQueryCondition.IDCardNo" value="${archivesUseRegisterQueryCondition.IDCardNo}"/></td>
				         </tr>
				         <tr>
				           <td align="right" class="text">借档人部门</td>
				           <td align="left"><input type="text" name="archivesUseRegisterQueryCondition.userDepartment" value="${archivesUseRegisterQueryCondition.userDepartment}"/></td>
				         </tr>
				      	 <tr>
				           <td align="right" class="text">借档日期</td>
				           <td class="text">
				             <input type="text" id="strDate" name="archivesUseRegisterQueryCondition.useDateBegin" value="<s:date name='archivesUseRegisterQueryCondition.useDateBegin' format='yyyy-MM-dd'/>"/>
				             <img style="margin-right:20px;" src="images/dropdownTime.gif" onclick="PopUpCalendar('strDate',true)"  />
				             到<input type="text" id="endDate" name="archivesUseRegisterQueryCondition.useDateEnd" value="<s:date name='archivesUseRegisterQueryCondition.useDateEnd' format='yyyy-MM-dd'/>"/>
				             <img style="margin-right:20px;" src="images/dropdownTime.gif" onclick="PopUpCalendar('endDate',true)"  />
				           </td>
				         </tr>			         
				       </table>
				       </form>
			       </fieldset>			     
			      <input type="image" src="images/search.gif" onclick="subQuery()" onmouseover="changeImage(this,'search2.gif')" onmouseout="changeImage(this,'search.gif')"/>
			  </td>
			</tr>
			<tr>
				<td>
					<table class="tabletop" width="100%">
						<tr>
			                <td class="tableTitle">利用登记列表</td>
			                <td align="right" class="text">第${dataPageInfo.currentPage}页 共${dataPageInfo.pageCount}页 共${dataPageInfo.rowCount}条记录</td>							                	
			            </tr>
					</table>
					<table id="showTable" width="100%" style="margin:0px; border: #104da6 1px solid;" cellspacing="1px" cellpadding="0px" >						
						<thead class="tableHead">
							<tr class="bgTitle">
							<%--	<th width="35px">选择</th> --%>								
								<th width="35px">序号</th>
								<th>借档人姓名</th>
								<th>借档人证件号</th>
								<th>借档人部门</th>
								<th>借档日期</th>			
								<th align="center" width="60px">操作</th>							
							</tr>
						</thead>
						<tbody>	
						<!--  
						 <form id="tableForm" name="tableForm" action="DALY/archivesUseAction_delUseList.action" method="post" style="margin: 0;padding: 0;">
						
						 <input type="hidden" name="type" id="type" value="JD">	
						 <input type="hidden" name="archivesUseRegisterQueryCondition.userRealName" value="${archivesUseRegisterQueryCondition.userRealName}"/>
						 <input type="hidden" name="archivesUseRegisterQueryCondition.iDCardNo" value="${archivesUseRegisterQueryCondition.IDCardNo}"/>		
						 <input type="hidden" name="archivesUseRegisterQueryCondition.userDepartment" value="${archivesUseRegisterQueryCondition.userDepartment}"/>
						 <input type="hidden" id="strDate" name="archivesUseRegisterQueryCondition.useDateBegin" value="<s:date name='archivesUseRegisterQueryCondition.useDateBegin' format='yyyy-MM-dd'/>"/>
						 <input type="hidden" id="endDate" name="archivesUseRegisterQueryCondition.useDateEnd" value="<s:date name='archivesUseRegisterQueryCondition.useDateEnd' format='yyyy-MM-dd'/>"/>
						 -->
						  <s:iterator value="#request.archivesUseRegisters" status="status">
						    <s:if test="(#status.index+1)%2==0">
						       <s:set name="color" value="'#e0edff'" scope="page"></s:set>
						    </s:if>
						    <s:else>
						       <s:set name="color" value="'#eef5ff'" scope="page"></s:set>
						    </s:else>
						    <tr  bgcolor="${pageScope.color}" id="row<s:property value="#status.index+1"/>" onclick="selectRow(this)">
						       <%--<td><input type="checkbox" name="ids" value="<s:property value="ID"/>"/></td> --%>
								<td align="center" height="20px"><s:property value="#status.index+1"/></td>
								<td><s:property value="archivesUsePersonInfo.name"/></td>
								<td><s:property value="archivesUsePersonInfo.IDCardNo"/></td>
								<td><s:property value="archivesUsePersonInfo.department"/></td>
								<td><s:date name="useDate" format="yyyy-MM-dd"/></td>								
								<td align="center"><a href="javascript:showDJXX('<s:property value="ID"/>')">详细</a></td>	
							</tr>
						  </s:iterator>
							<!--  </form> -->	
						</tbody>
					</table>
					 
				    <table width="100%" style="font-size: 12px;">
						 <tr>
						    <td>
						     <%--  <input type="checkbox" id="SelectAll" accesskey="S"  name="SelectAll" onclick="selectAll(this)" title="选中/取消 所有记录(S)"  /><label for="SelectAll">全选</label> --%>	
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
