<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
	    <base href="<%=basePath%>"/>
		<link rel="stylesheet" type="text/css" href="css/common.css" />
		<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
function showJN(NBXH,archivesTypeID){			
   var returnValue = window.showModalDialog("YJGL/YJJSAction_findFilesByArchives.action?archivesTypeID="+archivesTypeID+"&NBXH="+NBXH,"newwindow","dialogWidth=800px;dialogHeight=350px;center=yes;help=no;resizable=no;status=no;scroll=no;");
}	

//单击审核事件
function showSH(NBXH,archivesTypeID,parentFlag){	
	
	var obj = new Object();	
	   
    var NBXHS = new Array(NBXH);
     
	obj.archivesTypeID = archivesTypeID;
	obj.operationType="edit";
	obj.fileType = 1;//文件级管理 0为文件 1为案卷 2为添加卷内
	obj.NBXHS = NBXHS;
	obj.batNo=${batNo};
	obj.deptType=document.getElementById("deptType").value;

	var returnValue = window.showModalDialog(				
		"<%=basePath%>YJJSGL/item_SH.jsp",
		obj,
		"dialogWidth=700px;dialogHeight:500px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;resizable=no;");
	if(returnValue==1){
		//window.location.reload();
		window.top.qdRight.location.href=window.top.qdRight.location.href;
	}
}

//获取当前页面中所有档案的NBXH
function getAllNBXHs(){	
	var pageNBXHs =new Array();  
	var elements = document.getElementsByTagName("input");
	for(i =0;i<elements.length;i++){
		if(elements[i].name=='NBXHs'){			
			pageNBXHs.push(elements[i].value);
		}
	}
	return pageNBXHs;
}

parent.setSum('<s:property value="#request.paperTransferBatchesDetails.size"/>','<%=request.getAttribute("archivesTypeID")%>');
  	  
</script>
	</head>
	
	<body>
	    <input type="hidden" name="preSelectRow" id="preSelectRow" />
	    <input type="hidden" name="deptType" id="deptType" value="${requestScope.deptType }"/>
		<table id="showTable" width="100%" cellspacing="1px" cellpadding="0px">
			<thead class="tableHead">
			  <tr>
				<th>序号</th>
				<th>档号</th>
				<th>题名</th>
				<th>密级</th>
				<th>保管期限</th>
				<th>形成日期</th>
				<th>审核状态</th>
				<th>审核</th>
			  </tr>
			</thead>
			<tbody>
			 <s:if test="#request.paperTransferBatchesDetails.size == 0">
				<tr>
				  <td style="font-size: 12px;color: red; background-color:#eef5ff; text-align: center;" colspan="7">没有数据！</td>
				</tr>	  
			 </s:if>
			 <s:iterator value="#request.paperTransferBatchesDetails" status="status">
			    <s:if test="(#status.index+1)%2==0">
			       <s:set name="color" value="'#e0edff'" scope="page"></s:set>
			    </s:if>
			    <s:else>
			       <s:set name="color" value="'#eef5ff'" scope="page"></s:set>
			    </s:else>
				<tr bgcolor="${pageScope.color}" id="row<s:property value="NBXH"/>" onClick="selectRow(this)">
					<td style="height: 20px" align="center">
						<s:property value="#status.index+1"/>
					</td>
					<td><s:property value="archivesID"/></td>
					<td>
					    <s:if test="parentFlag == true">
					      <img style="width: 23px; height: 17px;" src="images/type_file.gif" title="案卷"/>
					    </s:if>
						<s:else>
						  <img style="width: 23px; height: 17px;" src="images/type_doc.gif" title="文件"/>
						</s:else>
						<s:property value="title"/>
					</td>
					<td><s:property value="secrecyText"/></td>
					<td><s:property value="retentionPeriodText"/></td>
					<td><s:property value="formationYear"/></td>
					<td style="color: red;"><s:property value="receiveCheckResult" /> </td>
					<td>
						<input type="hidden" value="<s:property value="NBXH"/>" name="NBXHs">
						<s:if test="#request.deptType == 'YWZDS'">
							<s:if test="archivesID == '' ">
							   <a href="javascript:showSH('<s:property value="NBXH"/>','<s:property value="archivesTypeID"/>','<s:property value="parentFlag"/>')">审核</a>
							</s:if>
							<s:elseif test="archivesID == null">
							   <a href="javascript:showSH('<s:property value="NBXH"/>','<s:property value="archivesTypeID"/>','<s:property value="parentFlag"/>')">审核</a>
							</s:elseif>
							<s:else>
							   <label style="color: silver;">审核</label>
							</s:else>
						</s:if>
						<s:elseif test="#request.deptType == 'DAGLS'">
						    <a href="javascript:showSH('<s:property value="NBXH"/>','<s:property value="archivesTypeID"/>','<s:property value="parentFlag"/>')">审核</a>
						</s:elseif>
					 	<%--<s:if test="parentFlag == true">					 		
					      <a href="javascript:showJN('<s:property value="NBXH"/>','<s:property value="archivesTypeID"/>')">卷内</a>
					    </s:if>					   
					--%></td>
				</tr>
			 </s:iterator>
            </tbody>
		</table>
	</body>
</html>



