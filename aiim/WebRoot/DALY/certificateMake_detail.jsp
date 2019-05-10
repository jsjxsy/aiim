<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/JXGL/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>出证制作明细</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="js/jquery.js"></script>

	<script type="text/javascript">
	//出证下载
	function downloadFile(dealedFlag, idObj){
		$('#dealedFlag').val(dealedFlag);
		$('#certificateFileName').val($('#'+idObj).html());
		$('form').attr('action','DALY/downloadCertificateFile.action');
		$('form').submit();
	}
	
	//出证上传
	function file_upload(certificateInfoID) {
<%--		var returnValue = window.showModalDialog("<%=basePath%>DALY/certificateMake_upload.jsp?certificateInfoID="+certificateInfoID,"newwindow","dialogWidth=550px;dialogHeight=100px;center=yes;scroll=no;status=no;resizable=no;help=no;location=no");--%>
<%--		  if(returnValue==1){//如果新增了档案利用登记信息则刷新页面--%>
<%--			  submitForm();--%>
<%--		  }--%>

		  window.open("<%=basePath%>DALY/certificateMake_upload.jsp?certificateInfoID="+certificateInfoID);
	}

	//学生基本信息
	function find_studentInfo(XH, certificateInfoID) {
		var returnValue = window.showModalDialog("<%=basePath%>DALY/archivesCertificateManageAction_findStudentInfo.action?XH="+XH+"&certificateInfoID="+certificateInfoID,"newwindow","dialogWidth=400px;dialogHeight=300px;center=yes;scroll=no;status=no;resizable=no;help=no;location=no");
		  if(returnValue==1){//如果新增了档案利用登记信息则刷新页面
			  submitForm();
		  }
	}

	//学生成绩
	function find_studentGrade(XH) {
		var returnValue = window.showModalDialog("<%=basePath%>DALY/archivesCertificateManageAction_findGrade.action?XH="+XH,"newwindow","dialogWidth=560px;dialogHeight=600px;center=yes;scroll=yes;status=no;resizable=no;help=no;location=no");
		  if(returnValue==1){//如果新增了档案利用登记信息则刷新页面
			  submitForm();
		  }
<%--		  window.open("<%=basePath%>DALY/archivesCertificateManageAction_findGrade.action?XH="+XH);--%>
	}

	//提交表单
	function submitForm() {
		$('form').submit();
	}
	</script>
  </head>
<body>
<s:form action="archivesCertificateManageAction_certificateMakeDetail" namespace="/DALY">
  <input type="hidden" name="preSelectRow" id="preSelectRow" />
  <s:hidden id="certificateRegID" name="certificateRegID" />
  <s:hidden id="certificateFileName" name="certificateFileName" />
  <s:hidden id="dealedFlag" name="dealedFlag" />
  <div id="location" style="margin-right: 2px; width: 280px; display: inline-block; float: right; margin-top: 0px; color: blue; text-align: right;height: 30px;vertical-align: middle;">
  		<font style="font-size: 12px;"><b>当前位置：</b>利用管理&nbsp;&gt;&gt;&nbsp;出证制作&nbsp;&gt;&gt;&nbsp;出证详情</font>
   </div>
  	<table class="tabletop" width="100%">
		<tr>
               <td class="tableTitle">档案出证明细</td>
               <td align="right" class="text"></td>							                	
           </tr>
	</table>
	<table id="showTable" width="100%" style="margin:0px; border: #104da6 1px solid;" cellspacing="1px" cellpadding="0px" >						
		<thead class="tableHead">
			<tr class="bgTitle">
				<th width="35px">序号</th>
				<th>出证类型</th>
				<th>份数</th>
				<th>出证序列号</th>
				<th>加急</th>
				<th>制作完成</th>
				<th>证明文件</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody align="center">
		  <s:iterator value="#request.archivesCertificateInfos" status="status">
		    <s:if test="(#status.index+1)%2==0">
		       <s:set name="color" value="'#e0edff'" scope="page"></s:set>
		    </s:if>
		    <s:else>
		       <s:set name="color" value="'#eef5ff'" scope="page"></s:set>
		    </s:else>
		    <tr  bgcolor="${pageScope.color}" id="row<s:property value="#status.index+1"/>" onclick="selectRow(this)">
				<td align="center" height="20px"><s:property value="#status.index+1"/></td>
				<td><s:property value="certificateTypeName"/></td>
				<td><s:property value="total"/></td>
				<td><s:property value="certificateSN"/></td>
				<td>
					<s:if test="expressFlag">是</s:if>
					<s:else>否</s:else>
				</td>
				<td>
					<s:if test="dealedFlag">是</s:if>
					<s:else>否</s:else>
				</td>
				<td>
					<a id="file<s:property value="#status.index"/>" href="javascript:downloadFile('<s:property value="dealedFlag"/>','file<s:property value="#status.index"/>');">
						<s:if test="dealedFlag"><s:property value="certificateFileName"/></s:if>
						<s:else><s:property value="templateFileName"/></s:else>
					</a>
				</td>
				<td>
					<a href="javascript:file_upload('<s:property value="iD"/>');">上传</a>
					<s:if test="gradeFlag">
						<a href="javascript:find_studentInfo('<s:property value="XH"/>','<s:property value="iD"/>');">学生信息</a>
						<a href="javascript:find_studentGrade('<s:property value="XH"/>');">学生成绩</a>
					</s:if>
				</td>
			</tr>
		  </s:iterator>
		</tbody>
	</table>
</s:form>
</body>
</html>