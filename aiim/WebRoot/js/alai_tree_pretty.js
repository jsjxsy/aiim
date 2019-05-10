
function getScriptPath(js)
{
	js=js.toLowerCase()
	var script=document.getElementsByTagName("SCRIPT");
	for(var i=0;i<script.length;i++)
	{
		var s=script[i].src.toLowerCase()
		if(s.indexOf(js)!=-1)return s.replace(js,"")
	}
	return null
}

function alai_tree_pretty(toObject)
{
	var path=getScriptPath("alai_tree.js")
	if(path==null){alert("run alai_tree_pretty() fail, please load alai_tree.js first!");return;}
	var icons=new alai_imagelist()
	icons.path=path+"../images/"
	icons.add("hfile","default")
	icons.add("plus","expand")
	icons.add("minus","collapse")
	icons.add("hfold_open")
	icons.add("hfold_close")
	//icons.add("hfile","leaf")
	//icons.add("hfile","twig")
	
	var tree=new alai_tree(icons,18,toObject)
	tree.afteradd=function(srcNode)
	{
		if(srcNode.parent!=tree.root)srcNode.parent.icon.src=icons.item["hfold_open"].src
	}
	tree.onexpand=function(srcNode)
	{
		srcNode.icon.src=icons.item["hfold_open"].src
	}
	tree.oncollapse=function(srcNode)
	{
		srcNode.icon.src=icons.item["hfold_close"].src
	}
	return tree
}
