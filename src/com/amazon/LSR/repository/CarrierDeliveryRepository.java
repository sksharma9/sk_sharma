package com.amazon.LSR.repository;

import java.util.List;
import java.util.Map;

import com.amazon.LSR.model.CarrierDelivery;
import com.amazon.LSR.model.Property;

public interface CarrierDeliveryRepository {

	Map<String, Long> getCarrierDeliveryData(List<Property> similarProperty) throws Exception;

	CarrierDelivery getCarrierDelivery(String propertyCarrierIdDeliveryTime) throws Exception;

	CarrierDelivery setCarrierDelivery(CarrierDelivery carrierDelivery) throws Exception;

}