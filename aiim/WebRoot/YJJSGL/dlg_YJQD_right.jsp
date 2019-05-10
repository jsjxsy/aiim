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
		<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
		
		<script type="text/javascript">
		  function remove(NBXH,archivesTypeID,transferBatNo){
			  <s:if test="#request.stateType == 1">
			     var deptType = "XCBM";
			  </s:if>
			  <s:elseif test="#request.stateType == 3">
			     var deptType = "YWZDS";
			  </s:elseif>

			  $.ajax({
				  url:"YJGL/YJJSAction_removeFromQDList.action",
		           type:"post",
		           data:"NBXH="+NBXH+"&archivesTypeID="+archivesTypeID+"&transferBatNo="+transferBatNo+"&deptType="+deptType,
		           success:function(date){
				      window.location.href = window.location.href ;
				      window.parent.returnValue = 1;
				      parent.document.getElementById("sum"+archivesTypeID).innerHTML = parent.document.getElementById("sum"+archivesTypeID).innerHTML-1;
		           },
		           error:function(XMLHttpRequest, textStatus){
		              alert(textStatus+","+XMLHttpRequest.status);
		           }
		      });
	      }

		    //打印封皮
			function envelopePrint(archivesTypeID,NBXH) {
				var url = '/aiim/PRINT/catalogPrintAction_switchCatalog.action?catalogTypeID=6&archivesTypeID=' + archivesTypeID + '&NBXH='+NBXH;
				showWinModalDialogScroll(url,'700px','500px');
			}

			//打印卷内目录
			function fileCatalog(archivesTypeID,NBXH) {
				var url = '/aiim/PRINT/catalogPrintAction_printConfig.action?catalogTypeID=3&isArchived=y&archivesTypeID=' + archivesTypeID + '&NBXH='+NBXH;
				showWinModalDialogScroll(url,'700px','500px');
			}
					  
	      parent.setSum(<s:property value="#request.paperTransferBatchesDetails.size"/>);
		</script>
	</head>
	
	<body>
	    <input type="hidden" name="preSelectRow" id="preSelectRow" />
		<table id="showTable" width="100%" cellspacing="1px" cellpadding="0px">
			<thead class="tableHead">
			    <tr>
			        <th>序号</th>
					<th>档号</th>
					<th>题名</th>
					<th>密级</th>
					<th>保管期限</th>
					<th>形成日期</th>
					<s:if test="#request.deptType == 'XCBM'">
					  <s:if test="#request.type == 'yj'">
					     <th>操作</th>
					  </s:if>
				   </s:if>
				   <s:if test="#request.deptType == 'XCBM'">
						<s:if test="#request.type == 'xx'">
						   <th>操作</th>
						</s:if>
					</s:if>
				</tr>
			</thead>
			<tbody>
			 <s:if test="#request.paperTransferBatchesDetails.size == 0">
				<tr>
				  <td style="font-size: 12px;color: red; background-color:#eef5ff; text-align: center;" colspan="7">没有数据！</td>
				</tr>	  
			 </s:if>
			 <s:iterator value="#request.paperTransferBatchesDetails" status="status">
			    <s:if test="#status.odd">
			       <s:set name="color" value="'#e0edff'" scope="page"></s:set>
			    </s:if>
			    <s:else>
			       <s:set name="color" value="'#eef5ff'" scope="page"></s:set>
			    </s:else>
				<tr bgcolor="${pageScope.color}" id="row<s:property value="NBXH"/>" onClick="selectRow(this)">
				    <td style="height: 20px" align="center">
						<s:property value="#status.index+1"/>
					</td>
					<td style="height: 20px" align="center">
						<s:property value="archivesID"/>
					</td>
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
					<s:if test="#request.deptType == 'XCBM'">
						<s:if test="#request.type == 'yj'">
						   <td align="center"><a href="javascript:remove('<s:property value="NBXH"/>','<s:property value="archivesTypeID"/>','<s:property value="transferBatNo"/>');">移除</a></td>
						</s:if>
					</s:if>
					<s:if test="#request.deptType == 'XCBM'">
						<s:if test="#request.type == 'xx'">
						   <td align="center">
						       <a href="javascript:envelopePrint('<s:property value="archivesTypeID"/>','<s:property value="NBXH"/>');">打印封皮</a>&nbsp;
						       <a href="javascript:fileCatalog('<s:property value="archivesTypeID"/>','<s:property value="NBXH"/>');">打印卷内目录</a>
						   </td>
						</s:if>
					</s:if>
				</tr>
			 </s:iterator>
            </tbody>
		</table>
	</body>
</html>



