/*******************************************************************************
		阿赖目录树控件程序 由赖国欣设计于2003年6月，保留所有权利！
*********************************************************************************/
function alai_imagelist()
{try{
	var item=[]
	count=0
	this.path=""
	this.type="gif"
	this.err=""
	iml=this
	this.copywrite="Copywrite by Alai(赖国欣) (c)2003，All right reserved!"
	this.add=function(src,key)
	{
		var img=new Image()
		if(src==null || src=="")return
		if(src.indexOf("/")==-1)src=iml.path+src
		if(!(/\.gif$|\.jpg$|\.png$|\.bmp$/i).test(src))src+="."+iml.type
		if(key==null || key=="")key=src.replace(/(.*\/){0,}([^\.]+).*/ig,"$2")
		item[count]=img;img.index=count;item[key]=img;count++;
		img.onerror=function()
		{
			alert("alai_imagelist cause run time error:\nImage file "+img.src+" not found!")
			item[img.index]=null;item[key]=null;count--;
		}
		img.src=src
		return img
	}
	this.item=item
	this.count=function(){return count;}
}
catch(e){
	iml.err="alai_imagelist cause run time error!\nError number:"+e.number+".\nError description:"+e.description;
	iml.err+="\n    -- \nYou can send the case and error description message to A@lai.com.cn for support.Please visit http://www.9499.net to get the last release!"
	alert(iml.err);
	return iml;
}
}

function alai_tree(imageList,indent,toObject,labelStyle)
{try{
	if(!document.getElementById){throw new Error(999,"Your browser dosen't support Alai_tree(),IE5.0+ is recommended.");}
	var count=0,depth=0,nodes=[],tree=this
	this.copywrite="Copywrite by Alai(赖国欣) (c)2003，All right reserved!"
	this.err=""
	imageList=imageList==null?new alai_imagelist():imageList
	var icons=imageList.item
	this.imageList=imageList
	if(icons["default"]!=null){if(icons["link"]==null)icons["link"]=icons["default"]}
	var showExpand=icons["expand"]!=null && icons["collapse"]!=null
	if(showExpand)
	{	if(icons["expand_top"]==null)icons["expand_top"]=icons["expand"]
		if(icons["expand_end"]==null)icons["expand_end"]=icons["expand"]
		if(icons["collapse_top"]==null)icons["collapse_top"]=icons["collapse"]
		if(icons["collapse_end"]==null)icons["collapse_end"]=icons["collapse"]
	}
	var showBranch=icons["leaf"]!=null && icons["twig"]!=null
	var showLine=icons["line"]!=null && icons["blank"]!=null
	var root={}
		root.children=[]
		root.expanded=true;
		root.getTier=function(){return 0;}
	var selectedNode=null,activeNode=null;
	Array.prototype.remove=function(index){if(index<0 || index>=this.length)return;for(var i=index;i<this.length;i++)this[i]=this[i+1];this.length--;}
	Array.prototype.indexOf=function(elm){for(var i=0;i<this.length;i++)if(elm==this[i])return i;return -1;}
	var setExIcon=function(srcNode)
	{	if(srcNode.hasChild && showExpand)
		{	var sEx=srcNode.next==null?"_end":"";
			if(srcNode==nodes[0])sEx="_top";
			srcNode.exIcon.src=srcNode.expanded?icons["collapse"+sEx].src:icons["expand"+sEx].src;
			srcNode.icon.style.marginLeft=0;
		}
		else
		{	if(showBranch){srcNode.exIcon.src=srcNode.next==null?icons["twig"].src:icons["leaf"].src}
			else if(showExpand){srcNode.icon.style.marginLeft=icons["expand"].width}
		}
	}
	var setLine=function(srcNode,idx)
	{	if(srcNode.hasChild){for(var i=0;i<srcNode.children.length;i++){srcNode.children[i].lineIcon[idx].src=icons["line"].src;setLine(srcNode.children[i],idx)}}
	}
	var doSelect=function(srcNode)
	{	if(selectedNode!=null){selectedNode.label.style.background="";selectedNode.label.style.color="black";}
		srcNode.label.style.background="highlight";srcNode.label.style.color="highlighttext";
		selectedNode=srcNode
	}
	var doFocus=function(srcNode)
	{	if(srcNode==selectedNode){srcNode.label.style.background="highlight";srcNode.label.style.color="highlighttext";}
		activeNode=srcNode
	}
	var doBlur=function(srcNode)
	{	if(srcNode==selectedNode){srcNode.label.style.background="buttonface";srcNode.label.style.color="windowtext";}
		activeNode=null
	}
	var run=function(cmd,a0,a1,a2)
	{
		if(typeof(cmd)=="string")
		{	try{return eval(cmd);}
			catch(E){alert("run script string error:\n"+cmd);}
		}
		else if(typeof(cmd)=="function"){return cmd(a0,a1,a2);}
	}
	var doKeyDown=function(srcNode)
	{	event.returnValue=false;
		switch(event.keyCode)
		{	case 38:
				if(srcNode.prev==null){if(srcNode.parent!=root){srcNode.parent.focus();srcNode.parent.select();}}
				else{var node=srcNode.prev;while(true){if(!node.hasChild || !node.expanded)break;node=node.last;} node.focus();node.select();}
				break;
			case 40:
				if(srcNode.first!=null && srcNode.expanded){srcNode.first.focus();srcNode.first.select();}
				else{var node=srcNode;while(true){if(node.next!=null || node.parent==root)break;node=node.parent}if(node.next!=null){node.next.focus();node.next.select();}}
				break;
			case 189:	;case 109:	;case 37:	srcNode.expand(false);break;
			case 107:	;case 187:	;case 39:	srcNode.expand(true);break;
			case 13:	srcNode.click();break;
			case 27:	srcNode.blur();break;
		}
	}
	var addNode=function(toNode,relation,text,key,ico,exeCategory,exeArg)
	{
		var node={}
		var nbody=document.createElement("span")
		var container=document.createElement("div")
		container.insertAdjacentElement("afterBegin",nbody)
		var tier
		if(relation==null)relation="last"
		if(toNode==root && (relation!="first" && relation!="last"))relation="last"
		relation=relation.toLowerCase()
		node.first=node.last=node.next=node.prev=null
		switch(relation)
		{	case "first":
				tier=toNode.getTier()+1
				if(toNode.hasChild){toNode.children[0].container.insertAdjacentElement("beforeBegin",container);}
				else	{toNode.container.insertAdjacentElement("beforeEnd",container)}
				node.parent=toNode
				toNode.children[toNode.children.length]=node
				if(toNode.hasChild){node.next=toNode.first;toNode.first.prev=node;toNode.first=node;}
				else{toNode.first=toNode.last=node;}
				break;
			case "next":
				tier=toNode.getTier()
				toNode.container.insertAdjacentElement("afterEnd",container)
				node.parent=toNode.parent
				toNode.parent.children[toNode.parent.children.length]=node
				if(toNode.next==null)node.parent.last=node
				node.next=toNode.next;node.prev=toNode;toNode.next=node;
				break;
			case "prev":
				tier=toNode.getTier()
				toNode.container.insertAdjacentElement("beforeBegin",container)
				node.parent=toNode.parent
				toNode.parent.children[toNode.parent.children.length]=node
				if(toNode.prev==null)node.parent.first=node
				node.prev=toNode.prev;node.next=toNode;toNode.prev=node
				break;
			default:
				tier=toNode.getTier()+1
				toNode.children[toNode.children.length]=node
				var o=toNode==root?tree.body:toNode.container
				o.insertAdjacentElement("beforeEnd",container)
				node.parent=toNode
				if(toNode.hasChild){node.prev=toNode.last;toNode.last.next=node;toNode.last=node;}
				else{toNode.first=toNode.last=node;}
		}
		node.parent.hasChild=true
		if(depth<tier)depth=tier;
		var label=document.createElement("span")
		container.style.cssText="margin:0px;padding:0px;white-space:nowrap;"
		with(nbody)
		{	insertAdjacentElement("beforeEnd",label)
			style.cssText="cursor:default;text-align:left;color:black;font-size:10pt;margin:0;"
			style.display=node.parent.expanded?"block":"none"
			indent=indent>=0?indent:16;nbody.style.textIndent=indent*(tier-1)
		}
		var icon=new Image()
		ico=ico==null||ico==""?"default":ico
		if(typeof(ico)=="string")if(ico.toLowerCase().indexOf(".gif")!=-1 || ico.toLowerCase().indexOf(".jpg")!=-1){ico=imageList.add(ico).index;}
		if(icons[ico]!=null)
		{	icon.src=icons[ico].src
			nbody.insertAdjacentElement("afterBegin",icon)
		}
		label.innerHTML=text
		label.style.cssText=labelStyle!=null?labelStyle:"padding:1;margin-left:2;vertical-align:middle;text-align:left;"
		with(label)
		{
			onclick=function(){run(node.select);if(run(node.onclick))if(run(tree.onclick,node))run(node.execute);}
			ondblclick=function(){if(run(node.ondblclick))run(tree.ondblclick,node)}
			onmouseover=function(){if(event.fromElement!=node.icon)if(run(node.onmouseover))run(tree.onmouseover,node)}
			onmouseout=function(){if(event.toElement!=node.icon)if(run(node.onmouseout))run(tree.onmouseout,node);}
			onmousemove=function(){if(run(node.onmousemove))run(tree.onmousemove,node)}
			onmousedown=function(){if(run(node.onmousedown))run(tree.onmousedown,node)}
			onmouseup=function(){if(run(node.onmouseup))run(tree.onmouseup,node)}
			onkeypress=function(){if(run(node.onkeypress))run(tree.onkeypress,node)}
			onkeydown=function(){if(run(node.onkeydown)){if(run(tree.onkeydown,node))doKeyDown(node);}}
			onkeyup=function(){if(run(node.onkeyup))run(tree.onkeyup,node)}
			onfocus=function(){if(run(node.onfocus)){if(run(tree.onfocus,node))doFocus(node);}}
			onblur=function(){if(run(node.onblur)){if(run(tree.onblur,node))doBlur(node);}}
		}
		with(icon)
		{
			onclick=function(){run(node.select);if(run(node.onclick))if(run(tree.onclick,node))run(node.execute);}
			ondblclick=function(){if(run(node.ondblclick))run(tree.ondblclick,node)}
			onmouseover=function(){if(event.fromElement!=node.icon)if(run(node.onmouseover))run(tree.onmouseover,node)}
			onmouseout=function(){if(event.toElement!=node.icon)if(run(node.onmouseout))run(tree.onmouseout,node);}
			onmousemove=function(){if(run(node.onmousemove))run(tree.onmousemove,node)}
			onmousedown=function(){if(run(node.onmousedown))run(tree.onmousedown,node)}
			onmouseup=function(){if(run(node.onmouseup))run(tree.onmouseup,node)}
			onkeypress=function(){if(run(node.onkeypress))run(tree.onkeypress,node)}
			onkeydown=function(){if(run(node.onkeydown)){if(run(tree.onkeydown,node))doKeyDown(node);}}
			onkeyup=function(){if(run(node.onkeyup))run(tree.onkeyup,node)}
			onfocus=function(){if(run(node.onfocus)){if(run(tree.onfocus,node))doFocus(node);}}
			onblur=function(){if(run(node.onblur)){if(run(tree.onblur,node))doBlur(node);}}
		}
		nbody.onblur=function(){if(node.onfocus()){if(tree.onfocus(node))doFocus(node);}}
		nbody.onfocus=function(){if(node.onblur()){if(tree.onblur(node))doBlur(node);}}
		icon.tabIndex=label.tabIndex=1;icon.hideFocus=true;
		node.onclick=node.ondblclick=node.onmouseover=node.onmouseout=node.onmousemove=node.onmousedown=node.onmouseup=node.onkeypress=node.onkeydown=node.onkeyup=node.oncollapse=node.onexpand=node.onfocus=node.onblur=node.onselect=new Function("return true");
		node.click=function(){node.label.click();}
		node.focus=function(){node.label.focus();}
		node.blur=function(){node.icon.blur();node.label.blur();}
		node.select=function(){if(node.onselect()){if(tree.onselect(node))doSelect(node);}}
		node.body=nbody
		node.container=container
		node.exIcon=null
		if(showBranch){node.exIcon=new Image();nbody.insertAdjacentElement("afterBegin",node.exIcon);}
		node.lineIcon=[]
		if(showLine)
		{	for(var i=tier-2;i>=0;i--){var img=new Image();img.src=icons["blank"].src;nbody.insertAdjacentElement("afterBegin",img);node.lineIcon[i]=img;}
			if(node.prev!=null)setLine(node.prev,tier-1)
			var n=node.parent;var i=tier-2;while(n!=root){if(n.next!=null)node.lineIcon[i].src=icons["line"].src;n=n.parent;i--}
		}
		if(showBranch){setExIcon(node);if(node.prev!=null)setExIcon(node.prev);if(node.next!=null)setExIcon(node.next);}
		var ma=node.parent;if(ma!=root){if(ma.exIcon==null && showExpand){ma.exIcon=new Image();var o=ma.icon.src==""?ma.label:ma.icon;o.insertAdjacentElement("beforeBegin",ma.exIcon);}setExIcon(ma);if(ma.exIcon){ma.exIcon.onmousedown=function(){ma.expand();}}}
		if(showExpand & !showBranch){icon.style.marginLeft=icons["expand"].width}
		node.label=label
		node.icon=icon
		node.add=function(text,ico,exeType,exeArg){return addNode(this,"last",text,"",ico,exeType,exeArg);}
		node.addLink=function(_url,text,target,img){if(text==null||text=="")text=_url;var exeArg="url";if(target!=null&&target!="")exeArg+=":"+target;if((img==null||img==""))img="link";return addNode(node,"last",text,"",img,exeArg,_url)}
		node.expanded=true
		node.expand=function(isShow,incSub)
		{
			if(node.children.length==0)return;
			if(isShow==null)node.expanded=!node.expanded;else node.expanded=isShow;
			incSub=incSub==null?false:incSub
			var sh=node.expanded?"block":"none"
			for(var i=0;i<node.children.length;i++){node.children[i].container.style.display=sh;if(incSub)node.children[i].expand(node.expanded,true);}
			setExIcon(node)
			if(node.expanded){if(run(node.onexpand)){run(tree.onexpand,node);}}
			else{ if(node.oncollapse()){tree.oncollapse(node);}}
			var n=node.parent;while(isShow && true){if(n==root)break;if(!n.expanded)n.expand(true);n=n.parent;}
		}
		node.remove=function(){tree.removeNode(node);}
		node.getPath=function(separator){separator=separator==null?"/":separator;var path="",oNode=node;for(var i=tier;i>0;i--){path=separator+oNode.label.innerText+path;oNode=oNode.parent;}return path;}
		node.getSibling=function(){return node.parent.children};
		node.getTier=function(){return tier;}
		node.getIndex=function(){for(var i=0;i<count;i++)if(tree.nodes[i]==node)return i;return -1;}
		node.children=[]
		node.hasChild=false
		node.getKey=function(){return key==""||key==null?null:key;}
		if(key!=null && key!="")tree.nodes[key]=node
		tree.nodes[count]=node
		count++
		node.execute=new Function()
		exeCategory=exeCategory==null?"":exeCategory.toLowerCase()
		switch(exeCategory)
		{	case "expand":
				node.execute=node.expand;break;
			case "js":
				if(typeof(exeArg)!="string")break;node.execute=function(){eval(exeArg);};break;
			default:
				if(exeCategory.indexOf("url")!=-1)
				{	if(typeof(exeArg)!="string"){break;}
					if(exeCategory.indexOf("url:")!=-1)
					{	var tar=exeCategory.replace("url:","");node.execute=function(){open(exeArg,tar);}
					}else {	node.execute=function(){open(exeArg,tree.target);}}
					exeCategory="url";
				}else {node.execute=node.expand;exeCategory="expand";}
		}
		run(tree.afteradd,node,exeCategory)
		return node
	}
	var remove=function(srcNode)
	{	var ma=srcNode.parent,jj=srcNode.prev,mm=srcNode.next;
		if(ma.first==srcNode && ma.last==srcNode && ma!=root)
		{	ma.hasChild=false;document.createElement("div").insertAdjacentElement("afterBegin",ma.exIcon);setExIcon(ma);ma.first=ma.last=null;
		}
		else
		{	if(jj!=null){jj.next=mm;setExIcon(jj);}else{ma.first=mm;}
			if(mm!=null){mm.prev=jj;setExIcon(mm);}else{ma.last=jj;}
		}
		srcNode.container.removeNode(true);nodes.remove(srcNode.getIndex());count--;srcNode=null;
	}
	this.removeNode=function(srcNode){if(!srcNode.hasChild)remove(srcNode);else{for(var i=0;i<srcNode.children.length;i++){tree.removeNode(srcNode.children[i]);}tree.removeNode(srcNode);}}
	root.add=function(text,ico,exeType,exeArg){return addNode(root,"last",text,"",ico,exeType,exeArg);}
	root.addLink=function(_url,text,target,img){if(text==null||text=="")text=_url;var exeArg="url";if(target!=null&&target!="")exeArg+=":"+target;if((img==null||img==""))img="link";return addNode(root,"last",text,"",img,exeArg,_url)}
	this.getNodesByTier=function(num){var col=[];for(var i=0;i<count;i++)if(nodes[i].getTier()==num)col[col.length]=nodes[i];return col;}
	this.expandAll=function(isShow){isShow=isShow==null?!root.expanded:isShow;for(var i=0;i<count;i++)nodes[i].expand(isShow);root.expanded=isShow;}
	this.expandToTier=function(num){tree.expandAll(false);for(var i=0;i<count;i++)if(nodes[i].getTier()<num)nodes[i].expand(true);}
	this.body=document.createElement("div")
	this.embed=function(obj,where){if(obj==null)obj=document.body;if(!obj.insertAdjacentElement)obj=document.body;if(where==null)where="afterBegin";var s=where.toLowerCase();if(s!="beforebegin" || s!="afterbegin" || s!="beforeend" || s!="afterend")where="afterBegin";obj.insertAdjacentElement(where,tree.body);}
	this.count=function(){return count;}
	this.getDepth=function(){return depth;}
	this.onclick=this.ondblclick=this.onmousemove=this.onmouseover=this.onmouseout=this.onmouseup=this.onmousedown=this.onkeypress=this.onkeydown=this.onkeyup=this.oncollapse=this.onexpand=this.afteradd=this.onblur=this.onfocus=this.onselect=new Function("return true;");
	this.add=addNode
	this.getNodeByPath=function(path){if(path==null||path=="")return null;if(path=="/")return root;if(path.lastIndexOf("/")!=path.length-1)path+="/";for(var i=count-1;i>=0;i--)if((nodes[i].getPath()+"/")==path)return nodes[i];return null;}
	this.testPath=function(path){if(path==null||path=="")return false;if(path=="/")return true;if(path.lastIndexOf("/")!=path.length-1)path+="/";for(var i=count-1;i>=0;i--)if((nodes[i].path()+"/").indexOf(path)==0)return true;return false;}
	this.pathParse=function(pathList,sort,ps,ls,ico)
	{	if(ps==null)ps="/";if(ls==null)ls=";";
		var list=pathList.split(ls);if(sort)list=list.sort();
		var colPath=[];for(var i=0;i<nodes.length;i++){colPath[colPath.length]=nodes[i].getPath();}
		var pathIndex=function(path){for(var i=colPath.length-1;i>=0;i--)if(colPath[i]==path)return i;return -1;}
		for(var i=0;i<list.length;i++)
		{	if(list[i]=="")continue;var path=list[i].split(ps),node=root;
			for(var j=0;j<path.length;j++)
			{	if(path[j]==""){continue;};var p="";for(var m=0;m<=j;m++){if(path[m]!=""){p+="/"+path[m];}}
				idx=pathIndex(p);if(idx!=-1){node=nodes[idx];}else {node=node.add(path[j],ico);colPath[colPath.length]=p;}
			}
		}
	}
	this.getSelectedNode=function(){return selectedNode;}
	this.getActiveNode=function(){return activeNode;}
	this.target="_self"
	this.root=root
	this.nodes=nodes
	this.embed(toObject)
}
catch(e){
	tree.err="alai_tree cause run time error!\nError number:"+e.number+".\nError description:"+e.description;
	tree.err+="\n    -- \nYou can send the case and error description message to A@lai.com.cn for support or visit http://www.9499.net to get the last release!"
	alert(tree.err);
	return tree;
}
}