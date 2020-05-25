package com.amazon.LSR.model;

public class CarrierTime {
	
	private String CarrierId;
	private Integer hour;
	
	public CarrierTime() {
		super();
		
	}
	public CarrierTime(String carrierId, Integer hour) {
		super();
		CarrierId = carrierId;
		this.hour = hour;
	}
	public String getCarrierId() {
		return CarrierId;
	}
	public void setCarrierId(String carrierId) {
		CarrierId = carrierId;
	}
	public Integer getHour() {
		return hour;
	}
	public void setHour(Integer hour) {
		this.hour = hour;
	}

}
