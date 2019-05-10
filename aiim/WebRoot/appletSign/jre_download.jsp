<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.OutputStream"%>
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

		<title>jre下载</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	</head>
	<body>
		<%
			OutputStream outputStream = null;
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			try {
				String filename = "jinstall-6u10-windows-i586.cab";
				filename = new String(filename.getBytes("iso8859-1"), "gb2312");
				response.setContentType("application/x-msdownload");
				response.setHeader("Content-disposition",
						"attachment; filename="+ new String(filename.getBytes("gb2312"),"iso8859-1"));
				bis = new BufferedInputStream(new FileInputStream(config.getServletContext().getRealPath("/appletSign/" + filename)));
				outputStream = response.getOutputStream();
				bos = new BufferedOutputStream(outputStream);
				byte[] buff = new byte[2048];
				int bytesRead;
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesRead);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (bis != null) {
					bis.close();
				}
				if (bos != null) {
					bos.close();	
				}
				if(outputStream!=null) {
					outputStream.close();
					outputStream = null;	
				}
				out.clear();
				out = pageContext.pushBody();
			}
		%>
	</body>
</html>