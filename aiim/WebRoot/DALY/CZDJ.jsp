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
    
    <title>出证登记</title>
    	
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
		//弹出出证登记窗口
		function showBorrow(){
		  returnValue = window.showModalDialog("<%=basePath%>DALY/archivesUseAction_findDefaultDataForCZDJ.action","newwindow","dialogWidth=550px;dialogHeight=462px;center=yes;scroll=no;status=no;resizable=no;help=no;location=no");
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

	//出证上传
	function file_upload(fileId) {
		returnValue = window.showModalDialog("<%=basePath%>DALY/dlgBorrow_CZSC.jsp?fileId="+fileId,"newwindow","dialogWidth=550px;dialogHeight=100px;center=yes;scroll=no;status=no;resizable=no;help=no;location=no");
		  if(returnValue==1){//如果新增了档案利用登记信息则刷新页面
			  window.document.location.reload();
		  }
	}

	//学生基本信息
	function find_studentInfo(idObj) {
		returnValue = window.showModalDialog("<%=basePath%>DALY/dlgBorrow_CZXS.jsp?XH="+idObj,"newwindow","dialogWidth=400px;dialogHeight=300px;center=yes;scroll=no;status=no;resizable=no;help=no;location=no");
		  if(returnValue==1){//如果新增了档案利用登记信息则刷新页面
			  window.document.location.reload();
		  }
	}

	//学生成绩
	function find_studentGrade(idObj) {
		returnValue = window.showModalDialog("<%=basePath%>DALY/dlgBorrow_CZXSCJ.jsp?XH="+idObj,"newwindow","dialogWidth=450px;dialogHeight=600px;center=yes;scroll=yes;status=no;resizable=no;help=no;location=no");
		  if(returnValue==1){//如果新增了档案利用登记信息则刷新页面
			  window.document.location.reload();
		  }
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
							<input type="image" src="images/czdj.gif" onmouseover="changeImage(this,'czdj1.gif')" onmouseout="changeImage(this,'czdj.gif')" onclick="showBorrow();"/>												
						</td>
						 
						<td>
							<div id="location" style="margin-right: 2px; width: 280px; display: inline-block; float: right; margin-top: 0px; color: blue; text-align: right;">
							  <font style="font-size: 12px;"><b>当前位置：</b>利用管理&nbsp;&gt;&gt;&nbsp;&nbsp;出证登记</font>
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
				           <td align="right" class="text">利用人</td>
				           <td align="left"><input type="text" name="archivesUseRegisterQueryCondition.userRealName" value="${archivesUseRegisterQueryCondition.userRealName}"/></td>
				         </tr>
				         <tr>
				           <td align="right" class="text">证件号</td>
				           <td align="left"><input type="text" name="archivesUseRegisterQueryCondition.IDCardNo" value="${archivesUseRegisterQueryCondition.IDCardNo}"/></td>
				         </tr>
				         <tr>
							<td align="right" class="text">出证类型</td>
							<td align="left">
								<select id="certificateTypeID" name="certificateTypeID" style="width: 155px;">
									<option value="0">---请选择---</option>
									<option value="1">学生毕业证明</option>
									<option value="2">学生成绩证明</option>
								</select>
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
			                <td class="tableTitle">档案出证明细</td>
			                <td align="right" class="text"></td>							                	
			            </tr>
					</table>
					<table id="showTable" width="100%" style="margin:0px; border: #104da6 1px solid;" cellspacing="1px" cellpadding="0px" >						
						<thead class="tableHead">
							<tr class="bgTitle">
							<%--	<th width="35px">选择</th> --%>								
								<th width="35px">序号</th>
								<th>出证人姓名</th>
								<th>出证类型</th>
								<th>出证数量</th>
								<th>加急办理</th>
								<th>登记日期</th>			
								<th align="center">下载</th>
								<th align="center" >上传</th>
								<th align="center" >学生信息</th>
								<th align="center" >成绩</th>
								<th align="center" >操作</th>
							</tr>
						</thead>
						<tbody align="center">
						<tr bgcolor="#eef5ff">
							<td width="35px">1</td>
							<td>张三</td>
							<td>学生毕业证明</td>
							<td>1</td>
							<td>否</td>
							<td>2010-08-24</td>			
							<td align="center" ><a href="javascript:void(0)" onclick="">学生毕业证明模板.doc</a></td>
							<td align="center" ><a href="javascript:void(0)" onclick="file_upload(1)">上传</a></td>
							<td align="center" >&nbsp;</td>
							<td align="center" >&nbsp;</td>
							<td align="center" >&nbsp;</td>
						</tr>
						<tr bgcolor="#e0edff">
							<td width="35px">1</td>
							<td>张三</td>
							<td>学生毕业证明</td>
							<td>1</td>
							<td>否</td>
							<td>2010-08-24</td>			
							<td align="center" ><a href="javascript:void(0)" onclick="">张三毕业证明.doc</a></td>
							<td align="center" ><a href="javascript:void(0)" onclick="file_upload(1)">上传</a></td>
							<td align="center" >&nbsp;</td>
							<td align="center" >&nbsp;</td>
							<td align="center" >&nbsp;</td>
						</tr>
						<tr bgcolor="#eef5ff">
							<td width="35px">1</td>
							<td>张三</td>
							<td>学生成绩证明</td>
							<td>10</td>
							<td>是</td>
							<td>2010-08-24</td>			
							<td align="center" >&nbsp;</td>
							<td align="center" ><a href="javascript:void(0)" onclick="file_upload(1)">上传</a></td>
							<td align="center" ><a href="javascript:void(0)" onclick="find_studentInfo(1);">学生信息</a></td>
							<td align="center" ><a href="javascript:void(0)" onclick="find_studentGrade(1)">成绩明细</a></td>
							<td align="center" ><a href="javascript:void(0)" onclick="">出证</a></td>
						</tr>		
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
					 
				 </td>
			</tr>
		</table>
	</body>
</html>
