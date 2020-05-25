package com.amazon.LSR.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.amazon.LSR.model.CarrierDelivery;
import com.amazon.LSR.model.Property;

@Repository("carrierDeliveryRepository")
public class CarrierDeliveryRepositoryImpl extends RepositoryUtil implements CarrierDeliveryRepository  {
	
	
	public CarrierDeliveryRepositoryImpl()
	{
		
	}

	@Override
	public Map<String, Long> getCarrierDeliveryData(List<Property> similarProperty) throws Exception {
		begin();
		try {

			TypedQuery<CarrierDelivery> query = getSession().createQuery("select cd from CarrierDelivery cd",
					CarrierDelivery.class);

			Set<String> simPropertySet = similarProperty.stream() // finding the similar property IDs and putting in a
																	// set
					.map(Property::getPropertyId).collect(Collectors.toSet());

			Map<String, Long> carrierVisits = new HashMap<String, Long>();

			carrierVisits = query.getResultStream().filter(cd -> simPropertySet.contains(cd.getPropertyId()))
					.collect(Collectors.groupingBy(CarrierDelivery::getCarrierId, Collectors.counting()));

			// getting carrier wise count in a map , i.e which carrier appears how many
			// times a week time.

			commit();

			return carrierVisits;
		} catch (HibernateException he) {
			rollback();
			System.out.println("Excpetion in retreiving 3p weekly visit data for similar properties");
			throw new Exception();
		}

	}
	

	@Override
	@SuppressWarnings("rawtypes")
	public CarrierDelivery getCarrierDelivery(String propertyCarrierIdDeliveryTime) throws Exception {

		try {
			begin();// begining the txn from super class.
			Query q = getSession().createQuery(
					"from CarrierDelivery where propertyCarrierIdDeliveryTime= :propertyCarrierIdDeliveryTime");
			q.setString("propertyCarrierIdDeliveryTime", propertyCarrierIdDeliveryTime);
			CarrierDelivery propertyCarrierIdDeliveryTime1 = (CarrierDelivery) q.uniqueResult();
			commit();
			return propertyCarrierIdDeliveryTime1;
		} catch (HibernateException he) {
			rollback();
			System.out.println("Excpetion in retriving CarrierDelivery data for PropertyCarrierIdDeliveryTime "
					+ propertyCarrierIdDeliveryTime);
			throw new Exception();
		}
	}

	@Override
	public CarrierDelivery setCarrierDelivery(CarrierDelivery carrierDelivery) throws Exception {

		try {
			begin();// begining the txn from super class.

			CarrierDelivery carrierDelivery2 = new CarrierDelivery(carrierDelivery.getPropertyCarrierIdDeliveryTime(),
					carrierDelivery.getPropertyId(), carrierDelivery.getCarrierId(), carrierDelivery.getDeliveryTime());
			getSession().save(carrierDelivery2);

			commit();
			return carrierDelivery2;
		} catch (HibernateException he) {
			rollback();
			System.out.println("Excpetion in saving Carrier data for PropertyCarrierIdDeliveryTime "
					+ carrierDelivery.getPropertyCarrierIdDeliveryTime());
			throw new Exception();
		}

	}
	
}

