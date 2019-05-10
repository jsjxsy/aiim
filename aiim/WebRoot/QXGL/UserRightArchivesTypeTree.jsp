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
<script type="text/javascript" src="dwr/interface/UserRightArchivesTypeManageDWR.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript">
function modifyArchivesType(){
	var keys = new Array();
	keys = getKeys();//获取所有的已选择的checkbox值
	var UserID = ${UserID};
	UserRightArchivesTypeManageDWR.modifyUserRightArchivesType(keys,UserID,callback);
  }

function callback(data){
	if(data){
		alert("修改用户档案资源访问权限成功！");
	}else{
		alert("修改用户档案资源访问权限失败！");
	}
}
</script>

<style type="text/css">
   #divTree{border:0;padding:4;overflow:auto;white-space:nowrap; margin:0px; padding: 0px;}
</style>
</head>

<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0" style="height:100%;margin: 0px;">
    <tr>
      <td align="left" valign="top"><div id="divTree2"></div></td>
    </tr>
    <tr  align="center">
	   <td><input type="button" value="提交" onclick="modifyArchivesType()"> &nbsp;&nbsp;&nbsp;
	    <input type="button" value="重置" > 
	    </td>
    <tr>
</table>
    <script>
		  //记录当前选中的节点
		  var selectedNode;
		  //构造数
		   var tree=new alai_tree_pretty(divTree2);
		   var root=tree.root;
		   ${tree}	
		   //所有的节点默认关闭  
		   tree.expandAll(false);
		   
		   //单击checkBox事件	  
		    tree.oncheck =function(srcNode){
	      if(srcNode.hasChild){
		    	var children = srcNode.children;
			 	var checked = srcNode.checkBox.checked;
				for(var i=0;i<children.length;i++){
				    children[i].checkBox.checked=checked;
				}		
		  }else{
			  var checked = srcNode.checkBox.checked;
			  if(checked == true){
			  srcNode.parent.checkBox.checked = checked;
			  }
			}
		  }
			
	   //得到所有的选中的末节点
	   function getKeys(){	
	     var keys = new Array();  
	     var colChkNodes = tree.colChkNode
		 for(var i=0;i<colChkNodes.length;i++){
			if(colChkNodes[i].checkBox.checked){
				keys.push(colChkNodes[i].getKey());
			 }
		 }	
		return keys;
	   }
	 </script>
</body>
</html>
