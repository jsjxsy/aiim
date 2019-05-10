<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.orifound.aiim.entity.TaskInfo"%>
<%@page import="com.orifound.aiim.entity.TaskPerson"%>
<%@page import="com.orifound.aiim.entity.TaskResponse"%>
<%@ include file="taglib.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

TaskInfo taskInfo = (TaskInfo)request.getAttribute("taskInfo");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	 <base href="<%=basePath%>" target="_self">
	<title>任务详细</title>
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="Cache-Control" content="no-cache,  must-revalidate" />
	<link href="css/Login.css" type="text/css" rel="stylesheet" />
	
	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/dateTool.js"></script>
    
<style type="text/css">
<!--
body, ul, li, div, h1, h2{padding:0;margin:0;}
ul{list-style:none;}
a{text-decoration:none;}
.clr{clear:both;overflow:hidden;height:0;}
a.closeBtn{position:absolute;top:10px;right:10px;display:block;width:60px;padding:4px 0;text-align:center;background:#fff;border:1px solid #85B6E2;color:#333;}
a.closeBtn:hover{color:#fff;border:1px solid #85B6E2;background:#85B6E2;}

body{padding:10px 20px 200px;}
h1{color:#85B6E2;text-align:center;padding-top:20px;}
.example{border:1px dashed #ccc;padding:40px;margin-top:10px;zoom:1;}
.example .description{color:#85B6E2;float:left;padding:10px 20px 0 80px;font-size:20px;}
.example .tigger{display:block;width:80px;padding:10px;text-align:center;background:#fff;border:1px solid #999;color:#333;cursor:pointer;float:left;}
.example select{margin-top:10px;}
.blk{width:500px;position:relative;}
.blk .head, .blk .head-right, .blk .foot, .blk .foot-right{background:url(images/pop_up_bg.png);overflow:hidden;height:4px;}
.blk .head{padding-left:4px;}
.blk .head-right{background-position:right top;}
.blk .foot{padding-left:4px;background-position:left bottom;_height:3px;}
.blk .foot-right{background-position:right bottom;}
.blk .main{border-left:2px solid #85B6E2;border-right:2px solid #85B6E2;position:relative;background:#fff;}
.blk .main h2{font:bold 16px "Microsoft YaHei";padding:10px 20px 2px;color:#85B6E2;}
.blk .main ul{padding:20px;zoom:1;overflow:hidden;font-size:12px;}
.blk .main ul li{float:left;width:100px;text-align:center;line-height:30px;margin-bottom:10px;}
.blk .main ul li a{color:#333;display:block;}
.blk .main ul li a:hover{background:#85B6E2;color:#fff;font-weight:bold;font-size:14px;}


/* 自定义样式 */
.t5 .blk{width:700px;}
.t5 .blk h2{color:#aaa;}
.t5 .blk .main ul li a{color:#aaa;}
.t5 .blk .head, .t5 .blk .head-right, .t5 .blk .foot, .t5 .blk .foot-right{background:url(images/pop_up_bg2.png);}

/**/
* html .popup_iframe{
	width:expression(this.previousSibling.style.width);
	height:expression(this.previousSibling.style.height);
}

/*表格字体设置*/
.taskTable {font-size: 12px;word-break:keep-all;border-collapse:collapse;}

/*div 回复内容修饰*/
.responseContent{margin-left: 20px;font-size:12px;}

/*回复时间、内容 修饰*/
.taskTime {float: left; margin-left: 20px;}
.taskContent {float: left; margin-left: 30px;}
.taskTime,.taskContent {height: 22px;}


/*设置标题底色*/
.bgTitle { background-color:#a3c9ff;}
/*回复人 修饰*/
.taskPerson {float: left;height: 10px;}

/*更多 修饰*/
.moreLink {float: right;margin-right:0px;}
-->
</style>

</head>

<body class="bg_color" style="margin-top:4px">
<a name="topAnchor" />
<s:form id="conditionForm" action="taskinfoManageAction_detail" namespace="/JXGL">
<s:hidden id="editTaskInfoId" name="editTaskInfoId" />
<table align="center" class="taskTable" width="98%"  cellpadding="1" cellspacing="0" border="0">
	<tr>
		<td align="right" id="publisher">发布人：</td>
		<td>${taskInfo.fromUserName}</td>
	</tr>
	<tr>
		<td align="right">主题：</td>
		<td>${taskInfo.title}</td>
	</tr>
	<tr>
		<td align="right">发送给：</td>
		<td>
			<!-- 接受人循环 -->
			<c:forEach items="${taskInfo.taskPersons}" var="taskPerson" varStatus="stau">
				<c:if test="${stau.index>=1}">&nbsp;</c:if>
				<a href="javascript:gotoPath('${taskPerson.userID}');">${taskPerson.userName}</a>
			</c:forEach>
		</td>
	</tr>
	<tr>
		<td align="right">内容：</td>
		<td><textarea rows="6" cols="95%" readonly="readonly">${taskInfo.content}</textarea></td>
	</tr>
	<tr class="bgTitle">
		<td colspan="2">
			<div class="taskPerson">${taskInfo.fromUserName}</div>
		</td>
	</tr>
	
	<!-- 任务发布人	回复循环显示 -->
	<c:forEach items="${taskInfo.ownTaskResponses}" var="ownTaskResponse" varStatus="stau">
		<tr><td colspan="2">
			<div class="taskTime">
				<fmt:formatDate value="${ownTaskResponse.responseTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
			</div>
			<div class="taskContent">
				${ownTaskResponse.limitContent}
			</div>
			<div class="moreLink" <c:if test="${fn:length(ownTaskResponse.limitContent)<55}">style="display:none;"</c:if>>
				<a id="ele${stau.count}" class="tigger" href="javascript:void(0);" onmouseout="closeDiv('close${stau.count}')">详情</a>
			</div>
		
			<div id="blk${stau.count}" class="blk" style="display:inline;">
	            <div class="head"><div class="head-right"></div></div>
	            <div class="main">
	                <h2>详细信息</h2>
	                <a href="javascript:void(0)" id="close${stau.count}" class="closeBtn" style="display:none;">关闭</a>
	                <span class="responseContent">${ownTaskResponse.responseContent}</span>
	            </div>
	            <div class="foot"><div class="foot-right"></div></div>
	        </div>
		</td></tr>
	</c:forEach>
	
	
	<!-- 任务接收人	回复循环	开始 -->
	<c:forEach items="${taskInfo.taskPersons}" var="taskPerson" varStatus="stau">
		<tr class="bgTitle">
			<td colspan="2"><a name="${taskPerson.userID}" />
				<div class="taskPerson">${taskPerson.userName}</div>
			</td>
		</tr>
		
		<!-- 接收人回复循环 	开始-->
		<c:forEach items="${taskPerson.taskResponses}" var="taskRespons" varStatus="subStau">
			<tr><td colspan="2">
				<div class="taskTime">
					<fmt:formatDate value="${taskRespons.responseTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
				</div>
				<div class="taskContent">
					${taskRespons.limitContent}
				</div>
				<div class="moreLink" <c:if test="${fn:length(taskRespons.limitContent)<55}">style="display:none;"</c:if>>
					<a id="${stau.count}ele${subStau.count}" class="tigger" href="javascript:void(0);" onmouseout="closeDiv('${stau.count}close${subStau.count}')">详情</a>
				</div>
				
				<div id="${stau.count}blk${subStau.count}" class="blk" style="display:none;">
		            <div class="head"><div class="head-right"></div></div>
		            <div class="main">
		                <h2>详细信息</h2>
		                <a href="javascript:void(0)" id="${stau.count}close${subStau.count}" class="closeBtn" style="display:none;">关闭</a>
		                <span class="responseContent">${taskRespons.responseContent}</span>
		            </div>
		            <div class="foot"><div class="foot-right"></div></div>
		        </div>
			</td></tr>
		</c:forEach>
		
	</c:forEach>
	
     <tr>
       <td align="right">内容：</td>
       <td><a name="reply"><textarea id="responseContent" rows="3" cols="95%" style="background-color: white;"></textarea></td>
     </tr>
     <tr>
       <td colspan="2" align="center">
         <input type="button" class="button15" value="提   交" onclick="javascript:saveTaskResponse();"/>&nbsp;&nbsp;&nbsp;&nbsp;
         <input type="reset" class="button15" value="重   填"/>
       </td>
     </tr>
</table>
   </s:form>
   
   <script type="text/javascript">
Function.prototype.binding = function() {
    if (arguments.length < 2 && typeof arguments[0] == "undefined") return this;
    var __method = this, args = jQuery.makeArray(arguments), object = args.shift();
    return function() {
        return __method.apply(object, args.concat(jQuery.makeArray(arguments)));
    }
}

var Class = function(subclass){
	subclass.setOptions = function(options){
		this.options = jQuery.extend({}, this.options,options);
		for(var key in options){
			if(/^on[A-Z][A-Za-z]*$/.test(key)){
				$(this).bind(key,options[key]);
			}
		}
	}
    var fn =  function(){
        if(subclass._init && typeof subclass._init == 'function'){
            this._init.apply(this,arguments);
        }
    }
    if(typeof subclass == 'object'){
        fn.prototype = subclass;
    }
    return fn;
}

var PopupLayer = new Class({
	options:{
		trigger:null,                            
		popupBlk:null,                           
		closeBtn:null,                           
		popupLayerClass:"popupLayer",            
		eventType:"click",                       
		offsets:{                                
			x:0,
			y:0
		},
		useFx:false,                             
		useOverlay:false,                        
		usePopupIframe:true,                     
		isresize:true,                           
		onBeforeStart:function(){}            
	},
	_init:function(options){
		this.setOptions(options);                
		this.isSetPosition = this.isDoPopup = this.isOverlay = true;    
		this.popupLayer = $(document.createElement("div")).addClass(this.options.popupLayerClass);     
		this.popupIframe = $(document.createElement("iframe")).attr({border:0,frameborder:0});         
		this.trigger = $(this.options.trigger);                         
		this.popupBlk = $(this.options.popupBlk);                       
		this.closeBtn = $(this.options.closeBtn);                       
		$(this).trigger("onBeforeStart");                               
		this._construct()                                               
		this.trigger.bind(this.options.eventType,function(){            
			if(this.isSetPosition){
				this.setPosition(this.trigger.offset().left + this.options.offsets.x, this.trigger.offset().top + this.trigger.get(0).offsetHeight + this.options.offsets.y);
			}
			this.options.useOverlay?this._loadOverlay():null;               
			(this.isOverlay && this.options.useOverlay)?this.overlay.show():null;
			if(this.isDoPopup && (this.popupLayer.css("display")== "none")){
				this.options.useFx?this.doEffects("open"):this.popupLayer.show();
			}							 
		}.binding(this));
		this.isresize?$(window).bind("resize",this.doresize.binding(this)):null;
		this.options.closeBtn?this.closeBtn.bind("click",this.close.binding(this)):null;   
	},
	_construct:function(){
		this.popupBlk.show();
		this.popupLayer.append(this.popupBlk.css({opacity:1})).appendTo($(document.body)).css({position:"absolute",'z-index':2,width:this.popupBlk.get(0).offsetWidth,height:this.popupBlk.get(0).offsetHeight});
		this.options.usePopupIframe?this.popupLayer.append(this.popupIframe):null;
		this.recalculatePopupIframe();
		this.popupLayer.hide();
	},
	_loadOverlay:function(){                
		pageWidth = ($.browser.version=="6.0")?$(document).width()-21:$(document).width();
		this.overlay?this.overlay.remove():null;
		this.overlay = $(document.createElement("div"));
		this.overlay.css({position:"absolute","z-index":1,left:0,top:0,zoom:1,display:"none",width:pageWidth,height:$(document).height()}).appendTo($(document.body)).append("<div style='position:absolute;z-index:2;width:100%;height:100%;left:0;top:0;opacity:0.3;filter:Alpha(opacity=30);background:#000'></div><iframe frameborder='0' border='0' style='width:100%;height:100%;position:absolute;z-index:1;left:0;top:0;filter:Alpha(opacity=0);'></iframe>")
	},
	doresize:function(){
		this.overlay?this.overlay.css({width:($.browser.version=="6.0")?$(document).width()-21:$(document).width(),height:($.browser.version=="6.0")?$(document).height()-4:$(document).height()}):null;
		if(this.isSetPosition){
			this.setPosition(this.trigger.offset().left + this.options.offsets.x, this.trigger.offset().top + this.trigger.get(0).offsetHeight + this.options.offsets.y);
		}
	},
	setPosition:function(left,top){          
		this.popupLayer.css({left:left,top:top});
	},
	doEffects:function(way){                
		way == "open"?this.popupLayer.show("slow"):this.popupLayer.hide("slow");
		
	},
	recalculatePopupIframe:function(){     
		this.popupIframe.css({position:"absolute",'z-index':-1,left:0,top:0,opacity:0,width:this.popupBlk.get(0).offsetWidth,height:this.popupBlk.get(0).offsetHeight});
	},
	close:function(){                      
		this.options.useOverlay?this.overlay.hide():null;
		this.options.useFx?this.doEffects("close"):this.popupLayer.hide();
	}
});

//保存任务回复信息
function saveTaskResponse() {
	var flag = true;
	var taskID = '<s:property value="#request.taskInfo.iD"/>';
	var responseContent = $('#responseContent');
	var responseUserID = '${userInfo.userID}';

	//回复内容是否为空
	if(responseContent.val()==null || responseContent.val()=='') {
		alert('请填写回复内容');
		responseContent.focus();
		flag = false;
	}

	if(flag) {
		//保存任务回复信息
		PerformanceManageDWR.saveTaskResponse(taskID, responseContent.val(), responseUserID,saveTaskResponseBak);
	}
	return;
}
//DWR保存任务回复信息 回调函数
function saveTaskResponseBak(flag) {
	if(flag) {
		alert('回复成功!');
	} else {
		alert('回复失败!');
	}
	$('form').submit();
}

//页面内部跳转
function gotoPath(anchor) {
	window.location.hash = anchor;
	if(anchor == 'reply') {
		$('#responseContent').focus();
	}
}

$(function() {
	<%
	if(taskInfo!=null && taskInfo.getOwnTaskResponses()!=null && taskInfo.getOwnTaskResponses().size()>=1) {
	%>
		var ownTaskResponseSize = '<%= taskInfo.getOwnTaskResponses().size()%>';
		var eleId ;
		var blkId ;
		var closeId ;
		var popupLayer;

		//任务发布人回复增加详情
		for(var i=1; i<=ownTaskResponseSize; i++) {
			eleId = '#ele'+i;
			blkId = '#blk'+i;
			closeId = '#close'+i;
			popupLayer = new PopupLayer({trigger:eleId,popupBlk:blkId,closeBtn:closeId,eventType:"mouseover",offsets:{x:-500,y:0}});
		}
	<%
	}
	%>
	

	<%
	int i = 0;
	int j = 0;
	//任务接收人回复增加详情
	if(taskInfo!=null && taskInfo.getTaskPersons()!=null && taskInfo.getTaskPersons().size()>=1) {
		for(TaskPerson person : taskInfo.getTaskPersons()) {
			i++;
			if(person.getTaskResponses()!=null && person.getTaskResponses().size()>=1) {
				for(TaskResponse taskResponse : person.getTaskResponses()) {
					j++;
				%>
					eleId = '#<%=i%>'+'ele'+'<%=j%>';
					blkId = '#<%=i%>'+'blk'+'<%=j%>';
					closeId = '#<%=i%>'+'close'+'<%=j%>';
					popupLayer = new PopupLayer({trigger:eleId,popupBlk:blkId,closeBtn:closeId,eventType:"mouseover",offsets:{x:-500,y:0}});
				<%
				}
			}
			j=0;
		}
	}
	%>
	//防止页面初始化锚点聚焦
	$('#publisher').focus();
});

function closeDiv(idObj) {
	$('#'+idObj).click();
}
</script>
</body>
</html>