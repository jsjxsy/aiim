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
    <link rel="stylesheet" type="text/css" href="css/common.css">
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
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

	function check(){
		var studentId = $("#studentId").val();
		var studentName = $("#studentName").val();
		var SN = $("#SN").val();
		if(studentId == "" && studentName == "" && SN == ""){
			alert("请输入查询条件！");
			return false;
		}
	}
	</script>

  </head>
  
  <body>
     <input type="hidden" name="preSelectRow" id="preSelectRow" />
		<table width="100%" style="margin:0px;" align="center">
		     <tr>
			  <td>   
			     <table width="100%" cellpadding="0" cellspacing="0" border="0">
			       <tr>
				       <td>
                          <input type="image" src="images/find.gif" alt="显示查询(Q)" onclick="showfind(this)" onmouseover="changeImage(this,'find2.gif')" onmouseout="changeImage(this,'find.gif')" />
					   </td>
					   <td>
					   	  <div style="margin-right:2px; width:210px; float:right; color:blue; font-size:12px;"><font style="font-weight:bold;">当前位置：</font>学生档案管理&nbsp;>>&nbsp;转出查询</div >					
					   </td>
			       </tr>
			     </table>
			   </td>
			</tr>
			<tr>
			  <td align="center" id="find" style="display:block; width:100%;">
			     <fieldset>
			       <form action="XSDAGL/studentFileManageAction_findStudentInfos.action" method="post" name="form1" style="margin: 0px; padding: 0px;" onsubmit="return check();">
			       <table style="font-size: 12px;">
			         <tr>
						<td class="text">学号</td>
						<td align="left">
						   <input type="text" name="studentId" id="studentId" style="width: 200px" value="${studentId}"/>
						</td>
					 </tr>
					 <tr>
						<td class="text">姓名</td>
						<td align="left">
							<input type="text" name="studentName" id="studentName" style="width: 200px" value="${studentName}"/>
						</td>
					  </tr>
					  <tr>
						<td class="text">转出单序列号</td>
						<td align="left">
							<input type="text" name="SN" id="SN" style="width: 200px" value="${SN}"/>
						</td>
					  </tr>
			          <tr>
			            <td></td>
			            <td>
			              <input type="image" src="images/search.gif" onmouseover="changeImage(this,'search2.gif')" onmouseout="changeImage(this,'search.gif')"/>
			            </td>
			          </tr>
			       </table>
			       </form>
			      </fieldset> 
			  </td>
			</tr>
			<tr>
				<td align="center" height="25px">
					<table class="tabletop" width="100%">
						<tr>
			                <td class="tableTitle">学生档案</td>
			                <td align="right" class="text"></td>							                	
			            </tr>
					</table>
					<table id="showTable" cellpadding="0px" cellspacing="1px">						
						<thead class="tableHead">
							<tr>
								<th style="width: 30px;">序号</th>								
								<th>学号</th>
								<th>姓名</th>
								<th>姓别</th>
								<th>专业</th>
								<th>班级</th>
								<th>入学年度</th>
								<th>毕业年度</th>	
								<th>转递单位</th>
								<th>转递方式</th>
								<th>转递情况</th>
								<th>转递号</th>	
							</tr>
						</thead>
						<tbody  id="showBody" >
							<s:iterator value="#request.studentInfos" status="rowstatus" var="s">
								<s:if test="#rowstatus.odd==true">
									<tr bgcolor="#eef5ff" style="height: 20px;" id="<s:property value="NBXH" />" onclick="clickRow(this)">
								</s:if>
								<s:else>
									<tr bgcolor="#e0edff" style="height: 20px;" id="<s:property value="NBXH" />" onclick="clickRow(this)">
								</s:else>
								<td align="center"><s:property value="#rowstatus.index+1" /></td>
								<td><s:property value="studentId" /></td>
								<td><s:property value="studentName" /></td>
								<td><s:property value="sex" /></td>
								<td><s:property value="specialty" /></td>
								<td><s:property value="grade" /></td>
								<td><s:property value="startSchoolYear" /></td>	
								<td><s:property value="finishSchoolYear" /></td>
								<td><s:property value="companyName" /></td>
								<td>
								    <s:if test="#s.moveOutWay ==1">EMS快递</s:if>
								    <s:if test="#s.moveOutWay ==2">机要通信</s:if>
								</td>
								<td>
								    <s:if test="#s.moveOutedFlag ==true">已转出</s:if>
								    <s:if test="#s.moveOutedFlag ==false">未转出</s:if>
								</td>
								<td><s:property value="SN" /></td>
							    </tr>
							</s:iterator>			
						</tbody>
					</table>
				</td>
			</tr>
		</table>
		${message}
  </body>
</html>