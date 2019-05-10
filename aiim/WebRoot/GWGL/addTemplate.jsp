<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新增模板</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/common.css"/>
    <%--    <script type="text/javascript" src="js/common.js"></script>--%>
    <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.form.js"></script>
    <script type="text/javascript" >
	//打开日期输入页面
	   function getTitle(obj){
	        var path = obj.value;
	        document.getElementById("title").value = path.substring(path.lastIndexOf("\\")+1,path.lastIndexOf("."));
	    }

	   /*翻页函数
		url 请求的url地址
		formName 查询条件的form
		currentPage 当前页
		 */
		function pageTurning(formName, currentPage) {
			document.getElementById("currentPage").value = currentPage;
			document.forms[formName].submit();
		}
		/*
		跳到某一页
		 */
		function gotoPage(formName) {
			var gotoPage = document.getElementById("gotoPage").value;
			pageTurning(formName, gotoPage);
		}

	    //提交form表单并刷新父页面
		$(document).ready(function(){
		   $("#addForm").ajaxForm({
		        beforeSubmit:function(){
				        
		        }, 
		        success:function(data){
		         // alert(data);
		          window.dialogArguments.document.conditionForm.submit();
		          window.close();
		        },
		        error:function(XMLHttpRequest, textStatus){alert("删除失败！ "+textStatus+","+XMLHttpRequest.status);}
		   });
		}); 	
	</script>
  </head>
 
  <body style="margin:0px; background-color:#f9f9f9;">	
	<form action="GWGL/fileUpload.action" method="post" id="addForm" name="addForm" style="margin: 0;padding: 0;" >	
	<table id="inputs" width="100%" style="height:100%;  margin-left:20px;  font-size:15px;" cellpadding="0" cellspacing="0" >
		<tr>
			<td width="70px" class="text">模板类型</td>
			<td>
				<select style="width:150px;" name="docType">
					<s:iterator var="officialDocType" value="#request.officialDocTypes">
						<option value="<s:property  value="key"/>"><s:property value="value.name"/></option>
					</s:iterator>
				</select>
				选择模板<input type="file" name="upload"  style="width:200px;" onchange="getTitle(this)"/>
			</td>
		</tr>
		<tr style="height:20px;">	
			<td class="text">公文名称</td>
		 <td><input type="text" name="title" size="50" id="title"/></td>
		</tr>
		<tr style="height:5px;"><td></td></tr>
		<tr>
		  <td align="right">备 注</td>
		  <td><input type="text" name="remark" style="width: 416px;"/></td>
	  </tr>
		<tr>
			<td></td>
			<td>
				<input type="submit" class="button" value="保     存" style="margin-left: 100px;"/>&nbsp;&nbsp;&nbsp;
				<input type="reset" class="button" value="重     置"/>
			</td>
		</tr>		
	</table>
	</form>
  </body>
</html>
