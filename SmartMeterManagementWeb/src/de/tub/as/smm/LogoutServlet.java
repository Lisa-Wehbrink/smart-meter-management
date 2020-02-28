package de.tub.as.smm;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.tub.as.smm.dao.LoginDao;

/**
 * Handles logout
 * @author Lisa
 *
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	LoginDao loginDao;
	
	/**
	 * Calls logout (LoginDao), then redirects to index.
	 */
	@Override
    protected void doGet(
        HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
    	loginDao.logout();
    	response.sendRedirect("index.jsp");
    }

}
