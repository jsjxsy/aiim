<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.orifound.aiim.entity.ArchivesTypeDataItem"%>
<%@page import="com.orifound.aiim.entity.ArchivesInfo"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Map dataItems = (Map)request.getAttribute("dataItems");
List archivesInfos = (List)request.getAttribute("archivesInfos");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>审核未通过</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.form.js"></script>
	
	<script type="text/javascript" src="dwr/interface/ArchivesInfoManageDWR.js"></script>
    <script type="text/javascript" src="dwr/engine.js"></script>

    <script type="text/javascript">
    function showItem(rowObj) {
	    var obj = new Object();	
	    var NBXHS = new Array(rowObj.id);
	     
		obj.archivesTypeID = $("#archivesTypeID").val();
		if($(rowObj).children("[id='Title']").children("img").attr("src") == "images/type_doc.gif"){
			obj.fileType = 0;
	    }else if($(rowObj).children("[id='Title']").children("img").attr("src") == "images/type_file.gif"){
	    	obj.fileType = 1;
	    }
		obj.operationType="edit";
		//obj.fileType = 1;//文件级管理 0为文件 1为案卷 2为添加卷内
		obj.NBXHS = NBXHS;

		var returnValue = window.showModalDialog(				
			"<%=basePath%>YJJSGL/item_SH_edit.jsp",
			obj,
			"dialogWidth=700px;dialogHeight:500px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;resizable=no;");
		if(returnValue==1){
			document.conditionForm.submit();
		}
    }

  //打开原文管理页面
	function ywgl(nbxh) {
	var retval = window.open('/aiim/DAGL/ywgl.jsp?archivesTypeID='+$("#archivesTypeID").val()+'&NBXH='+nbxh,"",
					"dialogWidth:590px; dialogHeight:500px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");
   }
    
  //卷内：双击行，查看著录信息
	function showChildItem(rowObj) {
		    var obj = new Object();	
		   
		    var NBXHS = new Array($("#parentNBXH").val(),rowObj.id);
		     
			obj.archivesTypeID = $("#archivesTypeID").val();
			obj.operationType="edit";
			obj.fileType = 2;//文件级管理 0为文件 1为案卷 2为添加卷内
			obj.NBXHS = NBXHS;
			
			var returnValue = window.showModalDialog(				
				"<%=basePath%>YJJSGL/item_SH_edit.jsp",
				obj,
				"dialogWidth=700px;dialogHeight:500px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;resizable=no;");
			if(returnValue==1){
				document.conditionForm.submit();
			}
	}
    
  //单击案卷行，改变颜色，显示卷内文件列表
	var rowId="";   //保存上一次点击行“tr”的ID；
	var rowColor="";   //保存上一次点击行的颜色
	function clickRow(obj)
	{
		if(document.getElementById(rowId)==null){//第一次点击处理
			rowId=obj.id;	//保存被点击行的ID
			rowColor=obj.style.backgroundColor;//保存被点击行的颜色
			obj.style.backgroundColor='#a3c9ff';		
		}else{
			document.getElementById(rowId).style.backgroundColor=rowColor;
			rowId = obj.id;
			rowColor=obj.style.backgroundColor;
			obj.style.backgroundColor='#a3c9ff';
			
		}	 
		$("#parentTitle").html($(obj).children("[id='Title']").text());//在卷内文件列表左上方显示案卷题名
		$("#parentNBXH").val(obj.id);
		findChildArchivesInfosByNBXH(obj.id);//查询案卷卷内文件，并显示出来
	}

	//DWR：查询卷内文件
	function findChildArchivesInfosByNBXH(nbxh){
		 ArchivesInfoManageDWR.findChildArchivesInfosByNBXH($("#archivesTypeID").val(),nbxh,findChildArchivesInfosByNBXHBack);	
	}
	function findChildArchivesInfosByNBXHBack(data){//查询卷内文件回调函数	
	    var $JuanNeiListBody = $("#JuanNeiListBody");
	    
	    //清空tbody中原有的代码
	    $JuanNeiListBody.html("");

	    $("#childRecordNum").text(data.length);
	    
	    var html = "";
	    var bgcolor = "#eef5ff";
	    for(var i=0;i<data.length;i++){
	      //设置行颜色
	      if(i%2==0){bgcolor = "#eef5ff"}else{bgcolor = "#e0edff"}
	      
	       //遍历集合生成html代码
	       html = html+"<tr bgcolor=\""+bgcolor+"\" id=\""+data[i].NBXH+"\" height=\"20px\" title=\"双击查看详细信息\" onclick=\"selectRow(this)\" ondblclick=\"showChildItem(this)\">";
	       html +="<td align=\"center\">"+(i+1)+"</td>";
		    var rowFieldsValues = data[i].rowFieldsValues;
		    <s:iterator value="#request.dataItems" var="dataItem">
			     for(var property in rowFieldsValues){ 
			          if(property == '<s:property value="#dataItem.key"/>'){
			            if(rowFieldsValues.<s:property value="#dataItem.key"/>.value == null){
			               html += "<td></td>";
			            }else{
			               html += "<td>"+rowFieldsValues.<s:property value="#dataItem.key"/>.value+"</td>";
			            }
			         }	
		         }
	        </s:iterator>
	      html += "<td><a href=\"javascript:ywgl('"+data[i].NBXH+"')\">查看</a></td>";
	      html += "</tr>";
	      
	      //将代码添加到tbody
	      $JuanNeiListBody.html(html);
	    }	
	}

	 //提交审核
	 function clickSubmitCheck(){
		 $("#form1").ajaxSubmit({
            url:"DAGL/submitToInputCheck.action",
            //data:"archivesTypeID="$("#archivesTypeID"),
		    success:function(data){
			    alert(data);
			    document.conditionForm.submit();
		    },
		    error:function(XMLHttpRequest, textStatus){alert(textStatus+","+XMLHttpRequest.status);}	     
	     });
	}

	 $(document).ready(function(){
	   getArchivesTypeByID();
	 });
	// 获得档案类型对象
	function getArchivesTypeByID(){
	    ArchivesInfoManageDWR.getArchivesTypeByID($("#archivesTypeID").val(),getArchivesTypeByIDCallBack);
	}
	function getArchivesTypeByIDCallBack(data){
	    $("#archivesTypeNameText").text(data.fullName+"("+data.fullCode+")");
	}
    </script>
  </head>
  
  <body>
    <input type="hidden" name="preSelectRow" id="preSelectRow" />
    <form name="conditionForm" action="YJGL/YJJSAction_findArchivesForXCBMDAYJ.action" method="post" style="margin: 0;padding: 0;">
       <input type="hidden" id="archivesTypeID" name="archivesTypeID" value="${requestScope.archivesTypeID }"/>
       <input type="hidden" id="state" name="state" value="${requestScope.state}"/>
       <input type="hidden" name="dataPageInfo.currentPage" id="currentPage" value="1"/>
    </form>
    <table width="100%" style="margin: 0px;" cellspacing="0" cellpadding="0">
		<tr>
			<td> 
				<input type="image" id="imgSongshen" src="images/songshen.gif" onmouseover="changeImage(this,'songshen2.gif')" onmouseout="changeImage(this,'songshen.gif')" onclick="clickSubmitCheck()"/>
			</td>
			<td>
				<div id="location" style="margin-right: 2px; width: 280px; display: inline-block; float: right; margin-top: 5px; color: blue; text-align: right;">
					<font style="font-size: 12px;"><b>当前位置：</b>档案移交&nbsp;&gt;&gt;&nbsp;&nbsp;著录审核未通过</font>
				</div>
			</td>
		</tr>
	</table>
	<table class="tabletop" width="100%">
	   <tr>
           <td class="tableTitle">档案&nbsp;&nbsp;<label id="archivesTypeNameText"></label></td>
           <td align="right" class="text">第${dataPageInfo.currentPage}页 共${dataPageInfo.pageCount}页 共${dataPageInfo.rowCount}条记录</td>							                	
       </tr>
	</table>
	<form id="form1" style="margin: 0;padding: 0;" method="post">
	<input type="hidden" id="archivesTypeID" name="archivesTypeID" value="${requestScope.archivesTypeID }"/>
    <table  id="showTable" cellpadding="0px" cellspacing="1px">	
       <thead class="tableHead">			
			<tr>
				<th width="35px">选择</th>
				<th style="width: 30px;">序号</th>
				<%
					for(Object dataItem : dataItems.values()){
					   out.print("<th>"+((ArchivesTypeDataItem)dataItem).getDisplayText() +"</th>");
					}
				%>
				<%--<s:iterator value="#request.dataItems">
				  <th><s:property value="value.displayText"/></th>
				</s:iterator>
				
				--%>
				<th>状态</th>
				<th align="center" style="width: 35px">操作</th>
			</tr>
		</thead>
		<tbody>
		<%
	   	     ArchivesInfo archivesInfo = null;
	   	     if(archivesInfos != null){
	   	        for(int i =0;i<archivesInfos.size();i++){
	   	    	    archivesInfo  = (ArchivesInfo)archivesInfos.get(i);
	   	    	
	   	    	    if(i%2 == 0){    		
	   	    		    out.println("<tr bgcolor=\"#eef5ff\" id=\""+archivesInfo.getNBXH()+"\"  title=\"双击查看详细信息\"  onclick=\"clickRow(this)\" ondblclick=\"showItem(this)\">");   	    		
	   	    	    }else{
	   	    		    out.println("<tr bgcolor=\"#e0edff\"  id=\""+archivesInfo.getNBXH()+"\" title=\"双击查看详细信息\"  onclick=\"clickRow(this)\" ondblclick=\"showItem(this)\" >");
	   	    	    }

	   	    	   if(archivesInfo.getFixedFlag() == true){
	   	    		  out.println("<td align=\"center\"><input type=\"checkbox\" name=\"NBXHS\" value=\""+archivesInfo.getNBXH() +"\"></td>");
	   	    	   }else{
	   	    		  out.println("<td align=\"center\"><input type=\"checkbox\" name=\"NBXHS\" value=\""+archivesInfo.getNBXH() +"\" disabled=\"disabled\"></td>");
	   	    	   }
	   	    	    
	   	    	    out.println("<td >"+(i+1)+"</td>");
			
	   	    	 for(Object dataItem : dataItems.values()){
	   	    		   if(archivesInfo.getRowFieldsValues().get(((ArchivesTypeDataItem)dataItem).getColumnName()).getValue() == null){
	   	    			  out.println("<td height=\"20px\"></td>");
	   	    		   }else{
	   	    			  out.print("<td id=\""+ ((ArchivesTypeDataItem)dataItem).getColumnName() +"\">");
					      if("Title".equals(((ArchivesTypeDataItem)dataItem).getColumnName())){
					    	  if("1".equals(archivesInfo.getRowFieldsValues().get("ParentFlag").getValue())){
					    		  out.print("<img src=\"images/type_file.gif\" width=\"20px\" height=\"15px\"/>");
					    	  }else{
					    		  out.print("<img src=\"images/type_doc.gif\" width=\"20px\" height=\"15px\"/>");
					    	  }
					      }
					      out.println(archivesInfo.getRowFieldsValues().get(((ArchivesTypeDataItem)dataItem).getColumnName()).getValue() +"</td>");
					   }
				    }
	   	    	   // out.println("<td align=\"center\"><a href=\"javascript:function(){return false;}\" onclick=\"showItem($(this).parent().parent()[0])\">审核</a></td>");
	   	    	   if(archivesInfo.getFixedFlag() == true){
	   	    		  out.print("<td style=\"color: red;\">已修改</td>");
	   	    	   }else{
	   	    		  out.print("<td style=\"color: red;\">未修改</td>");
	   	    	   }
	   	    	   out.print("<td align=\"center\"><a href=\"javascript:function(){return false;}\" onclick=\"showItem($(this).parent().parent()[0])\">修改</a></td>");
	   	    	   out.println("</tr>");
	   	    	  //  out.print("<td><a href=\"javascript:ywgl(\""+archivesInfo.getNBXH()+"\")>查看</a></td></tr>");
	   	         }
	   	      }
	   	    %>
	 </tbody>
	 </table>
	</form>
	<table width="100%" style="font-size: 12px;">
			 <tr>
			    <td>
			       <input type="checkbox" id="SelectAll" accesskey="S"  name="SelectAll" onclick="selectAll(this)" title="选中/取消 所有记录(S)"  /><label for="SelectAll">全选</label>
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
	<hr/>
	<table class="tabletop" width="100%">
           <tr>
               <td>
                   <label id="JuanMing" class="tableTitle">卷内目录—<span id="parentTitle" ></span></label>
               </td>
               <td align="right"  class="text" >
               	   <label style="margin-right:4px" id="rsInfo">共<span id="childRecordNum"></span>条记录</label>
               </td>							                	
           </tr>
	</table>
	<input type="hidden" name="parentNBXH" id="parentNBXH" />
	<table id="showTable" cellpadding="0px" cellspacing="1px">				
		<thead class="tableHead">			
			<tr>
				<th style="width: 30px;">序号</th>
				
				<s:iterator value="#request.dataItems">
				  <th><s:property value="value.displayText"/></th>
				</s:iterator>
				
				<th align="center" style="width: 35px">原文</th>
			</tr>
		</thead>
		<tbody id="JuanNeiListBody" >

		</tbody>
   </table>
	<s:if test="#request.message !=null ">
	  <script>alert("${message}");</script>
	</s:if>
  </body>  
</html>
