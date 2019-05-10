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
    
    <title>收文管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/common.css"/>
    <script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript">
    //打开日期输入页面
		function PopUpCalendar(ctrlobj,type)
		{
			var url;
			var obj=document.getElementById(ctrlobj);
			if (obj==null) {
				return;
			}
			var obj1=obj;
			showx=obj1.offsetLeft+window.screenLeft;
			showy=obj1.offsetTop+window.screenTop+20;
			while (obj1=obj1.offsetParent) {
				showx+=obj1.offsetLeft;
				showy+=obj1.offsetTop;
			}
			if (type==true) {
				url="<%=basePath%>js/CalendarWithFormat.html";
			}
			else {
				url="<%=basePath%>js/CalendarWithOutFormat.html"
			}
			var retval = window.showModalDialog(url, "", "dialogWidth:197px; dialogHeight:210px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no;help=no; "  );
			if( retval != null ){
				obj.value = retval;
			}
		 }
    </script>
  </head>

  <body style="margin:0px; background-color:#f9f9f9;">


	<table width="100%" style="height:100%" cellpadding="0" cellspacing="0">
		<tr>
			<td style="background-image:url(images/bghead.gif);height:50px;" >&nbsp;				
				<input type="image"ID="imgFirst" src="images/imagefirst3.gif" />
				<input type="image" src="images/imageprevious3.gif"  />
				<input type="image" ID="imgNext" src="images/imagenext3.gif" />
				<input type="image" ID="imgLast" src="images/imagelast3.gif"  />
				<img src="images/Separator.gif" alt="" />
				<input type="image" ID="imgAdd" src="images/add3.gif" />
				<input type="image" ID="imgDel" src="images/delete3.gif"  />
				<input type="image" ID="imgEdit" src="images/edit3.gif"  Width="36px" />					
				<img src="images/Separator.gif" alt="" />
				<input type="image" ID="imgSave" src="images/save.gif" />
				<input type="image" ID="imgSAdd" src="images/saveadd.gif"  />
				<input type="image" ID="imgCancel" src="images/imagecancel.gif"/>
				<img src="images/Separator.gif" alt="" />&nbsp;
				<input type="image" ID="imgExit" src="images/exitout.gif"  onclick="window.close();" />
			</td>
		</tr>
	</table>
	<br/>		
	<table id="inputs" width="100%" style="height:100%;  margin-left:50px;  font-size:15px;" cellpadding="0" cellspacing="0" >
		<tr>
			<td width="60px">文号</td>			
			<td>
				<input type="text" style="width: 177px"  style="margin-right:10px;"/> 
				收文类型<select style="width: 211px; margin-right:10px;  " >
					<option></option>
					<option selected="selected">公告</option>
					<option>决定</option>
					<option>决议</option>
				</select>
			</td>
		</tr>
		<tr style="height:5px;"></tr>
		<tr >
			<td style="height: 26px">题名</td>
			<td style="height: 26px">
				<input type="text" style="width: 457px" />
			</td>
		</tr>
		<tr style="height:5px;"></tr>
		<tr >
			<td>主题词</td>
			<td><input type="text" style="width: 457px; "/></td>
		</tr>
		<tr style="height:5px;"></tr>
		<tr>
			<td style="height: 26px">责任者</td>
			<td style="height: 26px">
				<select style="width: 131px; margin-right:10px;">
					<option></option>
					<option>组织部</option>
					<option>党办</option>
					<option>校办</option>
				</select>
				密级<select style="width: 93px; margin-right:10px; margin-left:5px;">
					<option value="0">	</option>
					<option value="1">开放</option>
					<option value="2">公开</option>
					<option value="3">内部</option>					
				</select>
				保管期限<select style="width: 111px; margin-right:10px; ">
					<option value="0">
					</option>
					<option value="1">永久
					</option>
					<option value="2">长期
					</option>
					<option value="3">短期
					</option>

				</select>			
			</td>
		</tr>
		<tr style="height:5px;"></tr>
		<tr >
			<td>公文属性</td>
			<td>
				<select style="width: 131px; margin-right:10px;">
					<option></option>
					<option>上级来文</option>
					<option>下级来文</option>
					<option>非隶属单位来文</option>
					<option>本单位行文</option>
				</select>
			</td>
		</tr>
		<tr style="height:5px;"></tr>
		<tr >
			<td>附件</td>
			<td>
				<input type="file" style="width: 464px" />	
			</td>
		</tr>
		
		<tr style="height:5px;"></tr>
		<tr >
			<td>份数</td>
			<td>
				<input type="text" style="width: 177px; margin-right:10px;" />
				收文日期
				<input type="text" id="xcDate" style="margin-left:5px; width: 182px;"/>
				<input  type="image" style="margin-right:20px;" src="images/DropdownTime.gif" onclick="PopUpCalendar('xcDate',true);return false;"  />
			</td>
		</tr>
		<tr style="height:5px;"></tr>
		
		<tr >
			<td>签发人</td>
			<td>
				<input type="text" style="width: 177px; margin-right:10px;" />
				主办单位
				<input type="text" id="xcDate" style="margin-left:5px; width: 199px;"/>
			</td>
		</tr>
		<tr style="height:5px;"></tr>
		<tr >
			<td>会签人</td>
			<td>
				<input type="text" style="width: 177px; margin-right:10px;" />
				会签单位
				<input type="text" id="xcDate" style="margin-left:5px; width: 199px;"/>
			</td>
		</tr>
		<tr style="height:5px;"></tr>
		<tr >
			<td>备注</td>
			<td>
				<textarea rows="3" style="width: 457px"></textarea>
			</td>
		</tr>
		<tr style="height:5px;"></tr>
		<tr >
			<td>著录人</td>
			<td>
				<input type="text" style="width: 180px; margin-right:20px;" value="张三" readonly="readonly" />
			</td>
		</tr>
	</table>	
</body>
</html>
