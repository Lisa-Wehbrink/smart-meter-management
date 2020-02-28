package de.tub.as.smm;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.tub.as.smm.dao.MeterDao;
import de.tub.as.smm.models.Meter;

/**
 * Handles creation of new meters / display after submitting form.
 * @author Lisa
 *
 */
@WebServlet("/meter-creation")
public class CreationServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
		
		// Injected DAO EJB:
	    @EJB
	    MeterDao meterDao;
	 
	    
	    /**
	     * Sets attribute "meters" (list of all meters) & forwards request.
	     */
	    @Override
	    protected void doGet(
	        HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    	
	    	
	        // Display the list of guests:
	        request.setAttribute("meters", meterDao.getAllMeters());
	        request.getRequestDispatcher("/meter-creation.jsp").forward(request, response);
	    }
	 
	    
	    /**
	     * Retrieves form data & passes new meter to EJB
	     */
	    @Override
	    protected void doPost(
	        HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	 
	        // Handle a new guest:
	       String deviceID = request.getParameter("deviceID");
	       String label = request.getParameter("label");
	       String loadTemp = request.getParameter("load");
	       boolean valid = false;
	       int load = 0;
	       
	       if(deviceID != null) {
	    	   valid = deviceID.matches("^[A-Z]{2}[0-9]{8}");
	       }
	       
	       if(loadTemp != null) {
	    	  load = Integer.parseInt(loadTemp);
	       }
	        
	        if (valid && label != null && load > 0) {
	        	int id = meterDao.assignID();
	        	load = Integer.parseInt(loadTemp);
	        	meterDao.persist(new Meter(id, label, deviceID, load));
	        }
	        if(!valid)
	        	request.setAttribute("error", "Gerätekennung muss aus zwei Großbuchstaben und acht Zahlen bestehen."
	        			+ "(Wie AB12345678)" );
	     
	        
	        // Display the list of guests:
	        doGet(request, response);
	    }
}
