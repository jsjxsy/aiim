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

   Map dataItems = (Map)request.getAttribute("dataItems");
   List archivesInfos = (List)request.getAttribute("archivesInfos");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>案卷管理</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

	    <link rel="stylesheet" type="text/css" href="css/common.css">
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="js/jquery.form.js"></script>
		
		<script type="text/javascript" src="dwr/interface/ArchivesInfoManageDWR.js"></script>
	    <script type="text/javascript" src="dwr/engine.js"></script>
	    		
		<script type="text/javascript">
		// 打开日期输入页面
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

		
	//打开原文管理页面
	function ywgl13() {
		window.showModalDialog(
						'<%=basePath%>DAGL/YWGL_view.htm',
						window,
						"dialogWidth:590px; dialogHeight:430px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");
	
	}
	
	//打开原文管理页面
	function ywgl(nbxh) {
	var retval = window.open('/aiim/DAGL/ywgl.jsp?archivesTypeID='+$("#archivesTypeID").val()+'&NBXH='+nbxh,"",
					"dialogWidth:590px; dialogHeight:500px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");
   }



//单击案卷check框事件，控制按钮的可用/不可用状态
	function oneSelect(obj) {
		var elements=document.getElementsByTagName("input");
		var SelAll=document.getElementById("SelectAll");
		var iCount=0;//总数
		var iCheck=0;//选 中总数
		var objHejuan =document.getElementById("imgHejuan");//合卷 
		var objDel=document.getElementById("imgDel");//删除
		//var objChaijuan=document.getElementById("imgChaijuan");//拆卷
		var objSongshen=document.getElementById("imgSongshen");	//提交送审		
		for (i=0; i<elements.length; i++) {
			if (elements[i].type=="checkbox" && elements[i].id != "SelectAll" &&  elements[i].name=='NBXHS') {
				iCount++;
				if (elements[i].checked==true) {
					iCheck++;
				}
			}
		}
		if (iCount==iCheck && iCount>0) {//设置全选 状态
			SelAll.checked=true;
		}
		else {
			SelAll.checked=false;
		}
		objDel.disabled=iCheck==0;
		//objHejuan.disabled =iCheck<2;
		//objChaijuan.disabled=iCheck==0;
		objSongshen.disabled=iCheck==0;
	
		changePic();
	}


	
	//案卷全选时,控制按钮的可用/不可用状态控制
	function allSelect(obj) {
			//var selValue=document.getElementById("dir");
			var elements=document.getElementsByTagName("input");
			var iCount=0;
		///	var objHejuan =document.getElementById("imgHejuan");//合卷 
			var objDel=document.getElementById("imgDel");//删除
			//var objChaijuan=document.getElementById("imgChaijuan");//拆卷
			var objSongshen=document.getElementById("imgSongshen");	//提交送审
			
		//	var objAdd=document.getElementById("imgAdd");
			var objDel=document.getElementById("imgDel");			
			//selValue.value="";
			for (i=0; i<elements.length; i++) {
				if (elements[i].type=="checkbox" && elements[i].id != obj.id && elements[i].title=='parentNBXH') {
					iCount++;
					elements[i].checked = obj.checked;
				}
			}
			objDel.disabled=!obj.checked;
			objSongshen.disabled=!obj.checked;
			changePic();
		}


	//案卷：通过检查按钮的disable属性改变图片颜色
	function changePic() {				
		var objDel=document.getElementById("imgDel");//删除
		//var objChaijuan=document.getElementById("imgChaijuan");//拆卷
		//var objHejuan =document.getElementById("imgHejuan");//合卷 
		var objSongshen=document.getElementById("imgSongshen");	//提交送审
		
		
		if (objDel.disabled==true) {
			objDel.src="images/del3.gif";
		}
		else {
			objDel.src="images/del.gif";
		}
		
		<%--if (objChaijuan.disabled==true) {	
			objChaijuan.src="images/chaijuan3.gif";
		}
		else {
			objChaijuan.src="images/chaijuan.gif";
		}

		if (objHejuan.disabled==true) {	
			objHejuan.src="images/hejuan3.gif";
		}
		else {
			objHejuan.src="images/hejuan.gif";
		}--%>
		
		if (objSongshen.disabled==true) {	
			objSongshen.src="images/songshen3.gif";
		}
		else {
			objSongshen.src="images/songshen.gif";
		}
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
		changeChildPic();//刷新按钮颜色
		$("#parentTitle").html($(obj).children("[id='Title']").text());//在卷内文件列表左上方显示案卷题名
		$("#parentNBXH").val(obj.id);
		findChildArchivesInfosByNBXH(obj.id);//查询案卷卷内文件，并显示出来
	}

	//单击《新增》按钮
	function showAdd() {	
		var obj = new Object();	
	    var NBXHS = new Array();
	    
	    //obj.parentNBXH = 0;
		obj.NBXHS = NBXHS;
		obj.archivesTypeID = $("#archivesTypeID").val();
		obj.operationType="add";
		obj.fileType = 1;//文件级管理 0为文件 1为案卷 2为添加卷内
		
		var returnValue = window.showModalDialog(				
			"<%=basePath%>DAGL/item.jsp",
			obj,
			"dialogWidth=700px;dialogHeight:500px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;resizable=no;");
		if(returnValue==1){
			document.conditionForm.submit();
			}
	}

	//双击查看著录信息
	function showItem(rowObj) {
	    var obj = new Object();	
	   
	    var NBXHS = new Array(rowObj.id);
	     
		obj.archivesTypeID = $("#archivesTypeID").val();
		obj.operationType="edit";
		obj.fileType = 1;//文件级管理 0为文件 1为案卷 2为添加卷内
		obj.NBXHS = NBXHS;

		var returnValue = window.showModalDialog(				
			"<%=basePath%>DAGL/item.jsp",
			obj,
			"dialogWidth=700px;dialogHeight:500px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;resizable=no;");
		if(returnValue==1){
			document.conditionForm.submit();
		}
    }

	<%--//单击《拆卷》按钮
	function chaijuan(){	
		$("#ajform").ajaxSubmit({
		    url:"DAGL/archivesInfoManageAction_brokeArchivesInfo.action",
		    success:function(data){
		       alert(data);
		       document.conditionForm.submit();
		    },
		    error:function(XMLHttpRequest, textStatus){alert(textStatus+","+XMLHttpRequest.status);}
		});
	}

	//单击《合卷》按钮
	function hejuan(){
	   var obj = new Object();	
	   var NBXHS = new Array();
	   
	   //得到案卷目录列表被选中的
	   var rows = $("#showTable").rows;
	   for(var i=1;i<rows.length;i++){
	      if(rows[i].cells[0].firstChild.checked == true){
	         NBXHS.push(rows[i].cells[0].firstChild.value);
	      }
	   }
	    //obj.parentNBXH = 0;
		obj.NBXHS = NBXHS;
		obj.archivesTypeID = $("#archivesTypeID").val();
		obj.operationType="hejuan";
		obj.fileType = 1;//文件级管理 0为文件 1为案卷 2为添加卷内
		
		var returnValue = window.showModalDialog(				
			"<%=basePath%>DAGL/item.jsp",
			obj,
			"dialogWidth=700px;dialogHeight:500px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;resizable=no;");
		if(returnValue==1){
			window.location.reload();
		}
	}		--%>

     //单击《删除》按钮
	 function clickBatchDel(){
	     if(confirm("确认删除？")){
	         $("#ajform").ajaxSubmit({
             	url:"DAGL/deleteArchivesInfos.action",
			    success:function(data){
				    alert(data);
			      document.conditionForm.submit();
			    },
			    error:function(XMLHttpRequest, textStatus){alert(textStatus+","+XMLHttpRequest.status);}	     
		     });
	     }		 
	}

	  //单击《提交审核》按钮
	 function clickSubmitCheck(){
	     $("#ajform").ajaxSubmit({
             url:"DAGL/submitToInputCheck.action",
		    success:function(data){
			    alert(data);
		      document.conditionForm.submit();
		    },
		    error:function(XMLHttpRequest, textStatus){alert(textStatus+","+XMLHttpRequest.status);}	     
	     });
	}

	//////////////// DWR //////////////////		
	//DWR：查询卷内文件
	function findChildArchivesInfosByNBXH(nbxh){
		 ArchivesInfoManageDWR.findChildArchivesInfosByNBXH($("#archivesTypeID").val(),nbxh,findChildArchivesInfosByNBXHBack);	
	}
	function findChildArchivesInfosByNBXHBack(data){//查询卷内文件回调函数	
	    var $JuanNeiListBody = $("#JuanNeiListBody");
	    
	    //清空tbody中原有的代码
	    $JuanNeiListBody.html("");
	    
	    var html = "";
	    var bgcolor = "#eef5ff";

	    $("#childRecordNum").text(data.length);
	    for(var i=0;i<data.length;i++){
	    
	      //设置行颜色
	      if(i%2==0){bgcolor = "#eef5ff"}else{bgcolor = "#e0edff"}
	      
	       //遍历集合生成html代码
	       html = html+"<tr bgcolor=\""+bgcolor+"\" id=\""+data[i].NBXH+"\" title=\"双击查看详细信息\" onclick=\"clickRowJN(this)\" ondblclick=\"showChildItem(this)\">";
	       html +="<td><input type=\"checkbox\" name=\"NBXHS\" id=\"childNBXHS\" onclick='childOneSelect(this)' value=\""+data[i].NBXH+"\" /></td>";
	       html +="<td>"+(i+1)+"</td>";
		    var rowFieldsValues = data[i].rowFieldsValues;
		    
		    <s:iterator value="#request.dataItems" var="dataItem">
			     for(var property in rowFieldsValues){ 
			          if(property == '<s:property value="#dataItem.key"/>'){
			            if(rowFieldsValues.<s:property value="#dataItem.key"/>.value == null){
			               html += "<td></td>";
			            }else{
			               html += "<td>"+rowFieldsValues.<s:property value="#dataItem.key"/>.value+"</td>";
			            }
			         }	
		         }
	        </s:iterator>
	      html += "<td><a href=\"javascript:ywgl('"+data[i].NBXH+"')\">查看</a></td>";
	      html += "</tr>";
	      
	      //将代码添加到tbody
	      $JuanNeiListBody.html(html);
	    }
		childOneSelect(null);	
	}
	
	
	//////////////////// 卷内  /////////////////////////////
	
	//单击卷内check框事件，控制按钮的可用/不可用状态
	function childOneSelect(obj) {
		var elements=document.getElementsByTagName("input");			
		var iCheck=0;//选 中总数
		var objDel=document.getElementById("imgChildDel");//删除
		<%--var objFenjuan=document.getElementById("imgChildFenjuan");//分卷
		var objChaifen =document.getElementById("imgChildChaifen");//拆分 --%>		
		for (i=0; i<elements.length; i++) {
			if (elements[i].type=="checkbox" && elements[i].id=='childNBXHS') {					
				if (elements[i].checked==true) {
					iCheck++;
				}
			}
		}	
		objDel.disabled=iCheck==0;				
		<%--objFenjuan.disabled =iCheck==0;
		objChaifen.disabled=iCheck==0;--%>
		changeChildPic();
	}
	
	//改变卷内按钮显/隐
	function changeChildPic(){
		var objAdd=document.getElementById("imgChildNew");//删除
		var objDel=document.getElementById("imgChildDel");//删除
		var objFenjuan=document.getElementById("imgChildFenjuan");//分卷
		var objChaifen =document.getElementById("imgChildChaifen");//拆分 	
	
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
		
		<%--if (objFenjuan.disabled==true) {	
			objFenjuan.src="images/fenjuan3.gif";
		}
		else {
			objFenjuan.src="images/fenjuan.gif";
		}
	
		if (objChaifen.disabled==true) {	
			objChaifen.src="images/chaifen3.gif";
		}
		else {
			objChaifen.src="images/chaifen.gif";
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
		var obj = new Object();	
			var NBXHS= new Array();
			NBXHS[0] = $("#parentNBXH").val();
		    //alert();
			obj.NBXHS = NBXHS;
			obj.archivesTypeID = $("#archivesTypeID").val();
			obj.operationType="add";
			obj.fileType = 2;//文件级管理 0为文件 1为案卷 2为添加卷内
			
			var returnValue = window.showModalDialog(				
				"<%=basePath%>DAGL/item.jsp",
				obj,
				"dialogWidth=700px;dialogHeight:500px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;resizable=no;");
			if(returnValue==1){
				document.conditionForm.submit();
				}
	}
	
	//卷内：双击行，查看著录信息
	function showChildItem(rowObj) {
		    var obj = new Object();	
		   
		    var NBXHS = new Array($("#parentNBXH").val(),rowObj.id);
		     
			obj.archivesTypeID = $("#archivesTypeID").val();
			obj.operationType="edit";
			obj.fileType = 2;//文件级管理 0为文件 1为案卷 2为添加卷内
			obj.NBXHS = NBXHS;
			
			var returnValue = window.showModalDialog(				
				"<%=basePath%>DAGL/item.jsp",
				obj,
				"dialogWidth=700px;dialogHeight:500px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;resizable=no;");
			if(returnValue==1){
				document.conditionForm.submit();
			}
	}
	
	//卷内：单击《删除》按钮
	function clickChildBatchDel(){	
		 if(confirm("确认删除？")){
	         $("#wjform").ajaxSubmit({
             	url:"DAGL/deleteArchivesInfos.action",
			    success:function(data){
				    alert(data);
			        $("#JuanNeiListBody").children().children().children("input:checked").parent().parent().remove();
			    },
			    error:function(XMLHttpRequest, textStatus){alert(textStatus+","+XMLHttpRequest.status);}	     
		     });
	     }		 
	}

	<%--//卷内：单击《拆分》按钮
	function clickSeparate(){
		var archivesInfo={
				NBXH:$("#parentNBXH").val(),
	 	    	 archivesTypeID:$("#archivesTypeID").val()     	    	
	 	    	};
		var arr=new Array();
		var elements=document.getElementsByTagName("input");	
		for (i=0; i<elements.length; i++) {
			if (elements[i].type=="checkbox" && elements[i].name == "childNBXHS") {				
				if (elements[i].checked==true) {
					arr.push(elements[i].value);
				}
			}
		}
		ArchivesInfoManageDWR.separateArchivesInfos(233,arr,archivesInfo,separateArchivesInfosBack);
	}
	function separateArchivesInfosBack(data){//拆分回调函数
		var nbxh = $("#parentNBXH").val();
		findChildArchivesInfosByNBXH(nbxh);	
		alert(data);
	}
	
	//卷内：单击《分卷》按钮
	function clickFenjuan(){
		var obj = new Object();	
		   var NBXHS = new Array();
		   
		   //得到案卷目录列表被选中的
		   var rows = $("#juanNeiTable").children();
		   for(var i=1;i<rows.length;i++){
		      if(rows[i].cells[0].firstChild.checked == true){
		         NBXHS.push(rows[i].cells[0].firstChild.value);
		      }
		   }
	
		    //obj.parentNBXH = 0;
			obj.NBXHS = NBXHS;
			obj.archivesTypeID = $("#archivesTypeID").val();
			obj.operationType="fenjuan";
			obj.fileType = 1;//文件级管理 0为文件 1为案卷 2为添加卷内
			
			var returnValue = window.showModalDialog(				
				"<%=basePath%>DAGL/item.jsp",
				obj,
				"dialogWidth=700px;dialogHeight:500px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;resizable=no;");
			if(returnValue==1){
				window.location.reload();
			}
	}--%>

	$(document).ready(function(){
	   <%
		       if(archivesInfos == null){
		    	   out.print("$(\"#find\").css(\"display\",\"block\");");
		    	   out.print("$(\"#find\").attr(\"alt\",\"隐藏查询(Q)\");");
		       }else if(archivesInfos.size() == 0){
		    	   out.print("$(\"#find\").css(\"display\",\"block\");");
		    	   out.print("$(\"#find\").attr(\"alt\",\"隐藏查询(Q)\");");
		       }else{
		    	   out.print("$(\"#find\").attr(\"alt\",\"显示查询(Q)\");");
		    	   out.print("$(\"#find\").css(\"display\",\"none\");");
		       }
	  %>
	   getArchivesTypeByID();
	 });


	 function addToYJList(){
		   $("#ajform").attr("action","YJGL/YJJSAction_addArchivesToTransferList.action");
	       $("#ajform").ajaxSubmit({
	    	   beforeSubmit:function(){
		          if($("#showTable").children("tbody").children().children(":nth-child(1)").children(":checked").length <= 0){
	                 alert("请选择要移交的档案信息！");
	                 return false;
				  }
		        }, 
		        success:function(data){
		          if(data != null && data != ""){
			          alert(data);
				  }else{
					  alert("添加到移交清单成功！");
					  document.conditionForm.submit();
			      }
		        },
		        error:function(XMLHttpRequest, textStatus){alert(textStatus+","+XMLHttpRequest.status+","+XMLHttpRequest.responseText);}
	       });
		}

		//打开当前的移交清单
		function findCurrentQD(type)
		{
			var returnValue = window.showModalDialog("/aiim/YJGL/YJJSAction_findCurrentQD.action?type="+type+"&deptType=XCBM&stateType=3","newwindow","dialogWidth=1000px;dialogHeight=600px;center=yes;help=no;resizable=no;status=no;scroll=no;");
			if(returnValue==1){
				document.conditionForm.submit();
			}
		}

	 
	// 获得档案类型对象
	function getArchivesTypeByID(){
	    ArchivesInfoManageDWR.getArchivesTypeByID($("#archivesTypeID").val(),getArchivesTypeByIDCallBack);
	}
	function getArchivesTypeByIDCallBack(data){
	    $("#archivesTypeNameText").text(data.fullName+"("+data.fullCode+")");
	}
	  </script>
	</head>
	<body  >
		<%--案卷级管理（默认）--%>
		<table width="100%" style="margin: 0px;" cellspacing="0"
			cellpadding="0">
			<tr>
				<td> 
					<%-- 按钮 --%>
					<input type="image" id="imgAdd" src="images/new.gif" onclick="showAdd();"
						onmouseover="changeImage(this,'new2.gif')"
						onmouseout="changeImage(this,'new.gif')" />
					<input type="image" id="imgDel" src="images/del3.gif" 
						onmouseover="changeImage(this,'del2.gif')"
						onmouseout="changeImage(this,'del.gif')"  disabled="disabled"  onclick="clickBatchDel()"/>
				<%--  	<input type="image" id="imgChaijuan" src="images/chaijuan3.gif"
						onmouseover="changeImage(this,'chaijuan2.gif')"
						onmouseout="changeImage(this,'chaijuan.gif')" onclick="chaijuan();" disabled="disabled" />
					<input type="image" id="imgHejuan" src="images/hejuan3.gif"
						onmouseover="changeImage(this,'hejuan2.gif')"
						onmouseout="changeImage(this,'hejuan.gif')" onclick="hejuan();" disabled="disabled" />--%>
					<input type="image" src="images/find.gif"
						onmouseover="changeImage(this,'find2.gif')"
						onmouseout="changeImage(this,'find.gif')" onclick="showfind(this)" alt="隐藏查询(Q)"/>
					<input type="image" id="imgSongshen" src="images/songshen3.gif"
						onmouseover="changeImage(this,'songshen2.gif')"
						onmouseout="changeImage(this,'songshen.gif')"
						onclick="clickSubmitCheck()" disabled="disabled" />
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
		<form name="conditionForm" action="DAGL/archivesInfoManageAction_findArchivesInfosByCondition.action" method="post" style="margin: 0;padding: 0;">
		   <input type="hidden" name="fileType" id="fileType" value="1"/><!-- 文件类型0：文件 1：案卷 2：卷内 -->
		   <input type="hidden" name="dataPageInfo.currentPage" id="currentPage" value="1"/><!-- 当前页码 -->
		   <input type="hidden" id="archivesTypeID" name="archivesTypeID" value="${requestScope.archivesTypeID }"/>	
		  <fieldset id="find" style="display: none;">		
			<table class="findTB" style="font-size: 12px; display: block;" align="center">
			    <%=request.getAttribute("htmlCode") %>
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
		<input type="hidden" id="deptType" name="deptType" value="XCBM"/>
		<input type="hidden" name="fileType" id="fileType" value="1"/>
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
					<th width="35px">选择</th>
					<th style="width: 30px;">序号</th>
					<%
					for(Object dataItem : dataItems.values()){
					   out.print("<th>"+((ArchivesTypeDataItem)dataItem).getDisplayText() +"</th>");
					}
					%>
					<th align="center">操作</th>
				</tr>
			</thead>
			<tbody>
			 <%
	   	     ArchivesInfo archivesInfo = null;
	   	     if(archivesInfos != null){
	   	        for(int i =0;i<archivesInfos.size();i++){
	   	    	    archivesInfo  = (ArchivesInfo)archivesInfos.get(i);
	   	    	
	   	    	    if(i%2 == 0){    		
	   	    		    out.println("<tr bgcolor=\"#eef5ff\" id=\""+archivesInfo.getNBXH()+"\"  title=\"双击查看详细信息\" onclick=\"clickRowAJ(this)\" ondblclick=\"showItem(this)\">");   	    		
	   	    	    }else{
	   	    		    out.println("<tr bgcolor=\"#e0edff\"  id=\""+archivesInfo.getNBXH()+"\" title=\"双击查看详细信息\" onclick=\"clickRowAJ(this)\" ondblclick=\"showItem(this)\">");
	   	    	    }

	   	    	    out.println("<td align=\"center\"><input type=\"checkbox\" name=\"NBXHS\" title=\"parentNBXH\" value=\""+archivesInfo.getNBXH() +"\" onclick=\"oneSelect(this)\"></td>");
	   	    	    out.println("<td >"+(i+1)+"</td>");
			
	   	    	    for(Object dataItem : dataItems.values()){
	   	    		   if(archivesInfo.getRowFieldsValues().get(((ArchivesTypeDataItem)dataItem).getColumnName()).getValue() == null){
	   	    			  out.println("<td></td>");
	   	    		   }else{
					      out.println("<td id=\""+((ArchivesTypeDataItem)dataItem).getColumnName()+"\">"+archivesInfo.getRowFieldsValues().get(((ArchivesTypeDataItem)dataItem).getColumnName()).getValue() +"</td>");
					   }
				    }
	   	    	    out.print("<td align=\"center\"><a onclick=\"javascript:showItem($(this).parent().parent()[0])\" href=\"javascript:function(){return false;}\">编辑</a>&nbsp;&nbsp;<a href=\"javascript:ywgl('"+archivesInfo.getNBXH()+"')\">原文</a></td></tr>\n");
	   	         }
	   	      }
	   	    %>		
			</tbody>	
		</table>	
		</form>
		
		<table width="100%" style="font-size: 12px;">
			 <tr>
			    <td>
			       <input type="checkbox" id="SelectAll" accesskey="S"  name="SelectAll" onclick="allSelect(this)" title="选中/取消 所有记录(S)"  /><label for="SelectAll">全选</label>
			       &nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:addToYJList();">加入移交清单</a>
                   &nbsp;&nbsp;&nbsp;&nbsp;<s:if test="#request.sum == 0"><a href="javascript:alert('当前没有清单！')" >清单档案数量(${requestScope.sum})</a></s:if>
                                           <s:else><a href="javascript:findCurrentQD('yj');" >清单档案数量(${requestScope.sum})</a></s:else>
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
					<!-- <input type="image" src="images/fenjuan3.gif" disabled="disabled" id="imgChildFenjuan" onclick="clickFenjuan()" onmouseover="changeImage(this,'fenjuan2.gif')" onmouseout="changeImage(this,'fenjuan.gif')"  />
					<input type="image" src="images/chaifen3.gif" disabled="disabled" id="imgChildChaifen" onclick="clickSeparate()" onmouseover="changeImage(this,'chaifen2.gif')" onmouseout="changeImage(this,'chaifen.gif')"  />-->
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
		<input type="hidden" name="fileType" id="fileType" value="1"/>
	    <input type="hidden" id="archivesTypeID" name="archivesTypeID" value="${requestScope.archivesTypeID }"/>	
		<table id="showTable" cellpadding="0px" cellspacing="1px">				
			<thead class="tableHead">			
				<tr>
					<th width="35px">选择</th>
					<th style="width: 30px;">序号</th>
					
					<s:iterator value="#request.dataItems">
					  <th><s:property value="value.displayText"/></th>
					</s:iterator>
					
					<th align="center" style="width: 35px">原文</th>
				</tr>
			</thead>
			<tbody id="JuanNeiListBody" >

			</tbody>
	   </table>
	   </form>
	</body>
</html>
