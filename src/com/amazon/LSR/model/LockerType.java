package com.amazon.LSR.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="locker_type_table")
public class LockerType {
	
	@Id
	@Column(name = "locker_type_id", nullable = false,unique = true)
    private	String lockerTypeId;
	
	@Column(name="slot_size", nullable = false)
	private int slotSize;
	
	@OneToMany(mappedBy = "lockerType",cascade = CascadeType.ALL)
	private List<Locker> lockerList;
	

	public LockerType()
	{
		
	}
	
	public LockerType(String lockerTypeId, int slotSize) {
		super();
		this.lockerTypeId = lockerTypeId;
		this.slotSize = slotSize;
	}

	public List<Locker> getLockerList() {
		return lockerList;
	}

	public String getLockerTypeId() {
		return lockerTypeId;
	}

	public int getSlotSize() {
		return slotSize;
	}
	public void setLockerList(List<Locker> lockerList) {
		this.lockerList = lockerList;
	}

	public void setLockerTypeId(String lockerTypeId) {
		this.lockerTypeId = lockerTypeId;
	}

	public void setSlotSize(int slotSize) {
		this.slotSize = slotSize;
	}
	

}
