package de.tub.as.smm;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.tub.as.smm.dao.LoginDao;
import de.tub.as.smm.dao.UserDao;
import de.tub.as.smm.models.Login;
import de.tub.as.smm.models.User;

/**
 * Servlet for login page
 * @author Lisa
 *
 */
@WebServlet("/guardian")
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	// Injected DAO EJB:
    @EJB
   UserDao userDao;
    @EJB
   LoginDao loginDao;
    
    
    /** forwards request / attributes set in doPost, if login unsuccessful
     * 
     */
    @Override
    protected void doGet(
        HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	
    	
    	request.getRequestDispatcher("/guardian.jsp").forward(request, response);
        
    }
 
    /**
     * Retrieves name entered, checks if user exists, password is correct or someone is logged in.
     * Logs in if user exists, password is correct & no login present
     *  & redirects to meter.jsp, displays error message otherwise.
     */
    @Override
    protected void doPost(
        HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	String name = request.getParameter("name");
    	String pw = request.getParameter("pw");
    	boolean valid = false;
    	boolean pwError = false;
    	boolean loggedIn = false;
    	
    	
    	if(name != null && pw != null) {
    		User user = userDao.find(name);
    		if(user != null) {
    			valid = userDao.checkPassword(pw, name);
    			pwError = !userDao.checkPassword(pw, name);
    		}
    		if(loginDao.loggedIn() != null && loginDao.loggedIn().size() > 0) {
    			loggedIn = true;
    			valid = false;
    		}
    	}
    	
    	if(valid) {
    		loginDao.persist(new Login(name));
    		response.sendRedirect(request.getContextPath() + "/index.jsp");
    	} else if(pwError) {
    		request.setAttribute("error", 
    				"Passwort falsch, bitte versuche es erneut.");
            doGet(request, response);
    	} else if(loggedIn) {
    		request.setAttribute("error", "Bitte logge den bisherigen User aus,"
    				+ " bevor du dich neu einloggst.");
    		doGet(request, response);
    	} else {
    		request.setAttribute("error", 
    				"Login unbekannt, bitte versuche es erneut "
    				+ "oder registriere dich.");
            doGet(request, response);
    	}
    }
}
