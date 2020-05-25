package com.amazon.LSR.repository;

import java.util.List;

import com.amazon.LSR.model.Property;

public interface PropertyRepository {

	Property getProperty(String propertyId) throws Exception;

	Property setProperty(Property property) throws Exception;
	
	List<Property> findSimilarProperties(int unitSize,int windowSize) throws Exception;
	
}