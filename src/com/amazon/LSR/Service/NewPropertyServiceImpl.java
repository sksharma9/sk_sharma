package com.amazon.LSR.Service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.amazon.LSR.model.CarrierTime;

@Service("newPropertyService")
public class NewPropertyServiceImpl {

	
	public static Map<Integer,List<CarrierTime>> newPropCarrierDeliveryInfo;
	
	public static Integer unitSize;
	
	public static String PropertyId;
	
	
	
}
