<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
        <base href="<%=basePath%>">
		<title>查档登记</title>
		
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="Cache-Control" content="no-cache,  must-revalidate" />
		<meta http-equiv="expires" content="Wed,  26  Feb  1997  08:21:57  GMT" />
		
		<link href="css/common.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="js/common.js"></script>
        <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
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
		
		
		var users;//定义利用人信息
		$(document).ready(function(){
		    //添加
		    $("#add").bind('click',function(){
		        $.ajax({
		           url:"json/archivesUseAction_findArchivesByBarcode.action",
		           type:"post",
		           data:"barcode="+$('#barcode').val(),
		           success:function(date){
		              if(date == null || date ==""){
		                 alert("没有找到数据！");
		              }else{
		                 var archivesInfo = eval('('+date+')');
		                 var firstTr = $("#showTable").children("tbody").children().first();
		                 var trColor = '#e0edff';
		                 if(firstTr.attr("bgcolor")=='#e0edff' && firstTr != null){
		                    trColor = "#eef5ff";
		                 }
		                 //避免重复添加
		                 var inputs = $("input[name='NBXHS']");
		                 var pFlag = true;
		                 for(var i=0;i<inputs.length;i++){
		                     if(inputs[i].value == archivesInfo.NBXH){
		                       pFlag = false;
			                   alert("列表中以存在该档案！");
			                   break;
			                 }
		                 }
		                 if(pFlag){
		                    $("#showTable").prepend("<tr bgcolor='"+trColor+"'>"+
												        "<td align='center'><input type='checkbox' name='NBXHS' value='"+archivesInfo.NBXH+"'/></td>"+
												        "<td>"+archivesInfo.archivesId+"</td>"+
												        "<td>"+archivesInfo.title+"</td>"+
												        "<td>"+archivesInfo.barcode+"</td>"+
												      "</tr>");
								NBXH = archivesInfo.NBXH;
		                 }
		              }
		           },
		           error:function(XMLHttpRequest, textStatus){
		              alert(textStatus+","+XMLHttpRequest.status);
		           }
		        });
		    });
		    
		    //删除
		    $("#btDel").bind("click",function(){
		       $("input:checked").parent().parent().remove(); 
		    });
		    
		    //查找利用人信息
		    $("#name").bind("blur",function(){
		       if($("#name").val() != "" && $("#name").val() !=null){
			       $.ajax({
			           url:"json/archivesUseAction_findArchivesUseUsersByName.action",
			           type:"post",
			           data:"userName="+$('#name').val()+"&type=CD",
			           success:function(date){
			             users = eval("("+date+")");
			             if(users.length >1){
			               $("#usersTable").children("tbody").children().remove();
			               $.each(users,function(i,user){
			                  $("#usersTable").children("tbody").append("<tr bgcolor='#e0edff'>"+
															            "<td align='center'>"+(i+1)+"</td>"+
															            "<td>"+user.name+"</td>"+
															            "<td>"+user.cardId+"</td>"+
															            "<td>"+user.dept+"</td>"+
															            "<td align='center'><button onclick='choose("+i+")' class='button'>选择</button></td>"+
															         "</tr>");								         							         
			               });
			      		   opendiv();
			             }
			           },
			           error:function(XMLHttpRequest, textStatus){
			             alert(textStatus+","+XMLHttpRequest.status);
			           }
			        });
		       } 
		    });
		    

		       alert($("#operate").val());
		       if($("#operate").val() == "ck"){
		          $("input").attr("disabled","disabled");
		       }
	    
		});
		 //选择
	    function choose(i){
	        var user = users[i];
	        $("#name").val(user.name);
	        $("#cardId").val(user.cardId);
	        $("#dept").val(user.dept);
	        $("#e_mail").val(user.e_mail);
	        $("#telNo").val(user.telNo);
	        colseDiv();
	    }	
		//-->
		</script>
	</head>
	<body class="bg_color" style="margin-top:4px">
	        <input type="hidden" id="operate" value="${requestScope.operate}"/>
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
								            <td class="text">姓名CD</td>
									        <td align="left">
										        <input id="name" type="text" /></td>
									        <td class="text">证件号</td>
									        <td align="left">
										        <input  id="cardId"  type="text" /></td>
									        </tr>
								        <tr>
								            <td class="text">&nbsp;所属部门</td>
									        <td align="left">
										        <input id="dept" type="text"  /></td>
									        <td class="text">E_mail</td>
									        <td align="left">
										        <input  id="e_mail" type="text" /></td>
									    </tr>
									    <tr>
									        <td class="text">电话</td>
									        <td align="left">
										        <input id="telNo" type="text" /></td>
									        <td class="text">所属区域</td>
									        <td align="left">
													<select name="Select1">
													<option>&nbsp;&nbsp;&nbsp;&nbsp;—&nbsp;&nbsp;国内&nbsp;&nbsp;—&nbsp;&nbsp;&nbsp;&nbsp;</option>
													<option>&nbsp;&nbsp;&nbsp;&nbsp;—&nbsp;&nbsp;国外&nbsp;&nbsp;—&nbsp;&nbsp;&nbsp;&nbsp;</option>
													</select>
											</td>
								        </tr>
								      </table>
						        </td>
						    </tr>
						</table>
						<br/>
						<table class="back_border" width="98%" cellpadding="0" cellspacing="0">
						    <tr>
						        <td class="bg_title bg_title4">&nbsp;利用登记信息</td>
						    </tr>
						    <tr>
						        <td>
						            <table width="100%">
								        <tr>
									        <td class="text" width="80px;">查档日期</td>
									        <td align="left">
										        <input type="text"  id="lend_date" class="back_border"  value="2010-03-23" readonly="readonly"/></td>
									        <td class="text" width="80px;">应还时间</td>
									        <td align="left">
									          <input type="text" name="" id="yjDate"/><img src="images/dropdownTime.gif" onclick="PopUpCalendar('yjDate',true)"/>
										    </td>
								        </tr>
								        <tr>
									        <td class="text">利用方式</td>
									        <td align="left">
										        <input type="text"  id="use_mode" class="back_border" value="查档" readonly="readonly"/></td>
									        <td class="text" width="80px;">利用目的</td>
									        <td align="left">
									            <s:select name="archivesUsePurpose" id="archivesUsePurpose" theme="simple" list="#request.archivesUsePurposes"  headerKey="00" headerValue="请选择..." listKey="iD" listValue="purpose"></s:select>
										     </td>
								        </tr>
								        <tr>
									        <td class="text">备注</td>
									        <td colspan="3" align="left">
										        <textarea  id="note" style="width:90%;">
										        </textarea>
										   </td>
								        </tr>
								        <tr>
								          <td class="text">扫描条码</td>
								          <td align="left" colspan="3">
											<table>
											   <tr>
											     <td><input type="text" name="barcode" id="barcode"/></td>
											     <td><button class="button" id="add">添    加</button></td>
											   </tr>
											</table>
										</td>
								        </tr>
								       <tr>
								        <td class="text">查档清单</td>
								         <td colspan="3" align="left">
								          <div style="overflow:auto; height:120px; border: #104da6 1px solid;">
									        <table width="100%" id="showTable" style="border: 0;">
									          <thead class="tableHead">
										        <tr>
										            <td>选择</td>
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
								    <input type="button" id="btDel" class="button" value="移       除"/>&nbsp;&nbsp;
								    <input type="button" id="btOk" class="button" value="确        定"/>&nbsp;&nbsp;
									<input type="button" id="btCancel" class="button" value="取      消" onClick="javascript:window.close();"/>
							    </td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<div id="divAddKC" style=" border: 1px solid #0000FF; position:absolute; background:white; visibility: hidden; width: 400px;height: 200px; left: 80px;">
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
					     <table width="100%" id="usersTable" style="border: #104da6 1px solid; font-size: 12px;">
					          <thead class="tableHead">
						        <tr>
						            <td>序号</td>
							        <td>姓名</td>
							        <td>证件号</td>
							        <td>单位</td>
							        <td>操作</td>		        
						        </tr>
						       </thead>
						       <tbody>
						       </tbody>
						    </table>   
					   </td>
					</tr>
					<tr>
						<td class="style19" colspan="6" style="height: 28px;text-align: center;">
						  <input name="btnOK0" onclick="colseDiv()" class="button" type="button" value="取消" style="width: 57px; height: 19px" /><br />
						</td>
					</tr>
				</table>
		   </div>
	</body>
</html>
