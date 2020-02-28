package de.tub.as.smm.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.persistence.TypedQuery;
import de.tub.as.smm.models.Meter;

/**
 * Stores meters
 * @author Lisa
 *
 */
@Stateless
public class MeterDao {
	
	@PersistenceContext private EntityManager em;

	/**
	 * 
	 * @param meter
	 */
	public void persist(Meter meter) {
		em.persist(meter);
	}
	
	/**
	 * Returns all stored meters ordered by their ID.
	 */
	public List<Meter> getAllMeters() {
		TypedQuery<Meter> query = em.createQuery(
				"SELECT m FROM Meter m ORDER BY m.id", Meter.class);
		
		return query.getResultList();
	}
	
	/**
	 * Finds specific meter by its unique ID.
	 * @param id
	 * @return Meter (if found) || null
	 */
	public Meter findMeter(int id) {
		String q = "SELECT m FROM Meter m WHERE m.id = :id";
		
		TypedQuery<Meter> query = em.createQuery(q, Meter.class);
    	query.setParameter("id", id);
    	
    	List<Meter> results = query.getResultList();
    	if(results != null && results.size() > 0)
    		return results.get(0);
    	else
    		return null;
    	
	}
	
	/**
	 * Retrieves next ID (in order starting at 2)
	 * @return ID (int)
	 */
	public int assignID(){
		List<Meter> meters = getAllMeters();
		int id = 1;
		
		if(meters != null && meters.size() > 0 )
			id +=  meters.size();
		
		return id;
	}
}
