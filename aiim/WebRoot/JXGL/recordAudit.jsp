<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="taglib.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>著录审核情况</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/Styles.css" type="text/css" rel="stylesheet" />
	<link href="css/common.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/dateTool.js"></script>
	
	<script type="text/javascript">
	$(function(){
		setInterval(refresh, 1000);	
	});
	
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

	function showBorrow(){
		  window.showModalDialog("dlgBorrow_JD.htm","newwindow","dialogWidth=500px;dialogHeight=420px;center=yes;scroll=no;status=no;resizable=no;help=no;location=no");
		}
	</script>	
<style type="text/css">
/*设置标题底色*/
.bgTitle { background-color:#a3c9ff;height:25px;}
/*设置表格顶部框底色*/
.borderTop {	border-top:#104da6 1px solid;	border-left:#104da6 1px solid;	border-right:#104da6 1px solid;}
body 
{
	height:100%;
	color: #000000; 
	font-size:12px;
	margin:0; 
	background-color:White;
}
/*表头*/
.tableTitle {font-weight:bold; text-align:left; padding:4px 0 0 5px;}
.text{ font-size:9pt;}
.tableHead{
	font-weight:bold; text-align:center; padding:4px 0 0 0px;
}
/*
#showTable tbody tr:hover{
	background-color:#a3c9ff;
}
*/
/*设置被选行的颜色*/
.selectRowColor{
	background-color:#a4caef;
}
</style>

<script type="text/javascript">

/**
 * 提交表单
 	查看著录情况
 */
function queryCondition() {
	var beginDate = StringToDate($('#beginTime').val());
	var endDate = StringToDate($('#endTime').val());
	if(beginDate > endDate) {
		alert('开始日期不能大于结束日期，请重新选择！');
		beginTime.focus();
		return false;
	}
	$('form').submit();
}
</script>
  </head>
  <body>
  <s:form action="workProcedureMonitorAction_manage" namespace="/JXGL">
  	<s:hidden name="nodeId" value="12" />
  	<table width="100%" style="margin:0px;font-size:12px;" cellspacing="0" cellpadding="0">
			<tr>
				<td>
				 <div style="margin-right:1px; display:block; float:right;margin-top:5px; color:blue;width: 570px;">
						<font style="font-size:13px;font-weight:bold;">当前位置:绩效管理&gt;&gt;工作过程监测&gt;&gt;著录审核情况&nbsp;&nbsp;当前时间：<span id="currentTime"></span></font>
				  </div>
				</td>
			</tr>
			<tr>
				<td>
				  	监测时间
				  	<s:textfield name="beginTime" id="beginTime" theme="simple"/><image style="margin-right:20px;" src="images/dropdownTime.gif" onclick="PopUpCalendar('beginTime',true)"  />
	             	到<s:textfield name="endTime" id="endTime" theme="simple"/><image style="margin-right:20px;" src="images/dropdownTime.gif" onclick="PopUpCalendar('endTime',true)"  />
				  	<input type="button" onclick="javascript:queryCondition();" value="查看" />
				</td>
			</tr>
			<tr>
				<td>
				 	<c:choose>
				 		<c:when test="${url == 'null'}">无结果记录</c:when>
				 		<c:otherwise><img id="jfreechart" src="${url}" alt="业务指导室著录情况一览表" /></c:otherwise>
				 	</c:choose>
				</td>
			</tr>
		</table>
	</s:form>
  </body>
</html>
