<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="taglib.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>任务信息显示</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/Styles.css" type="text/css" rel="stylesheet" />
	<link href="css/common.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/dateTool.js"></script>
	
		<style type="text/css">
/*设置标题底色*/
.bgTitle { background-color:#a3c9ff;height:25px;}
/*设置表格顶部框底色*/
.borderTop {	border-top:#104da6 1px solid;	border-left:#104da6 1px solid;	border-right:#104da6 1px solid;}
body 
{
	height:100%;
	color: #000000; 
	font-size:12px;
	margin:0; 
	background-color:White;
}
/*表头*/
.tableTitle {font-weight:bold; text-align:left; padding:4px 0 0 5px;}
.text{ font-size:9pt;}
.tableHead{
	font-weight:bold; text-align:center; padding:4px 0 0 0px;
}
/*
#showTable tbody tr:hover{
	background-color:#a3c9ff;
}
*/
/*设置被选行的颜色*/
.selectRowColor{
	background-color:#a4caef;
}


ol li { margin:8px}
#tags { height:23px; width:400px; margin:0; padding:0; margin-left:10px}
#tags li { float:left; margin-right:1px; background:url(images/tagleft.gif) no-repeat left bottom; height:23px; list-style-type:none}
#tags li a { text-decoration:none; float:left; background:url(images/tagright.gif) no-repeat right bottom; height:23px; padding:0px 10px; line-height:23px; color:#999}
#tags li.emptyTag { width:4px; background:none}
#tags li.selectTag { background-position: left top; position:relative; height:25px; margin-bottom:-2px}
#tags li.selectTag a { background-position: right top; color:#000; height:25px; line-height:25px;}

</style>
<script type="text/javascript" >

//改变图片背景颜色
function changeImage(obj,img) {
	if (obj) {
		obj.src="images/"+img;
	}
}
//设置单击表格中某一行时改变该行的颜色
function clickRow(obj)
{	
	var preSelectObj = document.getElementById('preSelectRow');//获取存ID为preSelectRow对象（此对象存储上一个被选中行的ID）
	if(preSelectObj.value!=null&&preSelectObj.value!="")
	{
		var preSelectId = preSelectObj.value;//取preSelectRow对象中的VALUE值
		document.getElementById(preSelectId).className=obj.className;//将当前被选中的行的样式赋给上一被选中的行的样式
	}	
	
	obj.className='selectRowColor';//改变被选中行的样式
	preSelectObj.value=obj.id;//保存当前被选中行的ID
}

//显示查询
function showFind(){
  document.getElementById('find').style.display='block';
}
//打开增加任务对话框
function showRW(){
  window.showModalDialog("taskInfo_add.jsp","newwindow","dialogWidth=500px;dialogHeight=400px;center=yes;scroll=no;status=no;resizable=no;help=no;location=no");
}

//打开任务详细回复
function showRWXX(editTaskInfoId){
	var url = "/aiim/JXGL/taskinfoManageAction_detail.action?editTaskInfoId="+editTaskInfoId;
<%--	openWindow("/aiim/JXGL/taskinfoManageAction_detail.action?editTaskInfoId="+editTaskInfoId, 900, 500);--%>
window.showModalDialog(url,"newwindow","dialogWidth=900px;dialogHeight=500px;center=yes;scroll=yes;status=no;resizable=no;help=no;location=no");		
}


function openWindow(url,width,height) {
	window.open(url,"newwindow","height="+height+",width="+width+",left="+(window.screen.width-width)/2+",top="+(window.screen.height-height)/2+",Toolbar=no,Menubar=no,scrollbars=yes,resizable=no,location=no,status=no");
}

//更新界面
function updateUI() {
	window.showModalDialog("/aiim/JXGL/taskinfoManageAction_updateUI.action?show=show&editTaskInfoId="+$('#editTaskInfoId').val(),"newwindow","dialogWidth=500px;dialogHeight=400px;center=yes;scroll=no;status=no;resizable=no;help=no;location=no");
}

//通过任务主题点击更新界面
function updateTask(editTaskInfoId) {
	var returnValue = window.showModalDialog("/aiim/JXGL/taskinfoManageAction_updateUI.action?show=show&editTaskInfoId="+editTaskInfoId,"newwindow","dialogWidth=500px;dialogHeight=400px;center=yes;scroll=no;status=no;resizable=no;help=no;location=no");
	if(returnValue && returnValue==1) {
		window.location.reload();
	}
}

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

//删除考核登记信息
function deleteBatch() {
	if(confirm('确认删除任务吗？')) {
		var checkIDs = new Array();
		var index = 0;
		$('#trList > tr').each(function(){
			var checkboxDOM = $(this).find('td').find('input:checkbox');
			if(checkboxDOM.attr('checked')) {
				$(this).detach();
				checkIDs[index] = checkboxDOM.val();
				index++;
			}
		});
		//如果有选择对象 删除任务信息
		if(checkIDs.length>=1) {
			PerformanceManageDWR.deleteBatchTaskInfo(checkIDs);
		}
	}
}

//获取选中的checkbox值数组
function getCheckbox() {
	var checkIDs = new Array();
	var index = 0;
	$('#trList > tr').each(function(){
		var checkboxDOM = $(this).find('td').find('input:checkbox');
		if(checkboxDOM.attr('checked')) {
			checkIDs[index] = checkboxDOM.val();
			index++;
		}
	});
	return checkIDs;
}

//设置只有选中一个checkbox 才能编辑
function setEdit() {
	var checkIDs = getCheckbox();
	if(checkIDs.length==1) {
		$('#editB').removeAttr('disabled');
		$('#editB').attr('src','images/edit.gif');
		$('#editTaskInfoId').val(checkIDs[0]);
	} else {
		$('#editB').attr('disabled','disabled');
		$('#editB').attr('src','images/edit3.gif');
	}
}

//设置只要选中一个checkbox 才能删除
function setDelete() {
	var checkIDs = getCheckbox();
	if(checkIDs.length>=1) {
		$('#deleteB').removeAttr('disabled');
		$('#deleteB').attr('src','images/del.gif');
	} else {
		$('#deleteB').attr('disabled','disabled');
		$('#deleteB').attr('src','images/del3.gif');
	}
}

var publishFlag = '${publishFlag}';

//检索考核登记信息
function search(flag) {
	setPublish(flag);
	
	//选择另一标签页时 清空查询条件
	if(flag != publishFlag) {
		clearForm();
	}

	$('#find').css('display','none');
	$('form').submit();
}

//设置是否进行任务发布
function setPublish(flag) {
	$('#publishFlag').val(flag);
}

//清除表单数据项
function clearForm() {
	$('#receiveName').val('');
	$('#content').val('');
	var keySize = '${keySize}';
	for(var i=0; i<=keySize; i++) {
		$('#titleKeys-'+i).removeAttr('checked');
	}
}

//选择标签页
function selectTag(idObj){
	$('#tags > li').each(function(){
		if($(this).attr('id') == idObj) {
			$(this).addClass('selectTag');
		} else {
			$(this).removeAttr('class');
		}
	});
}

//页面初始化加载
$(function() {
	//设置标签页选项
	
	if(publishFlag && publishFlag=='n') {
		selectTag('l2');
	} else {
		selectTag('l1');
	}
});
</script>
</head>
  
  <body>
  <s:form id="conditionForm" action="taskinfoManageAction_publishTask" namespace="/JXGL">
 		<input type="hidden" name="preSelectRow" id="preSelectRow" />
		<input type="hidden" name="dataPageInfo.currentPage" id="currentPage" value="1"/>
		<input type="hidden" name="editTaskInfoId" id="editTaskInfoId"/>
		<input type="hidden" name="publishFlag" id="publishFlag"/>
		
		<table width="100%" style="margin:0px;" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<div style="float:left;margin-left: 10px;">
						<input type="image" src="images/find.gif" alt="显示查询(Q)" onmouseover="changeImage(this,'find2.gif')" onmouseout="changeImage(this,'find.gif')" onclick="return showfind(this);"/>&nbsp;					
					</div>	
					<div style="margin-right:10px; display:block; float:right; color:blue;font-size:14px;">
						<font style="font-weight:bold;">当前位置：</font>我的工作空间&gt;&gt;我发布的任务
					</div>
				</td>
			</tr>
			<tr>
			  <td align="center">
			    <div id="find" style="display:none;height:143px; width:100%;margin-top:10px;">
			     <fieldset>
			       <table class="findTB">
			         <tr>
			           <td align="right">接收人:</td>
			           <td align="left"><s:textfield id="receiveName" name="receiveName"/></td>
			         </tr>
			      	 <tr>
			           <td align="right">关键词:</td>
			           	<td>
			           		<s:textfield id="content" name="content"/>
				           	<s:checkboxlist id="titleKeys" list="titleKeyMap" name="titleKeys" listKey="key" listValue="value"/>
			           </td>
					</tr>
			         <tr>
			           <td></td>
			           <td align="left"><image src="images/search.gif" onmouseover="changeImage(this,'search2.gif')" onmouseout="changeImage(this,'search.gif')" onclick="search('y')"/></td>
			         </tr>
			       </table>
			       </fieldset>
</s:form>
			    </div>
			  </td>
			</tr>
			<tr>
				<td>
					<table width="100%" cellspacing="0" cellpadding="0" style="margin:0px;">
						<tr class="bgTitle">
							<td style="height:25px" class="borderTop">
								<table style="width:100%;height:25px" cellspacing="0" cellpadding="0">
							            <tr>
							                <td style="margin-left: 100px;font-weight:bold;">
							                	我发布的任务
							                </td>
							                <td align="right"  class="text" >
							                	<label style="margin-right:4px" id="rsInfo">第${dataPageInfo.currentPage}页 共${dataPageInfo.pageCount}页 共${dataPageInfo.rowCount}条记录</label>
							                </td>						                	
							            </tr>
							    </table>
							</td>
						</tr>
					</table>				
				</td>			
			</tr>
			<tr>
				<td>
					<table id="showTable" width="100%" style="margin:0px; border: #104da6 1px solid;" cellspacing="1px" cellpadding="0px" >						
						<thead class="tableHead">
							<tr class="bgTitle">
								<th>任务主题</th>
								<th>发布人</th>
								<th>接收人</th>
								<th>发布时间</th>								
								<th>回复</th>
								<th>操作</th>							
							</tr>
						</thead>
						<tbody id="trList">
						<s:iterator value="#request.taskInfos" var="task" status="stau">
							<tr id="tr<s:property value="#task.iD"/>" bgcolor="#eef5ff" id="row1" onclick="clickRow(this);setEdit();setDelete();">
								<td align="center" ><a href='javascript:void(0);' onclick='updateTask("<s:property value="#task.iD"/>")'><s:property value="#task.title"/></a></td>
								<td><s:property value="#task.fromUserName"/></td>
								<td><a href='javascript:void(0);' onclick='showRWXX("<s:property value="#task.iD"/>")'><s:property value="#task.receiveNames"/></a></td>
								<td><fmt:formatDate value="${task.lastModifyTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td><s:property value="#task.taskResponseCount"/></td>
								<td align="char"><a href='javascript:void(0);' onclick='showRWXX("<s:property value="#task.iD"/>")'>详情</a></td>
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
								转到第<input type="text" id="gotoPage" name="gotoPage" style="width:18px; height:18px"/>页
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
