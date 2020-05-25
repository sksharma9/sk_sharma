package com.amazon.LSR.model;

public class PairHourPercent {

	private Integer hours;
	
	private Double Percentage;

	public PairHourPercent() {
		super();
	}

	public PairHourPercent(Integer hours, Double percentage) {
		super();
		this.hours = hours;
		Percentage = percentage;
	}

	public Integer getHours() {
		return hours;
	}

	public Double getPercentage() {
		return Percentage;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}

	public void setPercentage(Double percentage) {
		Percentage = percentage;
	}

}
