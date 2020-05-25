package com.amazon.LSR.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.amazon.LSR.model.TimePackageCount;

public interface CarrierProjectionService {

	Map<Date, List<TimePackageCount>> findWeekly3pProjection(Date d1, Date d2);

}