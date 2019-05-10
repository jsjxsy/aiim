<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>公文归档</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/common.css"/>
    <script type="text/javascript" src="js/common.js"></script>
   <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.form.js"></script>
 	<script type="text/javascript" src="dwr/interface/OfficialArchivesInfoManageDWR.js"></script>
   <script type="text/javascript" src="dwr/util.js"></script>
   <script type="text/javascript" src="dwr/engine.js"></script>
    <script type="text/javascript">
    if (typeof window['DWRUtil'] == 'undefined') 
        window.DWRUtil = dwr.util; 
      $(document).ready(function(){
        $("#OK").bind("click",function(){
           archived();
        });
        $("#cancel").bind("click",function(){
           window.close();
        });
        
      });
   //进行归档
   function archived(){
	   var NBXH = $("#NBXH").val();
	   var officialArchivesTypeID = $("#officialArchivesTypeID").val();
	   var archivesTypeID = $("#archivesTypeID").val();
	   var archivesFondID = $("#archivesFondID").val();
	   var childArchivesTypeID = $("#childArchivesTypeID").val();
	 
	   OfficialArchivesInfoManageDWR.archiviedOfficialArchivesInfos(NBXH,officialArchivesTypeID,archivesTypeID,childArchivesTypeID,archivesFondID,ArchivedCallBack);
	  
	   }
   
     function ArchivedCallBack(dd){
         if(dd==true){
        	 window.returnValue = $("#childArchivesTypeID").val();
             alert("归档成功！");
             }else{
			alert("归档失败");
           }

         window.close();
     }
    
	//获取子节点
     function getChildArchivesType(){
         if(DWRUtil.getValue(archivesTypeID) == -1){
             alert("请选择");
             }else{
            	 OfficialArchivesInfoManageDWR.findchildArchivesTypeByArchivesTypeID(DWRUtil.getValue(archivesTypeID),getChildArchivesTypeCallBack);
            }
         }
     function getChildArchivesTypeCallBack(data){
    	 DWRUtil.addOptions(childArchivesTypeID,data);   
     }


    </script>
  </head>
  
  <body style="margin:0px; background-color:#f9f9f9;">
  <input type="hidden" id="officialArchivesTypeID" name="officialArchivesTypeID" value="<s:property value="#request.officialArchivesTypeID"/>">
  <input type="hidden" id="NBXH" name="NBXH" value="<s:property value="#request.NBXH"/>">
	<table width="100%" height="100%"  cellpadding="0" cellspacing="0" >
	<tr>
			<td class="text">全宗编号</td>
			<td>
				<select style="width: 150px;" id="archivesFondID" name="archivesFondID">	
					<s:iterator value="#request.archivesFonds" var="archivesFond">
						<option value="<s:property value="ID"/>"><s:property value="name"/></option>
					</s:iterator>
				</select>				
			</td>
		</tr>
		<tr>
			<td class="text">档案门类</td>
			<td>
				<select style="width: 150px;" id="archivesTypeID" name="archivesTypeID" onchange="getChildArchivesType()">	
				    <option value="-1">---请选择---</option>
					<s:iterator value="#request.archivesTypes" var="archivesType">
						<option value="<s:property value="ID"/>"><s:property value="name"/></option>
					</s:iterator>
				</select>				
			</td>
		</tr>
		<tr>
			<td class="text">二级类目</td>
			<td>
				<select id="childArchivesTypeID"  name="childArchivesTypeID" style="width: 150px;">
				</select>
			</td>
		</tr>
		<tr>
		  <td class="text"></td>
			<td>
				<input type="button" id="OK" name="OK" class="button" value="确    定" />&nbsp;&nbsp;&nbsp;
				<input type="button" id="cancel" name="cancel" class="button" value="取    消"/>
			</td>
		</tr>
	</table>
  </body>
</html>
