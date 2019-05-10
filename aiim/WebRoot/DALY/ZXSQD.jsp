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
    
    <title>My JSP 'ZXSQD.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>

  </head>
  
  <body>
    <input type="hidden" name="preSelectRow" id="preSelectRow" />
		<table width="100%" style="margin: 0px;" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					 <table width="100%" cellpadding="0" cellspacing="0" border="0">
			       <tr>
				       <td>
						    <input type="image" name="imgDel" id="imgDel" accesskey="C" disabled="disabled" title="删除(C)" onclick="return confrm();" onmouseover="ChangeImage(this,'delA2.gif')" onmouseout="ChangeImage(this,'delA.gif')" src="delA.gif" style="border-width:0px;" />
						    <input accesskey="Q" onclick="return showfind(this)" type="image" alt="显示查询(Q)" src="images/find.gif" onmouseover="ChangeImage(this,'find2.gif')" onmouseout="ChangeImage(this,'find.gif')" />
						    <input type="image" src="images/DYDJD.gif" onmouseover="changeImage(this,'DYDJD1.gif')" onmouseout="changeImage(this,'DYDJD.gif')" onclick="showDJD()"/>
						</td>
						<td>
							<div style="margin-right:2px; width:230px; float:right; color:blue; font-size:12px;"><font style="font-weight:bold;">当前位置：</font>档案利用&nbsp;>>&nbsp;查询在线申请单</div >					
						</td>
				  </tr>
				  </table>
				</td>
			</tr>
			<tr id="find" style="DISPLAY: block">
					<td>
					  <fieldset>
						<table align="center">
							<tr>
								<td class="text" align="right">申请人：</td>
								<td colspan="3"><input name="txtTitle" type="text" id="txtTitle" class="back_border" /></td>
							</tr>
							<tr>
								<td class="text" align="right">证件号码：</td>
								<td colspan="3"><input name="txtSender" type="text" id="txtSender" class="back_border" /></td>
							</tr>
							<tr>
								<td class="text" align="right">申请人单位：</td>
								<td colspan="3"><input name="txtSender" type="text" id="txtSender" class="back_border" /></td>
							</tr>
							<tr>
								<td class="text" align="right">申请时间：</td>
								<td><input name="txtdate1" type="text" readonly="readonly" id="txtdate1" class="back_border" /><img alt="日期" style="CURSOR: pointer" onclick="javascript:PopUpCalendar('txtdate1',true); return false;"
										src="images/DropdownTime.gif" /></td>
								<td class="text">至</td>
								<td><input name="txtdate2" type="text" readonly="readonly" id="txtdate2" class="back_border" /><img alt="日期" style="CURSOR: pointer" onclick="javascript:PopUpCalendar('txtdate2',true); return false;"
										src="images/DropdownTime.gif" /></td>
							</tr>
							<tr>
								<td align="center" colspan="4"><input type="image" name="imgFind" id="imgFind" onmouseover="ChangeImage(this,'search2.gif')" onmouseout="ChangeImage(this,'search.gif')" src="images/search.gif" style="border-width:0px;" /></td>
							</tr>
						</table>
						</fieldset>
					</td>
				</tr>
			<tr>
				<td>
					<table class="tabletop" width="100%">
						<tr>
			                <td class="tableTitle">利用记录</td>
			                <td align="right" class="text">第${dataPageInfo.currentPage}页 共${dataPageInfo.pageCount}页 共${dataPageInfo.rowCount}条记录</td>							                	
			            </tr>
					</table>
					<table id="showTable" width="100%" style="margin: 0px; border: #104da6 1px solid;" cellspacing="1px" cellpadding="0px">
						<thead class="tableHead">
							<tr class="bgTitle">
								<th width="35px">选择</th>								
								<th>申请单编号</th>
								<th>姓名</th>
								<th>证件号码</th>
								<th>单位</th>
								<th>E_mail</th>
								<th>利用目的</th>
								<th>申请时间</th>
							</tr>
						</thead>
						<tbody>
							<tr  bgcolor="#eef5ff" id="row1" onclick="clickRow(this)">
								<td align="center" style="height: 12px"><input type="checkbox" id="001"/></td>
								<td style="height: 12px">2010020305426</td>
								<td style="height: 12px">李四</td>
								<td style="height: 12px">&nbsp;</td>
								<td style="height: 12px">人事部</td>
								<td style="height: 12px">5@163.com</td>
								<td style="height: 12px">工作调查</td>
								<td style="height: 12px">2010-03-15</td>
							</tr>
							<tr  bgcolor="#e0edff" id="row2" onclick="clickRow(this)">
								<td align="center"><input type="checkbox" id="001"/></td>
								<td>2010020305426</td>
								<td>张三</td>
								<td>&nbsp;</td>
								<td>人事部</td>
								<td>4@163.com</td>
								<td>学术研究</td>
								<td style="height: 12px">2010-03-15</td>
							</tr>							
						</tbody>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" style="font-size: 12px;">
						 <tr>
						    <td>
						       
							</td>
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
