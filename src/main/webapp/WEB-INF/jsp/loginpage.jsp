<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%
String path = request.getContextPath();
System.out.println(path);
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
  <h1>Login</h1>  
  
    <div id="login-error">${error}</div>  
  
    <form action="login" method="post">  
  
        <p>  
            <label for="j_username">Username</label> <input id="j_username"  
                name="j_username" type="text" />  
        </p>  
  
        <p>  
            <label for="j_password">Password</label> <input id="j_password"  
                name="j_password" type="password" />  
        </p>  
  		<%-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>  --%>
        <input type="submit" value="Login" />  
  
    </form>  
  </body>
</html>
