<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>" target="_self">
    
    <title>更新登记信息</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<script type="text/javascript" src="js/common.js"></script>
	 <script type="text/javascript" src="js/jquery.js"></script>
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">

	
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

	//单击关闭按钮
	function clickClose(){
		//将操作标识返回到父页面， 让父页面根据此标识刷新页面
		returnValue = ('true'=='<s:property value="#request.successFlg"/>');
		window.close();
	}

	//验证输入
	function validate(){
		if($("#measureDate").val()==""){
			alert('请输入日期！');
			$("#measureDate").focus();
			return false;
		}

		if($("#measureTime").val()==""){
			alert('请输入时间！');
			$("#measureTime").focus();
			return false;
		}

		if($("#temperature").val()==""){
			alert('请输入温度！');
			$("#temperature").focus();	
			return false;		
		}

		if($("#humidity").val()==""){
			alert('请输入湿度！');
			$("#humidity").focus();
			return false;
		}
		
		//验证实数
		var reg = new RegExp("^[0-9]+\.{0,1}[0-9]{0,2}$","gi");	
		if(reg.test($("#temperature").val())==false){
			alert('温度格式不正确，请重新输入！');
			$("#temperature").focus();
			return false;
		}
		var reg2 = new RegExp("^[0-9]+\.{0,1}[0-9]{0,2}$","gi");
		if(reg2.test($("#humidity").val())==false){
			alert('湿度格式不正确，请重新输入！');
			alert(":"+$("#humidity").val()+":");
			$("#humidity").focus();
			return false;
		}
	
		document.forms["inputForm"].submit();
	}
	</script>

  </head> 
   <body  style="background-color:#f9f9f9" >
  <form name="inputForm" action="KFGL/storeroomManageAction_updateTempratureHumidityInfo.action" method="post" >
    <input type="hidden" name="tempratureHumidityInfo.ID" value="${tempratureHumidityInfo.ID }">
    <table id="table1" cellspacing="0" cellpadding="0" style="margin-left:10px; margin-top:10px;" >
		<tr  height="30px;">
			<td>
				库房
			</td>
			<td width="190px;">
				<s:select id="storeroomID" name="tempratureHumidityInfo.storeroomID" list="%{#request.storeRooms}" theme="simple" cssStyle="width:150px;" listKey="ID" listValue="name"></s:select>
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
		</tr>
		<tr  height="30px;">
			<td >
				日期&nbsp;
			</td>
			<td>
				<input id="measureDate" type="text" readonly="readonly" name="tempratureHumidityInfo.measureDate" value="<s:date  name="tempratureHumidityInfo.measureDate"  format="yyyy-MM-dd"/>"/><img src="images/dropdownTime.gif" style="cursor:pointer;"onclick="PopUpCalendar('measureDate',true)" />
			</td>
			<td>
				时间&nbsp;
			</td>
			<td >
				<input id="measureTime" type="text" name="tempratureHumidityInfo.measureTime" value="${tempratureHumidityInfo.measureTime }"/><span style="color:blue; font-size: 12px;">格式：hh:mm</span>
			</td>		
		</tr>
		<tr height="30px;">
			<td>
				温度&nbsp;
			</td>
			<td >
				<input id="temperature" type="text" name="tempratureHumidityInfo.temperature" value="${tempratureHumidityInfo.temperature }"/>
			</td>
			<td>
				湿度&nbsp;
			</td>
			<td >
				<input id="humidity" type="text" name="tempratureHumidityInfo.humidity" value="${tempratureHumidityInfo.humidity}"  />
			</td>
		</tr>
	</table>
<table cellspacing="0" cellpadding="0" style="margin-left:10px;" width="100%">
	<tr>
		<td style="width: 280px;font-size: 12px;">注：温度单位是°C，湿度单位是%。</td>
		<td align="right"><div > <span style="font-size:12px;color:blue;<s:if test="#request.successFlg!=true">display:none;</s:if><s:else>display:inline;</s:else>  ">更新成功！</span><input type="button"  style="margin-left:5px;"  value=" 保存 " onclick="validate()"/><input type="button" style="margin-left:6px;margin-right: 25px;" <s:if test="#request.successFlg==true">value=" 退出 " </s:if><s:else>value=" 取消 " </s:else>  onclick="clickClose();"/></div></td>
	</tr>
</table>
</form>
  </body>
</html>
