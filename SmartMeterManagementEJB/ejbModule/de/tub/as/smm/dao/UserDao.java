package de.tub.as.smm.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import de.tub.as.smm.models.User;

/**
 * Session Bean implementation class UserDaoEJB
 */
@Stateless
public class UserDao {

	// Injected database connection:
    @PersistenceContext private EntityManager em;
    
    /**Stores new user
     * 
     * @param user
     */
    public void persist(User user) {
        em.persist(user);
    }
    
    
    /**Checks if user (name) exists.
     * 
     * @param name
     * @return User or null
     */
    public User find(String name) {
    	String q = "Select u FROM User u WHERE u.name = :name";
    	TypedQuery<User> query = em.createQuery(q, User.class);
    	query.setParameter("name", name);
    	
    	List<User> results = query.getResultList();
    	if(results != null && results.size() > 0)
    		return results.get(0);
    	else
    		return null;
    	
    }
    
    /**Checks if user with given password and name exists
     * 
     * @param pw
     * @param name
     * @return boolean
     */
    public boolean checkPassword(String pw, String name) {
    	String q = "SELECT u FROM User u WHERE u.name = :name AND u.pw = :pw";
    	TypedQuery<User> query = em.createQuery(q, User.class);
    	
    	query.setParameter("name", name);
    	query.setParameter("pw", pw);
    	
    	List<User> results = query.getResultList();
    	if(results != null && results.size() > 0)
    		return true;
    	else
    		return false;
    }
    
    /**Retrieves all users
     * 
     * @return List<User>
     */
    public List<User> getAllUsers() {
        TypedQuery<User> query = em.createQuery(
            "SELECT u FROM User u ORDER BY u.id", User.class);
        return query.getResultList();
    }

}
