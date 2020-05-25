package com.amazon.LSR.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "carrier_table")
public class Carrier {
	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "carrier_id", nullable = false,unique = true)
    private	String carrierId;
	
	@Column(name="carrier_name", nullable = false)
	private String carrierName;
	
	
	
	public Carrier(String carrierId,  String carrierName) {
		super();
		this.carrierId = carrierId;
		
		this.carrierName = carrierName;
		
		
	}



	public String getCarrierId() {
		return carrierId;
		
	}
	
	public String getCarrierName() {
		return carrierName;
	}
	


	public void setCarrierId(String carrierId) {
		this.carrierId = carrierId;
	}
	
	
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}



}
