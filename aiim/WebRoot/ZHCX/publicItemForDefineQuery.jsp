<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>获取公共可查询数据项</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.form.js"></script>
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/common.css">
	<script type="text/javascript" src="js/common.js"></script>
	
	<script type="text/javascript" src="dwr/interface/IntegratedQueryAction.js"></script>
	<script type="text/javascript" src="dwr/util.js"></script>
	<script type="text/javascript" src="dwr/engine.js"></script>
	
    <script type="text/javascript">
      var obj = window.dialogArguments;
      function test(){
         IntegratedQueryAction.getFindHtmlCodeBuyArchivesTypeIds(obj.archivesTypes,show);
      }
      function show(data){
        $("#conditions").append(data);
      }
      
      function returnHtmlCode(){
         window.returnValue = $("#conditions").children("tbody").html();
         window.close();
      }
      
      var strNameValueUrl = "";//用于URL传递数据

      //单击取消按钮时关闭窗口
      function clickCancel(){
          window.close();
      }
      
	//处理单击保存按钮事件
      function clickSubmit(){
          //获取自定义条件名称
    	  var queryName = window.parent.document.getElementById('queryNameTemp').value;
    	  $("#queryName").val(queryName);
    	  strNameValueUrl = "";
    	  var strNameValue = "";//用于验证
          var inputFlag = false;
          var inputs = $(":input");         
          for(var i = 0;i<inputs.length;i++){
             if($(inputs[i]).attr("type")!='button'){
                 //判断是否有输入
                 if($(inputs[i]).attr("value")!="" && $(inputs[i]).attr("name")!='archivesTypeId'){
					inputFlag = true;//设置输入标志
                 }
                 
                 //生成URL参数传输串
                 if($(inputs[i]).attr("name")!='archivesTypeId' && $(inputs[i]).attr("name")!='archivesTypeIdstr' && $(inputs[i]).attr("name")!='queryName'){
                	 strNameValueUrl = strNameValueUrl + "&" + $(inputs[i]).attr("name")+"=" + $(inputs[i]).attr("value");
                 }
                 if($(inputs[i]).attr("name")!='archivesTypeIdstr' && $(inputs[i]).attr("name")!='queryName'){
                	 //验证用字符串
                     strNameValue =strNameValue + $(inputs[i]).attr("name")+":" + $(inputs[i]).attr("value") + ";";
                 }
             }
           }

          if(inputFlag == false){
              alert('请输入查询条件！');
              return false;
          }
          //DWR 调用非法输入验证方法
          IntegratedQueryAction.validateQueryInputStr(strNameValue,validateQueryInputStrBack);        
      }
      
      	//验证非法输入回调方法
		function validateQueryInputStrBack(data){
			if(data==""){
				window.returnValue = strNameValueUrl;				
				$("#conditionForm").ajaxSubmit({
	            url:"ZHCX/integratedQueryAction_addUserDefinedQuery.action",
			    success:function(data){
				    window.returnValue = "保存成功！";
				    alert(data);			
				    if("添加成功！"==data){
					    window.close();
					}	
			    },
			    error:function(XMLHttpRequest, textStatus){alert(textStatus+","+XMLHttpRequest.status);}	     
		     });		
			}else{//当返回值不为空，即出现错误，显示错误信息
				alert(data);
			}
		}
                
    </script>
</head>
<body style="margin:0px; overflow: auto;" onload="test()" >  
	<center>  
          <fieldset >
		       <form action="ZHCX/integratedQueryAction_addUserDefinedQuery.action" method="post" name="conditionForm" id="conditionForm" style="margin: 0px; padding: 0px;">
			       <!-- 所选分类中的一个档案分类编号 -->
			       <input type="hidden" name="archivesTypeId" id="archivesTypeId" value="${requestScope.archivesTypeId}">	
			       <input type="hidden" name="archivesTypeIdstr" id="archivesTypeIdstr" value="${requestScope.archivesTypeIdstr}">		
			       <input type="hidden" name="queryName" id="queryName" value="">	     
			       <table style="font-size: 12px;">
			         ${requestScope.htmlCode }
			       	 <tr>
			           <td></td>
			           <td>
			             <input type="button" value="" class="saveButton" onclick="clickSubmit()" onmouseover="changeSubmitBgImage(this,'save2.gif')" onmouseout="changeSubmitBgImage(this,'save.gif')"/>
			            <input type="button" value="" class="cancelButton" onclick="clickCancel()" onmouseover="changeSubmitBgImage(this,'cancel2.gif')" onmouseout="changeSubmitBgImage(this,'cancel.gif')"/>
			           </td>
			         </tr>
			       </table>
		       </form>
		   </fieldset>
      </center>      
</body>
</html>
