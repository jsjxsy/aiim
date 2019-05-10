<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

    <title>自定义条件查询</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript">
	//打开查询对话框
		function showFind() {
		    var sFeature;
			var strNameValueUrl = "";	
			var archivesTypeIdstr = window.parent.left.getKeys();
			//alert(archivesTypeIdstr);
			if(archivesTypeIdstr==''){
			   alert("请选择档案分类！");
			}else{			
				strNameValueUrl = showModalDialog("/aiim/ZHCX/integratedQueryAction_getPublicQueryItems.action?archivesTypeIdstr="+archivesTypeIdstr,"","dialogWidth=450px;dialogHeight=300px;center=yes;scroll=no;status=no;resizable=no;help=no;");
				if(strNameValueUrl==undefined){
					return false;
				}
				//alert(strNameValueUrl);
				//alert(archivesTypeIdstr);			
				window.document.location.href="/aiim/ZHCX/integratedQueryAction_getCountInfoByArchivesTypes.action?archivesTypeIdstr="+archivesTypeIdstr + strNameValueUrl;
			}		
		}

		//在页面下方打开档案信息列表
		function showListDetail(archivesTypeId){
			document.getElementById("archivesTypeId").value = archivesTypeId;//给表单中的档案分类ID赋值			
			document.forms["findListDetail"].submit();
			return false;
			
		}

		//关键字检索
		function keyValueQuery(){
	
			//调用左边页面中的方法，获取已经选中的最末节点档案分类编号
			var archivesTypeIdstr = window.parent.left.getKeys();				
			if(archivesTypeIdstr==''){
			   alert("请选择档案分类！");
			}else{
				$("#archivesTypeIdstr").val(archivesTypeIdstr);
				document.forms["findByKeyWordForm"].submit();		
			//	window.document.location.href="/aiim/ZHCX/integratedQueryAction_getCountInfoByArchivesTypes.action?archivesTypeIdstr="+archivesTypeIdstr ;
			}
		}
		
		function findArchives(ArchivesTypeId){
			alert('findArchives');
		   $("#archivesTypeId").val(ArchivesTypeId);
		   document.conditionForm.submit();
		}

		//打开查阅申请
		function viewUseList(){
			window.showModalDialog("/aiim/DALY/archivesUseAction_addArchivesToUseList.action?archivesTypeIdAndNBXHs="+"&archivesUseWay="+2,"newwindow","dialogWidth=600px;dialogHeight=480px;center=yes;scroll=no;status=no;resizable=no;help=no;location=no");
		}
		
	</script>

<STYLE type="text/css">

TABLE.Form {
 BACKGROUND: #FFFFFF; COLOR: black; TEXT-ALIGN: left; 
 BORDER: 1px solid #A0B3C6;
 WIDTH: 100%; MARGIN: 0px; BORDER-COLLAPSE: collapse; 
}

TABLE.Form TD {
 FONT-FAMILY: Arial, Helvetica, Geneva; FONT-SIZE: 10pt; FONT-WEIGHT: normal; FONT-STYLE: none;
 TEXT-DECORATION: none; TEXT-ALIGN: left;
 BACKGROUND: #FFFFFF; COLOR: BLACK;
 BORDER: 1px solid #DEE7FA; PADDING: 2px; 
 VERTICAL-ALIGN: middle; 
}

</STYLE>
  </head>
  <body style="margin: 0;padding: 0;">  
	  <s:if test="#request.errMsg != null">
	    <script>alert("${requestScope.errMsg}!")</script>
	  </s:if>
  
         <table width="100%" style="margin:0px; padding: 0;" align="center" cellpadding="0" cellspacing="0" >
		     <tr>
			  <td>   
			     <table width="100%" cellpadding="0" cellspacing="0" border="0" style="margin: 0;padding: 0;" >
			       <tr>
				       <td>
                          <input type="image" src="images/viewApplication.gif" onmouseover="changeImage(this,'viewApplication2.gif')" onmouseout="changeImage(this,'viewApplication.gif')" onclick="viewUseList();"/>	
					   </td>
					   <td>
					   <div id="location" style="margin-right: 2px; width: 280px; display: inline-block; float: right; margin-top: 0px; color: blue; text-align: right;">
						  <font style="font-size: 12px;"><b>当前位置：</b>综合查询&nbsp;&gt;&gt;&nbsp;&nbsp;自定义条件查询</font>
					   </div>
					   	 				
					   </td>
			       </tr>
			     </table>
			   </td>
			</tr>
		</table>
		
		<!-- 单击统计结果查询档案信息，在iframe中显示 -->
		<form name="findListDetail" action="<%=basePath%>ZHCX/integratedQueryAction_findArchivesByQuerySQL.action" target="ListDetail" method="post" style="display: block;margin: 0;padding: 0;">			 
			<!-- 关键字，关键字检索 -->
			<input type="hidden" name="keyWordSQL" id="keyWordSQL" value="${request.keyWordSQL}">
			<!-- 跨门类查询的SQL语句 -->
			<input type="hidden" name="querySQL" id="querySQL" value="${request.querySQL}">
			<!-- 档案分类编号，由JS动态设置 -->
			<input type="hidden" name="archivesTypeId" id="archivesTypeId">
		  </form>
		<!-- 如果不是初始页面 -->
		<s:if test="#request.beginFlag==false">
			<!-- 当查到结果时列表显示 -->
			<s:if test="#request.resultNum > 0">
		  		<table align="center" width="80%" class="Form" cellpadding="1px;" cellspacing="1px;">			
					<tr>
						<s:iterator value="#request.archivesTypeCountInfos" status="status">
						    <s:if test="(#status.index+1)%6 == 0">						      
						       </tr><tr>
						    </s:if>			    
					    	<td width="20%"><a href="#"  target="ListDetail" onclick="showListDetail('<s:property value="archivesType.ID" />');return false;"><s:property value="archivesType.fullName" />(<s:property value="countNum" />)</a></td>
					   </s:iterator>
					</tr>			
				</table>
		  	</s:if>
		  	<!-- 当没有查到结果时给出提示 -->
		  	<s:else>
		  		<center><SPAN style="color: red;"> 没有满足条件的结果</SPAN></center>
		  	</s:else>
		</s:if>
		
		<iframe name="ListDetail" src="" width="100%" height="800px" frameborder="0"  marginheight=0 marginwidth=0 scrolling=auto  >		
		</iframe>
			

	</body>
</html>
    