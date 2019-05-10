<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>" target="_self"/>   
    <title>查阅申请</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
 <link rel="stylesheet" type="text/css" href="css/common.css">
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/jquery.js"></script>
	
	<script type="text/javascript" src="dwr/interface/ArchivesUseAction.js"></script>
    <script type="text/javascript" src="dwr/util.js"></script>
    <script type="text/javascript" src="dwr/engine.js"></script>
    
<!-- 页面处理  -->
 <script type="text/javascript"  >

	function deleteUseInfo(archivesTypeId,NBXH,useWay){
		ArchivesUseAction.delArchivesToUseList(archivesTypeId,NBXH,useWay
				,{
					callback:function(message1){
						alert(message1);
						$("#"+archivesTypeId+NBXH+useWay).remove();
					}
					,exceptionHandler:function(message2){alert(message2);}});
	}

	//弹出借档登记窗口
	function registUserInfo(){
	  returnValue = window.showModalDialog("<%=basePath%>DALY/archivesUseAction_getRegistUserInfoDataForOnlineApply.action","newwindow","dialogWidth=550px;dialogHeight=295px;center=yes;scroll=no;status=no;resizable=no;help=no;location=no");
	  if(returnValue==true){//如果新增了档案利用登记信息则刷新页面
		  //window.document.location.reload();
		  window.close();
	  }
	}

	//打印调卷单
	function printLocation(){
		window.showModalDialog("<%=basePath%>DALY/archivesUseAction_printLocationOfUseList.action","newwindow","dialogWidth=850px;dialogHeight=550px;center=yes;scroll=no;status=no;resizable=no;help=no;location=no");
		window.close();
		}
	
   </script>

</head>

<body style="margin:0px; background-color:#f9f9f9;">	
   <table class="tabletop" width="100%">
		<tr>
               <td class="tableTitle">利用申请列表(${useSize})</td>
               <td align="right" class="text"></td>							                	
           </tr>
	</table>
	<div style="width: 100%;height: 430px;  margin: 0;padding: 0;">
		<table id="showTable" width="100%" style="margin:0px;   border: #104da6 1px solid; vertical-align: top;" cellspacing="1px" cellpadding="0px" >						
			<thead class="tableHead">
				<tr class="bgTitle">
					<th>序号</th>
					<th>档号</th>
					<th>题名</th>
					<th>利用方式</th>
					<th>馆藏状态</th>	
					<th>操作</th>					
				</tr>
			</thead>
			<tbody>
				<s:iterator value="#request.archivesUseList" status="status">	
					<s:if test="(#status.index+1)%2==0">
				       <s:set name="color" value="'#e0edff'" scope="page"></s:set>
				    </s:if>
				    <s:else>
				       <s:set name="color" value="'#eef5ff'" scope="page"></s:set>
				    </s:else>	
					<tr id="<s:property value="archivesTypeID" /><s:property value="NBXH" /><s:property value="tag" />" bgcolor="${pageScope.color}"  >																	
							<td width="30px;" align="center"><s:property value="#status.index+1"/></td>	
							<td ><s:property value="archivesID"/></td>
							<td ><s:property value="title"/></td>
							<td >
								<s:if test="tag==1">
									借档
								</s:if>
								<s:if test="tag==2">
									查档
								</s:if>
								<s:if test="tag==3">
									查看原文
								</s:if>
							</td>	
							<td ><s:property value="storeStatus"/></td>	
							<td ><a href="javascript:deleteUseInfo(<s:property value="archivesTypeID" />,<s:property value="NBXH" />,<s:property value="tag" />)"  >删除</a></td>			
					</tr>
			   </s:iterator>
			</tbody>
		</table>
	</div>		
	<div style="float: right;">
	<input type="button" onclick="window.close();" value="<<继续添加">
	<input type="button" value="提交利用申请>>" onclick="registUserInfo()" style="margin-left: 3px;"> 
	<input type="button" value="打印调档单>>" onclick="printLocation()" style="margin-right: 3px;"> 
	</div>   	
		
<%--	
<fieldset style="overflow:auto; width: 100%;" >
	<legend style="font-size: 12px;">利用申请列表(${useSize})</legend>	
		<table style="width: 100%;font-size: 12px;">
			<thead>
				<tr>
					<th>序号</th>
					<th>档号</th>
					<th>题名</th>
					<th>利用方式</th>
					<th>馆藏状态</th>
				</tr>				
			</thead>
			<tbody>
			<s:iterator value="#request.archivesUseList" status="status">		
				<tr id="<s:property value="NBXH" />" >		
					<s:property value="#status.index+1"/>										
						<td width="20px;"><s:property value="#status.index+1"/></td>	
						<td ><s:property value="archivesID"/></td>
						<td ><s:property value="title"/></td>
						<td ><s:property value="NBXH"/></td>
				
				</tr>
		   </s:iterator>
		   </tbody>		   
		</table>	 
</fieldset>	
 <div style=" vertical-align: bottom; float: right;">

	 <s:if test="#request.viewAttachedFileFlag==false && #request.attachedFileSize!=0">
	 	<span style="font-size:12px;color:blue;">您无权查看原文，若要查看请提交申请!</span><input type="button" value="申请查看原文">
	 </s:if> 
	 <input style="margin-right: 3px;" type="button" value=" 退 出 " onclick="window.close();">

 </div>
  --%>
  
</body>
</html>

