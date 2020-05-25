import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.amazon.LSR.Service.ShipmentService;
import com.amazon.LSR.Service.ShipmentServiceImpl;
import com.amazon.LSR.model.Shipment;
import com.amazon.LSR.model.TimePackageCount;
import com.amazon.LSR.repository.ShipmentRepository;
import com.amazon.LSR.repository.ShipmentRepositoryImpl;

public class App {

	public static void main(String[] args) throws Exception {

		//ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		
	  //CarrierDeliveryRepository c=	ac.getBean("carrierDeliveryRepository", CarrierDeliveryRepository.class);

	//	ShipmentRepository sr= ac.getBean("shipmentRepository", ShipmentRepository.class);
		
		ShipmentRepository sr=new  ShipmentRepositoryImpl();
		List<Shipment> list= new ArrayList<Shipment>();
		
		Date d1= new Date(2020+1900, 5,7, 11, 28);
		Shipment s1= newObj("12345", "karlSt", d1);
		
		list.add(s1);
		
		Date d2= new Date(2020+1900, 5,7, 12, 01);
		Shipment s2= newObj("12346", "karlSt", d2);
		
		list.add(s2);

		Date d3= new Date(2020+1900, 5,7, 12, 23);
		Shipment s3= newObj("12347", "karlSt", d3);
		
		list.add(s3);


		Date d4= new Date(2020+1900, 5,7, 12, 45);
		Shipment s4= newObj("12348", "karlSt", d4);
		list.add(s4);

		
		Date d5= new Date(2020+1900, 5,7, 13, 01);
		Shipment s5= newObj("12349", "stLy", d5);
		list.add(s5);
		
		
		Date d6= new Date(2020+1900, 5,7, 13, 12);
		Shipment s6= newObj("12350", "karlSt", d6);
		list.add(s6);
		
		Date d7= new Date(2020+1900, 5,8, 11, 28);
		Shipment s7= newObj("12351", "karlSt", d7);
		list.add(s7);
		
		Date d8= new Date(2020+1900, 5,8, 11, 30);
		Shipment s8= newObj("12352", "karlSt", d8);
		list.add(s8);
		
		Date d9= new Date(2020+1900, 5,8, 11, 46);
		Shipment s9= newObj("12353", "karlSt", d9);
		list.add(s9);
		
		Date d10= new Date(2020+1900, 5,11, 9, 12);
		Shipment s10= newObj("12354", "clayRd", d10);
		list.add(s10);
		
		Date d11= new Date(2020+1900, 5,11,9, 18);
		Shipment s11= newObj("12355", "clayRd", d11);
		list.add(s11);
		
		Date d12= new Date(2020+1900, 5,11,12, 11);
		Shipment s12= newObj("12356", "karlSt", d12);
		list.add(s12);
		
		Date d13= new Date(2020+1900, 5,11,12, 18);
		Shipment s13= newObj("12357", "karlSt", d13);
		list.add(s13);
		
		Date d14= new Date(2020+1900, 5,11,12, 21);
		Shipment s14= newObj("12358", "karlSt", d14);
		list.add(s14);
		
		Date d15= new Date(2020+1900, 5,11,12, 37);
		Shipment s15= newObj("12359", "karlSt", d15);
		list.add(s15);
		
		Date d16= new Date(2020+1900, 5,12, 7, 28);
		Shipment s16= newObj("12360", "clayRd", d16);
		list.add(s16);
		
		Date d17= new Date(2020+1900, 5,12, 9, 10);
		Shipment s17= newObj("12361", "karlSt", d17);
		list.add(s17);
		
		Date d18= new Date(2020-1900, 5-1,12, 9, 16);
		Shipment s18= newObj("12364", "karlSt", d18);
		list.add(s18);
		/*
		 * for(Shipment sh:list) {
		 * 
		 * sr.setShipment(sh);
		 * 
		 * }
		 */
	//	sr.setShipment(s18);
		
		
		  ShipmentService ss= new ShipmentServiceImpl();
		  
		  Map<Date,List<TimePackageCount>> mp=ss.findAmazonPackagesWeekly(new Date(2020-1900,4,11,0,0), new Date(2020-1900,4,13,0,0), "karlSt");
		 
		  for(Map.Entry<Date,List<TimePackageCount>> a:mp.entrySet())
		  {
			  System.out.println("Date : "+a.getKey().getDate()+"/"+a.getKey().getMonth()+"/"+(a.getKey().getYear()+1900) +" : ");
			  for(TimePackageCount t:a.getValue())
			  {
				  System.out.print(t.getHour()+" : "+t.getPackageCount()+" , ");
			  }
		  }
		
	}
	
	private static Shipment newObj(String shipId,String addID,Date dt)
	{
		return new Shipment(shipId,dt,addID);
	}
}
