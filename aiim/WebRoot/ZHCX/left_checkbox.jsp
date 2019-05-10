<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache"> 
	<script src="js/alai_tree.js"></script>
	<script src="js/alai_tree_pretty.js"></script>
	<script src="js/alai_tree_check.js"></script>
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
	   var tree=new alai_tree_pretty(divTree);
	   var root=tree.root;
	   ${tree}	
	   //所有的节点默认关闭  
	   tree.expandAll(false);
	   
	   //单击checkBox事件	  
	   tree.oncheck =function(srcNode){
	      if(srcNode.hasChild){
	    	 // window.parent.right.location.href="<%=basePath%>ZHCX/integratedQueryAction_test.action";	
		     var children = srcNode.children;
			 var checked = srcNode.checkBox.checked;		
			 for(var i=0;i<children.length;i++){
			     children[i].checkBox.checked=checked;
			 }
		  }
		}

   
   //得到所有的选中的末节点
   function getKeys(){	   
    // var keys = new Array();  
	 var colChkNodes = tree.colChkNode;
	 var archivesTypeIds='';//存放档案分类编号，编号与编号之间用  ：隔开
	 for(var i=0;i<colChkNodes.length;i++){
		 if(colChkNodes[i].checkBox.checked && !colChkNodes[i].hasChild){
			//keys.push(colChkNodes[i].getKey());
			archivesTypeIds=archivesTypeIds + colChkNodes[i].getKey()+ ':';			
		 }
	 }	 
	return archivesTypeIds;
   }

     $(document).ready(function(){
	    window.parent.right.location.href="KMLCX.jsp";
     });
   </script>
   <input type="button" value="check" onclick="getKeys()"> 
</body>
</html>
