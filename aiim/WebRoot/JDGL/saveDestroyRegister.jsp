<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.orifound.aiim.entity.ArchivesInfo"%>
<%@page import="com.orifound.aiim.entity.FieldValue"%>
<%@page import="com.orifound.aiim.entity.ArchivesTypeDataItem"%>
<%@page import="com.orifound.aiim.entity.RetentionPeriod"%>
<%@page import="com.orifound.aiim.web.util.WebCommonUtil"%>
<%@ include file="/JXGL/taglib.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Map dataItems = (Map)request.getAttribute("dataItems");
List archivesInfos = (List)request.getAttribute("archivesInfos");
List<RetentionPeriod> retentionPeriods = (List<RetentionPeriod>)request.getAttribute("retentionPeriods");
WebCommonUtil webCommonUtil = new WebCommonUtil();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>存毁鉴定登记</title>
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
		  <%-- <s:if test="#request.archivesInfos == null">
              $("#find").css("display","block");
		   </s:if>
		   <s:elseif test="#request.archivesInfos.size == 0">
		      $("#find").css("display","block");
		   </s:elseif>
		   <s:else>
              $("#find").css("display","none");
		   </s:else>--%>

		 	//注册分页图片按钮 转到页面
			$('#gotoPageImg').click(function(){
				gotoPage('conditionForm');
			});

			//鉴定日期默认当前时间
		   $('#AppraisalDate').val(new Date().Format('yyyy-MM-dd'));
		 });


		//打印页面显示
		function showPrint(){
			var nodeId = $('#nodeId').val();
			openWindow("archiveAppraisalAction_searchBeforeSaveDestroyPrint.action?nodeId="+nodeId,800,600);
		}

		//打开增加任务对话框
		function showPrint1(){
			var nodeId = $('#nodeId').val();
			var url = "archiveAppraisalAction_searchBeforeSaveDestroyPrint.action?nodeId="+nodeId;
			showWinModalDialog(url, '800px', '600px');
		}

		function openWindow(url,width,height) {
			window.open(url,"newwindow","height="+height+",width="+width+",left="+(window.screen.width-width)/2+",top="+(window.screen.height-height)/2+",Toolbar=no,Menubar=no,scrollbars=yes,resizable=no,location=no,status=no");
		}

		//存毁鉴定登记
		function toRegister() {
			var flag = true;
			//检测是否整个页面的保存期限都提交
			$("#checkList > tr").each(function(){
				var selectDOM = $(this).find('td').find('select');
				if(flag) {
					if(selectDOM.val() <= -1) {
						flag = false;
						$(this).click();
						alert('请选择保存期限！');
						selectDOM.focus();
					}
				}
			});

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

			if(flag) {
				$('form').attr('action','JDGL/archiveAppraisalAction_saveDestroyRegister.action');
				$('form').submit();
			} 

			return;
		}
	</script>
</head>
<body>
<s:form id="conditionForm" name="conditionForm" action="archiveAppraisalAction_searchBeforesaveDestroyRegister" namespace="/JDGL">
		<input type="hidden" name="preSelectRow" id="preSelectRow" />
		<input type="hidden" name="dataPageInfo.currentPage" id="currentPage" value="1"><!-- 当前页码 -->
		<s:hidden id="nodeId" name="nodeId"/>
		<table width="100%" style="margin:0px;" cellspacing="0" cellpadding="0">
			<tr>
				<td><a href="PRINT/print_config.jsp">目录打印</a>
					<img src="images/register.gif"
						onmouseover="changeImage(this,'register2.gif')"
						onmouseout="changeImage(this,'register.gif')" onclick="showfind(this)" alt="登记"/>
					&nbsp;<img src="images/printing.gif" onmouseover="changeImage(this,'printing2.gif')" onmouseout="changeImage(this,'printing.gif')" onclick="showPrint()"/>
					<div style="margin-right:2px; color:blue; display:block; width:300px; float:right; margin-top:5px;" align="right">
						<font style="font-size:12px;font-weight:bold;">当前位置：</font>档案鉴定&nbsp;&gt;&gt;&nbsp;存毁鉴定登记</div >					
				</td>
			</tr>
			<tr>
				<td>
				  <fieldset id="find" style="display: none;">
					<table class="findTB" style="font-size: 12px; display: block;" align="center">
					    <tr>
							<td align="right">
							档案形成部门：
							</td>
							<td align="left">
								<s:select id="formationDepartmentID" name="formationDepartmentID" list="formationDepartments" listKey="iD" listValue="name" headerKey="0" headerValue="*请选择*" />
							</td>
						</tr>
					    <tr style="height: 40px;">
							<td></td>
							<td align="left">
								<input type="submit" value="" class="submitButton" onmouseover="changeSubmitBgImage(this,'search2.gif')" onmouseout="changeSubmitBgImage(this,'search.gif')" onclick="javascript:document.getElementById('findDiv').style.display='none';" />
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
								<td>鉴定结果</td>
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
					   	    	 	
					   	    	    out.print("<td><select id=\"retentionPeriod"+archivesInfo.getNBXH()+"\" name=\"retentionPeriod"+archivesInfo.getNBXH()+"\">");
					   	    	    out.print("<option value=-1>*请选择*</option>");
					   	    	 	for(RetentionPeriod period : webCommonUtil.getLimitedRetentionPeriods(archivesInfo.getTotalYears())) {
					   	    	 	out.print("<option value="+period.getID()+">"+period.getName()+"</option>");
					   	    	 	}
					   	    	 	out.print("<option value=0>销毁</option>");
					   	    	    out.print("</select></td>");
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
					<fieldset>
						<legend>存毁鉴定信息登记</legend>
							<table cellpadding="0" cellspacing="0" style="width:100%;font-size: 12px;">
								<tr style="height:24px;">
									<td>
										鉴定人<input type="text" id="AppraisalPersion" name="AppraisalPersion"  style="margin-right:10px; margin-left:23px; width: 322px;" value="${userInfo.realName}"/>
										鉴定日期<input type="text" id="AppraisalDate" name="AppraisalDate" style="width: 80px"><image style="margin-right:20px;" src="images/dropdownTime.gif" onclick="PopUpCalendar('appraisalDate',true)"  />
									</td>
								</tr>
								<tr style="height:24px;">
									<td>
									存毁意见<textarea id="AppraisalReason" name="AppraisalReason" style="width:486px;margin-left:11px;"></textarea>
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