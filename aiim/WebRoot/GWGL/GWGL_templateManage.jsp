<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<title>模板管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" >


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

//打开新增对话框
function showAdd()
{
	window.showModalDialog("GWGL_templateItem.html","newwindow","dialogWidth=540px;dialogHeight:150px;top=0;left=0;status:no; toolbar=no;menubar=no;scroll:0;location:yes; resizable=no;location=n");
}

//处理单击事件
var rowId="";   //保存上一次点击行“tr”的ID；
var rowColor="";   //保存上一次点击行的颜色
function clickRow1(obj)
{
	if(document.getElementById(rowId)==null){//第一次点击处理
		rowId=obj.id;	//保存被点击行的ID
		rowColor=obj.style.backgroundColor;//保存被点击行的颜色
		obj.style.backgroundColor='#a3c9ff';
	
	}else{
		document.getElementById(rowId).style.backgroundColor=rowColor;
		//'#e0edff';
		obj.style.backgroundColor='#a3c9ff';
		rowId = obj.id;
	}
	 
}



//文件级目录管理和案卷级目录管理的切换
function changeTypeOfDA(type_DA)
{
	if(type_DA=='DA_AJ'){   //如果类型是案卷
		document.getElementById('DA_WJ').style.display="none";
		document.getElementById('DA_AJ').style.display="block";
	}else{
		document.getElementById('DA_AJ').style.display="none";
		document.getElementById('DA_WJ').style.display="block";
	}
		
}


//改变按扭背景颜色
function changeBgColor(objImage,bgcolor)
{
	objImage.style.background=bgcolor;
}
</script>

<style type="text/css">
/*设置标题底色*/
.bgTitle { background-color:#a3c9ff;height:25px;}
/*设置表格顶部框底色*/
.borderTop {	border-top:#104da6 1px solid;	border-left:#104da6 1px solid;	border-right:#104da6 1px solid;}
body 
{
	height:100%;
	color: #000000; 
	font-size:12px;
	margin:0; 
	background-color:White;
}
/*表头*/
.tableTitle {font-weight:bold; text-align:left; padding:4px 0 0 5px;}
.text{ font-size:9pt;}
.tableHead{
	font-weight:bold; text-align:center; padding:4px 0 0 0px;
}
/*
#showTable tbody tr:hover{
	background-color:#a3c9ff;
}
*/
/*设置被选行的颜色*/
.selectRowColor{
	background-color:#a4caef;
}



</style>


</head>

<body>


<!--文件级管理（默认）-->
<table id="DA_WJ" cellpadding="0" cellspacing="0" style=" display:block; padding:0; margin:0; width:100%" >
	<tr>
		<td>		
		<table width="100%" style="margin:0px;" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<input type="image" src="images/NewS.gif"  onclick="showAdd();" onmouseover="changeImage(this,'NewS2.gif')" onmouseout="changeImage(this,'NewS.gif')"  />
					<input type="image" src="images/DelS.gif" disabled="disabled" />
					<input type="image" src="images/EditS.gif" disabled="disabled" />&nbsp;
					<input type="image" src="images/FindS.gif" onmouseover="changeImage(this,'FindS2.gif')" onmouseout="changeImage(this,'FindS.gif')" onclick="find()" />&nbsp;
					<div style="margin-right:2px; display:inline-block; width:280px; float:right; margin-top:5px; color:blue; text-align:right;"><font style="font-size:12px;font-weight:bold;">当前位置：</font>公文管理&nbsp;&gt;&gt;&nbsp;&nbsp;文档中心</div >	
				</td>
			</tr>
			<tr>
			  <td >
			    <div id="findDiv" style="display:none; width:100%;margin-top:10px;">
			       <table class="findTB"  >
			         
					<tr style="height: 26px;" align="left">
						<td>
						公文名称
						</td>
						<td>
							<input type="text" style="width:150px;" />
							<input type="submit" value="查询" style="margin-left:5px;"   onclick="javascript:document.getElementById('findDiv').style.display='none';"/>
						</td>
					</tr>
				
			       </table>
			    </div>
			  </td>
			</tr>

			<tr>
				<td>
					<table width="100%" cellspacing="0" cellpadding="0" style="margin:0px;">
						<tr class="bgTitle">
							<td style="height:25px" class="borderTop">
								<table style="width:100%;height:25px" cellspacing="0" cellpadding="0">
							            <tr>
							                <td>
							                    <label class="tableTitle">公文模板—公告</label>
							                </td>
							                <td align="right"  class="text" >
							                	<label style="margin-right:4px" id="rsInfo">第1页 共1页 共4条记录</label>
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
					<table id="showTable" width="100%" style="margin:0px; border: #104da6 1px solid;" cellspacing="1px" cellpadding="0px" >						
						<thead class="tableHead">
							<tr class="bgTitle">
								<th width="35px">&nbsp;</th>								
								<th style="width:30px;">序号</th>
								<th>公文名称</th>
								<th>创建者</th>								
								<th>建立时间</th>
								<th align="center" style="width: 35px">操作</th>								
							</tr>
						</thead>
									<tbody>
										<s:iterator var="officialTemplate"
											value="#request.officialTemplates">
											<tr bgcolor="#eef5ff" id="row1" status="rowstatus">
												<td align="center">
													<input type="checkbox" id="001" />
												</td>
												<td>
													<s:property value="#rowstatus.index+1"/>
												</td>
												<td>
													<s:property value="#title"/>
												</td>
												<td>
													<s:property value="#title"/>
												</td>
												<td>
													<s:date name="createDate"  format="yyyy-MM-dd"/>
												</td>
												<td align="center" style="width: 35px">
													<a href="gongGao/公告摸板_个人宣.doc" target="_blank">下载</a>
												</td>
											</tr>
										</s:iterator>
									</tbody>
								</table>
				</td>
			</tr>
			
			<tr>
				<td>
					<table width="100%">
						 <tr>
							<td><input type="checkbox" id="SelectAll" accesskey="S"  name="SelectAll"
								  title="选中/取消 所有记录(S)"  /><label for="SelectAll">全选</label>
								  
							</td>
							<td align="right">
								<input type="image" src="images/FirstS1.gif" />	
								<input type="image" src="images/PreviousS1.gif" />
								<input type="image" src="images/NextS.gif" />
								<input type="image" src="images/LastS.gif" />
								转到第<input type="text" style="width:15px; height:15px" id="goPage"/>页
								<input type="image" src="images/GoS.gif"/>
									                                           
							</td>
						</tr>
					</table>
				</td>
			</tr>
		
			
		</table>
		
		</td>
	</tr>
</table>


</body>

</html>
