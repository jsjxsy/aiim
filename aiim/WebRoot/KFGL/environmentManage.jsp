<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>库房环境管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" >
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
	var retval = window.showModalDialog(url, "", "dialogWidth:197px; dialogHeight:210px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no;help=no; "  );
	if( retval != null ){
		obj.value = retval;
	}
}	

//处理单击事件
var rowId="";   //保存上一次点击行“tr”的ID；
var rowColor="";   //保存上一次点击行的颜色
function clickRow1(obj)
{
	if(document.getElementById(rowId)==null){//第一次点击处理
		rowId=obj.id;	//保存被点击行的ID
		rowColor=obj.style.backgroundColor;//保存被点击行的颜色
		obj.style.backgroundColor='#a3c9ff';
	
	}else{
		document.getElementById(rowId).style.backgroundColor=rowColor;
		//'#e0edff';
		obj.style.backgroundColor='#a3c9ff';
		rowId = obj.id;
	}
}



//查询
function find()
{
	document.getElementById('findDiv').style.display=(document.getElementById('findDiv').style.display=='none')? 'block':'none';
}


function showAlter(tempratureId){
	alert(tempratureId);
	var returnValue = window.showModalDialog("<%=basePath%>KFGL/storeroomManageAction_getTempratureHumidityInfoByID.action?tempratureId="+tempratureId,"newwindow","dialogWidth=500px;dialogHeight:135px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");
	if(returnValue==true){
		window.location.reload();
	}
}


//打开新增对话框
function showAdd()
{
	var returnValue = window.showModalDialog("<%=basePath%>KFGL/storeroomManageAction_getAddHJDefaultInputItem.action","newwindow","dialogWidth=500px;dialogHeight:135px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");
	if(returnValue==true){
		window.location.reload();
	}
}



//打开简易查询页面
function dlgFind()
{
	window.showModalDialog("DlgFind.htm","newwindow","dialogWidth=600px;dialogHeight:350px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");

}

//打开统计
function openTJ()
{
	window.showModalDialog("KF_HJ_TJ.htm","newwindow","dialogWidth=350px;dialogHeight:150px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");

}


//打开统计
function openSZ()
{
	window.showModalDialog("KF_HJ_SZ.htm","newwindow","dialogWidth=490px;dialogHeight:190px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");
}

//单击删除
function deleteSelected(){
	document.forms["deleteForm"].submit();
}
</script>

  </head>
  <body style="font-size: 12px;">
  <input type="hidden" name="preSelectRow" id="preSelectRow" />
		<table width="100%" cellpadding="0" cellspacing="0" border="0">
	       <tr>
		       <td>		       		          
			   		<input type="image" style="margin-left: 3px;" src="images/new.gif"  onclick="showAdd();" onmouseover="changeImage(this,'new2.gif')" onmouseout="changeImage(this,'new.gif')"  />
					<input type="image" src="images/del.gif"  onmouseover="changeImage(this,'del2.gif')" onmouseout="changeImage(this,'del.gif')"  onclick="deleteSelected()" />
					<input type="image" src="images/find.gif" onmouseover="changeImage(this,'find2.gif')" onmouseout="changeImage(this,'find.gif')" onclick="find()" />
					<!-- 
					<input type="image" src="images/Tongji.gif" onmouseover="changeImage(this,'Tongji2.gif')" onmouseout="changeImage(this,'Tongji.gif')" onclick="openTJ()" />
					<input type="image" src="images/Shezhi.gif" onmouseover="changeImage(this,'Shezhi2.gif')" onmouseout="changeImage(this,'Shezhi.gif')" onclick="openSZ()" />
					 -->
			   </td>
			   <td>
				   <div id="location" style="margin-right: 2px; width: 280px; display: inline-block; float: right; margin-top: 0px; color: blue; text-align: right;">
					  <font style="font-size: 12px;"><b>当前位置：</b>库房管理&nbsp;&gt;&gt;&nbsp;&nbsp;环境管理</font>
				   </div>
			   </td>
	       </tr>
	     </table>
			 
		<form name="conditionForm" action="KFGL/storeroomManageAction_findTempratureHumidityInfosByDate.action" method="post" style="margin: 0;padding: 0;" >
			<div style=" display:none;" id="findDiv">
			<input type="hidden" name="dataPageInfo.currentPage" id="currentPage" value="1"><!-- 当前页码 -->
			<span style="margin-left:5px;">日期</span><input type="text" name="beginDate" value="${beginDate}"   id="beginDate" style="width: 90px" /><img src="images/dropdownTime.gif" style="CURSOR: pointer;"onclick="PopUpCalendar('beginDate',true)" />
		      至<input type="text" name="endDate" style="margin-left:5px; width: 90px;" id="endDate" value="${endDate}" /><img src="images/dropdownTime.gif" style="CURSOR: pointer;"onclick="PopUpCalendar('endDate',true)" />
			<input type="submit" style="margin-left:5px;" value="查询"  onclick="find();"/>
		</div>
		</form>
		<table width="100%" style="margin:0px; font-size: 12px;" cellspacing="0" cellpadding="0">
			<tr>
				<td>
				<table class="tabletop" width="100%">
					<tr>
		                <td class="tableTitle">利用记录</td>
		                <td align="right" class="text">第${dataPageInfo.currentPage}页 共${dataPageInfo.pageCount}页 共${dataPageInfo.rowCount}条记录</td>							                	
		            </tr>
				</table>
			<form name="deleteForm" action="KFGL/storeroomManageAction_deleteTempratureHumidityInfos.action" method="post" style="margin: 0;padding: 0;">
				<table id="showTable" width="100%" style="margin:0px; border: #104da6 1px solid;" cellspacing="1px" cellpadding="0px" >						
					<thead class="tableHead">
						<tr class="bgTitle">
							<th width="35px">选择</th>								
							<th>序号</th>
							<th>库房</th>
							<th>测量日期</th>
							<th>测量时间 </th>								
							<th>温度</th>	
							<th>湿度</th>								
							<th>登记时间</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="#request.tempratureHumidityInfos" status="rowStatus">
							<s:if test="(#rowStatus.index+1)%2==0">
								<s:set name="color" value="'#e0edff'" scope="page"></s:set>
							</s:if>
							<s:else>
								<s:set name="color" value="'#eef5ff'" scope="page"></s:set>
							</s:else>
							<tr bgcolor="${pageScope.color}" id="row<s:property value="ID"/><s:property value="ID"/>" onclick="clickRow1(this)" ondblclick="showAlter(<s:property value="ID"/>)">
								<td align="center" width="30px;"><input type="checkbox" name="tempratures" value="<s:property value="ID"/>" id="<s:property value="ID"/>" /></td>
								<td align="center" width="30px;"><s:property value="#rowStatus.index+1"/></td>
								<td><s:property value="storeroomName"/></td>
								<td><s:date name="measureDate" format="yyyy-MM-dd"/></td>
								<td><s:property value="measureTime"/></td>
								<td><s:property value="temperature"/></td>
								<td><s:property value="humidity"/></td>
								<td><s:date name="recordTime" format="yyyy-MM-dd"/></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			 </td>
		  </tr>
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
	</body>
</html>
