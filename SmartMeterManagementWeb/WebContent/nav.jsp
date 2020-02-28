<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@page import="java.util.*,de.tub.as.smm.models.Meter"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

<div id="nav">
		<ul>
			<li><a href="index.jsp">Home</a></li>
			<hr>
			<li><a href=guardian.jsp>Login</a></li>
			<li><a href="user.jsp">Register</a></li>
			<hr>
			<li><a href="meter-creation.jsp">Neues Smart Meter</a>
			<hr>
			
			
            <%
            @SuppressWarnings("unchecked")
            List<Meter> meters = (List<Meter>) request.getAttribute("meters");
            
            if(meters != null && meters.size() > 0) {
            	for(Meter meter : meters) {
            		int id = meter.getId();
            		String label = meter.getLabel();
            		%> <li><a href="meter.jsp?id=<%=id %>"> <%=label %> </a></li> <%
            		
            	}
            }
            
            %>
            
            <hr>
            <li><a href="<%=request.getContextPath()%>/log-me-out">Logout</a></li>
		</ul>
	</div>
</body>
</html>