package com.amazon.LSR.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.amazon.LSR.model.Package;

public class PackagePackageStateTest {
	
	
	public static void main(String[] args) throws Exception {
		
		/*
		 * PackageRepository pr= new PackageRepositoryImpl();
		 * 
		 * com.amazon.LSR.model.Package package1= new Package();
		 * 
		 * package1.setPackageId("21000"); package1.setTrackingId("AMZ1234");
		 * package1.setLockerId(1000); package1.setCarrierId("AMZ");
		 * package1.setResidentId("mr.jobs");
		 * 
		 * PackageState ps= new PackageState();
		 * 
		 * Date date_now= new Date();
		 * 
		 * ps.setDateStart(date_now);
		 * 
		 * Date date_now1= new Date(20,5,15,23,12);
		 * 
		 * ps.setDateStop(date_now1);
		 * 
		 * ps.setPackage_State("D");
		 * 
		 * ps.setStateId("201");
		 * 
		 * package1.setPackageState(ps);
		 * 
		 * pr.setPackage(package1);
		 * 
		 * Package p1= new Package();
		 * 
		 * p1=pr.getPackage("21000");
		 * 
		 * System.out.println(p1.toString());
		 * 
		 * System.out.println(p1.getPackageState().getPackage_State()+" "+p1.
		 * getPackageState().getDateStop());
		 */
		
		
		com.amazon.LSR.model.Package package1= new Package();
		
		package1.setPackageId("21000");
		package1.setTrackingId("AMZ1234");
		package1.setLockerId(1000);
		package1.setCarrierId("AMZ");
		package1.setResidentId("mr.jobs");
		
		
		
		com.amazon.LSR.model.Package package2= new Package();
		
		package2.setPackageId("21001");
		package2.setTrackingId("AMZ1234");
		package2.setLockerId(1000);
		package2.setCarrierId("FED");
		package2.setResidentId("mr.jobs");
		

		com.amazon.LSR.model.Package package3= new Package();
		
		package3.setPackageId("21000");
		package3.setTrackingId("AMZ1234");
		package3.setLockerId(1000);
		package3.setCarrierId("FED");
		package3.setResidentId("mr.jobs");
		
		List<Package> lp= new ArrayList<Package>();
		
		lp.add(package1);
		lp.add(package2);
		lp.add(package3);
		
		 Map<String, Long> carrierCoun =	lp.stream().collect(Collectors.groupingBy(Package::getCarrierId,Collectors.counting()));
		
		 for(Map.Entry<String, Long> entry:carrierCoun.entrySet())
		 {
			  System.out.println("Key = " + entry.getKey() + 
                      ", Value = " + entry.getValue());
		 }
		 
	}

	
	

}

