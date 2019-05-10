/********************************************************************************************
	阿赖目录树控件支持checkbox节点功能扩展程序 由赖国欣设计于2003年7月17日，保留所有权利！
**********************************************************************************************/
function alai_tree_check()
{
	if(typeof(alai_tree)!="function")
	{
		alert("run alai_tree_check() fail, please load alai_tree firt!")
		return
	}
	//add(toNode,relation,text,key,ico,exeCategory,exeArg)
	var colChkNode=[]
	alai_tree.prototype.colChkNode=colChkNode
	alai_tree.prototype.addChkNode=function(toNode,text,key,ico,exeCategory,exeArg,checked)
	{
		var newNode=this.add(toNode,"last",text,key,ico,exeCategory,exeArg)
		var chkBox=document.createElement('<input type="Checkbox">')
		var tree=this
		newNode.label.insertAdjacentElement("beforeBegin",chkBox)
		newNode.isCheck=true
		if(typeof(checked)=="boolean")chkBox.checked=checked;
		newNode.oncheck=new Function("return true;")
		chkBox.onpropertychange=function(){if(newNode.oncheck())tree.oncheck(newNode)}
		colChkNode[colChkNode.length]=newNode
		newNode.checkBox=chkBox
		return newNode
	}
	alai_tree.prototype.oncheck=new Function("return true;")
}
alai_tree_check()