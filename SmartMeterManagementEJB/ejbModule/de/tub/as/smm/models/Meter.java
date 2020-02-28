package de.tub.as.smm.models;

import java.io.Serializable;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Meter with ID, label, device ID & load
 * @author Lisa
 *
 */
@Entity
public class Meter implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	private String label;
	private String deviceID;
	private int load;
	
	/**
	 * Constructor
	 */
	public Meter(){
		
	}
	
	/**
	 * Constructor with parameters
	 * @param id
	 * @param l
	 * @param d
	 * @param load
	 */
	public Meter(int id, String l, String d, int load) {
		this.id = id;
		this.label = l;
		this.deviceID = d;
		this.load = load;
	}
	
	/**Returns random numbers for meter.jsp with 0.1 steps
	 * 
	 * @param min
	 * @param max
	 * @return random number in range (double)
	 */
	public double getRandom(int min, int max) {
		Random random = new Random();
		return (random.nextInt((int) ((max - min) * 10 + 1)) + min * 10) / 10.0;
	}
	
	/**
	 * Checks if warning is necessary
	 * @param amp
	 * @return true / false
	 */
	public boolean getWarning(double amp) {
		if(amp > load)
			return true;
		else
			return false;
	}
	
	/**
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * 
	 * @return label
	 */
	public String getLabel() {
		return label;
	}
	
	public int getLoad() {
		return load;
	}
	
	/**
	 * 
	 * @return deviceID
	 */
	public String getDeviceID() {
		return deviceID;
	}
	
	/**
	 * String representation
	 */
	@Override
	public String toString() {
		return id + " - " + label + " (" + deviceID + ")";
	}

}
