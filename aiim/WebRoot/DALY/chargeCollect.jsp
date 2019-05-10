<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.orifound.aiim.web.util.TimeTool" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>收费汇总信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

    <link rel="stylesheet" type="text/css" href="css/common.css"/>
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/dateTool.js"></script>
	
	<script type="text/javascript">

	//初始化页面
	 $(document).ready(function(){
	 	$('#beginTime').val("<%=TimeTool.getCurrentMonday()%>");
	 	$('#endTime').val(new Date().Format('yyyy-MM-dd 23:59:59'));
	  });
	  
	 //显示明细
	 /*
	function viewDetail(ID)
	{
		window.showModalDialog(
				"archivesCertificateManageAction_findChargeCollectDetail.action?CertificateRegID="+ID,
				window,
				"dialogWidth=480px;dialogHeight:400px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:1;location:yes; resizable=no;location=n");

	}
	*/
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
				url="http://localhost:8080/aiim/js/CalendarWithFormat.html";
			}
			else {
				url="http://localhost:8080/aiim/js/CalendarWithOutFormat.html"
			}
			var retval = window.showModalDialog(url,"", "dialogWidth:197px; dialogHeight:210px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no;help=no; "  );
			if( retval != null ){
				obj.value = retval;
			}
		}
	
	/*翻页函数
	url 请求的url地址
	formName 查询条件的form
	currentPage 当前页
	 */
	function pageTurning(formName, currentPage) {
		document.getElementById("currentPage").value = currentPage;
		document.forms[formName].submit();
	}
	/*
	跳到某一页
	 */
	function gotoPage(formName) {
		var gotoPage = document.getElementById("gotoPage").value;
		pageTurning(formName, gotoPage);
	}
	
	/**
 	*提交表单
 	*查看著录情况
	*/
function queryCondition() {
		
	var beginDate = StringToDate($('#beginTime').val());
	var endDate = StringToDate($('#endTime').val());
	if(beginDate > endDate) {
		alert('开始日期不能大于结束日期，请重新选择！');
		beginTime.focus();
		return false;
	}
	$('conditionForm').submit();
}
	</script>
  </head>
  
  <body>
    <input type="hidden" name="preSelectRow" id="preSelectRow" />
	<table width="100%" style="margin:0px;" align="center">
	     <tr>
		  <td>   
		     <table width="100%" cellpadding="0" cellspacing="0" border="0">
		       <tr>
			       <td>
                      <input type="image" src="images/find.gif" onmouseover="changeImage(this,'find2.gif')" onmouseout="changeImage(this,'find.gif')" onclick="showfind(this)" alt="显示查询(Q)"/>
				   </td>
				   <td>
				   	  <div style="margin-right:2px; width:210px; float:right; color:blue; font-size:12px;"><font style="font-weight:bold;">当前位置：</font>出证登记&nbsp;>>&nbsp;收费汇总</div >					
				   </td>
		       </tr>
		     </table>
		   </td>
		</tr>
		<tr>
		   <td id="find" style="display: none;">
		     <fieldset>
		     <form action="DALY/archivesCertificateManageAction_manage.action" name="conditionForm" id="conditionForm" method="post">
		     <input type="hidden" name="dataPageInfo.currentPage" id="currentPage" value="1">
		     <input type="hidden" name="nodeId" id="nodeId" value="13"/>
		     <table style="font-size: 12px;" align="center">
				<tr>
					<td class="text">时间段</td>
					<td>
					  从<input type="text" name="dateQuerycondition.beginTime" id="beginTime" style="width: 80px">
					<image style="margin-right:20px;" src="images/dropdownTime.gif" onclick="PopUpCalendar('beginTime',true)"  />
					</td>
					<td>
					  到<input type="text" name="dateQuerycondition.endTime" id="endTime" style="width: 80px">
					  <image style="margin-right:20px;" src="images/dropdownTime.gif" onclick="PopUpCalendar('endTime',true)"  />
					</td>
				</tr>
				<tr>
				    <td></td>
					<td>
					  <input type="submit" value="" class="submitButton"  onmouseover="changeSubmitBgImage(this,'search2.gif')" onmouseout="changeSubmitBgImage(this,'search.gif')" onclick="queryCondition();javascript:document.getElementById('findDiv').style.display='none';" />
					</td>
					<td></td>
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
		                <td class="tableTitle">收费汇总</td>
		                <td align="right"  class="text" >
							<label style="margin-right:4px" id="rsInfo"></label>
						</td>							                	
		            </tr>
				</table>	
				<table id="showTable" cellpadding="0px" cellspacing="1px">						
					<thead class="tableHead">
						<tr>
							<th>收费人</th>								
							<th>应缴金额 </th>
							<th>实收金额 </th>
						</tr>
					</thead>
					<tbody>
					<s:iterator var="archivesCertificateRegister" value="#request.archivesCertificateRegisters" status="u">
					 <s:if test="#u.last">
					  <tr bgcolor="#e0edff" id="row">
							<td>总计</td>
							<td><s:property value="shouldCharge"/></td>
							<td><s:property value="realCharge"/></td>
						</tr>
					 </s:if>
					 <s:else>
					 	<tr bgcolor="#e0edff" id="row">
							<td><a href="DALY/archivesCertificateManageAction_balanceStatDetail.action?ManagerUserID=<s:property value="managerUserID"/>&dateQuerycondition.beginTime=<s:date name="dateQuerycondition.beginTime" format="yyyy-MM-dd"/>&dateQuerycondition.endTime=<s:date name="dateQuerycondition.endTime" format="yyyy-MM-dd"/>"><s:property value="realName"/></a></td>
							<td><s:property value="shouldCharge"/></td>
							<td><s:property value="realCharge"/></td>
						</tr>
					 </s:else>
					</s:iterator>
					</tbody>
				</table>
			</td>
		</tr>
	</table>
  </body>
</html>
