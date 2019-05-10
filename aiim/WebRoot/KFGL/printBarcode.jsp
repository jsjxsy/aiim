<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>条码打印</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="dwr/interface/StoreroomManageAction.js"></script>
    <script type="text/javascript" src="dwr/util.js"></script>
    <script type="text/javascript" src="dwr/engine.js"></script>
	
<script type="text/javascript" >

//单击单选框，设置条码类型值
function changeBarcodeType(typeValue){//条码类型有3：1、设备条码；2、盒条码；3、档案条码
	$("archivesInfoBarcodeType").value = typeValue;
}

//数字验证
function validNum(){
	var barcodeCount = $("barcodeCount").value;
	var re = /^\d*$/;
	
	if(barcodeCount==""){
		$("strResult").innerText= '打印数量 不为空！';
		$("strResult").style.color='red';
	}else if(!re.test(barcodeCount)){
		//alert('请输入数字！');
		$("strResult").innerText= '打印数量 必须为数字！';
		$("strResult").style.color='red';		
	}else {
		printBarcode();
		}
	//alert(obj.value);
		
}

/////////////////DWR///////////////// 
//DWR:查询最大档案条码
function findCurrentBarcode(){

	var barcodeType = $("archivesInfoBarcodeType").value;
	StoreroomManageAction.findCurrentBarcode(barcodeType,findCurrentBarcodeBack)
}
function findCurrentBarcodeBack(data){
	alert(data);
	$("strResult").innerText = data;
	
}

//DWR: 打印档案条码 
function printBarcode(){
	var barcodeType=$("archivesInfoBarcodeType").value;
	var barcodeCount=$("barcodeCount").value;	
	StoreroomManageAction.printBarcode(barcodeType,barcodeCount,printBarcodeBack);
}
function printBarcodeBack(data){//打印条码的回调函数
	$("strResult").innerHTML = data;
	$("strResult").style.color='blue';
}
</script>

  </head>
  
  <body style="margin:0">
  <table width="100%" style="margin:0px; font-size:12px;" cellspacing="0" cellpadding="0">
	<tr>
		<td>
			&nbsp;
		</td>
		<td>
			<div style="margin-right:2px; display:inline-block; width:300px; color:blue; float:right; margin-top:5px;" align="right">
						<font style="font-size:12px;font-weight:bold;">当前位置：</font>库房管理&nbsp;&gt;&gt;&nbsp;条码打印</div >
		</td>
	</tr>	
</table>
  <input type="hidden" value="3" name="archivesInfoBarcodeType" id="archivesInfoBarcodeType">
  <center>
<fieldset style="width:550px; margin-left:20px;  height: 142px;">
	<legend>条码打印</legend>
	<table cellpadding="2px" cellspacing="0" style=" margin-left:10px;">
	<tr style="height:35px;">
		<td style="width: 66px">条码类型</td>
		<td style="width: 190px"><input type="radio" name="barcodeType" onclick="changeBarcodeType(3)" checked="checked" id="datm"/><label for="datm" style="font-size:14px;">档案条码</label><input type="radio" style="margin-left:30px;" onclick="changeBarcodeType(2)"  name="barcodeType" id="htm"/><label for="htm" style="font-size:14px;">盒条码</label></td>
	</tr>
	<tr style="height:35px;">
		<td style="width: 66px">打印数量</td>
		<td style="width: 190px">
		<input type="text" style="width: 171px" name="barcodeCount" id="barcodeCount" /> </td>
	</tr>
	</table>	
	<input type="button"  value=" 打印 " onclick="validNum()" />
	<div style="margin-left:10px; margin-top:5px;">&nbsp;<span id="strResult"  style="color: blue;font-size: 12px;"></span></div>

</fieldset>
</center>
  </body>
</html>
