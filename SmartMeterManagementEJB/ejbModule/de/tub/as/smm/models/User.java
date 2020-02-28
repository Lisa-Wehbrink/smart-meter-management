package de.tub.as.smm.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
 
    // Persistent Fields:
    @Id @GeneratedValue
    Long id;
    private String name;
    @SuppressWarnings("unused")
	private String pw;
    private Date signingDate;
 
    // Constructors:
    public User() {
    }
 
    public User(String name, String pw) {
        this.name = name;
        this.setPw(pw);
        this.signingDate = new Date(System.currentTimeMillis());
    }
    
    public String getName() {
    	return this.name;
    }
   
    /**
     * String representation
     */
    @Override
    public String toString() {
        return name + " (signed on " + signingDate + ")";
    }

	public void setPw(String pw) {
		this.pw = pw;
	}

	
}