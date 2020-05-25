package com.amazon.LSR.repository;

import com.amazon.LSR.model.LockerType;

public interface LockerTypeRepository {

	LockerType getLockerType(String lockerTypeId) throws Exception;

	LockerType setLockerType(LockerType lockerType1)  throws Exception;

}