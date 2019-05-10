<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.orifound.aiim.entity.ArchivesInfo"%>
<%@page import="com.orifound.aiim.entity.FieldValue"%>
<%@page import="com.orifound.aiim.entity.ArchivesTypeDataItem"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ucl" uri="/myTag"%>
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
		<title>文件管理</title>
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
		function ywgl(nbxh) {
		var retval = window.open('/aiim/DAGL/ywgl.jsp?archivesTypeID='+$("#archivesTypeID").val()+'&NBXH='+nbxh,"",
						"dialogWidth:590px; dialogHeight:500px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");
	   }
	
	//单击check框事件，控制按钮的可用/不可用状态
		function oneSelect(obj) {
			var elements=document.getElementsByTagName("input");
			var SelAll=document.getElementById("SelectAll");
			var iCount=0;//总数
			var iCheck=0;//选 中总数
			var objDel=document.getElementById("imgDel");//删除
			var objZujuan=document.getElementById("imgZujuan");//组卷
			var objSongshen=document.getElementById("imgSongshen");	//提交送审	
			var objChaJuan=document.getElementById("imgChaJuan");	//插卷
				
			for (i=0; i<elements.length; i++) {
				if (elements[i].type=="checkbox" && elements[i].id != "SelectAll") {
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
			objDel.disabled=(iCheck==0);
			objZujuan.disabled=(iCheck==0);
			objSongshen.disabled=(iCheck==0);
			objChaJuan.disabled=!obj.checked;
		
			changePic();
		}
	
	//获取所有文件的NBXH,返回值为一个Array，用于子页面的上一条、下一条操作
	function getAllCheckBoxNBXH(){
		var pageNBXHs =new Array(); 
		var elements=document.getElementsByTagName("input");
		for (i=0; i<elements.length; i++) {
			if (elements[i].type=="checkbox" && elements[i].name == 'NBXHS') {
				pageNBXHs.push(elements[i].value);
			}
		}	
		return pageNBXHs;
	}	
		//全选时,控制按钮的可用/不可用状态控制
		function allSelect(obj) {
				var elements=document.getElementsByTagName("input");
				var iCount=0;
				var objDel=document.getElementById("imgDel");//删除
				var objZujuan=document.getElementById("imgZujuan");//组卷
				var objSongshen=document.getElementById("imgSongshen");	//提交送审
				var objChaJuan=document.getElementById("imgChaJuan");	//插卷
				
				var objDel=document.getElementById("imgDel");			
				for (i=0; i<elements.length; i++) {
					if (elements[i].type=="checkbox" && elements[i].id != obj.id) {
						iCount++;
						elements[i].checked = obj.checked;
					}
				}
				objDel.disabled=!obj.checked;
				objZujuan.disabled=!obj.checked;
				objSongshen.disabled=!obj.checked;
				objChaJuan.disabled=!obj.checked;
				changePic();
			}
	
	
		//通过检查按钮的disable属性改变图片颜色
		function changePic() {		
			var objDel=document.getElementById("imgDel");//删除
			var objZujuan=document.getElementById("imgZujuan");//组卷
			var objSongshen=document.getElementById("imgSongshen");	//提交送审
			var objChaJuan=document.getElementById("imgChaJuan");	//插卷

			if (objDel.disabled==true) {
				objDel.src="images/del3.gif";
			}
			else {
				objDel.src="images/del.gif";
			}
			
			if (objZujuan.disabled==true) {	
				objZujuan.src="images/zujuan3.gif";
			}
			else {
				objZujuan.src="images/zujuan.gif";
			}
			
			if (objSongshen.disabled==true) {	
				objSongshen.src="images/songshen3.gif";
			}
			else {
				objSongshen.src="images/songshen.gif";
			}

			if (objChaJuan.disabled==true) {	
				objChaJuan.src="images/masIns3.gif";
			}
			else {
				objChaJuan.src="images/masIns.gif";
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
	
	
	/*翻页函数
	url 请求的url地址
	formName 查询条件的form
	currentPage 当前页
	*/ 
	function pageTurning(formName,currentPage){
	  document.getElementById("currentPage").value=currentPage;
	  document.forms[formName].submit();
	}
	/*
	跳到某一页
	*/
	function gotoPage(formName){
	  var gotoPage = document.getElementById("gotoPage").value;
	  pageTurning(formName,gotoPage);
	}
	//////////////////单击事件////////////////////////
	
	//打开新增对话框
		function showAdd() {
		    var obj = new Object();	
		    var NBXHS = new Array();
		    
		    obj.NBXHS = NBXHS;
			obj.archivesTypeID = $("#archivesTypeID").val();
			obj.operationType="add";
			obj.fileType = 0;//文件级管理 0为文件 1为案卷 2为添加卷内
			
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
			obj.fileType = 0;//文件级管理 0为文件 1为案卷 2为添加卷内
			obj.NBXHS = NBXHS;
			
			var returnValue = window.showModalDialog(				
				"<%=basePath%>DAGL/item.jsp",
				obj,
				"dialogWidth=700px;dialogHeight:500px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;resizable=no;");
			if(returnValue==1){
				document.conditionForm.submit();
			}
	    }
	
		//打开组卷对话框
		function zujuan(){
			var obj = new Object();	
		    
		    var NBXHS = getAllCheckedNBXH();
		     
			obj.archivesTypeID = $("#archivesTypeID").val();
			obj.operationType="zujuan";
			obj.fileType = 0;//文件级管理 0为文件 1为案卷 2为添加卷内
			obj.NBXHS = NBXHS;
			
			var returnValue = window.showModalDialog(				
				"<%=basePath%>DAGL/item.jsp",
				obj,
				"dialogWidth=700px;dialogHeight:500px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;resizable=no;");
			if(returnValue==1){
				document.conditionForm.submit();
			}
		}
		
		function getAllCheckedNBXH(){
			var NBXHS =new Array(); 
			var elements=document.getElementsByTagName("input");
			for (i=0; i<elements.length; i++) {
				if (elements[i].type=="checkbox" && elements[i].name == 'NBXHS' && elements[i].checked==true) {
					NBXHS.push(elements[i].value);
				}
			  }	
			  return NBXHS;
	    }
	
	     //单击批量删除
		 function clickBatchDel(){
			 if(confirm("确认删除？")){
		         $("#wjform").ajaxSubmit({
		        	url:"DAGL/deleteArchivesInfos.action",
				    success:function(data){
					    alert(data);
					    document.conditionForm.submit();
				    },
				    error:function(XMLHttpRequest, textStatus){alert(textStatus+","+XMLHttpRequest.status);}	     
			     });
		     }			 		 
		}
	
		  //提交审核
		 function clickSubmitCheck(){
			 $("#wjform").ajaxSubmit({
	             url:"DAGL/submitToInputCheck.action",
			    success:function(data){
				    alert(data);
				    document.conditionForm.submit();
			    },
			    error:function(XMLHttpRequest, textStatus){alert(textStatus+","+XMLHttpRequest.status);}	     
		     });
		}

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
			   $("#wjform").attr("action","YJGL/YJJSAction_addArchivesToTransferList.action");
		       $("#wjform").ajaxSubmit({
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

		//插卷
		function chaJuan(){
			var returnValue = window.showModalDialog("/aiim/DAGL/archivesInfoManageAction_getHtmlCodeForInputQuery.action?fileType=5&archivesTypeID="+$("#archivesTypeID").val(),"newwindow","dialogWidth=1000px;dialogHeight=600px;center=yes;help=no;resizable=no;status=no;scroll=no;");
			if(returnValue != null){
			   $("#parentNBXH").val(returnValue);
			   $("#wjform").attr("action","DAGL/archivesInfoManageAction_insertFileToArchives.action");
		       $("#wjform").ajaxSubmit({
		    	   beforeSubmit:function(){
			          if($("#showTable").children("tbody").children().children(":nth-child(1)").children(":checked").length <= 0){
		                 alert("请选择要插卷的文件！");
		                 return false;
					  }
			        }, 
			        success:function(data){
			          if(data != null && data != ""){
				          alert(data);
					  }else{
						  alert("插卷成功！");
						  document.conditionForm.submit();
				      }
			        },
			        error:function(XMLHttpRequest, textStatus){alert(textStatus+","+XMLHttpRequest.status+","+XMLHttpRequest.responseText);}
		       });
		    }
	    }
	</script>

	</head>
   
	<body>
	     <table  cellpadding="0" cellspacing="0" align="center" style="display: block; padding: 0; margin: 0; width: 99%">			
			<tr>
				<td>
		<!--文件级管理（默认）-->
					<table width="100%" style="margin: 0px;" cellspacing="0"
						cellpadding="0">
						<tr>
							<td> 
								<!-- 按钮 -->
								<input type="image" id="imgAdd" src="images/new.gif" onclick="showAdd();"
									onmouseover="changeImage(this,'new2.gif')"
									onmouseout="changeImage(this,'new.gif')" />
								<input type="image" id="imgDel" src="images/del3.gif" 
									onmouseover="changeImage(this,'del2.gif')"
									onmouseout="changeImage(this,'del.gif')"  disabled="disabled"  onclick="clickBatchDel()"/>
								<input type="image" id="imgZujuan" src="images/zujuan3.gif"
									onmouseover="changeImage(this,'zujuan2.gif')"
									onmouseout="changeImage(this,'zujuan.gif')" onclick="zujuan();" disabled="disabled" />
								<input type="image" src="images/find.gif"
									onmouseover="changeImage(this,'find2.gif')"
									onmouseout="changeImage(this,'find.gif')" onclick="showfind(this)" alt="显示查询(Q)"/>	
								<input type="image" id="imgSongshen" src="images/songshen3.gif"
									onmouseover="changeImage(this,'songshen2.gif')"
									onmouseout="changeImage(this,'songshen.gif')"
									onclick="clickSubmitCheck()" disabled="disabled" />
								<input type="image" id="imgChaJuan" src="images/masIns3.gif"
									onmouseover="changeImage(this,'masIns2.gif')"
									onmouseout="changeImage(this,'masIns.gif')"
									onclick="chaJuan()" disabled="disabled" />
							</td>
							<td>
								<div id="location" style="margin-right: 2px; width: 280px; display: inline-block; float: right; margin-top: 5px; color: blue; text-align: right;">
									<font style="font-size: 12px;"><b>当前位置：</b>档案管理&nbsp;&gt;&gt;&nbsp;&nbsp;文件管理</font>
								</div>
							</td>
						</tr>
					</table>
        
					<form name="conditionForm" action="DAGL/archivesInfoManageAction_findArchivesInfosByCondition.action" method="post" style="margin: 0;padding: 0;">	
					        <input type="hidden" name="fileType" id="fileType" value="0"><!-- 文件类型0：文件 1：案卷 2：卷内 -->
							<input type="hidden" name="dataPageInfo.currentPage" id="currentPage" value="1"><!-- 当前页码 -->
							<input type="hidden" id="archivesTypeID" name="archivesTypeID" value="${requestScope.archivesTypeID }">
					  <fieldset id="find" style="display: none;">		
						<table class="findTB" style="font-size: 12px; display: block;" align="center">
						    <%=request.getAttribute("htmlCode") %>
						    <tr style="height: 40px;">
								<td></td>
								<td align="left">
									<input type="submit" value="" class="submitButton" onmouseover="changeSubmitBgImage(this,'search2.gif')" onmouseout="changeSubmitBgImage(this,'search.gif')" onclick="javascript:document.getElementById('findDiv').style.display='none';"/>
								</td>
							</tr>
						</table>
					  </fieldset>
					</form>	
					<table class="tabletop" width="100%">
						<tr>
			                <td class="tableTitle" id="tableTitle">档案&nbsp;&nbsp;<label id="archivesTypeNameText"></label></td>
			                <td align="right" class="text">第${dataPageInfo.currentPage}页 共${dataPageInfo.pageCount}页 共${dataPageInfo.rowCount}条记录</td>							                	
			            </tr>
					</table>
					
					<form  name="wjform" method="post" id="wjform" style="margin: 0;padding: 0;">
					<input type="hidden" id="deptType" name="deptType" value="XCBM"/>
					<input type="hidden" name="fileType" id="fileType" value="0"/>
					<input type="hidden" name="parentNBXH" id="parentNBXH"/>
				    <input type="hidden" id="archivesTypeID" name="archivesTypeID" value="${requestScope.archivesTypeID }"/>
					<table  id="showTable" cellpadding="0px" cellspacing="1px">	
					       <thead class="tableHead">			
								<tr>
									<th width="35px">选择</th>
									<th style="width: 30px;">序号</th>
									<%
									for(Object dataItem : dataItems.values()){
									   out.print("<th>"+((ArchivesTypeDataItem)dataItem).getDisplayText() +"</th>\n");
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
						   	    		    out.print("<tr bgcolor=\"#eef5ff\" id=\""+archivesInfo.getNBXH()+"\"  onclick=\"clickRow(this)\" ondblclick=\"showItem(this)\">");   	    		
						   	    	    }else{
						   	    		    out.print("<tr bgcolor=\"#e0edff\"  id=\""+archivesInfo.getNBXH()+"\" onclick=\"clickRow(this)\" ondblclick=\"showItem(this)\">");
						   	    	    }

						   	    	    out.print("<td align=\"center\"><input type=\"checkbox\" name=\"NBXHS\" value=\""+archivesInfo.getNBXH() +"\" onclick=\"oneSelect(this)\"></td>");
						   	    	    out.print("<td >"+(i+1)+"</td>");
								
						   	    	    for(Object dataItem : dataItems.values()){
						   	    		   if(archivesInfo.getRowFieldsValues().get(((ArchivesTypeDataItem)dataItem).getColumnName()).getValue() == null){
						   	    			  out.print("<td></td>");
						   	    		   }else{
										      out.print("<td>"+archivesInfo.getRowFieldsValues().get(((ArchivesTypeDataItem)dataItem).getColumnName()).getValue() +"</td>");
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
				</td>
			</tr>
		</table>
	</body>
</html>
