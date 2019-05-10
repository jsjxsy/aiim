<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.orifound.aiim.entity.ArchivesInfo"%>
<%@page import="com.orifound.aiim.entity.FieldValue"%>
<%@page import="com.orifound.aiim.entity.ArchivesTypeDataItem"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Map dataItems = (Map)request.getAttribute("dataItems");
List<ArchivesInfo> archivesInfos = (List<ArchivesInfo>)request.getAttribute("archivesInfos");
//记录数据项总列数
int columnCount = 3;
if(dataItems!=null && dataItems.keySet().size()>=1) {
	columnCount = dataItems.keySet().size()+3;
}

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="js/jquery.js"></script>
    <title></title>
	<STYLE type="text/css">
		body {font-size:10px;}
		.tablePrint {border-collapse:collapse;border-width:1px 1px 1px 1px;margin:0px;font-size:10px;text-align: center;}
		.tablePrint caption {font-weight:bolder;}
		.tablePrint th,.tablePrint td {border-width:0 1px 1px 0;padding:2px;vertical-align: top;}
		
		.appraisalPersion {float: left;overflow:auto; width:498px; border:thin 1px solid;margin-left:28px;}
		.appraisalReason {float: left;overflow:auto; width:498px; height:80px; border:thin 1px solid;margin-left:12px;}
		
		.trBorder {border-width:1px 0 1px 0;}
		.trBorder td {border-width:1px 1px 1px 0;}
		
		.archivesinfoTitle{text-align: left;white-space:normal; word-break:break-all;width: 150px;}
		
		.appraisalTR{}
	</STYLE>
	
    <style media="print">
		.noprint {display:None;}
		.pageNext{page-break-after: always;}
	</style>
    
	<script type="text/javascript">
		//js  打印控制
		function printPage() {
			removeHeader();
			window.print();
		}

		//设置网页打印的页眉页脚为空
		function removeHeader() { 
			var hkey_root,hkey_path,hkey_key;
			hkey_root="HKEY_CURRENT_USER";
			hkey_path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
			try{
				var RegWsh = new ActiveXObject("WScript.Shell");
				hkey_key="header" ;
				RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"");
				hkey_key="footer";
				RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"");
				hkey_key="margin_left"; 
                RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"1");
                hkey_key="margin_top"; 
                RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"1");
                hkey_key="margin_right";
                RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"1");
                hkey_key="margin_bottom";
                RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"1");
			}
			catch(e){}
		}

		//构建档案形成部门id数组
		var departmentIDs = new Array();
		function buildFormationDepartmentIDArray() {
			<%
			if(archivesInfos != null && archivesInfos.size()>=1){
				int formationDepartmentID = 0;
				 for( ArchivesInfo archivesInfo : archivesInfos){
					 if(formationDepartmentID != archivesInfo.getFormationDepartmentID()) {
						 formationDepartmentID = archivesInfo.getFormationDepartmentID();
					%>
						departmentIDs.push(<%=formationDepartmentID%>);
					<%
					}
				}
			}
			%>
		}

		//分页打印控制
		function paginationPrint(pageHeight) {
			//记录表格目前的高度
			var tableHeight = new Number(0);
			//记录是否为不同部门
			var nextDepartment = false;
			
			$(departmentIDs).each(function(){
				tableHeight = 0;
				nextDepartment = true;
				//循环tbody 获取表格的总宽度
				$('#printPage'+this+' > tr').each(function(){
					tableHeight = tableHeight + $(this).attr('offsetHeight');
					//控制每个表格每页高度、其中第一页高度小50(去除标题高度)
					if((tableHeight >= pageHeight && !nextDepartment) || (nextDepartment && tableHeight>=(pageHeight-50))) {
						if($(this).attr('class') == 'appraisalTR') {	//如果分页为鉴定意见
							$(this).prev().prev().addClass('pageNext');
							$(this).prev().addClass('trBorder');
							
						} else if($(this).prev().attr('class') == 'appraisalTR') {	//如果分页为鉴定人
							$(this).prev().prev().prev().addClass('pageNext');
							$(this).prev().prev().addClass('trBorder');
							
						} else {	//正常tr分页
							
							//设置当列以下换页
							$(this).addClass('pageNext');
							//设置下一列加top边框
							$(this).next().addClass('trBorder');
						}
						tableHeight = 0;
						nextDepartment = false;
					}
				});
			});
		}

		//设置打印页面分页
		$(function(){
			//构建档案形成部门id数组
			buildFormationDepartmentIDArray();
			//设置每页打印高度
			var pageHeight = new Number(800);
			//分页打印设置
			paginationPrint(pageHeight);
		});
	</script>
</head>
<body>
<div class="noprint">
<input type="button" onclick="printPage()" value="打印">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" onclick="window.close();" value="关闭">
</div>
   	    <%
   	     ArchivesInfo archivesInfo = null;
   	    
   	     if(archivesInfos != null && archivesInfos.size()>=1){
   	    	 int formationDepartmentID = archivesInfos.get(0).getFormationDepartmentID();
   	    	 int serialNumber = 0;
   	    	 for(int i =0;i<archivesInfos.size();i++){
   	    		serialNumber++;
   	    	    archivesInfo  = archivesInfos.get(i);
   	    	    
   	    	    //设置 如果档案形成部门id不同  换打印页
   	    	    if(i==0 || formationDepartmentID != archivesInfo.getFormationDepartmentID()) {
   	    	    	//设置序号值
   	    	    	serialNumber = 1;
   	    	    	//重新设置形成部门id
   	    	    	formationDepartmentID = archivesInfo.getFormationDepartmentID();
   	    	    	if(i >= 1) {
						%>
				   	     <tr height="100px" class="appraisalTR">
				   	    	<td colspan="2" style="vertical-align: middle;text-align: center;">鉴定意见</td>
				   	    	<td colspan="<%=columnCount-2 %>">&nbsp;</td>
				   	    </tr>
				   	    <tr>
				   	     	<td colspan="2" align="center">鉴定人</td>
				   	    	<td colspan="<%=columnCount-5 %>">&nbsp;</td>
				   	    	<td align="center">日期</td>
				   	    	<td colspan="2">&nbsp;</td>
				   	    </tr>
						</tbody>
						</table>
	   	    	    	<div class="pageNext"></div>
						<%
					}
   	    	    	out.print("<h4 style='margin-left: 220px'>"+archivesInfo.getFormationDepartment()+"存毁鉴定清单</h4>");
   	    	    	%>
				   	<table class="tablePrint" cellspacing="1px" cellpadding="0px" border="1">
				   	  	<thead>
				   	  		<tr>
				   	  			<th width="30px">序号</th>
				   	  			<%
				   	  			for(Object dataItem : dataItems.values()){
				   	  			   out.print("<th width='"+((ArchivesTypeDataItem)dataItem).getInputEditBoxWidth()+"px'>"+((ArchivesTypeDataItem)dataItem).getDisplayText()+"</th>\n");
				   	  			}
				   	  			%>
				   	  			<th width="180px">馆藏位置</th>
				   	  			<th width="65px">鉴定结论</th>
				   	  		</tr>
				   	  	</thead>
				   	  	<tbody id="printPage<%=archivesInfo.getFormationDepartmentID()%>">
						<% 
   	    	    }
   	    	    
				out.print("<tr><td>"+serialNumber+"</td>");
   	    	    for(Object dataItem : dataItems.values()){
   	    		   if(archivesInfo.getRowFieldsValues().get(((ArchivesTypeDataItem)dataItem).getColumnName()).getValue() == null){
   	    			  out.print("<td>&nbsp;</td>");
   	    		   }else{
  	    			out.print("<td "+((ArchivesTypeDataItem)dataItem).getKeyInCol()+">"+archivesInfo.getRowFieldsValues().get(((ArchivesTypeDataItem)dataItem).getColumnName()).getValue()+"</td>");
				   }
			    }
   	    	 	out.print("<td>"+archivesInfo.getStoreAddressFullName()+"</td>");
   	    	 	out.print("<td>&nbsp;</td></tr>");
   	         }
   	      }
   	    %>
   	     <tr height="100px" class="appraisalTR">
   	    	<td colspan="2" style="vertical-align: middle;text-align: center;">鉴定意见</td>
   	    	<td colspan="<%=columnCount-2 %>">&nbsp;</td>
   	    </tr>
   	     <tr>
   	     	<td colspan="2" align="center">鉴定人</td>
   	    	<td colspan="<%=columnCount-5 %>">&nbsp;</td>
   	    	<td align="center">日期</td>
   	    	<td colspan="2">&nbsp;</td>
   	    </tr>
	   	</tbody>
</table>
</body>
</html>