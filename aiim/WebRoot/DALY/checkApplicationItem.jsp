<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"/>   
    <title>申请审批</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/common.css">
	<script type="text/javascript" src="<%=basePath%>js/common.js"></script>		
	<script type="text/javascript" src="dwr/interface/ArchivesUseAction.js"></script>
    <script type="text/javascript" src="dwr/util.js"></script>
    <script type="text/javascript" src="dwr/engine.js"></script>
    
<!-- 页面处理  -->
 <script type="text/javascript"  >

	//单击《退出》按钮
    function clickExit(){
        window.close();
    }

	//同意请求
	function agree(targetID){				
		checkArchivesUseRequestDetail(targetID ,1,"");//审批通过
	}
	
	//不同意请求
	function disagree(targetID){
		var backReason = window.showModalDialog(
				"backReason.jsp",
				window,
				"dialogWidth:310px; dialogHeight:150px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");
		if(backReason==undefined){
			return false;
		}
		//alert(targetID+':'+backReason);
		checkArchivesUseRequestDetail(targetID ,2,backReason);//审批不通过
	}


  //{callback:addArchivesUseRegisterBack,exceptionHandler:function(message){alert(message);}}
////////DWR 方法/////////////////
//申请审批：0代表未审；1代表通过；2代表未通过
function checkArchivesUseRequestDetail(ID ,checkResult,backReason){
ArchivesUseAction.checkArchivesUseRequestDetail(ID ,checkResult,backReason,{callback:checkArchivesUseRequestDetailBack,exceptionHandler:function(message){alert(message);}});
}

function checkArchivesUseRequestDetailBack(result){
	//alert(result);
	window.document.location.reload();
}
   </script>

</head>

<body style="margin:0px; background-color:#f9f9f9;">
	<s:if test="#request.recordsNum>0">		
	<div align="center" id="inputs" style="width: 100%;font-size:12px; overflow:auto; margin-top: 5px; ">
	    <fieldset style="overflow:auto; margin-left:3px; width:15%; height: 450px; float:left; vertical-align: top;" >
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
	    <fieldset  style="overflow:auto; margin-right:3px; width:25%; height: 450px; float:right; vertical-align: top;" >
			<legend style="font-size: 12px;">利用人登记信息</legend>	
			<table width="100%" style="font-size:12px;">
		        <tr>
		            <td class="text" width="90px;">证件号</td>
			        <td align="left">
			        	<input  id="IDCardNo" onchange="clearUserID()" type="text" onkeydown="getUserInfo()" name="archivesUsePersonInfo.IDCardNo" value="${userInfo.IDCardNo }"/>
				        <input type="hidden" name="archivesUsePersonInfo.ID" id="userID" value="0"/>
				    </td>		
				 </tr>
				 <tr>								       
			        <td class="text" width="90px;">姓名</td>
			        <td align="left">
			      	  	<input id="name" type="text" onchange="clearUserID()" name="userInfo.realName" value="${userInfo.realName }"/>										        
			        </tr>
		         <tr>
		            <td class="text" width="90px;">所属部门</td>
			        <td align="left">
				        <input id="department" type="text"  name="archivesUsePersonInfo.department"  value="${archivesUseRequestDetail.archivesUseRequest.userDepartment}"/>
				    </td>
				 </tr>
				 <tr>
			        <td class="text" width="90px;">E_mail</td>
			        <td align="left">
				        <input  id="email" type="text"  name="archivesUsePersonInfo.email" value="${userInfo.email }"/>
				    </td>
			    </tr>
			    <tr>
			        <td class="text" width="90px;">电话</td>
			        <td align="left">
				        <input id="tel" type="text" name="archivesUsePersonInfo.tel" value="${userInfo.tel }"/>
				    </td>
				</tr>
				<tr>
			        <td class="text">申请时间</td>
			        <td align="left">									        	
						<input type="text" name="requestTime" id="requestTime" value="<s:date name="#request.archivesUseRequestDetail.archivesUseRequest.requestTime" format="yyyy-MM-dd"/>">
					</td>
		        </tr>
		        <tr>
		        	<td class="text">申请理由</td>
		        	<td>
		        		<textarea name="requestReason" id="requestReason"  style="width: 250px;height: 95px;">${archivesUseRequestDetail.archivesUseRequest.requestReason}</textarea>
		        	</td>
		        </tr>
		        <tr>
		        	<td colspan="2" align="center">
		        		<input type="button" value="通过" onclick="agree(${archivesUseRequestDetail.ID})">
		        		<input type="button" value="打回" onclick="disagree(${archivesUseRequestDetail.ID})">
					</td>
		        </tr>
		   </table>		 
		</fieldset>	
		
		<center style="font-size: 13px;color:blue;">还有<span style="font-size:18px;">${recordsNum }</span>条待审核申请</center>
	    <table width="45%" style="font-size: 12px;" cellpadding="0" cellspacing="0">
	    	${htmlCode}
	    </table>
	</div>
	</s:if>
	<s:else>
		<br/>
		<div align="center"><span style="color:red;">当前没有待审核的申请</span></div>
	</s:else>




 
</body>
</html>

