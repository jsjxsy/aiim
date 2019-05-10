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
			$("#userID").val("");
		}

		//档案利用人信息验证
		function inputValidate(){
			if($("#name").val()==""){
				alert("姓名不能为空！");
				return false;
			}
			if($("#cardId").val()==""){
				alert("证件号不能为空！");
				return false;
			}
			if($("#dept").val()==""){
				alert("所属部门不能为空！");
				return false;
			}
			if($("#telNo").val()==""){
				alert("电话号码不能为空！");
				return false;
			}			
			if($("#area").val()==""){
				alert("请选择所属区域！");
				return false;
			}

			return true;
		}
				
		var users;//定义利用人信息
		$(document).ready(function(){
			//-----页面初始化-----
			//1、设置所有按钮不可用
			$(":button").attr("disabled","true");
			$("#archivesUsePurpose").val(${archivesUseRegister.purposeID });		
			$("#area").val(${archivesUseRegister.archivesUsePersonInfo.areaID });
			
  			//绑定事件：单击确定按钮，登记档案利用信息
		    $("#btOk").bind("click",function(){
			    //处理利用人信息:将手动录入的"利用人信息"添加到数据库中
		       if($("#userID").val()==""){ //当用户的ID为空时，则说明是手动录入利用人信息，否则是从数据库查询得来
		    	   if(addArchivesUsePersonInfo()==true){
		    		   addArchivesUseRegister();//登记档案利用信息
			       }else{
				       return false;
				   }
			   }else{
				   addArchivesUseRegister();//登记档案利用信息
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
		    $("#findByName").bind("click",function(){
			    //构造档案利用人信息对象，姓名必须赋值
			    var archivesUsePersonInfo = {
					    name:$("#name").val()
				};
			//调用DWR，通过姓名查询用户信息
		   	ArchivesUseAction.findArchivesUsePersonInfoByName(archivesUsePersonInfo,findArchivesUsePersonInfoByNameBack);
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
			
			var returnDate = $("#returnDate").val();
			if(returnDate==''){
				alert('请输入归还时间！');
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
				ArchivesUseAction.addArchivesUseRegister(archivesUseRegister,archivesBarcodes,returnDate,addArchivesUseRegisterBack);
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
			
			var archivesUsePersonInfo={
				name:$("#name").val(),
				IDCardNo:$("#cardId").val(),
				iDCardTypeID:0,
				department:$("#dept").val(),
				tel:$("#telNo").val(),
				email:$("#e_mail").val(),
				areaID:$("#area").val()				
			};
			//输入效验
			if(inputValidate()==true){
				//添加档案利用人信息到数据库,
				ArchivesUseAction.addArchivesUsePersonInfo(archivesUsePersonInfo,addArchivesUsePersonInfoBack);
				return true;
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
           if(storeroomArchivesInfo.NBXH==0){
               alert('没有找到条形码'+storeroomArchivesInfo.archivesBarcode+'所对应的档案信息！');
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
				NBXH = storeroomArchivesInfo.NBXH;
             }
            
		}

	function findArchivesUsePersonInfoByNameBack(data){
		//将数据保存至全局变量
		users = data;	
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
	        $("#cardId").val(user.IDCardNo);
	        $("#dept").val(user.department);
	        $("#e_mail").val(user.email);
	        $("#telNo").val(user.tel);
	        $("#area").val(user.areaID);
	        $("#userID").val(user.ID);
	        //user.areaID
	        colseDiv();
	    }	
		//-->
		</script>
	</head>
	<body class="bg_color" style="margin-top:4px">
	<!-- 利用方式  -->
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
										        <input  id="cardId" onchange="clearUserID()" type="text" name="IDCardNo" value="${archivesUseRegister.archivesUsePersonInfo.IDCardNo }"/></td>
										        <td class="text">姓名333</td>
									        <td align="left">
										        <input id="name" type="text" onchange="clearUserID()" name="userRealName" value="${archivesUseRegister.archivesUsePersonInfo.name }"/></td>
										        
									        </tr>
								        <tr>
								            <td class="text">所属部门</td>
									        <td align="left">
										        <input id="dept" type="text"  name="userDepartment"  value="${archivesUseRegister.archivesUsePersonInfo.department }"/></td>
									        <td class="text">E_mail</td>
									        <td align="left">
										        <input  id="e_mail" type="text"  name="email" value="${archivesUseRegister.archivesUsePersonInfo.email }"/></td>
									    </tr>
									    <tr>
									        <td class="text">电话</td>
									        <td align="left">
										        <input id="telNo" type="text" name="tel" value="${archivesUseRegister.archivesUsePersonInfo.tel }"/></td>
									        <td class="text">所属区域</td>
									        <td align="left">
									       		<s:select name="area" id="area"  list="#{1:'中国大陆',2:'港澳台',3:'外国'}" theme="simple" cssStyle="width:150px;" listKey="key" listValue="value"   />
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
							        <td class="bg_title">&nbsp;利用登记信息</td>
							    </tr>
							    <tr>
							        <td>
							            <table width="100%">
									        <tr>
										        <td class="text" width="60px;" >${useTypeText }日期</td>
										        <td align="left">										       		
										       		<input type="text" id="useDate" name="useDate" readonly="readonly" value="<s:date name="#request.archivesUseRegister.useDate" format="yyyy-MM-dd" />">										           
											    </td>
										        <td class="text" style="white-space: nowrap">利用方式</td>
										        <td align="left">
											        <input type="text"  id="use_mode" value="${useTypeText }" readonly="readonly"/>
											    </td>
									        </tr>
									        <tr>
										       
										        <td class="text" >利用目的</td>
										        <td align="left">
										             <s:select name="archivesUsePurpose" cssStyle="width:150px;"  id="archivesUsePurpose" theme="simple" list="#request.archivesUsePurposes" headerKey="0" headerValue="请选择..." listKey="iD" listValue="purpose"></s:select>
											     </td>
									        </tr>
									        <tr>
										        <td class="text">&nbsp;&nbsp;&nbsp;&nbsp;备注</td>
										        <td colspan="3" align="left">
											        <textarea  id="remark" name="remark" style="width:88%;height: 80px;" >${archivesUseRegister.remark }
											        </textarea>
											   </td>
									        </tr>
									       <tr>
									         <td colspan="4" align="left">
									         <span style="font-size:12px;color:blue;">档案列表</span>
									          <div style="overflow:auto; height:120px; border: #104da6 1px solid;">									          	
										        <table width="100%" id="showTable" style="border: 0; background-color: white;" cellpadding="2px" cellspacing="1px">
										          <thead class="tableHead">
											        <tr>											           
												        <td>档号</td>
												        <td>题名</td>
												        <td>条码</td>
												        <td>应还时间</td>		        
											        </tr>
											       </thead>
											       <tbody>
											        <s:iterator value="#request.archivesUseOutInfos" status="status" >													   
													    <tr bgcolor="#e0edff" >
													    <%--<td align="center" height="15px"><s:property value="#status.index+1"/></td>--%>												        															
															<td><s:property value="archivesID"/></td>
															<td><s:property value="title"/></td>
															<td><s:property value="archivesBarcode"/></td>	
															<td><s:date name="shouldReturnDate" format="yyyy-MM-dd" /></td>
														</tr>
													  </s:iterator>											      
												   </tbody>
										        </table>
									       	   </div>
											  </td> 
									       </tr>
								        </table>
							        </td>
							    </tr>
							</table>
							<!-- 
							<table style="height:30px" cellpadding="5" width="100%">
								<tr>
									<td align="center">
									    <input type="button" id="btDel" class="button" value="移       除"/>&nbsp;&nbsp;
									    <input type="button" id="btOk" class="button"  value="确        定"/>&nbsp;&nbsp;
										<input type="button" id="btCancel" class="button" value="取      消" onClick="javascript:window.close();"/>
								    </td>
								</tr>
							</table>
						    -->
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
