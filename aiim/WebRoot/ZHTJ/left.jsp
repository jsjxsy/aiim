<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'tree.jsp.jsp' starting page</title>
    
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
	       if(!selectedNode.hasChild){
		       if(srcNode.getKey() == 37)parent.window.right.location="${reportResultArchivesCollection}?ID="+srcNode.getKey();
		       if(srcNode.getKey() == 38)parent.window.right.location="${reportResultArchivesUsePerson}?ID="+srcNode.getKey();
		       if(srcNode.getKey() == 39)parent.window.right.location="${reportResultArchivesTypeUse}?ID="+srcNode.getKey();
		       if(srcNode.getKey() == 40)parent.window.right.location="${reportResultArchivesUsePurpose}?ID="+srcNode.getKey();
		       if(srcNode.getKey() == 41)parent.window.right.location="${reportResultArchivesSaved}?ID="+srcNode.getKey();
		       if(srcNode.getKey() == 42)parent.window.right.location="${reportResultDepartmentSaved}?ID="+srcNode.getKey();
		       if(srcNode.getKey() == 26)parent.window.right.location="${reportResultPersionalArchivesMoveOut}?ID="+srcNode.getKey();
		       if(srcNode.getKey() == 27)parent.window.right.location="${reportResultArchivesDestroy}?ID="+srcNode.getKey();
		       if(srcNode.getKey() == 28)parent.window.right.location="${reportResultArchivesPublic}?ID="+srcNode.getKey();
		       if(srcNode.getKey() == 29)parent.window.right.location="${reportResultWorkProcedure}?ID="+srcNode.getKey();
		       if(srcNode.getKey() == 30)parent.window.right.location="${reportResultStoreroomUse}?ID="+srcNode.getKey();
		       if(srcNode.getKey() == 43)parent.window.right.location="${reportResultTempratureHumidityForYear}?ID="+srcNode.getKey();
		       if(srcNode.getKey() == 44)parent.window.right.location="${reportResultTempratureHumidityForMonth}?ID="+srcNode.getKey();
		       if(srcNode.getKey() == 34)parent.window.right.location="${reportResultCertificateCharge}?ID="+srcNode.getKey();
		       if(srcNode.getKey() == 35)parent.window.right.location="${reportResultOfficialArchivesInput}?ID="+srcNode.getKey();
		       if(srcNode.getKey() == 36)parent.window.right.location="${reportResultSystemVisit}?ID="+srcNode.getKey();
		       
		    }
		  
	   }
	   
	   //添加节点
	   function addNode(){
	       //var addTitle = $("#addNodeTitle").attr("value");
	       tree.add(selectedNode,"last",addTitle,"n45","","","");
	   }

       function getLabel(){
    	   return selectedNode.label.innerHTML;
       }
	   
	   //删除节点
	   function delNode(){
	      tree.removeNode(selectedNode);
	   }
	</script>
</body>
</html>
