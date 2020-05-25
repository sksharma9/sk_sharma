package com.amazon.LSR.Service;

import java.util.List;

import com.amazon.LSR.model.Property;
import com.amazon.LSR.repository.PropertyRepository;

public interface PropertyService {

	List<Property> findSimilarProperties(int unitSize, int windowSize);

	PropertyRepository getPropertyRepository();

	void setPropertyRepository(PropertyRepository propertyRepository);

}