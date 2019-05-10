<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>高校档案管理系统</title>
  </head>
 <frameset rows="108,*" frameborder="no" border="0" framespacing="0">
  <frame src="head.jsp" name="head" scrolling="No" noresize="noresize" id="head" />
  <frame src="blank.html" name="mid" id="mid" />
</frameset>
<noframes>
<body>
  <c:if test="${sessionScope.userInfo == null}">
	  <c:redirect url="/loginOvertime.jsp"></c:redirect>
  </c:if>
</body>
</noframes></html>

