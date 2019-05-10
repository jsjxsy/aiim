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
    
    <title>系统消息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

    <link rel="stylesheet" type="text/css" href="css/common.css"/>
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.form.js"></script>
	<script type="text/javascript">
	function viewDetail(ID)
	{
		var returnValue = window.showModalDialog(
				"systemMessageAction_findMessageByID.action?ID="+ID,
				window,
				"dialogWidth=480px;dialogHeight:280px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");
				if(returnValue == 1){
					window.location.reload();
				}else if(returnValue != null){
					window.top.mid.location.href = returnValue;
				}
	}
	//单击批量删除
	function clickBatchDel(){
		 if(confirm("确认删除？")){
	        $("#systemMessage").ajaxSubmit({
	       	url:"XTXX/systemMessageAction_delMessages.action",
			    success:function(data){
				    document.conditionForm.submit();
			    },
			    error:function(XMLHttpRequest, textStatus){alert(textStatus+","+XMLHttpRequest.status);}	     
		     });
	    }			 		 
	}
	
	function oneSelect(obj) {
		var elements=document.getElementsByTagName("input");
		var SelAll=document.getElementById("SelectAll");
		var iCount=0;//总数
		var iCheck=0;//选 中总数
		var objDel=document.getElementById("imgDel");//删除
		for (i=0; i<elements.length; i++) {
			if (elements[i].type=="checkbox" && elements[i].id != "SelectAll") {
				iCount++;
				if (elements[i].checked==true) {
					iCheck++;
				}
			}
		}
		if (iCount==iCheck && iCount>0) {//设置全选 状态
			SelAll.checked=true;
		}
		else {
			SelAll.checked=false;
		}
		objDel.disabled=(iCheck==0);
		changePic();
	}

	function changePic() {		
		var objDel=document.getElementById("imgDel");//删除
		if (objDel.disabled==true) {
			objDel.src="images/del3.gif";
		}
		else {
			objDel.src="images/del.gif";
		}
	}

	//全选时,控制按钮的可用/不可用状态控制
	function allSelect(obj) {
			var elements=document.getElementsByTagName("input");
			var iCount=0;
			var objDel=document.getElementById("imgDel");//删除
			
			var objDel=document.getElementById("imgDel");			
			for (i=0; i<elements.length; i++) {
				if (elements[i].type=="checkbox" && elements[i].id != obj.id) {
					iCount++;
					elements[i].checked = obj.checked;
				}
			}
			objDel.disabled=!obj.checked;
			changePic();
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
	</script>
  </head>
  
  <body>
    <input type="hidden" name="preSelectRow" id="preSelectRow" />
	<table width="100%" style="margin:0px;" align="center">
	     <tr>
		  <td>   
		     <table width="100%"  style="margin: 0px" cellpadding="0" cellspacing="0" border="0">
		       <tr>
			       <td>
                      <input type="image" id="imgDel" src="images/del3.gif"
									onmouseover="changeImage(this,'del2.gif')"
									onmouseout="changeImage(this,'del.gif')" disabled="disabled"
									onclick="clickBatchDel()" />
                      <input type="image" src="images/find.gif" onmouseover="changeImage(this,'find2.gif')" onmouseout="changeImage(this,'find.gif')" onclick="showfind(this)" alt="显示查询(Q)"/>
				   </td>
				   <td>
				   	  <div style="margin-right:2px; width:210px; float:right; color:blue; font-size:12px;"><font style="font-weight:bold;">当前位置：</font>我的工作空间&nbsp;>>&nbsp;系统消息</div >					
				   </td>
		       </tr>
		     </table>
		   </td>
		</tr>
		<tr>
		   <td id="find" style="display: none;">
		     <fieldset>
		     <form action="XTXX/systemMessageAction_findMessagesByConditions.action" name="conditionForm" id="conditionForm" method="post">
		     <input type="hidden" name="dataPageInfo.currentPage" id="currentPage" value="1">
		     <input type="hidden" name="UserID" id="UserID" value="${requestScope.UserID }">
		     <table style="font-size: 12px;" align="center">
				<tr>
					<td class="text">标题</td>
					<td>
					  <input type="text" name="systemMessageQueryCondition.msgTitle" id="msgTitle"/>
					</td>
				</tr>
				<tr>
					<td class="text">内容</td>
					<td>
					   <input type="text"  name="systemMessageQueryCondition.msgContent" id="msgContent"/>
					</td>
				</tr>
				<tr>
				    <td></td>
					<td>
					  <input type="submit" value="" class="submitButton" onmouseover="changeSubmitBgImage(this,'search2.gif')" onmouseout="changeSubmitBgImage(this,'search.gif')" onclick="javascript:document.getElementById('findDiv').style.display='none';" />
					</td>
				</tr>
			</table>
			</form>
			</fieldset>
		   </td>
		</tr>
	</table>
			<table class="tabletop" width="100%" style="margin: 0px;margin-bottom: 0px;margin-left: 0px;margin-right: 0px;"  cellpadding="0px" cellspacing="1px">
				<tr>
	                <td class="tableTitle">系统消息</td>
	                <td align="right"  class="text" >
						<label style="margin-right:4px" id="rsInfo">第${dataPageInfo.currentPage}页 共${dataPageInfo.pageCount}页 共${dataPageInfo.rowCount}条记录</label>
					</td>							                	
	            </tr>
			</table>	 
			<form  name="systemMessage" id="systemMessage" method="post" style="margin: 0px">			
				<table id="showTable" style="margin:0px;margin-top: 0px;" cellpadding="0px" cellspacing="1px">						
					<thead class="tableHead">
						<tr>
							<th width="35px">选择</th>								
							<th style="width:30px;">序号</th>
							<th>标题</th>								
							<th>消息内容</th>
							<th>发言时间</th>
							<th>状态</th>
							<th align="center"  width="60px" >详细内容</th>
						</tr>
					</thead>
					<tbody>
					<s:iterator var="systemMessage" value="#request.systemMessages" status="u">
						<tr bgcolor="#e0edff" id="row">
							<td align="center"><input type="checkbox" name="IDS" id="IDS" value="<s:property value="ID"/>" onclick="oneSelect(this)"/></td>
							<td align="center" ><s:property value="#u.index+1"/></td>
							<td><s:property value="msgTitle"/></td>
							<td><s:property value="msgContent"/></td>
							<td><s:date name="#request.systemMessage.sendTime" format="yyyy-MM-dd"/></td>
							<td>
							<s:if test="readFlag">
							已阅
							</s:if>
							<s:else>
							未阅
							</s:else>
							</td>
							<td align="center"><a href="javascript:viewDetail(<s:property value="ID"/>);">查看</a></td>
						</tr>
					</s:iterator>
					</tbody>
				</table>
				</form>

		<table width="100%" style="font-size: 12px;">
			<tr>
				<td>
					&nbsp;&nbsp;&nbsp;
					<input type="checkbox" id="SelectAll" accesskey="S"  name="SelectAll" onclick="allSelect(this)" title="选中/取消 所有记录(S)"  /><label for="SelectAll">全选</label>
				</td>
				<td align="right" style="width: 100px; vertical-align: bottom;">
					<s:if test="#request.dataPageInfo.previousState=='enable'">
						<a href="javascript:pageTurning('conditionForm','1')"
							style="text-decoration: none;"> <image
								src="images/firsts.gif"
								onmouseover="changeImage(this,'firsts1.gif')"
								onmouseout="changeImage(this,'firsts.gif')" alt="第一页" /> </a>
						<a
							href="javascript:pageTurning('conditionForm',${dataPageInfo.currentPage-1})"
							style="text-decoration: none;"> <image
								src="images/previouss.gif"
								onmouseover="changeImage(this,'previouss1.gif')"
								onmouseout="changeImage(this,'previouss.gif')" alt="上一页" /> </a>
					</s:if>
					<s:elseif test="#request.dataPageInfo.previousState =='disable'">
						<image src="images/firsts2.gif" alt="已经是第一页" />
						<image src="images/previouss2.gif" alt="已经是上一页" />
					</s:elseif>
					<s:if test="#request.dataPageInfo.nextState=='enable'">
						<a
							href="javascript:pageTurning('conditionForm',${dataPageInfo.currentPage+1})"
							style="text-decoration: none;"> <image src="images/nexts.gif"
								onmouseover="changeImage(this,'nexts1.gif')"
								onmouseout="changeImage(this,'nexts.gif')" alt="下一页" /> </a>
						<a
							href="javascript:pageTurning('conditionForm',${dataPageInfo.pageCount})"
							style="text-decoration: none;"> <image src="images/lasts.gif"
								onmouseover="changeImage(this,'lasts1.gif')"
								onmouseout="changeImage(this,'lasts.gif')" alt="最后一页" /> </a>
					</s:if>
					<s:if test="#request.dataPageInfo.nextState=='disable'">
						<image src="images/nexts2.gif" alt="已经是最后一页" />
						<image src="images/lasts2.gif" alt="已经是最后一页" />
					</s:if>
				</td>
				<td style="width: 100px; font-size: 12px;">
					转到第
					<input type="text" name="gotoPage" id="gotoPage"
						style="width: 18px; height: 18px" />
					页
				</td>
				<td style="width: 20px; vertical-align: bottom;">
					<input type="image" src="images/gos.gif"
						onmouseover="changeImage(this,'gos2.gif')"
						onmouseout="changeImage(this,'gos.gif')"
						onclick="gotoPage('conditionForm')" />
				</td>
			</tr>
		</table>
  </body>
</html>
