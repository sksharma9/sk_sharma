package com.amazon.LSR.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazon.LSR.model.Property;
import com.amazon.LSR.model.TimePackageCount;
import com.amazon.LSR.repository.CarrierDeliveryRepository;
import com.amazon.LSR.repository.CarrierDeliveryRepositoryImpl;

@Service("carrierProjectionService")
public class CarrierProjectionServiceImpl implements CarrierProjectionService {

	private static Map<String, Long> carrierVisitFrequency = new HashMap<String, Long>();

	private static List<Property> similarPropList = new ArrayList<Property>();

	private CarrierDeliveryRepository carrierDeliveryRepository;

	private PropertyService propertyService;

	private PackageService packageService;

	public CarrierProjectionServiceImpl() {
		super();
	}

	@Autowired
	public CarrierProjectionServiceImpl(CarrierDeliveryRepository carrierDeliveryRepository,
			PropertyService propertyService, PackageService packageService) {
		super();
		this.carrierDeliveryRepository = carrierDeliveryRepository;
		this.propertyService = propertyService;
		this.packageService = packageService;
	}

	public CarrierProjectionServiceImpl(Integer windowSize) {

		similarPropList = propertyService.findSimilarProperties(NewPropertyServiceImpl.unitSize, windowSize);

		try {
			carrierVisitFrequency = carrierDeliveryRepository.getCarrierDeliveryData(similarPropList);
		} catch (Exception e) {

			System.out.println("Exception occured while getting carrierVisitFrequency for similar proprties " + e);
		}

	}

	private Map<String, Long> findCarrierWisePackageCount(Date d1, Date d2) {
		
		Map<String, Long> mapOfCarrierWisePackageCount = new HashMap<String, Long>();

		mapOfCarrierWisePackageCount = packageService.findWeekly3pPackageProjection(d1, d2, similarPropList);

		// getting count of carrier wise packages of that week to find the average.

		for (Map.Entry<String, Long> entry : mapOfCarrierWisePackageCount.entrySet()) {

			Long freq = carrierVisitFrequency.get(entry.getKey());

			Integer freq_Int = Integer.parseInt(Long.toString(freq));

			Integer total_Int = Integer.parseInt(Long.toString(entry.getValue()));

			Long average = (long) Math.round(total_Int / freq_Int);

			mapOfCarrierWisePackageCount.replace(entry.getKey(), average);

		}
		return mapOfCarrierWisePackageCount;
	}

	@Override
	public Map<Date, List<TimePackageCount>> findWeekly3pProjection(Date d1, Date d2)

	{
		Map<Date, List<TimePackageCount>> finalMapOf3pPackages = new HashMap<Date, List<TimePackageCount>>();

		Map<String, Long> mapOfAvgCarrierPackages = findCarrierWisePackageCount(d1, d2);

		Date date = d1;
		while (date.getTime() <= d2.getTime()) {

			Integer day = date.getDay();

			int size = NewPropertyServiceImpl.newPropCarrierDeliveryInfo.get(day).size();

			List<TimePackageCount> listOfTimePacCount = new ArrayList<TimePackageCount>();

			for (int i = 0; i < size; i++) {
				
				String carrierId = NewPropertyServiceImpl.newPropCarrierDeliveryInfo.get(day).get(i).getCarrierId();

				Integer hour = NewPropertyServiceImpl.newPropCarrierDeliveryInfo.get(day).get(i).getHour();

				Long packageCount = mapOfAvgCarrierPackages.get(carrierId);

				Integer packageCountInt = Integer.parseInt(packageCount.toString());

				TimePackageCount tpc = new TimePackageCount(hour, packageCountInt);

				listOfTimePacCount.add(tpc);
			}

			finalMapOf3pPackages.put(date, listOfTimePacCount);

			Date newDate = new Date(date.getTime() + 3600 * 24 * 1000); // incrementing date by 1 day

			date = newDate;

		}

		return finalMapOf3pPackages;

	}

	public PackageService getPackageService() {
		return packageService;
	}

	public void setPackageService(PackageService packageService) {
		this.packageService = packageService;
	}

	public CarrierDeliveryRepository getCarrierDeliveryRepository() {
		return carrierDeliveryRepository;
	}

	public PropertyService getPropertyService() {
		return propertyService;
	}

	public void setCarrierDeliveryRepository(CarrierDeliveryRepository carrierDeliveryRepository) {
		this.carrierDeliveryRepository = carrierDeliveryRepository;

	}

	public void setPropertyService(PropertyService propertyService) {
		this.propertyService = propertyService;
	}

}
