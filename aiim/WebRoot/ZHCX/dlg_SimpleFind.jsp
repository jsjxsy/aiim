<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>公共查询条件</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.form.js"></script>
	
	<link rel="stylesheet" type="text/css" href="css/common.css">
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
		//处理单击提交按钮事件
      function clickSubmit(){
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
                 if($(inputs[i]).attr("name")!='archivesTypeId'){
                	 strNameValueUrl = strNameValueUrl + "&" + $(inputs[i]).attr("name")+"=" + $(inputs[i]).attr("value");
                 }
                 
                 //验证用字符串
                 strNameValue =strNameValue + $(inputs[i]).attr("name")+":" + $(inputs[i]).attr("value") + ";";
             }
           }
         // alert(strNameValue);
          if(inputFlag == false){
              alert('请输入查询条件！');
              return false;
          }
          //DWR 调用非法输入验证方法
          IntegratedQueryAction.validateQueryInputStr(strNameValue,validateQueryInputStrBack);        
      }
      
      	//验证非法输入回调方法
		function validateQueryInputStrBack(data){
			//alert("data:"+data);
			if(data==""){
				window.returnValue = strNameValueUrl;
				window.close();
			}else{
				alert(data);
			}
		}
            
      //处理单击取消按钮事件
		function clickCancel(){
			window.close();
			return false;
		}

	
           
    </script>
</head>
<body style="margin:0px; overflow: auto;" onload="test()" >  
	<center>  
          <fieldset >
		       <form action="ZHCX/integratedQueryAction_findArchivesByCondition.action" method="post" name="conditionForm" style="margin: 0px; padding: 0px;">
		       <!-- 所选分类中的一个档案分类编号 -->
		       <input type="text" name="archivesTypeId" id="archivesTypeId" value="${requestScope.archivesTypeId}">			     
		       <table style="font-size: 12px;">
		         ${requestScope.htmlCode }
		       	 <tr>
		           <td></td>
		           <td>
		             <input type="button" value="" class="submitButton" onclick="clickSubmit()" onmouseover="changeSubmitBgImage(this,'search2.gif')" onmouseout="changeSubmitBgImage(this,'search.gif')"/>
		             <input type="button" value="" class="cancelButton" onclick="clickCancel()" onmouseover="changeSubmitBgImage(this,'cancel2.gif')" onmouseout="changeSubmitBgImage(this,'cancel.gif')" />
		           </td>
		         </tr>
		       </table>
		       </form>
		   </fieldset>
      </center>      
</body>
</html>
