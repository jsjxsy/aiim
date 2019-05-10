<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.orifound.aiim.entity.PaperTransferBatchesArchvTypeDetail"%>
<%@page import="com.orifound.aiim.entity.ArchivesType"%>
<%@page import="com.orifound.aiim.entity.SystemInitializer"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	    <base href="${basePath}">
		<title>审核批次的详细</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="css/common.css"/>
		<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="js/jquery.form.js"></script>
		
		<script type="text/javascript">
		$(document).ready(function(){
			
			window.returnValue = 1;
			
		   $($("#archivesTypeTable").children('tbody').children().first()).click();
		   
		   $("#generate").bind("click",function(){
			   $('#form1').ajaxSubmit({
			        beforeSubmit:function(){
			          $("#div1").text("正在生成档号请稍后.............");
			        }, 
			        success:function(data){
			          $("#div1").text(data);
			          if(data == "生成档号成功！"){
			        	  $("#generate").attr("disabled","true");
				      }
			          window.qdRight.location.href = window.qdRight.location.href;
			        },
			        error:function(XMLHttpRequest, textStatus){alert(textStatus+","+XMLHttpRequest.status);}
			    });
		   });
		   
		});

		
		function clickTR(obj,archivesIDMaked){
			var archivesTypeID = obj.id;
			<s:if test="#request.deptType == 'YWZDS'">
			if(archivesIDMaked == "true"){
				$("#generate").attr("disabled","disabled");
			}else{
				$("#generate").attr("disabled","");
			}
			</s:if>

			$.each($(obj).parent().children(),function(index,trr){
					$(trr).css('background','#e0edff');
			});
			obj.style.background = '#a4caef';
		    $("#archivesTypeID").val(archivesTypeID);
			window.qdRight.location.href = "/aiim/YJGL/YJJSAction_findArchivesInfosByArchivesTypeForSH.action?batNo=${batNo}&archivesTypeID="+archivesTypeID+"&deptType=${deptType}";	
		}

		function setSum(sum,archivesTypeID1){
			document.getElementById("sum").innerHTML = sum;
			document.getElementById("sum"+archivesTypeID1).innerHTML = sum;
	    }
		</script>		
	</head>

	<body >	
	    <form action="YJGL/YJJSAction_generateArchivesNO.action" id="form1" method="post" style="margin: 0;padding: 0;">
	      <input type="hidden" value="${batNo}" name="batNo"/>
	      <input type="hidden" name="archivesTypeID" id="archivesTypeID"/>
	    </form>
	    <s:if test="#request.deptType == 'YWZDS'">
	    <table>
	      <tr>
	        <td>
	         &nbsp;&nbsp;<input type="button" id="generate" class="button" value="生成档号"  title="点击生成档号！"/>
	         <%-- &nbsp;&nbsp;<input type="button" id="print" class="button" value="打印档号"  title="点击打印档号！"/>--%>
	        </td>
	        <td><div id="div1" style="font-size:12px; color:red; float:right; width:200px;"></div></td>
	      </tr>
	    </table>
	    </s:if>
		<table style="width: 100%;">
			<tr>
				<!--左框（移交类型列表）-->
				<td style="width: 200px; height: 600px; vertical-align: top; border: 1px #104da6 solid;">
				    <table class="tabletop" width="100%">
						<tr>
			                <td class="tableTitle">批次：${batNo}</td>
			            </tr>
					</table>
					<table style="width: 100%;" cellpadding="0px;" cellspacing="1px;" id="archivesTypeTable">
					  <tbody>
						<% 
						    String fullName = "";
							Map paperTransferBatchesArchvTypeDetails = (Map)request.getAttribute("paperTransferBatchesArchvTypeDetails");
						    Map archivesTypes = (Map)request.getAttribute("archivesTypes");
						    Integer key = 0;
						    int i=0;
						    for(Object obj : paperTransferBatchesArchvTypeDetails.values()){
						    	key = ((PaperTransferBatchesArchvTypeDetail)obj).getArchivesTypeID();
						    	fullName = SystemInitializer.getInstance().getPlaneArchivesTypes().get(key).getFullName();
						    	if(i == 0){
						    		request.setAttribute("archivesTypeID",key);
						    	}
						    	out.println("<tr style=\"background-color: #e0edff; font-size: 12px; height: 20px;\" onclick=\"clickTR(this,'"+((PaperTransferBatchesArchvTypeDetail)obj).getArchivesIDMaked()+"')\"  id=\""+key+"\">");
						    	out.println("<td><a href=\"YJGL/YJJSAction_findArchivesInfosByArchivesTypeForSH.action?batNo="+request.getAttribute("batNo")+"&archivesTypeID="+key+"&deptType="+request.getAttribute("deptType")+"\" target=\"qdRight\">"+((ArchivesType)archivesTypes.get(key)).getFullName()+"(<label id=\"sum"+key+"\">"+((PaperTransferBatchesArchvTypeDetail)obj).getTransferTotal()+"</label>)</td>");
						    	out.println("</tr>");
						    	i++;
						    }
						%>
					  </tbody>
					</table>
				</td>
				
				<!--右框（该类型清单列表）-->
				<td style="height: 555px; vertical-align: top; border: thin #104da6 solid;" id="">
				 <table class="tabletop" width="100%">
					<tr>
						<td class="tableTitle">档案列表—<%=fullName %></td>
						<td align="right" class="text">共<label id="sum"></label>条</td>
					</tr>
				 </table>
			     <iframe src="" name="qdRight" width="100%" height="100%" frameborder="0" scrolling="yes">
			      
			     </iframe>
				</td>
			</tr>			
		</table>
	</body>
</html>
