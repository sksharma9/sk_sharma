package com.amazon.LSR.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "package_state_table")
public class PackageState {

	@Id 
	@Column(name="state_id",nullable = false)
	private String stateId;
	
	@OneToOne
	@JoinColumn(name = "package_id")
	private Package package1;
	
	
	@Column(name="date_start",nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateStart;

	@Column(name="date_stop",nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateStop;
	
	@Column(name="package_state",nullable = false)
	private String package_State;
	
	public PackageState() {
		super();
	}

	public PackageState(String stateId,Date dateStart,Date dateStop, String package_State) {
		super();
		this.stateId = stateId;
		this.dateStart = dateStart;
		this.dateStop = dateStop;
		this.package_State = package_State;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public Date getDateStop() {
		return dateStop;
	}

	public String getPackage_State() {
		return package_State;
	}

	public Package getPackage1() {
		return package1;
	}

	public String getStateId() {
		return stateId;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public void setDateStop(Date dateStop) {
		this.dateStop = dateStop;
	}

	public void setPackage_State(String package_State) {
		this.package_State = package_State;
	}

	public void setPackage1(Package package1) {
		this.package1 = package1;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	
}
