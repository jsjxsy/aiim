<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>用户登录</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	overflow:hidden;
}
-->
</style>
</head>

<body >
<form action="login/userLoginAction_login.action" name="form">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td bgcolor="#02395f">&nbsp;</td>
  </tr>
  <tr>
    <td height="607" align="center" background="images/login_02.gif"><table width="974" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="331" background="images/login_01.jpg">&nbsp;</td>
      </tr>
      <tr>
        <td height="116"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="393" height="116" background="images/login_05.gif">&nbsp;</td>
            <td width="174"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td height="81" background="images/login_06.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="24%"><div align="center"><font style="height:1;font-size:9pt; color:#bfdbeb;filter:glow(color=#1070a3,strength=1)">用户</font></div></td>
                    <td width="76%" height="25"><input type="text" name="userName" value="admin" style="width:125px; height:20px; background:#32a2e3; font-size:12px; border:solid 1px #0468a7; color:#14649f;"></td>
                  </tr>
                  <tr>
                    <td><div align="center"><font style="height:1;font-size:9pt; color:#bfdbeb;filter:glow(color=#1070a3,strength=1)">密码</font></div></td>
                    <td height="25"><input type="password" name="password" value="admin"  style="width:125px; height:20px; background:#32a2e3; font-size:12px; border:solid 1px #0468a7; color:#14649f;"></td>
                  </tr>
                </table></td>
              </tr>
              <tr>
                <td height="35"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="38" height="35"><img src="images/login_08.gif" width="38" height="35"></td>
                    <td width="46"><img src="images/login_09.gif" style="width:46px; height:35px; border: 0; margin-top: 0px; cursor: pointer;" onclick="submit()"/></td>
                    <td width="45"><img src="images/login_10.gif" style="width:46px; height:35px; border: 0; margin-top: 0px; cursor: pointer;" onclick="reset()"/></td>
                    <td width="45"><img src="images/login_00.gif" style="width:46px; height:35px; border: 0; margin-top: 0px; cursor: pointer;" onclick="loginByAnonymous()"/></td>
                  </tr>
                </table></td>
              </tr>
            </table></td>
            <td width="407" background="images/login_07.gif">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="160" background="images/login_12.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td bgcolor="#02609c">&nbsp;</td>
  </tr>
</table>
<script type="text/javascript">
  function submit(){
	  document.forms[0].submit();
  }
  function loginByAnonymous(){
     window.location.href="login/userLoginAction_loginWithAnonymous.action";
  }
  function reset(){
     this.form.reset();
  }
</script>
</form>
${message }
</body>
</html>

