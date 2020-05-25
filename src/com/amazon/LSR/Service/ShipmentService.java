package com.amazon.LSR.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.amazon.LSR.model.TimePackageCount;

public interface ShipmentService {

	Map<Date, List<TimePackageCount>> findAmazonPackagesWeekly(Date d1, Date d2, String newPropertyId) throws Exception;

}