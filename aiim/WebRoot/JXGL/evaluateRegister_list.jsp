<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="taglib.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>考核管理</title>
	<link href="css/Styles.css" type="text/css" rel="stylesheet" />
	<link href="css/common.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="js/jquery.form.js"></script>
	<script type="text/javascript" src="js/popup.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/dateTool.js"></script>
	<script type="text/javascript" src="js/jquery.form.js"></script>
	
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
</style>
	
	<script type="text/javascript" >
//改变图片背景颜色
function changeImage(obj,img) {
	if (obj) {
		obj.src="images/"+img;
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
<%--  document.getElementById('find').style.display='block';--%>
  $('#find').css('display','block');
}

function showKH(evaluateId){
  	var returnValue = window.showModalDialog("/aiim/JXGL/evaluateManageAction_evaluateDetails.action?evaluateRegID="+evaluateId,"newwindow","dialogWidth=510px;dialogHeight=330px;center=yes;scroll=no;status=no;resizable=no;help=no;location=no");
	if(returnValue && returnValue=='1') {
		window.location.reload();
	}
}

var insertYear = new Number();
/**
 * 改变年度
 */
function changYear(flag) {
	var year = new Number($('#currentYear').val());
	if(flag) {
		//下一年度
		if(flag == 'down') {
			//查询是否已经存在考核记录
			insertYear = year+1;
			PerformanceManageDWR.findCountByYear(insertYear,insertEvaluateByYearBack);
		}
	}
}

//DWR 回调函数
function insertEvaluateByYearBack(evaluateCount){
	if(evaluateCount=null || evaluateCount <= 0) {
		if(toConfirm('您确定开始'+insertYear+'年度考核吗？')) {
		 	PerformanceManageDWR.insertEvaluateByYear(insertYear);
		}
	}
	$('#currentYear').append('<option value="'+insertYear+'">'+insertYear+'</option>');
	$('#currentYear').val(insertYear);
	$('form').submit();
}

function toConfirm(message) {
	if (confirm(message)) {
        return true;
    } else {
    	return false;
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

//检索考核登记信息
function search() {
	
	//判断考核日期格式是否正确
	var beginTime = $('#registerDate');
	
	var formationYearPatrn = /^[1-2]{1}[0-9]{1}[0-9]{2}\-(\d{2})\-(\d{2})$/;
	if(beginTime.val()==null || beginTime.val()=='') {
	} else if(!formationYearPatrn.exec(beginTime.val())) {
		alert('请输入正确的日期！');
		beginTime.focus();
		return false;
	}

	//判断最小分数
	var contentIDPatrn = /^[0-9]*$/;
	var minScore = $('#minScore');
	if(minScore.val()==null || minScore.val()=='') {
	} else if(minScore.val()<0 || !contentIDPatrn.exec(minScore.val())){
		alert('请输入正确的最小分数！');
		minScore.focus();
		return false;
	}

	//判断最大分数
	var maxScore = $('#maxScore');
	if(maxScore.val()==null || maxScore.val()=='') {
	} else if(maxScore.val()<0 || !contentIDPatrn.exec(maxScore.val())){
		alert('请输入正确的最大分数！');
		maxScore.focus();
		return false;
	}
	$('#find').css('display','none');
<%--	$('form').attr('action','JXGL/evaluateManageAction_search.action');--%>
	$('form').submit();
}

//删除考核登记信息
function deleteBatch() {
	if(confirm('您确认删除考核记录吗？')) {
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
		//如果有选择对象 删除考核记录
		if(checkIDs.length>=1) {
			PerformanceManageDWR.deleteBatchEvaluate(checkIDs);
		}
	}
}

//监控部门类型选择下拉框
	$(function(){
		$("#currentYear").change(function(){ //事件發生 
			jQuery('option:selected', this).each(function(){
				var optionVal = this.value;
				$('form').submit();
			});
		});

		//设置新开年度按钮是否有效
		var nowYear =new Number(new Date().getFullYear()); 
		var currentYear = $('#currentYear');
		PerformanceManageDWR.findMaxYear({callback: function(maxYear) {
			if(new Number(maxYear)>=1) {
				$('#nextYear').removeAttr('disabled');
				$('#nextYear').attr('src','images/nextYear.gif');
			}
		}});
		
		//判断是否需要追加人员
		PerformanceManageDWR.isAppendEvaluate(currentYear.val(),isAppendEvaluateBak);
	});
	

	//DWR isAppendEvaluate回调函数
	function isAppendEvaluateBak(count) {
		if(count >= 1) {
			if(confirm('有'+count+'个新人加入，是否追加考核记录？')) {
				appendEvaluate();
			}
		}
	}

	//追加人员考核记录(小于本年度的不进行追加)
	function appendEvaluate() {
		var currentYear = $('#currentYear').val();
		if(currentYear) {
			PerformanceManageDWR.appendEvaluate(currentYear,{
				callback: function() {
					alert('人员追加成功！');
					$('form').submit();
				}
			});
		}
	}
</script>


</head>
<body>
	<s:form id="conditionForm" name="conditionForm" action="evaluateManageAction_search" namespace="/JXGL" method="get">
  		<input type="hidden" name="dataPageInfo.currentPage" id="currentPage" value="1"/>
  		<input type="hidden" name="preSelectRow" id="preSelectRow" />
		<table width="100%" style="margin:0px;" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<!-- 按钮 -->
					<div style="float:left;margin-left: 10px;">
						<s:select id="currentYear" list="evaluatedYears" name="currentYear" theme="simple" style="margin-bottom:5px;"/>
						<input type="image" id="deleteB" src="images/del3.gif" disabled="disabled"
							onmouseover="changeImage(this,'del2.gif')"
							onmouseout="changeImage(this,'del.gif')" onclick="deleteBatch()"/>
						<img id="nextYear" src="images/nextYear3.gif" disabled="disabled"
							onmouseover="changeImage(this,'nextYear2.gif')"
							onmouseout="changeImage(this,'nextYear.gif')" onclick="changYear('down')" alt="下一年度"/>
					</div>
					<div style="margin-right:10px; display:block; float:right; color:blue;font-size:14px;">
						<font style="font-weight:bold;">当前位置：</font>绩效管理&gt;&gt;考核管理
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
							                <td align="center">
							                   <label class="tableTitle">${currentYear}年 考核记录</label>
							                </td>
							                <td align="right"  class="text" >
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
								<th width="35px">选择</th>								
								<th>姓名</th>
								<th>职务</th>								
								<th>考核分数</th>
								<th>评分等级</th>
								<th>操作</th>							
							</tr>
						</thead>
						<tbody id="trList">
							<s:iterator value="#request.evaluateRegisterVOs" id="vo" status="stau">
								<tr  bgcolor="#e0edff" id="tr<s:property value="#vo.iD"/>" onclick="clickRow(this);setDelete();">
									<td align="center"><input type="checkbox" id="td<s:property value="#vo.iD"/>" name="checkId" value="<s:property value="#vo.iD"/>"/></td>
									<td align="center" style="height: 21px"><a href="javascript:showKH('<s:property value="#vo.iD"/>');"><s:property value="#vo.realName"/></td>
									<td><s:property value="#vo.dutyName"/></td>
									<td><s:if test="#vo.score>=1"><s:property value="#vo.score"/></s:if></td>
									<td><s:property value="#vo.evaluateLevelName"/></td>
									<td align="char"><a href="JXGL/evaluateManageAction_evaluateDetails.action?evaluateRegID=<s:property value="#vo.iD"/>">考核</a></td>
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
						       <input type="checkbox" id="SelectAll" accesskey="S"  name="SelectAll" onclick="selectAll(this);setDelete();" title="选中/取消 所有记录(S)"  /><label for="SelectAll">全选</label>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		  </table>
</s:form> 
</body>
</html>
