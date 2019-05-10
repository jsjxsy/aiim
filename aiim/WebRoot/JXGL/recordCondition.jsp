<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="taglib.jsp" %>

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
	<meta http-equiv="content-type" content="text/html; charset=UTF-8"></meta>
    <title>著录情况 监测</title>
	<meta http-equiv="pragma" content="no-cache"></meta>
	<meta http-equiv="cache-control" content="no-cache"></meta>
	<meta http-equiv="expires" content="0"></meta>
	<link href="css/Styles.css" type="text/css" rel="stylesheet" />
	<link href="css/common.css" type="text/css" rel="stylesheet" />
	
	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/dateTool.js"></script>
<script type="text/javascript" >
function initTime() {
	$('#beginTime').val(getFirstDay());
	$('#endTime').val(new Date().Format('yyyy-MM-dd'));
}


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
	var retval = window.showModalDialog(url,"", "dialogWidth:197px; dialogHeight:210px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no;help=no; "  );
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

function showBorrow(){
  window.showModalDialog("dlgBorrow_JD.htm","newwindow","dialogWidth=500px;dialogHeight=420px;center=yes;scroll=no;status=no;resizable=no;help=no;location=no");
}

//单选按钮 选择
function checkRadio(radioId) {
	$('#'+radioId).attr('checked','checked');
	if(radioId == 'partTime') {	//选中档案兼职人员
		createBusinessGuids();
	} else if(radioId == 'businessGuid') {	//选中业务指导人员
		$('#businessGuids').css('display','none');
	}
}

//构建业务指导室人员复选框
function createBusinessGuids(selectBusinessGuids) {
	PerformanceManageDWR.findbusinessGuids({
			callback: function(businessGuids) {
				var checkboxs = $('#businessGuids');
				checkboxs.html('');
				if(selectBusinessGuids) {
					for(var i=0; i<businessGuids.length; i++) {
						var checkVal = '';
						for(var j=0; j<selectBusinessGuids.length; j++) {
							if(selectBusinessGuids[j] == businessGuids[i].userID) {
								checkVal = 'checked="checked"';
								continue;
							}
						}
						$('<input type="checkbox" id="'+businessGuids[i].userID+'" name="businessGuidId" title="'+businessGuids[i].realName+'" value='+businessGuids[i].userID+' '+checkVal+'/><label onclick="checkboxChange(\''+businessGuids[i].userID+'\')">'+businessGuids[i].realName+'</label>').appendTo(checkboxs);
						checkVal = '';
					}
				} else if(selectBusinessGuids=='none'){	//为选中一个人
					for(var i=0; i<businessGuids.length; i++) {
						$('<input type="checkbox" id="'+businessGuids[i].userID+'" name="businessGuidId" title="'+businessGuids[i].realName+'" value='+businessGuids[i].userID+' /><label onclick="checkboxChange(\''+businessGuids[i].userID+'\')">'+businessGuids[i].realName+'</label>').appendTo(checkboxs);
					}
				}else {
					for(var i=0; i<businessGuids.length; i++) {
						$('<input type="checkbox" id="'+businessGuids[i].userID+'" name="businessGuidId" title="'+businessGuids[i].realName+'" value='+businessGuids[i].userID+' checked="checked"/><label onclick="checkboxChange(\''+businessGuids[i].userID+'\')">'+businessGuids[i].realName+'</label>').appendTo(checkboxs);
					}
				}
				
				checkboxs.show();
			}
	});
}

//复选框 状态改变
function checkboxChange(checkboxId) {
	var checkboxObj = $('#'+checkboxId);
	if(checkboxObj.attr('checked')) {
		checkboxObj.attr('checked',false);
	} else {
		checkboxObj.attr('checked',true);
	}
}

/**
 * 提交表单
 	查看著录情况
 */
function queryCondition() {
	var beginDate = StringToDate($('#beginTime').val());
	var endDate = StringToDate($('#endTime').val());
	if(beginDate > endDate) {
		alert('开始日期不能大于结束日期，请重新选择！');
		beginTime.focus();
		return false;
	}
	$('form').submit();
}

//初始化设置
$(function() {
	setInterval(refresh, 1000);
	//设置单选按钮的选中状态
	var radioVal = '${roleId}';
	if(radioVal == 1) {
		$('#businessGuid').attr('checked',true);
		$('#jfreechart').attr('alt','业务指导室著录情况一览表');		
	}
	if(radioVal == 2) {
		$('#partTime').attr('checked',true);
		$('#jfreechart').attr('alt','兼职档案员著录情况一览表');
	}

	//设置业务指导室人员复选框
	var selectBusinessGuids = '${businessGuidIdList}';

	if(selectBusinessGuids) {
		if(selectBusinessGuids == 'none') {//未选中一个业务指导室人员
			createBusinessGuids('none');
		} else {//至少中一个业务指导室人员
			createBusinessGuids(selectBusinessGuids.split(","));
		}
	}
});

</script>

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

input {
	margin: 0 0 0 0;
}
</style>
  </head>
  
  <body>
  <s:form action="workProcedureMonitorAction_manage" namespace="/JXGL">
  	<s:hidden name="nodeId" value="11" />
  	<table width="100%" style="margin:0px;word-break:keep-all;font-size: 12px;" cellspacing="0" cellpadding="0" >
			<tr>
				<td>
		          <div style="margin-right:0px; display:block; float:right;margin-top:0px; color:blue;width: 570px;">
						<font style="font-size:13px;font-weight:bold;">当前位置:绩效管理&gt;&gt;工作过程监测&gt;&gt;著录完成情况&nbsp;&nbsp;当前时间：<span id="currentTime"></span></font>
				  </div>
				</td>
			</tr>
			<tr>
				<td>
			  		角色选择：<input type="radio" id="businessGuid" name="roleId" value="1" checked="checked" title="业务指导室"/><label onclick="checkRadio('businessGuid')">业务指导室</label>
				  	<input type="radio" id="partTime" name="roleId" value="2" title="兼职档案员"/><label onclick="checkRadio('partTime')">兼职档案员</label><span id="businessGuids"></span>
				</td>
			</tr>
			<tr>
				<td valign="top">
			  		监测时间:
				  	<input name="beginTime" id="beginTime" value="${beginTime}"/><image style="margin-right:20px;" src="images/dropdownTime.gif" onclick="PopUpCalendar('beginTime',true)"  />
	             	到<input name="endTime" id="endTime" value="${endTime}"/><image style="margin-right:20px;" src="images/dropdownTime.gif" onclick="PopUpCalendar('endTime',true)"  />
				  	<input type="button" onclick="javascript:queryCondition();" value="查看"/>
				</td>
			</tr>
			<tr>
				<td>
					<c:choose>
				 		<c:when test="${url == 'null'}">无结果记录</c:when>
				 		<c:otherwise><img id="jfreechart" src="${url}" alt="业务指导室著录情况一览表" /></c:otherwise>
				 	</c:choose>
				</td>
			</tr>
		</table>
	</s:form>
  </body>
</html>