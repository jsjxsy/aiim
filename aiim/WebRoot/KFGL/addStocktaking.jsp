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
    
    <title>新增库房盘点工作信息</title>
    
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
		document.forms["inputForm"].submit();
	}
	</script>

  </head> 
   <body  style="background-color:#f9f9f9" >
  <form name="inputForm" action="KFGL/storeroomManageAction_addStocktaking.action" method="post" >
    <table id="table1" cellspacing="0" cellpadding="0" style="margin-left:10px; margin-top:10px;" >
		<tr  height="30px;">
			<td>
				待盘点的库房
			</td>
			<td width="190px;">
				<s:select id="storeroomID" value="%{#request.storeroomID}" name="storeroomID" list="%{#request.storeRooms}" theme="simple" cssStyle="width:150px;" listKey="ID" listValue="name"></s:select>
			</td>
		</tr>
	</table>
	<br/>
<div align="right" style="height: 80;"><input type="submit" <s:if test="#request.successFlg==true">disabled="disabled"</s:if>  value=" 保存 " /><input type="button" style="margin-left:6px;margin-right: 10px;" <s:if test="#request.successFlg==true">value=" 退出 " </s:if><s:else>value=" 取消 " </s:else>  onclick="clickClose();"/></div>

</form>
  </body>
</html>
