<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.springframework.web.context.request.SessionScope"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>查询在线申请单</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/common.css">
		<script type="text/javascript" src="js/common.js"></script>
		
		<script type="text/javascript" src="dwr/interface/ArchivesUseAction.js"></script>
	    <script type="text/javascript" src="dwr/util.js"></script>
	    <script type="text/javascript" src="dwr/engine.js"></script>		
		<script type="text/javascript">
		
		//全局变量
		var spanId;//记录id,用于显示执行结果
		var divId；//记录id,用于显示执行结果
		var hrefId;//当前
		//同意请求
		function agree(tag){
			divId = "d_"+tag;
			spanId = "s_"+tag;			
			checkArchivesUseRequestDetail(tag ,1,"");//审批通过
					
		}
		
		
		
		//不同意请求
		function disagree(tag){
			divId = "d_"+tag;
			spanId = "s_"+tag;
			hrefId = "href_"+tag;
			var backReason = window.showModalDialog(
					"backReason.jsp",
					window,
					"dialogWidth:310px; dialogHeight:150px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");
			if(backReason==undefined){
				return false;
			}
			alert(tag);
			checkArchivesUseRequestDetail(tag ,2,backReason);//审批不通过
		}


//单击check框事件，控制按钮的可用/不可用状态
	function oneSelect(obj) {
		var elements=document.getElementsByTagName("input");
		var SelAll=document.getElementById("SelectAll");
		var iCount=0;//总数
		var iCheck=0;//选 中总数	
		var objDel=document.getElementById("imgDel");//删除		
		for (i=0; i<elements.length; i++) {
			if (elements[i].type=="checkbox"  && elements[i].name == "departIDs") {
				iCount++;
				if (elements[i].checked==true) {
					iCheck++;
				}
			}
		}
		if (iCount==iCheck && iCount>0) {//设置全选 状态
			SelAll.checked=true;
		}else {
			SelAll.checked=false;
		}
		if(iCheck>0){//有记录被选中
			$("imgDel").disabled = false;
			$("imgDel").src="images/del.gif";
		}else{//无记录被选中
			$("imgDel").disabled = true;
			$("imgDel").src="images/del3.gif";
		}		
	}
	
	

	//全选
	function allSelect(obj) {
		var elements=document.getElementsByTagName("input");
		var checkedNum = 0;
		for (i=0; i<elements.length; i++) {
			if (elements[i].type=="checkbox" && elements[i].name == 'departIDs') {
				elements[i].checked = obj.checked;
				checkedNum++;
			}
		}
		if(obj.checked && checkedNum>0){
			$("imgDel").disabled = false;
			$("imgDel").src="images/del.gif";
		}else{//无记录被选中
			$("imgDel").disabled = true;
			$("imgDel").src="images/del3.gif";
		}	
	}

	
	//处理单击事件
var rowId="";   //保存上一次点击行“tr”的ID；
var rowColor="";   //保存上一次点击行的颜色
function clickRow(obj)
{
	if(document.getElementById(rowId)==null){//第一次点击处理
		rowId=obj.id;	//保存被点击行的ID
		rowColor=obj.style.backgroundColor;//保存被点击行的颜色
		obj.style.backgroundColor='#a3c9ff';
	}else{
		document.getElementById(rowId).style.backgroundColor=rowColor;
		obj.style.backgroundColor='#a3c9ff';
		rowId = obj.id;
	}	 
}
//{callback:addArchivesUseRegisterBack,exceptionHandler:function(message){alert(message);}}
////////DWR 方法/////////////////
//申请审批：0代表未审；1代表通过；2代表未通过
function checkArchivesUseRequestDetail(ID ,checkResult,backReason){
	//ArchivesUseAction.checkArchivesUseRequestDetail(ID ,checkResult,backReason,checkArchivesUseRequestDetailBack);
	ArchivesUseAction.checkArchivesUseRequestDetail(ID ,checkResult,backReason,{callback:checkArchivesUseRequestDetailBack,exceptionHandler:function(message){alert(message);}});
}

function checkArchivesUseRequestDetailBack(result){
	if(result == 1){//审核通过
		document.getElementById(divId).style.display = 'none';
		var spanObj = document.getElementById(spanId);
		spanObj.style.color = 'blue';
		spanObj.innerText = '通过';
	}else if(result == 2){//审核不通过
		document.getElementById(divId).style.display = 'none';
		var spanObj = document.getElementById(spanId);
		spanObj.style.color = 'red';
		spanObj.innerText = '不通过';
	}	
}
     

</script>

<style type="text/css">
/*设置标题底色*/
.bgTitle {
	background-color: #a3c9ff;
	height: 25px;
}

/*设置表格顶部框底色*/
.borderTop {
	border-top: #104da6 1px solid;
	border-left: #104da6 1px solid;
	border-right: #104da6 1px solid;
}

body {
	height: 100%;
	color: #000000;
	font-size: 12px;
	margin: 0;
	background-color: White;
}

/*表头*/
.tableTitle {
	font-weight: bold;
	font-size:12px;
	text-align: left;
	padding: 4px 0 0 5px;
}

.text {
	font-size: 12px;
}

.tableHead {
	font-weight: bold;
	text-align: center;
	padding: 4px 0 0 0px;
}
.dd_sp2{ float:left; display:inline; width:75px; height:13px; overflow:hidden}

/*设置被选行的颜色*/
.selectRowColor {
	background-color: #a4caef;
}
</style>
	</head>
	<body style="overflow: scroll;" >
	<form action="DALY/archivesUseAction_findOnLineUseLists.action" method="post" name="conditionForm" id="conditionForm" style="margin: 0px; padding: 0px;">
		<input type="hidden" name="dataPageInfo.currentPage" id="currentPage" value="1">	
	</form>
		
		<table width="100%" style="margin: 0px;" cellspacing="0"
			cellpadding="0">
			<tr>
				<td> 
					&nbsp;
				</td>
				<td>
					<div id="location"
						style="margin-right: 2px; width: 280px; display: inline-block; float: right; margin-top: 5px; color: blue; text-align: right;">
						<font style="font-size: 12px;"><b>当前位置：</b>利用管理&nbsp;&gt;&gt;&nbsp;&nbsp;申请审批</font>
					</div>
				</td>
			</tr>
		</table>
	
	<table width="100%" style="margin: 0px;" cellspacing="0" cellpadding="0">			
		<tr>
			<td>
				<table class="tabletop" width="100%">
					<tr>
		                <td class="tableTitle">利用申请列表</td>
		                <td align="right" class="text">第${dataPageInfo.currentPage}页 共${dataPageInfo.pageCount}页 共${dataPageInfo.rowCount}条记录</td>							                	
		            </tr>
				</table>
				<table id="showTable" width="100%" style="margin:0px; border: #104da6 1px solid;" cellspacing="1px" cellpadding="0px" >						
					<thead class="tableHead">
						<tr class="bgTitle">
							<th width="30px;">
								序号
							</th>
							<th width="110px">
								档号
							</th>
							<th>
								档案分类
							</th>
							<th>
								题名
							</th>
							<th>
								密级
							</th>
							<th>
								利用人姓名
							</th>
							<th>
								利用人单位
							</th>							
							<th>
								利用方式
							</th>
							<th>
								申请时间
							</th>							
							<th>
								申请理由
							</th>
							<th>
								审批结果
							</th>					
							<th>
								操作
							</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="#request.archivesUseRequestDetails" id="id" status="rows">
							<s:if test="#rows.odd==true">
								<tr bgcolor="#eef5ff" height="20px;" id="<s:property value="ID"/>"  onclick="clickRow(this)" >
							</s:if>
							<s:else>
								<tr bgcolor="#e0edff" height="20px;"  id="<s:property value="ID"/>" onclick="clickRow(this)">
							</s:else>	
								<td align="center"><s:property value="#rows.index+1"/> </td>	
								<td><s:property value="archivesID"/></td>
								<td><s:property value="archivesTypeText"/></td>	
					           	<td><s:property value="title"/></td>
					           	<td><s:property value="secrecyText"/></td>
					           	<td><s:property value="archivesUseRequest.userInfo.realName"/></td>		           	
					           	<td><s:property value="archivesUseRequest.userDepartment"/></td>	           	
					           	<td><s:property value="useWayText"/></td>
					           	<td><s:date name="archivesUseRequest.requestTime" format="yyyy-MM-dd"/></td>
					           	<td width="80px"><span class="dd_sp2" title="<s:property value="archivesUseRequest.requestReason"/>"><s:property value="archivesUseRequest.requestReason"/></span></td>
					           	<td><span id=s_<s:property value="ID"/> >待审</span></td>
					           	<td><div id=d_<s:property value="ID"/> ><a href="#"  onclick="agree(<s:property value="ID"/>);return false;">同意</a> <a href="#"  onclick="disagree(<s:property value="ID"/>);return false;">不同意</a></div> </td>
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
					      <!--  <input type="checkbox" id="SelectAll" accesskey="S"  name="SelectAll" onclick="selectAll(this)" title="选中/取消 所有记录(S)"  /><label for="SelectAll">全选</label> -->	
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
