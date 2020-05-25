package com.amazon.LSR.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "unit_table")
public class Unit {
	@Id
	@Column(name="unit_id", nullable = false,unique = true)
	private String unitId;
	
	@ManyToOne
	@JoinColumn(name ="property_id")
    private	Property property; //we when do mapping we use whole object of mappped entity.

	@Column(name="address_id", nullable = false)
	private String addressId;

	@Column(name="kiosk_id", nullable = false)
	private int kioskId;
	
	public Unit() {
		super();
	}
	
	public Unit(String unitId, String addressId, int kioskId) {
		super();
		this.unitId = unitId;
		this.addressId = addressId;
		this.kioskId = kioskId;
	}


	public String getAddressId() {
		return addressId;
	}

	public int getKioskId() {
		return kioskId;
	}

	public Property getProperty() {
		return property;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public void setKioskId(int kioskId) {
		this.kioskId = kioskId;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}


}