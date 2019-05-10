<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.orifound.aiim.entity.DataPageInfo" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
 <base href="<%=basePath%>">
 <title>权限管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache"> 
<script src="js/alai_tree.js"></script>
<script src="js/alai_tree_pretty.js"></script>
<script src="js/alai_tree_check.js"></script>
<link rel="stylesheet" type="text/css" href="css/common.css">
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="dwr/interface/UserRightArchivesSecrecyManageDWR.js"></script>
<script type="text/javascript" src="dwr/interface/UserRolesArchivesSecrecyManageDWR.js"></script>
<script type="text/javascript" src="dwr/interface/UserInfoManageDWR.js"></script>
<script type="text/javascript" src="dwr/interface/UserRightSystemFeatureManageDWR.js"></script>
<script type="text/javascript" src="dwr/interface/UserRolesSystemFeatureManageDWR.js"></script>

<script type="text/javascript" src="dwr/util.js"></script>
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript">
	function oneSelect(obj) {
	var elements=document.getElementsByTagName("input");
	var SelAll=document.getElementById("SelectAll");
	var iCount=0;//总数
	var iCheck=0;//选 中总数
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
	}else {
		SelAll.checked=false;
	}
}


//全选时,控制按钮的可用/不可用状态控制
function allSelect(obj) {
		var elements=document.getElementsByTagName("input");
		var iCount=0;
		
		for (i=0; i<elements.length; i++) {
			if (elements[i].type=="checkbox" && elements[i].id != obj.id) {
				iCount++;
				elements[i].checked = obj.checked;
			}
		}
	}

//获取所有已经选择的角色
function getAllCheckedIDS(){
	var IDS =new Array(); 
	var elements=document.getElementsByTagName("input");
	for (i=0; i<elements.length; i++) {
		if (elements[i].type=="checkbox" && elements[i].name == "ID" && elements[i].checked==true) {
			IDS.push(elements[i].value);
		}
	  }	
	  return IDS;
}
</script>
<style type="text/css">
 #divTree{border:0;padding:4;overflow:auto;white-space:nowrap; margin:0px; padding: 0px;}
</style>
<style type="text/css">

body,div,ul,li{
padding:0;

}
body{
font:12px "宋体";

}
a:link{
color:#00F;
text-decoration:none;
}
a:visited {
color: #00F;
text-decoration:none;
}
a:hover {
color: #c00;
text-decoration:underline;
}
ul{ list-style:none;}
/*选项卡1*/
#Tab1{
width:460px;
margin:0px;
padding:0px;
margin:0 auto;}
/*选项卡2*/
#Tab2{

margin:0px;
padding:0px;
margin:0 auto;}
/*菜单class*/
.Menubox {
width:100%;
background:url(http://www.cnwebshow.com/ad/3.gif);
height:28px;
line-height:28px;
}
.Menubox ul{
margin:0px;
padding:0px;
}
.Menubox li{
float:left;
display:block;
cursor:pointer;
width:114px;
text-align:center;
color:#949694;
font-weight:bold;
}
.Menubox li.hover{
padding:0px;
background:#fff;

border-left:1px solid #A8C29F;
border-top:1px solid #A8C29F;
border-right:1px solid #A8C29F;
color:#739242;
font-weight:bold;
height:27px;

}
.Contentbox{
clear:both;
margin-top:0px;
border:1px solid #A8C29F;
border-top:none;

text-align:center;
padding-top:8px;
}
</style>
		
<script type="text/javascript">
<!--
/*第一种形式 第二种形式 更换显示样式*/
function setTab(name,cursel,n){
for(i=1;i<=n;i++){
var menu=document.getElementById(name+i);
var con=document.getElementById("con_"+name+"_"+i);
menu.className=i==cursel?"hover":"";
con.style.display=i==cursel?"block":"none";
}
}
//-->
</script>
<script type="text/javascript" >
//改变图片背景颜色
function changeImage(obj,img) {
	if (obj) {
		obj.src="images/"+img;
	}
}


//打开简易查询页面
function showfind()
{
	document.getElementById('findDiv').style.display=(document.getElementById('findDiv').style.display=='none')? 'block':'none';
}

</script>

<style type="text/css">
/*设置标题底色*/
.bgTitle { background-color:#a3c9ff;height:25px;}
/*设置表格顶部框底色*/
.borderTop {	border-top:#104da6 1px solid;	border-left:#104da6 1px solid;	border-right:#104da6 1px solid;}
body 
{
	height:100%;
	color: #000000; 
	font-size:13px;
	margin:0; 
	background-color:White;
}
/*表头*/
.tableTitle {font-weight:bold; text-align:left; padding:4px 0 0 5px;}
.text{ font-size:9pt;}
.tableHead{
	font-weight:bold; text-align:center; padding:4px 0 0 0px;
}
/*
#showTable tbody tr:hover{
	background-color:#a3c9ff;
}
*/
/*设置被选行的颜色*/
.selectRowColor{
	background-color:#a4caef;
}

table tr{
	height:20px;
}
</style>
<style type="text/css">
/*设置标题底色*/
.bgTitle { background-color:#a3c9ff;height:25px;}
/*设置表格顶部框底色*/
.borderTop {	border-top:#104da6 1px solid;	border-left:#104da6 1px solid;	border-right:#104da6 1px solid;}
body 
{
	height:100%;
	color: #000000; 
	font-size:12px;
	margin:0; 
	background-color:White;
}
/*表头*/
.tableTitle {font-weight:bold; text-align:left; padding:4px 0 0 5px;}
.text{ font-size:9pt;}
.tableHead{
	font-weight:bold; text-align:center; padding:4px 0 0 0px;
}
/*
#showTable tbody tr:hover{
	background-color:#a3c9ff;
}
*/
/*设置被选行的颜色*/
.selectRowColor{
	background-color:#a4caef;
}
</style>
</head>
<body>
<div style="margin-right:2px; display:inline; width:300px; color:blue; float:right; margin-top:5px;" align="right">
<font style="font-size:12px;font-weight:bold;">当前位置：</font>系统管理与维护&nbsp;&gt;&gt;&nbsp;系统权限管理</div >
<tr>
	  <td style="margin-top:3px;">
			<input type="image" src="images/find.gif"
									onmouseover="changeImage(this,'find2.gif')"
									onmouseout="changeImage(this,'find.gif')" onclick="showfind()" alt="显示查询(Q)"/>	
  	</td>
</tr>
<script type="text/javascript">
if (typeof window['DWRUtil'] == 'undefined')
{
 window.DWRUtil = dwr.util;
}
var rowCount;
var pageCount;
var currentPage = 1;
function queryUser(){
	var DataPageInfo = {
			pageSize:10,
			currentPage:currentPage
			};
	var IDCardNo = document.getElementById("IDCardNo").value;
	var RealName = document.getElementById("RealName").value;

	UserInfoManageDWR.findAllUserInfo(IDCardNo,RealName,DataPageInfo,callback);
}
function callback(data){

		 var data1 = data["userInfos"];
         if(data1){
     		var i=0;
     		delTable();
     		DWRUtil.addRows("showBody",data1,
     		[ function(item){ return ++i;},
     		  function(item){ return item.userName;},	  
     		  function(item){ return item.realName;},
     		  function(item){ return item.IDCardNo;},
     		  function(item){ return item.departmentName;}
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
     			row.id=data1[options.rowIndex].userID;
     			row.title="双击查看详细信息";
     			row.onclick=new Function("clickRow(this)");
     			row.ondblclick=new Function("showInfo(this)")   
     			return row;}
     		});
     		alert("查询成功！");
     	}else{
     		alert("查询失败！");
     		}

 		var dataPageInfo = data["dataPageInfo"];
 		 pageCount = dataPageInfo.pageCount;
 		 currentPage = dataPageInfo.currentPage;
 		 rowCount = dataPageInfo.rowCount;
		document.getElementById("rsInfo").innerHTML="第"+currentPage+"页  共"+pageCount+"页 共"+rowCount+"条记录";		 
      
}

	

var rowId="";   //保存上一次点击行“tr”的ID；
var rowColor=""; 
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

function showInfo(obj){
	var userID = obj.id;
	if(userID == null || userID == ""){
		alert("用户编号非法为空！");
		}else{
			window.location.href="getUserRightSystemFeatureTree.action?UserID="+userID;
			}
	
}
//删除卷内表格body
function delTable(){
	DWRUtil.removeAllRows('showBody');
}

var pageid = 1; //当前页码
var totalpage = 1;//总共有多少页


function goFirstPage(){
		//判断当前是否为第一页，如果为第一页则不必去加载数据 		
		if(currentPage==1){
			alert("已经为首页！");
			return;
		}
		currentPage = 1;
		queryUser();
	}
	
	function goNextPage(){
		if(currentPage == pageCount){
			alert("已经为最末页，没有下1页！");
			return;
		}
		currentPage = currentPage + 1;
		 queryUser();
	}
	
	function goPrePage(){
		if(currentPage == 1){
			alert("已经为第1页，没有上一页！");
			return;
		}
		currentPage = currentPage - 1;
		queryUser();
	}
	
	function goLastPage(){
		if(currentPage == pageCount){
			alert('已经为最末页！');
			return;
		}
		currentPage = pageCount;
		queryUser();
	}
</script>

	<tr>
		<td align="center">
		    <div id="findDiv" style="display:none; width:100%;margin-top:3px; height:150px; ">
			      <fieldset>
			      <input type="hidden" name="dataPageInfo.currentPage" id="currentPage" value="1">
			       <table class="findTB"  align="center">
			         <tr style=" height:12px;">
						<td>
						</td>
					</tr>
					<tr style="height: 26px;" align="right">
						<td>
						证件号码
						</td>
						<td align="left">
							<input type="text" id="IDCardNo"  name="IDCardNo" style="width:310px;" />
						</td>
					</tr>
					<tr style="height: 26px" align="right">
						<td>
						真实姓名 
						</td>
						<td align="left"><input type="text"  id="RealName"  name="RealName" style="width: 310px;"/></td>
					</tr>
			         <tr style="height:40px;">
			         	<td><input type="button" value="" class="submitButton" onmouseover="changeSubmitBgImage(this,'search2.gif')" onmouseout="changeSubmitBgImage(this,'search.gif')" onclick="javascript:document.getElementById('findDiv').style.display='none';document.getElementById('showTable').style.display='block';queryUser();"/></td>
			         </tr>
			       </table>
			     </fieldset>
		</div>
	  </td>
	  <div id="showTable"  width="100%" style="display:none;margin:0px; border: #104da6 1px solid;" cellspacing="1px" cellpadding="0px">
			<table  style="margin:0px;"  width="100%" cellspacing="0" cellpadding="0" >
							<tr class="bgTitle">
								<td style="height:25px" class="borderTop">
									<table style="width:100%;height:25px" cellspacing="0" cellpadding="0">
								            <tr>
								                <td align="left">
								                   	用户列表
								                </td>
								                <td align="right"  class="text" >
								                	<label style="margin-right:4px" id="rsInfo">第页 共页 共条记录</label>
								                </td>							                	
								            </tr>
								    </table>
								</td>
							</tr>
						</table>		
					<table width="100%" style="margin:0px; border: #104da6 1px solid;" cellspacing="1px" cellpadding="0px" >						
							<thead class="tableHead">
								<tr class="bgTitle">
								<th width="68">序号</th>
								<th width="80">用户名</th>								
								<th width="130">真实姓名</th>
								<th width="100">证件号码</th>
								<th width="100">部门名称</th>
								</tr>
							</thead>
							<tbody  id="showBody" >
								<tr  bgcolor="#eef5ff" id="row11" onclick="clickRow1(this)"  >
									<td></td>
									<td></td>
									<td ></td>
									<td ></td>
									<td ></td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
				
				<tr>
					<td>
						<table width="100%" border="1">
							 <tr>
								<td align="left">									  
								</td>
							<td align="right">
								<input type="image" src="images/firsts1.gif" onclick="goFirstPage()"/>	
								<input type="image" src="images/previouss1.gif"  onclick="goPrePage()"/>
								<input type="image" src="images/nexts.gif"  onclick="goNextPage()"/>
								<input type="image" src="images/lasts.gif" onclick="goLastPage()"/>
								转到第<input type="text" style="width:15px; height:15px" id="gopage"/>页
								<input type="image" src="images/gos.gif" onclick=""/>
							</td>
							</tr>
						</table>
					</td>
				</tr>		
		</div>	
	<div id="Tab2" style="display:block;" >
	<div class="Menubox">
		<ul>
			<li id="two1" onclick="setTab('two',1,3)" class="hover" >系统功能授权</li>
			<li id="two2" onclick="setTab('two',2,3)">档案分类资源授权</li>
			<li id="two3" onclick="setTab('two',3,3)">档案密级授权</li>
		</ul>
	</div>

	<div class="Contentbox">
	<!--系统功能授权-->
	<div id="con_two_1"  align="center"  style="display:block;">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" style="height: 100%;margin: 0px;">
	    <tr>
	      <td align="left" valign="top"><div id="divTree1"></div></td>
	    </tr>
	    <tr>
	    <td align="center">
	     <input type="button" value="提交" onclick="modifySystemFeature()"> &nbsp;&nbsp;&nbsp;&nbsp; 
	     <input type="button" value="重置" > 
	    </td>
	    </tr>
	</table>
 	<script>
		  //记录当前选中的节点
		  var selectedNode;
		  //构造数
		   var tree=new alai_tree_pretty(divTree1);
		   var root=tree.root;
		   ${tree}	
		   //所有的节点默认关闭  
		   tree.expandAll(false);
		   //单击checkBox事件	  
		   tree.oncheck =function(srcNode){
		      if(srcNode.hasChild){
			    var children = srcNode.children;
				 var checked = srcNode.checkBox.checked;
					for(var i=0;i<children.length;i++){
					    children[i].checkBox.checked=checked;
			  }		
		  	}else{
			  var checked = srcNode.checkBox.checked;
			  if(checked == true){
			  srcNode.parent.checkBox.checked = checked;
			  }
		  }
		}
		
	   //得到所有的选中的末节点
	   function getKeys1(){	
		 var keys = new Array();
		 var colChkNodes = tree.colChkNode;
		 for(var i=0;i<colChkNodes.length;i++){
			if(colChkNodes[i].checkBox.checked){
					keys.push(colChkNodes[i].getKey());	
			 }
		 }	
		return keys;
	   }
	   function modifySystemFeature(){
		      <% String type = (String)request.getAttribute("type");
		         if(type.equals("user") ){
		         	%>
		         	UserRightSystemFeatureManageDWR.modifyUserRightSystemFeature(getKeys1(),${UserID},callback);
		         	function callback(data){
			         	if(data){
				         	alert("用户系统功能权限修改成功！");
			         		}else{
							alert("用户系统功能权限修改失败！");
				         	}
			         	}
		         	<%
		         } if(type.equals("userrole") ){
		        	%>
		        	UserRolesSystemFeatureManageDWR.modifyUserRolesSystemFeature(getKeys1(),${RoleID},callback);
		         	function callback(data){
			         	if(data){
				         	alert("角色系统功能权限修改成功！");
			         		}else{
							alert("角色系统功能权限修改失败！");
				         	}
			         	}
		         	<%}%>
		 	  }
	   </script>
	</div>
		<!--档案分类资源授权-->
		<div id="con_two_2" style="display:none">
		       <% String type2 = (String)request.getAttribute("type");
		         if(type2.equals("user") ){
		         	%>
		         <!-- 用户档案分类资源授权 -->
		         <iframe name="iframepage" id="iframepage" marginwidth="0" marginheight="0"  vspace="0" hspace="0" src="QXGL/getUserRightArchivesTypeTree.action?UserID=${UserID}"    frameborder="no" scrolling="no"    width="100%" >
		         </iframe>
		        <%
		         } if(type2.equals("userrole") ){
		        %>
		        <!-- 角色档案分类资源授权 -->
		        <iframe name="iframepage" id="iframepage" marginwidth="0" marginheight="0"  vspace="0" hspace="0" src="QXGL/getUserRolesArchivesTypeTree.action?RoleID=${RoleID}"    frameborder="no" scrolling="no"    width="100%" >
		         </iframe>
		       <%}%>
		 	 <script type="text/javascript" language="javascript">
				//iframe自适应高度
				var ifm = document.getElementById("iframepage");
				ifm.height=document.body.scrollHeight; 
		    </script> 
		</div>
		
		<div id="con_two_3" style="display:none;">
			<table  width="100%" style="margin:0px; border: #104da6 1px solid;"  cellspacing="1px" cellpadding="0px" >						
						<thead class="tableHead">
							<tr class="bgTitle">
								<th width="33%">选择</th>								
								<th width="*%"><p align="left">档案密级名称 </p></th>								
							</tr>
						</thead>
						<tbody>
							
							<s:iterator var="archivesSecrecy" value="#request.srcArchivesSecrecys" status="a">
							<tr  bgcolor="#e0edff" id="row1">
								<td align="center">
									<input type="checkbox" id="ID" name="ID" value="<s:property value="#archivesSecrecy.key"/>"/>
								</td>
								<td><s:property value="#archivesSecrecy.value.Name"/></td>
								</tr>
							</s:iterator>
							<tr>
								<td>
								<input type="checkbox" id="SelectAll" accesskey="S"
									name="SelectAll" onclick="allSelect(this)" title="选中/取消 所有记录(S)" />
								<label for="SelectAll">全选</label>
								</td>
							</tr>
					
						</tbody>
					</table>
					<script type="text/javascript">
						var elements=document.getElementsByTagName("input");
						for (i=0; i<elements.length; i++) {
							<s:iterator var="distArchivesSecrecy" value="#request.distArchivesSecrecys">
							if (elements[i].type=="checkbox" && elements[i].name == "ID" && elements[i].value == <s:property value="SecrecyID"/> ) {
									elements[i].checked=true;
							}
							</s:iterator>
						}

						 function modifyArchivesSecrecy(){
							 	var IDS = new Array();
			         			IDS = getAllCheckedIDS();
						      <% String type3 = (String)request.getAttribute("type");
						         if(type3.equals("user") ){
						         	%>
			         				var userID=${UserID};	 
			         				UserRightArchivesSecrecyManageDWR.modifyUserRightArchivesSecrecy(IDS,userID,callback);
						         	function callback(data){
						         		if(data){
						         		  alert("修改档案密级成功！");
						         		 }else{
						         			alert（”修改密级失败失败！“）；
						         		}
						         	}
						         	<%
						         } if(type3.equals("userrole") ){
						        	%>
						        	var roleID=${RoleID};	 
						        	UserRolesArchivesSecrecyManageDWR.modifyUserRolesArchivesSecrecy(IDS,roleID,callback);
						         	function callback(data){
						         		if(data){
						         		  alert("修改档案密级成功！");
						         		 }else{
						         			alert（”修改密级失败失败！“）；
						         		}
						         	}
						         	<%}%>
						 	  }
					</script>
			<input type="button" value="提交" onclick="modifyArchivesSecrecy()">  <input type="button" value="重置" > 
		</div>
	</div>
</div>
</body>
</html>
