<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>利用申请单</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
    <link rel="stylesheet" type="text/css" href="css/common.css">
	<script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript">
	  //从清单中移除档案信息
	  function removeFromQD(NBXH){    
        $.ajax({
                type:"post",
                url:"DALY/archivesUseAction_delArchivesCurrentUseList.action",
                data:"NBXH="+NBXH,
                beforeSend:function(XMLHttpRequest){
                   if(!confirm("确认删除？")) return false;
                },
                success:function (){                    
                   $('tr').remove('#'+NBXH);          
                 },
                 error:function(XMLHttpRequest, textStatus, errorThrown){
                   $("body").children().remove();
                   $("body").append("<div>"+"系统操作异常"+textStatus + XMLHttpRequest.responseText+"</div>");
                 }
         });
      }  
      /*$(document).ready(function(){
        $("#ajaxMessage").ajaxStart(function(){
                $(this).text("准备建立请求.readyState0:");
            });
            $("#ajaxMessage").ajaxSend(function(evt, request, settings){
                $(this).text("开始请求,准备发送数据.readyState1:"+request.readyState);
            });
            $("#ajaxMessage").ajaxComplete(function(event,request, settings){
                if(request.status==200)
                    $(this).text("请求完成.readyState4:"+request.readyState);
            });
            $("#ajaxMessage").ajaxStop(function(){
                $(this).text("请求结束.");
            });
      });*/
	</script>
	</head>
	<body class="bg_color" style="margin-top:4px;">
	<div id="ajaxMessage"></div>
		<table style="height:100%" cellspacing="0" cellpadding="0" width="100%">
			<tr>
				<td align="center">
					<table class="back_border" width="98%" cellpadding="0" cellspacing="0">
					    <tr>
					        <td class="bg_title">&nbsp;利用人信息</td>
					    </tr>
					    <tr>
					        <td>
					            <table width="100%">
							        <tr>
								        <td class="text">姓名：</td>
								        <td align="left">
									        <input  id="realName" name="userInfo.realName" value="${sessionScope.userInfo.realName}" type="text"/><font color="red" size="3px">*</font>
									    </td>
								        <td class="text">证件号：</td>
								        <td align="left">
									        <input id="iDCardNo" name="userInfo.iDCardNo" value="${sessionScope.userInfo.IDCardNo}" type="text" /><font color="red" size="3px">*</font>
									    </td>
							        </tr>
							        <tr>
								        <td class="text">所属单位：</td>
								        <td align="left">
									        <input  id="departmentID" name="userInfo.departmentID" value="${sessionScope.userInfo.departmentID}" type="text"/>
									    </td>
								        <td class="text">电话：</td>
								        <td align="left">
									        <input id="tel" type="text" />
									    </td>
							        </tr>
							        <tr>
								        <td class="text">E_mail：</td>
								        <td align="left">
									        <input  id="user_id" type="text"/><font color="red" size="3px">*</font></td>
								        <td class="text">密码：</td>
								        <td align="left"><input  id="user_id" type="text"/><font color="red" size="3px">*</font></td>
							        </tr>
							        <tr>
							          <td class="text">所属地域：</td>
								        <td align="left">
								          <select>
								            <option>&nbsp;&nbsp;—&nbsp;中国大陆&nbsp;—&nbsp;&nbsp;</option>
								            <option>&nbsp;&nbsp;—&nbsp;中国台湾&nbsp;—&nbsp;&nbsp;</option>
								            <option>&nbsp;&nbsp;—&nbsp;中国香港&nbsp;—&nbsp;&nbsp;</option>
								            <option>&nbsp;&nbsp;—&nbsp;中国澳门&nbsp;—&nbsp;&nbsp;</option>
								            <option>&nbsp;&nbsp;—&nbsp;美&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;国&nbsp;—&nbsp;&nbsp;</option>
								            <option>&nbsp;&nbsp;—&nbsp;英&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;国&nbsp;—&nbsp;&nbsp;</option>
								            <option>&nbsp;&nbsp;—&nbsp;加&nbsp;&nbsp;拿&nbsp;&nbsp;大&nbsp;—&nbsp;&nbsp;</option>
								            <option>&nbsp;&nbsp;—&nbsp;新&nbsp;&nbsp;加&nbsp;&nbsp;坡&nbsp;—&nbsp;&nbsp;</option>
								            <option>&nbsp;&nbsp;—&nbsp;其他未列&nbsp;—&nbsp;&nbsp;</option>
								          </select>
									    </td>
									    <td></td>
									    <td></td>
							        </tr>
							      </table>
					        </td>
					    </tr>
					</table>
					<table class="back_border" width="98%" cellpadding="0" cellspacing="0">
					    <tr>
					        <td class="bg_title">利用登记信息</td>
					    </tr>
					    <tr>
					        <td>
					            <table width="100%">
							        <tr>
								        <td class="text">申请日期：</td>
								        <td align="left">
									        <input type="text" />
									    </td>
								        <td class="text">利用目的：</td>
								        <td align="left">
									      <select>
									        <option>&nbsp;&nbsp;&nbsp;&nbsp;--工作考察--</option>
									        <option>&nbsp;&nbsp;&nbsp;&nbsp;--学术研究--</option>
									        <option>&nbsp;&nbsp;&nbsp;&nbsp;--经济建设--</option>
									        <option>&nbsp;&nbsp;&nbsp;&nbsp;--落实政策--</option>
									        <option>&nbsp;&nbsp;&nbsp;&nbsp;--个人取证--</option>
									        <option>&nbsp;&nbsp;&nbsp;&nbsp;--其他.....--</option>
									      </select>  
									    </td>
							        </tr>
							        <tr>
								        <td class="text">备注：</td>
								        <td colspan="3" align="left">
								         <textarea rows="3" style="width: 100%"></textarea>
									   </td>
							        </tr>
							       <tr>
							           <td class="text">利用清单：</td>
							           <td colspan="3" align="left">
									        <div style="overflow:auto; height:200px; border:1px silver solid;">
										        <table width="100%" id="showTable">
										          <thead class="tableHead">
											        <tr class="bgTitle">
												        <td>档号</td>
												        <td>题名</td>
												        <td>利用方式</td>		        
											            <td>操作</td>
											        </tr>
											       </thead>
											       <tbody>
											        <tr bgcolor="#e0edff" id="1">
												        <td style="height: 18px">G01-2002-DQ11-1</td>
												        <td style="height: 18px"><img style="width:23px;height:17px;" src="images/type_doc.gif" title="文件"/>测试题名1</td>
												        <td style="height: 18px">查档</td>
												        <td align="center" style="height: 18px">
												           <a href="javascript:removeFromQD('1');">移除</a>
												        </td>
												   </tr>
												   <tr bgcolor="#e0edff" id="2">
												        <td>G01-2002-DQ11-2</td>
												        <td><img style="width:23px;height:17px;" src="images/type_doc.gif" title="文件"/>测试题名2</td>
												        <td>借档</td>
												        <td align="center">
												            <a href="javascript:removeFromQD('2');">移除</a>
												        </td>
												   </tr>
												  </tbody>
										        </table>
									           </div>
							                </td> 
							            </tr>
						            </table>
					        </td>
					    </tr>
					</table>
				    <table style="height:30px" cellpadding="5" width="100%">
						<tr>
						  <td align="center">
						    <input type="button" id="btCancel" class="button" value="打印清单"/>&nbsp;&nbsp;
						    <input type="button" id="btOk" class="button" value="提交申请"/>&nbsp;&nbsp;
							<input type="button" id="btCancel" class="button" value="取      消" onClick="javascript:window.close();"/>	
						  </td>
					    </tr>
				   </table>
				</td>
			</tr>
		</table>
	</body>
</html>
