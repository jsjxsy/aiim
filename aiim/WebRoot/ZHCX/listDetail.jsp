<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.orifound.aiim.entity.ArchivesTypeDataItem"%>
<%@page import="com.orifound.aiim.entity.ArchivesInfo"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="my" uri="/myTag"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>跨门类查询子页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/common.css">
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/jquery.form.js"></script>
	
	<script type="text/javascript" src="dwr/interface/ArchivesUseAction.js"></script>
	<script type="text/javascript" src="dwr/interface/IntegratedQueryAction.js"></script>
	<script type="text/javascript" src="dwr/interface/ArchivesInfoManageDWR.js"></script>
	 <script type="text/javascript" src="dwr/util.js"></script>
	<script type="text/javascript" src="dwr/engine.js"></script>
	<SCRIPT type="text/javascript">
	

	//处理单击行事件
//	var rowId="";   //保存上一次点击行“tr”的ID；
//	var rowColor="";   //保存上一次点击行的颜色
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


	//双击查看著录信息
	function showItem(rowObj) {
	    var obj = new Object();	
	    var NBXHS = new Array(rowObj.id);
	     
		obj.archivesTypeId = $("#archivesTypeId").val();
		obj.operationType="edit";
		var typeid = '#type'+rowObj.id;	//id 格式为type+NBXH的input隐藏输入中存放<s:property value="parentFlag"/>
		obj.fileType = 0;//文件级管理 0为文件 1为案卷 2为添加卷内
		if($(typeid).val()=='true'){//如果是案卷
			obj.fileType = 1;
		}			
//		alert(obj.fileType);
		obj.NBXHS = NBXHS;
		//打开查看界面，参数通过obj传递到子页面
		var returnValue = window.showModalDialog(				
			"<%=basePath%>ZHCX/item.jsp",
			obj,
			"dialogWidth=700px;dialogHeight:500px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;resizable=no;");
		
    }


	
/////////// 处理事件  //////////////////////
	//单击案卷行，改变颜色，显示卷内文件列表
	var rowId="";   //保存上一次点击行“tr”的ID；
	var rowColor="";   //保存上一次点击行的颜色
	function clickRowAJ(obj)
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
		//document.getElementById("imgChildNew").disabled = false;//使添加卷内文件可用
		//changeChildPic();//刷新按钮颜色		
		$("#parentNBXH").val(obj.id);	
		var parentFlagId = 'parent'+ obj.id;	//id 格式为type+NBXH的input隐藏输入中存放<s:property value="parentFlag"/>	
		if($('#'+parentFlagId).val()=='true'){//如果是案卷	
			//alert('anjuan')
			$("#junNeiListDiv").css("display","block");
			//$("#parentTitle").html($(obj).children("[name='Title']").text());//在卷内文件列表左上方显示案卷题名
			$("#parentTitle").html($("#archivesID"+obj.id).val() + "—" + $("#title"+obj.id).val());
			findChildArchivesInfosByNBXH(obj.id);//查询案卷卷内文件，并显示出来
		}else{//如果是文件
			//alert('wenjian');
			//将上一次显示的案卷目录以及卷内文件记录数清空
			$("#junNeiListDiv").css("display","none");
			$("#childRecordNum").html('');//卷内文件记录数设为空
			$("#parentTitle").html('');//卷内文件左上方的题名设为空
			$("#JuanNeiListBody").html('');//清空tbody中原有的代码
		}			
	//	findChildArchivesInfosByNBXH(obj.id);//查询案卷卷内文件，并显示出来
		
	}


	//将高级查询中的值清空
	function clearAdvanceInput(){
		$("#advanceQueryCondition").val("");
	}


////////////////DWR //////////////////		
	//DWR：查询卷内文件
	function findChildArchivesInfosByNBXH(nbxh){
		IntegratedQueryAction.findChildArchivesInfosByNBXH($("#archivesTypeId").val(),nbxh,findChildArchivesInfosByNBXHBack);	
	}
	function findChildArchivesInfosByNBXHBack(data){//查询卷内文件回调函数	
	    var $JuanNeiListBody = $("#JuanNeiListBody");
	    
	    $("#childRecordNum").html(data.length);//卷内文件记录数写到页面
	    //清空tbody中原有的代码
	    $JuanNeiListBody.html("");
	    
	    var html = "";
	    var bgcolor = "#eef5ff";

	    for(var i=0;i<data.length;i++){
	    
	      //设置行颜色
	      if(i%2==0){bgcolor = "#eef5ff"}else{bgcolor = "#e0edff"}
	      
	       //遍历集合生成html代码
	       html = html+"<tr style=\"height:18px;\" bgcolor=\""+bgcolor+"\" id=\""+data[i].NBXH+"\" title=\"双击查看详细信息\" onclick=\"clickRowJN(this)\" ondblclick=\"showArchivesInfo("+data[i].archivesTypeID+","+data[i].NBXH+")\">";
	   //    html +="<td><input type=\"checkbox\" name=\"NBXHS\" id=\"childNBXHS\" onclick='childOneSelect(this)' value=\""+data[i].NBXH+"\" /></td>";
	       html +="<td align=\"center\">"+(i+1)+"</td>";
		    var rowFieldsValues = data[i].rowFieldsValues;
		    <s:iterator value="#request.dataItems" var="dataItem">
			     for(var property in rowFieldsValues){ 
			          if(property == '<s:property value="#dataItem.key"/>'){
				         if(property=='ArchivesID'){//对档号特殊处理
				        	 if(rowFieldsValues.<s:property value="#dataItem.key"/>.value == null){
					        	 html = "<td></td>";
					         }else{
					        	 html += "<td><a href=\"#\" onclick=\"showArchivesInfo("+rowFieldsValues.ArchivesTypeID.value+","+rowFieldsValues.NBXH.value+");return false;\">"+rowFieldsValues.<s:property value="#dataItem.key"/>.value+"</a></td>";
						     }				        	 
					     }else if(rowFieldsValues.<s:property value="#dataItem.key"/>.value == null){
			               html += "<td></td>";
			            }else{
			               html += "<td>"+rowFieldsValues.<s:property value="#dataItem.key"/>.value+"</td>";
			            }
			         }	
		         }
	        </s:iterator>
	      html += "<td><a href=\"javascript:showArchivesInfo("+data[i].archivesTypeID+","+data[i].NBXH+")\">查看</a></td>";
	      html += "</tr>";
	      
	      //将代码添加到tbody
	      $JuanNeiListBody.html(html);
	    }
		//childOneSelect(null);	
	}
	

/////////卷内事件处理:
//单击卷内
var childRowId="";   //保存上一次点击行“tr”的ID；
var childRowColor="";   //保存上一次点击行的颜色
function clickRowJN(obj)
{
	if(document.getElementById(childRowId)==null){//第一次点击处理
		childRowId=obj.id;	//保存被点击行的ID
		childRowColor=obj.style.backgroundColor;//保存被点击行的颜色
		obj.style.backgroundColor='#a3c9ff';
	
	}else{
		document.getElementById(childRowId).style.backgroundColor=childRowColor;
		childRowColor=obj.style.backgroundColor;
		obj.style.backgroundColor='#a3c9ff';
		childRowId = obj.id;
	}	 
}


//卷内：双击行，查看著录信息
function showChildItem(rowObj) {
	    var obj = new Object();	
	   
	    var NBXHS = new Array($("#parentNBXH").val(),rowObj.id);
	     
		obj.archivesTypeId = $("#archivesTypeId").val();
		obj.operationType="edit";
		obj.fileType = 2;//文件级管理 0为文件 1为案卷 2为添加卷内
		obj.NBXHS = NBXHS;
		
		var returnValue = window.showModalDialog(				
			"<%=basePath%>DAGL/item.jsp",
			obj,
			"dialogWidth=700px;dialogHeight:500px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;resizable=no;");
		if(returnValue==1){
			document.conditionForm.submit();
		}
}


//根据档案分类编号和NBXH查询档案详细信息，包括档案原文电子文件信息
function showArchivesInfo(archivesTypeId,NBXH){
	alert(archivesTypeId+' : '+NBXH);
	 window.showModalDialog("/aiim/ZHCX/integratedQueryAction_findArchivesInfoByNBXH.action?archivesTypeId="+archivesTypeId+"&NBXH="+NBXH,"newwindow","dialogWidth=600px;dialogHeight=480px;center=yes;scroll=no;status=no;resizable=no;help=no;location=no");
}

//向利用清单中添加档案信息
function showArchivesToUseList(archivesUseWay){
	//var typeId = "";
	var archivesTypeIdAndNBXHs = "";//用于存储
	var arry = $("input:checked");
	alert(arry.length);
	for(i = 0;i<arry.length;i++){
		if(arry[i].name == "NBXHS"){
			//typeId = "typeId"+arry[i].value;
			archivesTypeIdAndNBXHs += $("#typeId"+arry[i].value).val()+":"+arry[i].value + ";"; 
		}
	}
	     
	alert(archivesTypeIdAndNBXHs);
	window.showModalDialog("/aiim/DALY/archivesUseAction_addArchivesToUseList.action?archivesTypeIdAndNBXHs="+archivesTypeIdAndNBXHs+"&archivesUseWay="+archivesUseWay,"newwindow","dialogWidth=600px;dialogHeight=480px;center=yes;scroll=no;status=no;resizable=no;help=no;location=no");
	return false;
}


	</SCRIPT>
	
  </head>
  
  <body>
  
      <s:if test="#request.errMsg != null">
	    <script>alert("${requestScope.errMsg}!")</script>
	  </s:if>
     <%--<input type="hidden" name="preSelectRow" id="preSelectRow" /> --%>
      
        <table width="100%" style="margin:0px;padding: 0;" align="center" cellpadding="0" cellspacing="0">
		     
			<tr>
			  <td align="center" id="find" style="display:block;">			   
			       <form action="ZHCX/integratedQueryAction_findArchivesByQuerySQL.action" method="post" name="conditionForm" style="margin: 0px; padding: 0px; display: none;">
				       <input type="hidden" name="dataPageInfo.currentPage" id="currentPage" value="1">
				       <input type="text" name="archivesTypeId" id="archivesTypeId" value="${requestScope.archivesType.ID }">
				       <input type="text" name="querySQL" id="querySQL" value="${requestScope.querySQL }">			       
			       </form>			      
			  </td>
			</tr>
			</table>
			<%--  ====================== --%>
			
			
			
		 <%
	       Map dataItems = (Map)request.getAttribute("dataItems");
	       List archivesInfos = (List)request.getAttribute("archivesInfos");
   		 %>
		
		<!-- 控制当没有数据时，列名不会显示出来    -->
		<s:if test="#request.dataPageInfo.rowCount>0">
			<!-- 当记录数大于0时，列名显示  -->
			<table width="100%" style="margin:0px; display: block;" align="center" cellpadding="0"  cellspacing="0">	
		</s:if>	
		<s:else>
			<!-- 当记录数小于0时，列名不显示  -->
			<table width="100%" style="margin:0px; display: none" align="center" cellpadding="0" cellspacing="0">	
		</s:else>	 	 			
			<tr >
				<td align="center" height="25px">
					<table class="tabletop" width="100%">
						<tr>
			                <td class="tableTitle">档案列表</td>
			                <td align="right" class="text">第${dataPageInfo.currentPage}页 共${dataPageInfo.pageCount}页 共${dataPageInfo.rowCount}条记录</td>							                	
			            </tr>
					</table>
					
					<table id="showTable" cellpadding="0px" cellspacing="1px">	
						
						<thead class="tableHead">			
								<tr>
									<th width="35px">选择</th>
									<th style="width: 30px;">序号</th>
									<%
									for(Object dataItem : dataItems.values()){
										   System.out.println(((ArchivesTypeDataItem)dataItem).getColumnName());
										   %>
										   <th><%=((ArchivesTypeDataItem)dataItem).getDisplayText() %></th>
										   <% 
									   }
									%>
									<th align="center" style="width: 35px">详细</th>
								</tr>
							</thead>
							<form id="tableForm" action="DALY/archivesUseAction_addArchivesToUseList.action" method="post" style="margin: 0;padding: 0;">
													
						       <%
						   	     ArchivesInfo archivesInfo = null;
						   	    if(archivesInfos != null){
						   	      for(int i =0;i<archivesInfos.size();i++){
						   	    	archivesInfo  = (ArchivesInfo)archivesInfos.get(i);
						   	    	
						   	    	  if(i%2 == 0){
						   	    		  %>
						   	    		  <tr bgcolor="#eef5ff" id="<%=archivesInfo.getNBXH() %>"  onclick="clickRowAJ(this)" ondblclick="showArchivesInfo('<%=archivesInfo.getArchivesTypeID()%>','<%=archivesInfo.getNBXH() %>')">
						   	    		  <%
						   	    	  }else{
						   	    		%>
						   	    		<tr bgcolor="#e0edff"  id="<%=archivesInfo.getNBXH() %>" onclick="clickRowAJ(this)" ondblclick="showArchivesInfo('<%=archivesInfo.getArchivesTypeID()%>','<%=archivesInfo.getNBXH() %>')">
						   	    		<%
						   	    	  }
						   	    	%>
						   	    	
						   	    	<td align="center">
							   	    	<input type="hidden" id="title<%=archivesInfo.getNBXH() %>" value="<%=archivesInfo.getTitle() %>"/>
							   	    	<input type="hidden" id="archivesID<%=archivesInfo.getNBXH() %>" value="<%=archivesInfo.getArchivesID() %>"/>
							   	    	<input type="hidden" id="parent<%=archivesInfo.getNBXH() %>" value="<%=archivesInfo.getParentFlag() %>"/>
							   	    	<input type="hidden" id="typeId<%=archivesInfo.getNBXH() %>" value="<%=archivesInfo.getArchivesTypeID()%>"/>
							   	    	<input type="checkbox" name="NBXHS" value="<%=archivesInfo.getNBXH() %>" onclick="oneSelect(this)"/> 
						   	    	</td>
									<td ><%=i+1%></td>
									<%
						   	    	 for(Object dataItem : dataItems.values()){
						   	    		   if(archivesInfo.getRowFieldsValues().get(((ArchivesTypeDataItem)dataItem).getColumnName()).getValue() == null){
						   	    			 %>
											   <td></td>
											 <%
						   	    		   }else{
						   	    			   %>
						   	    			   <td>
						   	    			   <%
						   	    			   if("Title".equals(((ArchivesTypeDataItem)dataItem).getColumnName())){
							   	    				if(archivesInfo.getParentFlag()== true){
							   	    				 %>
							   	    				 <img alt="案卷" style="width:20px;height:15px;" src="<%=basePath%>images/type_file.gif">
							   	    				 <%
							   	    			    }else{
							   	    					 %>							   	    				
							   	    			    	<img alt="文件" style="width:20px;height:15px;" src="<%=basePath%>images/type_doc.gif">
							   	    			    	<%
							   	    			    }
						   	    			   }						   	    			   
										    %>									    
										   <%
										   	if("ArchivesID".equals(((ArchivesTypeDataItem)dataItem).getColumnName())){
										   	%>
										   <a href="javascript:showArchivesInfo('<%=archivesInfo.getArchivesTypeID()%>','<%=archivesInfo.getNBXH() %>')">
										   		 <%=archivesInfo.getRowFieldsValues().get(((ArchivesTypeDataItem)dataItem).getColumnName()).getValue() %>
										   </a>
										   	<%
										   	}else{
										   		out.print(archivesInfo.getRowFieldsValues().get(((ArchivesTypeDataItem)dataItem).getColumnName()).getValue());
										   	}
										   %>
										  
										   </td>
										   <% }
									  }
						   	    	 %><td><a href="javascript:showArchivesInfo('<%=archivesInfo.getArchivesTypeID()%>','<%=archivesInfo.getNBXH() %>')">查看</a></td></tr><%
						   	      }
						   	      }
						   	    %>
						    </form>			
						</table>												
					<table width="100%" style="font-size: 12px;">
						 <tr>
						    <td><input type="checkbox" id="SelectAll" accesskey="S"  name="SelectAll" onclick="selectAll(this)" title="选中/取消 所有记录(S)"  /><label for="SelectAll">全选</label>
								<a href="#" onclick="showArchivesToUseList(1);return false;">借档</a>&nbsp;&nbsp;
								<a href="#" onclick="showArchivesToUseList(2);return false;">查档</a>&nbsp;&nbsp;							
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
				</td>
			</tr>
		</table>
		
		<!-- 初始页面标志  -->
		<s:if test="#request.initialPage!=true">
			<s:if test="#request.dataPageInfo.rowCount==0">
			<!-- 当不是初始页，记录数又小于0时，给出没有记录提示  -->
				<center><span  style="color: red;">没有满足查询条件的档案！</span></center>
			</s:if>	
		</s:if>		
		
		<!-- 显示卷内目录  -->
	<div id="junNeiListDiv" style="display:none; margin: 0;padding: 0;width: 100%">
		<table class="tabletop"   width="100%">
            <tr>
                <td>
                    <label id="JuanMing" class="tableTitle">卷内目录—<span id="parentTitle" ></span></label>
                </td>
                <td align="right"  class="text" >
                	<label style="margin-right:4px" id="rsInfo">共<span id="childRecordNum"></span>条记录</label>
                </td>							                	
            </tr>
		</table>
		<input type="hidden" name="parentNBXH" id="parentNBXH" >
		<form  name="wjform" method="post" id="wjform" style="margin: 0;padding: 0;">
		<input type="hidden" name="fileType" id="fileType" value="1"/>
	    <input type="hidden" id="archivesTypeId" name="archivesTypeId" value="${requestScope.archivesTypeId }"/>	
		<table id="showTable" cellpadding="0px" cellspacing="1px">				
			<thead class="tableHead">			
				<tr>
					<th style="width: 30px;">序号</th>					
					<s:iterator value="#request.dataItems">
					  <th><s:property value="value.displayText"/></th>
					</s:iterator>
					
					<th align="center" style="width: 35px">详细</th>
				</tr>
			</thead>
			<tbody id="JuanNeiListBody" >

			</tbody>
	   </table>
	   </form>
	</div>
			
		
  </body>
</html>
