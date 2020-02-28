package de.tub.as.smm.models;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Contains meter data (kwh at given date)
 * @author Lisa
 *
 */
@Entity
public class MeterData implements Serializable{
	private static final long serialVersionUID = 1L;
	
	// Persistent Fields:
	@Id @GeneratedValue
    Long id;
	private Date date;
	private double kwh;
	private Login user;
	private int meterID;
	
	// Constructors:
	public MeterData() {
		
	}
	
	public MeterData(double kwh, Login user, int id){
		this.kwh = kwh;
		this.user = user;
		this.setMeterID(id);
		this.date = new Date(System.currentTimeMillis());
	}
	
	/**
	 * String representation with date formatter
	 */
	@Override
	public String toString() {
		DateFormat formatter = new SimpleDateFormat("dd.MM.yyy");
		String output = "<td>" + formatter.format(this.date) + "</td>";
		output += "<td>" + this.kwh + "kWh</td>";
		output += "<td>" + this.user.toString() + "</td>";
		
		return output;
	}

	public int getMeterID() {
		return meterID;
	}

	public void setMeterID(int meterID) {
		this.meterID = meterID;
	}
	

}
