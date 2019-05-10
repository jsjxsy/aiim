<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.orifound.aiim.entity.ArchivesInfo"%>
<%@page import="com.orifound.aiim.entity.FieldValue"%>
<%@page import="com.orifound.aiim.entity.ArchivesTypeDataItem"%>
<%@page import="com.orifound.aiim.entity.AppraisalUseScopesDetail"%>
<%@ include file="/JXGL/taglib.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Map dataItems = (Map)request.getAttribute("dataItems");
List<AppraisalUseScopesDetail> results = (List<AppraisalUseScopesDetail>)request.getAttribute("appraisalUseScopesDetails");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>划控鉴定登记信息</title>
 	<link href="css/Styles.css" type="text/css" rel="stylesheet" />
	<link href="css/common.css" type="text/css" rel="stylesheet" />
	
		<script type="text/javascript" src="js/common.js"></script>
		
		<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="js/jquery.form.js"></script>
		<style>  
		.roleList td{border:1px solid #FFFFFF;height:20px;border-width:1px 0 0 1px; }
		.bgTitle1 {background-color:#a3c9ff; 
			 height:23px; 
			 color:#00337d; 
			 border-bottom:#104da6 1px solid; 
			 text-align: center;
 		}
		.bgTitle1 th{border:#FFFFFF 1px solid;border-width:1px 0 0 1px;}
		</style>  
		
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
		       if(results == null){
		    	   out.print("$(\"#find\").css(\"display\",\"block\");");
		       }else if(results.size() == 0){
		    	   out.print("$(\"#find\").css(\"display\",\"none\");");
		       }else{
		    	   out.print("$(\"#find\").css(\"display\",\"none\");");
		    	   
		    	   out.print("$(\"#tr_title\").css(\"display\",\"block\");");
		    	   out.print("$(\"#tr_showTable\").css(\"display\",\"block\");");
		    	   out.print("$(\"#tr_pageConfig\").css(\"display\",\"block\");");
		    	   out.print("$(\"#tr_role\").css(\"display\",\"block\");");
		    	   
		    	   
		    	   //页面初始化设置第一个档案的授权角色
		    	   out.println("findRoleNamesByControlArea(\""+results.get(0).getID()+"\");");
		    	   out.println("$('#row1').click()");
		       }
		   %>

		 	//转到页面
			$('#gotoPageImg').click(function(){
				gotoPage('conditionForm');
			});

		 });

		 //根据划控鉴定信息id 获取授权的角色名称列表
		 function findRoleNamesByControlArea(idObj) {
			 ArchiveAppraisalDWR.findRoleNamesByControlArea(idObj, findRoleNamesByControlAreaBack);
		 }

		 //DWR findRoleNamesByControlArea 回调函数
		 function findRoleNamesByControlAreaBack(roleNames) {
			 var roleList = $('#roleList');
			 roleList.html('');
			 if(roleNames!=null && roleNames.length>=0) {
				 var bgcolor = 'bgcolor="#e0edff"';
				 for(var i=1; i<=roleNames.length; i++) {
					 if(new Number(i)%2 == 0) {
						 bgcolor = 'bgcolor="#eef5ff"'; 
					 } else {
						 bgcolor = 'bgcolor="#e0edff"';
					 }
					 roleList.append('<tr id="role'+i+'" '+bgcolor+' onclick="clickRow(this);"><td>'+i+'</td><td>'+roleNames[i-1]+'</td></tr>');
				 }
			 }
		 }

		 
		//显示详情
		 function showDetail(NBXHOBj,archivesTypeId){
			 var url = "archiveAppraisalAction_controlAreaSearchDetail.action?archivesTypeId="+archivesTypeId+"&NBXH="+NBXHOBj;
			 showWinModalDialog(url ,'500px' ,'360px');
		 }
	</script>
</head>
  
<s:form id="conditionForm" name="conditionForm" action="archiveAppraisalAction_controlAreaSearch" namespace="/JDGL">
		<input type="hidden" name="preSelectRow" id="preSelectRow" />
		<input type="hidden" name="dataPageInfo.currentPage" id="currentPage" value="1"><!-- 当前页码 -->
		<s:hidden id="nodeId" name="nodeId"/>
		<table width="100%" style="margin:0px;" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<input type="image" src="images/find.gif" alt="显示查询(Q)" onmouseover="changeImage(this,'find2.gif')" onmouseout="changeImage(this,'find.gif')" onclick="return showfind(this);"/>
					<div style="margin-right:2px; color:blue; display:block; width:300px; float:right; margin-top:5px;" align="right">
						<font style="font-size:12px;font-weight:bold;">当前位置：</font>鉴定登记信息&nbsp;&gt;&gt;&nbsp;划控鉴定
					</div>				
				</td>
			</tr>
			<tr>
				<td>
				   <fieldset id="find" style="display: none;">		
						<table class="findTB" style="font-size: 12px; display: block;" align="center">
				       <tr>
				           <td style="height: 29px" align="left">档号</td><td><s:textfield id="archivesID" name="archivesID" style="width: 207px" /></td>
				       </tr>				      
				        <tr>
				           <td style="height: 29px" align="left">题名</td><td><s:textfield id="title" name="title" style="width: 207px" /></td>
				       </tr>
			          <tr>
				           <td style="height: 29px;" align="left">归档部门</td>
				           <td>
				           		<s:select id="formationDepartmentID" name="formationDepartmentID" style="width: 210px" list="formationDepartments" listKey="iD" listValue="name" headerKey="0" headerValue="*请选择*" />
				           </td>
				       </tr>
				       <tr>
				           <td style="height: 29px;" align="left">档案类型</td>
				           <td>
				           		<s:select id="archivesTypeId" name="archivesTypeId" style="width: 210px" list="archivesTypes" listKey="iD" listValue="name" headerKey="0" headerValue="*请选择*" />
							</td>
				       </tr>
				      <tr>
			           <td style="height: 29px">鉴定日期	 </td>	          			           	
			           <td align="left"> <s:textfield name="AppraisalDate" id="yjDate" style="width: 75px"/><image style="margin-right:10px;" src="images/dropdownTime.gif" onclick="PopUpCalendar('yjDate',true)"  />
			         		  到<s:textfield name="AppraisalDateEnd" id="yjDate1" style="width: 75px"/><image  src="images/dropdownTime.gif" onclick="PopUpCalendar('yjDate1',true)"  />
			          	</td>
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
			<tr id="tr_title" style="display: none;">
				<td>
					<table class="tabletop" width="100%">
						<tr>
			                <td class="tableTitle">划控鉴定登记信息</td>
			                <td align="right" class="text">第${dataPageInfo.currentPage}页 共${dataPageInfo.pageCount}页 共${dataPageInfo.rowCount}条记录</td>							                	
			            </tr>
					</table>
				</td>			
			</tr>
			<tr id="tr_showTable" style="display: none;">
				<td>
					<table id="showTable" width="100%" style="margin:0px; border: #104da6 1px solid;" cellspacing="1px" cellpadding="0px">						
						<thead class="tableHead">
							<tr class="bgTitle">
								<th style="width:30px;">序号</th>
								<th>归档部门</th>
								<th>档案类型</th>
								<th>档号</th>
								<th style="width: 400px">题名</th>
								<th >鉴定日期</th>					
								<th >详情</th>							
							</tr>
						</thead>
						<tbody id="checkList">
							<s:iterator value="#request.appraisalUseScopesDetails" var="entity" status="stau">
								<s:if test="#stau.count%2==0">
									<tr  bgcolor="#eef5ff" id="row<s:property value="#stau.count"/>" onclick="clickRow(this);findRoleNamesByControlArea('<s:property value="#entity.iD"/>');">
								</s:if>
								<s:else>
									<tr  bgcolor="#e0edff" id="row<s:property value="#stau.count"/>" onclick="clickRow(this);findRoleNamesByControlArea('<s:property value="#entity.iD"/>');">
								</s:else>
									<td><s:property value="#stau.count"/></td>
									<td><s:property value="#entity.formationDepartment"/></td>
									<td><s:property value="#entity.archivesTypeName"/></td>
									<td><a href="#"><s:property value="#entity.archivesID"/></a></td>
									<td><s:property value="#entity.title"/></td>
									<td><fmt:formatDate value="${entity.appraisalDate}" type="date"/></td>
									<td><a href="javascript:showDetail('<s:property value="#entity.NBXH"/>','<s:property value="#entity.archivesTypeID"/>')">详情</a></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</td>
			</tr>
			<tr id="tr_pageConfig" style="display: none;">
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
		</table>
</s:form>
</html>