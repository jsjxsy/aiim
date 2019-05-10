<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("basePath",basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
    <base href="<%=basePath%>">
    
    <title>在线申请单详细</title>
    	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" type="text/css" href="css/common.css">
	<script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
    
    <script type="text/javascript" src="dwr/interface/ArchivesUseAction.js"></script>
    <script type="text/javascript" src="dwr/util.js"></script>
    <script type="text/javascript" src="dwr/engine.js"></script>
    
    <script type="text/javascript" >
       //打开日期输入页面
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
				url="${basePath}js/CalendarWithFormat.html";
			}
			else {
				url="${basePath}js/CalendarWithOutFormat.html"
			}
			var retval = window.showModalDialog(url, "", "dialogWidth:197px; dialogHeight:210px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no;help=no; "  );
			if( retval != null ){
				obj.value = retval;
			}
		}	
		//弹出借档登记窗口
		function showBorrow(){
		  returnValue = window.showModalDialog("<%=basePath%>DALY/archivesUseAction_findDefaultDataForDJ.action?useType=JD","newwindow","dialogWidth=550px;dialogHeight=450px;center=yes;scroll=no;status=no;resizable=no;help=no;location=no");
		  if(returnValue==1){//如果新增了档案利用登记信息则刷新页面
			  window.document.location.reload();
		  }
		}

		//查看登记信息
		function showDJXX(registerID){
			alert(registerID);
		   window.showModalDialog("<%=basePath%>DALY/archivesUseAction_findArchivesUseRegisterByID.action?useType=JD&registerID="+registerID,"newwindow","dialogWidth=550px;dialogHeight=450px;center=yes;scroll=no;status=no;resizable=no;help=no;location=no");
		}
		

/////// ---DWR---  ///////	
	//添加利用人信息
	function addUserPersonInfo(){
		var archivesUsePersonInfo;
		archivesUsePersonInfo={
				name:'daitao',
				iDCardNo:32,
				iDCardTypeID:3,
				department:'233',
				tel:'343434',
				email:'adf@126.com',
				areaID:1
	     	    	};
		ArchivesUseAction.addArchivesUsePersonInfo(archivesUsePersonInfo,addUserPersonInfoBack);
	}
	//添加利用人信息
	function addUserPersonInfoBack(data){
		alert(data);
	}


	</script>
	</head>
<body>
		<input type="hidden" name="preSelectRow" id="preSelectRow" />
		<table width="100%" style="margin:0px;" cellspacing="0" cellpadding="0">
			<tr>
			  <td>   
			     <table width="100%" cellpadding="0" cellspacing="0" border="0">
			       <tr>
				       <td>				       		
							<input type="image" src="images/DYDJD.gif" onmouseover="changeImage(this,'DYDJD1.gif')" onmouseout="changeImage(this,'DYDJD.gif')" onclick="alert('print');"/>												
						</td>
						 
						<td>
																		
						</td>
				  </tr>
			   </table>		
			</tr>			
			<tr>
				<td>
					<table class="tabletop" width="100%">
						<tr>
			                <td class="tableTitle">利用登记列表</td>
			                <td align="right" class="text"> 共${recordSize}条记录</td>							                	
			            </tr>
					</table>
					<table id="showTable" width="100%" style="margin:0px; border: #104da6 1px solid;" cellspacing="1px" cellpadding="0px" >						
						<thead class="tableHead">
							<tr class="bgTitle">
								<th width="35px">选择</th>													
								<th width="35px">序号</th>
								<th>档号</th>
								<th>档案分类</th>
								<th>密级</th>
								<th>题名</th>
								<th>利用方式</th>
								<th>审批结果</th>
								<th>打回原因</th>	
								<th>馆藏状态</th>		
								<th align="center" width="60px">操作</th>							
							</tr>
						</thead>
						<tbody>							
						  <s:iterator value="#request.archivesUseRequestDetails" status="status">
						    <s:if test="(#status.index+1)%2==0">
						       <s:set name="color" value="'#e0edff'" scope="page"></s:set>
						    </s:if>
						    <s:else>
						       <s:set name="color" value="'#eef5ff'" scope="page"></s:set>
						    </s:else>
						    <tr  bgcolor="${pageScope.color}" id="row<s:property value="#status.index+1"/>" onclick="selectRow(this)">
						       <td><input type="checkbox" name="ids" value="<s:property value="ID"/>"/></td>
								<td align="center" height="20px"><s:property value="#status.index+1"/></td>
								<td><s:property value="archivesID"/></td>
								<td><s:property value="archivesTypeText"/></td>
								<td><s:property value="secrecyText"/></td>
								<td><s:property value="title"/></td>
								<td><s:property value="useWayText"/></td>
								<s:if test="checkResult==1">
								<td style="color: blue;">审批通过</td>
								</s:if>
								<s:if test="checkResult==2">
								<td style="color: red;">审批不通过</td>
								</s:if>
								<s:if test="checkResult==0">
								<td>待审批</td>
								</s:if>
							
								<td><s:property value="backReason"/></td>
								<td><s:property value="tag"/></td>															
								<td align="center"><a href="javascript:showDJXX('<s:property value="ID"/>')">详细</a></td>	
							</tr>
						  </s:iterator>						
						</tbody>
					</table>	
								    
				 </td>
			</tr>
		</table>
	</body>
</html>
