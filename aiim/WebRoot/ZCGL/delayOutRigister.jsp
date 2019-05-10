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
    
    <title>缓发登记</title>
    
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

	//批量添加
	function massAdd(){
		window.showModalDialog("<%=basePath%>ZCGL/massDelayOutRegister.jsp","newwindow","dialogWidth=400px;dialogHeight:150px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");

	}

	//缓发
	function add(){
		var odiv = document.getElementById("addStudent");
		odiv.style.display=odiv.style.display=='none'? 'block':'none';		
	}
	</script>
	
  </head>
  
  <body style="overflow: scroll;">
<table width="100%" style="margin:0px; font-size:12px;" cellspacing="0" cellpadding="0">
	<tr>
		<td><!-- 
			<input type="image" src="images/print.gif" style="margin-left:3px;" onmouseover="changeImage(this,'print2.gif')" onmouseout="changeImage(this,'print.gif')" onclick="alert('打印中...')"/>
			 -->
			 <input type="button" value="新增" onclick="add()">
			  <input type="button" value="批量添加" onclick="massAdd()">
		</td>
		<td>
			<div style="margin-right:2px; display:inline-block; width:300px; color:blue; float:right; margin-top:5px;" align="right">
						<font style="font-size:12px;font-weight:bold;">当前位置：</font>转出管理&nbsp;&gt;&gt;&nbsp;EMS转出登记</div >
		</td>
	</tr>	
</table>
<div id="addStudent" style="display: none;margin-left: 2px;"><font size="2">学号</font><input type="text" name="studentID"/><input type="button" value="添加"></div>
	
<table width="100%" style="margin:0px; font-size:12px;" cellspacing="0" cellpadding="0">	
	<tr style="height:2px;">
		<td></td>
	</tr>
	<tr>
		<td>
			<table width="100%" cellspacing="0" cellpadding="0" style="margin:0px; font-size:12px;">
				<tr class="bgTitle">
					<td style="height:25px" class="tableTop">
						<table style="width:100%;height:25px" cellspacing="0" cellpadding="0">
					            <tr>
					                <td>
					                    <font style="font-size:13px;font-weight:bold">档案</font>
					                </td>
					                <td align="right"  class="text" >
					                	<label style="margin-right:4px" id="rsInfo"> 共<span style="color:blue;font-weight:bold; font-size:13px;">${recordCount}</span>条记录</label>
					                </td>							                	
					            </tr>
					    </table>
					</td>
				</tr>
			</table>				
		</td>			
	</tr>
	<tr>
		<td>
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
							操作
						</th>
					</tr>
				</thead>
				<tbody>
				<tr bgcolor="#eef5ff" style="height: 20px;"
								id="22" onclick="clickRow(this)" title="双击查看、编辑" ondblclick="showItem(this)">
						<td style="widtd: 30px;" align="center">
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
							<a href="javascript:alert('取消缓发')">删除</a>
						</td>
					</tr>
					
					<tr bgcolor="#e0edff" style="height: 20px;"
								id="23" onclick="clickRow(this)" title="双击查看、编辑" ondblclick="showItem(this)">
						<td style="widtd: 30px;" align="center">
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
							<a href="javascript:alert('取消缓发')">删除</a>
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
