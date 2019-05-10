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
    
    <title>档案装盒</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<script type="text/javascript" src="js/common.js"></script>
	 <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	 <script type="text/javascript" src="js/jquery.form.js"></script>
    <script language="javascript" type="text/javascript">
		//打开新增对话框
		function showAdd()
		{
			window.showModalDialog("Item1.html","newwindow","dialogWidth=700px;dialogHeight:500px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");
		}
		//扫描盒条码
		function showSMHTM(){
		    var returnValue = window.showModalDialog("/aiim/YJJSGL/LRHTM.htm",window,"dialogWidth=300px;dialogHeight:100px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");
		    if(returnValue != ""){
			    window.location.href = "/aiim/YJGL/YJJSAction_findArchivesInfoByBoxBarcode.action?archivesBoxBarcode="+returnValue;
			}
		}
		$(document).ready(function(){

			$("#print").bind('click',boxCatalog);
			
		   //换盒
		   $("#changeBox").bind('click',showSMHTM);
		   <s:if test="#request.archivesBoxBarcode == null">
		      $("#changeBox").click();
		   </s:if>
		   <s:elseif test="#request.archivesBoxBarcode == ''">
		      $("#changeBox").click();
		   </s:elseif>
		   
		   //移除
		   $("#remove").bind('click',function(){
			   $('#removeForm').ajaxSubmit({
				   beforeSubmit:function(){},
				   success:function(data){
				      $("input[name='archivesBarcodes']:checked").parent().parent().remove();
			       },
				   error:function(XMLHttpRequest, textStatus){alert(textStatus+","+XMLHttpRequest.status);}
			   });
		   });
		   
		   //查找档案信息添加到档案信息表中
		   $("#add").bind('click',function(){
		        $.ajax({
		           url:"json/YJJSAction_putArchiveToBox.action",
		           type:"post",
		           data:'archivesBoxBarcode='+$("#archivesBoxBarcode").text()+'&archivesBarcode='+$("#archivesBarcode").val(),
		           success:function(date){
		        	  var archivesInfo = eval('('+date+')');
		              if(archivesInfo.archivesID== "" || archivesInfo.title ==""){
		                 alert("没有找到数据！");
		              }else{
		                // var archivesInfo = eval('('+date+')');
		                 var firstTr = $("#showTable").children("tbody").children().first();
		                 var trColor = '#e0edff';
		                 if(firstTr.attr("bgcolor")=='#e0edff' && firstTr != null){
		                    trColor = "#eef5ff";
		                 }
		                 //避免重复添加
		                 var inputs = $("input[name='archivesBarcodes']");
		                 var pFlag = true;
		                 for(var i=0;i<inputs.length;i++){
		                     if(inputs[i].value == archivesInfo.archivesBarcode){
		                       pFlag = false;
			                   alert("列表中以存在该档案！");
			                   break;
			                 }
		                 }
		                 if(pFlag){
		                    $("#showTable").prepend("<tr bgcolor='"+trColor+"' id=\"row"+archivesInfo.archivesBarcode+"\" onclick=\"selectRow(this)\">"+
												        "<td align='center'><input type='checkbox' name='archivesBarcodes' value='"+archivesInfo.archivesBarcode+"'/></td>"+
												        "<td>"+archivesInfo.archivesBarcode+"</td>"+
												        "<td>"+archivesInfo.archivesID+"</td>"+
												        "<td>"+archivesInfo.title+"</td>"+
												      "</tr>");
		                 }
		              }
		           },
		           error:function(XMLHttpRequest, textStatus){
		              alert(textStatus+","+XMLHttpRequest.status);
		           }
		        });
		    });
		});

		//打印盒内目录
		function boxCatalog() {
			var url = '/aiim/PRINT/catalogPrintAction_printConfig.action?catalogTypeID=2&boxBarcode=' + $("#archivesBoxBarcode").val();
			showWinModalDialogScroll(url,'700px','500px');
		}
			
    </script>
  </head>
  
  <body>
    <input type="hidden" name="preSelectRow" id="preSelectRow" />
    <fieldset style="margin-top:5px;">
        <table>
            <tr>
                <td style="font-size: 12px;">
                    请扫描或输入档案条码：
                </td>
                <td >
                   <input type="text" id="archivesBarcode" name="archivesBarcode"/>
                </td>
                <td>
                  <input type="button" class="button" value="添     加" id="add"/>  
                </td>
            </tr>
         </table>
    </fieldset>
    <hr/>
      &nbsp;&nbsp;<input type="button" class="button" value="移   除" id="remove"/>
     <%-- &nbsp;&nbsp;<input type="button" class="button" value="清   空" id="removeAll"/> --%>
      &nbsp;&nbsp;<input type="button" class="button" value="换   盒" id="changeBox"/>
      &nbsp;&nbsp;<input type="button" class="button" value="打印盒目录" id="print"/>
     <table class="tabletop" width="100%" style="margin-top: 2px;">
	     <tr>
              <td class="tableTitle" width="60px">档案装盒</td>
              <td class="tableTitle" >盒条码：<label id="archivesBoxBarcode">${archivesBoxBarcode }</label></td>							                	
         </tr>
	</table>
	<form action="YJGL/YJJSAction_removeFromBox.action" method="post" name="removeForm" id="removeForm" style="padding: 0;margin: 0;">
	<table id="showTable" width="100%" style="margin:0px; border: #104da6 1px solid;" cellspacing="1px" cellpadding="0px" >						
		<thead class="tableHead">
			<tr class="bgTitle">
				<th width="35px">选择</th>								
				<th>档案条码</th>
				<th>档号</th>
				<th>题名</th>						
			</tr>
		</thead>
		<tbody id="ghlb">
		  <s:iterator value="#request.storeroomArchivesInfos" status="status">
		    <s:if test="(#status.index+1)%2==0">
		       <s:set name="color" value="'#e0edff'" scope="page"></s:set>
		    </s:if>
		    <s:else>
		       <s:set name="color" value="'#eef5ff'" scope="page"></s:set>
		    </s:else>
		    <tr  bgcolor="${pageScope.color}" id="row<s:property value="#status.index+1"/>" onclick="selectRow(this)">
			  <td align="center">
			     <input type="checkbox" name="archivesBarcodes" value="<s:property value="archivesBarcode"/>"/>
			  </td>
			  <td><s:property value="archivesBarcode"/></td>
			  <td><s:property value="archivesID"/></td>
			  <td><s:property value="title"/></td>
			</tr>
		  </s:iterator>	
	    </tbody>
	</table>
	</form>
  </body>
</html>
