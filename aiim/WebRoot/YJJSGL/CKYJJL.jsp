<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("basePath",basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>查看移交目录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/common.js"></script>
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<style type="text/css">
	  .date{
	     width:200px;
	  }
	</style>
	
    <script type="text/javascript" >
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
				url="${basePath}js/CalendarWithFormat.html";
			}
			else {
				url="${basePath}js/CalendarWithOutFormat.html"
			}
			var retval = window.showModalDialog(url, "", "dialogWidth:197px; dialogHeight:210px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no;help=no; "  );
			if( retval != null ){
				obj.value = retval;
			}
		}
		
		 //打开移交清单页面
		function showYJQD(batNO,type)
		{ 
			<s:if test="#request.deptType == 'YWZDS'">
		       var stateType = 4;
		    </s:if>
		    <s:elseif test="#request.deptType == 'XCBM'">
		       var stateType = 2;
		    </s:elseif>
			window.showModalDialog("/aiim/YJGL/YJJSAction_findQDByBatNo.action?batNo="+batNO+"&type="+type+"&deptType=${deptType}&stateType="+stateType,"newwindow","dialogWidth=1000px;dialogHeight=600px;center=yes;help=no;resizable=no;status=no;scroll=no;");
		}

		function checkForm(){
			if(isDate($("#transferDateBegin").val()) == false){
				return false;
		    }
		    if(isDate($("#transferDateEnd").val()) == false){
		    	return false;
			}
	    }

		/*function init(){
			document.getElementById("label").innerHTML = "-"+window.parent.left.getLabel();
	    }*/
		</script>
  </head>
  
  <body>
  
    <input type="hidden" name="preSelectRow" id="preSelectRow" />
		<table width="100%" style="margin:0px;" align="center">
		     <tr>
			  <td>   
			     <table width="100%" cellpadding="0" cellspacing="0" border="0">
			       <tr>
				       <td>
                          <input type="image" src="images/find.gif" alt="显示查询(Q)" onclick="showfind(this)" onmouseover="changeImage(this,'find2.gif')" onmouseout="changeImage(this,'find.gif')" />
					   </td>
					   <td>
					   	  <div style="margin-right:2px; width:210px; float:right; color:blue; font-size:12px;"><font style="font-weight:bold;">当前位置：</font>移交管理&nbsp;>>&nbsp;查看移交记录</div >					
					   </td>
			       </tr>
			     </table>
			   </td>
			</tr>
			<tr>
			  <td align="center" id="find" style="display:none; width:100%;">
			     <fieldset>
			       <form action="YJGL/YJJSAction_findTransferOverBatches.action" method="post" name="conditionForm" style="margin: 0px; padding: 0px;" onsubmit="return checkForm()">
			       <input type="hidden" name="dataPageInfo.currentPage" id="currentPage" value="1">
			       <table style="font-size: 12px;">
			        <!--  <tr>
			           <td class="text">移交批次</td>
			           <td><input type="text" name="batNo" value="${batNo}"/></td>
			         </tr> -->
			         <tr>
			           <td class="text">移交日期</td>
			            <td>
			              <input type="text" id="transferDateBegin" name="paperTransferBatchesQueryCondition.transferDateBegin" value="<s:date name="paperTransferBatchesQueryCondition.transferDateBegin" format="yyyy-MM-dd"/>"/>
			              <img src="images/dropdownTime.gif" onclick="PopUpCalendar('transferDateBegin',true)"/>
			              &nbsp;到&nbsp;
			              <input type="text" id="transferDateEnd" name="paperTransferBatchesQueryCondition.transferDateEnd" value="<s:date name="paperTransferBatchesQueryCondition.transferDateEnd" format="yyyy-MM-dd"/>"/>
			              <img src="images/dropdownTime.gif" onclick="PopUpCalendar('transferDateEnd',true)"/>
			           </td>
			         </tr>
			         <tr>
			           <td></td>
			           <td>
			             <input type="image" src="images/search.gif" onmouseover="changeImage(this,'search2.gif')" onmouseout="changeImage(this,'search.gif')"/>
			           </td>
			         </tr>
			       </table>
			       </form>
			      </fieldset> 
			  </td>
			</tr>
			<tr>
				<td align="center" height="25px">
					<table class="tabletop" width="100%">
						<tr>
			                <td class="tableTitle">移交批次<label id="label"></label></td>
			                <td align="right" class="text">第${dataPageInfo.currentPage}页 共${dataPageInfo.pageCount}页 共${dataPageInfo.rowCount}条记录</td>							                	
			            </tr>
					</table>
					<table id="showTable" cellpadding="0px" cellspacing="1px">						
						<thead class="tableHead">
							<tr>							
								<th>序号</th>
								<th>批次号</th>
								<th>档案数量</th>
								<th>移交人</th>
								<th>移交日期</th>
								<th align="center" width="50px">操作</th>
							</tr>
						</thead>
						<tbody>						
						  <s:iterator value="#request.paperTransferBatches" status="status">
						    <s:if test="(#status.index+1)%2==0">
						       <s:set name="color" value="'#e0edff'" scope="page"></s:set>
						    </s:if>
						    <s:else>
						       <s:set name="color" value="'#eef5ff'" scope="page"></s:set>
						    </s:else>
						    <tr  bgcolor="${pageScope.color}" id="row<s:property value="#status.index+1"/>" onclick="selectRow(this)">
								<td align="center" height="20px"><s:property value="#status.index+1"/></td>
								<td><s:property value="batNo"/></td>
								<td><s:property value="transferTotal"/></td>
								<td><s:property value="batNoCreateUserName"/></td>
								<td><s:date name="transferTime" format="yyyy-MM-dd"/></td>
								<td align="center"><a href="javascript:showYJQD('<s:property value="batNo"/>','xx')">查看</a></td>	
							</tr>
						  </s:iterator> 		
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
  </body>
</html>
