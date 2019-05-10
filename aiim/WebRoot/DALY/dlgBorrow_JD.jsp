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
		<title>${useTypeText}登记</title>
		
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="Cache-Control" content="no-cache,  must-revalidate" />
		<meta http-equiv="expires" content="Wed,  26  Feb  1997  08:21:57  GMT" />
		
		<link href="css/common.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="js/common.js"></script>
        <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
        
        <script type="text/javascript" src="dwr/interface/ArchivesUseAction.js"></script>
	   
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
			$("#userID").val("");
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
				
		var users;//定义利用人信息
		$(document).ready(function(){

  			//绑定事件：单击确定按钮，登记档案利用信息
		    $("#btOk").bind("click",function(){
			    //处理利用人信息:将手动录入的"利用人信息"添加到数据库中
		       if($("#userID").val()==""){ //当用户的ID为空时，则说明是手动录入利用人信息，否则是从数据库查询得来
		    	   addArchivesUsePersonInfo();//添加用户信息，添加用户信息后执行登记档案利用信息		    		
			   }else{
				   addArchivesUseRegister();//直接登记档案利用信息
			   }
			   
		    });
		    
			
		    //绑定事件：添加档案信息到列表中
		    $("#add").bind('click',function(){
			    //输入验证
		    	if($("#barcode").val()==''){
			    	alert('请输入条形码！');
			    	return false;
			    }
			    
		    	 //构造档案利用人信息对象，姓名必须赋值
			    var storeroomArchivesInfo = {
			    		archivesBarcode:$("#barcode").val()
				};
				//根据条形码值查询库房档案信息
		    	ArchivesUseAction.findArchivesByBarcode(storeroomArchivesInfo,findArchivesByBarcodeBack);
		    });

		    
		    //绑定事件：删除选中档案条目
		    $("#btDel").bind("click",function(){
		       $("input:checked").parent().parent().remove(); 
		    });
		    
		///----    
		    //绑定事件：查找利用人信息
		    $("#findByIDCardNo").bind("click",function(){
			    //构造档案利用人信息对象，姓名必须赋值
			    var archivesUsePersonInfo = {
					    name:$("#name").val(),
					    IDCardNo:$("#IDCardNo").val()
				};
			//调用DWR，通过姓名查询用户信息
		   	ArchivesUseAction.findArchivesUsePersonInfoByIDCardNo(archivesUsePersonInfo,findArchivesUsePersonInfoByIDCardNoBack);
		    }); 
		    

		    
		 //总JQuery结束    
		});

	
		//添加实物档案利用登记信息 
		function addArchivesUseRegister(){
			
			var bFlag = true;//利用方式标志，查档/借档
			if($("#useType").val()=='CD'){
				bFlag = false;
			}
			
			var archivesUseRegister = {
				borrowFlag:bFlag,
				purposeID:$("#archivesUsePurpose").val(),
			//	useDate:$("#useDate").val(),  //在服务器端设值
				usePersonID:$("#userID").val(),
				remark:	$("#remark").val()
			};
			
			var useDays = $("#useDays").val();
			if(useDays==''){
				alert('请输入利用天数！');
				return false;
			}		

			var archivesUsePurpose = $("#archivesUsePurpose").val();
			if(archivesUsePurpose==0){
				alert('请选择利用目的！');
				return false;
			}	
			
			var archivesBarcodes = "";//条码集合串，用":"隔开
		    var inputs = $("input[name='archivesBarcodes']");
		    for(i = 0;i<inputs.length;i++){
		    	archivesBarcodes += inputs[i].value+':';
			}
			if(archivesBarcodes==''){
				alert('请输入要利用档案的条形码！');
			}else{
				//调用DWR，添加档案利用登记信息
				ArchivesUseAction.addArchivesUseRegister(archivesUseRegister,archivesBarcodes,useDays,{callback:addArchivesUseRegisterBack,exceptionHandler:function(message){alert(message);}});
			}
			
		}

		//添加实物档案利用登记信息回调函数
		function addArchivesUseRegisterBack(data){
			if(data==''){//如果返回结果为''则表示操作成功
				window.returnValue = 1;
				alert('利用登记成功！');
				window.close();
			}else{//操作失败返回错误原因
				alert(data);
			}
		}
		

		//保存当前档案利用人信息
		function addArchivesUsePersonInfo(){
			//用户信息
			var archivesUsePersonInfo={
				name:$("#name").val(),
				IDCardNo:$("#IDCardNo").val(),
				iDCardTypeID:0,
				department:$("#department").val(),
				tel:$("#tel").val(),
				email:$("#email").val(),
				areaID:$("#areaID").val()				
			};
			//输入效验
			if(inputValidate()==true){
				//添加档案利用人信息到数据库,
				ArchivesUseAction.addArchivesUsePersonInfo(archivesUsePersonInfo,function(userID){
					if(userID!=''){
						$("#userID").val(userID);
						addArchivesUseRegister();//当添加利用人信息成功后，即可进行利用登记
					}else{
						alert('保存利用人信息失败！');
						return false;
					}
				});
				
			}else{
				return false;
			}
		}
		
		//回调函数添加档案利用人信息
		function addArchivesUsePersonInfoBack(userID){
			//若保存失败，则会通过message返回错误信息
			if(userID!=''){
				$("#userID").val(userID);
			}else{
				alert('保存利用人信息失败！');
			}
		}

		
		//根据档案条形码查询库房档案信息
		function findArchivesByBarcodeBack(storeroomArchivesInfo){	
			//检查该条形码所对应的档案是否存在		
           if(storeroomArchivesInfo.NBXH==0){
               alert('没有找到条形码'+storeroomArchivesInfo.archivesBarcode+'所对应的档案信息！');
               return false;
           }
    
           if(storeroomArchivesInfo.storeStatus!='可被利用'){
               alert('添加失败：档案《'+ storeroomArchivesInfo.title +'》'+storeroomArchivesInfo.storeStatus);
               return false;
           }
                
           var firstTr = $("#showTable").children("tbody").children().first();
           var trColor = '#e0edff';
           if(firstTr.attr("bgcolor")=='#e0edff' && firstTr != null){
              trColor = "#eef5ff";
           }
           //避免重复添加
           var inputs = $("input[name='archivesBarcodes']");
           var pFlag = true;
           for(var i=0;i<inputs.length;i++){
               if(inputs[i].value == storeroomArchivesInfo.archivesBarcode){
                    pFlag = false;
                 alert("列表中以存在该档案！");
                 break;
               }
           }
           if(pFlag){
              $("#showTable").prepend("<tr bgcolor='"+trColor+"'>"+
						        "<td align='center'><input type='checkbox' name='archivesBarcodes' value='"+storeroomArchivesInfo.archivesBarcode+"'/></td>"+
						        "<td>"+storeroomArchivesInfo.archivesID+"</td>"+
						        "<td>"+storeroomArchivesInfo.title+"</td>"+
						        "<td>"+storeroomArchivesInfo.archivesBarcode+"</td>"+
						      "</tr>");
           }
            
		}

	//根据姓名查询档案利用人信息回调函数
	function findArchivesUsePersonInfoByIDCardNoBack(data){
		//将数据保存至全局变量
		users = data;
		if(users.length==0){
			alert('该证件号不存在！');
			return false;
			}	
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
		
		 //选择
	    function choose(i){
	        var user = users[i];
	        $("#name").val(user.name);
	        $("#IDCardNo").val(user.IDCardNo);
	        $("#department").val(user.department);
	        $("#email").val(user.email);
	        $("#tel").val(user.tel);
	        $("#areaID").val(user.areaID);
	        $("#userID").val(user.ID);
	        colseDiv();
	    }	

		 //处理证件号码的回车事件
		 function getUserInfo(){
			 if(event.keyCode=='13'){
				 $("#findByIDCardNo").click();
		     }			 
	     }

		 //处理输入条形码的回车事件
	     function getArchivesInfoByBarcode(){
	    	 if(event.keyCode=='13'){
	    		 $("#add").click();
		     }	
		 }
	     
		//-->
		</script>
	</head>
	<body class="bg_color" style="margin-top:4px">	    
 
		<input type="hidden" name="useType" id="useType" value="${useType}">
			<table style="height:100%" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td align="center">
						<table class="back_border" width="98%" style="height:80px;" cellpadding="0" cellspacing="0">
						    <tr>
						        <td class="bg_title">利用人信息</td>
						    </tr>
						    <tr>
						        <td>
						            <table width="100%">
								        <tr>
								       		<td class="text">证件号</td>
									        <td align="left">
										        <input  id="IDCardNo" onchange="clearUserID()" type="text"  onkeydown="getUserInfo();" name="IDCardNo" value="${IDCardNo }"/><input id="findByIDCardNo" type="button"  value="|"/></td>
									        
								            <td class="text">姓名</td>
									        <td align="left">
										        <input id="name" type="text" onchange="clearUserID()" name="userRealName" value="${userRealName }"/></td>
									     </tr>   
								        <tr>
								            <td class="text">&nbsp;所属部门</td>
									        <td align="left">
										        <input id="department" type="text"  name="userDepartment"  value="${userDepartment }"/></td>
									        <td class="text">E_mail</td>
									        <td align="left">
										        <input  id="email" type="text"  name="email" value="${email }"/></td>
									    </tr>
									    <tr>
									        <td class="text">电话</td>
									        <td align="left">
										        <input id="tel" type="text" name="tel" value="${tel }"/></td>
									        <td class="text">所属区域</td>
									        <td align="left">
									       		 <s:select name="areaID" id="areaID"  list="#{1:'中国大陆',2:'港澳台',3:'外国'}" theme="simple" cssStyle="width:150px;" listKey="key" listValue="value"   />
											</td>
								        </tr>
								      </table>
						        </td>
						    </tr>
						</table>
						<br/>
						<form action="DALY/archivesUseAction_useRegist.action" method="post" style="margin: 0;padding: 0">
		        			<input name="borrowFlag" value="false" type="hidden"/>
		        			<input type="hidden" name="userID" id="userID" value=""><!-- 用户ID -->
		        			<input type="hidden" id="operate" value="${requestScope.operate}"/><!-- 借档/查档 -->
							<table class="back_border" width="98%" cellpadding="0" cellspacing="0">
							    <tr>
							        <td class="bg_title bg_title4">&nbsp;利用登记信息</td>
							    </tr>
							    <tr>
							        <td>
							            <table width="100%" >
									       <!--  <tr>
										        <td class="text" width="80px;">${useTypeText }日期</td>
										        <td align="left">
										       		<input type="text" id="useDate" name="useDate" readonly="readonly" value="<%= new SimpleDateFormat("yyyy-MM-dd").format(new Date()) %>">										           
											    </td>
										        <td class="text" width="80px;">应还时间</td>
										        <td align="left">
										          <input type="text" name="returnDate" readonly="readonly" id="returnDate" value=""/><img src="images/dropdownTime.gif" onclick="PopUpCalendar('returnDate',true)"/>
											    </td>
									        </tr>
									         -->
									        <tr>
										        
										        <td class="text" width="80px;">利用目的</td>
										        <td align="left">
										             <s:select name="archivesUsePurpose" cssStyle="width:150px;"  id="archivesUsePurpose" theme="simple" list="#request.archivesUsePurposes" headerKey="0" headerValue="请选择..." listKey="iD" listValue="purpose"></s:select>
											     </td>
											     <td class="text" width="80px;">利用时间</td>
										        <td align="left">
										        	<s:if test="#requst.useType=='JD'">
										        	    <input type="text"  id="useDays" readonly="readonly" name="useDays" value="${useDays }" /><span style="font-size:12px;">天</span>
										        	</s:if>
										        	<s:else>
										        		 <input type="text"  id="useDays" name="useDays" value="${useDays }" /><span style="font-size:12px;">天</span>
										        	</s:else>
											    </td>
									        </tr>
									        <tr>
										        <td class="text">备注</td>
										        <td colspan="3" align="left">
										        	<textarea style="width: 420px;height:40px;" id="remark" name="remark"></textarea>
											        
											   </td>
									        </tr>
									        <tr align="left">
									          <td class="text" align="left" style="font-weight: bold;">扫描条码	</td>
									          <td colspan="3" ><input type="text" onkeydown="getArchivesInfoByBarcode()" style="width: 375px;" name="barcode" id="barcode"/>
										          <button class="button" id="add"  style="margin-left: 3px;margin-bottom: 2px; ">添    加</button>
											  </td>
									        </tr>
									       <tr>
									        <%--<td class="text">${useTypeText }清单</td> --%>
									         <td colspan="4" align="left">
									          <div style="overflow:auto; height:150px; border: #104da6 1px solid;">
										        <table width="100%" id="showTable" style="border: 0; background-color: white;margin: 0;padding: 0;" cellpadding="0" cellspacing="1px">
										          <thead class="tableHead">
											        <tr>
											            <td width="30px;">选择</td>
												        <td>档号</td>
												        <td>题名</td>
												        <td>条码</td>		        
											        </tr>
											       </thead>
											       <tbody>											      
												   </tbody>
										        </table>
									       	   </div>
											  </td> 
									       </tr>
								        </table>
							        </td>
							    </tr>
							</table>
							<table style="height:30px" cellpadding="5" width="100%">
								<tr>
									<td align="center">
									    <input type="button" id="btDel" class="button" value="移&nbsp;&nbsp;除"/>&nbsp;&nbsp;
									    <input type="button" id="btOk" class="button"  value="确&nbsp;&nbsp;定"/>&nbsp;&nbsp;
										<input type="button" id="btCancel" class="button" value="取&nbsp;&nbsp;消" onClick="javascript:window.close();"/>
								    </td>
								</tr>
							</table>
						</form>
					</td>
				</tr>
			</table>
			
			<div id="divAddKC" style=" border: 1px solid #0000FF; position:absolute; background:white; visibility: hidden; width: 400px;height: 250px; left: 80px;">
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
