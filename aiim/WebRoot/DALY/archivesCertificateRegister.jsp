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
	
	<script type="text/javascript">

	  
	 //显示明细
	function viewDetail(CertificateRegID)
	{
		window.showModalDialog(
				"archivesCertificateManageAction_findChargeCollectDetail.action?CertificateRegID="+CertificateRegID,
				window,
				"dialogWidth=600px;dialogHeight:400px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");

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
	</script>
  </head>
  <body>
		   <form action="DALY/archivesCertificateManageAction_balanceStatDetail.action" name="conditionForm" id="conditionForm" method="post">
				<input type="hidden" name="dataPageInfo.currentPage" id="currentPage" value="1">
				<input type="hidden" name="ManagerUserID" id="ManagerUserID" value="${requestScope.ManagerUserID}">
				<input type="hidden" name="dateQuerycondition.beginTime" id="beginTime" value="<s:date name="#request.dateQuerycondition.beginTime" format="yyyy-MM-dd"/>">
				<input type="hidden" name="dateQuerycondition.endTime" id="endTime" value="<s:date name="#request.dateQuerycondition.endTime" format="yyyy-MM-dd"/>">
			</form>
				<table class="tabletop" width="100%">
					<tr>
		                <td class="tableTitle">利用人详细信息</td>
		                <td align="right"  class="text" >
							<label style="margin-right:4px" id="rsInfo">第${dataPageInfo.currentPage}页 共${dataPageInfo.pageCount}页 共${dataPageInfo.rowCount}条记录</label>
						</td>							                	
		            </tr>
				</table>	
				<table id="showTable" cellpadding="0px" cellspacing="1px">						
					<thead class="tableHead">
						<tr>
							<th>利用人</th>								
							<th>应缴金额 </th>
							<th>实收金额 </th>
							<th>登记日期 </th>
							<th>发票代码 </th>
							<th>备注</th>
							<th>明细信息</th>
						</tr>
					</thead>
					<tbody>
					<s:iterator var="archivesCertificateRegister" value="#request.archivesCertificateRegisters">
						<tr bgcolor="#e0edff" id="row">
							<td><s:property value="realName"/></td>
							<td><s:property value="shouldCharge"/></td>
							<td><s:property value="realCharge"/></td>
							<td><s:date name="registerDate" format="yyyy年MM月dd日"/></td>
							<td><s:property value="invoiceSN"/></td>
							<td><s:property value="remark"/></td>
							<td><a href="javascript:viewDetail(<s:property value="ID"/>)">查看</a></td>
						</tr>
					</s:iterator>
					</tbody>
				</table>
			</td>
		</tr>
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
							style="text-decoration: none;"> <image src="images/nexts.gif"
								onmouseover="changeImage(this,'nexts1.gif')"
								onmouseout="changeImage(this,'nexts.gif')" alt="下一页" /> </a>
						<a
							href="javascript:pageTurning('conditionForm',${dataPageInfo.pageCount})"
							style="text-decoration: none;"> <image src="images/lasts.gif"
								onmouseover="changeImage(this,'lasts1.gif')"
								onmouseout="changeImage(this,'lasts.gif')" alt="最后一页" /> </a>
					</s:if>
					<s:if test="#request.dataPageInfo.nextState=='disable'">
						<image src="images/nexts2.gif" alt="已经是最后一页" />
						<image src="images/lasts2.gif" alt="已经是最后一页" />
					</s:if>
				</td>
				<td style="width: 100px; font-size: 12px;">
					转到第
					<input type="text" name="gotoPage" id="gotoPage"
						style="width: 18px; height: 18px" />
					页
				</td>
				<td style="width: 20px; vertical-align: bottom;">
					<input type="image" src="images/gos.gif"
						onmouseover="changeImage(this,'gos2.gif')"
						onmouseout="changeImage(this,'gos.gif')"
						onclick="gotoPage('conditionForm')" />
				</td>
			</tr>
		</table>
  </body>
</html>
