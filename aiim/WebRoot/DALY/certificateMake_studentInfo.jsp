<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String fileId = request.getParameter("fileId");
%>
<%@ include file="/JXGL/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>出证学生基本信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/common.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/popup.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/jquery.form.js"></script>

	<style type="text/css">
		select{width: 155px;}
	</style>
	
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
		var retval = window.showModalDialog(url,"", "dialogWidth:197px; dialogHeight:210px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no;help=no; "  );
		if( retval != null ){
			obj.value = retval;
		}
	}

	$(function(){
		$('#baseForm').ajaxForm({
			beforeSubmit:function(){
				var flag = true;
				var XH = $('#XH');
				var nameCN = $('#nameCN');
				var majorNameCN = $('#XH');
				var collegeNameCN = $('#XH');
				var entranceDate = $('#entranceDate');
				var graduateDate = $('#graduateDate');
				if(flag && XH.val()=='') {
					alert('请输入学号！');
					XH.focus();
					flag = false;
				}

				if(flag && nameCN.val()=='') {
					alert('请输入中文名！');
					nameCN.focus();
					flag = false;
				}

				if(flag && majorNameCN.val()=='0') {
					alert('请选择专业！');
					majorNameCN.focus();
					flag = false;
				}

				if(flag && collegeNameCN.val()=='0') {
					alert('请选择学院！');
					collegeNameCN.focus();
					flag = false;
				}

				if(flag && entranceDate.val()=='') {
					alert('请输入入学日期！');
					entranceDate.focus();
					flag = false;
				}

				if(flag && graduateDate.val()=='') {
					alert('请输入毕业日期！');
					graduateDate.focus();
					flag = false;
				}
				return flag;
			},
		    success:function(data){
			  window.returnValue= '1';
		      window.close();
		    },
		    error:function(XMLHttpRequest, textStatus){alert(textStatus+","+XMLHttpRequest.status);}
		});	
	});
	
	</script>
  </head>
  <body style="margin-top:4px">
  <s:form id="baseForm" action="archivesCertificateManageAction_saveOrUpdateStudentInfo" namespace="/DALY">
  		<s:hidden id="certificateInfoID" name="certificateInfoID" />
  		<s:hidden id="updateStudentFlag" name="updateStudentFlag" />
	  	<table class="back_border" style="height:98%;width:98%;" align="center" cellpadding="0" cellspacing="0">
		    <tr>
		        <td class="bg_title">出证学生基本信息</td>
		    </tr>
	  		<tr>
		        <td valign="top">
		            <table width="100%" style="font-size: 12px;">
				         <tr>
							<td align="right">学号</td>
							<td align="left">
								<s:if test="{#updateStudentFlag=='y'}">
									<label><s:property value="certificateStudent.XH"/></label>
								</s:if>
								<s:else>
									<s:textfield id="XH" name="certificateStudent.XH" />
								</s:else>
							</td>
						</tr>
						<tr>
							<td align="right">中文名</td>
							<td align="left"><s:textfield id="nameCN" name="certificateStudent.nameCN" /></td>
						</tr>
						<tr>
							<td align="right">英文名</td>
							<td align="left"><s:textfield id="nameEN" name="certificateStudent.nameEN" /></td>
						</tr>
						<tr>
							<td align="right">专业</td>
							<td align="left">
								<s:select id="majorNameCN" name="certificateStudent.majorNameCN" list="majors"
									listKey="majorNameCN" listValue="majorNameCN" headerKey="0" headerValue="----请选择----" />
							</td>
						</tr>
						<tr>
							<td align="right">学院</td>
							<td align="left">
								<s:select id="collegeNameCN" name="certificateStudent.collegeNameCN" list="colleges" 
									listKey="collegeNameCN" listValue="collegeNameCN" headerKey="0" headerValue="----请选择----" />
							</td>
						</tr>
						<tr>
							<td align="right">入学日期</td>
							<td align="left">
								<input type="text" id="entranceDate" name="certificateStudent.entranceDate" 
									value='<s:date name="certificateStudent.entranceDate" format="yyyy-MM-dd"/>'/>
								<img style="margin-right:20px;" src="images/dropdownTime.gif" onclick="PopUpCalendar('entranceDate',true)"  />
							</td>
						</tr>
						<tr>
							<td align="right">毕业日期</td>
							<td align="left">
								<input type="text" id="graduateDate" name="certificateStudent.graduateDate" 
									value='<s:date name="certificateStudent.graduateDate" format="yyyy-MM-dd"/>'/>
								<img style="margin-right:20px;" src="images/dropdownTime.gif" onclick="PopUpCalendar('graduateDate',true)"  />
							</td>
						</tr>
			        </table>
			        <table style="height:30px" cellpadding="5" width="100%">
						<tr>
							<td align="center">
							    <input type="submit" id="btOk" class="button" value="确&nbsp;定"/>&nbsp;&nbsp;
								<input type="button" id="btCancel" class="button" value="取&nbsp;消" onClick="javascript:window.close();"/>
						    </td>
						</tr>
					</table>
		        </td>
		    </tr>
	  	</table>
	  </s:form>
  </body>
</html>
