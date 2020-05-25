package com.amazon.LSR.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.amazon.LSR.model.Locker;
import com.amazon.LSR.model.LockerType;
import com.amazon.LSR.repository.LockerTypeRepository;
import com.amazon.LSR.repository.LockerTypeRepositoryImpl;

public class LockerLocTypeTest {
	
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		Locker l1= new Locker();
		l1.setActivationDate(new Date());
		
		Locker l2= new Locker();
		l2.setActivationDate(new Date());
		
		LockerType lt= new  LockerType();
		
		
		List<Locker> lockerList= new ArrayList<Locker>();
		
		lockerList.add(l1);
		lockerList.add(l2);
		
		lt.setLockerTypeId("BIG");
		
		lt.setSlotSize(65);
		
		lt.setLockerList(lockerList);
		
		LockerTypeRepository ltr= new  LockerTypeRepositoryImpl() ;
		
		
		ltr.setLockerType(lt);
		
		LockerType lt2= new  LockerType();
		
		lt2= ltr.getLockerType("BIG");
		
		System.out.println(lt2.getLockerList().get(1).getLockerId()+" "+lt2.getSlotSize()+" "+lt2.getLockerTypeId());
		
		
	}

}
