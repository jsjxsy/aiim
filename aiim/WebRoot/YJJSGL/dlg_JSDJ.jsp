<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("path",basePath);		
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>" target="_self"/>
		<title>接收登记</title>
		<META http-equiv="Pragma" content="no-cache">
		
		<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="js/jquery.form.js"></script>
		<link rel="stylesheet" type="text/css" href="css/common.css"/>
		<style type="text/css">
		  
		</style>
		<script type="text/javascript">
		  $(document).ready(function(){
             $("#btOk").bind("click",function(){
                 $("#form1").ajaxSubmit({
                	  beforeSubmit:function(){
                      var flag = true;
                	 
	   		          var transferTotalPatrn = /^[0-9]*$/;
	   		          var types = $("input[alt='数量']");
	   		          $.each(types, function(i, type){
	   		    	     if(type.value == null || type.value == ""){
		   		    	     alert("档案数量不能为空");
		   		    	     flag = false;
		   		    	     return false;
		   		    	 }else if(!transferTotalPatrn.exec(type.value)){
		   		    		 alert("档案数量请输入数字");
		   		    		 flag = false;
		   		    		 return false;
			   		     }
	   		    	  });
	   		    	  return flag;
	   		        }, 
	   		        success:function(data){
	   		          if(data != null && data != ""){
		   		          alert(data);
		   		      }
	   		          window.returnValue=1;
	   		        },
	   		        error:function(XMLHttpRequest, textStatus){alert(textStatus+","+XMLHttpRequest.status);}
                 });
             });

               
		  });

		
		  function refreshParent(){
		    var types = document.getElementsByName("type");
		    var typeStr = "";
		    for(var i = 0; i <types.length;i++){
		        var str = types[i].value;
		        if(str == ""){
		           str = 0;
		        }
			    if(i <= types.length){
			       typeStr += types[i].id+","+str+";";
			    }else{
			       typeStr += types[i].id+","+str;
			    }   
		    }
		    document.getElementById("types").value=typeStr;
		    document.form1.action="YJGL/YJJSAction_modifyBatches.action";
		    window.returnValue=1;
		    // window.dialogArguments.location.href=window.dialogArguments.location.href ;
		  }
		</script>
	</head>
	<body class="bg_color" style="margin-top: 4px;">
	   <s:if test="#request.batNo == null">	   
	     <form action="YJGL/YJJSAction_findByBatNO.action" method="post"> 
	      <table class="back_border" width="98%" style="height: 98%" cellpadding="0" cellspacing="0" align="center">
	      <tr>
			<td class="bg_title">批次信息</td>
		  </tr>
	      <tr>
	         <td align="center">
	           <table>
	             <tr>
	               <td colspan="3" style="font-size: 12px;color: red;">
	                 ${message}
	               </td>
	             </tr>
	             <tr>
	               <td><font style="font-size: 12px;">请输入批次号：</font></td>
	               <td><input type="text" name="batNo"/></td>
	               <td><input type="submit" value="确      定" class="button"/></td>
	             </tr>
	           </table>   
	         </td>
	        </tr>
	      </table>
	     </form> 
	   </s:if>
	    <s:else>
	     
	      <table class="back_border" width="98%" cellpadding="0" cellspacing="0" align="center">
			<tr>
				<td class="bg_title">批次信息</td>
			</tr>
			<tr>
				<td>
					<table width="100%">
						<tr>
							<td class="text">
								批次号
							</td>
							<td align="left">
								<input id="batNo" name="batNo" type="text" value="<s:property value="#request.batNo"/>" readonly="readonly"/>
							</td>
							<td class="text">
								档案总数量
							</td>
							<td align="left">
								<input id="transferTotal" name="transferTotal" value="${requestScope.paperTransferBatch.transferTotal}" type="text" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<td class="text">
								移交人
							</td>
							<td align="left">
								<input id="batNoCreateUserName" name="batNoCreateUserName" value="${requestScope.paperTransferBatch.batNoCreateUserName}" type="text" readonly="readonly"/>
							</td>
							<td class="text">
								移交时间
							</td>
							<td align="left">
                                <input id="transferTime" name="transferTime" value="<s:date name="paperTransferBatch.transferTime" format="yyyy-MM-dd"/>" type="text" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<td class="text">
								送出部门
							</td>
							<td align="left">
								<input id="transferDepartmentName" name="transferDepartmentName"  value="${requestScope.paperTransferBatch.transferDepartmentName}" type="text" readonly="readonly"/>
							</td>
							<td class="text">
								接收部门
							</td>
							<td align="left">
								<select disabled="disabled">
									<option selected="selected">
										&nbsp;&nbsp;—&nbsp;业务指导室&nbsp;—&nbsp;&nbsp;
									</option>
									<option>
										&nbsp;&nbsp;—&nbsp;档案管理室&nbsp;—&nbsp;&nbsp;
									</option>
								</select>
							</td>
						</tr>
						<tr>
						  <td colspan="4">
						    <table cellpadding="0" cellspacing="0">
							     <tr>
							       <td class="text">
							                 接收档案数量
							       </td>
							       <td>
							         <div style="overflow: auto; height: 80px; margin-left: 5px;">
							         <form action="YJGL/YJJSAction_modifyBatches.action" method="post" name="form1" id="form1">
							         <input id="batNo" name="batNo" type="hidden" value="<s:property value="#request.batNo"/>"/>
							         <table cellpadding="0" cellspacing="0"  style="border:1px solid;" >
							           <tr> 
							           <c:forEach items="${paperTransferBatch.paperTransferBatchesArchvTypeDetails}" var="paperTransferBatchesArchvTypeDetail"  varStatus="status">
							             <c:if test="${(status.index+1)%2 == 0}">
							                <td class="text">
							                  <c:forEach items="${archivesTypes}" var="archivesType">
							                    <c:if test=" ${paperTransferBatchesArchvTypeDetail.value.archivesTypeID==archivesType.value.ID}">
							                       <c:out value="${archivesType.value.fullName }"></c:out>
							                    </c:if>
							                  </c:forEach>
							                </td>
								            <td>
								              <input type="text" name="${paperTransferBatchesArchvTypeDetail.value.archivesTypeID}" id="archivesTypeID" value="" alt="数量"/>
								            </td>
								          </tr> 
								          <tr> 
							              </c:if>   
							              <c:if test="${(status.index+1)%2 == 1}">
							                 <td class="text">
							                   <c:forEach items="${archivesTypes}" var="archivesType">
							                    <c:if test="${paperTransferBatchesArchvTypeDetail.value.archivesTypeID==archivesType.value.ID}">
							                        <c:out value="${archivesType.value.fullName }"></c:out>
							                    </c:if>
							                  </c:forEach>
							                 </td>
								             <td>
								               <input type="text" name="${paperTransferBatchesArchvTypeDetail.value.archivesTypeID}" id="archivesTypeID" value="" alt="数量"/>
								             </td>
							              </c:if>
							           </c:forEach>
							           </tr>
							         </table>
							         </form>
							         </div>
							       </td>
							     </tr>
							  </table>
						  </td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<table style="height: 30px;margin-bottom: 4px;" width="98%">
			<tr>
			    <td width="205px" style="color: red;font-size: 12px;text-align: left;">${requestScope.message}</td>
				<td align="left">
					<input type="submit" id="btOk" class="button" value="确     定" />&nbsp;&nbsp;
					<input type="button" id="btCancel" class="button" value="关    闭" onClick="javascript:window.close();" />&nbsp;&nbsp;
					<input type="button" id="continue" class="button" value="继续登记" onclick="continueToRegistration()" />
				</td>
			</tr>
		</table>
		<form action="YJGL/JSDJRefreshAction.action" name="refreshForm">
		</form>
	   </s:else>
	   <script type="text/javascript">
	     function continueToRegistration(){
	        document.refreshForm.submit();
	     }
	   </script>
	</body>
</html>
