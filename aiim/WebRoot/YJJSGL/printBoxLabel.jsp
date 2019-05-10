<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>续借归还</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<link href="css/common.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript">
           function renewAndTeturn(type){
              $("#type").val(type);
              document.form1.submit();
           }
           $(document).ready(function(){
           
              //添加
		    $("#add").bind('click',function(){
		        if($('#archivesBoxBarcode').val() == ''){
		           alert('请输入盒条码！');
		        }else{
			        $.ajax({
			           url:"json/YJJSAction_getArchivesBoxLabel.action",
			           type:"post",
			           data:"archivesBoxBarcode="+$('#archivesBoxBarcode').val(),
			           success:function(date){
			        	  var archivesBoxLabel = eval('('+date+')');
			              if(archivesBoxLabel.minArchivesID == "" || archivesBoxLabel.maxArchivesID ==""){
			                 alert("此盒内没有档案！");
			              }else{
			                // var archivesBoxLabel = eval('('+date+')');
			                 var firstTr = $("#showTable").children("form").children("tbody").children().first();
			                  //获取颜色
			                 var trColor = '#e0edff';    
			                 if(firstTr.attr("bgcolor")=='#e0edff' && firstTr != null){
			                    trColor = "#eef5ff";
			                 }

			                 
			                 //避免重复添加
			                 var pFlag = true;
			                 var archivesBoxBarcodes = $("#showTable").children("form").children("tbody").children().children(":nth-child(2)");
			                 for(var i=0;i<archivesBoxBarcodes.length;i++){
			                     if($(archivesBoxBarcodes[i]).text() == $('#archivesBoxBarcode').val()){
			                       pFlag = false;
				                   alert("列表中以存在！");
				                   break;
				                 }
			                 }
			                 if(pFlag){
			                    $("#showTable").prepend("<tr bgcolor='"+trColor+"'>"+
			                                                "<td align='center'><input type='checkbox' name='archivesBoxBarcodes' value='"+$('#archivesBoxBarcode').val()+"'/></td>"+
													        "<td>"+$('#archivesBoxBarcode').val()+"</td>"+
													        "<td style='color:red'>"+archivesBoxLabel.minArchivesID+"到"+archivesBoxLabel.maxArchivesID+"</td>"+
													      "</tr>");
			                 }
			              }
			           },
			           error:function(XMLHttpRequest, textStatus){
			              alert(textStatus+","+XMLHttpRequest.status);
			           }
			        });
		        } 
		    });
		    
		    $("#del").bind('click',function(){
		        $("input[name='archivesBoxBarcodes']:checked").parent().parent().remove();
		    });
           });
        </script>
	</head>

	<body>
		<fieldset style="margin-top:5px;">
        <table>
            <tr>
                <td style="font-size: 12px;">
                                    请扫描或输入盒条码：
                </td>
                <td >
                   <input type="text" id="archivesBoxBarcode" name="archivesBoxBarcode"/>
                </td>
                <td>
                  <input type="button" class="button" value="添     加" id="add"/>  
                </td>
            </tr>
         </table>
       </fieldset>
		<hr/>
          &nbsp;&nbsp;<input type="image" id="del" src="images/YC.gif" onmouseover="changeImage(this,'YC2.gif')" onmouseout="changeImage(this,'YC.gif')"/>
          &nbsp;&nbsp;<input type="image" src="images/printt.gif" onmouseover="changeImage(this,'printt2.gif')" onmouseout="changeImage(this,'printt.gif')"/>
          <table width="99%" cellspacing="0" cellpadding="0" style="margin:0px;" align="center">
			<tr class="bgTitle">
				<td style="height:25px" class="borderTop">
					<table class="tabletop" width="100%" cellspacing="0" cellpadding="0">
			            <tr>
			                <td class="tableTitle">列表</td>
			                <td></td>							                	
			            </tr>
				    </table>	
				    <form action="DALY/archivesUseAction_renewAndTeturn.action" name="form1" style="margin: 0;padding: 0;">
						<input type="hidden" id="type" name="type">						
					<table id="showTable" width="99%" align="center" style="margin:0px; border: #104da6 1px solid;" cellspacing="1px" cellpadding="0px" >						
						<thead class="tableHead">
							<tr class="bgTitle">
								<th width="35px">选择</th>								
								<th>盒条码</th>
								<th>档号起止范围</th>
							</tr>
						</thead>
						
						<tbody id="ghlb">
								
						</tbody>
						
					</table>
					</form>
					 <table width="100%" style="font-size: 12px;">
						<tr>
						   <td>
						      <input type="checkbox" id="SelectAll" accesskey="S"  name="SelectAll" onclick="selectAll(this)" title="选中/取消 所有记录(S)"  /><label for="SelectAll">全选</label>	
						   </td>
						</tr>
					</table>		
				</td>
			</tr>
		</table>	
	</body>
</html>
