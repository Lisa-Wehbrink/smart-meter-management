package de.tub.as.smm.dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import de.tub.as.smm.models.Login;

/**Stores who is currently logged in.
 * 
 * @author Lisa
 *
 */
@Stateful
public class LoginDao {
	
	@PersistenceContext private EntityManager em;
	
	/**Adds login to database
	 * 
	 * @param login
	 */
	public void persist(Login login) {
		if(loggedIn() != null || loggedIn().size() > 0)
			logout();
		
		em.persist(login);
	}
	
	/**Checks if there are any users logged in.
	 * 
	 * @return List<Login>
	 */
	public List<Login> loggedIn() {
		TypedQuery<Login> query = em.createQuery("SELECT l FROM Login l", Login.class);
		
		return query.getResultList();
	}
	
	/**Logs out all users by deleting them from database.
	 * 
	 */
	public void logout() {
		em
			.createQuery
				("DELETE FROM Login")
			.executeUpdate();
	}
	
	
}
