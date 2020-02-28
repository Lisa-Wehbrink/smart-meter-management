<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@page import="java.util.*,de.tub.as.smm.models.Meter"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
</head>
<body>
<div id="content">

			<h2>Smart Meter</h2>
			<table>
				<tr>
					<th>Beschreibung</th>
					<th>Geraetekennung</th>
				</tr>
				
				<%
				@SuppressWarnings("unchecked")
				List<Meter> meters = (List<Meter>) request.getAttribute("meters");
				
				if(meters != null) {
					for(Meter meter : meters) {
						String label = meter.getLabel();
						String deviceID = meter.getDeviceID();
						int id = meter.getId();
						
						%> 
						<tr>
						<td><a href="meter.jsp?id=<%=id %>"> <%=label %> </a></td>
						<td><a href="meter.jsp?id=<%=id %>"> <%=deviceID %> </a></td>
						</tr>
						<%
					}
				}
				%>
				
			</table>
</div>

</body>
</html>