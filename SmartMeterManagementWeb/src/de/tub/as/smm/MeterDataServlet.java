package de.tub.as.smm;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.tub.as.smm.dao.LoginDao;
import de.tub.as.smm.dao.MeterDataDao;
import de.tub.as.smm.models.Login;
import de.tub.as.smm.models.MeterData;

/**
 * Servlet for meter data
 * @author Lisa
 *
 */
@WebServlet("/meter")
public class MeterDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Injected DAO EJB:
    @EJB
    MeterDataDao meterDao;
    @EJB
    LoginDao loginDao;
 
    /**
     * Retrieves ID for meter that requested data, redirects to that meters 
     * URL (/meter.jsp?=id=id)
     */
    @Override
    protected void doGet(
        HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	String no = request.getParameter("mid");
    	int id = Integer.parseInt(no);
    	
    	
    	String redirect = "/meter.jsp?id=" + id;
    	
    	response.sendRedirect(request.getContextPath() + redirect);
    }
 
    /**
     * Retrieves form input from meter.jsp, ID from request
     *  & username from loginDao and stores data.
     */
    @Override
    protected void doPost(
        HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        // Handle a new guest:
        String input = request.getParameter("kwh");
        String no = request.getParameter("mid");
        List<Login> logins = loginDao.loggedIn();
        
        if (input != null && logins != null && logins.size() > 0 
        		&& no != null) {
        	Login user = logins.get(0);
        	double kwh = Double.parseDouble(input);
        	int id = Integer.parseInt(no);
            meterDao.persist(new MeterData(kwh,user,id));
        }
 
        // Display the list of guests:
        doGet(request, response);
    }
    
    

}
