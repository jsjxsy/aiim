<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="taglib.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"></meta>
    <title>接收人选择</title>
	<meta http-equiv="pragma" content="no-cache"></meta>
	<meta http-equiv="cache-control" content="no-cache"></meta>
	<meta http-equiv="expires" content="0"></meta>
	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	
	<style type="text/css">
		.top {text-align:left;vertical-align: bottom; }
		
		a {
			/*取消链接的下划线*/
			text-decoration:none;
			/*链接充满所有的区域*/
			display:block;
			color: black;
		}
		
		.trBgcolor {background-color:#E8E8FF;}
		
		.main_receiver{position:absolute; visibility:visible;}
		/*左上*/
		.main_left_top{left:50px; top:10px;width:180px; height:21px;z-index:1;}
		
		/*右上*/
		.main_right_top{left:245px; top:10px;width:180px; height:21px;z-index:2;}
		
		/*左下*/
		.main_left_bottom{left:50px; top:31px;width:180px; height:300px;z-index:3;}
		
		/*右下*/
		.main_right_bottom{left:245px; top:31px;width:180px; height:242px;z-index:4;}
		
		/*底部*/
		.main_bottom{left:30px; top:290px; width:411px; height:15px; z-index:5; text-align: center;vertical-align: middle;}
		
		/*提交 取消按钮样式*/
		.button15 {border:#005fd3 solid 1px; background-color:#b0d0ff; vertical-align:middle; font-size:9pt;}
	</style>
	
	<script type="text/javascript">
		//接收人姓名拼接字符串
		var receiverNames = '';
		//接收人id拼接字符串
		var receiverIds = '';
		
		//设置鼠标经过时 tr背景色
		$(function(){
			 PerformanceManageDWR.findTaskPersons({
					callback: function(userInfos) {
						//判断当前年度是否有记录
						if(userInfos!=null && userInfos.length>=1) {
							var tableList = $('#tableList');
							for(var i=0; i<userInfos.length; i++) {
								var tdId = 'a'+userInfos[i].userID;
								$('<tr><td><a id="'+tdId+'" href="javascript:void(0);" onclick="choicePerson(\''+userInfos[i].userID+'\')">'+userInfos[i].realName+'</a><input type="hidden" id="h'+tdId+'" value="'+userInfos[i].userID+'"/></td></tr>').appendTo(tableList);	
								altrow('tableList','trBgcolor','');
							}
						}
					}
			 });

			 //设置任务接收人
			 var parent = window.parent.opener;
			 if(parent) {
				 var taskInfoId = parent.document.getElementById('taskInfoId');
				 if(taskInfoId) {
					 PerformanceManageDWR.findTaskPersonByTaskInfoId(taskInfoId.value,{
							callback: function(userInfos) {
								//判断当前年度是否有记录
								if(userInfos!=null && userInfos.length>=1) {
									var tableList = $('#receiverList');
									for(var i=0; i<userInfos.length; i++) {
										var tdId = 'a'+userInfos[i].userID;
										$('<tr id="receiver'+userInfos[i].userID+'"><td><a id="'+tdId+'" href="javascript:void(0);" onclick="removePerson(\''+userInfos[i].userID+'\')">'+userInfos[i].userName+'</a></td></tr>').appendTo(tableList);	
										altrow('receiverList','trBgcolor','');
										
										//拼接任务接收人信息字符串
										receiverNames += userInfos[i].userName+';';
										receiverIds += userInfos[i].userID+';';
									}
								}
							}
					 });
				}
			 }
			
		});
	
		//选择接收人
		function choicePerson(receiverId) {
			if(receiverId) {
				//设置存在标志位
				var exist = false;
				$('#receiverList').find('tr').each(function(){
					//判断是否已经存在接收人列表
					if($(this).attr('id') == 'receiver'+receiverId) {
						exist = true;
					}
				});
				if(!exist) {
					var nameObj = $('#a'+receiverId).html();
					var trObj = '<tr id="receiver'+receiverId+'"><td><a href="javascript:void(0);" onclick="removePerson(\''+receiverId+'\')">'+nameObj+'</a></td></tr>';
					$(trObj).appendTo('#receiverList');
					receiverNames += $('#a'+receiverId).html()+';';
					receiverIds += $('#ha'+receiverId).val()+';';
					//设置鼠标经过时 tr背景色
					altrow('receiverList','trBgcolor','');
				}
			}
		}

		//删除接收人
		function removePerson(receiverId) {
			//从字符串中删除接收人
			receiverNames = receiverNames.replace($('#a'+receiverId).html()+';','');
			receiverIds = receiverIds.replace($('#ha'+receiverId).val()+';','');

			//删除tr元素
			removeDOM('receiver'+receiverId);
		}

		//删除DOM元素
		function removeDOM(idObj) {
			$('#'+idObj).detach();
		}

		function altrow(tabID,oveClass,outClass) {
			$("#" + tabID).find("tr").each(function(){
				var tr = $(this);
				//鼠标经过 设置
				tr.mousemove(function () {
					tr.addClass(oveClass);
				});
				//鼠标移出 设置 
				tr.mouseout(function () {
					tr.removeClass(oveClass);
					tr.addClass(outClass);
				});
			});
		}

		//提交
		function onSubmit(flag) {
			if(flag) {
				var parent = window.parent.opener;
				if(parent) {
					var taskReceivers = parent.document.getElementById('taskReceivers');
					if(taskReceivers)
					{
						taskReceivers.value = receiverNames.substr(0,receiverNames.length-1);
					}
					var taskReceiverIds = parent.document.getElementById('receiveIds');
					if(taskReceiverIds)
					{
						taskReceiverIds.value = receiverIds.substr(0,receiverIds.length-1);
					}
				}
			}
			window.close();
		}
	</script>
  </head>
<body bgcolor="#ffffff">
<div>
	<!-- 左上 -->
	<div id="qqreceiverr2c2" class="main_receiver main_left_top">
		<div class="top">联系人</div>
	</div>
	<!-- 右上 -->
	<div id="qqreceiverr2c4" class="main_receiver main_right_top">
			<div class="top">接收人</div>
	</div>
	<!-- 左下 -->
	<div id="qqreceiverr3c2" class="main_receiver main_left_bottom">
		<select style="width:180px;border-right-width: 0px;">
				<option>档案管理部门</option>
			</select>
			<div style="BORDER-RIGHT: 1px solid; BORDER-TOP: 1px solid;  overflow: auto; BORDER-LEFT: 1px solid; WIDTH: 100%; BORDER-BOTTOM: 1px solid; HEIGHT: 220px;border-color: blue;">
				<table border="0" width="100%" id="tableList">
				</table>
			</div>
	</div>
	<!-- 右下 -->
	<div id="qqreceiverr3c4" class="main_receiver main_right_bottom">
		<div style="BORDER-RIGHT: 1px solid; BORDER-TOP: 1px solid;  overflow: auto; BORDER-LEFT: 1px solid; WIDTH: 100%; BORDER-BOTTOM: 1px solid; HEIGHT: 100%; border-color: blue;">
			<table border="0" width="100%" id="receiverList">
			</table>
		</div>
	</div>
	<!-- 底部 提交 -->
	<div id="qqreceiver1r4c2" class="main_receiver main_bottom">
		<button type="button" class="button15" onclick="javascript:onSubmit(true);">提交</button>&nbsp;&nbsp;&nbsp;&nbsp;
		<button type="button" class="button15" onclick="javascript:onSubmit(false);">取消</button>
	</div>
	</div>
</body>
</html>