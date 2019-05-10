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
    <base href="<%=basePath%>">
    
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
	<script type="text/javascript" src="dwr/interface/attachedFileManageAction.js"></script>
    <script type="text/javascript" src="dwr/util.js"></script>
    <script type="text/javascript" src="dwr/engine.js"></script>
	<script type="text/javascript">
	if (typeof window['DWRUtil'] == 'undefined')
	{
	 window.DWRUtil = dwr.util;
	}

	//查找原文
    function findFiles(){
    	attachedFileManageAction.findAllAttachedFile($("NBXH").value,$("archivesTypeID").value,findAllAttachedFileCallBack);
    }
    function findAllAttachedFileCallBack(data){
        $("filesSum").innerHTML=data.length;
    	var i=1;
    	DWRUtil.removeAllRows('files');
    	DWRUtil.addRows("files",data,
    	[ 
    	  function(item){ return item.title;},	  
    	  function(item){ return item.originalType;},
    	  function(item){ return item.originalSize;} ,
    	  function(item){ return getFormateDate(item.attachedTime);} ,
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
    	attachedFileManageAction.getServiceURLDWR(getServiceURLCallback);
    }
    function getServiceURLCallback(data){
    	serviceURL = data;
    }
    
  //删除原文
    function deleteFile(fileID){
        attachedFileManageAction.delAttachedFileByNBXHDWR(fileID,$("NBXH").value,$("archivesTypeID").value,delAttachedFileCallBack);
    }
    function delAttachedFileCallBack(data){
    	window.setTimeout("findFiles()",500)
    }

    //下载原文
	function downLoad(fileID){
		attachedFileManageAction.downLoadAttachedFileByNBXHDWR(fileID,$("NBXH").value,$("archivesTypeID").value,downLoadCallBack);
		//window.location.href="/aiim/YWGL/attachedFileManageAction_downLoadAttachedFileByNBXH.action?fileID="+fileID+"&NBXH="+$("NBXH").value+"&archivesTypeID="+$("archivesTypeID").value;
    }
    function downLoadCallBack(data){
    	window.location.href = data;
    }

    //获得文件标题
    function getTitle(obj){
        var path = obj.value;
        $("title").value = path.substring(path.lastIndexOf("\\")+1,path.lastIndexOf("."));
    }

    function submitForm(){
    	getSavedPath(j("#archivesTypeID").val());
    }

    //获得上传后文件的路径
    function getSavedPath(archivesTypeID){
        var nbxh = j("#NBXH").val();
        var oldPath = j("#filePath").val();
        alert(oldPath);
        var deptName = '<%=userInfo.getDepartmentName()%>'; 
        j.ajax({
            url:"YWGL/attachedFileManageAction_getSavedPath.action",
            data:"archivesTypeID="+j("#archivesTypeID").val(),
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

    function openMessage()
    {
    	var div11=document.createElement("div11");
    	div11.style.position="absolute";
    	div11.style.visibility="visible";
    	div11.style.top="0";
    	div11.style.left="0";
    	div11.style.filter="alpha(opacity=70)";
    	div11.style.width="100%";
    	div11.style.height="100%";
    	div11.style.zIndex="10001";
    	div11.style.background="#CCCCCC";
    	div11.name="div11";
    	div11.id="div11";

    	var div2 =  document.getElementById("divAddKC");
    	div2.style.position="absolute";
    	div2.style.visibility="visible";
    	div2.style.top=200;
    	div2.style.left=150;
    	div2.style.filter="alpha(opacity=100)";
    	div2.style.zIndex="10002";
    	div2.style.background="#FFFFFF";
    	
    	div11.appendChild(div2);
    	document.body.appendChild(div11);
    }
    //关闭弹出div
    function colseMessage()
    {	
        alert("close");
    	var div11=document.getElementById("div11");
    	document.body.removeChild(div11);
    }
	</script>
</head>
<!-- onload="findFiles()" -->
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
	           <form id="upLoadForm" name="upLoadForm" action="YWGL/attachedFileManageAction_saveArchivesInfoAttachedFile.action" method="POST" style="text-align: center;" > 
	              <input type="hidden" name="archivesInfoAttachedFile.archivesTypeID" id="archivesTypeID" value="<%=request.getParameter("archivesTypeID")%>"/><!-- 分类编号 -->
	              <input type="hidden" name="archivesInfoAttachedFile.NBXH" id="NBXH" value="<%=request.getParameter("NBXH")%>"/><!-- 内部序号 -->
	              <input type="hidden" name="archivesInfoAttachedFile.archivingFileName " id="archivingFileName"/><!-- 归档文件名 -->
	              <input type="hidden" name="archivesInfoAttachedFile.oriFileName" id="oriFileName"/><!-- 原始文件名 -->
	              <input type="hidden" name="archivesInfoAttachedFile.originalSize" id="originalSize"/><!-- 原始文件大小 -->
	              <input type="hidden" name="archivesInfoAttachedFile.originalType" id="originalType"/><!-- 原始文件类型 -->
	               
		           <table style="font-size: 12px;" >
		             <tr>
		               <td>文件题名</td>
		               <td><input type="text" name="archivesInfoAttachedFile.title" size="50" id="title"/><!-- 文件标题 --></td>
		             </tr>
		             <tr>
		               <td>选择文件</td>
		               <td><input type="file" name="filePath" id="filePath" size="50" onchange="getTitle(this)"/></td>
		             </tr>
		             <tr>
		                <td></td>
		                <td>
			                <input type="radio" value="0" name="archivesInfoAttachedFile.fetchFullTextRequest" checked="checked"/>不提取全文
			                <input type="radio" value="1" name="archivesInfoAttachedFile.fetchFullTextRequest"/>提取全文并追加
			                <input type="radio" value="2" name="archivesInfoAttachedFile.fetchFullTextRequest"/>提取全文并覆盖
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
							<th>题名</th>								
							<th>类型</th>
							<th>大小(KB)</th>
							<th>上传日期</th>
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
	<!-- <APPLET id="signApplet" CODE = "com.orifound.aiim.util.FileSignerApplet" JAVA_CODEBASE="<%=basePath%>Applet" ARCHIVE = "appletSign.jar" WIDTH="100" HEIGHT="100"> -->
</body>
</html>
 