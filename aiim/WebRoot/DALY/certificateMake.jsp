<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("basePath",basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
    <base href="<%=basePath%>">
    
    <title>出证登记</title>
    	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" type="text/css" href="css/common.css">
	<script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
    
    <script type="text/javascript" src="dwr/interface/ArchivesUseAction.js"></script>
    <script type="text/javascript" src="dwr/util.js"></script>
    <script type="text/javascript" src="dwr/engine.js"></script>
    
    <script type="text/javascript" >
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
				url="${basePath}js/CalendarWithFormat.html";
			}
			else {
				url="${basePath}js/CalendarWithOutFormat.html"
			}
			var retval = window.showModalDialog(url, "", "dialogWidth:197px; dialogHeight:210px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no;help=no; "  );
			if( retval != null ){
				obj.value = retval;
			}
		}	
		//弹出出证登记窗口
		function showBorrow(){
		  returnValue = window.showModalDialog("<%=basePath%>DALY/archivesUseAction_findDefaultDataForCZDJ.action","newwindow","dialogWidth=550px;dialogHeight=462px;center=yes;scroll=no;status=no;resizable=no;help=no;location=no");
		  if(returnValue==1){//如果新增了档案利用登记信息则刷新页面
			  window.document.location.reload();
		  }
		}

		//查看登记信息
		function showDJXX(registerID){
			alert(registerID);
		   window.showModalDialog("<%=basePath%>DALY/archivesUseAction_findArchivesUseRegisterByID.action?useType=JD&registerID="+registerID,"newwindow","dialogWidth=550px;dialogHeight=450px;center=yes;scroll=no;status=no;resizable=no;help=no;location=no");
		}

	//单击查询按钮，提交表单
	function subQuery(){
		$('form').submit();
	}

	//档案出证信息详情
	function detail(idObj) {
		$('#certificateRegID').val(idObj);
		$('form').attr('action','DALY/archivesCertificateManageAction_certificateMakeDetail.action').submit();
	}
	</script>
	</head>
<body>
		<input type="hidden" name="preSelectRow" id="preSelectRow" />
		<table width="100%" style="margin:0px;" cellspacing="0" cellpadding="0">
			<tr>
			  <td>   
			     <table width="100%" cellpadding="0" cellspacing="0" border="0">
			       <tr>
				       <td>
							<input type="image" src="images/find.gif" alt="显示查询(Q)" onmouseover="changeImage(this,'find2.gif')" onmouseout="changeImage(this,'find.gif')" onclick="return showfind(this);"/>
						</td>
						 
						<td>
							<div id="location" style="margin-right: 2px; width: 280px; display: inline-block; float: right; margin-top: 0px; color: blue; text-align: right;">
							  <font style="font-size: 12px;"><b>当前位置：</b>利用管理&nbsp;&gt;&gt;&nbsp;&nbsp;出证制作</font>
						    </div>												
						</td>
				  </tr>
			   </table>		
			</tr>
			<tr>
			  <td align="center" id="find" style="display:none;width:100%;">			     
			     <fieldset>
				     <s:form id="certificateMake" action="archivesCertificateManageAction_certificateMake" namespace="/DALY">
				     	<s:hidden id="certificateRegID" name="certificateRegID" />
				       <table class="findTB">
				         <tr>
				           <td align="right" class="text">利用人</td>
				           <td align="left"><s:textfield name="personName" /></td>
				         </tr>
				         <tr>
				           <td align="right" class="text">证件号</td>
				           <td align="left"><s:textfield name="cardId" /></td>
				         </tr>
				       </table>
				       </s:form>
			       </fieldset>			     
			      <input type="image" src="images/search.gif" onclick="subQuery()" onmouseover="changeImage(this,'search2.gif')" onmouseout="changeImage(this,'search.gif')"/>
			  </td>
			</tr>
			<tr>
				<td>
					<table class="tabletop" width="100%">
						<tr>
			                <td class="tableTitle">档案出证登记信息</td>
			                <td align="right" class="text"></td>							                	
			            </tr>
					</table>
					<table id="showTable" width="100%" style="margin:0px; border: #104da6 1px solid;" cellspacing="1px" cellpadding="0px" >						
						<thead class="tableHead">
							<tr class="bgTitle">
								<th width="35px">序号</th>
								<th>利用人</th>
								<th>证件号</th>
								<th>应缴金额</th>
								<th>实际收费</th>
								<th>登记日期</th>
								<th>发票代码</th>
								<th>经办人</th>
								<th>备注</th>
							</tr>
						</thead>
						<tbody align="center">
						  <s:iterator value="archivesCertificateRegisters" status="status">
						    <s:if test="(#status.index+1)%2==0">
						       <s:set name="color" value="'#e0edff'" scope="page"></s:set>
						    </s:if>
						    <s:else>
						       <s:set name="color" value="'#eef5ff'" scope="page"></s:set>
						    </s:else>
						    <tr  bgcolor="${pageScope.color}" id="row<s:property value="#status.index+1"/>" onclick="selectRow(this)">
								<td align="center" height="20px"><s:property value="#status.index+1"/></td>
								<td><a href="javascript:detail('<s:property value="iD"/>');"><s:property value="personName"/></a></td>
								<td><s:property value="cardNo"/></td>
								<td><s:property value="shouldCharge"/></td>
								<td><s:property value="realCharge"/></td>
								<td><s:date name="registerDate" format="yyyy-MM-dd hh:mm:ss"/></td>
								<td>
									<s:if test="invoiceSN!=null && ''!=invoiceSN"><s:property value="invoiceSN"/></s:if>
									<s:else>&nbsp;</s:else>
								</td>
								<td><s:property value="managerUserName"/></td>
								<td>
									<s:if test="remark!=null && ''!=remark"><s:property value="remark"/></s:if>
									<s:else>&nbsp;</s:else>
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