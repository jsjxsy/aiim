<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("path",basePath);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	    <base href="<%=basePath%>"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<META http-equiv="Pragma" content="no-cache">
		<title>接收审核</title>
		<link rel="stylesheet" type="text/css" href="css/common.css"/>
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript" >
		//打开审核窗口
		function showSH(batNO)
		{
			var deptType = document.getElementById("deptType").value;
			var returnValue = window.showModalDialog("<%=basePath%>YJGL/YJJSAction_findArchivesTypesByBatNo.action?batNo="+batNO+"&deptType="+deptType,window,"dialogWidth=1000px;dialogHeight=600px;center=yes;help=no;resizable=no;status=no;scroll=no;");
            if(returnValue == 1){
                window.location.reload();
            }
		}

		function init(){
			document.getElementById("label").innerHTML = window.parent.left.getLabel();
	    }
		</script>
	</head>
	<body onload="init()">
		<input type="hidden" name="preSelectRow" id="preSelectRow" />
		<input type="hidden" name="deptType" id="deptType" value="${requestScope.deptType }"/>
		<table width="100%" style="margin:0px;" align="center">
			<tr>
				<td align="center" height="25px">
					<table class="tabletop" width="100%">
						<tr>
			                <td class="tableTitle">移交批次—<label id="label"></label></td>
			                <td align="right" class="text"></td>							                	
			            </tr>
					</table>				
					<table id="showTable" cellpadding="0px" cellspacing="1px">						
						<thead class="tableHead">
							<tr>
								<th>批次号</th>
								<th>档案数量</th>
								<th>移交人</th>
								<th>移交日期</th>
								<th align="center" width="70px">操作</th>
							</tr>
						</thead>
						<tbody>
					    <s:if test="#request.paperTransferBatches == null">
						  <tr>
							<td style="font-size: 12px;color: red; background-color:#eef5ff; text-align: center;" colspan="7">${message}</td>
						  </tr>	  
						</s:if>
					     <!-- 判断是否有数据 -->
					    <s:elseif test="#request.paperTransferBatches.size == 0">
						  <tr>
							<td style="font-size: 12px;color: red; background-color:#eef5ff; text-align: center;" colspan="7">没有数据！</td>
						  </tr>	  
						</s:elseif>
					    <s:else>
						  <s:iterator value="#request.paperTransferBatches" status="status" var="p">
						    <!-- 判断行的颜色  -->
						   <s:if test="(#status.index+1)%2==0">
						       <s:set name="color" value="'#e0edff'" scope="page"></s:set>
						    </s:if>
						    <s:else>
						       <s:set name="color" value="'#eef5ff'" scope="page"></s:set>
						    </s:else>						    
						      <tr  bgcolor="${color}" id="row${status.index}" onclick="selectRow(this)">
								<td height="20px" align="center">${requestScope.p.batNo }</td>
								<td>${requestScope.p.transferTotal }</td>
								<td>${requestScope.p.batNoCreateUserName }</td>
								<td><s:date name="transferTime" format="yyyy-MM-dd"/></td>
								<td align="center">
								  <s:if test="#request.deptType == 'YWZDS'">
								      <a href="javascript:showSH('${requestScope.p.batNo}')">审核</a>
								  </s:if>
								  <s:elseif test="#request.deptType == 'DAGLS'">
								      <a href="javascript:showSH('${requestScope.p.batNo}')">归档审核</a>
								  </s:elseif>
								</td>
							</tr>
						  </s:iterator>
					    </s:else>
						</tbody>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>
