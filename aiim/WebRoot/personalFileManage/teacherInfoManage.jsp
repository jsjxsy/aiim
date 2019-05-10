<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.orifound.aiim.entity.ArchivesTypeDataItem"%>
<%@page import="com.orifound.aiim.entity.ArchivesInfo"%>
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
		<title>教职工档案管理</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

	    <link rel="stylesheet" type="text/css" href="css/common.css">
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="js/jquery.form.js"></script>
		<script type="text/javascript">
		  var j = jQuery.noConflict();
		</script>
		
		
		<script type="text/javascript" src="dwr/interface/teacherFileManageAction.js"></script>
		<script type="text/javascript" src="dwr/interface/ArchivesInfoManageDWR.js"></script>
	    <script type="text/javascript" src="dwr/engine.js"></script>
	    <script type="text/javascript" src="dwr/util.js"></script>
	    		
		<script type="text/javascript">
		//解决浏览器不识别DWRUtil问题
		if (typeof window['DWRUtil'] == 'undefined')
		{
		 window.DWRUtil = dwr.util;
		}
		
/////////// 处理事件  //////////////////////
	//单击案卷行，改变颜色，显示卷内文件列表
	var rowId="";   //保存上一次点击行“tr”的ID；
	var rowColor="";   //保存上一次点击行的颜色
	function clickRowAJ(obj)
	{
		if(document.getElementById(rowId)==null){//第一次点击处理
			rowId=obj.id;	//保存被点击行的ID
			rowColor=obj.style.backgroundColor;//保存被点击行的颜色
			obj.style.backgroundColor='#a3c9ff';		
		}else{
			document.getElementById(rowId).style.backgroundColor=rowColor;
			rowId = obj.id;
			rowColor=obj.style.backgroundColor;
			obj.style.backgroundColor='#a3c9ff';
		}	 
		document.getElementById("imgChildNew").disabled = false;//使添加卷内文件可用
<%--		document.getElementById("imgChaJuan").disabled = false;--%>
		changeChildPic();//刷新按钮颜色
		j("#parentTitle").html(j(obj).children("[id='Title']").text());//在卷内文件列表左上方显示案卷题名
		j("#parentNBXH").val(obj.id);
		findChildArchivesInfosByNBXH(obj.id);//查询案卷卷内文件，并显示出来
	}

	//添加档案信息
	function showAdd() {
	    var obj = new Object();	
		obj.archivesTypeID = j("#archivesTypeID").val();
		obj.operationType="add";
		
		var returnValue = window.showModalDialog(				
			"<%=basePath%>personalFileManage/item_teacherAdd.jsp?archivesTypeID="+j("#archivesTypeID").val(),
			obj,
			"dialogWidth=700px;dialogHeight:500px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;resizable=no;");
		if(returnValue==1){
			document.conditionForm.submit();
		}
	}

	//修改档案信息
	function showEdit(){
		var returnValue = window.showModalDialog(				
			"<%=basePath%>JZGDAGL/teacherFileManageAction_findTeacherInfoByNBXH.action?nbxh="+j("#parentNBXH").val(),
			"",
			"dialogWidth=700px;dialogHeight:500px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;resizable=no;");
		if(returnValue==1){
			document.conditionForm.submit();
		}
		
	}

	//设置以转出状态
	function setMoveOut(){
		j.ajax({
			url:"JZGDAGL/teacherFileManageAction_setMoveOut.action",
			type:"post",
			data:"nbxh=" + j("#parentNBXH").val(),
			success:function(data){alert(data)},
			error:function(XMLHttpRequest, textStatus){alert(textStatus+","+XMLHttpRequest.status);}
	    });
	}
	//////////////// DWR //////////////////		
	//DWR：查询卷内文件
	function findChildArchivesInfosByNBXH(nbxh){
		teacherFileManageAction.findChildByNbxhDWR(nbxh,findChildArchivesInfosByNBXHBack);	
	}
	function findChildArchivesInfosByNBXHBack(data){//查询卷内文件回调函数	
		var i=0;
		var jJuanNeiListBody = j("#JuanNeiListBody");
	    //清空tbody中原有的代码
	    jJuanNeiListBody.html("");
	    
		DWRUtil.addRows("JuanNeiListBody",data,
		[ function(item){ return "<input type='checkbox' name='docIds' onclick='childOneSelect(this)' value="+item.ID+" />"}, 
		  function(item){i++; return i;},
		  function(item){ return item.docTypeID;},	  
		  function(item){ return item.docName;},
		  function(item){ return item.formationDate;},
		  function(item){ return item.copys;},
		  function(item){ return item.pages;}, 
		  function(item){ 
			var hlink = document.createElement("a");
			hlink.setAttribute("href","javascript:editDoc("+item.ID+")"); 
			hlink.innerText="修改";
			return hlink;}
		]
		,
		{escapeHtml:false,
		 rowCreator:function(options) { //自定义 tr 的创建行为 
			var row = document.createElement("tr"); 
			if(options.rowIndex%2==0){
				row.style.backgroundColor="#eef5ff";
			}else{
				row.style.backgroundColor="#e0edff";
			}
			row.id="c"+data[options.rowIndex].ID;
			row.onclick=new Function("clickRowJN(this)");   //添加单击事件
			return row;},
		  cellCreator:function(options) { //自定义 td 的创建行为
　　　　　　　　var td = document.createElement("td");
			  if(options.cellNum  == 7){
				  td.style.textAlign="center";
		      }　　　　　　
　　　　　　　　 return td; 
　　　　　　 }
		});
		childOneSelect(null);	
	}
	
	
	//////////////////// 卷内  /////////////////////////////
	
	//单击卷内check框事件，控制按钮的可用/不可用状态
	function childOneSelect(obj) {
		var elements=document.getElementsByTagName("input");			
		var iCheck=0;//选 中总数
		var objDel=document.getElementById("imgChildDel");//删除	
		for (i=0; i<elements.length; i++) {
			if (elements[i].type=="checkbox" && elements[i].name=='docIds') {					
				if (elements[i].checked==true) {
					iCheck++;
				}
			}
		}	
		objDel.disabled=iCheck==0;
		changeChildPic();
	}
	
	//改变卷内按钮显/隐
	function changeChildPic(){
		var objAdd=document.getElementById("imgChildNew");//删除
		var objDel=document.getElementById("imgChildDel");//删除
		var imgChaJuan=document.getElementById("imgChaJuan");//删除
	
		if (objAdd.disabled==true) {
			objAdd.src="images/new3.gif";
		}
		else {
			objAdd.src="images/new.gif";
		}
		
		if (objDel.disabled==true) {
			objDel.src="images/del3.gif";
		}
		else {
			objDel.src="images/del.gif";
		}

		<%--if (imgChaJuan.disabled==true) {
			imgChaJuan.src="images/masIns3.gif";
		}
		else {
			imgChaJuan.src="images/masIns.gif";
		}--%>
	}
	/////////卷内事件处理:
	//单击卷内
	var childRowId="";   //保存上一次点击行“tr”的ID；
	var childRowColor="";   //保存上一次点击行的颜色
	function clickRowJN(obj)
	{
		if(document.getElementById(childRowId)==null){//第一次点击处理
			childRowId=obj.id;	//保存被点击行的ID
			childRowColor=obj.style.backgroundColor;//保存被点击行的颜色
			obj.style.backgroundColor='#a3c9ff';
		}else{
			document.getElementById(childRowId).style.backgroundColor=childRowColor;
			childRowColor=obj.style.backgroundColor;
			obj.style.backgroundColor='#a3c9ff';
			childRowId = obj.id;
		}	 
	}
	
	//卷内：单击《新增》按钮
	function showChildAdd() {
			var returnValue = window.showModalDialog(				
				"<%=basePath%>JZGDAGL/teacherFileManageAction_findTeacherInfoByNBXHToAddFile.action?nbxh="+j("#parentNBXH").val(),
				"newWindow",
				"dialogWidth=500px;dialogHeight=400px;top=0;left=0;status=no; toolbar=no;menubar=no;scroll:0;resizable=no;");
			if(returnValue==1){
				//alert(j("#parentNBXH").val());
				document.getElementById(j("#parentNBXH").val()).click();
			}
	}
	
	//卷内：修改卷内
	function editDoc(id) {	   	
			var returnValue = window.showModalDialog(				
				"<%=basePath%>JZGDAGL/teacherFileManageAction_findTeacherDocByIdForEdit.action?ID="+id,
				"",
				"dialogWidth=500px;dialogHeight=400px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;resizable=no;");
			if(returnValue==1){
				document.getElementById(j("#parentNBXH").val()).click();
			}
	}
	
	//卷内：单击《删除》按钮 删除卷内
	function clickChildBatchDel(){	
		 if(confirm("确认删除？")){
	         j("#wjform").ajaxSubmit({
             	url:"<%=basePath%>JZGDAGL/teacherFileManageAction_delDoc.action",
			    success:function(data){
				    alert(data);
				    document.getElementById(j("#parentNBXH").val()).click();
			        //j("#JuanNeiListBody").children().children().children("input:checked").parent().parent().remove();
			    },
			    error:function(XMLHttpRequest, textStatus){alert(textStatus+","+XMLHttpRequest.status);}	     
		     });
	     }		 
	}


	j(document).ready(function(){
	   getArchivesTypeByID();
	 });
	 
	// 获得档案类型对象
	function getArchivesTypeByID(){
	    ArchivesInfoManageDWR.getArchivesTypeByID(j("#archivesTypeID").val(),getArchivesTypeByIDCallBack);
	}
	function getArchivesTypeByIDCallBack(data){
	    j("#archivesTypeNameText").text(data.fullName+"("+data.fullCode+")");
	}

	//批量插卷
	function batAdd(){
		var returnValue = window.showModalDialog(				
			"<%=basePath%>JZGDAGL/teacherFileManageAction_getTeacherDocTypes.action",
			"newWindow",
			"dialogWidth=500px;dialogHeight=600px;top=0;left=0;status=no; toolbar=no;menubar=no;scroll:0;resizable=no;");
		if(returnValue==1){
			//alert(j("#parentNBXH").val());
			document.getElementById(j("#parentNBXH").val()).click();
		}
	}
	  </script>
	</head>
	<body  >
		<%--案卷级管理（默认）--%>
		<table width="100%" style="margin: 0px;" cellspacing="0"
			cellpadding="0">
			<tr>
				<td> 
					<input type="image" id="imgAdd" src="images/new.gif" onclick="showAdd()" onmouseover="changeImage(this,'new2.gif')" onmouseout="changeImage(this,'new.gif')" />
					<input type="image" src="images/find.gif" onmouseover="changeImage(this,'find2.gif')" onmouseout="changeImage(this,'find.gif')" onclick="showfind(this)" alt="隐藏查询(Q)"/>	
					<input type="image" src="images/masIns.gif" onclick="batAdd()" id="imgChaJuan"  onmouseover="changeImage(this,'masIns2.gif')" onmouseout="changeImage(this,'masIns.gif')"/>
				</td>
				<td>
					<div id="location"
						style="margin-right: 2px; width: 280px; display: inline-block; float: right; margin-top: 5px; color: blue; text-align: right;">
						<font style="font-size: 12px;"><b>当前位置：</b>档案管理&nbsp;&gt;&gt;&nbsp;&nbsp;案卷管理</font>
					</div>
				</td>
			</tr>
		</table>
		
		<!-- 查询区域     默认隐藏 -->
		<form name="conditionForm" action="JZGDAGL/teacherFileManageAction_findTeacherInfo.action" method="post" style="margin: 0;padding: 0;">
		   <input type="hidden" name="dataPageInfo.currentPage" id="currentPage" value="1"/><!-- 当前页码 -->
		   <input type="hidden" id="archivesTypeID" name="archivesTypeID" value="${requestScope.archivesTypeID }"/>	
		  <fieldset id="find" style="display: none;">		
			<table class="findTB" style="font-size: 12px; display: block;" align="center">
			    <tr>
			      <td class="text">姓名</td>
			      <td><input type="text" name="xm" id="xm" value="${xm}"/></td>
			    </tr>
			    <tr>
			      <td class="text">工资号</td>
			      <td><input type="text" name="gzh" id="gzh" value="${gzh}"/></td>
			    </tr>
				<tr style="height: 40px;">
					<td></td>
					<td align="left">
						<input type="submit" value="" class="submitButton" onmouseover="changeSubmitBgImage(this,'search2.gif')" onmouseout="changeSubmitBgImage(this,'search.gif')" />
					</td>
				</tr>
			</table>
		  </fieldset>
		</form>
		
		<form  name="ajform" method="post" id="ajform" style="margin: 0;padding: 0;">
		<input type="hidden" name="dataPageInfo.currentPage" id="currentPage" value="1"/><!-- 当前页码 -->
	    <input type="hidden" id="archivesTypeID" name="archivesTypeID" value="${requestScope.archivesTypeID }"/>
		<table class="tabletop" width="100%">
			<tr>
                <td class="tableTitle" id="tableTitle">档案&nbsp;&nbsp;<label id="archivesTypeNameText"></label></td>
                <td align="right" class="text">第${dataPageInfo.currentPage}页 共${dataPageInfo.pageCount}页 共${dataPageInfo.rowCount}条记录</td>							                	
            </tr>
		</table>
	    <table id="showTable" cellpadding="0px" cellspacing="1px">				
			<thead class="tableHead">			
				<tr>
					<th style="width: 30px;">序号</th>
					<th>姓名</th>
					<th>工资号</th>
					<th>性别</th>
					<th>出生日期</th>
					<th>省份证号码</th>
					<th>建档时间</th>
					<th>所属学院</th>
					<th>职称</th>
					<th>评职称时间</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			    <s:iterator value="#request.teacherInfos" status="status">
				    <s:if test="#status.odd==true">
			       		<s:set name="color" value="'#e0edff'" scope="page"></s:set>
				    </s:if>
				    <s:else>
				       <s:set name="color" value="'#eef5ff'" scope="page"></s:set>
				    </s:else>	
					<tr bgcolor="${pageScope.color}"  id="<s:property value="NBXH" />" onclick="clickRowAJ(this)">
						<td height="20px"><s:property value="#status.index+1"/></td>
						<td ><s:property value="xm"/></td>
						<td ><s:property value="gzh"/></td>
						<td ><s:property value="xb"/></td>
						<td ><s:property value="csrq"/></td>
						<td ><s:property value="sfzhm"/></td>
						<td ><s:property value="jdsj"/></td>
						<td ><s:property value="ssxy"/></td>
						<td ><s:property value="zc"/></td>
						<td ><s:property value="pzcsj"/></td>
						<td ><s:property value="workFlowStatus"/></td>
						<td align="center">
							<a href="javascript:setMoveOut();">转出</a>
							<a href="javascript:showEdit();">修改</a>
						</td>
					</tr>	
			    </s:iterator>
			</tbody>	
		</table>	
		</form>
		
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
		<hr/>				
		<!-- <img alt="line" src="images/2.gif" style="height:1px;width: 100%;margin: 0px;padding: 0px;" > -->
		
		<table width="100%" style=" margin:0px;" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<input type="image" src="images/new3.gif" disabled="disabled"  onclick="showChildAdd();" id="imgChildNew"  onmouseover="changeImage(this,'new2.gif')" onmouseout="changeImage(this,'new.gif')"  />
					<input type="image" src="images/del3.gif" disabled="disabled" onclick="clickChildBatchDel()"  id="imgChildDel" onmouseover="changeImage(this,'del2.gif')" onmouseout="changeImage(this,'del.gif')"/>
				</td>
			</tr>
		</table>
			
		<table class="tabletop" width="100%">
            <tr>
                <td>
                    <label id="JuanMing" class="tableTitle">卷内目录—<span id="parentTitle" ></span></label>
                </td>
                <td align="right"  class="text" >
                	<label style="margin-right:4px" id="rsInfo">共<span id="childRecordNum"></span>条记录</label>
                </td>							                	
            </tr>
		</table>
		<input type="hidden" name="parentNBXH" id="parentNBXH" >
		<form  name="wjform" method="post" id="wjform" style="margin: 0;padding: 0;">
	    <input type="hidden" id="archivesTypeID" name="archivesTypeID" value="${requestScope.archivesTypeID }"/>	
		<table id="showTable" cellpadding="0px" cellspacing="1px">				
			<thead class="tableHead">			
				<tr>
					<th width="35px">选择</th>
					<th style="width: 30px;">序号</th>
					<th>材料分类编号</th>
					<th>材料名称</th>
					<th>档案形成日期</th>
					<th>份数</th>
					<th>页数</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="JuanNeiListBody" >

			</tbody>
	   </table>
	   </form>
	</body>
</html>
