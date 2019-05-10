<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="my" uri="/myTag"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("basePath",basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <sx:head/>
    
    <title>关键字检索</title>
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
	 <script type="text/javascript" src="dwr/util.js"></script>
	<script type="text/javascript" src="dwr/engine.js"></script>
	
	<script type="text/javascript">
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
				url="${basePath}js/CalendarWithFormat.html";
			}
			else {
				url="${basePath}js/CalendarWithOutFormat.html"
			}
			var retval = window.showModalDialog(url, "", "dialogWidth:197px; dialogHeight:210px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no;help=no; "  );
			if( retval != null ){
				obj.value = retval;
			}
		}

	   //单击查询事件：隐藏查询区域
		function clickHide(){		
			document.getElementById('find').style.display="none";
		
		}


		//显示高级查询:根据高级页面返回的结果来执行查询操作
		function showADFind(){
			
		  var returnValue = window.showModalDialog("/aiim/ZHCX/integratedQueryAction_getAdvanceQueryItems.action?archivesTypeId="+$("#archivesTypeId").val(),"newwindow","dialogWidth=500px;dialogHeight=280px;center=yes;scroll=no;status=no;resizable=no;help=no;location=no");
			if(returnValue == undefined){
				return false;
			}
			 if(returnValue!=""){//如果返回不为空就执行查询操作
			 $("#advanceQueryCondition").val(returnValue);
			// document.forms["conditionForm"].action="ZHCX/integratedQueryAction_advanceQueryByCondition.action";
			 document.forms["conditionForm"].action="ZHCX/integratedQueryAction_findArchivesByCondition.action";
			 document.forms["conditionForm"].submit();		
		  }
		}
		
		//打开利用清单页面
		function showBorrow(){
           window.showModalDialog("/aiim/DALY/dlg_UseList.jsp","newwindow","dialogWidth=600px;dialogHeight=480px;center=yes;scroll=no;status=no;resizable=no;help=no;location=no");
        }
        
		//借档
		dojo.addOnLoad(function(){
		  dojo.event.topic.subscribe("JD",this,function(date,type,e){
		    if(type == 'load'){
               alert(date);
               document.conditionForm.submit();
		    }else if(type == 'error'){
		       alert('发送请求失败！');
		    }else if(type == 'before'){
		       document.getElementById("type").value="1";	        
		    }
		  });
		  
		//查档
		  dojo.event.topic.subscribe("CD",this,function(date,type,e){
		    if(type == 'load'){
               alert(date);
               document.conditionForm.submit();
		    }else if(type == 'error'){
		       alert('发送请求失败！');
		    }else if(type == 'before'){
		       document.getElementById("type").value="2";	        
		    }
		  });
		  
		//查看原文
		  dojo.event.topic.subscribe("YW",this,function(date,type,e){
		    if(type == 'load'){
               alert(date);
               document.conditionForm.submit();
		    }else if(type == 'error'){
		       alert('发送请求失败！');
		    }else if(type == 'before'){
		       document.getElementById("type").value="3";	        
		    }
		  });


			
		  //dojo.byId("archivesTypeId").value=window.parent.left.selectedNode.getKey();
		  
		 /* dojo.event.connect(dojo.byId(""),'click',function(){
		      window.showModalDialog("/aiim/ZHCX/dlg_AdvanceFind.jsp","newwindow","dialogWidth=500px;dialogHeight=280px;center=yes;scroll=no;status=no;resizable=no;help=no;location=no");
		  });*/
		});	   



		//处理单击行事件
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
		var typeid = 'type'+ obj.id;	//id 格式为type+NBXH的input隐藏输入中存放<s:property value="parentFlag"/>	
		if($('#'+typeid).val()=='true'){//如果是案卷	
		//	alert('anjuan');
			$("#parentTitle").html($(obj).children("[name='Title']").text());//在卷内文件列表左上方显示案卷题名
			//	var titleid = "title"+obj.id;
			findChildArchivesInfosByNBXH(obj.id);//查询案卷卷内文件，并显示出来
		}else{//如果是文件
			//将上一次显示的案卷目录以及卷内文件记录数清空
			$("#childRecordNum").html('');//卷内文件记录数设为空
			$("#parentTitle").html('');//卷内文件左上方的题名设为空
			$("#JuanNeiListBody").html('');//清空tbody中原有的代码
		}			
		findChildArchivesInfosByNBXH(obj.id);//查询案卷卷内文件，并显示出来
		
	}


////////////////DWR //////////////////		
	//DWR：查询卷内文件
	function findChildArchivesInfosByNBXH(nbxh){
		 ArchivesInfoManageDWR.findChildArchivesInfosByNBXH($("#archivesTypeId").val(),nbxh,findChildArchivesInfosByNBXHBack);	
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
	       html = html+"<tr style=\"height:17px;\" bgcolor=\""+bgcolor+"\" id=\""+data[i].NBXH+"\" title=\"双击查看详细信息\" onclick=\"clickRowJN(this)\" ondblclick=\"showChildItem(this)\">";
	   //    html +="<td><input type=\"checkbox\" name=\"NBXHS\" id=\"childNBXHS\" onclick='childOneSelect(this)' value=\""+data[i].NBXH+"\" /></td>";
	       html +="<td>"+(i+1)+"</td>";
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
	</script>

	
  </head>
  
  <body>
    <my:pageUCL uclKey="UCLKey"></my:pageUCL>
      <s:if test="#request.errMsg != null">
	    <script>alert("${requestScope.errMsg}!")</script>
	  </s:if>
     <%--<input type="hidden" name="preSelectRow" id="preSelectRow" /> --%>    
        <table width="100%" style="margin:0px;" align="center" cellpadding="0" cellspacing="0">
		     <tr>
			  <td>   
			     <table width="100%" cellpadding="0" cellspacing="0" border="0">
			       <tr>
				       <td>
				          <my:ElementUCL uclKey="UCLKey">                          
                          </my:ElementUCL>					      
                          <input type="image" src="images/LYQD1.png" onmouseover="changeImage(this,'LYQD.png')" onmouseout="changeImage(this,'LYQD1.png')" onclick="showBorrow()"/>		 
                          <input type="image" src="images/DYDJD.gif" onmouseover="changeImage(this,'DYDJD1.gif')" onmouseout="changeImage(this,'DYDJD.gif')" onclick="showDJD()"/>
					   </td>
					   <td>
						   <div id="location" style="margin-right: 2px; width: 280px; display: inline-block; float: right; margin-top: 0px; color: blue; text-align: right;">
							  <font style="font-size: 12px;"><b>当前位置：</b>综合查询&nbsp;&gt;&gt;&nbsp;&nbsp;关键字检索</font>
						   </div>
					   </td>
			       </tr>
			     </table>
			   </td>
			</tr>
			<tr>			  
			 <td align="center" id="find" style="display:block;">			 
			     <fieldset>
			       <form action="ZHCX/integratedQueryAction_findArchivesByKeyWord.action" method="post" name="conditionForm" style="margin: 0px; padding: 0px;">
				       <input type="text" name="dataPageInfo.currentPage" id="currentPage" value="1">
				       <input type="text" name="archivesTypeId" id="archivesTypeId" value="${requestScope.archivesTypeId }">
				       <span style="font-size:13px;">关键字:</span><input type="text" style="width: 200px;margin-right: 3px;" name="keyWord" id="keyWord" value="${requestScope.keyWord }"><input type="submit" value="" class="submitButton"  onmouseover="changeSubmitBgImage(this,'search2.gif')" onmouseout="changeSubmitBgImage(this,'search.gif')"/>
			       </form>
			      </fieldset> 
			  </td>
			</tr>
			<tr>
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
									<th width="35px" >选择</th>
									<th style="width: 30px;">序号</th>
									<s:iterator value="#request.dataItems">
									  <th><s:property value="value.displayText"/></th>
									</s:iterator>									
									<th align="center" style="width: 35px">原文</th>
								</tr>
							</thead>
							<form id="tableForm" action="DALY/archivesUseAction_addArchivesToUseList.action" method="post" style="margin: 0;padding: 0;">
							<%--<input type="hidden" name="type" id="type">--%>
						     <s:iterator value="#request.archivesInfos" status="rowstatus" var="archivesInfo">
								<s:if test="#rowstatus.odd==true">
									<tr bgcolor="#eef5ff" id="<s:property value="NBXH" />"  onclick="clickRowAJ(this)" ondblclick="showItem(this)">
								</s:if>
								<s:else>
									<tr bgcolor="#e0edff"  id="<s:property value="NBXH" />" onclick="clickRowAJ(this)" ondblclick="showItem(this)">
								</s:else>																	
										<td align="center">
											<input type="checkbox" name="NBXHS" value="<s:property value="NBXH" />" onclick="oneSelect(this)">
											<input type="hidden" id="type<s:property value="NBXH" />" value="<s:property value="parentFlag"/>">
											<input type="hidden" id="title<s:property value="NBXH" />" value="<s:property value="Title"/>">
										</td>										
										<td ><s:property value="#rowstatus.index+1"/></td>
										<s:iterator value="#request.dataItems" var="dataItem">
										  <s:iterator value="#archivesInfo.rowFieldsValues" var="rowFieldsValue">
										  	  <s:if test="#dataItem.key == #rowFieldsValue.key">
										        <td name="<s:property value="#dataItem.key"/>">
										        	<%-- 在Title上加上文件/案卷图标 --%> 
										        	<s:if test="#dataItem.key=='Title'">										        		
										        		<s:if test="#archivesInfo.parentFlag==true">
										        			<img alt="案卷" style="width:20px;height:15px;" src="<%=basePath%>images/type_file.gif">
										        		</s:if>
										        		<s:else>
										        			<img alt="文件" style="width:20px;height:15px;" src="<%=basePath%>images/type_doc.gif">
										        		</s:else>
										        		<s:property value="#rowFieldsValue.value.value"/>
										        	</s:if>
										        	<s:else>
										        		<s:property value="#rowFieldsValue.value.value"/>
										        	</s:else>
													
												</td>
										      </s:if>	
										      
										    </s:iterator>
										</s:iterator>
										<td align="center"><a href="javascript:ywgl(<s:property value="NBXH" />)">查看</a></td>
									</tr>
						    </s:iterator>
						    </form>			
						</table>
					<table width="100%" style="font-size: 12px;">
						 <tr>
						    <td><input type="checkbox" id="SelectAll" accesskey="S"  name="SelectAll" onclick="selectAll(this)" title="选中/取消 所有记录(S)"  /><label for="SelectAll">全选</label>
								<sx:a formId="tableForm" notifyTopics="JD">借档</sx:a>&nbsp;&nbsp;
								<sx:a formId="tableForm" notifyTopics="CD">查档</sx:a>&nbsp;&nbsp;
								<sx:a formId="tableForm" notifyTopics="YW">查看原文</sx:a>
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
		
		<%-- 卷内文件 --%>
		<%--
		<table width="100%" style=" margin:0px;" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<input type="image" src="images/new3.gif" disabled="disabled"  onclick="showChildAdd();" id="imgChildNew"  onmouseover="changeImage(this,'new2.gif')" onmouseout="changeImage(this,'new.gif')"  />
					<input type="image" src="images/del3.gif" disabled="disabled" onclick="clickChildBatchDel()"  id="imgChildDel" onmouseover="changeImage(this,'del2.gif')" onmouseout="changeImage(this,'del.gif')"/>
					<!-- <input type="image" src="images/fenjuan3.gif" disabled="disabled" id="imgChildFenjuan" onclick="clickFenjuan()" onmouseover="changeImage(this,'fenjuan2.gif')" onmouseout="changeImage(this,'fenjuan.gif')"  />
					<input type="image" src="images/chaifen3.gif" disabled="disabled" id="imgChildChaifen" onclick="clickSeparate()" onmouseover="changeImage(this,'chaifen2.gif')" onmouseout="changeImage(this,'chaifen.gif')"  />-->
				</td>
			</tr>
		</table>
		--%>	
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
		<input type="hidden" name="parentNBXH" id="parentNBXH" >
		<form  name="wjform" method="post" id="wjform" style="margin: 0;padding: 0;">
		<input type="hidden" name="fileType" id="fileType" value="1"/>
	    <input type="hidden" id="archivesTypeId" name="archivesTypeId" value="${requestScope.archivesTypeId }"/>	
		<table id="showTable" cellpadding="0px" cellspacing="1px">				
			<thead class="tableHead">			
				<tr>
				<%--<th width="35px">选择</th>--%>
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
	   </form>
	 
	</body>
</html>
