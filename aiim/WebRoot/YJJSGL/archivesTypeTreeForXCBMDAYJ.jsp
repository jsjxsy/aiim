<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>形成部门档案移交树</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="js/alai_tree.js"></script>
	<script src="js/alai_tree_help.js"></script>
	<script src="js/jquery-1.4.2.min.js"></script>
	<style type="text/css">
	 #divTree{border:0;padding:4;overflow:auto;white-space:nowrap; margin:0px; padding: 0px;}
	</style>

</head>

<body>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" style="height: 100%;margin: 0px;">
	    <tr>
	      <td align="left" valign="top"><div id="divTree"></div></td>
	    </tr>
	</table>

    <script>
	  //记录当前选中的节点
	  var selectedNode;

	  //构造数
	   var tree=new alai_tree_help(divTree);
	   var root=tree.root;
	   ${tree}	
	   //所有的节点默认关闭  
	   tree.expandAll(false);
	   
	   //节点被选中
	   tree.onclick=function(srcNode){
	       selectedNode = srcNode;
	        if(!srcNode.hasChild){
	          parent.window.right.location="${proceseAction}?state="+srcNode.getKey()+"&archivesTypeID="+srcNode.parent.getKey();
	       } 
	   }
	</script>
	<s:if test="#request.message != null">
	   <script>alert("${message}");</script>
	</s:if>
	
</body>
</html>
