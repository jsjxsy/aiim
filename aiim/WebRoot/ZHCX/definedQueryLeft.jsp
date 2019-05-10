<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户自定义条件查询</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link type="text/css" rel="stylesheet" href="<%=basePath%>css/common.css"/>
	<script src="js/alai_tree.js"></script>
	<script src="js/alai_tree_help.js"></script>
	<script src="js/jquery-1.4.2.min.js"></script>
	
	   <script type="text/javascript" src="dwr/interface/IntegratedQueryAction.js"></script>
	<script type="text/javascript" src="dwr/util.js"></script>
	<script type="text/javascript" src="dwr/engine.js"></script>
	<style type="text/css">
	 #divTree{border:0;padding:4;overflow:auto;white-space:nowrap; margin:0px; padding: 0px;}
	</style>

<script type="text/javascript">


//打开新增自定义查询页面
function showDefinedQuery(){	
 	var returnValue =  window.showModalDialog("/aiim/ZHCX/integratedQueryAction_getDefinedQueryArchivesTypeTreeWithCheckBox.action","newwindow","dialogWidth=700px;dialogHeight=500px;center=yes;scroll=no;status=no;resizable=no;help=no;location=no");
 	if(returnValue == undefined || returnValue == ""){
 	 	return false;
 	}else{
 	 	window.location.reload(true);
 	}
}

//单击删除按钮
function clickDelete(){

	
	if(selectedNode==null){
		alert('请选择要删除的自定义条件查询！');
	}else{
		if(confirm("确认删除？")==false){
			return false;
		}else{
			IntegratedQueryAction.deleteDefinedQueryByID(selectedNode.getKey(),deleteDefinedQueryByIDBack);
		}
		
	}	
}
//删除自定义条件查询的回调函数
function deleteDefinedQueryByIDBack(data){
	if(data==""){
		alert('删除成功！');
		window.location.reload(true);
	}else{
		alert(data);
	}
}
</script>
</head>

<body style="margin: 0;padding: 0;" >
	<table width="100%" style="vertical-align: top;"  cellpadding="0" cellspacing="0" style="margin: 0px;padding: 0;">
	    <tr style="width: 100">
	      <td align="left" style="background: #d8e8ff;" width="100%">
	        <input type="button" value="添加" id="add" style="margin-left: 40px;" onclick="showDefinedQuery()" class="button"/>	   
	        <input type="button" value="删除" id="del" onclick="clickDelete()" class="button"/>
	      </td>
	    </tr>
	    <tr>
			<td>
				<div style="margin-top: 3px; overflow: auto;"  id="divTree"></div>
			</td>	    
	    </tr>
	    </table>	    
	    
    <script>
	  //记录当前选中的节点
	  var selectedNode=null;

	  //构造数
	   var tree=new alai_tree_help(divTree);
	   var root=tree.root;
	   ${tree}	
	   //所有的节点默认关闭  
	   tree.expandAll(true);
	   
	   //节点被选中
	   tree.onclick=function(srcNode){
	       selectedNode = srcNode;	       
		   parent.window.right.location="${proceseAction}?nodeId="+srcNode.getKey();
	   }
	   
	   //添加节点
	   function addNode(){
	       //var addTitle = $("#addNodeTitle").attr("value");
	       tree.add(selectedNode,"last",addTitle,"n45","","","");
	   }
	   
	   //删除节点
	   function delNode(){
	      tree.removeNode(selectedNode);
	   }
	</script>
</body>
</html>
