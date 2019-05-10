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
<form action="JZGDAGL/teacherFileManageAction_addTeacherInfo.action" method="post" id="form1" name="form1">
	<input name="teacherInfo.archivesTypeID" type="hidden" value="<%=request.getParameter("archivesTypeID") %>"/>
	<fieldset style="width: 100%; margin-top: 10px;margin-left: 5px; margin-right: 5px; ">
		<legend>教职工基本信息</legend>
		<table cellpadding="0" cellspacing="0" style="margin-left:8px;font-size:12px">
			<tr height="30px">
				<td class="text">工资号</td>
				<td><input type="text" name="teacherInfo.gzh"/></td>
				<td class="text">姓名</td>
				<td><input type="text" name="teacherInfo.xm"/></td>	
				<td class="text">性别</td>
				<td><input type="text" name="teacherInfo.xb"/></td>			
			</tr>
			<tr height="30px">
				<td class="text">出生日期</td>
				<td><input type="text" name="teacherInfo.csrq"/></td>
				<td class="text">籍贯</td>
				<td><input type="text" name="teacherInfo.jg"/></td>	
				<td class="text">民族</td>
				<td><input type="text" name="teacherInfo.mz"/></td>			
			</tr>
			<tr height="30px">
				<td class="text">身份证号码</td>
				<td><input type="text" name="teacherInfo.sfzhm"/></td>
				<td class="text">党派</td>
				<td><input type="text" name="teacherInfo.dp"/></td>	
				<td class="text">来校前单位</td>
				<td><input type="text" name="teacherInfo.lxqdw"/></td>			
			</tr>
			<tr height="30px">
				<td class="text">参加工作时间</td>
				<td><input type="text" name="teacherInfo.cjgzsj"/></td>
				<td class="text">建档时间</td>
				<td><input type="text" name="teacherInfo.jdsj"/></td>	
				<td class="text">所属学院</td>
				<td><input type="text" name="teacherInfo.ssxy"/></td>			
			</tr>
			<tr height="30px">
				<td class="text">所属院系</td>
				<td><input type="text" name="teacherInfo.ssyx"/></td>
				<td class="text">职称系列</td>
				<td><input type="text" name="teacherInfo.zcxl"/></td>	
				<td class="text">职称</td>
				<td><input type="text" name="teacherInfo.zc"/></td>			
			</tr>
			<tr height="30px">
				<td class="text">评职称时间</td>
				<td><input type="text" name="teacherInfo.pzcsj"/></td>
				<td class="text">职务类型</td>
				<td><input type="text" name="teacherInfo.zwlx"/></td>	
				<td class="text">职务</td>
				<td><input type="text" name="teacherInfo.zw"/></td>			
			</tr>
			<tr height="30px">
				<td class="text">提职时间 </td>
				<td><input type="text" name="teacherInfo.tzsj"/></td>
				<td class="text">文化程度</td>
				<td><input type="text" name="teacherInfo.whcd"/></td>	
				<td class="text">学位</td>
				<td><input type="text" name="teacherInfo.xw"/></td>			
			</tr>
			<tr height="30px">
				<td class="text">毕业时间 </td>
				<td><input type="text" name="teacherInfo.bysj"/></td>
				<td class="text">毕业院校</td>
				<td><input type="text" name="teacherInfo.byyx"/></td>	
				<td class="text">档案类型</td>
				<td><input type="text" name="teacherInfo.dalx"/></td>			
			</tr>
			<tr height="30px">
				<td class="text">处理类型 </td>
				<td><input type="text" name="teacherInfo.cllx"/></td>
				<td class="text">处理时间</td>
				<td><input type="text" name="teacherInfo.clsj"/></td>	
				<td ></td>
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
