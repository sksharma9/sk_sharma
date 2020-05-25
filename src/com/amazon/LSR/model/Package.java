package com.amazon.LSR.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "package_table")
public class Package {

	@Id
	@Column(name = "package_id", nullable = false, unique = true)
	private String packageId;

	@Column(name = "tracking_id", nullable = false, unique = true)
	private String trackingId;

	@Column(name = "locker_id", nullable = false)
	private int lockerId;

	@Column(name = "carrier_id", nullable = false)
	private String carrierId;

	@Column(name = "resident_id", nullable = false)
	private String residentId;

	@OneToOne(mappedBy = "package1", cascade = CascadeType.ALL)
	private PackageState packageState;

	public Package() {
		super();
	}

	public Package(String packageId, String trackingId, int lockerId, String carrierId, String residentId) {
		super();
		this.packageId = packageId;
		this.trackingId = trackingId;
		this.lockerId = lockerId;
		this.carrierId = carrierId;
		this.residentId = residentId;
	}

	public String getCarrierId() {
		return carrierId;
	}

	public int getLockerId() {
		return lockerId;
	}

	public String getPackageId() {
		return packageId;
	}

	public PackageState getPackageState() {
		return packageState;
	}

	public String getResidentId() {
		return residentId;
	}

	public String getTrackingId() {
		return trackingId;
	}

	public void setCarrierId(String carrierId) {
		this.carrierId = carrierId;
	}

	public void setLockerId(int lockerId) {
		this.lockerId = lockerId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public void setPackageState(PackageState packageState) {
		this.packageState = packageState;
	}

	public void setResidentId(String residentId) {
		this.residentId = residentId;
	}

	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}

	@Override
	public String toString() {
		return "Package [packageId=" + packageId + ", trackingId=" + trackingId + ", lockerId=" + lockerId
				+ ", carrierId=" + carrierId + ", residentId=" + residentId + ", packageState=" + packageState + "]";
	}

	public double getDiffbwDates() {

		long diff = Math.abs(packageState.getDateStop().getTime() - packageState.getDateStart().getTime());

		double diffHours = (double) diff / (60 * 60 * 1000);

		return diffHours;

	}

}
