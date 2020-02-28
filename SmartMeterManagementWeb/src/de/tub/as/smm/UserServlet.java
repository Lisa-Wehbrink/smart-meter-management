package de.tub.as.smm;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.tub.as.smm.dao.UserDao;
import de.tub.as.smm.models.User;

/**
 * Servlet implementation class UserServlet
 */

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Injected DAO EJB:
    @EJB
    UserDao userDao;
 
    /**
     * Sets attribute "user" with all available users.
     */
    @Override
    protected void doGet(
        HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        // Display the list of guests:
        request.setAttribute("user", userDao.getAllUsers());
        request.getRequestDispatcher("/user.jsp").forward(request, response);
    }
 
    /**
     * Retrieves entered name and stores it as new user.
     */
    @Override
    protected void doPost(
        HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        // Handle a new guest:
        String name = request.getParameter("name");
        String pw = request.getParameter("pw");
        
        if (name != null && pw != null) {
        	User user = userDao.find(name);
        	
        	if(user == null)
        		userDao.persist(new User(name, pw));
        	else
        		request.setAttribute("error", "Der gewünschte Name ist leider schon vergeben. "
        				+ "Bitte wähle einen anderen.");
        }
        
        // Display the list of guests:
        doGet(request, response);
    }
}

