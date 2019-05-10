<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <base href="<%=basePath%>" target="_self"/>   
    
    <title>收文登记</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="dwr/interface/OfficialArchivesInfoManageDWR.js"></script>
   <script type="text/javascript" src="dwr/util.js"></script>
   <script type="text/javascript" src="dwr/engine.js"></script>
    <script type="text/javascript">
    	//通过检查按钮的disable属性改变图片颜色
	function changePic() {
		if ($("imgAdd").disabled==true) {	
			$("imgAdd").src="images/image_add3.gif";
		}
		else {
			$("imgAdd").src="images/image_add.gif";
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
	
   
	
    //初始化///////////////////////////////////////////////////////////////////////////////////////////////
    var NBXHS;//当前内部序号集合
    var operationType; //全局变量，判断操作类型
   // var fileType;//存储是文件还是案卷还是卷内
    var officialArchivesTypeID;
    var obj;
    var index;//页面第几条索引
    var parentFlag =false;
    function init() {  
         obj = window.dialogArguments;
         //初始化全局变量
         operationType = obj.operationType;
         NBXHS = obj.NBXHS;
         officialArchivesTypeID = obj.officialArchivesTypeID;
         index = 0;
         
         //初始化值页面
         switch(operationType){
           case "add"://如果是新增进来的，则初始化一空页面
			  getHtmlCode(officialArchivesTypeID,0);
			  break;
		  case "edit"://如果是修改进来的
			  getHtmlCode(officialArchivesTypeID,NBXHS);//根据传入的内部序号初始化出页面，注意在生成页面时有案卷内部序号的会有值
         	  break; 
 	    }
 	
 	//初始化页面得到html代码
 	function getHtmlCode(officialArchivesTypeID,NBXH){
 	    //调用初始化页面方法
 	    OfficialArchivesInfoManageDWR.getHtmlCode(officialArchivesTypeID,NBXH,getHtmlCodeCallBack);
 	}
 	
 	function getHtmlCodeCallBack(data){
 	    $("inputs").innerHTML = "<input type=\"hidden\"  name=\"officialArchivesTypeID\" id=\"officialArchivesTypeID\" value=\""+officialArchivesTypeID+"\"/>";
 	    $("inputs").innerHTML +="<table id=\"inputFormTab\" style=\"font-size:12px;\"  align=\"center\" cellpadding=\"0\" cellspacing=\"0\" >"+data+"</table>";
 	   // setOperationStyle();
 	}
 }
 
 
 	function clickSave() {
	  switch(operationType){
	       case "add":
                      saveOfficialArchivesInfo();
                      break;
           case "edit":
	                   updateOfficialArchivesInfo();
	                   break; 
         }
		window.returnValue=1;   //向父页面传值		
	}
 	
 	//单击《添加》按钮		
	function clickAdd() {
	    parentFlag = false;
		switch (0){
			 case 0://如果当前是对文件进行操作则清空所有表单
			      //处理清空表单
			      clearInput();
			      operationType = "add";
			      break;
		}
		window.returnValue=1;   //向父页面传值		
	}
 	
 	//单击《原文》按钮
	function ywgl() {
		var nbxh = $("NBXH").value;
		var retval = window
				.showModalDialog(
						'ywgl.jsp?officialArchivesTypeID='+$("officialArchivesTypeID").value+'&NBXH='+nbxh,
						"newwindow",
						"dialogWidth:590px; dialogHeight:500px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");
	}
 	
 	
 	//单击《取消》按钮
	function clickCancel() {//取得当前内部序号，并重新生成页面
    //   $("input").each(function() {
	//  	 this.value = "";
	//   }); 
   	$("NBXH").value = "";             
	}

	//单击《退出》按钮
    function clickExit(){
        window.close();
    }


	 <%--       通过操作类型设置按钮显隐 --%>
		function setOperationStyle() {
			resetButton();//1：重置按钮	
			switch (operationType) {  //2：判断操作类型，并对各操作类型设置按钮可用状态
			case 'add':
				$("imgSave").disabled = false;
				$("imgCancel").disabled = false;
				//$("imgCancel").disabled = false;
				changePic();//3：更新颜色
				break;
			case 'edit':
				$("imgAdd").disabled = false;
				$("imgSave").disabled = false;
				$("imgCancel").disabled = false;
				$("imgFile").disabled = false;
				changePic();//3：更新颜色
				break;
			}	
			changePic();	
		}
		
		<%--	//按钮重置（将所有活动按钮设为不可用）--%>
	    function resetButton(){
			$("imgAdd").disabled=true;
			$("imgSave").disabled=true;
			$("imgCancel").disabled=true;
			$("imgFile").disabled=true;
		}
		//清空输入项
		function clearInput() {
			$("NBXH").value = "";
		}
		
		
		 //DWR：保存档案著录信息
	     function saveOfficialArchivesInfo(){
	          var formMap;
	              formMap = dwr.util.getValues("inputForm"); 
	              OfficialArchivesInfoManageDWR.saveOfficialArchivesInfo(officialArchivesTypeID,formMap,saveOfficialArchivesInfoCallBack);
	       
	     }
	     
	     function saveOfficialArchivesInfoCallBack(nbxh){
	       alert("保存成功！内部序号："+nbxh);
	       operationType = "edit";
	       $("NBXH").value = nbxh; 
	       NBXHS.splice(NBXHS.length,0,nbxh);//向数组插入一个元素
	       index = NBXHS.length-1;
	       alert("保存后:"+NBXHS);
		   setOperationStyle();
	     }
	     
	     //DWR：更新著录信息
	     function updateOfficialArchivesInfo(){
	    	 var formMap;
	    	  formMap = dwr.util.getValues("inputForm"); 
	    	  OfficialArchivesInfoManageDWR.updateOfficialArchivesInfo(officialArchivesTypeID,formMap,updateOfficialArchivesInfoCallBack);
	     }
	     function updateOfficialArchivesInfoCallBack(nbxh){
	         alert("更新成功！内部序号："+nbxh);
	         setOperationStyle();
	     }
  </script>
  </head>

<body onload="init()" style="margin:0px; background-color:#f9f9f9;">
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
				<td style="background-image:url(images/bghead.gif);height:50px;" >&nbsp;
				<input type="image" ID="imgAdd" name="imgAdd" src="images/image_add.gif" 
					onmouseover="changeImage(this,'image_add2.gif')"
					onmouseout="changeImage(this,'image_add.gif')" onclick="clickAdd()"/>
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
					onmouseout="changeImage(this,'exitout.gif')" />
			</td>
		</tr>
	</table>
	<br/>
	  <form name="inputForm" id="inputForm" method="post" style="margin: 0;padding: 0; font-size: 12px;">
	   <div id="inputs" style="height:445px;width: 100%;font-size:12px; overflow: auto; margin-top: 5px;">
	</div>
	</form>	
</body>
</html>
