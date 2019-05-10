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

		<title>关联档案条码</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<link href="css/common.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript">
           //打开日期输入页面
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
					url="j<%=basePath%>js/CalendarWithOutFormat.html"
				}
				var retval = window.showModalDialog(url, "", "dialogWidth:197px; dialogHeight:210px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no;help=no; "  );
				if( retval != null ){
					obj.value = retval;
				}
			}
			
			$(document).ready(function(){
			   $("#userId").val(window.parent.left.selectedNode.getKey());
			});
			
			//打开关联窗口
			function openGL(batNO)
			{
				window.showModalDialog("/aiim/YJGL/YJJSAction_findArchivesTypesByBatNoForGL.action?batNo="+batNO,window,"dialogWidth=1000px;dialogHeight=600px;center=yes;help=no;resizable=no;status=no;scroll=no;");
			}

			function checkForm(){
				if(isDate($("#transferDateBegin").val()) == false){
					return false;
			    }
			    if(isDate($("#transferDateEnd").val()) == false){
			    	return false;
				}
		    }

			function init(){
				document.getElementById("label").innerHTML = window.parent.left.getLabel();
		    }
        </script>
	</head>

	<body onload="init()">
        <input type="hidden" name="preSelectRow" id="preSelectRow" />
		<table width="100%" style="margin:0px;" cellspacing="0" cellpadding="0">
			<tr>
				<td>
				   <table width="100%" cellpadding="0" cellspacing="0" border="0">
				       <tr>
					       <td>
	                          <input type="image" src="images/find.gif" alt="显示查询(Q)" onclick="showfind(this)" onmouseover="changeImage(this,'find2.gif')" onmouseout="changeImage(this,'find.gif')" />
						   </td>
						   <td>
				   	          <div style="margin-right:2px; width:210px; float:right; color:blue; font-size:12px;"><font style="font-weight:bold;">当前位置：</font>移交管理&nbsp;>>&nbsp;关联档案条码</div >					
						   </td>
				       </tr>
			      </table>
			   </td>
			</tr>
			<tr>
			  <td align="center" style="display:none;width:100%;" id="find">
			      <form action="YJGL/YJJSAction_findRegisterOverBatsByInside.action" name="conditionForm" method="post" style="margin: 0;padding: 0;" onsubmit="return checkForm()">
			      <input type="hidden" name="nodeId" id="userId"/>
			      <input type="hidden" name="dataPageInfo.currentPage" id="currentPage" value="1"/>
			      <fieldset>
			       <table class="findTB">
			         <%--<tr>
			           <td class="text">接收人：</td>
			           <td><input type="text"/></td>
			         </tr>
			         <tr>
			           <td class="text">移交人：</td>
			           <td><input type="text"/></td>
			         </tr>
			         --%><tr>
			           <td class="text">移交日期：</td>
			           <td class="text">
			             <input type="text" id="transferDateBegin" name="paperTransferBatchesQueryCondition.transferDateBegin" value="<s:date name="paperTransferBatchesQueryCondition.transferDateBegin" format="yyyy-MM-dd"/>"/>
			              <img src="images/dropdownTime.gif" onclick="PopUpCalendar('transferDateBegin',true)"/>
			              &nbsp;到&nbsp;
			              <input type="text" id="transferDateEnd" name="paperTransferBatchesQueryCondition.transferDateEnd" value="<s:date name="paperTransferBatchesQueryCondition.transferDateEnd" format="yyyy-MM-dd"/>"/>
			              <img src="images/dropdownTime.gif" onclick="PopUpCalendar('transferDateEnd',true)"/>
			           </td>
			         </tr>
			         <tr>
			           <td></td>
			           <td align="left"><input type="image" src="images/search.gif" onmouseover="changeImage(this,'search2.gif')" onmouseout="changeImage(this,'search.gif')"/></td>
			         </tr>
			       </table>
			      </fieldset>
			      </form>
			  </td>
			</tr>
			<tr>
				<td>
		          <table width="100%" cellspacing="0" cellpadding="0" style="margin:0px;" align="center">
					<tr class="bgTitle">
						<td style="height:25px" class="borderTop">
							<table class="tabletop" width="100%" cellspacing="0" cellpadding="0">
					            <tr>
					                <td class="tableTitle">未关联条码批次 -<label id="label"></label></td>
					                <td align="right"  class="text" >第${dataPageInfo.currentPage}页 共${dataPageInfo.pageCount}页 共${dataPageInfo.rowCount}条记录 </td>							                	
					            </tr>
						    </table>							
							<table id="showTable" width="100%" align="center" style="margin:0px; border: #104da6 1px solid;" cellspacing="1px" cellpadding="0px" >						
								<thead class="tableHead">
									<tr class="bgTitle">
										<th>批次号</th>
										<th>档案数量</th>
										<th>移交人</th>
										<th>移交日期</th>
										<th>接收人</th>
										<td>接收日期</td>
										<th align="center" width="60px">操作</th>
									</tr>
								</thead>
								<tbody>
								    <s:if test="#request.paperTransferBatches == null">
								        <tr  bgcolor="#eef5ff">
											<td align="center" colspan="7" style="color: red;font-size: 12px;">${requestScope.message }</td>
										</tr>
								    </s:if>
								    <s:elseif test="#request.paperTransferBatches.size == 0">
								        <tr  bgcolor="#eef5ff">
											<td align="center" style="color: red;font-size: 12px;" colspan="7">没有数据！</td>
										</tr>
								    </s:elseif>
								    <s:else>
								         <s:iterator value="#request.paperTransferBatches" status="status">
								            <s:if test="#status.odd">
								               <s:set scope="page" name="color" value="'#eef5ff'"></s:set>
								            </s:if>
								            <s:else>
								               <s:set scope="page" name="color" value="'#e0edff'"></s:set>
								            </s:else>
								            <tr  bgcolor="${color}"  id="row<s:property value="#status.index"/>" onclick="selectRow(this)">
												<td align="center" style="height: 20px"><s:property value="batNo"/></td>
												<td><s:property value="transferTotal"/></td>
												<td><s:property value="batNoCreateUserName"/></td>
												<td><s:date name="transferTime" format="yyyy-MM-dd"/></td>
												<td><s:property value="receiveUserName"/></td>
												<td><s:date name="receiveTime" format="yyyy-MM-dd"/></td>
												<td align="center"><a href="javascript:openGL('<s:property value="batNo"/>');">关联条码</a></td>
											</tr>
								         </s:iterator>
								    </s:else>									
								</tbody>
							</table>
							 <table width="100%" style="font-size: 12px;">
								 <tr>
								    <td></td>
									<td align="right" style=" width: 100px; vertical-align: bottom;">
									   <s:if test="#request.dataPageInfo.previousState=='enable'" >
											<a href="javascript:pageTurning('conditionForm','1')" style="text-decoration: none;">
											   <image src="images/firsts.gif" onmouseover="changeImage(this,'firsts1.gif')" onmouseout="changeImage(this,'firsts.gif')" alt="第一页"/>
											</a>
											<a href="javascript:pageTurning('conditionForm',${dataPageInfo.currentPage-1})" style="text-decoration: none;">	
											   <image src="images/previouss.gif" onmouseover="changeImage(this,'previouss1.gif')" onmouseout="changeImage(this,'previouss.gif')" alt="上一页"/>
											</a>
										</s:if>
										<s:elseif test="#request.dataPageInfo.previousState =='disable'">
										   <image src="images/firsts2.gif" alt="已经是第一页"/>
										   <image src="images/previouss2.gif" alt="已经是上一页"/>
										</s:elseif>
										<s:if test="#request.dataPageInfo.nextState=='enable'">
											<a href="javascript:pageTurning('conditionForm',${dataPageInfo.currentPage+1})" style="text-decoration: none;">
											   <image src="images/nexts.gif" onmouseover="changeImage(this,'nexts1.gif')" onmouseout="changeImage(this,'nexts.gif')" alt="下一页"/>
											</a>
											<a href="javascript:pageTurning('conditionForm',${dataPageInfo.pageCount})" style="text-decoration: none;">
											    <image src="images/lasts.gif" onmouseover="changeImage(this,'lasts1.gif')" onmouseout="changeImage(this,'lasts.gif')" alt="最后一页"/>
										    </a>
									    </s:if>
									    <s:if test="#request.dataPageInfo.nextState=='disable'">
										   <image src="images/nexts2.gif" alt="已经是最后一页"/>
										   <image src="images/lasts2.gif" alt="已经是最后一页"/>
									    </s:if>
									</td>
									<td style="width: 70px;font-size: 12px;">	
										转到第<input type="text" name="gotoPage" style="width:18px; height:18px"/>页
									</td>
									<td style="width: 15px; vertical-align: bottom;">
										<input type="image" src="images/gos.gif" onmouseover="changeImage(this,'gos2.gif')" onmouseout="changeImage(this,'gos.gif')" onclick="gotoPage('conditionForm')"/>                                           
									</td>
								</tr>
							</table>		
						</td>
					</tr>
				</table>
			</td>
		</tr>
		</table>			
	</body>
</html>
