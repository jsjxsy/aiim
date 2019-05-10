<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>批量缓发登记</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<script src="js/jquery.js" type="text/javascript"></script>
<SCRIPT src="js/jqueryprogressbar.js" type="text/javascript"></SCRIPT>
	
<SCRIPT type=text/javascript>
    var pct=0;
    var handle=0;
    function update(){
            $("#progressbar").reportprogress(++pct);//pct设置进度条的当前长度
            if(pct==100){                           //设定总长度值
                clearInterval(handle);
            //    $("#run").val("start");
                pct=0;
            }
    }
    jQuery(function($){
        $("#run").click(
            function(){    
            	$("#uploadProgress").css('display','block');
                    handle=setInterval("update()",100);  //设定增长的时间
                  //  this.value="stop";               
            }
        );
        $("#reset").click(function(){
            pct=0;
            $("#progressbar").reportprogress(0);
        });
        $("#newFile").click(function(){
            pct=0;
            $("#uploadProgress").css('display','none');
            clearInterval(handle);
            $("#progressbar").reportprogress(0);
        });
    });
</SCRIPT>

<STYLE>
    #progressbar {
        BORDER-RIGHT: #9abcf7 1px solid; 
        BORDER-TOP: #9abcf7 1px solid; 
        BORDER-LEFT: #9abcf7 1px solid; 
        WIDTH: 200px;
        COLOR: black;
        BORDER-BOTTOM: #9abcf7 1px solid;
        POSITION: relative; 
        HEIGHT: 20px
    }
    #progressbar DIV.progress {
        OVERFLOW: hidden; 
        WIDTH: 0px; 
        POSITION: absolute; 
        HEIGHT: 100%; 
        BACKGROUND-COLOR: blue;
    }
    #progressbar DIV.progress .text {
        COLOR: white; 
        POSITION: absolute; 
        TEXT-ALIGN: center; FONT-SIZE:larger;
    }
    #progressbar DIV.text {
        WIDTH: 100%; 
        POSITION: absolute; 
        HEIGHT: 100%; 
        TEXT-ALIGN: center; 
        FONT-SIZE:larger;
    }
</STYLE>

  </head>
  
  <body>
    <span style="font-size: 12px; margin-left: 5px;margin-top: 10px;">选择文件</span><input type="file" id="newFile">
    <input type="button" value="上传" id="run">
	<input type="button" value="更新"><br/>
	<div style="display: none;height: 20px;" id="uploadProgress" ><span style="font-size: 12px;height:80%; margin-left: 5px;">上传进度</span><div id="progressbar" style="display: inline;"></div></div>
	<div id="updateResult" style="display: none"><span></span></div>
	
  </body>
</html>
