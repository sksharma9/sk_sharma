package com.amazon.LSR.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.amazon.LSR.model.PairHourPercent;

public class TimeDistributionServiceImpl implements TimeDistributionService {
	
	private PackageService packageService;
	
	private KMeanClusteringService  kMeanClusteringService;
	
	private PropertyService propertyService;

	

	public TimeDistributionServiceImpl() {
		super();
	}

	@Override
	public Map<String,List<PairHourPercent>> getWeeklyMapOfTimeDist(Date d1,Date d2,int seedVal,int clusterSize,int windowSize)
	{
		
		int unitSize=NewPropertyServiceImpl.unitSize;
		
		return kMeanClusteringService.getPackTimeDistribution(packageService.findWeeklyTimeDiffMap(d1, d2, propertyService.findSimilarProperties(unitSize, windowSize)), seedVal, clusterSize);
		
	}
	
	public KMeanClusteringService getkMeanClusteringService() {
		return kMeanClusteringService;
	}
	
	
	public PropertyService getPropertyService() {
		return propertyService;
	}
	
	public PackageService getPackageService() {
		return packageService;
	}
	@Autowired
	public void setkMeanClusteringService(KMeanClusteringService kMeanClusteringService) {
		this.kMeanClusteringService = kMeanClusteringService;
	}

    @Autowired
	public void setPackageService(PackageService packageService) {
		this.packageService = packageService;
	}
    @Autowired
	public void setPropertyService(PropertyService propertyService) {
		this.propertyService = propertyService;
	}
}
