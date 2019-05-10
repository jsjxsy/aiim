<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>新增自定义条件查询</title>
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
	<script type="text/javascript">
	function getPublicQueryItem(){
		//alert(getQueryNameTemp());
		var archivesTypeIdstr = getKeys();//获取档案分类ID字符串;格式：xx:xx: 
		if(archivesTypeIdstr==''){
			alert('请选择档案分类！');
			return false;
		}
		document.rigthPage.location.href = "/aiim/ZHCX/integratedQueryAction_getPublicQueryItemsForDefinedQuery.action?archivesTypeIdstr="+archivesTypeIdstr;
	}
	</script>

</head>

<body>
<div style="width: 100%; margin-bottom: 2px; padding-top: 4px" align="left">
<table style="margin: 0;padding: 0;" cellpadding="0" cellspacing="0">
<tr>
	<td><span style="font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;查询名称 </span></td>
	<td><input type="text"  style="width: 635px;" name="queryNameTemp" id="queryNameTemp"/></td>
</tr>
<tr>
	<td><span style="font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;备注信息 </span></td>
	<td><textarea name="remark" id="remark" style="width: 635px;height: 40px;" ></textarea></td>
</tr>
</table>

</div>
	<table height="100%" width="100%" cellpadding="0" cellspacing="0" >
		<tr>
			<td height="100%" width="200px;">
			  <fieldset style="height: 85%; width: 200px;margin-left:3px; overflow: auto;background-color: white;">
			  	<legend style="font-size: 12px; ">选择档案分类</legend>				
				      <div id="divTree"></div>				  
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

			   //获取自定义条件查询名称
			   function getQueryNameTemp(){		
				 		   
				   return $("#queryNameTemp").val();
			   }
			
			     $(document).ready(function(){
				   // window.parent.right.location.href="KMLCX.jsp";
			     });
			   </script>			   
			   </fieldset>
			
			</td>
			<td width="90%" height="100%" >							
				<input type="button" style="margin-top: 7px;" value="创建查询条件" onclick="getPublicQueryItem()"/>
				<iframe src="" frameborder="1"  name="rigthPage" id="rightPage" style="width: 98%;height: 79%">
				</iframe>				
			</td>
		</tr>
	</table>
	
</body>
</html>
