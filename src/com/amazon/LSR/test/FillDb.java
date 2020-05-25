package com.amazon.LSR.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.amazon.LSR.Service.PropertyService;
import com.amazon.LSR.Service.PropertyServiceImpl;
import com.amazon.LSR.Service.ShipmentService;
import com.amazon.LSR.model.Property;
import com.amazon.LSR.model.Unit;
import com.amazon.LSR.repository.PropertyRepository;
import com.amazon.LSR.repository.ShipmentRepository;
import com.amazon.LSR.repository.ShipmentRepositoryImpl;

public class FillDb {
	

	PropertyService psi = ac.getBean("propertyService", PropertyServiceImpl.class);

	PropertyRepository pr = ac.getBean("propertyRepository", PropertyRepository.class);

	Property pro = new Property();

	pro.setPropertyId("KarlSt");

	pro.setPropertyState("active");

	Unit u1 = new Unit();

	u1.setUnitId("101");
	u1.setKioskId(1000);
	u1.setAddressId("mr.jobs");

	Unit u2 = new Unit();

	u2.setUnitId("102");
	u2.setKioskId(1000);
	u2.setAddressId("mr.perkinson");

	List<Unit> lu = new ArrayList<Unit>();

	lu.add(u1);
	lu.add(u2);

	// prop 2

	Property pro2 = new Property();

	pro2.setPropertyId("saintLy");

	pro2.setPropertyState("active");

	Unit u3 = new Unit();

	u3.setUnitId("401");
	u3.setKioskId(1003);
	u3.setAddressId("mr.stocks");

	Unit u4 = new Unit();

	u4.setUnitId("402");
	u4.setKioskId(1003);
	u4.setAddressId("mr.stanwaw");

	Unit u5 = new Unit();

	u5.setUnitId("403");
	u5.setKioskId(1003);
	u5.setAddressId("mr.federer");

	List<Unit> lu2 = new ArrayList<Unit>();

	lu2.add(u3);
	lu2.add(u4);
	lu2.add(u5);

	pro2.setUnitList(lu2);

	pro.setUnitList(lu);

	pr.setProperty(pro);

	pr.setProperty(pro2);

	// PropertyServiceImpl psi= new PropertyServiceImpl();

	List<Property> lp = psi.findSimilarProperties(5, 4);

	for (Property p : lp) {
		System.out.println(p.getPropertyId());
	}


	ShipmentRepository sr= new  ShipmentRepositoryImpl();
	
	
	
	
}
