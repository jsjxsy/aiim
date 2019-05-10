<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ include file="/JXGL/taglib.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
        <base href="<%=basePath%>" target="_self">
		<title>出证收费登记</title>
		
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="Cache-Control" content="no-cache,  must-revalidate" />
		<meta http-equiv="expires" content="Wed,  26  Feb  1997  08:21:57  GMT" />
		
		<link href="css/common.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="js/common.js"></script>
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/json2.js"></script>
        
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
				$("#name").focus();
				return false;
			}
			if($("#cardId").val()==""){
				alert("证件号不能为空！");
				$("#cardId").focus();
				return false;
			}
			if($("#dept").val()==""){
				alert("所属部门不能为空！");
				$("#dept").focus();
				return false;
			}
			if($("#telNo").val()==""){
				alert("电话号码不能为空！");
				$("#telNo").focus();
				return false;
			}			
			if($("#area").val()==""){
				alert("请选择所属区域！");
				$("#area").focus();
				return false;
			}

			return true;
		}
				
		var users;//定义利用人信息
		$(document).ready(function(){
  			//绑定事件：单击确定按钮，登记档案利用信息
		    $("#btOk").bind("click",function(){
			    var pflag = true;

			  //检测实收金额
				if(pflag) {
					var realCharge = $('#realCharge');
					if(realCharge.val()=='' || realCharge.val()<0) {
						pflag = false;
						alert('请输入实收金额！');
						realCharge.focus();
					}
				}
			    
			    //处理利用人信息:将手动录入的"利用人信息"添加到数据库中
		       if(pflag && $("#userID").val()==""){ //当用户的ID为空时，则说明是手动录入利用人信息，否则是从数据库查询得来
		    	   pflag = addArchivesUsePersonInfo();//添加用户信息，添加用户信息后执行登记档案利用信息		    		
			   }
			   
			   if (pflag) {
				   if (certificateInfoArray.length >= 1 ) {
					   addArchivesCertificateInfo();//当添加利用人信息成功后，即可添加档案出证信息
					} else {
						pflag = false;
						alert('请选择出证信息！');
						$('#certificateTypes').focus();
					}
				   
			   }
		    });
		    
			
		    //绑定事件：添加档案信息到列表中
		    $("#add1").bind('click',function(){
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

		///----    
		    //绑定事件：根据姓名 	查找利用人信息
		    $("#findByName").bind("click",function(){
			    //构造档案利用人信息对象，姓名必须赋值
			    var archivesUsePersonInfo = {
					    name:$("#name").val(),
					    IDCardNo:'none'
				};
			//调用DWR，通过姓名查询用户信息
		   	ArchivesCertificateManageDWR.findArchivesUsePersonInfoByQuery(archivesUsePersonInfo,findArchivesUsePersonInfoByNameBack);
		    }); 


		  //绑定事件：根据证件号	查找利用人信息
		    $("#findByIDCardNo").bind("click",function(){
		    	var IDCardNo = $("#cardId").val();
		    	if(IDCardNo==null || IDCardNo=='') {
		    		IDCardNo = 'none';
		    	}
			    //构造档案利用人信息对象，姓名必须赋值
			    var archivesUsePersonInfo = {
			    		name:'',
			    		IDCardNo:IDCardNo
				};
			//调用DWR，通过姓名查询用户信息
		   	ArchivesCertificateManageDWR.findArchivesUsePersonInfoByQuery(archivesUsePersonInfo,findArchivesUsePersonInfoByNameBack);
		    }); 
		 //总JQuery结束    
		});

		//保存当前档案利用人信息
		function addArchivesUsePersonInfo(){
			var pflag = true;
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
			if(pflag && inputValidate()==true){
				if(certificateInfoArray.length<=0) {
					pflag = false;
					alert('请选择出证信息！');
					$('#certificateTypes').focus();
				}
				
				if(pflag) {
					//添加档案利用人信息到数据库
					ArchivesUseAction.addArchivesUsePersonInfo(archivesUsePersonInfo,function(userID){
						if(userID!=''){
							$("#userID").val(userID);
						}else{
							pflag = false;
							alert('保存利用人信息失败！');
						}
					});
				}
			} else {
				pflag = false;
			}
			return pflag;
		}

		//添加档案出证信息
		function addArchivesCertificateInfo() {
			var pflag = true;
			if(pflag) {
				var shouldCharge = $('#shouldCharge').val();
				var realCharge = $('#realCharge').val();
				if(compareNumberSize(shouldCharge, realCharge)) {
					if(confirm('实缴金额小于应缴金额，您确定吗？') == false) {
						pflag = false;
					} 
				} else {
					var change = realCharge - shouldCharge;
					alert('实缴金额大于应缴金额，请找零'+change+'元。');
				}
			}
			if(pflag) {
				$('#certificateInfoArrayJSON').val(JSON.stringify(certificateInfoArray));
				$('form').submit();
			}
		}

	//根据姓名查询档案利用人信息回调函数
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
	        colseDiv();
	    }

		 //出证明细信息对象
	    function certificateInfo(id, certificateTypeID, total, expressFlag, remark) {
			//唯一标识	
	    	this.id = id;
		    //出证类型id
		    this.certificateTypeID = certificateTypeID;
		   	//份数
		    this.total = total;
		    //加急
		    this.expressFlag = expressFlag;
		    //备注
		    this.remark = remark;
	    }
			
		//选择档案出证类型
		var certificateType;
		//是否加急
		var expressName = '否';
		var expressValue = false;
		 //出证总金额
	    var totalMoney = new Number();
	    var certificateInfoArray = new Array();
	    var INDEX = 0;
	    
		//增加出证类型
		function addCertificateType() {
			var pFlag = true;
			var certificateTypeID = $('#certificateTypes');
			var total = $('#total');

			//判断是否选择出证类型
			if (pFlag) {
				if (certificateTypeID.val() == 0) {
					alert('请选择出证类型！');
					certificateTypeID.focus();
					pFlag = false;
				}
			}

			//判断是否输入出证份数
			if (pFlag) {
				if(total.val()==null || total.val()<=0) {
					alert('请输入出证份数!');
					total.focus();
					pFlag = false;
				}
			}

			var price = $('#price');
			var remark = $('#remark');
			if (pFlag) {
				var certificateType=certificateTypeID.find("option:selected").text();
				var money = price.val()*total.val();
				totalMoney += money;
				var trID = 'tr'+totalMoney;
				
				var certInfo = new certificateInfo(INDEX, certificateTypeID.val(), total.val(), expressValue, remark.val());
				INDEX = INDEX + 1;
				certificateInfoArray.push(certInfo);
				
				$('#certificateTbody').append('<tr bgcolor=\'#e0edff\' id=\''+trID+'\'><td>'+certificateType+'</td><td>'+price.val()+'</td><td>'+total.val()+'</td><td>'+expressName+'</td><td>'
						+money+'</td><td>'+remark.val()+'</td><td><a href=\"javascript:removeCertificate(\''+trID+'\',\''+money+'\',\''+certInfo.id+'\')\">删除</a></td></tr>');
			}
			$('#shouldCharge').val(totalMoney);
			
			
			//新增成功，清空数据 
			if (pFlag) {
				certificateTypeID.val(0);
				price.val('');
				total.val('');
				remark.val('');
				$('#expressFlag').attr('checked', false);
			}
		}

		//删除出证
		function removeCertificate(idObj, money, index) {
			totalMoney = totalMoney - money;
			$('#shouldCharge').val(totalMoney);
			certificateInfoArray.splice(index,1);
			alert('删除certificateInfoArray length='+certificateInfoArray.length);
			$('#'+idObj).remove();
		}

		//选择出证类型
		function selectCertificateType(idObj) {
			var certificateTypes = ${certificateTypesJSON};
			return findJSON(certificateTypes, 'ID', idObj);
		}

		//通过key查询json集合
		function findJSON(jsonList, keyObj, valueObj) {
			for (var one in jsonList) {
		        for(var key in jsonList[one]) {
		            if(keyObj == key && jsonList[one][key]==valueObj) {
			            return jsonList[one];
		            }
		        }
		    }
		}

		$(function(){
			//监听出证类型选择
			$("#certificateTypes").change(function(){ //事件發生 
  				jQuery('option:selected', this).each(function(){
  	  				certificateType = selectCertificateType(this.value);
  	  				$('#price').val(certificateType['generalPrice']);
  				});
  			});
			//绑定加急标志
  			$('#expressFlag').click(function(){
  	  			if($(this).attr('checked')) {
  	  				$('#price').val(certificateType['expressPrice']);
  	  				expressName = '是';
  	  				expressValue = true;
  	  			} else {
  	  				$('#price').val(certificateType['generalPrice']);
  	  				expressName = '否';
  	  				expressValue = false;
  	  			}
  	  		}); 
		});

		//-->
		</script>
	</head>
	<body class="bg_color" style="margin-top:4px">
	<s:form action="archivesCertificateManageAction_priceBalance" namespace="/DALY">
		<input type="hidden" name="userID" id="userID" value=""><!-- 用户ID -->
		<input type="hidden" name="certificateInfoArrayJSON" id="certificateInfoArrayJSON"><!-- 出证明细JSON格式 -->
			<table style="height:100%;border: red 1px solid;font-size: 12px;" cellspacing="0" cellpadding="0" width="100%">
			 	<tr>
			        <td colspan="7">利用人信息</td>
			    </tr>
			    <tr>
			        <td colspan="7">
			        	<table width="100%" border="1">
					        <tr>
					        <td>
					        	姓名<input id="name" type="text" name="userRealName"/>
					        	<input id="findByName" type="button"  value="|"/>
					        </td>
							<td >
								证件号<input  id="cardId" type="text" name="IDCardNo"/>
								<input id="findByIDCardNo" type="button"  value="|"/>
							</td>
							<td >
								所属部门<input id="dept" type="text"  name="userDepartment" />
							</td>
							</tr>
							<tr>
						        <td>E_mail<input  id="e_mail" type="text"  name="email"/></td>
								<td>电话<input id="telNo" type="text" name="tel" /></td>
								<td>所属区域
									<select name="area" id="area" style="width:150px;" >
										<option value="1">&nbsp;&nbsp;&nbsp;&nbsp;—&nbsp;&nbsp;国内&nbsp;&nbsp;—&nbsp;&nbsp;&nbsp;&nbsp;</option>
										<option value="2">&nbsp;&nbsp;&nbsp;&nbsp;—&nbsp;&nbsp;国外&nbsp;&nbsp;—&nbsp;&nbsp;&nbsp;&nbsp;</option>
									</select>
								</td>
						    </tr>
				    	</table>
			    	</td>
			    </tr>
			    <tr>
			        <td colspan="7">&nbsp;添加出证明细信息</td>
			    </tr>
			    <tr>
			         <td colspan="7">
				        <table width="100%"  id="showTable" style="border: #104da6 1px solid;" border="1" cellpadding="0" cellspacing="1px">
				          <thead class="tableHead">
					        <tr>
								<th>出证类型</th>
								<th>单价</th>
								<th>份数</th>
								<th>加急</th>
								<th>金额</th>
								<th>备注</th>
								<th>操作</th>
							</tr>
					       </thead>
					       <tr height="20px;">
					       		<td>
						       		<s:select id="certificateTypes" list="certificateTypes" headerKey="0" headerValue="---请选择---" 
										listKey="iD" listValue="name" />
								</td>
								<td><input type="text" id="price" name="price"></td>
								<td><input type="text" id="total" name="total"></td>
								<td><input type="checkbox" id="expressFlag" name="expressFlag" value="1" /></td>
								<td>&nbsp;</td>
								<td><input type="text" id="remark" name="remark"></td>
								<td align="center"><a href="javascript:addCertificateType();">增加</a></td>
					       </tr>
					       <tbody id="certificateTbody" align="center">							      
						   </tbody>
							<tr height="20px;">
								<td colspan="7" align="left">
									应收金额:<input type="text" id="shouldCharge" name="shouldCharge" style="width: 80px;">
									实收金额:<input type="text" id="realCharge" name="realCharge" style="width: 80px;">
									发票编号:<input type="text" id="invoiceSN" name="invoiceSN" style="width: 120px;"/>&nbsp;&nbsp;
									<span style="float: right;margin-right: 10px;">
								</td>
							</tr>
				        </table>
					  </td> 
			       </tr>
				<tr>
					<td align="center">
					    <input type="button" id="btOk" class="button"  value="收&nbsp;&nbsp;费"/>&nbsp;&nbsp;
						<input type="button" id="btCancel" class="button" value="取&nbsp;&nbsp;消" onClick="javascript:window.close();"/>
				    </td>
				</tr>
			</table>
	</s:form>	    
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
							       <tbody align="center">
							       </tbody>
							    </table> 	
						  </div>					   					      
					   </td>
					</tr>					
				</table>
		   </div>
	</body>
</html>
