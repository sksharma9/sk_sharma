package com.amazon.LSR.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "locker_table")
public class Locker {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "locker_id", nullable = false, unique = true)
	private int lockerId;

	@ManyToOne
	@JoinColumn(name = "locker_type_id", nullable = false)
	private LockerType lockerType;

	@Column(name = "activation_date", nullable = false)
	private Date activationDate;

	public Locker() {
		super();
	}
	
	public Locker(int lockerId, Date activationDate) {
		super();
		this.lockerId = lockerId;
		this.activationDate = activationDate;
	}

	public Date getActivationDate() {
		return activationDate;
	}

	public int getLockerId() {
		return lockerId;
	}

	public LockerType getLockerType() {
		return lockerType;
	}

	public void setActivationDate(Date activationDate) {
		this.activationDate = activationDate;
	}

	public void setLockerId(int lockerId) {
		this.lockerId = lockerId;
	}

	public void setLockerType(LockerType lockerType) {
		this.lockerType = lockerType;
	}

}
