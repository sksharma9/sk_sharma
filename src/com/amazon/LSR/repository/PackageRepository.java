package com.amazon.LSR.repository;

import java.util.Date;
import java.util.List;

import com.amazon.LSR.model.Package;
import com.amazon.LSR.model.Property;

public interface PackageRepository {

	Package getPackage(String packageId) throws Exception;

	Package setPackage(Package package1) throws Exception;
	
	public List<Package> findWeekly3pPackages(Date d1, Date d2, List<Property> similarPropList)  throws Exception;
	


}