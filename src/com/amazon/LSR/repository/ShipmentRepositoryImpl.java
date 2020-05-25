package com.amazon.LSR.repository;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.amazon.LSR.model.Shipment;

@SuppressWarnings("deprecation")
@Repository("shipmentRepository")
public class ShipmentRepositoryImpl extends RepositoryUtil implements ShipmentRepository  {
	public ShipmentRepositoryImpl() {
		// Default
	}

	@Override
	public List<Shipment> findWeeklyAmazonShipment(Date d1, Date d2, String newProperty) throws Exception {
		begin();
		try {

			System.out.println(d1+" "+d2);
			TypedQuery<Shipment> query = getSession().createQuery("select s from Shipment s", Shipment.class);

			List<Shipment> shipmentWeekly = query.getResultStream()
					.filter(s -> s.getDeliveryTime().getTime()>=d1.getTime() && s.getDeliveryTime().getTime()<=d2.getTime())
					.filter(s -> s.getAddressId().equalsIgnoreCase(newProperty)).collect(Collectors.toList());

			commit();

			return shipmentWeekly;

		}

		catch (HibernateException he) {
			rollback();
			System.out.println(
					"Excpetion in retreiving weekly package data for new property bw date " + d1 + " to " + d2);
			throw new Exception();
		}

	}

	@Override
	@SuppressWarnings("rawtypes")
	public Shipment getShipment(String shipmentId) throws Exception {

		try {
			begin();// begining the txn from super class.
			Query q = getSession().createQuery("from Shipment where shipmentId= :shipmentId");
			q.setString("shipmentId", shipmentId);
			Shipment shipment1 = (Shipment) q.uniqueResult();
			commit();
			return shipment1;
		} catch (HibernateException he) {
			rollback();
			System.out.println("Excpetion in retriving shipment data for shipmentId " + shipmentId);
			throw new Exception();
		}
	}

	@Override
	public Shipment setShipment(Shipment shipment1) throws Exception {

		try {
			begin();// begining the txn from super class.

			Shipment shipment2 = new Shipment(shipment1.getShipmentId(), shipment1.getDeliveryTime(),
					shipment1.getAddressId());

			getSession().save(shipment2);
			commit();
			return shipment1;
		} catch (HibernateException he) {
			rollback();
			System.out.println("Excpetion in saving Shipment data for shipmentId " + shipment1.getShipmentId());
			throw new Exception();
		}

	}

}