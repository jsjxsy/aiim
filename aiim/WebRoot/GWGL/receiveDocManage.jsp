<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>收文管理</title>
    
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
				url="<%=basePath%>js/CalendarWithOutFormat.html"
			}
			var retval = window.showModalDialog(url, "", "dialogWidth:197px; dialogHeight:210px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no;help=no; "  );
			if( retval != null ){
				obj.value = retval;
			}
		 }
      //打开新增对话框
		function addTemplate()
		{
			window.showModalDialog("addTemplate.jsp","newwindow","dialogWidth=540px;dialogHeight:150px;center=yes;help=no;resizable=no;status=no;scroll=no;");
		}
		
		//对发文进行归档
		function archiving(){
			window.showModalDialog("archiving.jsp","newwindow","dialogWidth=250px;dialogHeight:60px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");
		
		}
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
                         <input type="image" src="images/new.gif"  onclick="showAdd();" onmouseover="changeImage(this,'new2.gif')" onmouseout="changeImage(this,'new.gif')"  />
						 <input type="image" src="images/del.gif" disabled="disabled" />
						 <input type="image" src="images/edit.gif" disabled="disabled" />
						 <input type="image" src="images/find.gif" alt="显示查询(Q)" onmouseover="changeImage(this,'find2.gif')" onmouseout="changeImage(this,'find.gif')" onclick="showfind(this)" />
						 <input type="image" src="images/printing.gif" onmouseover="changeImage(this,'printing2.gif')" onmouseout="changeImage(this,'printing.gif')" onclick="alert('打印中...')" />					
						 <input type="image" src="images/archive.gif" onmouseover="changeImage(this,'archive2.gif')" onmouseout="changeImage(this,'archive.gif')" onclick="archiving()"/>
					   </td>
					   <td>
					   	  <div style="margin-right:2px; width:200px; float:right; color:blue; font-size:12px;"><font style="font-weight:bold;">当前位置：</font>公文管理&nbsp;>>&nbsp;文档中心</div >					
					   </td>
			       </tr>
			     </table>
			   </td>
			</tr>
			<tr>
			   <td align="center" id="find" style="display:none; width:100%;">
			     <fieldset>
			       <form action="GWGL/officeDocAction_findDoc.action" method="post" name="conditionForm" style="margin: 0px; padding: 0px;">
			       <input type="hidden" name="type" value="SW"/>
			       <input type="hidden" name="dataPageInfo.currentPage" id="currentPage" value="1">
			       <table class="findTB"  >
						<tr style="height: 26px;" align="left">
							<td class="text">文号</td>
							<td><input type="text" style="width:310px;" /></td>
						</tr>
						<tr style="height: 26px" align="left">
							<td class="text">题名</td>
							<td><input type="text" style="width: 310px;"/></td>
						</tr>
						<tr style="height: 26px">
							<td class="text">责任者</td>
							<td>
								<select style="width: 316px">
									<option></option>
									<option>组织部</option>
									<option>党办</option>
									<option>校办</option>
								</select>
							</td>
						</tr>
						<tr style="height: 26px" align="left">
							<td class="text">发文日期</td>
							<td>
								<input type="text" id="bmgdDate" style="  width: 118px;"/>
								<input  type="image" style="padding-right:10px;" src="images/dropdownTime.gif" onclick="PopUpCalendar('bmgdDate',true);return false;"  />至
								<input type="text" id="bmgdDate2" style=" margin-left:5px; width: 118px;"/>
								<input  type="image" style="padding-right:10px;" src="images/dropdownTime.gif" onclick="PopUpCalendar('bmgdDate2',true);return false;"  />
							</td>
						</tr>
				        <tr style="height:40px;">
				         	<td></td>
				            <td align="center">
				               <input type="submit" class="button" value="查    询" />
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
			                <td class="tableTitle">公文模板—公告</td>
			                <td align="right" class="text">第${dataPageInfo.currentPage}页 共${dataPageInfo.pageCount}页 共${dataPageInfo.rowCount}条记录</td>							                	
			            </tr>
					</table>				
					<table id="showTable" cellpadding="0px" cellspacing="1px">						
						<thead class="tableHead">
							<tr>
								<th width="35px">选择</th>								
								<th style="width:30px;">序号</th>
								<th>文号</th>								
								<th>题名</th>								
								<th>责任者</th>
								<th>密级</th>
								<th>保管期限</th>
								<th>发文日期</th>
								<th align="center" style="width: 35px">原文</th>
							</tr>
						</thead>
						<tbody>
						<form action="GWGL/officeDocAction_delTemplates.action" method="post" name="delForm" id="delForm" style="margin: 0;padding: 0;">
						    <input type="hidden" name="type"/>
						    <s:if test="#request.paperTransferBatches == null">
						        <tr  bgcolor="#eef5ff">
									<td align="center" colspan="9" style="color: red;font-size: 12px;">${requestScope.message }</td>
								</tr>
						    </s:if>
						    <s:elseif test="#request.paperTransferBatches.size == 0">
						        <tr  bgcolor="#eef5ff">
									<td align="center" style="color: red;font-size: 12px;" colspan="9">没有数据！</td>
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
						            <tr  bgcolor="${color}"  id="row<s:property value="#status.index"/>" onclick="selectRow(this)" title="双击查看" ondblclick="showItem()">
										<td align="center" style="height: 20px"><s:property value="batNo"/></td>
										<td><s:property value="receiveUserName"/></td>
										<td><s:property value="receiveUserName"/></td>
										<td><s:date name="receiveTime" format="yyyy-MM-dd"/></td>
										<td><s:property value="receiveUserName"/></td>
										<td><s:property value="receiveUserName"/></td>
										<td><s:date name="receiveTime" format="yyyy-MM-dd"/></td>
										<td><s:date name="receiveTime" format="yyyy-MM-dd"/></td>
										<td align="center"><a href="javascript:openGL('<s:property value="batNo"/>');">关联条码</a></td>
									</tr>
						         </s:iterator>
						    </s:else>
						    <tr  bgcolor="#eef5ff" id="row1" onclick="clickRow1(this)" title="双击查看"  ondblclick="showItem()">
								<td align="center"><input type="checkbox" id="001"/></td>
								<td>1</td>
								<td>200302121</td>
								<td>测试公文1</td>
								<td>组织部</td>
								<td>开放</td>
								<td>长期</td>
								<td>2010-01-08</td>
								<td align="center" style="width: 35px"><a href="gongGao/公告摸板_个人宣.doc" target="_blank">查看</a></td>
							</tr>
							<tr bgcolor="#e0edff" id="row2" onclick="clickRow1(this)" title="双击查看" ondblclick="showItem()">
								<td align="center"><input type="checkbox"/></td>
								<td>2</td>
								<td>200302122</td>
								<td>测试公文2</td>
								<td>组织部</td>
								<td>开放</td>
								<td>长期</td>
								<td>2010-01-08</td>
								<td align="center" style="width: 35px"><a href="gongGao/公告摸板_个人宣.doc" target="_blank">查看</a></td>
							</tr>
						    </form>
						</tbody>
					</table>
					<table width="100%" style="font-size: 12px;">
						 <tr>
						    <td>
						      &nbsp;&nbsp;&nbsp;<input type="checkbox" onclick="selectAll(this)" id="SelectAll" accesskey="S"  name="SelectAll" title="选中/取消 所有记录(S)" />全选
						    </td>
							<td align="right" style="width: 100px; vertical-align: bottom;">
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
