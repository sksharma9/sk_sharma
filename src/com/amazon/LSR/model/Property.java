package com.amazon.LSR.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "property_table")

public class Property {

	@Id
	@Column(name = "property_id", nullable = false, unique = true)
	private String propertyId;

	public Property() {
		super();
	}

	@Column(name = "property_state", nullable = false)
	private String propertyState;

	@OneToMany(mappedBy = "property", cascade = CascadeType.ALL) // mapping managed by property object in Unit model.
	private List<Unit> unitList;

	public Property(String propertyId, String propertyState) {
		super();
		this.propertyId = propertyId;
		this.propertyState = propertyState;
	}

	public String getPropertyId() {
		return propertyId;
	}

	public String getPropertyState() {
		return propertyState;
	}

	public List<Unit> getUnitList() {
		return unitList;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	public void setPropertyState(String propertyState) {
		this.propertyState = propertyState;
	}

	public void setUnitList(List<Unit> unitList) {
		this.unitList = unitList;
	}

}
