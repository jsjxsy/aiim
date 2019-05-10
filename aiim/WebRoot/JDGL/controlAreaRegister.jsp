<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.orifound.aiim.entity.ArchivesInfo"%>
<%@page import="com.orifound.aiim.entity.FieldValue"%>
<%@page import="com.orifound.aiim.entity.ArchivesTypeDataItem"%>
<%@page import="com.orifound.aiim.entity.UserRole"%>
<%@page import="com.orifound.aiim.entity.SystemInitializer"%>
<%@page import="com.orifound.aiim.entity.UserInfo"%>
<%@ include file="/JXGL/taglib.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Map dataItems = (Map)request.getAttribute("dataItems");
List<ArchivesInfo> archivesInfos = (List<ArchivesInfo>)request.getAttribute("archivesInfos");
List<UserRole> userRoles = SystemInitializer.getInstance().getUserRoles();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>划控鉴定登记</title>
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

			//鉴定日期默认当前时间
			$('#AppraisalDate').val(new Date().Format('yyyy-MM-dd'));

			//初始化 开放选项
			var PublicFlag = '${PublicFlag}';
			if(PublicFlag == '1') {
				$('#isOpen').attr('checked','checked');
				$('#find').css('display','none');
			} else {
				$('#noOpen').attr('checked','checked');
			}

			//页面初始化 设置第一条记录选中
			var firstBNXH = '<%= archivesInfos!=null&&archivesInfos.size()>=0 ? archivesInfos.get(0).getNBXH() : 0 %>';
			if(firstBNXH!=null && firstBNXH>=1) {
				$('#'+firstBNXH).click();
			}
		 });

		 //保存用户划控的档案内部序号
		 var NBXH = 0;
		 
		 //单击某行档案 查询划控明细
		 function findAppraisalUseScopesRolesByByNBXH(NBXHObj) {
			 //判断内部序号是否有效
			 if(NBXHObj >= 1) {
				 NBXH = NBXHObj;
				 var archivesTypeID = $('#nodeId').val();
				 ArchiveAppraisalDWR.findAppraisalUseScopesDetailByByNBXH(archivesTypeID, NBXHObj, findAppraisalUseScopesRolesByByNBXHBack);
			 }
		 }

		 //DWR 回调函数
		 function findAppraisalUseScopesRolesByByNBXHBack(appraisalUseScopesDetail) {
			 var AppraisalPersion = $('#AppraisalPersion');
			 var AppraisalDate = $('#AppraisalDate');
			 var AppraisalReason = $('#AppraisalReason');
			 if(appraisalUseScopesDetail && appraisalUseScopesDetail!=null) {
				 //清空表单
				 clearFormField();

				 //赋值
				 AppraisalPersion.val(appraisalUseScopesDetail.appraisalPersion);
				 AppraisalDate.val(getFormateDate(appraisalUseScopesDetail.appraisalDate));
				 AppraisalReason.val(appraisalUseScopesDetail.appraisalReason);
				 
				 //判断是否存在划控角色
				 var roleIds = appraisalUseScopesDetail.roleIds;
				 if(roleIds!=null && roleIds.length>=1) {
					 //循环设置 多选框选中
					 for(var i=0; i<roleIds.length; i++) {
						 $('#userRole'+roleIds[i]).attr('checked','checked');
					 }
				 }
			 } else {	//不存在记录 则清空表单域
				 clearFormField();
			 }
		 }

		 //清空表单域
		 function clearFormField() {
			 $('#AppraisalDate').val('');
			 $('#AppraisalReason').val('');
			 $("#checkRoles input:checkbox").each(function(){
				$(this).removeAttr('checked');					
			 });
			 
			 $('#AppraisalPersion').val('<%=((UserInfo)request.getSession().getAttribute("userInfo")).getRealName()%>');
			 $('#AppraisalDate').val(new Date().Format('yyyy-MM-dd'));
		 }
		 
		//划控鉴定登记
		function toRegister() {
			var flag = true;

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

			//循环生成划控的角色id数组
			var roleIds = new Array();
			if(flag) {
				$("#checkRoles input:checkbox").each(function(){
					var checkboxDOM = $(this);
					if(checkboxDOM.attr('checked')) {
						roleIds.push(checkboxDOM.val());
					}
				});
			}

			if(flag) {
				//档案类型id
				var archivesTypeID = $('#nodeId').val();
				var AppraisalReason = $('#AppraisalReason').val();
				var managerUserID = '<%=((UserInfo)request.getSession().getAttribute("userInfo")).getUserID()%>';
				ArchiveAppraisalDWR.controlAreaRegister(NBXH, archivesTypeID, AppraisalReason, AppraisalDate.val(), AppraisalPersion.val(), managerUserID, roleIds, controlAreaRegisterBack);

				if(roleIds==null || roleIds.length<=0) {
					$('#AppraisalReason').val('');
					$('#AppraisalPersion').val('<%=((UserInfo)request.getSession().getAttribute("userInfo")).getRealName()%>');
				}
			}
			 
			return;
		}

		//DWR划控鉴定 回调函数
		function controlAreaRegisterBack(flag) {
			if(flag) {
				alert('划控鉴定成功！');
			} else {
				alert('划控鉴定失败！');
			}
		}
	</script>
</head>
<body>
<s:form id="conditionForm" name="conditionForm" action="archiveAppraisalAction_searchBeforeControlAreaRegister" namespace="/JDGL">
		<input type="hidden" name="preSelectRow" id="preSelectRow" />
		<input type="hidden" name="dataPageInfo.currentPage" id="currentPage" value="1"><!-- 当前页码 -->
		<s:hidden id="nodeId" name="nodeId"/>
		
		<table width="100%" style="margin:0px;" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<input type="image" src="images/find.gif" alt="显示查询(Q)" onmouseover="changeImage(this,'find2.gif')" onmouseout="changeImage(this,'find.gif')" onclick="return showfind(this);"/>
					<div style="margin-right:2px; color:blue; display:block; width:300px; float:right; margin-top:5px;" align="right">
						<font style="font-size:12px;font-weight:bold;">当前位置：</font>档案鉴定&nbsp;&gt;&gt;&nbsp;划控鉴定登记</div>					
				</td>
			</tr>
			<tr>
				<td>
				   <fieldset id="find" style="display: none;">		
						<table class="findTB" style="font-size: 12px; display: block;" align="center">
						    ${htmlCode}
						    <tr style="height: 40px;">
								<td></td>
								<td align="left">
									<input type="submit" value="" class="submitButton" onmouseover="changeSubmitBgImage(this,'search2.gif')" onmouseout="changeSubmitBgImage(this,'search.gif')" />
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
							</tr>
						</thead>
						<tbody id="checkList">
					   	    <%
					   	     ArchivesInfo archivesInfo = null;
					   	     if(archivesInfos != null){
					   	        for(int i =0;i<archivesInfos.size();i++){
					   	    	    archivesInfo  = (ArchivesInfo)archivesInfos.get(i);
					   	    	
					   	    	    if(i%2 == 0){    		
					   	    		    out.print("<tr bgcolor=\"#eef5ff\" id=\""+archivesInfo.getNBXH()+"\"  onclick=\"clickRow(this);findAppraisalUseScopesRolesByByNBXH('"+archivesInfo.getNBXH()+"')\" >");   	    		
					   	    	    }else{
					   	    		    out.print("<tr bgcolor=\"#e0edff\"  id=\""+archivesInfo.getNBXH()+"\" onclick=\"clickRow(this);findAppraisalUseScopesRolesByByNBXH('"+archivesInfo.getNBXH()+"')\" >");
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
		    <tr >
				<td>
					<fieldset>
						<legend>划控鉴定信息登记</legend>
							<table cellpadding="0" cellspacing="0" style="width:100%;font-size: 12px;">
								<tr style="height:24px;">
									<td>
										鉴定人<input type="text" id="AppraisalPersion" name="AppraisalPersion"  style="margin-right:10px; margin-left:23px; width: 338px;" value="${userInfo.realName}"/>
										鉴定日期<input type="text" id="AppraisalDate" name="AppraisalDate" style="width: 80px"><image style="margin-right:20px;" src="images/dropdownTime.gif" onclick="PopUpCalendar('AppraisalDate',true)"  />
									</td>
								</tr>
								<tr style="height:24px;">
									<td>
									划控依据<textarea id="AppraisalReason" name="AppraisalReason" style="width:500px;margin-left:11px;"></textarea>
									</td>
								</tr>
								<tr style="height:30px;">
									<td>
										<div style="float: left;margin-top:30px;FONT-FAMILY: Verdana, Helvetica, sans-serif;font-size: 12px;">划控范围</div>
										<div style="float: left;overflow:auto; width:498px; height:80px; border:thin 1px solid;margin-left:12px;">
											<table>
												<tbody id="checkRoles">
												<%--循环显示角色信息--%>
												<%
												out.print("<tr>");
												for(int i =0; i<userRoles.size(); i++) {
													if( i>=1 && i%3 == 0) {
														out.print("</tr><tr>");
													}
													out.print("<td><input type=\"checkbox\" id=\"userRole"+userRoles.get(i).getID()+"\" value="+userRoles.get(i).getID()+" /><label for=\"userRole"+userRoles.get(i).getID()+"\">"+userRoles.get(i).getName()+"</label></td>");
												}
												out.print("</tr>");
												%>
												</tbody>
											</table>
										</div>
										<div style="margin-top:58px;margin-left:510px;">
											<image src="images/confirm.gif" alt="确定" onmouseover="changeImage(this,'confirm2.gif')" onmouseout="changeImage(this,'confirm.gif')" onclick="javascript:toRegister();"/>
										</div>
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