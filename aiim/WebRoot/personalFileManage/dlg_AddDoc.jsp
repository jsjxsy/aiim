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
    
    <title>添加学生卷内材料</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/common.css">
	
	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="js/jquery.form.js"></script>
    <script type="text/javascript">
    $(document).ready(function(){
        $("#form1").ajaxForm({
            beforeSubmit:function(){
               var studentDocs = $(":checkbox:checked");
               if(studentDocs.length == 0){
                   alert("请选择要添加的材料！");
                   return false;
               }
            },
            success:function(data){
                alert(data);
                window.returnValue = 1;
                window.close();
            },
            error:function(XMLHttpRequest, textStatus){alert(textStatus+","+XMLHttpRequest.status);}
        });
    });
    </script>
  </head>
  
  <body style="text-align: center;">
  <form action="http://www.baidu.com" method="post" name="form2">
  </form>
  <br/>
  <fieldset style="width: 98%">
      <form action="XSDAGL/studentFileManageAction_addDocs.action" method="post" id="form1">
      <input type="hidden" name="nbxh" value="<s:property value="#request.studentInfo.NBXH"/>"/>
	  <table style="font-size: 12px;" align="center">
	    <tr>
	       <td>学号：<s:property value="#request.studentInfo.studentId"/></td>
	       <td>姓名：<s:property value="#request.studentInfo.studentName"/></td>
        </tr>
        <tr>
	       <td>专业：<s:property value="#request.studentInfo.specialty"/></td>
	       <td>班级：<s:property value="#request.studentInfo.grade"/></td>
	    </tr>
	    <tr><td colspan="2"><hr/></td></tr>
	    <tr>
	       <s:iterator value="#request.studentInfo.studentDocsInfos" status="status" var="s">
	          <s:if test="#status.even">
	           <td>
	             <s:if test="#s.existsFlag">
	               <input type="checkbox" name="studentDocs" value="<s:property value="id"/>" checked/><s:property value="docName"/>
	             </s:if>
		         <s:else>
		            <input type="checkbox" name="studentDocs" value="<s:property value="id"/>"/><s:property value="docName"/>
		         </s:else>    
		       </td>
		       </tr><tr>
	          </s:if>
	          <s:else>
	           <td>
		          <s:if test="#s.existsFlag">
	               <input type="checkbox" name="studentDocs" value="<s:property value="id"/>" checked/><s:property value="docName"/>
	             </s:if>
		         <s:else>
		            <input type="checkbox" name="studentDocs" value="<s:property value="id"/>"/><s:property value="docName"/>
		         </s:else> 
		       </td>
	          </s:else>
		   </s:iterator>  
		</tr>
		<tr>
		  <td colspan="2" align="center">
		    <input type="submit" value="确     定" class="button"/>
		  </td>
		</tr>
	 </table>
	 </form>
  </fieldset>  
  </body>
</html>
