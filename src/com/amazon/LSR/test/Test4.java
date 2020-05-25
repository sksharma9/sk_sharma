package com.amazon.LSR.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import com.amazon.LSR.model.Shipment;
import com.amazon.LSR.repository.ShipmentRepository;
import com.amazon.LSR.repository.ShipmentRepositoryImpl;

public class Test4 {

	
	public static void main(String[] args) throws Exception {
		
		
		Map<Date,List<Integer>> map= new HashMap<>();
		 
		Date d= new Date(2020-1900,5,24,0,0);
		
		System.out.println(d);
		
		System.out.println(d.getYear()+1900);
		
		System.out.println(d.getMonth());
		
		Date d18= new Date(2020-1900, 5,12, 9, 16);
		Shipment s18= new Shipment("12363", d18 ,"karlSt");
		

		ShipmentRepository sr=new  ShipmentRepositoryImpl();
		
		sr.setShipment(s18);
	}
}
