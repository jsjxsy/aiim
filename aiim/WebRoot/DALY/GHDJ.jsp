<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>归还登记</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<link href="css/common.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript" src="js/jquery.js"></script>
		
		<script type="text/javascript" src="dwr/interface/ArchivesUseAction.js"></script>
	    <script type="text/javascript" src="dwr/util.js"></script>
	    <script type="text/javascript" src="dwr/engine.js"></script>
		
        <script type="text/javascript">
           $(document).ready(function(){
            $("#delAll").bind('click',function(){
                $("#showTable").children("tbody").children().remove();
            });
              //添加
		    $("#add").bind('click',function(){
		        $.ajax({
		           url:"json/archivesUseAction_renewAndTeturn.action",
		           type:"post",
		           data:"barcode="+$('#barcode').val()+"&type=GH",
		           success:function(date){
		              if(date == null || date ==""){
		                 alert("没有找到数据！");
		              }else{
		                 var archivesUseInfo = eval('('+date+')');
		                 var firstTr = $("#showTable").children("tbody").children().first();
		                  //获取颜色
		                 var trColor = '#e0edff';    
		                 if(firstTr.attr("bgcolor")=='#e0edff' && firstTr != null){
		                    trColor = "#eef5ff";
		                 }
		                 //避免重复添加
		                 
		                 var archivesIDs = $("#showTable").children("tbody").children().children("td:nth-child(1)");
		                 var pFlag = true;
		                 $.each(archivesIDs,function(index,archivesID){
			                   if($(archivesID).text() == archivesUseInfo.archivesID){
			                       pFlag = false;
				                   alert("列表中以存在该档案！");
				                   return false;
				                 }
		                 });
		                 if(pFlag){
		                    $("#showTable").children("tbody").prepend("<tr bgcolor='"+trColor+"'>"+
												        "<td align='center' height=\"25p\">"+archivesUseInfo.archivesID+"</td>"+
												        "<td>"+archivesUseInfo.title+"</td>"+
												        "<td>"+archivesUseInfo.userRealName+"</td>"+
												        "<td>"+archivesUseInfo.purpose+"</td>"+
												        "<td>"+archivesUseInfo.useDate+"</td>"+
												        "<td>"+archivesUseInfo.returnDate+"</td>"+
												        "<td>"+archivesUseInfo.managerUserName+"</td>"+
												        "<td align='center'>"+archivesUseInfo.pageSum+"</td>"+
												      "</tr>");
		                 }
		              }
		           },
		           error:function(XMLHttpRequest, textStatus){
		              alert(textStatus+","+XMLHttpRequest.status);
		           }
		        });
		    });
           });


           var perArchivesBarcode = "";//保存前一次续借登记的档案条形码,处理重复续借操作
       	//续借登记
           function returnArchives(){
               var daysNum = $("#daysNum").val();
               var archivesBarcode = $("#archivesBarcode").val();
               if(archivesBarcode==""){
                   alert('请输入条形码！');
                   return false;
               }
               if(perArchivesBarcode==archivesBarcode){
                  	alert('您已经成功归还档案，请不要重复操作！');
                  	return false;
               }
               
           	ArchivesUseAction.returnArchives(archivesBarcode,{
              	 callback:function(archivesUseOutInfo){
           		perArchivesBarcode = archivesBarcode;
           		 var firstTr = $("#showTable").children("tbody").children().first();
	                  //获取颜色
	                 var trColor = '#e0edff';    
	                 if(firstTr.attr("bgcolor")=='#e0edff' && firstTr != null){
	                    trColor = "#eef5ff";
	                 }
	            var useTypeText = (archivesUseOutInfo.borrowFlag==1)? '借出':'查看';
           		var dat = archivesUseOutInfo.shouldReturnDate;
           		//动态向Table中添加续借过的档案
        		 $("#showTable").children("tbody").prepend(
	              "<tr bgcolor='"+trColor+"'>"+
			        "<td  height=\"20px\">"+archivesUseOutInfo.archivesID+"</td>"+
			        "<td>"+archivesUseOutInfo.title+"</td>"+
			        "<td>"+archivesUseOutInfo.archivesBarcode+"</td>"+
			        "<td>"+archivesUseOutInfo.archivesUseRegister.archivesUsePersonInfo.name+"</td>"+
			        "<td>"+useTypeText+"</td>"+			 
			        "<td>"+getFormateDate(archivesUseOutInfo.archivesUseRegister.useDate)+"</td>"+			        
			        "<td>"+dat.getFullYear()+'-'+(1+dat.getMonth())+'-'+dat.getDate()+"</td>"+	
			      "</tr>");
             	 
             	 },
             	exceptionHandler:function(message){
                  	alert(message);
                }
                  });
           }

       	//日期格式化
           function getFormateDate(dat){
               return dat.getFullYear()+'-'+(1+dat.getMonth())+'-'+dat.getDate();
           }

       		//控制打印控件的显示/隐藏
           function showHidePrint(){
              // document.getElementById("printByDate").display = none;
             if($("#printByDate").css("display")== "none"){
            	 $("#printByDate").css("display","inline");
             }else{
            	 $("#printByDate").css("display","none");
             }
               
               
           }

           //根据档案归还日期范围，打印馆藏位置
           function printArchivesInfoAddress(){
               alert('打印馆藏位置');
           }

           //处理证件号码的回车事件
  		 function archivesBarcodeEnterEnvent(){
  			 if(event.keyCode=='13'){
  				 $("#returnArchives").click();
  		     }			 
  	     }
        </script>
	</head>

	<body>
	<div id="location" style="margin-right: 2px; width: 50%; display:block; float: right; margin-top: 0px; color: blue; text-align: right;">
	  		<font style="font-size: 12px;"><b>当前位置：</b>利用管理&nbsp;&gt;&gt;&nbsp;&nbsp;归还登记</font>
    </div>
  
	 <div >
		&nbsp;&nbsp;<span style="font-size: 14px;">档案条码:</span>
		<input type="text" id="archivesBarcode" name="archivesBarcode"  onkeydown="archivesBarcodeEnterEnvent()" />
		<input id="returnArchives" type="button" value="确   定" onclick="returnArchives()" />		
	</div>	
		<hr style="margin: 0;padding: 0;color: #104da6"/>	
          &nbsp;&nbsp;<input type="button" class="button" id="delAll" value="清    空"/>
          &nbsp;&nbsp;<input type="button" class="button" id="button1" onclick="showHidePrint()" value="打印档案位置"/>
          
          <div id="printByDate" style=" background-color:#EEEEEE;  font-size: 12px; display: none; margin: 0;padding: 0;border: 1px #104da6 solid;">
	          <span style="color:red;">请选择档案归还时间范围(格式：yyyy-MM-dd)：</span>
	          <input type="text" name="beginDate"/>到<input type="text" name="endDate"/>
	          <input type="button" value="打印" onclick="printArchivesInfoAddress()" >&nbsp;&nbsp;
          </div>  
          <table width="99%" cellspacing="0" cellpadding="0" style="margin:0px;" align="center">
			<tr class="bgTitle">
				<td style="height:25px" class="borderTop">
					<table class="tabletop" width="100%" cellspacing="0" cellpadding="0">
			            <tr>
			                <td class="tableTitle">列表</td>
			                <td></td>							                	
			            </tr>
				    </table>							
					<table id="showTable" width="99%" align="center" style="margin:0px; border: #104da6 1px solid;" cellspacing="1px" cellpadding="0px" >						
						<thead class="tableHead">
							<tr class="bgTitle">								
								<th align="center">档号</th>
								<th>题名</th>
								<th>条形码</th>
								<th>利用人</th>								
								<th>利用方式</th>
								<th>借出日期</th>
								<th>应还日期</th>
							</tr>
						</thead>
						<tbody id="ghlb">
							<!-- 数据显示区  -->
						</tbody>
					</table>		
				</td>
			</tr>
		</table>	
	</body>
</html>
