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
		<title>接收登记</title>
		<link rel="stylesheet" type="text/css" href="css/common.css"/>
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript" >
		//打开登记
		function showDJ()
		{
			window.showModalDialog("${path}YJJSGL/dlg_JSDJ.jsp","newwindow","dialogWidth=700px;dialogHeight=315px;center=yes;help=no;resizable=no;status=no;scroll=no;");
		}
		//打开登记窗口
		function showDJByBatNo(batNO)
		{
			var refresh = window.showModalDialog("${path}YJGL/YJJSAction_findByBatNO.action?batNo="+batNO,window,"dialogWidth=700px;dialogHeight=315px;center=yes;help=no;resizable=no;status=no;scroll=no;");
		    if(refresh == 1){
		       window.location.reload();
		    }
		}

       //打开移交清单页面
		function showYJQD(batNO,type)
		{ 
			window.showModalDialog("YJGL/YJJSAction_findQDByBatNO.action?batNo="+batNO+"&type="+type,"newwindow","dialogWidth=1000px;dialogHeight=600px;center=yes;help=no;resizable=no;status=no;scroll=no;");
		}

		function init(){
			document.getElementById("label").innerHTML = window.parent.left.getLabel();
	    }
		</script>
	</head>
	<body onload="init()">
		<input type="hidden" name="preSelectRow" id="preSelectRow" />
		<table width="100%" style="margin:0px;" align="center">
		     <tr>
			  <td>   
			     <table width="100%" cellpadding="0" cellspacing="0" border="0">
			       <tr>
				       <td>
                          <input type="image" src="images/dengji.png"  onclick="showDJ();" onmouseover="changeImage(this,'dengji2.png')" onmouseout="changeImage(this,'dengji.png')" />
					   </td>
					   <td>
					   	  <div style="margin-right:2px; width:200px; float:right; color:blue; font-size:12px;"><font style="font-weight:bold;">当前位置：</font>移交管理&nbsp;>>&nbsp;接收登记</div >					
					   </td>
			       </tr>
			     </table>
			   </td>
			</tr>
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
						  <s:iterator value="#request.paperTransferBatches" status="status" var="p">
						    <!-- 判断行的颜色  -->
						   <s:if test="(#status.index+1)%2==0">
						       <s:set name="color" value="'#e0edff'" scope="page"></s:set>
						    </s:if>
						    <s:else>
						       <s:set name="color" value="'#eef5ff'" scope="page"></s:set>
						    </s:else>
						     <!-- 判断是否有数据 -->
						    <s:if test="#request.paperTransferBatches.size == 0">
							  <tr>
								<td style="font-size: 12px;color: red; background-color:#eef5ff; text-align: center;" colspan="7">没有数据！</td>
							  </tr>	  
							</s:if>
						    <s:else>
						      <tr  bgcolor="${color}" id="row${status.index}" onclick="selectRow(this)">
								<td height="20px" align="center">${requestScope.p.batNo }</td>
								<td>${requestScope.p.transferTotal }</td>
								<td>${requestScope.p.batNoCreateUserName }</td>
								<td><s:date name="transferTime" format="yyyy-MM-dd"/></td>
								<td align="center">
								  <!-- <a href="javascript:showYJQD('${requestScope.p.batNo }','xx')">详细</a>&nbsp;&nbsp; -->
								  <a href="javascript:showDJByBatNo('${requestScope.p.batNo }');">登记</a>
								</td>
							</tr>
						    </s:else>
						  </s:iterator>
						</tbody>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>
