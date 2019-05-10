<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%
String path = request.getContextPath();

String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
StringBuffer uu = new StringBuffer(basePath);
StringBuffer url = request.getRequestURL();
String str_url = url.substring(url.lastIndexOf("/")+1,url.length());
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>" >   
 <!--    <sx:head /> -->   
    <title>档案条目信息</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="dwr/interface/ArchivesInfoManageDWR.js"></script>
    <script type="text/javascript" src="dwr/util.js"></script>
    <script type="text/javascript" src="dwr/engine.js"></script>
    
<!-- 页面处理  -->
 <script type="text/javascript"  >
//全局变量 
   var NBXHS = new Array();
// var fatherPageNBXHs = new Array();//用于接收父页面传递过来的NBXH目录，用于上一条、下一条操作
 //var anJuanNBXHs = new Array();//用于存放当前页面的案卷目录，用于上一条、下一条操作
 //var juanNeiNBXHs = new Array();//用于存放当前的卷内目录，用于上一条、下一条操作

 //上一条下一条浏览档案信息
function browsing(offset)//offset偏移量：第一条：0；上一条：1；下一条：2；最末条：3
{	 
	 var nbxh = $("NBXH").value;//获取当前显示档案的NBXH	
	 switch(offset){
	 case 0://第一条
		 //findArchivesInfoByNBXH(fatherPageNBXHs[0]);
		 findArchivesInfoByNBXH(NBXHS[0]);
		 break;
	 case 1://上一条
		// findArchivesInfoByNBXH(fatherPageNBXHs[getIndexFromPageNBXHs(nbxh)-1]);
		 findArchivesInfoByNBXH(NBXHS[nbxh-1]);
		 break;
	 case 2://下一条
		 //findArchivesInfoByNBXH(fatherPageNBXHs[getIndexFromPageNBXHs(nbxh)+1]);
		 findArchivesInfoByNBXH(NBXHS[nbxh+1]);
		 break;
	 case 3://最末条
		 //findArchivesInfoByNBXH(fatherPageNBXHs[fatherPageNBXHs.length-1]);
		 findArchivesInfoByNBXH(NBXHS[NBXHS.length-1]);
		 break;
	 }
	 $("imgDel").disabled=false;
 	$("imgEdit").disabled=false;
 	changePic();
	// setBrowsingStyle();
}

 //添加一个nbxh到fatherPageNBXHs集合中
function addItemToFatherPageNBXHs(nbxh){
	fatherPageNBXHs.unshift(nbxh);
}

 
//设置上一条下一条...是否可用
function setBrowsingStyle(){
	var nbxh = $("NBXH").value;//获取当前显示档案的NBXH	
	var length = fatherPageNBXHs.length;	
	if(length<=1 || $("zujuanFlag").value==1){//如果只有一条记录
		$("imgNext").disabled = true;
		$("imgLast").disabled = true;
		$("imgFirst").disabled = true;
		$("imgPrevious").disabled = true;
		

	}else{//两条以上记录时
		$("imgNext").disabled = false;
		$("imgLast").disabled = false;
		$("imgFirst").disabled = false;
		$("imgPrevious").disabled = false;
		if(getIndexFromPageNBXHs(nbxh)==0){//当前为第一条记录
			$("imgFirst").disabled = true;
			$("imgPrevious").disabled = true;
		}else if(getIndexFromPageNBXHs(nbxh)==(length-1)){//当前为最后一条记录
			$("imgNext").disabled = true;
			$("imgLast").disabled = true;
		}
	}
	changePic();
	
}
//获取当前档案在fatherPageNBXHs的位置
function getIndexFromPageNBXHs(nbxh)
{	
	for(i =0;i<fatherPageNBXHs.length;i++){
		if(nbxh ==fatherPageNBXHs[i]){
			return i;
		}
	}
	return fatherPageNBXHs.length;//如果在集合中没有找到指定nbxh，就返集合中最后一个元素的位置
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
		
		if ($("imgEdit").disabled==true) {	
			$("imgEdit").src="images/image_edit3.gif";
		}
		else {
			$("imgEdit").src="images/image_edit.gif";
		}
		
		if ($("imgSave").disabled==true) {	
			$("imgSave").src="images/image_save3.gif";
		}
		else {
			$("imgSave").src="images/image_save.gif";
		}

		if ($("imgSAdd").disabled==true) {	
			$("imgSAdd").src="images/image_saveadd3.gif";
		}
		else {
			$("imgSAdd").src="images/image_saveadd.gif";
		}

		if ($("imgCancel").disabled==true) {	
			$("imgCancel").src="images/image_cancel3.gif";
		}
		else {
			$("imgCancel").src="images/image_cancel.gif";
		}

		if ($("imgAjuan").disabled==true) {	
			$("imgAjuan").src="images/image_ajuan3.gif";
		}
		else {
			$("imgAjuan").src="images/image_ajuan.gif";
		}

		if ($("imgJnei").disabled==true) {	
			$("imgJnei").src="images/image_jnei3.gif";
		}
		else {
			$("imgJnei").src="images/image_jnei.gif";
		}

		if ($("imgFile").disabled==true) {	
			$("imgFile").src="images/image_lookfile3.gif";
		}
		else {
			$("imgFile").src="images/image_lookfile.gif";
		}
	}

	//按钮重置（将所有活动按钮设为不可用）
	function resetButton(){
		$("imgFirst").disabled=true;
		$("imgPrevious").disabled=true;
		$("imgNext").disabled=true;
		$("imgLast").disabled=true;
		$("imgAdd").disabled=true;
		$("imgDel").disabled=true;
		$("imgEdit").disabled=true;
		$("imgSave").disabled=true;
		$("imgSAdd").disabled=true;
		$("imgCancel").disabled=true;
		$("imgAjuan").disabled=true;
		$("imgJnei").disabled=true;
		$("imgFile").disabled=true;
	}

//通过操作类型设置按钮显隐
	function setOperationStyle() {
		resetButton();//1：重置按钮	
		switch ($("operationType").value) {  //2：判断操作类型，并对各操作类型设置按钮可用状态
		case 'edit':		
			$("imgSave").disabled = false;
			$("imgSAdd").disabled = false;
			$("imgCancel").disabled = false;
			changePic();//3：更新颜色
			return false;
		case 'view':
//			$("imgFirst").disabled = false;
//			$("imgPrevious").disabled = false;
//			$("imgNext").disabled = false;
//			$("imgLast").disabled = false;
			setBrowsingStyle();//设置上一条下一条...的状态
			$("imgAdd").disabled = false;
			$("imgDel").disabled = false;
			$("imgEdit").disabled = false;
			//$("imgAjuan").disabled = false;
			//$("imgJnei").disabled = false;
			fileType();//控制案卷和卷内显/隐
			//$("imgFile").disabled = false;
			attachedFile();
			changePic();//3：更新颜色
			return false;
		}		
	}

//根据fileType设置按钮《原文》的显/隐
	function attachedFile(){
		if($("fileType").value=='1'){
			$("imgFile").disabled = true;
		}else{
			$("imgFile").disabled = false;
		}
	}

	
	//根据fileType设置按钮《案卷》和《卷内》的显/隐
	function fileType(){
		switch($("fileType").value){
		case '0'://文件
			$("imgAjuan").disabled=true;
			$("imgJnei").disabled=true;
			break;
		case '1'://案卷
			$("imgAjuan").disabled=true;
			$("imgJnei").disabled=false;
			break;
		case '2'://卷内
			$("imgAjuan").disabled=false;
			$("imgJnei").disabled=true;
			break;
		}
	}


	

///////////////////处理事件///////////////////////////

	//单击《原文》按钮
	function ywgl() {
		var nbxh = $("NBXH").value;
		var retval = window
				.showModalDialog(
						'ywgl.jsp?archivesTypeID=<%=request.getParameter("archivesTypeID")%>&NBXH='+nbxh,
						"",
						"dialogWidth:590px; dialogHeight:430px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");
	}


		//单击《案卷》按钮
       function clickAjuan(){    
    	   fatherPageNBXHs=anJuanNBXHs;//将浏览对象切换到案卷目录      
			$("fileType").value=1;
			var nbxh = $("parentNBXH").value;
			$("parentNBXH").value = 0;	//案卷没有parentNBXH，故设0；		
			findArchivesInfoByNBXH(nbxh);
			fileType();//根据fileType的值设置按钮《案卷》和《卷内》的显/隐
			attachedFile(); //根据fileType的值设置原文的显/隐
			changePic();
       }
       
		//单击《卷内》按钮
//		var JuanNeiNBXHS ;//卷内文件内部序号列表juanNeiNBXHs
//		var currentIndex;//当前显示的位置（用于上一条、下一条...操作）
       function clickJnei(){
    	   anJuanNBXHs = fatherPageNBXHs;
           var nbxh = $("NBXH").value; 
           $("parentNBXH").value=$("NBXH").value;
    	   	findChildArchivesInfosNBXHS(nbxh);//查询指定案卷下的NBXH列表    	   	
			$("fileType").value=2;
			
			fileType();//控制文件类型的显隐
			attachedFile();//控制原文按钮的显隐
			changePic();
        }
       
	 //单击《添加》按钮
	 	function clickAdd(){
	 	 	$("operationType").value = 'edit';
	 	 	setOperationStyle();//设置操作风格
	 	 	clearInput();				
	 	 }

		//单击《删除》按钮
		 function clickDel(){	
			if(window.confirm("确认删除?")){
				deleteArchivesInfo();	//调用DWR执行删除操作
				$("operationType").value = 'view';	
		 	 	setOperationStyle();
		 	 	clearInput();
			}
		 }
		 
		//单击《修改》按钮
		 function clickEdit(){
			 $("updateFlag").value = '1';//设置修改标识  1为更新
			$("operationType").value = 'edit';
	 	 	setOperationStyle();
		 }

		 //单击《保存》按钮		
	function clickSave() {		
		if($("zujuanFlag").value ==1 ){//组卷
			combineArchivesInfos();   //调用DWR组卷方法
			$("zujuanFlag").value=0;
			$("operationType").value = 'view';//设置操作风格值			
			setOperationStyle();			//更新操作风格
		}else if($("zujuanFlag").value ==2){//合卷		
			mergeArchivesInfos();     //调用DWR合卷方法
			$("zujuanFlag").value=0;
			$("operationType").value = 'view';			
			setOperationStyle();
		}else if($("zujuanFlag").value ==3){//分卷			
			fenjuan();			
			$("zujuanFlag").value=0;
			$("operationType").value = 'view';
			setOperationStyle();	
		}else{
			if ($("updateFlag").value == '1') {//保存状态判断：1更新/0新增
				updateArchivesInfo();//更新档案著录信息
				$("updateFlag").value ='0';//将保存状态设为新增			
			} else {
				saveArchivesInfo();//保存档案著录信息
				//addToFather();	
			}
			$("operationType").value = 'view';
			setOperationStyle();
		}	
		window.returnValue=1;   //向父页面传值	
		
	}

	//单击《存加》按钮	
	function clickSaveAdd() {
		saveArchivesInfo();//保存档案著录信息
		$("operationType").value = 'edit';
		setOperationStyle();
		clearInput();
	}

	//单击《取消》按钮
	function clickCancel() {
		//getArchivesInfo获取档案著录信息
		$("operationType").value = 'view';
		setOperationStyle();

	}

	//单击《退出》按钮
    function clickExit(){
        window.close();
      }
 	

	//清空输入项
	function clearInput() {
		$("title").value = "";
		$("secrecyID").value = "";
		$("retentionPeriodID").value = "";
		$("pageSum").value = "";
		$("formationYear").value = "";
	}

////////////// DWR ////////////////  
	function findChildArchivesInfosNBXHS(nbxh){
		//var fileType =<%=request.getParameter("fileType")%>;
		//var nbxh = $("parentNBXH").value;
		var archivesInfo;
    	archivesInfo={
      	    	 archivesTypeID:'<%=request.getParameter("archivesTypeID")%>',
      	    	 NBXH:nbxh
      	    	};
    	ArchivesInfoManageDWR.findChildArchivesInfosNBXHS(archivesInfo,findChildArchivesInfosNBXHSBack);
		}
	function findChildArchivesInfosNBXHSBack(childNBXHs){
		juanNeiNBXHs = childNBXHs;//将返回 结果赋给全局变量保存
		var nbxhOne = juanNeiNBXHs[0];
		fatherPageNBXHs = childNBXHs;//将数据传给集合fatherPageNBXHs，用于上一条、下一条操作
	   	findArchivesInfoByNBXH(nbxhOne);//显示卷内第一个文件

		}


	function getChildArchivesInfosNBXHS(nbxh){
		//var fileType =<%=request.getParameter("fileType")%>;
		//var nbxh = $("parentNBXH").value;
		var archivesInfo;
    	archivesInfo={
      	    	 archivesTypeID:'<%=request.getParameter("archivesTypeID")%>',
      	    	 NBXH:nbxh
      	    	};
    	ArchivesInfoManageDWR.findChildArchivesInfosNBXHS(archivesInfo,getChildArchivesInfosNBXHSBack);
		}
	function getChildArchivesInfosNBXHSBack(childNBXHs){
		//juanNeiNBXHs = data;//将返回 结果赋给全局变量保存
		fatherPageNBXHs = childNBXHs;//将数据传给集合fatherPageNBXHs，用于上一条、下一条操作
		//var nbxhOne = juanNeiNBXHs[0];
	//	currentIndex = 0;
	//   	findArchivesInfoByNBXH(nbxhOne);//显示卷内第一个文件
	//	alert(juanNeiNBXHs[0]);
		}

	//DWR：组卷
	 function combineArchivesInfos(){
		 var arr = window.dialogArguments;//获取从父页面传递的NBXH数组
		 var archivesInfo;    	 
    	 archivesInfo={
    	 archivesFondsID:$("archivesFondsID").value,
    	 archivesTypeID:$("archivesTypeID").value,
    	 NBXH:$("NBXH").value,
    	 title:$("title").value,
    	 secrecyID:$("secrecyID").value,
    	 retentionPeriodID:$("retentionPeriodID").value,
    	 pageSum:$("pageSum").value,
    	 formationYear:$("formationYear").value};
    	 ArchivesInfoManageDWR.combineArchivesInfos(233,arr,archivesInfo,combineArchivesInfosBack);
	}
		function combineArchivesInfosBack(datt){
			alert(datt);
		}

		//DWR：合卷
		 function mergeArchivesInfos(){		
			 window.returnValue=1;	
			 var arr = window.dialogArguments;//获取从父页面传递的NBXH数组
			 var archivesInfo;    	 
	    	 archivesInfo={
	    	 archivesFondsID:$("archivesFondsID").value,
	    	 archivesTypeID:$("archivesTypeID").value,
	    	 NBXH:$("NBXH").value,
	    	 title:$("title").value,
	    	 secrecyID:$("secrecyID").value,
	    	 retentionPeriodID:$("retentionPeriodID").value,
	    	 pageSum:$("pageSum").value,
	    	 formationYear:$("formationYear").value};
	    	 alert('合卷！');
	    	 ArchivesInfoManageDWR.mergeArchivesInfos(233,arr,archivesInfo,mergeArchivesInfosBack);
		}
			function mergeArchivesInfosBack(datt){
				alert(datt);
			}

		//DWR：分卷
		 function fenjuan(){			
			 var arr = window.dialogArguments;//获取从父页面传递的NBXH数组
			 var parentNBXH = <%=request.getParameter("parentNBXH")%>;			
			 var archivesInfo = {
	    	 archivesFondsID:$("archivesFondsID").value,
	    	 archivesTypeID:$("archivesTypeID").value,
	    	 NBXH:$("NBXH").value,
	    	 title:$("title").value,
	    	 secrecyID:$("secrecyID").value,
	    	 retentionPeriodID:$("retentionPeriodID").value,
	    	 pageSum:$("pageSum").value,
	    	 formationYear:$("formationYear").value};
	    	 alert('分卷！');
	    	 ArchivesInfoManageDWR.splitArchivesInfos(233,arr,parentNBXH,archivesInfo,splitArchivesInfosBack);
		}
		function splitArchivesInfosBack(data){
			alert(data);
		}
		
		
     //DWR：保存档案著录信息
     function saveArchivesInfo(){
         var fileType =<%=request.getParameter("fileType")%>; 
         var parentFlag=0; 
         var parentNBXH=0;
         switch(fileType){
         case 0://文件
             break;
         case 1://案卷
        	 parentFlag=1;
        	 break;
         case 2://卷内        
        	 parentNBXH=<%=request.getParameter("parentNBXH")%>;
        	 break;
        default:
            alert('fileType='+fileType+' 该类型没有定义！');
        	break;
         }   
    	 var archivesInfo;    	 
    	 archivesInfo={
    	 archivesFondsID:$("archivesFondsID").value,
    	 archivesTypeID:$("archivesTypeID").value,
    	 parentFlag:parentFlag,
    	 parentNBXH:parentNBXH,
    	 NBXH:$("NBXH").value,
    	 title:$("title").value,
    	 secrecyID:$("secrecyID").value,
    	 retentionPeriodID:$("retentionPeriodID").value,
    	 pageSum:$("pageSum").value,
    	 formationYear:$("formationYear").value};
    	 var fileType = $("fileType").value;
    	 ArchivesInfoManageDWR.saveArchivesInfo(213,fileType,archivesInfo,saveArchivesInfoBack);
    	// service.saveArchivesInfo(213,archivesInfo,saveArchivesInfoBack);
     }
     function saveArchivesInfoBack(nbxh){//DWR回调函数：保存档案著录信息
    	 var fileType =<%=request.getParameter("fileType")%>; 
    	 if(fileType!==2 && $("updateFlag").value==0){//如果是新增的文件或案卷（不包括卷内，不包括修改）
    		 addItemToFatherPageNBXHs(nbxh);//将nbxh添加到fatherPageNBXHs集合中     
    		 $("NBXH").value = nbxh;//将NBXH保存至页面
         }
    	 setBrowsingStyle();
       
     }


   //DWR：更新档案著录信息
     function updateArchivesInfo(){
    	 var archivesInfo;    	 
    	 archivesInfo={
   	    	 archivesFondsID:$("archivesFondsID").value,
   	    	 archivesTypeID:$("archivesTypeID").value,
   	    	 NBXH:$("NBXH").value,
   	    	 title:$("title").value,
   	    	 secrecyID:$("secrecyID").value,
   	    	 retentionPeriodID:$("retentionPeriodID").value,
   	    	 pageSum:$("pageSum").value,
   	    	 formationYear:$("formationYear").value};
    	 ArchivesInfoManageDWR.updateArchivesInfo(110,archivesInfo,updateArchivesInfoBack);
   		 //ArchivesInfoManageAction.saveArchivesInfo(213,archivesInfo,saveArchivesInfoBack);   		  
     }
     function updateArchivesInfoBack(date1){//DWR回调函数：保存档案著录信息
       alert(date1);
     }


     //DWR：删除档案著录信息
     function deleteArchivesInfo(){  
         var fileType = $("fileType");     
    	 var archivesInfo={
   	    	 archivesFondsID:$("archivesFondsID").value,
   	    	 archivesTypeID:$("archivesTypeID").value,
   	    	 NBXH:$("NBXH").value,
   	    	 title:$("title").value,
   	    	 secrecyID:$("secrecyID").value,
   	    	 retentionPeriodID:$("retentionPeriodID").value,
   	    	 pageSum:$("pageSum").value,
   	    	 formationYear:$("formationYear").value};
    	 ArchivesInfoManageDWR.deleteArchivesInfo(310,fileType,archivesInfo,deleteArchivesInfoBack);
     }
     function deleteArchivesInfoBack(nbxh1){//DWR回调函数：保存档案著录信息
       var nbxh = $("NBXH").value;//获取当前显示档案的NBXH
       if(getIndexFromPageNBXHs(nbxh)!=	fatherPageNBXHs.length-1){//如果不是最后一条记录时
    	   browsing(2);//下一条
         }else{
        	$("imgDel").disabled=true;
        	$("imgEdit").disabled=true;
        	//changePic();        	
         }
       
       fatherPageNBXHs.splice(getIndexFromPageNBXHs(nbxh),1);//从fatherPageNBXHs中删除指定NBXH 
       if(fatherPageNBXHs.length==0){    	   
   		   $("imgFile").disabled = true;
   			//changePic();    		
       }     
       changePic(); 
     }
     
	//DWR：通过内部序号查找档案著录信息
     function findArchivesInfoByNBXH(nbxh){
    	var archivesInfo={
      	    	 archivesTypeID:'<%=request.getParameter("archivesTypeID")%>',
      	    	 NBXH:nbxh
      	    	};
    	ArchivesInfoManageDWR.findArchivesInfoByNBXH(archivesInfo,findArchivesInfoByNBXHBack);
     }
     function findArchivesInfoByNBXHBack(archivesInfo){//回调：通过内部序号查找档案著录信息
    	 clearInput();//清除当前显示数据
         var pFlag= false;
         if($("fileType").value==0){//当前为文件
             pFlag=true;
         }else if($("fileType").value==1){//当前显示案卷        
             pFlag=true;
             $("parentNBXH").value=0;        	
         }else if($("fileType").value==2){//当前显示卷内
             pFlag=true;             
         }
         //在页面上显示各公共数据项
         if(pFlag){
            $("NBXH").value = archivesInfo.NBXH;
        	$("archivesFondsID").value=archivesInfo.archivesFondsID;
      		$("title").value = archivesInfo.title;
      		$("secrecyID").value = archivesInfo.secrecyID;
      		$("retentionPeriodID").value = archivesInfo.retentionPeriodID;
      		$("pageSum").value = archivesInfo.pageSum;
      		$("formationYear").value = archivesInfo.formationYear;
      		setBrowsingStyle();
         }else{
			alert('没有找到该档案信息！');
         }
         
		
     }

     function init() {    
    	 fatherPageNBXHs = window.dialogArguments;//初始化全局变量fatherPageNBXHs，保存父页面传递过来的NBXH列表
    	 if('2'=='<%=request.getParameter("fileType") %>'){//如果是从卷内目录进来的话
			anJuanNBXHs = window.dialogArguments;//将传递进来的案卷NBXHs保存到全局变量anJuanNBXHs
			getChildArchivesInfosNBXHS(<%=request.getParameter("parentNBXH") %>);
         }
        
      if('0'!='<%=request.getParameter("NBXH")%>'){	//当NBXH为不为0时(为查看状态或编辑状态),DWR调用查找著录信息，并显示到页面
    	  var nbxh = <%=request.getParameter("NBXH")%>;		
    	  findArchivesInfoByNBXH(nbxh);
      }
 		setOperationStyle();//根据传入参数，设置界面风格(view,edit...)
 	  if('1'=='<%=request.getParameter("zujuanFlag")%>'){//如果是组卷，就设《保存》为可用，其它按钮都失效
 		 resetButton();
 		 $("imgSave").disabled = false;
		 changePic();	
 	  }
 	  if('2'=='<%=request.getParameter("zujuanFlag")%>'){//如果是合卷，就设《保存》为可用，其它按钮都失效
 		 resetButton();
 		 $("imgSave").disabled = false;
		 changePic();	
 	  }
 	 if('3'=='<%=request.getParameter("zujuanFlag")%>'){//如果是分卷，就设《保存》为可用，其它按钮都失效
 		 resetButton();
 		 $("imgSave").disabled = false;
		 changePic();	
 	  }
 	}
   </script>

</head>

<body onload="init()" style="margin:0px; background-color:#f9f9f9;">
	
	<table width="100%"  cellpadding="0" cellspacing="0">
		<tr>
			<td style="background-image:url(images/bghead.gif);height:50px;" >
				<input type="image" id="imgFirst" name="imgFirst" src="images/image_first.gif"
					onmouseover="changeImage(this,'image_first2.gif')"
					onmouseout="changeImage(this, 'image_first.gif')" onclick="browsing(0)" />
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
				<input type="image" ID="imgEdit" src="images/image_edit.gif" 
					onmouseover="changeImage(this,'image_edit2.gif')"
					onmouseout="changeImage(this,'image_edit.gif')" onclick="clickEdit()" />					
				<img src="images/separator.gif" alt="" />
				<input type="image" ID="imgSave" src="images/image_save.gif"
					onmouseover="changeImage(this,'image_save2.gif')"
					onmouseout="changeImage(this,'image_save.gif')" onclick="clickSave()" />
				<input type="image" ID="imgSAdd" src="images/image_saveadd.gif"
					onmouseover="changeImage(this,'image_saveadd2.gif')"
					onmouseout="changeImage(this,'image_saveadd.gif')" onclick="clickSaveAdd()" />
				<input type="image" ID="imgCancel" src="images/image_cancel.gif" 
					onmouseover="changeImage(this,'image_cancel2.gif')"
					onmouseout="changeImage(this,'image_cancel.gif')" onclick="clickCancel()"/>
				<img src="images/separator.gif" alt="" />				
				<input type="image" ID="imgAjuan" src="images/image_ajuan.gif" 
					onmouseover="changeImage(this,'image_ajuan2.gif')"
					onmouseout="changeImage(this,'image_ajuan.gif')" onclick="clickAjuan()" />
				<input type="image" ID="imgJnei" src="images/image_jnei.gif" 
					onmouseover="changeImage(this,'image_jnei2.gif')"
					onmouseout="changeImage(this,'image_jnei.gif')" onclick="clickJnei()"/>
				<img src="images/separator.gif" alt="" />
				<input type="image" ID="imgFile" src="images/image_lookfile.gif" onclick="ywgl()" 
					onmouseover="changeImage(this,'image_lookfile2.gif')"
					onmouseout="changeImage(this,'image_lookfile.gif')"  />
				<input type="image" ID="imgExit" src="images/exitout.gif"  onclick="clickExit()"
					onmouseover="changeImage(this,'exitout2.gif')"
					onmouseout="changeImage(this,'exitout.gif')" />
			</td>
		</tr>
	</table>
	<br/>	
	<input type="hidden" name="zujuanFlag" id="zujuanFlag" value="<%=request.getParameter("zujuanFlag") %>"><!-- 组卷标识 -->
	<input type="hidden" name="updateFlag" id="updateFlag" > 	<!-- 更新标识  -->
	<input type="hidden" name="operationType" id="operationType" value="<%=request.getParameter("operationType") %>"> <!-- 查看/编辑 -->
<form name="inputForm" method="post" style="margin: 0;padding: 0;">
	<input type="hidden" name="parentNBXH" id="parentNBXH" value="<%=request.getParameter("parentNBXH") %>"> <!-- 所属案卷的内部序号 -->	
	<input type="hidden" name="NBXH" id="NBXH" value="<%=request.getParameter("NBXH") %>" /> <!-- 内部序号 -->
	<input type="hidden" name="parentFlag" id="parentFlag" value="0"><!--案卷标志 -->
	<input type="hidden" name="fileType" id="fileType" value="<%=request.getParameter("fileType") %>" ><!-- 文件类型：0、文件；1、案卷；2、卷内 -->
	<input type="hidden" name="workFlowStatus" id="workFlowStatus" value="0"><!--工作流状态 -->
	<input type="hidden" name="strNBXHS" id="strNBXHS" ><!-- 字符串：内部序号集合 -->
	<div style="height:430px;width: 100%; overflow: auto;">
		  <table id="inputs" style="font-size:12px;" align="center" cellpadding="0" cellspacing="0" >
		    <tr height="30px;">
			    <td width="60px" >全宗号</td>
				<td>
					<select style="width:500px;" name="archivesFondsID" id="archivesFondsID" >
						<option value="0">请选择</option>
						<option value="G01">G01</option>					
					</select>
				</td>
			</tr>
			<tr height="30px;">
			  <td>分类号</td>
			  <td>
			     <input type="text" style="width: 500px;" name="archivesTypeID" id="archivesTypeID" value="<%=request.getParameter("archivesTypeID")%>"  />
			  </td>
			</tr>
			<tr height="30px;">
			  <td>分类号</td>
			  <td>
			     <input type="text" style="width: 500px;" name="archivesTypeID" id="archivesTypeID" value="<%=request.getParameter("archivesTypeID")%>"  />
			  </td>
			</tr>
			<!--<tr >
				<td>
				<SPAN style="color: red">题名</SPAN>
				</td>
				<td>			
					<textarea rows="3" style="width: 468px" name="title" id="title"></textarea>				
				</td>
			</tr>
		
			<tr>		
				<td>
				密级
				</td>
				<td>
					<select style="width: 196px; margin-right:10px;" name="secrecyID" id="secrecyID">
						<option value="0">	</option>
						<option value="1">开放</option>
						<option value="2">公开</option>
						<option value="3">内部</option>					
					</select>
					保管期限<select style="width: 193px; margin-right:10px; margin-left:5px;" name="retentionPeriodID" id="retentionPeriodID">
						<option value="0">
						</option>
						<option value="1">永久
						</option>
						<option value="2">长期
						</option>
						<option value="3">短期
						</option>
					</select>			
				</td>
			</tr>
			
			<tr style="height:10px;">	
			</tr>
			
			<tr >
				<td >
					页数
				</td>
				<td>
					<input type="text" style="width: 195px; margin-right:10px;" value="0" name="pageSum" id="pageSum" />				
					形成日期<input type="text" id="formationYear" style="margin-left:5px; width: 176px;" name="formationYear" /><img style="margin-right:20px;cursor: pointer"  src="images/dropdownTime.gif" onclick="PopUpCalendar('formationYear',true)"  /> 
		
				</td>
			</tr>
			
			<tr style="height:10px;">	
			</tr>
		
			
			<tr>
				<td>备考表
				</td>
				<td>
					<textarea rows="3" style="width: 469px"></textarea>
				</td>
			</tr>
			
			<tr style="height:10px;">	
			</tr>
			<tr >
				<td>
					备注
				</td>
				<td>
					<textarea rows="3" style="width: 468px"></textarea>
				</td>
			</tr>
			
			<tr >
				<td>
					著录人
				</td>
				<td>
					<input type="text" style="width: 180px; margin-right:20px;" value="张三"  />
				</td>
			</tr>  -->
		</table>
	</div>
</form>	
</body>
</html>

