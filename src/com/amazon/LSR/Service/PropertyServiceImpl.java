package com.amazon.LSR.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazon.LSR.model.Property;
import com.amazon.LSR.repository.PropertyRepository;

@Service("propertyService")
public class PropertyServiceImpl implements PropertyService {

	
	private PropertyRepository propertyRepository;

	public PropertyServiceImpl() {
		super();
	}
	
	@Autowired
	public PropertyServiceImpl(PropertyRepository propertyRepository) {
		super();
		this.propertyRepository = propertyRepository;
	}
	@Override
	public List<Property> findSimilarProperties(int unitSize,int windowSize) {
		
		List<Property> similarList= new ArrayList<>();
		
		try {
			similarList = propertyRepository.findSimilarProperties(unitSize,windowSize);
		} 
		catch (Exception e) {
		
			System.out.println("Not able to find Similar Properties for new property");
		}
		return similarList;
	}
	@Override
	public PropertyRepository getPropertyRepository() {
		return propertyRepository;
	}

	@Override
	public void setPropertyRepository(PropertyRepository propertyRepository) {
		this.propertyRepository = propertyRepository;
	}

}
