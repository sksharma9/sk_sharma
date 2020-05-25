package com.amazon.LSR.model;

public class TimePackageCount {

	private Integer hour;
	private Integer packageCount;
	
	
	public TimePackageCount() {
		
	}
	
	public TimePackageCount(Integer hour, Integer packageCount) {
		super();
		this.hour = hour;
		this.packageCount = packageCount;
	}

	public Integer getHour() {
		return hour;
	}

	public Integer getPackageCount() {
		return packageCount;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	

	public void setPackageCount(Integer packageCount) {
		this.packageCount = packageCount;
	}

}
