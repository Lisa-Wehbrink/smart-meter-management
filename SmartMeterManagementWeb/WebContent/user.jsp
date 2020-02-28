<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@page import="java.util.*,de.tub.as.smm.models.User"%>	
<jsp:useBean id="login" scope="session" class="de.tub.as.smm.dao.UserDao" />   	
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Management</title>

<link rel="stylesheet" type="text/css" href="style.css"/>
</head>

<body>
<div id="header"><h1>Registrierung</h1><br></div>
    <jsp:include page="nav.jsp"/>
    
    <div id="webpage">
    	<div id="register">
			<form method="POST" action="user">
				Name: <br>
				<input type="text" name="name" required/> <br>
				Passwort: <br>
				<input type="password" name="pw" required/><br>
				<input type="submit" value="Add" />
				
			</form>
			<p> Zum <a href="guardian.jsp">Login</a></p>
			<p>
			<%
			String error = (String) request.getAttribute("error");
			if(error != null) {
				%><b><%=error %></b> <%
			}
			%>
		</div>
		<hr>
		<div id="user">
			<ol>
				<%
					@SuppressWarnings("unchecked")
					List<User> users = (List<User>) request.getAttribute("user");
					if (users != null) {
						for (User user : users) {
				%>
				<li><%=user%></li>
				<%
					}
					}
					
				%>
			</ol>
		</div>
		<hr>
	</div>

	<div id="footer">
		<p>Gruppe 17</p>
	</div>
</body>
</html>