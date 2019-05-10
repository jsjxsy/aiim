<%@	page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="s" uri="/struts-tags"%>
<%@	page import="com.orifound.aiim.entity.ArchivesTypeDataItem"%>
<%@	page import="com.orifound.aiim.entity.OfficialArchivesInfo"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
Map dataItems = (Map)request.getAttribute("dataItems");
List officialArchivesInfos = (List)request.getAttribute("officialArchivesInfos");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>公文登记</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="css/common.css" />
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="js/jquery.form.js"></script>
		<script type="text/javascript">
		 
		 //打开新增对话框
		function showAdd() {
		    var obj = new Object();	
		    var NBXHS = new Array();
		    obj.NBXHS = NBXHS;
			obj.officialArchivesTypeID = $("#officialArchivesTypeID").val();
			obj.operationType="add";
	
			var returnValue = window.showModalDialog(				
				"<%=basePath%>GWGL/dispatchDocItem.jsp",
				obj,
				"dialogWidth=700px;dialogHeight:500px;top=0;left=0;status:YES; toolbar=YES;menubar=YES;scroll:0;resizable=YES;");
			if(returnValue==1){
				document.conditionForm.submit();
				}
 
		}
		//处理单击事件
		 var rowId="";   //保存上一次点击行“tr”的ID；
		 var rowColor="";   //保存上一次点击行的颜色
		 //保存上一次点击行的颜色
		function clickRow(obj) {
			if (document.getElementById(rowId) == null) {//第一次点击处理
				rowId = obj.id; //保存被点击行的ID
				rowColor = obj.style.backgroundColor;//保存被点击行的颜色
				obj.style.backgroundColor = '#a3c9ff';

			} else {
				document.getElementById(rowId).style.backgroundColor = rowColor;
				obj.style.backgroundColor = '#a3c9ff';
				rowId = obj.id;
			}
		}
			
<%--        //打开编辑对话框--%>
<%--		function showItem(rowObj)--%>
<%--		{--%>
<%--			alert(rowObj);--%>
<%--			    var obj = new Object();	--%>
<%--		   --%>
<%--		    var NBXHS = new Array(rowObj.id);--%>
<%--		     --%>
<%--			obj.officialArchivesTypeID = $("#officialArchivesTypeID").val();--%>
<%--			obj.operationType="edit";--%>
<%--			//obj.fileType = 0;//文件级管理 0为文件 1为案卷 2为添加卷内--%>
<%--			obj.NBXHS = NBXHS;--%>
<%--			--%>
<%--			var returnValue = window.showModalDialog(				--%>
<%--				"<%=basePath%>GWGL/dispatchDocItem.jsp",--%>
<%--				obj,--%>
<%--				"dialogWidth=700px;dialogHeight:500px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;resizable=no;");--%>
<%--			if(returnValue == true){--%>
<%--			   document.conditionForm.submit();--%>
<%--			}--%>
<%--		}--%>

        //打开编辑对话框
		function showItem(NBXH)
		{
		    var obj = new Object();	
		   
		    var NBXHS = new Array();
		     
			obj.officialArchivesTypeID = $("#officialArchivesTypeID").val();
			obj.operationType="edit";
			//obj.fileType = 0;//文件级管理 0为文件 1为案卷 2为添加卷内
			obj.NBXHS = NBXH;
			alert(NBXH)
			var returnValue = window.showModalDialog(				
				"<%=basePath%>GWGL/dispatchDocItem.jsp",
				obj,
				"dialogWidth=700px;dialogHeight:500px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;resizable=no;");
			if(returnValue == true){
			   document.conditionForm.submit();
			}
		}
		
		//删除
		$(document).ready(function(){
			    $("#del").bind("click",function(){
			       $('#delForm').ajaxSubmit({
			        beforeSubmit:function(){
			          if($("input:checked[name='NBXHs']").length <= 0){
			             alert("请选择要删除的公文！");
			             return false;
			          }else{
			             if(confirm("确认删除公文？数量"+$("input:checked[name='NBXHs']").length)){
			                return true;
			             }else{
			                return false;
			             }
			          }
			        }, 
			        success:function(data){
			           alert(data);
			           document.conditionForm.submit();
			        },
			        error:function(XMLHttpRequest, textStatus){alert("删除失败！ "+textStatus+","+XMLHttpRequest.status);}
			    });
		    });
		 });

		//单击check框事件，控制按钮的可用/不可用状态
		function oneSelect(obj) {
			var elements=document.getElementsByTagName("input");
			var SelAll=document.getElementById("SelectAll");
			var iCount=0;//总数
			var iCheck=0;//选 中总数
			var objDel=document.getElementById("imgDel");//删除
			for (i=0; i<elements.length; i++) {
				if (elements[i].type=="checkbox" && elements[i].id != "SelectAll") {
					iCount++;
					if (elements[i].checked==true) {
						iCheck++;
					}
				}
			}
			if (iCount==iCheck && iCount>0) {//设置全选 状态
				SelAll.checked=true;
			}
			else {
				SelAll.checked=false;
			}
			objDel.disabled=iCheck==0;
			changePic();
		}
	
	//获取所有公文的NBXH,返回值为一个Array，用于子页面的上一条、下一条操作
	function getAllCheckBoxNBXH(){
		var pageNBXHs =new Array(); 
		var elements=document.getElementsByTagName("input");
		for (i=0; i<elements.length; i++) {
			if (elements[i].type=="checkbox" && elements[i].name == 'NBXHS') {
				pageNBXHs.push(elements[i].value);
			}
		}	
		return pageNBXHs;
	}	
	//全选时,控制按钮的可用/不可用状态控制
	function allSelect(obj) {
			//var selValue=document.getElementById("dir");
			var elements=document.getElementsByTagName("input");
			var iCount=0;
			var objDel=document.getElementById("imgDel");//删除
			
		//	var objAdd=document.getElementById("imgAdd");
			var objDel=document.getElementById("imgDel");			
			//selValue.value="";
			for (i=0; i<elements.length; i++) {
				if (elements[i].type=="checkbox" && elements[i].id != obj.id) {
					iCount++;
					elements[i].checked = obj.checked;
					if (obj.checked==true) {
					//	selValue.value+=",'"+getValue(elements[i].id)+"'";
						objDel.disabled=iCount==0;
					}
					else {
						objDel.disabled=true;
					}
				}
			}
			changePic();
		}
	//通过检查按钮的disable属性改变图片颜色
	function changePic() {		
		var objDel=document.getElementById("imgDel");//删除

		if (objDel.disabled==true) {
			objDel.src="images/del3.gif";
		}
		else {
			objDel.src="images/del.gif";
		}
	}
		
	function getAllCheckedNBXH(){
		var NBXHS =new Array(); 
		var elements=document.getElementsByTagName("input");
		for (i=0; i<elements.length; i++) {
			if (elements[i].type=="checkbox" && elements[i].name == 'NBXHS' && elements[i].checked==true) {
				NBXHS.push(elements[i].value);
			}
		  }	
		  return NBXHS;
    }
	
	//打开原文管理页面
	function ywgl(nbxh) {
		var officialArchivesTypeID = $("#officialArchivesTypeID").val();
		window.showModalDialog(
						"<%=basePath%>GWGL/ywgl.jsp?officialArchivesTypeID="+officialArchivesTypeID+"&NBXH="+nbxh,
						window,
						"dialogWidth:590px; dialogHeight:430px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");
	}

	/*翻页函数
	url 请求的url地址
	formName 查询条件的form
	currentPage 当前页
	 */
	function pageTurning(formName, currentPage) {
		document.getElementById("currentPage").value = currentPage;
		document.forms[formName].submit();
	}
	/*
	跳到某一页
	 */
	function gotoPage(formName) {
		var gotoPage = document.getElementById("gotoPage").value;
		pageTurning(formName, gotoPage);
	}

	//单击批量删除
	function clickBatchDel() {
		if (confirm("确认删除？")) {
			$("#djform").ajaxSubmit( {
				url : "GWGL/deleteOfficialArchivesInfos.action",
				success : function(data) {
					alert(data);
					document.conditionForm.submit();
				},
				error : function(XMLHttpRequest, textStatus) {
					alert(textStatus + "," + XMLHttpRequest.status);
				}
			});
		}
	}


	//对发文进行归档
	function archiving(nbxh){
		var officialArchivesTypeID = $("#officialArchivesTypeID").val();
		var returnValue = window.showModalDialog(
				"<%=basePath%>GWGL/archivingOfficialArchivesInfos.action?officialArchivesTypeID="+officialArchivesTypeID+"&NBXH="+nbxh,
		        "归档",
		        "dialogWidth=300px;dialogHeight:110px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");
		if(returnValue > 0){
			document.conditionForm.submit();
			}
        }

    //打印
    function print(){
    	var officialArchivesTypeID = $("#officialArchivesTypeID").val();
    	window.showModalDialog(
				"<%=basePath%>PRINT/catalogPrintAction_printConfig.action?catalogTypeID=4&officialArchivesTypeID="+officialArchivesTypeID,
		        "打印",
		        "dialogWidth=600px;dialogHeight:400px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:1;location:yes; resizable=no;location=n");
        }
</script>
	</head>

	<body>
		<input type="hidden" name="preSelectRow" id="preSelectRow" />
		<table width="100%" style="margin: 0px;" align="center">
			<tr>
				<td>
					<input type="image" id="imgAdd" src="images/new.gif"
						onclick="showAdd();" onmouseover="changeImage(this,'new2.gif')"
						onmouseout="changeImage(this,'new.gif')" />
					<input type="image" id="imgDel" src="images/del3.gif"
						onmouseover="changeImage(this,'del2.gif')"
						onmouseout="changeImage(this,'del.gif')" disabled="disabled"
						onclick="clickBatchDel()" />
					<input type="image" src="images/find.gif"
						onmouseover="changeImage(this,'find2.gif')"
						onmouseout="changeImage(this,'find.gif')" onclick="showfind(this)"
						alt="显示查询(Q)" />
					<input type="image" src="images/printing.gif"
						onmouseover="changeImage(this,'printing2.gif')"
						onmouseout="changeImage(this,'printing.gif')" onclick="print();" />
				</td>
				<td>
					<div
						style="margin-right: 2px; width: 200px; float: right; color: blue; font-size: 12px;">
						<font style="font-weight: bold;">当前位置：</font>公文管理&nbsp;>>&nbsp;公文登记
					</div>
				</td>
			</tr>
		</table>


		<form action="GWGL/findOfficialArchivesInfosByCondition.action"
			method="post" name="conditionForm" style="margin: 0px; padding: 0px;">

			<fieldset id="find" style="display: none;">
				<table class="findTB" style="font-size: 12px; display: block;"
					align="center">
					<input type="hidden" name="type" value="DJ">
					<input type="hidden" name="dataPageInfo.currentPage"
						id="currentPage" value="1">
					<!-- 当前页码 -->
					<input type="hidden" id="FormationDepartmentID"
						name="FormationDepartmentID"
						value="${requestScope.FormationDepartmentID}">
					<input type="hidden" id="officialArchivesTypeID"
						name="officialArchivesTypeID"
						value="${requestScope.officialArchivesTypeID }">
					${requestScope.htmlCode }
					<tr style="height: 40px;">
						<td></td>
						<td align="left">
							<input type="submit" value="" class="submitButton"
								onmouseover="changeSubmitBgImage(this,'search2.gif')"
								onmouseout="changeSubmitBgImage(this,'search.gif')"
								onclick="javascript:document.getElementById('findDiv').style.display='none';" />
						</td>
					</tr>
				</table>
			</fieldset>
		</form>
			<table class="tabletop" width="100%">
						<tr>
			                <td class="tableTitle" id="tableTitle">收文登记&nbsp;&nbsp;<label id="archivesTypeNameText"></label></td>
			                <td align="right" class="text">第${dataPageInfo.currentPage}页 共${dataPageInfo.pageCount}页 共${dataPageInfo.rowCount}条记录</td>							                	
			            </tr>
		</table>
		
	<form  id="djform" name="djform" method="post" id="wjform" style="margin: 0;padding: 0;">
		<input type="hidden" id="officialArchivesTypeID"
						name="officialArchivesTypeID"
						value="${requestScope.officialArchivesTypeID }">
		<table id="showTable" cellpadding="0px" cellspacing="1px">
			<thead class="tableHead">
				<tr>
					<th width="35px">
						选择
					</th>
					<th style="width: 30px;">
						序号
					</th>
					<%
						for(Object dataItem : dataItems.values()){
						   out.print("<th>"+((ArchivesTypeDataItem)dataItem).getDisplayText() +"</th>");
						}
						%>
					<th align="center" style="width: 70px">
						原文
					</th>
					<th align="center" style="width: 70px">
						操作
					</th>
				</tr>
			</thead>
			<%
			OfficialArchivesInfo officialArchivesInfo = null;
	   	     if(officialArchivesInfos != null){
	   	        for(int i =0;i<officialArchivesInfos.size();i++){
	   	    	    officialArchivesInfo  = (OfficialArchivesInfo)officialArchivesInfos.get(i);
	   	    	
	   	    	    if(i%2 == 0){    		
	   	    		    out.println("<tr bgcolor=\"#eef5ff\" id=\""+officialArchivesInfo.getNBXH()+"\"  title=\"双击查看详细信息\"   >");   	    		
	   	    	    }else{
	   	    		    out.println("<tr bgcolor=\"#e0edff\"  id=\""+officialArchivesInfo.getNBXH()+"\" title=\"双击查看详细信息\"  >");
	   	    	    }

	   	    	    out.println("<td align=\"center\"><input type=\"checkbox\" name=\"NBXHS\" value=\""+officialArchivesInfo.getNBXH() +"\" onclick=\"oneSelect(this)\"></td>");
	   	    	    out.println("<td >"+(i+1)+"</td>");
			
	   	    	    for(Object dataItem : dataItems.values()){
	   	    		   if(officialArchivesInfo.getRowFieldsValues().get(((ArchivesTypeDataItem)dataItem).getColumnName()).getValue() == null){
	   	    			  out.println("<td></td>");
	   	    		   }else{
		   	    			if(((ArchivesTypeDataItem)dataItem).getColumnName().equals("DocNo")){
		   	    			out.println("<td><a href=\"javascript:showItem("+officialArchivesInfo.getNBXH()+")\">"+officialArchivesInfo.getRowFieldsValues().get("DocNo").getValue()+"</a></td>");
		   	    		   }else{
		   	    			out.println("<td>"+officialArchivesInfo.getRowFieldsValues().get(((ArchivesTypeDataItem)dataItem).getColumnName()).getValue() +"</td>"); 
		   	    		    }
					   }
				    }
	   	    	   out.print("<td><a href=\"javascript:ywgl("+officialArchivesInfo.getNBXH()+")\">查看</a></td>");
		   	    	if(officialArchivesInfo.getSavedFlag()){
		   	    	    out.print("<td>已归档</td></tr>");
		   	    	   }else{
		   	    		 out.print("<td><a href=\"javascript:archiving("+officialArchivesInfo.getNBXH()+")\"/>归档</a></td></tr>"); 
		   	    	   }
		   	       
	   	         }
	   	      }
	   	    %>
		</table>
		</form>

		<%--		<table id="showTable" cellpadding="0px" cellspacing="1px">--%>
		<%--				<thead class="tableHead">--%>
		<%--					<tr>--%>
		<%--						<th width="35px">--%>
		<%--							选择--%>
		<%--						</th>--%>
		<%--						<th style="width: 30px;">--%>
		<%--							序号--%>
		<%--						</th>--%>
		<%--						<s:iterator value="#request.dataItems">--%>
		<%--							<th>--%>
		<%--								<s:property value="value.displayText" />--%>
		<%--							</th>--%>
		<%--						</s:iterator>--%>
		<%--						<th align="center" style="width: 70px">原文</th>--%>
		<%--						<th align="center" style="width: 70px">操作</th>--%>
		<%--					</tr>--%>
		<%--				</thead>--%>
		<%--				<s:iterator value="#request.officialArchivesInfos" status="rowstatus"--%>
		<%--					var="officialArchivesInfo">--%>
		<%--					<s:if test="#rowstatus.odd==true">--%>
		<%--						<tr bgcolor="#eef5ff" id="<s:property value="NBXH" />"--%>
		<%--							onclick="clickRow(this)" ondblclick="showItem(this)">--%>
		<%--					</s:if>--%>
		<%--					<s:else>--%>
		<%--						<tr bgcolor="#e0edff" id="<s:property value="NBXH" />"--%>
		<%--							onclick="clickRow(this)" ondblclick="showItem(this)">--%>
		<%--					</s:else>--%>
		<%--					<td align="center">--%>
		<%--						<input type="checkbox" name="NBXHS"--%>
		<%--							value="<s:property value="NBXH" />" onclick="oneSelect(this)">--%>
		<%--					</td>--%>
		<%--					<td>--%>
		<%--						<s:property value="#rowstatus.index+1" />--%>
		<%--					</td>--%>
		<%--					<s:iterator value="#request.dataItems" var="dataItem">--%>
		<%--						<s:iterator value="#officialArchivesInfo.rowFieldsValues"--%>
		<%--							var="rowFieldsValue">--%>
		<%--							<s:if test="#dataItem.key == #rowFieldsValue.key">--%>
		<%--								<td name="<s:property value="#dataItem.key"/>">--%>
		<%--									<s:property value="#rowFieldsValue.value.value" />--%>
		<%--								</td>--%>
		<%--							</s:if>--%>
		<%--						</s:iterator>--%>
		<%--					</s:iterator>--%>
		<%--					<td><a href="javascript:ywgl(<s:property value="NBXH" />)">查看</a></td>--%>
		<%--					<s:if test="SavedFlag" >--%>
		<%--					 <td>归档</td>--%>
		<%--					</s:if>--%>
		<%--					<s:else>--%>
		<%--					   <td><a href="javascript:archiving(<s:property value="NBXH" />)">归档</a></td>--%>
		<%--					</s:else>--%>
		<%--					</tr>--%>
		<%--				</s:iterator>--%>
		<%--			</table>--%>
		<%--		</form>--%>

		<table width="100%" style="font-size: 12px;">
			<tr>
				<td>
					&nbsp;&nbsp;&nbsp;
					<input type="checkbox" onclick="selectAll(this)" id="SelectAll"
						accesskey="S" name="SelectAll" title="选中/取消 所有记录(S)" />
					全选
				</td>
				<td align="right" style="width: 100px; vertical-align: bottom;">
					<s:if test="#request.dataPageInfo.previousState=='enable'">
						<a href="javascript:pageTurning('conditionForm','1')"
							style="text-decoration: none;"> <image
								src="images/firsts.gif"
								onmouseover="changeImage(this,'firsts1.gif')"
								onmouseout="changeImage(this,'firsts.gif')" alt="第一页" /> </a>
						<a
							href="javascript:pageTurning('conditionForm',${dataPageInfo.currentPage-1})"
							style="text-decoration: none;"> <image
								src="images/previouss.gif"
								onmouseover="changeImage(this,'previouss1.gif')"
								onmouseout="changeImage(this,'previouss.gif')" alt="上一页" /> </a>
					</s:if>
					<s:elseif test="#request.dataPageInfo.previousState =='disable'">
						<image src="images/firsts2.gif" alt="已经是第一页" />
						<image src="images/previouss2.gif" alt="已经是上一页" />
					</s:elseif>
					<s:if test="#request.dataPageInfo.nextState=='enable'">
						<a
							href="javascript:pageTurning('conditionForm',${dataPageInfo.currentPage+1})"
							style="text-decoration: none;"> <image src="images/nexts.gif"
								onmouseover="changeImage(this,'nexts1.gif')"
								onmouseout="changeImage(this,'nexts.gif')" alt="下一页" /> </a>
						<a
							href="javascript:pageTurning('conditionForm',${dataPageInfo.pageCount})"
							style="text-decoration: none;"> <image src="images/lasts.gif"
								onmouseover="changeImage(this,'lasts1.gif')"
								onmouseout="changeImage(this,'lasts.gif')" alt="最后一页" /> </a>
					</s:if>
					<s:if test="#request.dataPageInfo.nextState=='disable'">
						<image src="images/nexts2.gif" alt="已经是最后一页" />
						<image src="images/lasts2.gif" alt="已经是最后一页" />
					</s:if>
				</td>
				<td style="width: 80px; font-size: 12px;">
					转到第
					<input type="text" name="gotoPage"
						style="width: 18px; height: 18px" />
					页
				</td>
				<td style="width: 15px; vertical-align: bottom;">
					<input type="image" src="images/gos.gif"
						onmouseover="changeImage(this,'gos2.gif')"
						onmouseout="changeImage(this,'gos.gif')"
						onclick="gotoPage('conditionForm')" />
				</td>
			</tr>
		</table>
	</body>
</html>
