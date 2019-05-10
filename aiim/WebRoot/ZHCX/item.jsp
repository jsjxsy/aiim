<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"/>   
    <title>档案详细信息</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="dwr/interface/ArchivesInfoManageDWR.js"></script>
    <script type="text/javascript" src="dwr/util.js"></script>
    <script type="text/javascript" src="dwr/engine.js"></script>
    
<!-- 页面处理  -->
 <script type="text/javascript"  >

	//单击《退出》按钮
    function clickExit(){
        window.close();
    }
   </script>

</head>

<body style="margin:0px; background-color:#f9f9f9;">	
   <form name="inputForm" id="inputForm" method="post" style="margin: 0;padding: 0; font-size: 12px;">
	<div id="inputs" style="height:350px;width: 100%;font-size:12px; overflow:auto; margin-top: 5px; border:1px solid gray;">
	    <table width="100%" style="font-size: 12px;" cellpadding="0" cellspacing="0">
	    	${htmlCode}
	    </table>
	</div>
</form>	

<fieldset style="overflow:auto; height: 90px; vertical-align: top;" >
	<legend style="font-size: 12px;">原文列表(${attachedFileSize})</legend>	
		<table style="width: 100%;font-size: 12px;float: left; ">
			<s:iterator value="#request.archivesInfoAttachedFiles" status="rowstatus">		
				<tr id="<s:property value="NBXH" />" align="left">												
					<td width="20px;"><s:property value="#rowstatus.index+1"/>、</td>	
					<s:if test="#request.viewAttachedFileFlag==true">
						<td ><a href="#" style="font-size: 12px;"><s:property value="title"/></a></td>
					</s:if>
					<s:else>
						<td ><span style="font-size: 12px;"><s:property value="title"/></span></td>
					</s:else>
				</tr>
		   </s:iterator>		   
		</table>	 
</fieldset>	
 <div style=" vertical-align: bottom; float: right;">

	 <s:if test="#request.viewAttachedFileFlag==false && #request.attachedFileSize!=0">
	 	<span style="font-size:12px;color:blue;">您无权查看原文，若要查看请提交申请!</span><input type="button" value="申请查看原文">
	 </s:if> 
	 <input style="margin-right: 3px;" type="button" value=" 退 出 " onclick="window.close();">

 </div>
 
</body>
</html>

