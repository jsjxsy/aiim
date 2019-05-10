<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>" target="_self">
    
    <title>原文管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/common.css">
	
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="dwr/interface/officialArchivesAttachedFileManageAction.js"></script>
    <script type="text/javascript" src="dwr/util.js"></script>
    <script type="text/javascript" src="dwr/engine.js"></script>
	<script type="text/javascript">
	if (typeof window['DWRUtil'] == 'undefined')
	{
	 window.DWRUtil = dwr.util;
	}


	//查找原文
    function findFiles(){
    	officialArchivesAttachedFileManageAction.findAllAttachedFile($("NBXH").value,$("officialArchivesTypeID").value,findAllAttachedFileCallBack);
    }
    function findAllAttachedFileCallBack(data){
    	var i=1;
    	DWRUtil.removeAllRows('files');
    	DWRUtil.addRows("files",data,
    	[ function(item){ return i++;},
    	  function(item){ return item.title;},	  
    	  function(item){ return item.oriFileName;},
    	  function(item){ return item.originalType;},
    	  function(item){ return item.originalSize;} ,
    	  function(item){ return item.attachedTime.toLocaleDateString() ;} ,
    	  function(item){ 
    		   var hlink = "<a href=\"javascript:downLoad('"+item.ID+"')\">下载</a>"+"  "+
			    ""
    			return hlink;
    	   }   
    	]
    	,
    	{escapeHtml:false,
    	 rowCreator:function(options) { 
    		var row = document.createElement("tr"); 
    		if(options.rowIndex%2==0){
    			row.style.backgroundColor="#eef5ff";
    		}else{
    			row.style.backgroundColor="#e0edff";
    		}
    		row.style.height="20px";
    		//row.align="center";
    		row.id=data[options.rowIndex].ID;
    		//row.onclick=new Function("selectRow(this)");   
    		return row;},
   		cellCreator:function(options) { 
	   			var cell = document.createElement("td"); 
	   			if(options.cellNum == 6) { 
	   			    cell.style.textAlign="center";
	   			} 
	   			return cell; 
   			}
    	});
    }

  //删除原文
    function deleteFile(fileID){
    	officialArchivesAttachedFileManageAction.delAttachedFileByNBXH(fileID,$("NBXH").value,$("officialArchivesTypeID").value,delAttachedFileCallBack);
    }
    function delAttachedFileCallBack(data){
    	window.setTimeout("findFiles()",500);
    }

    //下载原文
	function downLoad(fileID){
		window.location.href="/aiim/GWGL/downloadAttachedFile.action?fileID="+fileID+"&NBXH="+$("NBXH").value+"&archivesTypeID="+$("archivesTypeID").value;
    }

  
	</script>
</head>
<body onload="findFiles()">
    <input type="hidden" name="preSelectRow" id="preSelectRow" />
	<table width="100%" style="margin:0px; font-size: 12px;" align="center" >
	   <tr>
	     <td style="color: red; text-align: center;"><s:fielderror></s:fielderror></td>
	   </tr>
		<tr>
			<td align="center" height="25px">
	      
			    
				<table class="tabletop" width="100%">
					<tr>
		                <td class="tableTitle">原文</td>
		                <td align="right" class="text">共12条记录</td>							                	
		            </tr>
				</table>		
				<table id="showTable" width="100%" style="margin:0px; border: #104da6 1px solid;" cellspacing="1px" cellpadding="0px" >						
				<input type="hidden" name="officialArchivesTypeID" id="officialArchivesTypeID" value="<%=request.getParameter("officialArchivesTypeID")%>"/>
	              <input type="hidden" name="NBXH" id="NBXH" value="<%=request.getParameter("NBXH")%>"/>
					<thead class="tableHead">
						<tr>
							<th>序号</th>
							<th>标题</th>								
							<th>文件名</th>
							<th>文件类型</th>
							<th>文件大小</th>
							<th>附加原文日期</th>
							<th align="center">下载</th>
						</tr>
					</thead>
					<tbody id="files">
					</tbody>
				</table>
			</td>
		</tr>
	</table>
	${message}
</body>
</html>
 