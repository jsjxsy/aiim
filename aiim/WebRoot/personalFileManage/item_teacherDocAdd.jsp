<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>著录教职工卷内材料</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/common.css"/>
	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.form.js"></script>
	<SCRIPT type="text/javascript">
	 //得到表格的一行
	 function getRow(table,array){
	    var $ths = table.children("thead").children().children();
	    var $tr = $("<tr></tr>");
	    for(var i=0;i<$ths.length;i++){
	       $tr.append("<td>"+array[i]+"</td>");
	    }
	    return $tr;
	 }

	 $(function(){
		 $("#form1").ajaxForm({
			 beforeSubmit:function(){},
			 success:function(data){alert(data);window.returnValue = 1;},
			 error:function(XMLHttpRequest, textStatus){alert(textStatus+","+XMLHttpRequest.status);}
		 });
     });
	</SCRIPT>
	
<style type="text/css">
body{
	font-size:14px;
	background-color:#f9f9f9;
}
</style>
</head>

<body>
<fieldset style="width: 100%; margin-top: 10px;margin-left: 5px; margin-right: 5px; ">
	<legend>教职工基本信息</legend>
	<table cellpadding="0" cellspacing="0" style="margin-left:8px;font-size:12px">
		<tr height="30px">
			<td width="50px" >工资号</td>
			<td><s:property value="#request.teacherInfo.gzh"/></td>
			<td width="50px">姓名</td>
			<td><s:property value="#request.teacherInfo.xm"/></td>			
		</tr>
		<tr height="30px">
			<td width="50px" >档号</td>
			<td><s:property value="#request.teacherInfo.archivesID "/></td>
			<td width="50px">专业</td>
			<td><s:property value="#request.teacherInfo.zy"/></td>			
		</tr>
	</table>
</fieldset>
<form action="JZGDAGL/teacherFileManageAction_addDoc.action" method="post" id="form1" name="form1">
<input name="nbxh" type="hidden" value="<s:property value="#request.teacherInfo.NBXH"/>"/>
<fieldset style="width: 100%; margin-top: 10px;margin-left: 5px; margin-right: 5px; ">
	<legend>已有卷内文件</legend>
	<table style="width: 100%;" >
		<tr>
			<td width="50px;"><span style="font-size:12px">文件类型</span></td>
			<td>
			   <s:select list="%{#request.teacherDocsTypes}" listKey="ID" listValue="name" value="请选择材料类型" theme="simple" name="teacherDocsInfo.docTypeID"></s:select>
			</td>
		</tr>
		<tr>
			<td class="text">材料名称</td>
			<td><input type="text"  name="teacherDocsInfo.docName"></td>
			<td class="text">形成日期</td>
			<td><input type="text"  name="teacherDocsInfo.formationDate"></td>
		</tr>
		<tr>
			<td class="text">份数</td>
			<td><input type="text"  name="teacherDocsInfo.copys"></td>
			<td class="text">页数</td>
			<td><input type="text"  name="teacherDocsInfo.pages"></td>
		</tr>
		<tr>
			<td class="text">顺序号</td>
			<td><input type="text"  name="teacherDocsInfo.orderID"></td>
			<td class="text"></td>
			<td></td>
		</tr>
	</table>
</fieldset>
    <table align="center">
       <tr>
          <td><input type="submit" value="提    交"></td>
       </tr>
    </table>
    </form>
</body>

</html>
