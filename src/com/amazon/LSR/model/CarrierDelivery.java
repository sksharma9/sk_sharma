package com.amazon.LSR.model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "carrier_delivery_table")
public class CarrierDelivery {

	@Id
	@Column(name = "property_carrier_id_delivery_time", nullable = false)
	private String propertyCarrierIdDeliveryTime;

	@Column(name = "propertyid", nullable = false)
	private String propertyId;

	@Column(name = "carrier_id", nullable = false)
	private String carrierId;

	@Column(name = "delivery_time", nullable = false)
	private String deliveryTime;

	public CarrierDelivery(String propertyCarrierIdDeliveryTime, String propertyId, String carrierId,
			String deliveryTime) {
		super();
		this.propertyCarrierIdDeliveryTime = propertyCarrierIdDeliveryTime;
		this.propertyId = propertyId;
		this.carrierId = carrierId;
		this.deliveryTime = deliveryTime;

	}

	public String getPropertyCarrierIdDeliveryTime() {
		return propertyCarrierIdDeliveryTime;
	}

	public void setPropertyCarrierIdDeliveryTime(String propertyCarrierIdDeliveryTime) {
		this.propertyCarrierIdDeliveryTime = propertyCarrierIdDeliveryTime;
	}

	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	public String getCarrierId() {
		return carrierId;
	}

	public void setCarrierId(String carrierId) {
		this.carrierId = carrierId;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

}