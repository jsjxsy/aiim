<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.orifound.aiim.entity.ArchivesInfo"%>
<%@page import="com.orifound.aiim.entity.FieldValue"%>
<%@page import="com.orifound.aiim.entity.ArchivesTypeDataItem"%>
<%@page import="com.orifound.aiim.entity.ArchivesSecrecy"%>
<%@page import="com.orifound.aiim.entity.SystemInitializer"%>
<%@ include file="/JXGL/taglib.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Map dataItems = (Map)request.getAttribute("dataItems");
List archivesInfos = (List)request.getAttribute("archivesInfos");
ArchivesSecrecy openArchivesSecrecy = SystemInitializer.getInstance().getOpenArchivesSecrecy();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>公开鉴定登记</title>
 	<link href="css/Styles.css" type="text/css" rel="stylesheet" />
	<link href="css/common.css" type="text/css" rel="stylesheet" />
	
		<script type="text/javascript" src="js/common.js"></script>
		
		<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="js/jquery.form.js"></script>
		<script type="text/javascript" src="js/dateTool.js"></script>
		
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
			var archivesTypeID = $("#archivesTypeID").value;
			window.showModalDialog(
							"<%=basePath%>DAGL/ywgl.jsp?archivesTypeID="+archivesTypeID+"&NBXH="+nbxh,
							window,
							"dialogWidth:590px; dialogHeight:430px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");
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
			objDel.disabled=iCheck==0;
			objZujuan.disabled=iCheck==0;
			objSongshen.disabled=iCheck==0;
		
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
				//var selValue=document.getElementById("dir");
				var elements=document.getElementsByTagName("input");
				var iCount=0;
				var objDel=document.getElementById("imgDel");//删除
				var objZujuan=document.getElementById("imgZujuan");//组卷
				var objSongshen=document.getElementById("imgSongshen");	//提交送审
				
			//	var objAdd=document.getElementById("imgAdd");
				var objDel=document.getElementById("imgDel");			
				//selValue.value="";
				for (i=0; i<elements.length; i++) {
					if (elements[i].type=="checkbox" && elements[i].id != obj.id) {
						iCount++;
						elements[i].checked = obj.checked;
						if (obj.checked==true) {
						//	selValue.value+=",'"+getValue(elements[i].id)+"'";
							objDel.disabled=iCount==0;
							objZujuan.disabled=iCount==0;
							objSongshen.disabled=iCount==0;
						}
						else {
							objDel.disabled=true;
							objZujuan.disabled=true;
							objSongshen.disabled=true;
						}
					}
				}
				changePic();
			}
	
	
		//通过检查按钮的disable属性改变图片颜色
		function changePic() {		
			var objDel=document.getElementById("imgDel");//删除
			var objZujuan=document.getElementById("imgZujuan");//组卷
			var objSongshen=document.getElementById("imgSongshen");	//提交送审

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
		       }else if(archivesInfos.size() == 0){
		    	   out.print("$(\"#find\").css(\"display\",\"block\");");
		       }else{
		    	   out.print("$(\"#find\").css(\"display\",\"none\");");
		       }
		   %>

		 	//转到页面
			$('#gotoPageImg').click(function(){
				gotoPage('conditionForm');
			});
			
			//初始化 开放选项
			var PublicFlag = '${PublicFlag}';
			if(PublicFlag == '1') {
				$('#isOpen').attr('checked','checked');
				$('#find').css('display','none');
				$('#publicField').css('display','none');
			} else {
				$('#noOpen').attr('checked','checked');
			}

			//鉴定日期默认当前时间
			$('#AppraisalDate').val(new Date().Format('yyyy-MM-dd'));
		 });


		//公开鉴定登记
		function toRegister() {
			var flag = true;

			if($('#noOpen').attr('checked')) {
				var AppraisalPersion = $('#AppraisalPersion');
				if(AppraisalPersion.val()==null || AppraisalPersion.val()=='') {
					alert('请选择鉴定人！');
					AppraisalPersion.focus();
					flag =false;
				}
				
				var AppraisalDate = $('#AppraisalDate');
				if(AppraisalDate.val()==null || AppraisalDate.val()=='') {
					alert('请选择鉴定日期！');
					AppraisalDate.focus();
					flag =false;
				}
			}
			
			if(flag) {
				$('form').attr('action','JDGL/archiveAppraisalAction_openRegister.action');
				$('form').submit();
			} 
			return;
		}

		var g_NBXH;
		//取消公开鉴定
		function cancelOpen(NBXH) {
			var archivesTypeId = $('#nodeId').val();
			g_NBXH = NBXH;
			ArchiveAppraisalDWR.cancelPublicOpenRegister(archivesTypeId, NBXH, 0, cancelPublicOpenRegisterBak);
			
		}

		//DWR 回调函数
		function cancelPublicOpenRegisterBak(flag) {
			if(flag) {
				alert('取消公开成功！');
				$('#'+g_NBXH).detach();
			} else {
				alert('取消公开失败！');
			}
		}
	</script>
</head>
<body>
<s:form id="conditionForm" name="conditionForm" action="archiveAppraisalAction_searchBeforeOpenRegister" namespace="/JDGL">
		<input type="hidden" name="preSelectRow" id="preSelectRow" />
		<input type="hidden" name="dataPageInfo.currentPage" id="currentPage" value="1"><!-- 当前页码 -->
		<s:hidden id="nodeId" name="nodeId"/>
		<table width="100%" style="margin:0px;" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<input type="image" src="images/find.gif" alt="显示查询(Q)" onmouseover="changeImage(this,'find2.gif')" onmouseout="changeImage(this,'find.gif')" onclick="return showfind(this);"/>
					<div style="margin-right:2px; color:blue; display:block; width:300px; float:right; margin-top:5px;" align="right">
						<font style="font-size:12px;font-weight:bold;">当前位置：</font>档案鉴定&nbsp;&gt;&gt;&nbsp;开放鉴定登记</div>					
				</td>
			</tr>
			<tr>
				<td>
				   <fieldset id="find" style="display: none;">		
						<table class="findTB" style="font-size: 12px; display: block;" align="center">
						    ${htmlCode}
						    <tr style="height: 26px;" align="left">
								<td>
								是否已公开
								</td>
								<td>
									<input type="radio" name="PublicFlag" id="isOpen" value="1"/><lable for="isOpen">是</lable>
									<input type="radio" name="PublicFlag" id="noOpen" checked="checked" value="0"/><lable for="noOpen">否</lable>
							</tr>
						    <tr style="height: 40px;">
								<td></td>
								<td align="left">
									<input type="submit" value="" class="submitButton" onmouseover="changeSubmitBgImage(this,'search2.gif')" onmouseout="changeSubmitBgImage(this,'search.gif')"/>
								</td>
							</tr>
						</table>
					  </fieldset>
				</td>
			</tr>
			<tr>
				<td>
				<table class="tabletop" width="100%">
						<tr>
			                <td class="tableTitle">待鉴定档案</td>
			                <td align="right" class="text">第${dataPageInfo.currentPage}页 共${dataPageInfo.pageCount}页 共${dataPageInfo.rowCount}条记录</td>							                	
			            </tr>
					</table>
				</td>			
			</tr>
			<tr>
				<td>
					<table id="showTable" width="100%" style="margin:0px; border: #104da6 1px solid;" cellspacing="1px" cellpadding="0px" >						
						<thead class="tableHead">
							<tr>
								<th style="width: 30px;">序号</th>
								<%
								for(Object dataItem : dataItems.values()){
								   out.print("<th>"+((ArchivesTypeDataItem)dataItem).getDisplayText() +"</th>\n");
								}
								%>
								<td>是否公开</td>
							</tr>
						</thead>
						<tbody id="checkList">
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

					   	    	    out.print("<td>"+(i+1)+"<input type=\"hidden\" name=\"NBXH"+archivesInfo.getNBXH()+"\" value="+archivesInfo.getNBXH()+"></td>");
								
					   	    	    for(Object dataItem : dataItems.values()){
					   	    		   if(archivesInfo.getRowFieldsValues().get(((ArchivesTypeDataItem)dataItem).getColumnName()).getValue() == null){
					   	    			  out.print("<td></td>");
					   	    		   }else{
					   	    			   if(((ArchivesTypeDataItem)dataItem).getColumnName().equals("ArchivesID")) {
					   	    					out.print("<td><a href=\"javascript:ywgl(\'"+archivesInfo.getNBXH()+"\')\">"+archivesInfo.getRowFieldsValues().get(((ArchivesTypeDataItem)dataItem).getColumnName()).getValue() +"</a></td>");
					   	    			   } else {
					   	    					out.print("<td>"+archivesInfo.getRowFieldsValues().get(((ArchivesTypeDataItem)dataItem).getColumnName()).getValue() +"</td>");	   
					   	    			   }
									   }
								    }
					   	    	 	
					   	    	//判断是否进行公开鉴定
					   	    	    if(archivesInfo.getSecrecyID()==openArchivesSecrecy.getID()) {	//取消公开鉴定
					   	    	    	out.print("<td align='center'><a href=\"javascript:cancelOpen('"+archivesInfo.getNBXH()+"')\">取消</a></td>");
					   	    	    
					   	    	    } else {	//进行公开鉴定
					   	    	   		//公开	单选按钮
						   	    	    out.print("<td align='center'><input type=\"checkbox\" id=\"openFlag\" name=\"openFlag"+archivesInfo.getNBXH()+"\" ></td>");
					   	    	    }
					   	         }
					   	      }
					   	    %>
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
								<image id="gotoPageImg" src="images/gos.gif" onmouseover="changeImage(this,'gos2.gif')" onmouseout="changeImage(this,'gos.gif')" />                                   
							</td>
						</tr>
					</table>
				</td>
			</tr>
		    <tr>
				<td>
					<fieldset id="publicField">
						<legend>公开鉴定信息登记</legend>
							<table cellpadding="0" cellspacing="0" style="width:100%;font-size: 12px;">
								<tr style="height:24px;">
									<td>
										鉴定人<input type="text" id="AppraisalPersion" name="AppraisalPersion"  style="margin-right:10px; margin-left:25px; width: 322px;" value="${userInfo.realName}"/>
										鉴定日期<input type="text" id="AppraisalDate" name="AppraisalDate" style="width: 80px"><image style="margin-right:20px;" src="images/dropdownTime.gif" onclick="PopUpCalendar('AppraisalDate',true)"  />
									</td>
								</tr>
								<tr style="height:24px;">
									<td>
									公开依据<textarea id="AppraisalReason" name="AppraisalReason" style="width:486px;margin-left:13px;"></textarea>
									<image src="images/confirm.gif" alt="确定" onmouseover="changeImage(this,'confirm2.gif')" onmouseout="changeImage(this,'confirm.gif')" onclick="javascript:toRegister();"/>
									</td>
								</tr>
							</table>
					</fieldset>
				</td>
			</tr>			
		</table>
</s:form>
	</body>
</html>