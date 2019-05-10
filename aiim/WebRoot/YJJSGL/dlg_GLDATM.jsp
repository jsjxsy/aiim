<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.orifound.aiim.entity.ArchivesType"%>
<%@page import="com.orifound.aiim.entity.PaperTransferBatchesArchvTypeDetail"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	    <base href="${basePath}">
		<title>档案条码关联</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="css/common.css"/>
		<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="js/jquery.form.js"></script>
		<script type="text/javascript">
		 $(document).ready(function(){
		    $($("#table").children('tbody').children().first()).click();

		    $("#GL").bind("click",function(){
		    	  $('#myForm').ajaxSubmit({
				        beforeSubmit:function(){
				          $("#time").val(new Date());
				          if($("#archivesID").val() == null || $("#archivesID").val() == ""){
			                 alert('请输入档号！');
			                 return false;	             
				          }else if($("#barcode").val() == null || $("#barcode").val() == ""){
				             alert('请输入条码！');
				             return false;
				          }
				        }, 
				        success:function(data){
				           var color = "#e0edff";
				           var fristTr = $("#showTable").children('tbody').children(":nth-child(1)");
				           if(fristTr.css("background") == "#e0edff"){
				             color = "#eef5ff"
				          }

				          var archivesInfo = eval('('+data+')');
				          //alert(archivesInfo.archivesID);
				          var $archivesID_tds = $("#showTable").children('tbody').children().children(":nth-child(1)");
				          if($archivesID_tds.length == 0){
				        	  var array = new Array(archivesInfo.archivesID,archivesInfo.barcode,archivesInfo.title);
					          var $tr = getRow($("#showTable"),array);
					          $tr.attr("id",archivesInfo.barcode);
					          $tr.css("background",color);
					          $tr.children(":nth-child(1)").css({"height":"20px"});
					          $("#showTable").children('tbody').prepend($tr);
					      }else{
						      var flag = true;
					    	  for(var i=0;i< $archivesID_tds.length;i++){
						          if($($archivesID_tds[i]).text() == archivesInfo.archivesID){
						        	  $($archivesID_tds[i]).next().text(archivesInfo.barcode);
						        	  flag = false;
							      }
						      }
						      if(flag){
						    	  var array = new Array(archivesInfo.archivesID,archivesInfo.barcode,archivesInfo.title);
						          var $tr = getRow($("#showTable"),array);
						          $tr.attr("id",archivesInfo.barcode);
						          $tr.css("background",color);
						          $tr.children(":nth-child(1)").css({"height":"20px"});
						          $("#showTable").children('tbody').prepend($tr);
							  }
						  }
				          $("#barcode").val("");
				          $("#tr"+$("#archivesTypeID").val()).click();
				        },
				        error:function(XMLHttpRequest, textStatus){alert(textStatus+","+XMLHttpRequest.status);}
				    });  
			  });
            /*$('#myFormId').submit(function() { 
			    // 提交表单 
			    $(this).ajaxSubmit(function(){
			      slert($(this));
			    }); 
			    // 为了防止普通浏览器进行表单提交和产生页面导航（防止页面刷新？）返回false 
			    return false; 
			   }); */
			   
			  $("#putToBox").bind('click',function(){
			     var $tds = $("#showTable").children('tbody').children().children(":nth-child(1)");
			     var jsonStr;
			     //得到json串
			     if($tds.length>0){
			         jsonStr = "{'archivesTypeID':'"+$("#archivesTypeID").val()+"','archives':[";
				     $.each($tds,function(index,td){
				        if(index>0){
				         jsonStr+=","
				        }
				        jsonStr +="{'barcode':'"+$(td).next().text()+"','archivesID':'"+$(td).text()+"','archivesTypeID':'"+$("#archivesTypeID").val()+"'}";
				     });
				     jsonStr +="]" 
			     }
			     if(jsonStr == null){
			        alert('列表中无数据！');
			     }else{
			         var returnValue = window.showModalDialog("/aiim/YJJSGL/LRHTM.htm",window,"dialogWidth=250px;dialogHeight:100px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");
			         jsonStr +=",'boxBarcode':'"+returnValue+"'}"
			         $.ajax({
			             url:'json/YJJSAction_putArchivesToBox.action',
			             type:'post',
			             data:'jsonResult='+jsonStr,
			             success:function(data){
			                alert(data);
			             },
			             error:function(XMLHttpRequest, textStatus){alert(textStatus+","+XMLHttpRequest.status);}
			         });
			     }
			  }); 
		 });
		 
		 //得到表格的一行
		 function getRow(table,array){
		    var $ths = table.children("thead").children().children();
		    var $tr = $("<tr></tr>");
		    for(var i=0;i<$ths.length;i++){
		       $tr.append("<td>"+array[i]+"</td>");
		    }
		    return $tr;
		 }

		 //选中分类
		 function selectTr(obj,archivesTypeID,batNo){
		    if($("#showTable").children('tbody').children().length>0 && $("#archivesTypeID").val() != archivesTypeID){
		       if(confirm("档案列表将被清空，确认跟换类型？")){
		           $("#showTable").children('tbody').children().remove();
		           setTrColor(obj,archivesTypeID,batNo);
		           findTopArchivesByBatNoWhitOutBarcode(archivesTypeID,batNo);
		           //findTopArchivesInfo(archivesTypeID,batNo);
		       }
		    }else{
		          setTrColor(obj,archivesTypeID,batNo);
		          findTopArchivesByBatNoWhitOutBarcode(archivesTypeID,batNo);
		    }   
		 }
		 //设置选中分类颜色和两个input的值
		 function setTrColor(obj,archivesTypeID,batNo){
	       $.each($("#table").children('tbody').children(),function(index,str){
		        $(str).css('background','#e0edff');
		    });
		    
		    $(obj).css('background','#a4caef');
		    $("#archivesTypeID").val(archivesTypeID);
		    $("#batNo").val(batNo);
		 }

		 //查找第一条档案信息
		 function findTopArchivesByBatNoWhitOutBarcode(archivesTypeID,batNo){
			 $.ajax({
	             url:'json/YJJSAction_findTopArchivesByBatNoWhitOutBarcode.action',
	             type:'post',
	             data:'archivesTypeID='+archivesTypeID+'&batNo='+batNo,
	             success:function(data1){
	                var archivesInfo1 = eval("("+data1+")");
	                $("#archivesID").val(archivesInfo1.archivesID);
	                $("#title").text(archivesInfo1.title);
	             },
	             error:function(XMLHttpRequest, textStatus){alert(textStatus+","+XMLHttpRequest.status);}
	         });
	     }
		</script>
	</head>

	<body>
		<table style="width: 100%;">
			<tr>
				<td style="width: 200px; height: 600px; vertical-align: top; border: 1px #104da6 solid;">
				    <table class="tabletop" width="100%">
						<tr>
			                <td class="tableTitle">批次：${batNo}</td>
			            </tr>
					</table>
					<table style="width: 100%;" cellpadding="0px;" cellspacing="1px;" id="table">
					<% 
						Map paperTransferBatchesArchvTypeDetails = (Map)request.getAttribute("paperTransferBatchesArchvTypeDetails");
					    Map archivesTypes = (Map)request.getAttribute("archivesTypes");
					    Integer key = 0;
					    for(Object obj : paperTransferBatchesArchvTypeDetails.values()){
					    	key = ((PaperTransferBatchesArchvTypeDetail)obj).getArchivesTypeID();
					    	out.println("<tr style=\"background-color: #e0edff; font-size: 12px; height: 20px;\" onclick=\"selectTr(this,'"+ key +"','"+ request.getAttribute("batNo") +"')\" id=\"tr"+key+"\">");
					    	out.println("<td>"+((ArchivesType)archivesTypes.get(key)).getFullName()+"</td>");
					    	out.println("</tr>");
					    }
					%>
					</table>
				</td>
				<td style="height: 400px; vertical-align: top; border: thin #104da6 solid;" id="">
				    <form action="json/YJJSAction_pasteArchivesBarcode.action" name="myForm" id="myForm" method="post" style="margin: 0;padding: 0;">
			        <fieldset>
			            <input type="hidden" name="time" id="time"/>
			            <input type="hidden" name="batNo" id="batNo"/>
			            <input type="hidden" name="archivesTypeID" id="archivesTypeID"/>
				        <table align="center">
				         <tr>
				            <td class="text">档号</td>
				            <td>
				              <input type="text" name="archivesID" id="archivesID"/>
				              <%--  <input type="text" name="formationYear" id="formationYear" style="width: 100px;"/>-
				               <input type="text" name="contentID" id="contentID" style="width: 100px;"/> --%>
				            </td>
				          </tr>
				          <tr>
				            <td class="text">题名</td>
				            <td id="title" style="font-size: 12px;"><%--<input type="text" name="title" id="title"/>--%></td>
				          </tr>
				          <tr>
				            <td class="text">条码</td>
				            <td><input type="text" name="barcode" id="barcode"/></td>
				          </tr>
				          <tr>
				            <td></td>
				            <td align="center">
				                <input type="button" class="button" value="关     联" id="GL"/>&nbsp;&nbsp;
				                <input type="button"  id="putToBox" class="button" value="装     盒"/>
				            </td>
				          </tr>
				        </table>
			         </fieldset>
			         </form>
					<table class="tabletop" width="100%" cellspacing="0" cellpadding="0">
				            <tr>
				                <td class="tableTitle">党群-党务综合</td>
				                <td align="right"  class="text" >
				                </td>							                	
				            </tr>
				    </table>
				    <div style=" display: block;overflow: auto; height: 420px">
				    <table id="showTable" width="100%" style="margin:0px;" cellspacing="1px" cellpadding="0px">						
						<thead class="tableHead">
							<tr class="bgTitle">
								<th>档号</th>
								<th>条码</th>
								<th>题名</th>
							</tr>
						</thead>
						<tbody>
							
						</tbody>		
					</table>
					</div>
				</td>
			</tr>
		</table>
	</body>
</html>
