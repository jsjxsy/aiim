<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>转出统计</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/common.css"/>
	<script type="text/javascript">
		
	//改变图片背景颜色
	function changeImage(obj,img) {
		if (obj) {
			obj.src="images/"+img;
		}
	}

	function statistics(){
		document.getElementById('findDiv').style.display=(document.getElementById('findDiv').style.display=='none')? 'block':'none';
	}
</script>
<style type="text/css">
#showTable tr,th{
	height:25px;
}

#showTable td,th{
	border:1px black solid;
}
</style>
  </head>
  
  <body>
    <table width="100%" style="margin:0px; font-size:12px;" cellspacing="0" cellpadding="0">
	<tr>
		<td><!-- 
			<input type="image" src="images/print.gif" style="margin-left:3px;" onmouseover="changeImage(this,'print2.gif')" onmouseout="changeImage(this,'print.gif')" onclick="alert('打印中...')"/>
			 -->
			 <input type="image" src="images/statistics.gif" onmouseover="changeImage(this,'statistics2.gif')" onmouseout="changeImage(this,'statistics.gif')" onclick="statistics()"/>
		</td>
		<td>
			<div style="margin-right:2px; display:inline-block; width:300px; color:blue; float:right; margin-top:5px;" align="right">
						<font style="font-size:12px;font-weight:bold;">当前位置：</font>转出管理&nbsp;&gt;&gt;&nbsp;转出统计</div >
		</td>
	</tr>	
</table>
  	
          <fieldset id="findDiv">
   		<table class="findTB" align="center">
			<tr>
				<td>年度</td>
				<td align="left">
					<input type="text" name="beginTime" style="width: 90px;">至<input type="text" name="endTime" style="width: 90px;">
				</td>
			</tr>
			
			<tr>
				<td>转递方式</td>
				<td align="left">
					<select name="specialty" id="specialty" style="width: 200px">
						<option value="0"></option>
						<option value="1">EMS</option>
						<option value="2">机要</option>
					</select>
			<!-- 		<input type="text" name="title" id="title"
						style="width: 200px" value="${title}"/> -->
				</td>
			</tr>
			
			<tr style="height: 40px;">
				<td></td>
				<td align="left">
					<input type="submit" value="统计" style="margin-left: 65px;"
						onclick="javascript:document.getElementById('findDiv').style.display='none'" />
					<label style="width: 100px;">
						&nbsp;
					</label>
				</td>
			</tr>
		</table>
   	</fieldset>
<hr>
<table  id="statisticsYear" cellpadding="0" cellspacing="0" style=" display:block;   width:50%" align="center">
						
			<tr>
				<td align="center">
					<table id="showTable" width="60%" border="1" style="margin:0px; border-color:black; border-collapse:collapse;border-style: solid;  " cellspacing="0px" cellpadding="0px" >						
						<caption style="font-size:14px;">1998年至2010年本科生人事档案年转出总量统计</caption>
						<thead >
							<tr style="height: 20px;" >
								<th width="40px;" rowspan="2">序号</th>
								<th rowspan="2">年度</th>
								<th colspan="4">转出数量</th>								
							</tr>
							<tr>
							<th>本科生</th><th>研究生</th><th>教职工</th><th>合计</th>
							</tr>
						</thead>
						<tbody id="table_body" style="">
							<tr   id="row1" >
								<td>1</td>
								<td>1998</td>								
								<td>234</td>
								<td>234</td>
								<td>234</td>
								<td>2065</td>
							</tr>
							<tr  id="row2" >
								<td>2</td>
								<td>1999</td>
								<td>234</td>
								<td>234</td>
								<td>234</td>
								<td>2065</td>
							</tr>
							<tr  id="row3"   >
								<td >3</td>
								<td >2001</td>
								<td>234</td>
								<td>234</td>
								<td>234</td>
								<td>2065</td>
							</tr>
							<tr id="row4" >
								<td >4</td>
								<td >2002</td>
								<td>234</td>
								<td>234</td>
								<td>234</td>
								<td>2065</td>
							</tr>
							<tr id="row5" >
								<td>5</td>
								<td>2003</td>
								<td>234</td>
								<td>234</td>
								<td>234</td>
								<td>2065</td>

							</tr>
							<tr   id="row6" >
								<td>6</td>
								<td>2004</td>
								<td>234</td>
								<td>234</td>
								<td>234</td>
								<td>2065</td>
							</tr>
							<tr  id="row7" >
								<td>7</td>
								<td>2005</td>
								<td>234</td>
								<td>234</td>
								<td>234</td>
								<td>2065</td>
							</tr>
							<tr   id="row8" >
								<td>8</td>
								<td>2006</td>
								<td>234</td>
								<td>234</td>
								<td>234</td>
								<td>2065</td>
							</tr>
							<tr   id="row9" >
								<td>9</td>
								<td>2007</td>
								<td>234</td>
								<td>234</td>
								<td>234</td>
								<td>2065</td>
							</tr>
							<tr   id="row10" >
								<td>10</td>
								<td>2008</td>
								<td>234</td>
								<td>234</td>
								<td>234</td>
								<td>2065</td>
							</tr>
							<tr   id="row11" >
								<td>11</td>
								<td>2009</td>
								<td>234</td>
								<td>234</td>
								<td>234</td>
								<td>2065</td>
							</tr>
							<tr id="row12" >
								<td>12</td>
								<td>2010</td>
								<td>234</td>
								<td>234</td>
								<td>234</td>
								<td>2065</td>
							</tr>
							<tr id="row13" >
								<td colspan="2"><b>合&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;计</b></td>
								<td>1234</td>
								<td>1234</td>
								<td>134</td>
								<td>22065</td>
							</tr>
						</tbody>
					</table>
					<div align="left">统计时间：2008年12月3日</div>
				</td>
			</tr>
		</table>
   	

  </body>
</html>
