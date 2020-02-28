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

import de.tub.as.smm.dao.MeterDao;
import de.tub.as.smm.dao.MeterDataDao;
import de.tub.as.smm.models.Meter;

/**Populates navigation bar with stored meters & links
 * Populates MeterData table in meter.jsp with stored data.
 * Adds meter data (title etc) to meter.jsp
 * 
 * @author Lisa
 *
 */
@WebFilter("/*")
public class NavFilter implements Filter {
	
	@EJB
	MeterDao meterDao;
	@EJB
	MeterDataDao dataDao;

	/**
	 *Creates initial two meters if not present, sets attribute "meters" & title etc.
	 *Retrieves ID to get filtered MeterData (for meter page)
	 */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {    
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        	
	        if(meterDao.findMeter(1) == null)
				meterDao.persist(new Meter(1, "Smart Meter 1", "AB12345678", 75));
			if(meterDao.findMeter(2) == null)
				meterDao.persist(new Meter(2, "Smart Meter 2", "YZ87654321", 80));
			
        	request.setAttribute("meters", meterDao.getAllMeters());
        	List<Meter> meters = meterDao.getAllMeters();
        	
        	
        	String temp = request.getParameter("id");
        	if(temp != null) {
        		int id = Integer.parseInt(temp);
        		request.setAttribute("data", dataDao.getFilteredData(id));
        	}
        	
        	if(meters != null && meters.size() > 0 && temp != null) {
        		int id = Integer.parseInt(temp);
    			for(Meter meter : meters) {
    				if(id == meter.getId()) {
    					request.setAttribute("title", meter.getLabel());
    					request.setAttribute("device", meter.getDeviceID());
    					int load = meter.getLoad();
    					
    					request.setAttribute("load", load);
    					request.setAttribute("volt", meter.getRandom(220, 240));
    					
    					double amp = meter.getRandom(0, (load + 5));
    					boolean warning = meter.getWarning(amp);
    					request.setAttribute("amp", amp);
    					if(warning)
    						request.setAttribute("warning", "Vorsicht, Stromstärke übersteigt die"
    								+ " Belastungsgrenze." );
    				}
    			}
    		}
        	
            chain.doFilter(request, response);
      
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