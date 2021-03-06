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
    
    <title>更新任务</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/Styles.css" type="text/css" rel="stylesheet" />
	<link href="css/common.css" type="text/css" rel="stylesheet" />
	<link href="css/Login.css" type="text/css" rel="stylesheet" />
	
	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/popup.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/jquery.form.js"></script>
		<style type="text/css">
		  .tableHead{
	font-weight:bold; text-align:center; padding:4px 0 0 0px;
}
.tableTitle {font-weight:bold; text-align:left; padding:4px 0 0 5px;}
/*设置标题底色*/
.bgTitle { background-color:#a3c9ff;height:25px;}
/*设置表格顶部框底色*/
.borderTop {	border-top:#104da6 1px solid;	border-left:#104da6 1px solid;	border-right:#104da6 1px solid;}
.tableHead{
	font-weight:bold; text-align:center; padding:4px 0 0 0px;
}
.tbody tr{
	 background-color:#e0edff;
}
</style>
		
<script type="text/javascript">
//打开选择接收人对话框
function showTaskPersons(){
  window.showModalDialog("receiver_choice.jsp","newwindow","dialogWidth=500px;dialogHeight=380px;center=yes;scroll=no;status=no;resizable=no;help=no;location=no");
}

function openWindow(url,width,height) {
	window.open(url,"newwindow","height="+height+",width="+width+",left="+(window.screen.width-width)/2+",top="+(window.screen.height-height)/2+",Toolbar=no,Menubar=no,scrollbars=yes,resizable=no,location=no,status=no");
}

$(function(){
	//设置发布的状态
	var publishFlag = '${taskinfo.publishFlag}';
	if(publishFlag) {
		$('#publishY').attr('checked','true');
	} else {
		$('#publishN').attr('checked','true');
	}
	
	$('#myForm').ajaxForm({
		beforeSubmit:function(){
			var title = $('#title');
			if(title.val()==null || title.val()=='') {
				alert('请输入任务主题！');
				title.focus();
				return false;
			}

			var content = $('#content');
			if(content.html()==null || content.html()=='') {
				alert('请输入任务内容！');
				content.focus();
				return false;
			}

			//选中发布 必须选中接收人
			if($('#publishFlag').val()=='y') {
				var taskReceivers = $('#taskReceivers');
				if(taskReceivers==null || taskReceivers.val()==null || taskReceivers.val()=='') {
					alert('进行发布时，请选择接收人！');
					taskReceivers.focus();
					$('#choice').click();
					return false;
				}
			}
		},
	    success:function(data){
		  window.returnValue= '1';
	      window.close();
	    },
	    error:function(XMLHttpRequest, textStatus){alert(textStatus+","+XMLHttpRequest.status);}
	});	
});



//设置是否进行任务发布
function setPublish(flag) {
	$('#publishFlag').val(flag);
}
</script>
	</head>
  
  <body class="bg_color" style="margin-top:4px">
  	<s:hidden name="oldReceiveIds" />
  	<s:hidden id="taskInfoId" name="editTaskInfoId" />
	<table align="center" class="back_border" width="98%" style="height:80px;" cellpadding="0" cellspacing="0">
	    <tr>
	        <td class="bg_title bg_title4" align="center">&nbsp;详细信息</td>
	    </tr>
	    <tr>
	        <td>
	            <table width="100%">
	            	<tr>
				        <td class="text">&nbsp;主题：</td>
				        <td align="left">
					        <span style="width:380px;">${taskinfo.title}</span></td>
					</tr>
			        <tr>
				        <td class="text">&nbsp;发&nbsp;送&nbsp;给：</td>
				        <td align="left">
				        	<span style="width:380px;">${taskinfo.receiveNames}</span>
					   	</td>
					</tr>
			        <tr>
				        <td class="text">&nbsp;内容：</td>
				        <td align="left" colspan="3">
				           <textarea id="content" name="content" rows="15" style="width:380px;" readonly="readonly">${taskinfo.content}</textarea>
				        </td>
					</tr>
				</table>
	        </td>
	    </tr>
</table>
	<table style="height:30px" cellpadding="5" width="100%">
		<tr>
			<td align="center">
				<input type="hidden" id="publishFlag" name="publishFlag" value="${publishFlag}">
				<input type="button" id="btCancel" class="button15" value="关闭" onclick="javascript:window.close();"/>
			</td>
		</tr>
	</table>
</body>
</html>