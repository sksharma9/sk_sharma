package com.amazon.LSR.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.amazon.LSR.model.Package;
import com.amazon.LSR.model.Property;

public interface PackageService {

	Map<String, Long> findWeekly3pPackageProjection(Date d1, Date d2, List<Property> simPropertyList);

	List<Package> findWeekly3pPackageTimeWise(Date d1, Date d2, List<Property> simPropertyList, String time);
	
	Map<String, List<Double>> findWeeklyTimeDiffMap(Date d1, Date d2, List<Property> similarPropList);

}	