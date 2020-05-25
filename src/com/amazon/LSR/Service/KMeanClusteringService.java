package com.amazon.LSR.Service;

import java.util.List;
import java.util.Map;

import com.amazon.LSR.model.PairHourPercent;

public interface KMeanClusteringService {

	Map<String, List<PairHourPercent>> getPackTimeDistribution(Map<String, List<Double>> mapOfTimeDiff, int seedVal,
			int clusterSize);

}