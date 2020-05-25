package com.amazon.LSR.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "shipment_table")
public class Shipment {
	@Id
	@Column(name = "shipment_id", nullable = false, unique = true)
	private String shipmentId;

	@Column(name = "delivery_time", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date deliveryTime;

	@Column(name = "address_id", nullable = false)
	private String addressId;

	public Shipment() {
		super();
	}

	public Shipment(String shipmentId, Date deliveryTime, String addressId) {
		super();
		this.shipmentId = shipmentId;
		this.deliveryTime = deliveryTime;
		this.addressId = addressId;

	}

	public String getAddressId() {
		return addressId;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public String getShipmentId() {
		return shipmentId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public void setShipmentId(String shipmentId) {
		this.shipmentId = shipmentId;
	}
	
	public Integer getHoursOffShipment()
	{
		return getDeliveryTime().getHours();
	}

}