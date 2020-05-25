package com.amazon.LSR.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.amazon.LSR.model.PairHourPercent;

public interface TimeDistributionService {

	Map<String, List<PairHourPercent>> getWeeklyMapOfTimeDist(Date d1, Date d2, int seedVal, int clusterSize,
			int windowSize);

}