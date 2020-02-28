package de.tub.as.smm.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 * @author Lisa
 *
 */
@Entity
public class Login implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id 
	private String name;
	
	/**
	 * Constructor without parameters (for EJB)
	 */
	public Login() {
		
	}
	
	/**Constructor for login with parameter
	 * 
	 * @param name
	 */
	public Login(String name) {
		this.name = name;
	}
	

	/**
	 * String representation
	 */
	 @Override
	    public String toString() {
	        return name;
	    }
}
