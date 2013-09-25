<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<title>SAPERION ECM Together</title>
  </head>

  <body>
    <h1>Welcome to SAPERION ECM Together</h1>
    <p>
        Find the REST services under <a href="<%= basePath %>api/1/"><%= basePath %>api/1/</a> <br>
    </p>
  </body>
</html>
