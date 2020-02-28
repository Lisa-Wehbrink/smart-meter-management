package de.tub.as.smm;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.tub.as.smm.dao.LoginDao;
import de.tub.as.smm.models.Login;

/**Redirects meter & meter creation pages if not logged in.
 * 
 * @author Lisa
 *
 */
@WebFilter({"/meter.jsp", "/meter-creation.jsp"})
public class LoginFilter implements Filter {
	
	@EJB
	LoginDao loginDao;

	/**
	 * Creates redirect URL, checks for session & login and redirects if necessary.
	 */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {    
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
        HttpSession session = request.getSession(false);
        String url = request.getContextPath() + "/guardian.jsp";
        
        List<Login> users = loginDao.loggedIn();
        boolean exist = false;
        if(users != null && users.size() > 0) 
        	exist = true;
        
        boolean loggedIn = (session != null) && exist;
        boolean tryLogin = request.getRequestURI().equals(url);

        if (loggedIn || tryLogin) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect(url);
        }
    }

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}