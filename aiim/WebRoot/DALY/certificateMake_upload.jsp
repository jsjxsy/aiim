<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.orifound.aiim.entity.UserInfo"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String certificateInfoID = request.getParameter("certificateInfoID");
UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
%>
<%@ include file="/JXGL/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>" target="_self">
    
    <title>出证档案上传</title>
    
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
	<script type="text/javascript">

	//上传文件服务器路径
	var uploadPath;
	//webservice服务器地址
	var serviceURL;

	 //获得文件服务器地址
    function getServiceURL(){
    	attachedFileManageAction.getServiceURLDWR(getServiceURLCallback);
    }
    function getServiceURLCallback(data){
    	serviceURL = data;
    }

    //获取出证文件上传路径
    function getUploadPath() {
		ArchivesCertificateManageDWR.getUploadPath({
			callback: function(uploadPathDate) {
				uploadPath = uploadPathDate;
			},
			errrorHandler:function(message){
				alert('系统出错！！');
				window.location.reload();
			}
		});
	}

	$(function(){
		$('#certificateInfoID').val(<%= certificateInfoID%>);
		$('#uploadForm').ajaxForm({
			beforeSubmit:function(){
				var flag = true;
				var oldPath = $('#uploadFile').val();
				
				if(oldPath==null || oldPath=='') {
					alert('请选择上传文件！');
					alert($('#uploadFile').focus());
					flag = false;
				}

				if (flag) {
					getUploadPath();
					getServiceURL();
					alert('uploadPath='+uploadPath);
					alert('serviceURL='+serviceURL);
					var fileSign = document.getElementById('signApplet');
					var deptName = '<%=userInfo.getDepartmentName()%>';
					
<%--					var returnValue = fileSign.signFile(0, oldPath, uploadPath, deptName, serviceURL);--%>
<%--	        		var attachedFile = eval('('+returnValue+')');--%>
<%--	        		if(attachedFile.success != "success"){--%>
<%--	        			alert(attachedFile.errorMessage);--%>
<%--	        			flag = false;--%>
<%--	        		} --%>
				}
				falg = false;
				return falg;
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
  <body style="margin-top:1px">
  <s:form id="uploadForm" action="archivesCertificateManageAction_uploadFile" namespace="/DALY">
  	<s:hidden id="certificateInfoID" name="certificateInfoID" />
  	<table class="back_border" style="height:98%;width:98%;" align="center" cellpadding="0" cellspacing="0">
	    <tr>
	        <td class="bg_title">出证档案上传</td>
	    </tr>
  		<tr>
  			<td>
  				<input type="file" style="width: 100%" id="uploadFile" name="upload" value="浏览">
  			</td>
  		</tr>
  		<tr>
  			<td align="right">
  				<input type="submit" value="上传">
  				<input type="button" value="关闭" onclick="window.close();">
  			</td>
  		</tr>
  	</table>
  </s:form>
  <object id="signApplet"
	    classid = "clsid:8AD9C840-044E-11D1-B3E9-00805F499D93"
	    codebase = "<%=basePath%>appletSign/jinstall-6u10-windows-i586.cab#Version=6,0,0,32"
	    WIDTH = "0" HEIGHT = "0" >
	    <PARAM NAME = CODE VALUE = "com.orifound.aiim.util.FileSignerApplet" >
	    <PARAM NAME = ARCHIVE VALUE = "<%=basePath%>appletSign/appletSign.jar" >
	    <param name = "type" value = "application/x-java-applet;version=1.6">
	    <param name = "scriptable" value = "false">
	
	    <comment>
		<embed
	            type = "application/x-java-applet;version=1.6" 
	            CODE = "com.orifound.aiim.util.FileSignerApplet" 
	            ARCHIVE = "appletSign.jar" 
	            WIDTH = "0" 
	            HEIGHT = "0"
		        scriptable = false
		        pluginspage = "<%=basePath%>appletSign/jre_download.jsp">
		    <noembed>
	        </noembed>
		</embed>
	    </comment>
	</object>
  </body>
</html>