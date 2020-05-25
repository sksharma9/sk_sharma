package com.amazon.LSR.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazon.LSR.model.CarrierTime;
import com.amazon.LSR.model.Package;
import com.amazon.LSR.model.Property;
import com.amazon.LSR.repository.PackageRepository;

@Service("packageService")
public class PackageServiceImpl implements PackageService {

	private PackageRepository packageRepository;

	public PackageServiceImpl() {
		super();
	}

	@Autowired
	public PackageServiceImpl(PackageRepository packageRepository) {
		super();
		this.packageRepository = packageRepository;
	}

	@Override // carrier wise calculation of no of packages for a week for similar props.
	public Map<String, Long> findWeekly3pPackageProjection(Date d1, Date d2, List<Property> simPropertyList) {

		List<Package> list3pPackage = new ArrayList<>();

		Map<String, Long> carrierWiseCount = new HashMap<String, Long>();

		try {

			list3pPackage = packageRepository.findWeekly3pPackages(d1, d2, simPropertyList);

			carrierWiseCount = list3pPackage.stream()
					.collect(Collectors.groupingBy(Package::getCarrierId, Collectors.counting()));
			// doing group by over carriers in total similar prop packages over a week.
			// storing in a map.
		}

		catch (Exception e) {

			System.out.println(
					"Exception while fetching carrierWise weekly data of packages for similar properties for week bw "
							+ d1 + " to " + d2);

		}

		return carrierWiseCount;
	}

	@Override // finding the packages for a particular time on a given day. i.e a list of monday 2:00 PM
	public List<Package> findWeekly3pPackageTimeWise(Date d1, Date d2, List<Property> simPropertyList, String time) {

		List<Package> list3pPackage;

		List<Package> timeWiseList = new ArrayList<Package>();

		try {

			list3pPackage = packageRepository.findWeekly3pPackages(d1, d2, simPropertyList);

			timeWiseList = list3pPackage.stream()
					.filter(p -> p.getPackageState().getDateStart().getHours() == Integer.parseInt(time))
					.collect(Collectors.toList());
			// getting time-wise list of packages out of all packages of that week.
		}

		catch (Exception e) {

			System.out.println(
					"Exception while fetching timewise weekly data of packages for similar properties for week bw " + d1
							+ " to " + d2 + " time " + time);

		}

		return timeWiseList;
	}

	@Override // finding the MAP of <time , list(date_stop-date_start)> for all the times the delivery is done.
	public Map<String, List<Double>> findWeeklyTimeDiffMap(Date d1, Date d2, List<Property> similarPropList) {

		Map<String, List<Double>> mapOfTimeDiff = new HashMap<>(); //map containing data of time diff

		for (Map.Entry<Integer, List<CarrierTime>> entry : NewPropertyServiceImpl.newPropCarrierDeliveryInfo.entrySet())
//new prop timings
		{
			for (int i = 0; i < entry.getValue().size(); i++) {

				if (mapOfTimeDiff.containsKey(entry.getValue().get(i).getHour().toString()) == false)

				{	
					
					List<Package> listTimeWise = findWeekly3pPackageTimeWise(d1, d2, similarPropList,
							entry.getValue().get(i).getHour().toString());

					List<Double> listofTimeDiff = listTimeWise.stream().map(Package::getDiffbwDates)
							.collect(Collectors.toList());
					
					mapOfTimeDiff.put(entry.getValue().get(i).toString(), listofTimeDiff);
					
				}

			}
		}

		return mapOfTimeDiff;
	}

	public PackageRepository getPackageRepository() {
		return packageRepository;
	}

	public void setPackageRepository(PackageRepository packageRepository) {
		this.packageRepository = packageRepository;
	}

}
