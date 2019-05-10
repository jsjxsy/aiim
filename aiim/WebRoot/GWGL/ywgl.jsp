<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.orifound.aiim.entity.UserInfo"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
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
	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.form.js"></script>
	<script type="text/javascript">
	  var j = jQuery.noConflict();
	</script>
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
    	 $("filesSum").innerHTML=data.length;
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
			    "<a href=\"javascript:deleteFile('"+item.ID+"')\">删除</a>"
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
    var fileSign;
    var serviceURL;
    j(function(){
        j("#upLoadForm").ajaxForm({
            beforeSubmit:function(){},
            success:function(data){
                alert(data);
                findFiles();
            },
            error:function(XMLHttpRequest, textStatus){alert(textStatus+","+XMLHttpRequest.status);}
        });

        getServiceURL();
        fileSign = document.getElementById('signApplet');
    });

    //获得文件服务器地址
    function getServiceURL(){
    	officialArchivesAttachedFileManageAction.getServiceURLDWR(getServiceURLCallback);
    }
    function getServiceURLCallback(data){
    	serviceURL = data;
    }
    
  //删除原文
    function deleteFile(fileID){
    	officialArchivesAttachedFileManageAction.delAttachedFileByNBXHDWR(fileID,$("NBXH").value,$("officialArchivesTypeID").value,delAttachedFileCallBack);
    }
    function delAttachedFileCallBack(data){
    	window.setTimeout("findFiles()",500);
    }

    //下载原文
	function downLoad(fileID){
		officialArchivesAttachedFileManageAction.downLoadAttachedFileByNBXHDWR(fileID,$("NBXH").value,$("officialArchivesTypeID").value,downLoadCallBack);
    }
    
	  function downLoadCallBack(data){
	    	window.location.href = data;
	    }
    function getTitle(obj){
        var path = obj.value;
        $("title").value = path.substring(path.lastIndexOf("\\")+1,path.lastIndexOf("."));
    }
    
    function submitForm(){
    	getSavedPath(j("#officialArchivesTypeID").val());
    }

    function getPath(obj) {
	    if (obj) {
		    if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
		    obj.select(); return document.selection.createRange().text;
	    }else if (window.navigator.userAgent.indexOf("Firefox") >= 1) {
		    if (obj.files) {
		    return obj.files.item(0).getAsDataURL();
		    }
   		 return obj.value;
   	 	}
   		 return obj.value;
   		 }
    }  
    
  	//获得上传后文件的路径
    function getSavedPath(officialArchivesTypeID){
        var nbxh = j("#NBXH").val();
       // var oldPath = j("#filePath").val();
        var oldPath = getPath(document.getElementById("filePath"))
        var deptName = '<%=userInfo.getDepartmentName()%>'; 
        j.ajax({
            url:"GWGL/officialArchivesAttachedFileManageAction_getSavedPath.action",
            data:"officialArchivesTypeID="+officialArchivesTypeID,
            type:"post",    
        	beforeSubmit:function(){},
	        success:function(data){
				//文件上传保存路径(以"\"结尾)
				var returnValue = fileSign.signFile(nbxh, oldPath, data, deptName, serviceURL);
        		var attachedFile = eval('('+returnValue+')');
        		if(attachedFile.success != "success"){
        			alert(attachedFile.errorMessage);
                }else{
                	j("#archivingFileName").val(attachedFile.archivingFileName);
                	j("#oriFileName").val(attachedFile.oriFileName);
                	j("#originalSize").val(attachedFile.originalSize);
                	j("#originalType").val(attachedFile.originalType);
                	j("#upLoadForm").submit();
                }
		    },
	        error:function(XMLHttpRequest, textStatus){alert(textStatus+","+XMLHttpRequest.status);}
        });
    }
	</script>
</head>
<body onload="findFiles()">

 <object id="signApplet"
	    classid = "clsid:8AD9C840-044E-11D1-B3E9-00805F499D93"
	    codebase = "<%=basePath%>appletSign/jinstall-6u10-windows-i586.cab#Version=6,0,0,32"
	    WIDTH = "0" HEIGHT = "0" >
	    <PARAM NAME = CODE VALUE = "com.orifound.aiim.util.FileSignerApplet" >
	    <PARAM NAME = ARCHIVE VALUE = "<%=basePath%>appletSign/appletSign.jar" >
	    <param name = "type" value = "application/x-java-applet;version=1.6">
	    <param name = "scriptable" value = "false">
	
	    <comment>
		<embed
	            type = "application/x-java-applet;version=1.6" 
	            CODE = "com.orifound.aiim.util.FileSignerApplet" 
	            ARCHIVE = "appletSign.jar" 
	            WIDTH = "0" 
	            HEIGHT = "0"
		        scriptable = false
		        pluginspage = "http://java.sun.com/products/plugin/index.html#download">
		    <noembed>
	        </noembed>
		</embed>
	    </comment>
	</object>
     
    <div style="border-color: blue; display: none;" id="div11">
      <div id="divAddKC">上传中，请稍侯。。。</div>
    </div>
    <input type="hidden" name="preSelectRow" id="preSelectRow" />
	<table width="100%" style="margin:0px; font-size: 12px;" align="center" >
	   <tr>
	     <td style="color: red; text-align: center;"><s:fielderror></s:fielderror></td>
	   </tr>
		<tr>
			<td align="center" height="25px">
	           <form id="upLoadForm" name="upLoadForm" action="GWGL/officialArchivesAttachedFileManageAction_saveOfficialArchivesInfoAttachedFile.action" method="POST" enctype="multipart/form-data" style="text-align: center;"> 
	              <input type="hidden" name="officialArchivesType.ID" id="officialArchivesTypeID" value="<%=request.getParameter("officialArchivesTypeID")%>"/>
	              <input type="hidden" name="officialArchivesInfoAttachedFile.NBXH" id="NBXH" value="<%=request.getParameter("NBXH")%>"/>
	                 <input type="hidden" name="officialArchivesInfoAttachedFile.archivingFileName " id="archivingFileName"/><!-- 归档文件名 -->
	              <input type="hidden" name="officialArchivesInfoAttachedFile.oriFileName" id="oriFileName"/><!-- 原始文件名 -->
	              <input type="hidden" name="officialArchivesInfoAttachedFile.originalSize" id="originalSize"/><!-- 原始文件大小 -->
	              <input type="hidden" name="officialArchivesInfoAttachedFile.originalType" id="originalType"/><!-- 原始文件类型 -->
		           <table style="font-size: 12px;" >
		             <tr>
		               <td>标题</td>
		               <td><input type="text" name="officialArchivesInfoAttachedFile.title" size="50" id="title"/></td>
		             </tr>
		             <tr>
		               <td>选择文件</td>
		               <td><input type="file" name="filePath"  id="filePath" size="50" onchange="getTitle(this)"/></td>
		             </tr>
		             <tr>
		                <td></td>
		                <td>
			                <input type="radio" value="1" name="officialArchivesInfoAttachedFile.fetchFullTextRequest" checked="checked"/>不提取全文
			                <input type="radio" value="2" name="officialArchivesInfoAttachedFile.fetchFullTextRequest"/>提取全文并追加
			                <input type="radio" value="3" name="officialArchivesInfoAttachedFile.fetchFullTextRequest"/>提取全文并覆盖
		                </td>
		             </tr>
		             <tr>
		               <td></td>
		               <td><input type="button" value="上    传 " class="button" onclick="submitForm()"/></td>
		             </tr>
		           </table>                         
			    </form>
			    
				<table class="tabletop" width="100%">
					<tr>
		                <td class="tableTitle">原文</td>
		                <td align="right" class="text">共<label id="filesSum"></label>条记录</td>							                							                	
		            </tr>
				</table>		
				<table id="showTable" width="100%" style="margin:0px; border: #104da6 1px solid;" cellspacing="1px" cellpadding="0px" >						
					<thead class="tableHead">
						<tr>
							<th>序号</th>
							<th>标题</th>								
							<th>文件名</th>
							<th>文件类型</th>
							<th>文件大小(KB)</th>
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
 