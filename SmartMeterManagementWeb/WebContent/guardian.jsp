<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@page import="java.util.*,de.tub.as.smm.models.Login"%>
 
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>AnwSys Aufgabe 2</title>

<link rel="stylesheet" type="text/css" href="style.css"/>
</head>
<body>
	<div id="header"><h1>Login</h1><br></div>
   	<jsp:include page="nav.jsp"/>
   	
    <div id="webpage">
		<div id="hint">
			<p>Bitte logge dich ein, um die Smart Meter einsehen & bearbeiten 
			zu können.<p>
		</div>
		<div id="login">
			<form method="POST" action="guardian">
			Name: <br>
			<input type="text" name="name" id="name" required/><br>
			Passwort: <br>
			<input type="password" name="pw" required/><br>
			<input type="submit" value="Login"/>
			<input type ="button" value="Registrieren" 
			onclick="javascript:window.location='user.jsp';"/>
			</form>
			
			<%
			String msg = (String) request.getAttribute("error");
			if(msg != null) {
				%> <p id="loginfail"> <%=msg %></p>
			<%
			}
			%>
		</div>
	</div>
	
	<div id="footer">
		<p>Gruppe 17</p>
	</div>
	
</body>
</html>