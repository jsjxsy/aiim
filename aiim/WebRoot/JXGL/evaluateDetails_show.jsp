<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="taglib.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>考核明细信息显示</title>
    
		<link href="css/Styles.css" type="text/css" rel="stylesheet" />
		<link href="css/Login.css" type="text/css" rel="stylesheet" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="Cache-Control" content="no-cache,  must-revalidate" />
		<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="js/popup.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript" src="js/jquery.form.js"></script>
		
		<style type="text/css">
		  	.tableHead{font-weight:bold; text-align:center; padding:4px 0 0 0px;}
			.tableTitle {font-weight:bold; text-align:left; padding:4px 0 0 5px;}
			/*设置标题底色*/
			.bgTitle { background-color:#a3c9ff;height:25px;}
			/*设置表格顶部框底色*/
			.borderTop {border-top:#104da6 1px solid;	border-left:#104da6 1px solid;	border-right:#104da6 1px solid;}
			.tableHead{font-weight:bold; text-align:center; padding:4px 0 0 0px;}
			.tbody tr{background-color:#e0edff;}
		</style>
		
		<script type="text/javascript">
		//获取后台的考核项id数组
		var detailIds = '${detailIds}';
		var detailIdArray = detailIds.split(",");

		$(function(){
			$('#myForm').ajaxForm({
				beforeSubmit:function(){
					var flag = true;
					var maxScore = new Number();
					var item;
					//判断每项评分 是否为空
					if(detailIds) {
						for(var i=0; i<detailIdArray.length;i++) {
							item = $('#DD_EvaluateItemScore'+detailIdArray[i]);
							if(item.val() == '') {
								alert('评分不能为空，请重新输入！');
								item.focus();
								flag = false;
								break;
								
							} else { ////判断每个考核评分是否超过最大值
								
								maxScore = $('#DD_EvaluateItemId'+detailIdArray[i]).attr('alt');
								if(new Number(item.val()) >= maxScore) {
									flag = false;
									alert('评分超过最大值，请重新输入！');
									item.focus();
									break;
								}
							}
						}
					}
					if(flag) {
						//判断评分等级 是否为空
						var evaluateLevelId = $('#evaluateLevelId');
						if(evaluateLevelId.val() == 0) {
							alert('请输入评分等级！');
							evaluateLevelId.focus();
							flag = false;
						}
					}
					return flag;
					
				},
			    success:showResponse,
			    error:function(XMLHttpRequest, textStatus){alert(textStatus+","+XMLHttpRequest.status);}
			});
		});

		function showResponse(xml){
			window.returnValue= '1';
			 window.close();
		}
		
			//计算总分 只有当所有评分都有值时才显示总分
			function computeScore() {
				var flag = true;
				var score = new Number();
				var item;
				var maxScore = new Number();
				$(detailIdArray).each(function(index, val) {
					item = $('#DD_EvaluateItemScore'+val);
					if(item.val() == '') {
						flag = false;
					}
					maxScore = $('#DD_EvaluateItemId'+val).attr('alt');
					if(new Number(item.val()) > maxScore) {
						flag = false;
					}
					if(flag) {
						score = score+new Number(item.val());
					} 
				});
				if(flag) {
					$('#score').val(score);
				}
				return;
			}

			//设置 每项评分 失焦是计算总分
			$(function() {
				$(detailIdArray).each(function(index, val) {
					var item = $('#DD_EvaluateItemScore'+val);
					if(item) {
						item.focusout(function() {
							computeScore();
						});
					}
				});
			});
			
		</script>
	</head>
	<body class="bg_color" style="margin-top:4px">
	<s:form id="myForm" action="evaluateManageAction_update" namespace="/JXGL">
		<s:hidden name="evaluateRegID" />
        	<div style="width:100%;overflow:auto; height:100%;">
				<table align="center" class="back_border" width="98%" style="height:80px;" cellpadding="0" cellspacing="0">
					    <tr>
					        <td class="bg_title bg_title4" align="center">&nbsp;<s:property value="#request.evaluateRegisterVO.realName"/> <s:property value="#request.evaluateRegisterVO.evaluateRegister.years"/>年 考核</td>
					    </tr>
					    <tr>
					        <td>
					            <table width="100%">
					            <s:iterator value="#request.evaluateRegisterVO.details" var="item">
					            <input type="hidden" id="DD_EvaluateItemId<s:property value="#item.evaluateItemID"/>" name="DD_EvaluateItemId<s:property value="#item.evaluateItemID"/>" alt="<s:property value="#item.maxScore"/>" value="<s:property value="#item.evaluateItemID"/>"/>
				            			<tr>
								        	<td><s:property value="#item.tag"/>(<s:property value="#item.maxScore"/>分):</td>
								        	<td>
								        	<input id="DD_EvaluateItemScore<s:property value="#item.evaluateItemID"/>" name="DD_EvaluateItemScore<s:property value="#item.evaluateItemID"/>" 
										        			type="text"  value='<s:if test="#item.score>=1"><s:property value="#item.score"/></s:if>' />
								        	</td>
								        </tr>
					            </s:iterator>     						        
							        <tr>
							        	<td>
							        		总&nbsp;&nbsp;分:
							        	</td>
							        	<td>
							        		<input id="score" type="text" name="score1" value='<s:if test="#request.evaluateRegisterVO.score>=1"><s:property value="#request.evaluateRegisterVO.score"/></s:if>' disabled="disabled"/>
							        	</td>
							        </tr>
							        <tr>
							        	<td>
							        		评分等级:
							        	</td>
							        	<td>
							        		<s:select id="evaluateLevelId" name="evaluateLevelId" list="evaluateLevels" headerKey="0" headerValue="**请选择**" listKey="iD" listValue="name" value="#request.evaluateRegisterVO.evaluateRegister.levelID"></s:select>
							        	</td>
							        </tr>
							         <tr>
								        <td class="text">&nbsp;综合评价：</td>
								        <td align="left" colspan="3">
								           <textarea id="content" name="content" rows="5" style="width:90%;">${evaluateRegisterVO.evaluateRegister.content}</textarea>
								        </td>
									</tr>
							      </table>
					        </td>
					    </tr>
					</table>
					<table width="100%">
				        <tr>
				          <td align="center">
				             <input type="submit" class="button15" value="提   交" />
				             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				             <input type="button" class="button15" value="取   消" onclick="javascript:window.close()"/>
				          </td>
				        </tr>
			      </table>
		</div>
	</s:form>
	</body>
</html>
