package de.tub.as.smm.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import de.tub.as.smm.models.MeterData;

/**Stores meter data (kwh, date, meter ID and user who submitted data)
 * 
 * @author Lisa
 *
 */
@Stateless
public class MeterDataDao {

	// Injected database connection:
    @PersistenceContext private EntityManager em;
 
    /**Adds new data
     * 
     * @param data
     */
    public void persist(MeterData data) {
        em.persist(data);
    }
 
    /**Returns all available MeterData
     * 
     * @return List<MeterData>
     */
    public List<MeterData> getAllData() {
       TypedQuery<MeterData> query = em.createQuery(
    		   "SELECT m FROM MeterData m ORDER BY m.date", MeterData.class);
        return query.getResultList();
    }
  
    /**
     * Returns all data for one meter (found through ID)
     * @param id
     * @return List<MeterData>
     */
    public List<MeterData> getFilteredData(int id) {
    	String q = "SELECT m FROM MeterData m WHERE m.meterID = :id ORDER BY m.date";
    	TypedQuery<MeterData> query = em.createQuery(q, MeterData.class);
    	query.setParameter("id", id);
    	return query.getResultList();
    }

	
}
