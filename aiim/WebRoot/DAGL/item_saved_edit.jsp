<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>" target="_self"/>   
    <title>归档信息修改</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="dwr/interface/ArchivesInfoManageDWR.js"></script>
    <script type="text/javascript" src="dwr/util.js"></script>
    <script type="text/javascript" src="dwr/engine.js"></script>
    
<!-- 页面处理  -->
 <script type="text/javascript"  >
 //上一条下一条浏览档案信息
function browsing(offset)//offset偏移量：第一条：0；上一条：1；下一条：2；最末条：3
{	 
	 var nbxh = $("NBXH").value;//获取当前显示档案的NBXH	
	 switch(offset){
	 case 0://第一条
		 getHtmlCode(archivesTypeID,NBXHS[0]);
		 index = 0;
		 break;
	 case 1://上一条
	     if(index == 0){
	       getHtmlCode(archivesTypeID,NBXHS[0]);
	     }else{
	       getHtmlCode(archivesTypeID,NBXHS[index - 1]);
	       index = index - 1;
	     }
		 
		 break;
	 case 2://下一条
	    if(index == (NBXHS.length - 1)){
	       getHtmlCode(archivesTypeID,NBXHS[NBXHS.length-1]);
	     }else{
	       getHtmlCode(archivesTypeID,NBXHS[index+1]);
	       index = index + 1;
	     } 
		 break;
	 case 3://最末条
		 getHtmlCode(archivesTypeID,NBXHS[NBXHS.length-1]);
		 index = NBXHS.length-1;
		 break;
	 }
	$("imgDel").disabled=false;
 	
 	setBrowsingStyle();
 	changePic();
 	setCurrentPage();
	// setBrowsingStyle();
}

 
//设置上一条下一条...是否可用
function setBrowsingStyle(){
	var length = NBXHS.length;	
	if(length<=1){//如果只有一条记录
		$("imgNext").disabled = true;
		$("imgLast").disabled = true;
		$("imgFirst").disabled = true;
		$("imgPrevious").disabled = true;
	}else{//两条以上记录时
		$("imgNext").disabled = false;
		$("imgLast").disabled = false;
		$("imgFirst").disabled = false;
		$("imgPrevious").disabled = false;
		if(index == 0){//当前为第一条记录
			$("imgFirst").disabled = true;
			$("imgPrevious").disabled = true;
		}else if(index == (length-1)){//当前为最后一条记录
			$("imgNext").disabled = true;
			$("imgLast").disabled = true;
		}
	}
	changePic();
	setCurrentPage();
}


	//通过检查按钮的disable属性改变图片颜色
	function changePic() {
		if ($("imgFirst").disabled==true) {	
			$("imgFirst").src="images/image_first3.gif";
		}
		else {
			$("imgFirst").src="images/image_first.gif";
		}
		if ($("imgPrevious").disabled==true) {	
			$("imgPrevious").src="images/image_previous3.gif";
		}
		else {
			$("imgPrevious").src="images/image_previous.gif";
		}
		
		if ($("imgNext").disabled==true) {
			$("imgNext").src="images/image_next3.gif";
		}
		else {
			$("imgNext").src="images/image_next.gif";
		}
		
		if ($("imgLast").disabled==true) {	
			$("imgLast").src="images/image_last3.gif";
		}
		else {
			$("imgLast").src="images/image_last.gif";
		}
		
		if ($("imgAdd").disabled==true) {	
			$("imgAdd").src="images/image_add3.gif";
		}
		else {
			$("imgAdd").src="images/image_add.gif";
		}
		
		if ($("imgDel").disabled==true) {	
			$("imgDel").src="images/image_delete3.gif";
		}
		else {
			$("imgDel").src="images/image_delete.gif";
		}

		if ($("imgSave").disabled==true) {	
			$("imgSave").src="images/image_save3.gif";
		}
		else {
			$("imgSave").src="images/image_save.gif";
		}

		if ($("imgCancel").disabled==true) {	
			$("imgCancel").src="images/image_cancel3.gif";
		}
		else {
			$("imgCancel").src="images/image_cancel.gif";
		}

		if ($("imgFile").disabled==true) {	
			$("imgFile").src="images/image_lookfile3.gif";
		}
		else {
			$("imgFile").src="images/image_lookfile.gif";
		}
	}

<%--	//按钮重置（将所有活动按钮设为不可用）--%>
    function resetButton(){
		$("imgFirst").disabled=true;
		$("imgPrevious").disabled=true;
		$("imgNext").disabled=true;
		$("imgLast").disabled=true;
		$("imgAdd").disabled=true;
		$("imgDel").disabled=true;
		$("imgSave").disabled=true;
		$("imgCancel").disabled=true;
		$("imgFile").disabled=true;
	}

<%--       通过操作类型设置按钮显隐--%>
	function setOperationStyle() {
		resetButton();//1：重置按钮	
		switch (operationType) {  //2：判断操作类型，并对各操作类型设置按钮可用状态
		case 'add':
			$("imgSave").disabled = false;
			$("imgCancel").disabled = false;
			changePic();//3：更新颜色
			break;
		case 'edit':
			setBrowsingStyle();//设置上一条下一条...的状态
			if(fileType == 0){
				$("imgAdd").style.display="none";
				$("imgDel").style.display="none";
			}else if(fileType == 1){
				$("imgAdd").disabled = false;
				$("imgDel").disabled = false;
			}
			$("imgSave").disabled = false;
			$("imgCancel").disabled = false;
			$("imgFile").disabled = false;
			changePic();//3：更新颜色
			break;
		}	
		changePic();	
	}
	
	
	//清空输入项
	function clearInput() {
		var inputs = document.getElementsByTagName("input");
		for(var i=0;i<inputs.length;i++){
			if(inputs[i].getAttribute("title") != ""){
				inputs[i].value = "";
			}
		}
		var textareas = document.getElementsByTagName("textarea");
		for(var i=0;i<textareas.length;i++){
			if(textareas[i].getAttribute("title") != ""){
				textareas[i].value = "";
			}
		}
		//$("NBXH").value = "";
		//$("parentNBXH").value = "";
	}

<%--//根据fileType设置按钮《原文》的显/隐--%>
<%--	/*function attachedFile(){--%>
<%--		if($("fileType").value=='1'){--%>
<%--			$("imgFile").disabled = true;--%>
<%--		}else{--%>
<%--			$("imgFile").disabled = false;--%>
<%--		}--%>
<%--	}*/--%>

////////////// DWR ////////////////  ////////////////////////////////////////

    //DWR:得到卷内内部序号
	function findChildArchivesInfosNBXHS(){
      	ArchivesInfoManageDWR.findChildArchivesInfosNBXHS(NBXHS[0],obj.archivesTypeID,findChildArchivesInfosNBXHSBack);
	}
	function findChildArchivesInfosNBXHSBack(childNBXHs){
	     if(NBXHS.length>1){
	        NBXHS.splice(1,NBXHS.length);
	     }
		 NBXHS = NBXHS.concat(childNBXHs);
		// alert("得到卷内后NBXHS:"+NBXHS);
		 if(fileType == 2){
		   $("parentNBXH").value= NBXHS[0];//设置页面案卷内部序号
		 }
	}

<%--	//DWR：组卷--%>
	 function combineArchivesInfos(){
		 var formMap = dwr.util.getValues("inputForm"); 
	   	 ArchivesInfoManageDWR.combineArchivesInfos(obj.archivesTypeID,NBXHS,formMap,combineArchivesInfosBack);
	 }
	 function combineArchivesInfosBack(data){
		    operationType = "edit";
		    NBXHS.splice(0,0,data);
            $("NBXH").value = data;  
	        operationType ="edit";
	        setOperationStyle();
			//alert("组卷后："+NBXHS);
	 }

<%--	   //DWR：合卷--%>
	   function mergeArchivesInfos(){		
             var formMap = dwr.util.getValues("inputForm"); 
	    	 ArchivesInfoManageDWR.mergeArchivesInfos(NBXHS,formMap,mergeArchivesInfosBack);
	   }
	   function mergeArchivesInfosBack(nbxh){
	        NBXHS.splice(0,0,nbxh);
	        findChildArchivesInfosNBXHS(NBXHS[0]);//得到卷内文件的内部序号
<%--	        //window.setTimeout(function(){alert("合卷后："+NBXHS);},500);--%>
	        operationType ="edit";
	        setOperationStyle();  
	   }

<%--		//DWR：分卷--%>
		function fenjuan(){			
			 var formMap = dwr.util.getValues("inputForm"); 
	    	 ArchivesInfoManageDWR.splitArchivesInfos(NBXHS,formMap,splitArchivesInfosBack);
		}
		function splitArchivesInfosBack(data){
			//alert("分卷返回:"+data);
			NBXHS.splice(0,0,data);
			operationType ="edit";
	        setOperationStyle();
	       // alert("分卷后："+NBXHS);
		}

     //DWR：删除档案著录信息
     function deleteArchivesInfo(){
         if(index == 0){//删除案卷
        	 ArchivesInfoManageDWR.deleteArchivesInfo($("NBXH").value,$("archivesTypeID").value,1,deleteArchivesInfoBack);
         }else{
        	 ArchivesInfoManageDWR.deleteArchivesInfo($("NBXH").value,$("archivesTypeID").value,fileType,deleteArchivesInfoBack);
         }   
    	
     }
     function deleteArchivesInfoBack(nbxh1){//DWR回调函数：删除档案信息
    	// alert(index);
         //alert(NBXHS);
         if(index == 0){//案卷被删除了
        	 //alert("案卷");
        	 //window.close();
        	 NBXHS = new Array();
        	 index = 0;
        	 operationType = "add";
        	 setOperationStyle();
        	 //NBXHS.splice(0,NBXHS.length);
             //清空数组
         }else{
            if(index == NBXHS.length-1){//如果是最后一条则向前移并删除最后一条
                //alert("上一条");
            	 browsing(1);
            	 NBXHS.splice(index+1,1);
            	 setOperationStyle();
            }else{
            	//alert("下一条");
            	//browsing(2);
           	    NBXHS.splice(index,1);
           	    setBrowsingStyle();
           	    getHtmlCode(archivesTypeID,NBXHS[index]);
            }
         }
         changePic(); 
         alert("删除成功！");
<%--         if(NBXHS.length <= 1){--%>
<%--            //清空表单--%>
<%--         }else{--%>
<%--        	 if(index == NBXHS.length -1){//如果删除的是最后一个则前移并删除最后一个   --%>
<%--                 browsing(3);--%>
<%--                 NBXHS.splice(index,1);--%>
<%--              }else{--%>
<%--                 NBXHS.splice(index,1);--%>
<%--                 getHtmlCode(archivesTypeID,NBXHS[index]);--%>
<%--              }--%>
<%--         }*/--%>
<%--         --%>
<%--	      // var nbxh = $("NBXH").value;//获取当前显示档案的NBXH--%>
<%--	      /* if(getIndexFromPageNBXHs(nbxh)!=	NBXHS.length-1){//如果不是最后一条记录时--%>
<%--	    	   browsing(2);//下一条--%>
<%--	         }else{--%>
<%--	        	$("imgDel").disabled=true;--%>
<%--	        	$("imgEdit").disabled=true;--%>
<%--	        	//changePic();        	--%>
<%--	       }--%>
<%--	       --%>
<%--	       NBXHS.splice(getIndexFromPageNBXHs(nbxh),1);//从fatherPageNBXHs中删除指定NBXH --%>
<%--	       if(NBXHS.length==0){    	   --%>
<%--	   		   $("imgFile").disabled = true;--%>
<%--	   			//changePic();    		--%>
<%--	       }     --%>
	       
     }
    
     //DWR：保存档案著录信息
     function saveArchivesInfo(){
          var formMap;
           if(fileType == 0){
                formMap = dwr.util.getValues("inputForm"); 
              ArchivesInfoManageDWR.saveArchivesInfo(0,archivesTypeID,false,formMap,saveArchivesInfoCallBack);
           } 
           if(fileType == 1){
             if(NBXHS.length>=1){
                 formMap = dwr.util.getValues("inputForm"); 
                ArchivesInfoManageDWR.saveArchivesInfo(2,archivesTypeID,true,formMap,saveArchivesInfoCallBack);            
             }else{
                $("parentNBXH").value="";
                formMap = dwr.util.getValues("inputForm"); 
                ArchivesInfoManageDWR.saveArchivesInfo(1,archivesTypeID,true,formMap,saveArchivesInfoCallBack);
             }  
           }
           if(fileType == 2){
              formMap = dwr.util.getValues("inputForm"); 
             ArchivesInfoManageDWR.saveArchivesInfo(2,archivesTypeID,true,formMap,saveArchivesInfoCallBack);
           }
     }
     function saveArchivesInfoCallBack(nbxh){
      // alert("保存成功！内部序号："+nbxh);
       operationType = "edit";
       $("NBXH").value = nbxh; 
       NBXHS.splice(NBXHS.length,0,nbxh);//向数组插入一个元素
       index = NBXHS.length-1;
      // alert("保存后:"+NBXHS);
	   setOperationStyle();
	   alert("保存成功！");
     }
     
     //DWR：更新著录信息
     function updateArchivesInfo(){
    	 var formMap;
         if(fileType == 0){
              formMap = dwr.util.getValues("inputForm"); 
            ArchivesInfoManageDWR.updateArchivesInfo(archivesTypeID,0,formMap,updateArchivesInfoCallBack);
         } else{
        	 formMap = dwr.util.getValues("inputForm"); 
             if(index == 0){   
                 ArchivesInfoManageDWR.updateArchivesInfo(archivesTypeID,1,formMap,updateArchivesInfoCallBack);
             }else{
                 ArchivesInfoManageDWR.updateArchivesInfo(archivesTypeID,2,formMap,updateArchivesInfoCallBack);
             }  
         }
     }
     function updateArchivesInfoCallBack(nbxh){
       //  alert("更新成功！内部序号："+nbxh);
         setOperationStyle();
         alert("更新成功！");
     }


//初始化///////////////////////////////////////////////////////////////////////////////////////////////
    var NBXHS;//当前内部序号集合
    var operationType; //全局变量，判断操作类型
    var fileType;//存储是文件还是案卷还是卷内
    var archivesTypeID;
    var obj;
    var index;//页面第几条索引
    var parentFlag =false;
    function init() {  
         obj = window.dialogArguments;
         //初始化全局变量
         operationType = obj.operationType;
         NBXHS = obj.NBXHS;
         fileType = obj.fileType;
         archivesTypeID = obj.archivesTypeID;
         
         index = 0;

         setOperationStyle();
         getArchivesTypeByID();

         //初始化值页面
         switch(operationType){
           case "add"://如果是新增进来的，则初始化一空页面
                      getHtmlCode(archivesTypeID,0);
                      if(fileType == 1){
                         parentFlag = true;
                         $("imgCancel").disabled=true;
                         changePic();
                      }
                      if(fileType == 0){
                    	 $("imgCancel").disabled=true;
                    	 changePic();
                      }
                      if(fileType == 2){//如果是添加卷内文件，则根据案卷内部序号找到所有的卷内文件内部序号并添加到全局数组
                      
                        findChildArchivesInfosNBXHS(NBXHS[0]);//得到卷内文件的内部序号
                        
                      }
                      break;
           case "edit"://如果是修改进来的
                      getHtmlCode(archivesTypeID,NBXHS[0]);//根据传入的内部序号初始化出页面，注意在生成页面时有案卷内部序号的会有值
	                   if(fileType == 1){//如果是案卷，得到当前案卷的所有卷内目录的内部序号
	                     parentFlag = true;
                         findChildArchivesInfosNBXHS(NBXHS[0]);//得到卷内文件的内部序号
                         window.setTimeout(function(){
                             //设置当前索引
    	                         for(var i=0;i<NBXHS.length;i++){
    	                            if(c_NBXH == NBXHS[i]){
    	                               index = i;
    	                            }
    	                         } 
    	                          setOperationStyle();
    	                          changePic();
                             },500);
	                   }else  if(fileType == 2){//如果是修改卷内文件
                         getHtmlCode(archivesTypeID,NBXHS[1]);//根据该卷内文件内部序号去生成页面，再根据案卷内部序号得到数组
                         var c_NBXH = NBXHS[1];
                         findChildArchivesInfosNBXHS(NBXHS[0]);//得到卷内文件的内部序号
                         
                         window.setTimeout(function(){
                         //设置当前索引
	                         for(var i=0;i<NBXHS.length;i++){
	                            if(c_NBXH == NBXHS[i]){
	                               index = i;
	                            }
	                         } 
	                          setOperationStyle();
	                          changePic();
                         },500);
			           }else {
			        	   setOperationStyle();
	                       changePic();
				       }
	                   break; 
           case "zujuan"://如果是组卷进来的，数组里面已经填充满了，但是NBXHS[0]不是案卷内部序号，只需生成一个空的页面就行了
           
	                   getHtmlCode(archivesTypeID,0);
	                   $("imgSave").disabled = false;
                       changePic();
	                   break; 
           case "hejuan"://如果是合卷进来的，数组里面已经填充满了，但是NBXHS[0]不是案卷内部序号，只需生成一个空的页面就行了
           
                       getHtmlCode(archivesTypeID,0);
                       $("imgSave").disabled = false;
                       changePic();
	                   break; 
           case "fenjuan"://如果是合卷进来的，数组里面已经填充满了，但是NBXHS[0]不是案卷内部序号，只需生成一个空的页面就行了
                       getHtmlCode(archivesTypeID,0);
                       $("imgSave").disabled = false;
                       changePic();
	                   break;       
         }
 	}
 	
 	//初始化页面得到html代码
 	function getHtmlCode(archivesTypeID,NBXH){
 	    //调用初始化页面方法
 	    ArchivesInfoManageDWR.getHtmlCode(archivesTypeID,NBXH,getHtmlCodeCallBack);
 	}
 	function getHtmlCodeCallBack(data){
 	    $("inputs").innerHTML = "<input type=\"hidden\"  name=\"archivesTypeID\" id=\"archivesTypeID\" value=\""+archivesTypeID+"\"/>";
 	    $("inputs").innerHTML +="<table id=\"inputFormTab\" style=\"font-size:12px;\"  align=\"center\" cellpadding=\"0\" cellspacing=\"0\" >"+data+"</table>";
 	    //setOperationStyle();
 	  //  if(operationType == "zujuan" || operationType == "hejuan" || operationType == "fenjuan"){
 	    //    $("imgSave").disabled = false;
			//changePic();
 	  //  }
 	}
 	
 	
 	
 	
//单击事件////////////////////////////////////////////////////////////////////////////////////////////////////
 	//单击《保存》按钮		
	function clickSave() {
	  switch(operationType){
	       case "add":
                      saveArchivesInfo();
                      break;
           case "edit":
	                   updateArchivesInfo();
	                   break; 
           case "zujuan":
	                   combineArchivesInfos();
	                   break; 
           case "hejuan":
                       mergeArchivesInfos();
	                   break; 
           case "fenjuan":
                       fenjuan(); 
	                   break;       
         }
		window.returnValue=1;   //向父页面传值		
	}
 	
 	//单击《添加》按钮		
	function clickAdd() {
		 $("currentPage").innerHTML = "";
	    //alert("fileType:"+fileType);
	    parentFlag = false;
		switch (fileType){
			 case 0://如果当前是对文件进行操作则清空所有表单
			 
			      //处理清空表单
			      clearInput();
			      
			      operationType = "add";
			      break;
			 case 1://如果当前树对案卷及卷内文件进行操作则清空所有表单并将parentNBXH表单设置值

                 //处理清空表单
                 clearInput();
                 
			      $("parentNBXH").value = NBXHS[0];
			      operationType = "add";
			      break;
			 case 2://如果当前树对案卷及卷内文件进行操作则清空所有表单并将parentNBXH表单设置值
			 
			      //处理清空表单
			      clearInput();
			      
			      $("parentNBXH").value = NBXHS[0];
			      operationType = "add";
			      break;
		}
		window.returnValue=1;   //向父页面传值
		setOperationStyle();
        changePic();		
	}
 	
 	//单击《原文》按钮
	function ywgl() {
		var nbxh = $("NBXH").value;
		var retval = window
				.showModalDialog(
						'ywgl.jsp?archivesTypeID='+$("archivesTypeID").value+'&NBXH='+nbxh,
						"",
						"dialogWidth:590px; dialogHeight:500px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");
	}
 	
 	//单击《删除》按钮		
	function clickDel() {
		deleteArchivesInfo();
		window.returnValue=1;   //向父页面传值		
	}
 	
 	//单击《取消》按钮
	function clickCancel() {//取得当前内部序号，并重新生成页面
		//getArchivesInfo获取档案著录信息
		getHtmlCode(archivesTypeID,NBXHS[index]);
		operationType = 'edit';
		setOperationStyle();
		window.returnValue=1;

	}

	//单击《退出》按钮
    function clickExit(){
        window.close();
    }

	// 获得档案类型对象
    function getArchivesTypeByID(){
    	ArchivesInfoManageDWR.getArchivesTypeByID(archivesTypeID,getArchivesTypeByIDCallBack);
    }
    function getArchivesTypeByIDCallBack(data){
        $("ArchivesTypeNameText").innerHTML=data.fullName+"("+data.fullCode+")";
    }

    function setCurrentPage(){
        $("currentPage").innerHTML = index+1+"/"+NBXHS.length;
    }
   </script>

</head>

<body onload="init()" style="margin:0px; background-color:#f9f9f9; text-align: center;">
	
	<table width="100%"  cellpadding="0" cellspacing="0" style="border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color:black;">
		<tr>
			<td style="background-image:url(images/bghead.gif);height:50px;" >
				<input type="image" id="imgFirst" name="imgFirst" src="images/image_first.gif"
					onmouseover="changeImage(this,'image_first2.gif')"
					onmouseout="changeImage(this, 'image_first.gif')" onclick="browsing(0)"/>
				<input type="image" id="imgPrevious" src="images/image_previous.gif"
					onmouseover="changeImage(this,'image_previous2.gif')"
					onmouseout="changeImage(this,'image_previous.gif')" onclick="browsing(1)" />
				<input type="image" ID="imgNext" src="images/image_next.gif" 
					onmouseover="changeImage(this,'image_next2.gif')"
					onmouseout="changeImage(this,'image_next.gif')"  onclick="browsing(2)"/>
				<input type="image" id="imgLast" src="images/image_last.gif" 
					onmouseover="changeImage(this,'image_last2.gif')"
					onmouseout="changeImage(this,'image_last.gif')" onclick="browsing(3)"/>
				<img src="images/separator.gif" alt="" />
				<input type="image" ID="imgAdd" name="imgAdd" src="images/image_add.gif" 
					onmouseover="changeImage(this,'image_add2.gif')"
					onmouseout="changeImage(this,'image_add.gif')" onclick="clickAdd()"/>
				<input type="image" ID="imgDel" src="images/image_delete.gif" 
					onmouseover="changeImage(this,'image_delete2.gif')"
					onmouseout="changeImage(this,'image_delete.gif')" onclick="clickDel()"/>					
				<img src="images/separator.gif" alt="" />
				<input type="image" ID="imgSave" src="images/image_save.gif"
					onmouseover="changeImage(this,'image_save2.gif')"
					onmouseout="changeImage(this,'image_save.gif')" onclick="clickSave()" />
				<input type="image" ID="imgCancel" src="images/image_cancel.gif" 
					onmouseover="changeImage(this,'image_cancel2.gif')"
					onmouseout="changeImage(this,'image_cancel.gif')" onclick="clickCancel()"/>
				<img src="images/separator.gif" alt="" />
				<input type="image" ID="imgFile" src="images/image_lookfile.gif" onclick="ywgl()" 
					onmouseover="changeImage(this,'image_lookfile2.gif')"
					onmouseout="changeImage(this,'image_lookfile.gif')"  />
				<input type="image" ID="imgExit" src="images/exitout.gif"  onclick="clickExit()"
					onmouseover="changeImage(this,'exitout2.gif')"
					onmouseout="changeImage(this,'exitout.gif')"/>
			</td>
		</tr>
	</table >
	<table width="90%"  cellpadding="0" cellspacing="0" align="center">
	  <tr>
	    <td align="left" style="font-size: 12px; color: red;">档案分类：<label id="ArchivesTypeNameText"></label></td>
	    <td align="right" style="font-size: 12px; color: red;">当前：<label id="currentPage"></label></td>
	  </tr>
	</table>
	<fieldset style="height:420px;width: 99%;font-size:12px;margin-top: 5px;">
	   <form name="inputForm" id="inputForm" method="post" style="margin: 0;padding: 0; font-size: 12px;">
			<div id="inputs" style="height:420px;width: 98%;font-size:12px; overflow: auto;">
			   
			</div>
		</form>	
	</fieldset>
</body>
</html>
