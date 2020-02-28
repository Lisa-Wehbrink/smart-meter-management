<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@page import="java.util.*,de.tub.as.smm.models.Meter"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Smart Meter Creation</title>
<link rel="stylesheet" type="text/css" href="style.css"/>
	
</head>
<body>
	<div id="header"><h1>Dein Smart Meter</h1><br></div>
    <jsp:include page="nav.jsp"/>
    
    <div id="webpage">
    
    <div id="hint">
    	<p>Bitte gib die Daten deines Smart Meters an, um eine neue Unterseite zu erstellen.</p>
    </div>
    
    <div id="creation">
    	<form method="POST" action="meter-creation" id="meter-creator">
			Geraetekennung: <br>
			<input type="text" name="deviceID" id="deviceID" required/><br>
			Maximale Belastung: <br>
			<input type="number" name="load" id="load" min="50" max="100" required/><br>
			Beschreibung: <br>
			<input type="text" name="label" id="label" required/> <br>
			<input type="submit" value="Speichern"/>
		</form>
    </div>
    <hr>
    
    <div id="new-meter">
    	<ol>
    <%
    @SuppressWarnings("unchecked")
    List<Meter> meters = (List<Meter>) request.getAttribute("meters");
    
    if(meters != null && meters.size() > 0) {
    	for (Meter meter : meters) {
    		String label = meter.getLabel();
    		String device = meter.getDeviceID();
    		String output = label + " (" + device + ")";
			%>
			<li><%=output%></li>
			<%
				}
				}
				
			%>
		</ol>
	
	<%
	String error = (String) request.getAttribute("error");
	if(error != null) {
		%> <p> <%=error %></p> <%
	}
	%>
    </div>
    
    
    </div>

</body>
</html>