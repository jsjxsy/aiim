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
    
    <title>到期预警</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
    <SCRIPT type="text/javascript">
  //查看登记信息
//	function showDJXX(registerID){
//		alert(registerID);
//	   window.showModalDialog("<%=basePath%>DALY/archivesUseAction_findArchivesUseRegisterByID.action?useType=JD&useOutDate=true&registerID="+registerID,"newwindow","dialogWidth=550px;dialogHeight=450px;center=yes;scroll=no;status=no;resizable=no;help=no;location=no");
//	}

	//查看登记信息
	function showDJXX(registerID){
		alert(registerID);
	   window.showModalDialog("<%=basePath%>DALY/archivesUseAction_findArchivesUseRegisterByID.action?useType=JD&useOutDate=near&registerID="+registerID,"newwindow","dialogWidth=550px;dialogHeight=450px;center=yes;scroll=no;status=no;resizable=no;help=no;location=no");
	}
    </SCRIPT>

  </head>
  
  <body> 
  <div id="location" style="margin-right: 2px; width: 50%; display:block; float: right; margin-top: 0px; color: blue; text-align: right;">
	  		<font style="font-size: 12px;"><b>当前位置：</b>利用管理&nbsp;&gt;&gt;&nbsp;&nbsp;到期预警 </font>
    </div>
     <form action="DALY/archivesUseAction_findExpiringArchivesUseInfos.action" name="conditionForm" method="post">
	  <input type="hidden" name="dataPageInfo.currentPage" id="currentPage"/>
	</form>
    <form action="DALY/archivesUseAction_findArchivesUseExpiredUserInfos.action" style="display: none;" name="conditionForm" method="post">
		  <input type="hidden" name="dataPageInfo.currentPage" id="currentPage"/>
	</form>
    <input type="hidden" name="preSelectRow" id="preSelectRow" />
		<table width="100%" style="margin: 0px;" cellspacing="0" cellpadding="0">			
			<tr>
				<td>
					<table class="tabletop" width="100%">
						<tr>
			                <td class="tableTitle">利用记录</td>
			                <td align="right" class="text">第${dataPageInfo.currentPage}页 共${dataPageInfo.pageCount}页 共${dataPageInfo.rowCount}条记录</td>							                	
			            </tr>
					</table>
					<table id="showTable" width="100%" style="margin:0px; border: #104da6 1px solid;" cellspacing="1px" cellpadding="0px" >						
						<thead class="tableHead">
							<tr class="bgTitle">
								<th width="35px">选择</th>								
								<th>姓名</th>
								<th>证件号</th>
								<th>利用人部门</th>
								<th>利用方式</th>
								<th>利用日期</th>
								<td>详细</td>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="#request.archivesUseRegisters" status="status">
								<s:if test="(#status.index+1)%2==0">
									<s:set name="color" value="'#e0edff'" scope="page"></s:set>
								</s:if>
								<s:else>
									<s:set name="color" value="'#eef5ff'" scope="page"></s:set>
								</s:else>
								<tr bgcolor="${pageScope.color}" id="row<s:property value="#status.index+1"/>" onclick="selectRow(this)" >
									<td align="center" height="20px"><s:property value="#status.index+1"/></td>
									<td><s:property value="archivesUsePersonInfo.name"/></td>
									<td><s:property value="archivesUsePersonInfo.IDCardNo"/></td>
									<td><s:property value="archivesUsePersonInfo.department"/></td>
									<s:if test="borrowFlag==false">
										<td style="color: red;">查档</td>
									</s:if>
									<s:else>
										<td>借档</td>
									</s:else>
									<td><s:date name="useDate" format="yyyy-MM-dd"/></td>								
									<td align="center"><a href="javascript:showDJXX('<s:property value="ID"/>')">详细</a></td>
								</tr>							
							</s:iterator>
							
						 </tbody>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" style="font-size: 12px;">
						 <tr>
						    <td>
						       <input type="checkbox" id="SelectAll" accesskey="S"  name="SelectAll" onclick="selectAll(this)" title="选中/取消 所有记录(S)"  /><label for="SelectAll">全选</label>	
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






















<%-- 
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
    
    <title>到期预警</title>
    
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
				       <td></td>
						<td>
							<div style="margin-right:2px; width:210px; float:right; color:blue; font-size:12px;"><font style="font-weight:bold;">当前位置：</font>档案利用&nbsp;>>&nbsp;利用查询</div >					
						</td>
				    </tr>
				  </table>
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
								<th>档号</th>
								<th>题名</th>
								<th>利用人</th>
								<th>利用人单位</th>
								<th>利用方式</th>
								<th>利用目的</th>
								<th>利用日期	</th>
								<th>归还日期</th>
								<th>经办人</th>
								<th>归还状态</th>
							</tr>
						</thead>
						<tbody>
							<tr bgcolor="#eef5ff" id="row1" onclick="selectRow(this)">
								<td class="style1" style="height: 20px">G01-2002-DQ11-1</td>
								<td>测试题名1	</td>
								<td>李四</td>
								<td>校党委</td>
								<td>借出</td>
								<td>工作考察</td>
								<td>2010-02-04</td>
								<td>2010-03-04</td>
								<td>李四</td>
								<td>已借出</td>
							</tr>
							<tr bgcolor="#e0edff" id="row2" onclick="selectRow(this)">
								<td class="style1" style="height: 20px">G01-2002-DQ11-1</td>
								<td>测试题名1	</td>
								<td>李四</td>
								<td>校党委</td>
								<td>借出</td>
								<td>工作考察</td>
								<td>2010-02-04</td>
								<td>2010-03-04</td>
								<td>李四</td>
								<td>已借出</td>
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
		<form action="DALY/archivesUseAction_findExpiringArchivesUseInfos.action" name="conditionForm" method="post">
		  <input type="hidden" name="dataPageInfo.currentPage" id="currentPage"/>
		</form>
  </body>
</html>
--%>