<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
        <base href="<%=basePath%>" target="_self">
		<title>利用信息登记</title>
		
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="Cache-Control" content="no-cache,  must-revalidate" />
		<meta http-equiv="expires" content="Wed,  26  Feb  1997  08:21:57  GMT" />
		
		<link href="css/common.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="<%=basePath%>js/common.js"></script>
        <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
        
        <script type="text/javascript" src="dwr/interface/ArchivesUseAction.js"></script>
	    <script type="text/javascript" src="dwr/util.js"></script>
	    <script type="text/javascript" src="dwr/engine.js"></script>
		<script type="text/javascript">
		<!--
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
				url="<%=basePath%>/js/CalendarWithFormat.html";
			}
			else {
				url="<%=basePath%>/js/CalendarWithOutFormat.html"
			}
			var retval = window.showModalDialog(url, "", "dialogWidth:197px; dialogHeight:210px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no;help=no; "  );
			if( retval != null ){
				obj.value = retval;
			}
		}

		//当更改用户信息时置UserID为""
		function clearUserID(){
			$("#userID").val(0);
		}
		var users;//定义利用人信息
				
		 //选择
	    function choose(i){
		    alert('asdfadf');
	        var user = users[i];
	        alert(user.tel);
	        $("#userID").val(user.ID);
	    	$("#IDCardNo").val(user.IDCardNo);	    	    	
	    	$("#name").val(user.name);
	    	$("#department").val(user.department);
	    	$("#tel").val(user.tel);
	    	alert(user.tel);
	    	$("#email").val(user.email);
	    	$("#areaID").val(user.areaID);
	        colseDiv();
	    }	

		//档案利用人信息验证
		function inputValidate(){
			if($("#name").val()==""){
				alert("姓名不能为空！");
				return false;
			}
			if($("#IDCardNo").val()==""){
				alert("证件号不能为空！");
				return false;
			}
			if($("#department").val()==""){
				alert("所属部门不能为空！");
				return false;
			}
			if($("#email").val()==""){
				alert("邮箱不能为空！");
				return false;
			}
			if($("#tel").val()==""){
				alert("电话号码不能为空！");
				return false;
			}			
			if($("#areaID").val()==""){
				alert("请选择所属区域！");
				return false;
			}
			return true;
		}
		
		//单击确定按钮
	    function clickOK(){
		    //输入验证
		  //  if(inputValidate() ==false){
		   if(false){
			    return false;
			    }
		    if($("#userPWD").val() != $("#confirm_userPWD").val()){
				alert('密码不一致！');
				return false;
			    }
		    if($("#requestReason").val()==""){
				alert('请输入申请理由！');
				return false;
				}
		    if($("#userDepartment").val()==""){
				alert('请输入用户所属部门！');
				return false;
				}
			
			//用户信息
	    	var userInfo = {
	    			userID:$("#userID").val(),
	    	    	IDCardNo:$("#IDCardNo").val(),	    	    	
	    	    	realName:$("#name").val(),  
	    	    	userName:$("#email").val(),  	    	
	    	    	tel:$("#tel").val(),
	    	    	userPWD:$("#userPWD").val(),
	    	    	address:$("#address").val(),
	    	    	email:$("#email").val()
    	    	};
	    	var userDepartment = $("#userDepartment").val();	
	    	var requestReason = $("#requestReason").val();	
	    	ArchivesUseAction. addRegisterInfoForOnlineApply(userInfo,userDepartment,requestReason,
	    	   {callback:function(message){//执行成功
					alert("申请成功！");
					window.returnValue = true;
					window.close();
		    	   }
   	    		,exceptionHandler:function(message){//执行失败
    				alert("申请失败："+message);
    				}
			   });
		}

	    //处理证件号码的回车事件
		 function getUserInfo(){
			 if(event.keyCode =='13'){
				// alert('wewr');
				// $("#findByIDCardNo").click();
				findByIDCardNo();
								 
		     }			 
	     }

	    //绑定事件：查找利用人信息
		function findByIDCardNo(){
		    //构造档案利用人信息对象，姓名必须赋值
		    var archivesUsePersonInfo = {
				    //alert('asdf');
				    name:$("#name").val(),
				    IDCardNo:$("#IDCardNo").val()
			};
		//调用DWR，通过姓名查询用户信息
	   	ArchivesUseAction.findArchivesUsePersonInfoByIDCardNo(archivesUsePersonInfo,findArchivesUsePersonInfoByIDCardNoBack);
	    }	    
function findArchivesUsePersonInfoByIDCardNoBack1(ds){
	alert('asdfasdf');
}

		//根据姓名查询档案利用人信息回调函数
		function findArchivesUsePersonInfoByIDCardNoBack(data){
			//将数据保存至全局变量
			users = data;	
			if(users.length==1){
				 var user = users[0];
			        $("#name").val(user.name);
			        $("#IDCardNo").val(user.IDCardNo);
			        $("#department").val(user.department);
			        $("#email").val(user.email);
			        $("#tel").val(user.tel);
			        $("#areaID").val(user.areaID);
			        $("#userID").val(user.ID);
				return false;
			}
		    if(users.length!=-1){
		        //删除原有数据
		      $("#usersTable").children("tbody").children().remove();
		      
		      $.each(users,function(i,user){
		         $("#usersTable").children("tbody").append(
		            "<tr bgcolor='#e0edff'>"+
			            "<td align='center'>"+(i+1)+"</td>"+
			            "<td>"+user.name+"</td>"+
			            "<td>"+user.IDCardNo+"</td>"+
			            "<td>"+user.department+"</td>"+
			            "<td align='center'><button onclick='choose("+i+")' class='button'>选择</button></td>"+
			         "</tr>");								         							         
		      });      
		      //打开用户显示层
			  
		    }	  opendiv();
		}
	 
		//-->
		</script>
	</head>
	<body class="bg_color" style="margin-top:4px">		
		
			
			<table cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td align="center">
						<table class="back_border" width="98%" style="height:80px;" cellpadding="0" cellspacing="0">
						    <tr>
						        <td class="bg_title">利用信息</td>
						    </tr>
						    <tr>
						        <td>
						        <form action="<%=basePath%>DALY/archivesUseAction_addRegisterInfoForOnlineApply.action" method="post" style="margin: 0;padding: 0;">
						            <table width="100%">
								        <tr>
								            <td class="text">证件号</td>
									        <td align="left">
									        	<input  id="IDCardNo" onchange="clearUserID()" type="text" onkeydown="getUserInfo()" name="archivesUsePersonInfo.IDCardNo" value="${userInfo.IDCardNo }"/>
										        <!-- <input  onclick="findByIDCardNo()" type="button"  value="|"/> -->
										        <input type="hidden" name="archivesUsePersonInfo.ID" id="userID" value="${userInfo.userID}"/>
										    </td>										       
									        <td class="text">姓名</td>
									        <td align="left">
									      	  	<input id="name" type="text"  onchange="clearUserID()" name="archivesUsePersonInfo.name" value="${userInfo.realName }"/>										        
									        </tr>
								        <tr>
								            <td class="text">电话</td>
									        <td align="left">
										        <input id="tel" type="text" name="archivesUsePersonInfo.tel" value="${userInfo.tel }"/></td>
									        <td class="text">E_mail</td>
									        <td align="left">
										        <input  id="email" type="text"  name="archivesUsePersonInfo.email" value="${userInfo.email }"/></td>
									    </tr>
									    <tr>
								            <td class="text">地址</td>
									        <td colspan="3" align="left">										        
										        <input  id="address" type="text" style="width: 415px;"  name="archivesUsePersonInfo.email" value="${userInfo.address }"/>
										    </td>
									    </tr>
									    <!-- 匿名用户的在线提交申请的登记信息 -->
									    <s:if test="#request.anonymouseFlag==true">									    
									    <tr>
									        <td class="text">密码</td>
									        <td align="left">									        	
												<!--<s:select name="areaID" id="areaID"  list="#{1:'中国大陆',2:'港澳台',3:'外国'}" theme="simple" cssStyle="width:150px;" listKey="key" listValue="value"   />-->
												<input id="userPWD"  type="password" name="userPWD" style="width: 148px;" >
											</td>
											<td class="text">确认密码</td>
									        <td align="left">
										        <input id="confirm_userPWD" type="password" style="width: 149px;" name="confirm_userPWD" /></td>
									        <td class="text"></td>
								        </tr>
								        </s:if>
								        <!-- 注册用户的在线提交申请的登记信息 -->
								        <s:else>
								        <tr>
								        	<td>&nbsp;</td>
								        </tr>
								        <tr style="display: none;">
									        <td class="text">密码</td>
									        <td align="left">									        	
												<!--<s:select name="areaID" id="areaID"  list="#{1:'中国大陆',2:'港澳台',3:'外国'}" theme="simple" cssStyle="width:150px;" listKey="key" listValue="value"   />-->
												<input id="userPWD"  type="password" name="userPWD" style="width: 148px;" >
											</td>
											<td class="text">确认密码</td>
									        <td align="left">
										        <input id="confirm_userPWD" type="password" style="width: 149px;" name="confirm_userPWD" /></td>
									        <td class="text"></td>
									    </tr>
								        </s:else>
								         <tr>									        
									       <td class="text">所属部门</td>
									       <td colspan="3" align="left">
										        <input id="userDepartment" type="text" style="width: 415px;"  name="userDepartment"  value=""/></td>
								        </tr>
								        
								        <tr>
								        	<td class="text">申请理由</td>
								        	<td colspan="3">
								        		<textarea name="requestReason" id="requestReason"  style="width: 415px;height: 95px;"></textarea>
								        	</td>
								        </tr>
								        <tr>
								        	<td colspan="4" align="right"> 
								        		<s:if test="#request.anonymouseFlag==true">
								        			<span style="font-size:12px;float:left;color:blue;">&nbsp;&nbsp;注：E_mail将作为下次登录的用户名</span>
								        		</s:if>
								        		
								        		<input type="button" onclick="clickOK()" id="btnOK" value="&nbsp;确&nbsp;定&nbsp;"/>
												<input type="button" id="btnCancel" value="&nbsp;取&nbsp;消&nbsp;" onClick="javascript:window.close();"/>
											</td>
								        </tr>
								      </table>
								      </form>
						        </td>
						    </tr>
						</table>
						
					</td>
				</tr>
			</table>
		
		
			
			<div id="divAddKC" style=" border: 1px solid #0000FF; position:absolute; background:white; visibility: hidden; width: 400px;height: 200px; left: 60px;">
				<table style="width:100%; height:100%;" cellspacing="0" cellpadding="0">
					<tr>
						<td colspan="6" style="height: 26px; background-color:#a3c9ff;" valign="top">
							<table style="width: 100%; height: 22px" cellspacing="0" cellpadding="0">
								<tr>
									<td align="left" style="color: white; font-size: 12px;"><strong>&nbsp;选择利用人</strong></td>
									<td align="right">
									    <span onclick="colseDiv()" style="color: white;font-size: 12px; cursor: pointer;">关闭[X]&nbsp;</span>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr> 
					   <td valign="top">	
						   <div style=" overflow:auto; width: 100%;height: 100%">					   
						   		 <table width="100%" id="usersTable" style="border: #104da6 1px solid; font-size: 12px;">
						          <thead class="tableHead">
							        <tr>
							            <td>序号</td>
								        <td>姓名</td>
								        <td>证件号</td>
								        <td>单位</td>
								        <td>操作</td	>	        
							        </tr>
							       </thead>
							       <tbody>
							       </tbody>
							    </table> 	
						  </div>					   					      
					   </td>
					</tr>					
				</table>
		   </div>
	</body>
</html>
