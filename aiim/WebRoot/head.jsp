<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<title>高校档案管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script type="text/javascript">
		<!--
		function showleft(obj) {
		    //var obj=document.getElementById("show");
		    
		    if(obj==null) {
		        return;
		    }
		    if (obj.getAttribute("alt")=="隐臧左边菜单") {
			    top.mid.cols="0,*";
			    obj.setAttribute("alt","显示左边菜单");
			    obj.src="images/header10.gif";
		    }
		    else {
			    top.mid.cols="200,*";
			    obj.setAttribute("alt","隐臧左边菜单");
			    obj.src="images/header10b.gif";
		    }
		}
		
		
		//单击二级标题改变标题的字粗
	/*	function  clickTitle()
		{
			var aObjs=document.getElementsByTagName('a');
			for(var i=0;i<aObjs.length;i++){
				aObjs[i].style.fontWeight='normal';
			}
			this.style.fontWeight='bold';
		}
		
		//初始化二级标题的单击事件
		function initialTitle(){
			showMenu(0);			
			var aObjs=document.getElementsByTagName('a');
			for(var i=0;i<aObjs.length;i++){
				//aObjs[i].onclick=clickTitle;
			}

		}
		
	     }*/
		//-->
	</script>
	<script type="text/javascript">
						       
						    </script>
	<style type="text/css">
	.HeadBg 
	{ 
	   background-color:#d8e6ff;
	   border-bottom:solid 2px #bbbbbb;
	   font-size: 12px;
	}
	body
	{
	   margin: 0px 0px 0px;
	}
	.menu
	{
	  font-size: 12px;
	}
	.menu td
	{
	  cursor: pointer;
	}
	.menu1
	{
	   width:100%;
	   background-color:#a3c9ff; 
	   display:block; 
	   height: 11px;
	}
	.menu2
	{
	  width:100%;
	  background-color:#a3c9ff; 
	  display:none; 
	  height: 11px;
	}
	a
	{
	   font-size: 12px;
	}
	A:link	{	
	text-decoration:none;
	color:	#3333cc;
	}	
		
    A:visited	{	
	text-decoration:none;
	color:	#333399;
	}	
		
    A:active	{	
	text-decoration:none;
	color:#333399;
	}	
		
    A:hover	{	
	text-decoration:underline;
	color:red;
	}
   </style>
    </head>
	<body >
	<c:if test="${sessionScope.userInfo == null}">
       <c:redirect url="/loginOvertime.jsp"></c:redirect>
    </c:if>
            <table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-right:solid 2px #bbbbbb;">
                <tr>
                    <td style="width:181px;"><img width="181" alt="" src="images/logo.jpg" /></td>
                    <td style="width:589px;"><img  alt="" src="images/header03.jpg"  height="74px"/></td>
                    <td style="background-image:url(images/header02.jpg); padding-top:38px; padding-right:4px;" align="right"></td>
                    <td style="width:66px;" valign="top"><img alt="" src="images/header01.jpg"/></td>
                </tr>
                
            </table>
            <table width="100%" cellpadding="0" cellspacing="0" style="border-right:solid 2px #bbbbbb;">
                <tr>
                    <td align="left" class="HeadBg" style="width: 170px; ">    
						当前用户：${sessionScope.userInfo.realName }
					</td>
					<td class="HeadBg">
					  <table  cellpadding="0" cellspacing="0" border="0" style="margin-top:2px;" align="right">
                          <tr>
                            <td height="11px">
                             <table border="0" cellpadding="0" cellspacing="0" align="right" class="menu">
						       <tr>
						        <s:iterator value="#session.userInfo.systemMenus" id="systemFeature" status="status">
						           <s:if test="#status.index == 0">
						              <script type="text/javascript">
						                 var oldMenuId = <s:property value="value.iD"/>;//记录历史点击菜单ID
						              </script>
						              <td id="<s:property value="value.iD"/>" name="p_Menu" onclick="showMenu(<s:property value="value.iD"/>)" style="background-color:#a3c9ff;" >&nbsp;&nbsp;<s:property value="value.title"/>&nbsp;&nbsp;</td><td><img src="images/2.gif"/></td>
						           </s:if>  
						           <s:elseif test="#status.index+1 == #session.userInfo.systemMenus.size">
						              <td id="<s:property value="value.iD"/>" name="p_Menu" onclick="showMenu(<s:property value="value.iD"/>)" >&nbsp;&nbsp;<s:property value="value.title"/>&nbsp;&nbsp;</td>
						           </s:elseif>
						           <s:else>
						              <td id="<s:property value="value.iD"/>" name="p_Menu" onclick="showMenu(<s:property value="value.iD"/>)">&nbsp;&nbsp;<s:property value="value.title"/>&nbsp;&nbsp;</td><td><img src="images/2.gif"/></td>
						           </s:else>
						         </s:iterator>
						       </tr>
						     </table>
                            </td>
                          </tr>
                          <tr>
                            <td style="height:11px;">
                                <s:iterator value="#session.userInfo.systemMenus" id="systemFeature" status="status">
                                   <s:if test="#status.index == 0">
                                     <div id="m<s:property value="value.iD"/>" class="menu1">
                                   </s:if>
						           <s:else>
						             <div id="m<s:property value="value.iD"/>" class="menu2">
						           </s:else>
						             <table border="0" cellpadding="0" cellspacing="0" align="center">
								       <tr>
								         <s:iterator value="value.childSystemFeatures" status="c_Status">	
								            <s:if test="#c_Status.last">
								              <td style="text-overflow:ellipsis; word-break:keep-all;" height="11px" id="<s:property value='value.iD'/>" onclick="changeMenuStyle(<s:property value='value.iD'/>)"><a href="<s:property value='value.menuURI'/>" target="mid"><s:property value="value.title"/></a></td>
								            </s:if>
								            <s:else>
								              <td style="text-overflow:ellipsis; word-break:keep-all;" height="11px" id="<s:property value='value.iD'/>" onclick="changeMenuStyle(<s:property value='value.iD'/>)"><a href="<s:property value='value.menuURI'/>" target="mid"><s:property value="value.title"/></a></td><td><img src="images/1.gif"/></td>
								            </s:else>	            
								         </s:iterator>
								        </tr>
								     </table>
						           </div>
						         </s:iterator>                   
                            </td>
                          </tr>
                        </table>
					</td>
               </tr>
           </table>
           <script type="text/javascript">
             //显示隐藏菜单
		  function showMenu(newMenuId){
		    //显示当前菜单ID
		    document.getElementById(newMenuId).style.backgroundColor="#a3c9ff";
		    if(document.getElementById("m"+newMenuId)){
		       document.getElementById("m"+newMenuId).style.display="block";
		    }
		    
		    if(oldMenuId == 0){
		      oldMenuId = newMenuId;
		    }
		    
		    //如果单击的不是当前菜单
		    if(oldMenuId != newMenuId){
		         document.getElementById(oldMenuId).style.backgroundColor="";
		         if(document.getElementById("m"+oldMenuId)){
		            //隐藏历史菜单
		            document.getElementById("m"+oldMenuId).style.display="none";
		         }	      
		     }
		    oldMenuId = newMenuId;//记录历史菜单ID
		   } 
		   
		   
		   //单击二级菜单改变样式
		   var c_OldMenuId = 0;
		   function  changeMenuStyle(c_NewMenuId)
			{
			    if(c_OldMenuId == 0){
			       c_OldMenuId = c_NewMenuId;
			    }
			    //alert('old:'+c_OldMenuId+'  '+"new:"+c_NewMenuId);
			    //选中子菜单字为粗体
				document.getElementById(c_NewMenuId).style.fontWeight='bold';
				if(c_OldMenuId != c_NewMenuId){
					if(document.getElementById(c_OldMenuId)){
					   //设置上一子菜单字体
					   document.getElementById(c_OldMenuId).style.fontWeight='';
					}
				}
				c_OldMenuId=c_NewMenuId;
			}
           </script>
     </body>
</html>
