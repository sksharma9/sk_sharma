package com.amazon.LSR.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.springframework.stereotype.Service;

import com.amazon.LSR.model.PairHourPercent;
import com.amazon.LSR.model.TimePackageCount;

@Service("lockerAnalysisService")
public class LockerAnalysisServiceImpl {

	private static String lockerType = "MED";
	private static Integer lockerSize = 30;
	
	private ShipmentService shipmentService;

	private CarrierProjectionService carrierProjectionService;

	private TimeDistributionService timeDistributionService;
	
	public void getWeeklyLockerAnalysis(Date d1,Date d2) throws Exception
	{
		
		Map<Date, List<TimePackageCount>> shipmentMap=shipmentService.findAmazonPackagesWeekly(d1, d2, NewPropertyServiceImpl.PropertyId);
	
		Map<Date, List<TimePackageCount>> projectionMap3p = carrierProjectionService.findWeekly3pProjection(d1, d2);
		
		Map<String, List<PairHourPercent>>  timeDistMap= timeDistributionService.getWeeklyMapOfTimeDist(d1, d2, 15, 4, 10);
	
		
		//priority queue to hold distribution.
 		PriorityQueue<TimePackageCount> priorityQ= new PriorityQueue<TimePackageCount>(new Comparator<TimePackageCount>() {
			@Override
			public int compare(TimePackageCount o1, TimePackageCount o2) {
				
				if(o1.getHour()<o2.getHour())
					return -1;
				else
					return 1;
			}
		});
		
 		Date date= d1;
 		
 		Integer currentSize=0;
 		
 		Integer HourOfDay=0;
 		
 		Integer timeCounter=0;
 		
 		Integer currentFailures=0;
 		
 		while(date.getTime()<=d2.getTime())
 		{
 			shipmentMap.get(date).sort(new Comparator<TimePackageCount>() {
 				@Override
 				public int compare(TimePackageCount o1, TimePackageCount o2) {
 					
 					if(o1.getHour()<o2.getHour())
 						return -1;
 					else
 						return 1;
 				}
 			   });
 			
 			projectionMap3p.get(date).sort(new Comparator<TimePackageCount>() {
 				@Override
 				public int compare(TimePackageCount o1, TimePackageCount o2) {
 					
 					if(o1.getHour()<o2.getHour())
 						return -1;
 					else
 						return 1;
 				}
 			   });
 			
 			int i=0;
 			int j=0;
 			
 			while(true)
 			{
 				if(HourOfDay==shipmentMap.get(date).get(i).getHour())//checking if any amazon package came at that time,,,
 				{
 					Integer hour=shipmentMap.get(date).get(i).getHour();
 					Integer count=shipmentMap.get(date).get(i).getPackageCount();
 					
 					for(PairHourPercent php:timeDistMap.get(Integer.toString(hour)))
 					{
 					Long pickedUpCount=	Math.round(php.getPercentage()*count/100);
 					
 					Integer pickedUpCount_int= Integer.parseInt(Long.toString(pickedUpCount));
 					
 					priorityQ.add(new TimePackageCount(php.getHours()+timeCounter,pickedUpCount_int));
 					
 					}
 					if(lockerFailures(currentSize, count)==0)//checking if we can fill the locker without any failures...
 					{
 						currentSize=currentSize+count;
 						currentFailures=0;
 					}// incrementing locker size by new number of packages
 					else
 					{
 						currentFailures=lockerFailures(currentSize, count);
 						currentSize=lockerSize;
 					}
 				
 					i++;
 				}
 				
 				if(HourOfDay==projectionMap3p.get(date).get(j).getHour())// checking if any 3p package came at that time,,,
 				{
 					Integer hour=projectionMap3p.get(date).get(j).getHour();
 					Integer count=projectionMap3p.get(date).get(j).getPackageCount();
 					
 					for(PairHourPercent php:timeDistMap.get(Integer.toString(hour)))
 					{
 					Long pickedUpCount=	Math.round(php.getPercentage()*count/100);
 					Integer pickedUpCount_int= Integer.parseInt(Long.toString(pickedUpCount));
 					priorityQ.add(new TimePackageCount(php.getHours()+timeCounter,pickedUpCount_int));
 					}
 					
 					if(lockerFailures(currentSize, count)==0)//checking if we can fill the locker without any failures...
 					{
 						currentSize=currentSize+count;
 						currentFailures=0;
 					}// incrementing locker size by new number of packages
 					else
 					{
 						currentFailures=lockerFailures(currentSize, count);
 						currentSize=lockerSize;
 					}
 					
 					j++;
 				}
 				
 				while(priorityQ.isEmpty()==false && timeCounter==priorityQ.peek().getHour())
 				{
 					currentSize= currentSize-priorityQ.peek().getPackageCount();
 					priorityQ.poll(); //polling Priority queue once packages are picked up.
 				}
 				
 				timeCounter++;
 				
 				if(HourOfDay==23)
 				{
 					HourOfDay=0;break;
 				}
 				else
 					HourOfDay++;
 			}
 			
            Date newDate= new Date(date.getTime()+3600*24*1000); // incrementing date by 1 day
			
			date=newDate;
 			
 			
 		}
		
		
	} 
	
	public  int lockerFailures(int currentSize,int packageCount)
	{
		if(currentSize==lockerSize)
		{
			return packageCount;
		}
		else
		{
			int diff=lockerSize-currentSize;
			if(packageCount>diff)
				return packageCount-diff;
			else
				return 0;
			
		}
	}

}


 