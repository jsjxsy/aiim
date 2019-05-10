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
    <title>高级查询</title>
    <base href="<%=basePath%>">    
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="Cache-Control" content="no-cache,  must-revalidate" />
	<meta http-equiv="expires" content="Wed,  26  Feb  1997  08:21:57  GMT" />
    <script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
    <link href="css/common.css" type="text/css" rel="stylesheet" />
    
    <script type="text/javascript" src="dwr/interface/IntegratedQueryAction.js"></script>
	<script type="text/javascript" src="dwr/util.js"></script>
	<script type="text/javascript" src="dwr/engine.js"></script>
	<script type="text/javascript">

	
	var conditions = new Array();
	
	//验证输入的条件值是否正确
	function checkvalue() {
	    var txt=document.getElementById("txtvalue");
	    var dpFields=document.getElementById("dpFields");
	    
	    if (txt) {
	        if (dpFields.options[dpFields.selectedIndex].value.split(",")[1]==2 || dpFields.options[dpFields.selectedIndex].value.split(",")[1]==3) {
	            if (isNaN(txt.value)) {
	                alert("比较数值请输入整数！");
	                return false;
	            }
	        }
	        else if(dpFields.options[dpFields.selectedIndex].value.split(",")[1]==4) {
	            return isDate(txt.value);
	        }
	        if (checkexist()==false) {
	            if (txt.value=="") {
	                if (confirm("您想查找空值吗？")) {
	                    return true;
	                }
	                else {
	                    return false;
	                }
	            }
	            return true;
	        }
	        else {
	            alert("条件列表中已存在该条件，请重新选择查询条件！");
	        }
	    }
	    return false;
	}
	
	//检查条件是否存在
	function checkexist() {
	    var list=document.getElementById("conditions");
	    var dpFields=document.getElementById("dpFields");
	    var dpOpreate=document.getElementById("dpOpreate");
	    var txtvalue=document.getElementById("txtvalue");
	    var result;
	    
	    if (!dpFields || !dpOpreate || !txtvalue) {
	        return false;
	    }
	    result=dpFields.options[dpFields.selectedIndex].text + " " + dpOpreate.options[dpOpreate.selectedIndex].text + " " + txtvalue.value;
	    if (list) {
	        for (i=0;i<list.options.length;i++) {
	            if (list.options[i].text.indexOf(result)>=0) {
	                if (list.options[i].text.substring(list.options[i].text.indexOf(result))==result) {
	                    return true;
	                }
	            }
	        }
	    }
	    return false;
	}
	
	//单击高级查询事件:将从页面获取的查询条件集合传递到父页面
	function advanceQuery(){
		var str_queryCondition="";
		alert(conditions.length);
		if(conditions.length<=0){
			alert('请输入查询条件！');
		}else{
			//str_queryCondition += conditions[0].dpFields + " " + conditions[0].dpOpreate + " " + "'" + conditions[0].txtValue + "'";
			for(i=0;i<conditions.length;i++){
				if(conditions[i].dpOpreate=='like' || conditions[i].dpOpreate=='not like'){
					conditions[i].txtValue= "%"+conditions[i].txtValue+"%";
				}
				str_queryCondition += " " + conditions[i].dpConnect + " " +  conditions[i].dpFields + " " + conditions[i].dpOpreate + " " + "'" + conditions[i].txtValue + "'";
			}
			window.returnValue = str_queryCondition;
			window.close();
			return false;
		}
		
	}

	//关闭窗口
    function closeDialog(){
        window.close();
        return false;
    }
	
	///////////以下是控制条件代码//////////////////
	$(document).ready(function(){
		$("#btAdd").bind("click",createConditions);
		$("#btDel").bind("click",removeCondition);
		$("#delAll").bind("click",removeAll);
	});
	
	//创建条件串并加入到列表中
	function createConditions(){
	   var columnName = $("#dpFields").val();//字段名
	   var archivesTypeId = $("#archivesTypeId").val();//档案分类号	 
	   var txtValue=$("#txtValue").val();//输入文本值
	   //DWR调用验证函数
	   IntegratedQueryAction.validateAdvanceQueryInput(archivesTypeId,columnName,txtValue,validateAdvanceQueryInputBack);
	  // alert(txtValue);	  
	}

	//DWR调用验证函数的回调函数
	function validateAdvanceQueryInputBack(data){		
		   var dpConnect=$('#dpConnect option:selected').val();
		   var dpConnectText=$('#dpConnect option:selected').text();
		   var dpFields=$('#dpFields option:selected').val();
		   var dpFieldsText=$('#dpFields option:selected').text();
		   var dpOpreate=$('#dpOpreate option:selected').val();
		   var dpOpreateText=$('#dpOpreate option:selected').text();
		   var txtValue=$("#txtValue").val();
		if(data==""){//判断返回值是否为空
			 if(checkvalue(txtValue)){					
				//将新输入的条件加入到条件列表中
				 conditions.push(new Condition(dpConnect,dpConnectText,dpFields,dpFieldsText,dpOpreate,dpOpreateText,txtValue));
			     //在列表框中刷新列表     
		         getOptions(conditions);
			    
			   }  
		}else{//如果不为空则表明出现错误信息，即将错误信息打印出来 
			alert(data);
		}
	}
	
	//删除所有条件
	function removeAll(){
	   conditions.splice(0,conditions.length);
	   $("#conditions").children().remove();
	}
	
	//删除某个条件
	function removeCondition(){
	  var index = $("#conditions option:selected").attr("id");
	  if(index){
	    conditions.splice(index,1);
	    //alert(conditions.length);
	    //删除所有并重新得到列表
        getOptions(conditions);
	  }  
	}
	
	//生成条件列表,并显示到页面中
	function getOptions(conditions){
	   //先清空列表
	   $("#conditions").children().remove();
	   for(var i=0;i<conditions.length;i++){
	     if(i==0){
	        $("#conditions").append("<option id="+i+">"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+conditions[i].dpFieldsText+" "+conditions[i].dpOpreateText+" "+conditions[i].txtValue+"</option>");
	     }else{
	        $("#conditions").append("<option id="+i+">"+conditions[i].dpConnectText+" "+conditions[i].dpFieldsText+" "+conditions[i].dpOpreateText+" "+conditions[i].txtValue+"</option>");
	     }
	   }
	}
	
	//条件对象
	//dpConnect:or/and；dpConnectText：并且/或；dpFields：字段名；dpFieldsText：字段文本；
	//dpOpreate：操作符；dpOpreateText：操作名称；txtValue：比较值
	function Condition(dpConnect,dpConnectText,dpFields,dpFieldsText,dpOpreate,dpOpreateText,txtValue){
	   this.dpConnect=dpConnect;
	   this.dpConnectText=dpConnectText;
	   this.dpFields=dpFields;
	   this.dpFieldsText=dpFieldsText;
	   this.dpOpreate=dpOpreate;
	   this.dpOpreateText=dpOpreateText;
	   this.txtValue=txtValue;
	}
	
	</script>
</head>
<body>
<%-- 隐藏参数 档案分类编号 --%>
	<input type="hidden" id="archivesTypeId" name="archivesTypeId" value="${requestScope.archivesTypeId }"/>
    <form id="form1">
    
    <input id="TableName" type="hidden" style="width:0px; height:0px"  />
    <input id="preFirst" type="hidden" style="width:0px; height:0px"  />
    <table style="width:99.9%; height:100%; background-color:#d2e2ff;">
        <tr>
        <td align="center" valign="middle">
    <fieldset style="width:98%; height:60%; background-color:#d2e2ff;margin-top:10px;">
    <table width="100%">
        <tr>
            <td>
                <table width="100%">
                    <tr>
                        <td style="font-size:12px;" align="left">连接关系</td>
                        <td style="font-size:12px;" align="left">查询字段</td>
                        <td style="font-size:12px;" align="left">比较关系</td>
                        <td style="font-size:12px;" align="left">比较数值</td>
                    </tr>
                    <tr>
                        <td align="left">
                            <select id="dpConnect" class="back_border">
                                <option value="and">并且</option >
                                <option  value="or">或者</option >
                            </select >
                        </td>
                        <td align="left">
                        <s:select id="dpFields" list="%{#request.dataItems}" listKey="key" listValue="value.displayText" theme="simple" cssStyle="width:80px;margin:0px;padding:0px;" />
                        
                       </td> 
                        <td align="left">
                            <select id="dpOpreate">
                                <option  value="=">等于</option >
                                <option  value="<>">不等于</option >
                                <option  value=">">大于</option >
                                <option  value=">=">大于等于</option >
                                <option  value="<">小于</option >
                                <option  value="<=">小于等于</option >
                                <option  value="like">包含</option >
                                <option  value="not like">不包含</option >
                            </select >
                        </td>
                        <td align="left">
                            <input type="text" id="txtValue" style="width:80px;" /></td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td>
            <table width="100%">
                <tr>
                    <td style="width:50%; font-size:12px;" align="left" >条件列表：</td>
                    <td align="right">
                        <input type="button" value="添加条件" class="button" id="btAdd"/></td>
                </tr>
            </table>
            </td>
        </tr>
        <tr>
            <td>
               <select id="conditions" style="width:480px; overflow:auto;" size="7">
                                
               </select>
            </td>
        </tr>
        <tr>
            <td>
            <table width="100%">
                <tr>
                    <td style=" width:50%" align="right">
                        <input id="delAll"" type="button" class="button"  value="清空条件" />&nbsp;&nbsp;
                        <input id="btDel" type="button" class="button" value="删除条件" />&nbsp;
				    </td>
                </tr>
            </table>
            </td>
        </tr>
    </table>
    </fieldset>
    <table>
        <tr>
            <td align="center">
            <input type="button" class="button"  value="查    询" / onclick="advanceQuery()">&nbsp;&nbsp;
           <input type="button" class="button"  value="关    闭" onClick="closeDialog()"/></td>
        </tr>
    </table>
            </td>
        </tr>
    </table>
    </form>
</body>
</html>
