<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>利用登记</title>
    <base href="<%=basePath%>">
    
    <title>分类查询</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<script type="text/javascript" src="js/common.js"></script>
  </head>
	<body class="bg_color" style="margin-top:4px;">
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
									        <input  id="user_id"  type="text"/><font color="red" size="3px">*</font>
									    </td>
								        <td class="text">证件号：</td>
								        <td align="left">
									        <input id="user_name" type="text" /><font color="red" size="3px">*</font>
									    </td>
							        </tr>
							        <tr>
								        <td class="text">所属部门：</td>
								        <td align="left">
									        <input  id="user_id"  type="text"/>
									    </td>
								        <td class="text">电话：</td>
								        <td align="left">
									        <input id="user_name" type="text" />
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
											        <tr bgcolor="#e0edff">
												        <td style="height: 18px">G01-2002-DQ11-1</td>
												        <td style="height: 18px"><img style="width:23px;height:17px;" src="images/type_doc.gif" title="文件"/>测试题名1</td>
												        <td style="height: 18px">查档</td>
												        <td align="center" style="height: 18px"><a href="#" onClick="return confirm('确认移除？');">移除</a></td>
												   </tr><tr bgcolor="#e0edff">
												        <td>G01-2002-DQ11-2</td>
												        <td><img style="width:23px;height:17px;" src="images/type_doc.gif" title="文件"/>测试题名2</td>
												        <td>借档</td>
												        <td align="center"><a href="#" onClick="return confirm('确认移除？');">移除</a></td>										   </tr>
												   <tr bgcolor="#e0edff">
												        <td>G01-2002-DQ11-3</td>
												        <td><img style="width:23px;height:17px;" src="images/type_file.gif" title="案卷" />测试题名3</td>
												        <td>借档</td>
												        <td align="center"><a href="#" onClick="return confirm('确认移除？');">移除</a></td>										   </tr>
												   <tr bgcolor="#e0edff">
												        <td>G01-2002-DQ11-4</td>
												        <td><img style="width:23px;height:17px;" src="images/type_file.gif" title="案卷"/>测试题名4</td>
												        <td>原文</td>
												        <td align="center"><a href="#" onClick="return confirm('确认移除？');">移除</a></td>										   </tr>
												   <tr bgcolor="#e0edff">
												        <td>G01-2002-DQ11-5</td>
												        <td><img style="width:23px;height:17px;" src="images/type_file.gif" title="案卷"/>测试题名5</td>
												        <td>原文</td>
												        <td align="center"><a href="#" onClick="return confirm('确认移除？');">移除</a></td>										   </tr>
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
