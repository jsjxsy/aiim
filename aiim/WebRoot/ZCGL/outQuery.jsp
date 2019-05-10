<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>查询</title>
    
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

//处理单击事件
var rowId="";   //保存上一次点击行“tr”的ID；
var rowColor="";   //保存上一次点击行的颜色
function clickRow(obj)
{
if(document.getElementById(rowId)==null){//第一次点击处理
	rowId=obj.id;	//保存被点击行的ID
	rowColor=obj.style.backgroundColor;//保存被点击行的颜色
	obj.style.backgroundColor='#a3c9ff';

}else{
	document.getElementById(rowId).style.backgroundColor=rowColor;
	obj.style.backgroundColor='#a3c9ff';
	rowId = obj.id;
}	 
}

//改变图片背景颜色
function changeImage(obj,img) {
	if (obj) {
		obj.src="images/"+img;
	}
}

//简单查询
function find()
{
	document.getElementById('findDiv').style.display=(document.getElementById('findDiv').style.display=='none')? 'block':'none';
}
</script>

  </head>
  
  <body>
  <table width="100%" style="margin:0px; font-size:12px;" cellspacing="0" cellpadding="0">
	<tr>
		<td><!-- 
			<input type="image" src="images/print.gif" style="margin-left:3px;" onmouseover="changeImage(this,'print2.gif')" onmouseout="changeImage(this,'print.gif')" onclick="alert('打印中...')"/>
			 -->
			 <input type="image" src="images/find.gif"
						onmouseover="changeImage(this,'find2.gif')"
						onmouseout="changeImage(this,'find.gif')" onclick="find()" />
		</td>
		<td>
			<div style="margin-right:2px; display:inline-block; width:300px; color:blue; float:right; margin-top:5px;" align="right">
						<font style="font-size:12px;font-weight:bold;">当前位置：</font>转出管理&nbsp;&gt;&gt;&nbsp;转出查询</div >
		</td>
	</tr>	
</table>
 
   	<fieldset id="findDiv">
   		<table class="findTB" align="center">
   			<!-- <tr>
				<td>选择对象</td>
				<td align="left">
					<input type="radio" name="objType" value="1" id="student"><label for="student">学生</label>
					<input type="radio" name="objType" value="1" id="worker"><label for="worker">教职工</label>
				</td>
			</tr>
			 -->
			<tr>
				<td>学号/工资号</td>
				<td align="left">
					<input type="text" name="archivesBarcode" id="archivesBarcode"
						style="width: 200px" value="${archivesBarcode}"/>
				</td>
			</tr>
			<tr>
				<td>姓名</td>
				<td align="left">
					<input type="text" name="name" id="name"
						style="width: 200px" value="${title}"/>
				</td>
			</tr>
			<tr>
				<td>专业</td>
				<td align="left">
					<select name="specialty" id="specialty" style="width: 200px">
						<option value="0"></option>
						<option value="1">自动化</option>
						<option value="2">电子科学与技术</option>
					</select>
			<!-- 		<input type="text" name="title" id="title"
						style="width: 200px" value="${title}"/> -->
				</td>
			</tr>								
			<tr>
				<td>年度</td>
				<td align="left">
					<input type="text" name="year" id="year"
						style="width: 200px" value="${archivesID}"/>
				</td>
			</tr>
			<tr>
				<td>转出状态</td>
				<td align="left">
					<select name="recordFlag" style="width:200px;">
						<option value="0" ></option>
						<option value="1">已转出</option>
						<option value="2">未转出</option>
					</select>										
				</td>
			</tr>
			<tr style="height: 40px;">
				<td></td>
				<td align="left">
					<input type="submit" value="查询" style="margin-left: 65px;"
						onclick="javascript:document.getElementById('findDiv').style.display='none'; document.getElementById('showResult').style.display='block';" />
					<label style="width: 100px;">
						&nbsp;
					</label>
				</td>
			</tr>
		</table>
   	</fieldset>
   	<table width="100%" style="margin: 0px;" cellspacing="0" cellpadding="0">			
			<tr id="showResult" style="">
				<td>
					<table width="100%" cellspacing="0" cellpadding="0"
						style="margin: 0px;">
						<tr class="bgTitle">
							<td style="height: 25px" class="tableTop">
								<table style="width: 100%; height: 25px" cellspacing="0"
									cellpadding="0">
									<tr>
										<td>
											<label class="tableTitle">
												档案
											</label>
										</td>
										<td align="right" class="text">
											<label style="margin-right: 4px" id="rsInfo">
												共<span style="color:blue; font-weight:bold;">${recordCount}<s:property value="recordCount"/></span>条记录
											</label>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>

					<table id="showTable" width="100%"
						style="margin: 0px; border: #104da6 1px solid; font-size: 12px;"
						cellspacing="1px" cellpadding="0px">
						<thead class="tableHead">
							<tr class="bgTitle">
								<th style="width: 30px;">
									序号
								</th>								
								<s:if test="#request.personType==3">
									<th>工资号</th>
								</s:if>		
								<s:else>
									<th>学号</th>
								</s:else>
								<th>
									姓名
								</th>
								<th>
									姓别
								</th>
								<th>
									专业
								</th>
								<th>
									年度
								</th>
								<th>
									转递单位
								</th>
								<th>转递方式</th>
								<th>转递号</th>
							</tr>
						</thead>
						<tbody>
						<tr bgcolor="#eef5ff" style="height: 20px;"
										id="22" onclick="clickRow(this)" >
								<td style="widtd: 30px;">
									1
								</td>
								<td>
									6404010107
								</td>
								<td>
									陈源
								</td>
								<td>
									男
								</td>
								<td>
									计算机
								</td>
								<td>
									2006
								</td>
								<td>
									上海AA公司
								</td>
								<td>
									快递
								</td>
								<td>
									1265984
								</td>
								
							</tr>
							
							<tr bgcolor="#e0edff" style="height: 20px;"
										id="23" onclick="clickRow(this)" >
								<td style="widtd: 30px;">
									2
								</td>
								<td>
									6404010107
								</td>
								<td>
									高园
								</td>
								<td>
									女
								</td>
								<td>
									工商管理
								</td>
								<td>
									2006
								</td>
								<td>
									北京BA公司
								</td>
								<td>
									快递
								</td>
								<td>
									1265945
								</td>
							</tr>
							
							<s:iterator value="#request.storeroomArchivesInfos"
								status="rowstatus">
								<s:if test="#rowstatus.odd==true">
									<tr bgcolor="#eef5ff" style="height: 20px;"
										id="<s:property value="NBXH" />" onclick="clickRow(this)">
								</s:if>
								<s:else>
									<tr bgcolor="#e0edff" style="height: 20px;"
										id="<s:property value="NBXH" />" onclick="clickRow(this)">
								</s:else>
								<td align="center">
									<s:property value="#rowstatus.index+1" />
								</td>
								<td>
									<s:property value="archivesID" />
								</td>
								<td>
									<s:property value="title" />
								</td>
								<td>
									<s:property value="storeStatus" />
								</td>
								<td>
									<s:property value="storeAddressFullName" />
								</td>
								<td>
									<s:property value="archivesBoxBarcode" />
								</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</td>
			</tr>
		</table>
  </body>
</html>
