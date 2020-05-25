package com.amazon.LSR.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazon.LSR.model.Shipment;
import com.amazon.LSR.model.TimePackageCount;
import com.amazon.LSR.repository.ShipmentRepository;
import com.amazon.LSR.repository.ShipmentRepositoryImpl;

@Service("shipmentService")
public class ShipmentServiceImpl implements ShipmentService {
	
	
	private ShipmentRepository shipmentRepository= new ShipmentRepositoryImpl();

	
	public ShipmentServiceImpl() {
		super();
	}


	@Autowired
	public ShipmentServiceImpl(ShipmentRepository shipmentRepository) {
		super();
		this.shipmentRepository = shipmentRepository;
	}


	@Override
	public Map<Date,List<TimePackageCount>> findAmazonPackagesWeekly(Date d1,Date d2,String newPropertyId) throws Exception
	{
		Map<Date,List<TimePackageCount>> mapOfAmzPackages= new HashMap<Date, List<TimePackageCount>>();
		
		
		
		List<Shipment> listOfWeeklyShipment	= shipmentRepository.findWeeklyAmazonShipment(d1, d2, newPropertyId);
		//weekly raw shipment list.
		for(Shipment s:listOfWeeklyShipment)
			System.out.println(s.getShipmentId());
		
		
		Date date=d1;
		
		while(date.getTime()<=d2.getTime())
		{
			final Date dateFinal= new Date(date.getYear(), date.getMonth(), date.getDate(), date.getHours(), date.getMinutes());
			
			List<Integer> listTimewisePackages  = listOfWeeklyShipment.stream()
			.filter(s -> dateChecker(s.getDeliveryTime(),dateFinal))
			.map(Shipment::getHoursOffShipment).collect(Collectors.toList());
		   //computing daily packages of amazon collecting hours of delivery time.
			
			
		List<TimePackageCount> listOfTimePacCount= new ArrayList<TimePackageCount>();
		
		Map<Integer,Integer> hm= new HashMap<Integer, Integer>();
		
			for(Integer hour:listTimewisePackages)//  timings and frequency  of packages calculating using map
			{
				if(hm.containsKey(hour)==false)
				
					hm.put(hour,1);
				
				else
					hm.replace(hour, hm.get(hour)+1);
					
			}
			
			for(Map.Entry<Integer, Integer> entry:hm.entrySet())
			{
				TimePackageCount tpc= new TimePackageCount(entry.getKey(),entry.getValue());
				
				listOfTimePacCount.add(tpc);									
			}
			
			
			
			mapOfAmzPackages.put(date, listOfTimePacCount); // putting in a map of date and list <time , no of amazon packages>
           
			Date newDate= new Date(date.getTime()+3600*24*1000); // incrementing date by 1 day
			
			date=newDate;
		}
		
		return mapOfAmzPackages;
		
		
	}


	public ShipmentRepository getShipmentRepository() {
		return shipmentRepository;
	}


	public void setShipmentRepository(ShipmentRepository shipmentRepository) {
		this.shipmentRepository = shipmentRepository;
	}
	
	private boolean dateChecker(Date d1,Date d2)
	{
		if(d1.getYear()==d2.getYear())
		{
			if(d1.getMonth()==d2.getMonth())
			{
				if(d1.getDate()==d2.getDate())
					return true;
				else
					return false;
			}
			else
				return false;
		}
		else
			return false;
	}

}
