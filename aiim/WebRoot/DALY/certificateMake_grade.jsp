<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/JXGL/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>" target="_self">

		<title>出证学生成绩</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="css/common.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/popup.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript" src="js/jquery.form.js"></script>
		<script type="text/javascript" src="js/json2.js"></script>
		<style type="text/css">
select {
	width: 155px;
}

#courseList input {
	width: 60px;
}
</style>

		<script type="text/javascript">
		//js成绩对象
		function Grade(iD,courseNameCN, term, totalHour, credit, grade) {
			this.iD = iD;
			this.courseNameCN = courseNameCN;
			this.term = term;
			this.totalHour = totalHour;
			this.credit = credit;
			this.grade = grade;
		}

		//全局学生课程成绩信息对象数据
		var global_GradeArray = new Array();
		
		//构建学生成绩对象数组
		function buildGradeArray(flag) {
			//每行的列数
			var tdSize = new Number($('#courseList tr:first').children('td').size());
			var gradeObj;
			var XH = $('#XH').val();
			$('#courseList > tr').each(function(){
				gradeObj = new Grade($(this).children().eq(0).find('input:first').val(),
						$(this).children().eq(1).find('input:first').val(),
						$(this).children().eq(2).find('input:first').val(),
						$(this).children().eq(3).find('input:first').val(),
						$(this).children().eq(4).find('input:first').val(),
						$(this).children().eq(5).find('input:first').val());
				
				global_GradeArray.push(gradeObj);
			});
			$('#gradeArrayJSON').val(JSON.stringify(global_GradeArray));
			//公选课程选择
			if(flag == 'open') {
				checkCourse();
			}
			$('form').submit();
			if(flag == 'close') {
				window.returnValue= '1';
			    window.close();
			}
			return;
		}

		//页面初始化 设置页面input标签属性
		$(function(){
			$('#courseList > tr').each(function(){
				$(this).children('td').each(function() {
					var tdObj = $(this);
					$(this).children('input:first')
						.css("font-size",tdObj.css("font-size"))
						.width(tdObj.width())
						.css("background-color",tdObj.css("background-color"));
				});
			});
		});

		//设置DOM对象显示属性
		function setDomDisplay(domId, displayValue) {
			$('#'+domId).css('display',displayValue);
		}

		//公选课程 选择点击行时 选中
		function courseCheck(domId) {
			var dom = $('#'+domId);
			dom.attr('checked',!dom.attr('checked'));
		}
		
		//查询公选课程
		function findElectivesCourse() {
			ArchivesCertificateManageDWR.findElectivesCourse($('#XH').val(), $('#courseName').val(),findElectivesCourseBack);
		}

		//查询公选课程回调函数
		function findElectivesCourseBack(electivesCourse) {
			if(electivesCourse.length>=1) {
				var commonCourseList = $('#commonCourseList');
				commonCourseList.html('');
				var bgColor = '#eef5ff';
				for(var i=0; i<electivesCourse.length; i++) {
					//设置行的背景色
					if((i+1)%2 == 0) {
						bgColor = '#e0edff';
					} else {
						bgColor = '#eef5ff';
					}
					commonCourseList.append('<tr onclick=\"courseCheck(\'course'+i+'\');\" bgcolor=\"'+bgColor+'\"><td><input type=\"checkbox\" id=\"course'+i+'\" value=\"'+electivesCourse[i].courseNameCN+'\"/></td><td>'+electivesCourse[i].courseNameCN+'</td></tr>');
				}
				$('#trCourse').show();
			} else {
				alert('无公选课程选择！');
				$('#find').hide();
			}
		}

		//公选课程选择
		function checkCourse() {
			//获取选择的公选课程
			var checkboxArray = new Array();
			var checkboxDOM;
			$("#commonCourseList > tr").each(function(){
				checkboxDOM = $(this).children('td').find(':checkbox');
				if(checkboxDOM.attr('checked')) {
					checkboxArray.push(checkboxDOM.val());
				}
			});
			
			//判断是否选择公选课程
			if(checkboxArray.length >= 1) {
				ArchivesCertificateManageDWR.saveElectivesCourse($('#XH').val(), checkboxArray, saveElectivesCourseBack);
			}
		}
		
		function saveElectivesCourseBack(studentGradeInfos) {
		}
	</script>
	</head>
	<body style="margin-top: 0px">
		<s:form id="baseForm"
			action="archivesCertificateManageAction_updateGrage"
			namespace="/DALY">
			<input type="hidden" name="preSelectRow" id="preSelectRow" />
			<s:hidden id="XH" name="XH" />
			<s:hidden id="gradeArrayJSON" name="gradeArrayJSON" />
			<div class="back_border" style="background-color:white;">
				<image src="images/gxkc.gif"
					onmouseover="changeImage(this,'gxkc1.gif')"
					onmouseout="changeImage(this,'gxkc.gif')"
					onclick="setDomDisplay('find','inline');" />
			</div>
			<table class="back_border"
				style="height: 98%; width: 100%; margin: 0px;background-color:white;" align="center"
				cellpadding="0" cellspacing="0">
				<tr id="find" style="display: none; width: 100%;">
					<td align="center">
						<fieldset>
							<input type="hidden" name="useType" value="JD" />
							<input type="hidden" name="dataPageInfo.currentPage"
								id="currentPage" value="1">
							<table class="findTB" style="background-color:white;">
								<tr>
									<td align="right" class="text">
										课程名称
										<input type="text" id="courseName" name="courseName" />
										<image src="images/search.gif" onclick="findElectivesCourse()"
											onmouseover="changeImage(this,'search2.gif')"
											onmouseout="changeImage(this,'search.gif')" />
									</td>
								</tr>
							</table>
						</fieldset>
					</td>
				</tr>
				<tr id="trCourse" style="display:none;">
					<td valign="top">
						<table id="showTable" width="100%"
							style="margin: 0px; border: #104da6 1px solid;" cellspacing="1px"
							cellpadding="0px">
							<thead class="tableHead" align="center">
								<tr class="bgTitle">
									<th>选择</th>
									<th>课程名称</th>
								</tr>
							</thead>
							<tbody id="commonCourseList" align="center">
							</tbody>
						</table>
						<table style="height: 30px; margin: 0px;" cellpadding="5" width="100%">
							<tr>
								<td align="center">
									<input type="button" id="btOk" class="button" value="确&nbsp;定" onclick="buildGradeArray('open');"/>
									&nbsp;&nbsp;
									<input type="button" id="btCancel" class="button"
										value="取&nbsp;消" onClick="javascript:window.close();" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class="bg_title">
						${certificateStudent.nameCN} 学生成绩明细
					</td>
				</tr>
				<tr>
					<td valign="top">
						<table id="showTable" width="100%"
							style="margin: 0px; border: #104da6 1px solid;" cellspacing="1px"
							cellpadding="0px">
							<thead class="tableHead" align="center">
								<tr class="bgTitle">
									<th>
										序号
									</th>
									<th>
										课程名称
									</th>
									<th>
										学期
									</th>
									<th>
										学时
									</th>
									<th>
										学分
									</th>
									<th>
										成绩
									</th>
								</tr>
							</thead>
							<tbody id="courseList" align="center">
								<s:iterator value="#request.studentGradeInfos" status="status">
									<s:if test="(#status.index+1)%2==0">
										<s:set name="color" value="'#e0edff'" scope="page"></s:set>
									</s:if>
									<s:else>
										<s:set name="color" value="'#eef5ff'" scope="page"></s:set>
									</s:else>
									<tr bgcolor="${pageScope.color}"
										id="row<s:property value="#status.index+1"/>"
										onclick="selectRow(this)">
										<td width="56px" align="center" height="20px">
											<s:property value="#status.index+1" />
											<input type="hidden"
												id='idObj<s:property value="#status.index"/>'
												value='<s:property value="iD"/>'>
										</td>
										<td width="168px">
											<s:property value="courseNameCN" />
											<input type="hidden"
												id='courseNameCN<s:property value="#status.index"/>'
												value='<s:property value="courseNameCN"/>'>
										</td>
										<td width="84px">
											<input type="text"
												id='term<s:property value="#status.index"/>'
												value='<s:property value="term"/>'>
										</td>
										<td width="84px">
											<input type="text"
												id='totalHour<s:property value="#status.index"/>'
												value='<s:property value="totalHour"/>'>
										</td>
										<td width="84px">
											<input type="text"
												id='credit<s:property value="#status.index"/>'
												value='<s:property value="credit"/>'>
										</td>
										<td width="84px">
											<input type="text"
												id='grade<s:property value="#status.index"/>'
												value='<s:property value="grade"/>'>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
						<table style="height: 30px; margin: 0px;" cellpadding="5"
							width="100%">
							<tr>
								<td align="center">
									<input type="button" id="btOk11" class="button"
										value="确&nbsp;定" onclick="buildGradeArray('close')" />
									&nbsp;&nbsp;
									<input type="button" id="btCancel11" class="button"
										value="取&nbsp;消" onClick="javascript:window.close();" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</s:form>
	</body>
</html>
