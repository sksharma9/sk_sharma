package com.amazon.LSR.repository;

import java.util.Date;
import java.util.List;

import com.amazon.LSR.model.Shipment;

public interface ShipmentRepository {

	List<Shipment> findWeeklyAmazonShipment(Date d1, Date d2, String newProperty) throws Exception;

	Shipment getShipment(String shipmentId) throws Exception;

	Shipment setShipment(Shipment shipment1) throws Exception;

}





