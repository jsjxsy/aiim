<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="taglib.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	 <base href="<%=basePath%>">
	<title>任务详细</title>
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="Cache-Control" content="no-cache,  must-revalidate" />
	
	<link href="css/Styles.css" type="text/css" rel="stylesheet" />
	<link href="css/common.css" type="text/css" rel="stylesheet" />
	<link href="css/Login.css" type="text/css" rel="stylesheet" />
	
	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/dateTool.js"></script>
	<script type="text/javascript" src="js/popup.js"></script>
		<style type="text/css">
		 .tableHead{
	font-weight:bold; text-align:center; padding:4px 0 0 0px;
}
.tableTitle {font-weight:bold; text-align:left; padding:4px 0 0 5px;}
/*设置标题底色*/
.bgTitle { background-color:#a3c9ff;height:25px;}
/*设置表格顶部框底色*/
.borderTop {	border-top:#104da6 1px solid;	border-left:#104da6 1px solid;	border-right:#104da6 1px solid;}
.tableHead{
	font-weight:bold; text-align:center; padding:4px 0 0 0px;
}
.tbody tr{
	 background-color:#e0edff;
}

		</style>
		<script type="text/javascript">
		  function showXX(i){
		     var text = document.getElementById('a'+i).innerHTML;
		     if(text =="展开"){
		        document.getElementById('a'+i).innerHTML="收起"; 
		        document.getElementById('ZRR'+i).style.display="block";
		     }if(text =="收起"){
		       document.getElementById('a'+i).innerHTML="展开"; 
		        document.getElementById('ZRR'+i).style.display="none";
		     }
		  }
		  function showPJ(i){
		  var text = document.getElementById('a'+i).innerHTML;
		  if(text =="收起"){
		      document.getElementById('pj'+i).style.display="block";
		    }
		  }
		</script>
	</head>
	<body class="bg_color" style="margin-top:4px">
						<table align="center" class="back_border" width="98%"  cellpadding="0" cellspacing="0">
						    <tr>
						        <td class="bg_title bg_title4" align="center">&nbsp;任务详情信息</td>
						    </tr>
						    <tr>
						        <td>
						            <table width="100%">
								        <tr>
									        <td class="text">&nbsp;任务主题：</td>
									        <td align="left">
										        <input  id="user_id" class="back_border" type="text" name="title" value="${taskInfo.title}" readonly="readonly"/></td>
									        <td class="text">&nbsp;&nbsp;发&nbsp;布&nbsp;人：</td>
									        <td align="left">
										        <input id="user_name" type="text" class="back_border" value="${taskInfo.fromUserName}" readonly="readonly"/></td>
								        </tr>
								        <tr>
									        <td class="text">&nbsp;&nbsp;接&nbsp;收&nbsp;人：</td>
									        <td align="left">
										        <input  id="receiveName" name="receiveName" class="back_border" type="text" value="AA" readonly="readonly"/></td>
									        <td class="text">&nbsp;发布时间：</td>
									        <td align="left">
										       <input  id="s_Date" class="back_border" type="text" value="<fmt:formatDate value="${taskInfo.publishTime}" type="both"/>" readonly="readonly"/>
										    </td>
										</tr>
								        <tr>
									        <td class="text">&nbsp;任务内容：</td>
									        <td align="left" colspan="3">
									           <textarea rows="4" style="width:90%; background-color:#eeeeee;" readonly="readonly">${taskInfo.content}</textarea>
									        </td>
										</tr>
									</table>
						        </td>
						    </tr>
						    <tr class="bg_title bg_title4" align="left">
						      <td>
						        <table width="100%">
						        <tr>
						         <td width="95%">
						            任务发布人：${taskInfo.fromUserName}</td>
						         <td>
						            <a href="javascript:showXX(0);" id="a0">展开</a>
						         </td>
						        </tr> 
						       </table>
						      </td>
						    </tr>
						    <tr>
						      <td height="3px">
						       <table width="100%" id="ZRR0" style="display:none">
						       		
						       		<!-- 任务发布人的回复循环显示 -->
							       <c:forEach items="${taskInfo.ownTaskResponses}" var="ownTaskResponse">
								        <tr>
								          <td style="width:70px;"> 回复内容：</td>
								          <td>${ownTaskResponse.responseContent}&nbsp;&nbsp;&nbsp;&nbsp;时间：<fmt:formatDate value="${ownTaskResponse.responseTime}" type="date"/></td>
								        </tr>
								        <tr>
								         <td colspan="2" height="2px"><hr/></td>
								        </tr>
							        </c:forEach>
							        
						        </table>
						       </td>
						    </tr>
						    
						   <!-- 任务接收人循环显示 --> 
					<c:forEach items="${taskInfo.taskPersons}" var="taskPerson" varStatus="stau">
						    <tr class="bg_title bg_title4" align="left">
						      <td>
						        <table width="100%">
						        <tr>
						         <td width="95%"> 
						            责任人${stau.count}：${taskPerson.userName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;查阅时间：2010-03-28&nbsp;&nbsp;10:20:32&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:showPJ(1);">评价</a>
						         </td>
						         <td>
						            <a href="javascript:showXX('${stau.count}');" id="a${stau.count}">展开</a>
						         </td>
						        </tr> 
						       </table>
						      </td>
						    </tr>
						    <tr>
						      <td height="3px">
						       <table width="100%" id="ZRR${stau.count}" style="display:none">
						       
						       <!-- 回复内容循环显示 -->
						       <c:forEach items="${taskPerson.taskResponses}" var="taskRespons">
						        <tr>
						          <td style="width:70px;"> 回复内容：</td>
						          <td>${taskRespons.responseContent}&nbsp;&nbsp;&nbsp;&nbsp;时间：<fmt:formatDate value="${taskRespons.responseTime}" type="date"/></td>
						        </tr>
						        <tr>
						         <td colspan="2" height="2px"><hr/></td>
						        </tr>
						        </c:forEach>
						        <tr>
						          <td colspan="2">
						             <table>
						             <tr>
						               <td rowspan="2" style="color:red;">领导评语1：</td>
						               <td>1、ABCDEFGHIJKLMNOPQRSTUVWXYZ</td>
						             </tr>
						             <tr>
						                 <td>2、ABCDEFGHIJKLMNOPQRSTUVWXYZ</td>
						             </tr>
						           </table>
						          </td>
						        </tr>
						      
						        <tr id="pj1" style="display:none;">
						          <td></td>
						          <td>
						              <textarea rows="2" cols="90%" style="background-color:#eeeeee;"></textarea><br/>
						              <input type="button" class="button15" value="提    交"/>
						          </td>
						        </tr>
						        </table>
						       </td>
						    </tr>
						</c:forEach>
						
						    <tr class="bg_title bg_title4" align="left">
						      <td align="left"> 我来说两句： </td>
						    </tr>
						    <tr>
						      <td>
						       <table width="100%">
						        <tr>
						          <td style="width:70px;"> 内容：</td>
						          <td><textarea rows="3" cols="90%" style="background-color:#eeeeee;"></textarea></td>
						        </tr>
						        <tr>
						          <td colspan="2" align="center">
						            <input type="button" class="button15" value="提     交"/>&nbsp;&nbsp;&nbsp;&nbsp;
						            <input type="button" class="button15" value="重     填"/>
						          </td>
						        </tr>
						       </table>
						       </td>
						    </tr>
						</table>
		</body>
</html>

