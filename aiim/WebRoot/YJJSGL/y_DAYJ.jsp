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
  
    <title>档案移交--业务指导室</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.form.js"></script>
    <script type="text/javascript" >
        <%--//打开日期输入页面
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
		}--%>
		//打开当前的移交清单
		function findCurrentQD(type)
		{
			var returnValue = window.showModalDialog("/aiim/YJGL/YJJSAction_findCurrentQD.action?type="+type+"&deptType=YWZDS&stateType=3","newwindow","dialogWidth=1000px;dialogHeight=600px;center=yes;help=no;resizable=no;status=no;scroll=no;");
			if(returnValue == 1){
				window.location.reload();
		    }
		}
		//打开移交清单页面
		function showYJQD(batNO,type)
		{ 
			var returnValue = window.showModalDialog("/aiim/YJGL/YJJSAction_findQDByBatNo.action?batNo="+batNO+"&type="+type+"&deptType=XCBM&stateType=3","newwindow","dialogWidth=1000px;dialogHeight=600px;center=yes;help=no;resizable=no;status=no;scroll=no;");
			if(returnValue == 1){
				window.location.reload();
		    }
		}
		
		//加入移交清单
		function addToYJList(){
       $("#form1").ajaxSubmit({
    	   beforeSubmit:function(){
	          if($("#showTable").children("tbody").children().children(":nth-child(1)").children(":checked").length == 0){
                 alert("请选择要移交的批次！");
                 return false;
			  }
	        }, 
	        success:function(data){
	          if(data != null && data != ""){
		          alert(data);
			  }else{
				  alert("添加到移交清单成功！");
				  window.location.reload();
		      }
	        },
	        error:function(XMLHttpRequest, textStatus){alert(textStatus+","+XMLHttpRequest.status+","+XMLHttpRequest.responseText);}
       });
	}
		<%--dojo.addOnLoad(function(){
		  document.getElementById("deptId").value = window.parent.left.selectedNode.getKey();
		  dojo.event.topic.subscribe("addToQD",this,function(date,type,e){
		    if(type == 'load'){
               alert(date);
               document.form2.submit();
		    }
		  });
		});--%>
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
                         <%--<input type="image" src="images/find.gif" alt="显示查询(Q)" onclick="showfind(this)" onmouseover="changeImage(this,'find2.gif')" onmouseout="changeImage(this,'find.gif')" /> --%>
					    <input type="image" src="images/YJview.gif" onclick="getDQ();" onmouseover="changeImage(this,'YJview2.gif')" onmouseout="changeImage(this,'YJview.gif')" />
					   </td>
					   <td>
					   	  <div style="margin-right:2px; width:200px; float:right; color:blue; font-size:12px;"><font style="font-weight:bold;">当前位置：</font>移交管理&nbsp;>>&nbsp;档案移交</div >					
					   </td>
			       </tr>
			     </table>
			   </td>
			</tr>
			<%--<tr>
			  <td align="center" id="find" style="display:none;width:100%;">
			     <fieldset>
			       <form id="form2" name="form2" action="YJGL/YJJSAction_findExamineOverBatByDept.action" method="post" style="margin: 0px; padding: 0px;">
			       <input type="hidden" name="dataPageInfo.currentPage" id="currentPage" value="1">
			       <input type="hidden" name="nodeId" id="deptId">
			       <table style="font-size: 12px;">
			           <tr>
			           <td class="text">移交批次</td>
			           <td><input type="text" name="batNo" value="${batNo}"/></td>
			         </tr>
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
			--%>
			<tr>
				<td align="center" height="25px">
					<table class="tabletop" width="100%">
						<tr>
			                <td class="tableTitle">移交批次—所有</td>
			                <td align="right" class="text"><%--第${dataPageInfo.currentPage}页 共${dataPageInfo.pageCount}页 共${dataPageInfo.rowCount}条记录--%></td>							                	
			            </tr>
					</table>
					<form id="form1" name="form1" action="YJGL/YJJSAction_insertBatIntoNew.action" method="post" style="margin: 0px; padding: 0px;">			
					<table id="showTable" cellpadding="0px" cellspacing="1px">						
						<thead class="tableHead">
							<tr>
								<th width="40px">选择</th>								
								<th>序号</th>
								<th>批次号</th>
								<th>档案数量</th>
								<th>移交人</th>
								<th>移交日期</th>
								<th>接收人</th>
								<th>接收日期</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
						   <s:if test="#request.paperTransferBatches == null">
						      <tr>
								<td style="font-size: 12px;color: red; background-color:#eef5ff; text-align: center;" colspan="7">${requestScope.message}</td>
							  </tr>	
						   </s:if>
						   <s:else>
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
								        <td align="center"><input type="checkbox" name="batNos" value="${requestScope.p.batNo }"/></td>
								        <td>${status.index+1}</td>
										<td height="20px" align="center">${requestScope.p.batNo }</td>
										<td>${requestScope.p.transferTotal }</td>
										<td>${requestScope.p.batNoCreateUserName }</td>
										<td><s:date name="transferTime" format="yyyy-MM-dd"/></td>
										<td>${requestScope.p.receiveUserName }</td>
										<td><s:date name="receiveTime" format="yyyy-MM-dd"/></td>
										<td align="center">
										  <a href="javascript:showYJQD('${requestScope.p.batNo }','xx')">详细</a>&nbsp;&nbsp;
										</td>
									  </tr>
								    </s:else>
								 </s:iterator>
						   </s:else>  					
						</tbody>
					</table>
					</form>
					<table width="100%" style="font-size: 12px;">
						 <tr>
							<td>
							    &nbsp;&nbsp;&nbsp;<input type="checkbox" onclick="selectAll(this)" id="SelectAll" accesskey="S"  name="SelectAll" title="选中/取消 所有记录(S)" />全选
							    &nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:addToYJList();">加入移交清单</a>
                                &nbsp;&nbsp;&nbsp;&nbsp;<s:if test="#request.sum == 0"><a href="javascript:alert('当前没有清单！')" >清单档案数量(${requestScope.sum})</a></s:if>
                                           <s:else><a href="javascript:findCurrentQD('yj');" >清单档案数量(${requestScope.sum})</a></s:else>
							</td>
							<%--<td align="right" style=" width: 100px; vertical-align: bottom;">
							   <s:if test="#request.dataPageInfo.previousState=='enable'" >
									<a href="javascript:pageTurning('form2','1')" style="text-decoration: none;">
									   <image src="images/firsts.gif" onmouseover="changeImage(this,'firsts1.gif')" onmouseout="changeImage(this,'firsts.gif')" alt="第一页"/>
									</a>
									<a href="javascript:pageTurning('form2',${dataPageInfo.currentPage-1})" style="text-decoration: none;">	
									   <image src="images/previouss.gif" onmouseover="changeImage(this,'previouss1.gif')" onmouseout="changeImage(this,'previouss.gif')" alt="上一页"/>
									</a>
								</s:if>
								<s:elseif test="#request.dataPageInfo.previousState =='disable'">
								   <image src="images/firsts2.gif" alt="已经是第一页"/>
								   <image src="images/previouss2.gif" alt="已经是上一页"/>
								</s:elseif>
								<s:if test="#request.dataPageInfo.nextState=='enable'">
									<a href="javascript:pageTurning('form2',${dataPageInfo.currentPage+1})" style="text-decoration: none;">
									   <image src="images/nexts.gif" onmouseover="changeImage(this,'nexts1.gif')" onmouseout="changeImage(this,'nexts.gif')" alt="下一页"/>
									</a>
									<a href="javascript:pageTurning('form2',${dataPageInfo.pageCount})" style="text-decoration: none;">
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
								<input type="image" src="images/gos.gif" onmouseover="changeImage(this,'gos2.gif')" onmouseout="changeImage(this,'gos.gif')" onclick="gotoPage('form2')"/>                                           
							</td>
						--%>
						</tr>
					</table>
				</td>
			</tr>
		</table>
  </body>
</html>
