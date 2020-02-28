<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@page import="java.util.*,de.tub.as.smm.models.MeterData"%> 
  <%@page import="java.util.*,de.tub.as.smm.models.Meter"%>  
 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Smart Meter</title>
<link rel="stylesheet" type="text/css" href="style.css"/>
	<script type="text/javascript" src="script.js"></script>


</head>

<body onload="getRandom(0,80)">
    <div id="header"><h1>Dein Smart Meter</h1><br></div>
    <jsp:include page="nav.jsp"/>
    
    <div id="webpage">
        
        <div id="meter">
            
            <div id="pic">
            		<%
            		String id = request.getParameter("id");
            		String title = (String) request.getAttribute("title");
            		String device = (String) request.getAttribute("device");
            		int load = (int) request.getAttribute("load");
            		double volt = (double) request.getAttribute("volt");
            		double amp = (double) request.getAttribute("amp");
            		String warning = (String) request.getAttribute("warning");
            				
            		if(id != null) {
            			int met = Integer.parseInt(id);
            			request.setAttribute("met", met);
            		}
            		
            		
            		%>
                    <h2><%=title%></h2> <br>
                    <img src="Smart Meter.jpg" alt="Smart Meter" width="310" height="307">
            </div> <br>

            <div id="info"> 
                
                <%
                if(warning != null) {
        			%> <p id="warning"><%=warning %></p> <%
        		}
                %>
                <p><b>Gerätekennung:</b> <%=device %></p>
                <p><b>Maximale Belastung: </b><%=load %> A</p>

                <div id="random">
                    <p><b> Spannung: </b> <p id="spannung"><%=volt %></p> V</p>
                    <br><br>
                    <p><b> Stromstärke: </b> <p id="belastung"><%=amp %></p> A</p>
                </div>

            </div> 
        </div>
        <div id="ablesewerte">
           	<h2>Ablesewerte</h2>
            <div id="input">
                <p>Gib hier deinen aktuellen Zählerstand an:</p>
                <form method="POST" action="meter">
                    Verbrauch (in kWh):<br>
                    <input type="number" name="kwh" id="kwh" maxLength=12 step="0.1" required/><br>
                    <input type="submit" value="Absenden"/><br>
                    <input type="hidden" id="mid" name="mid" value="<%=id%>">
                </form>
                
            </div>
			
            <div id="output">
            
                <table id="result">
				<tr>
					<th>Datum</th>
					<th>Wert</th>
                    <th>Nutzer</th>
				</tr>
				<%
				@SuppressWarnings("unchecked")
				List<MeterData> data = (List<MeterData>) request.getAttribute("data");
				
				if (data != null && data.size() > 0) {
					for (MeterData d : data) {
						
					%>
					<tr><%=d%></tr>
					<%
					}
				}
				%>
				</table>
            </div>
        </div>
        
    </div>
    <div id="footer">
		<p>Gruppe 17</p>
	</div>
</body>

</html>