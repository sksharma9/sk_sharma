package com.amazon.LSR.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.amazon.LSR.model.Locker;
import com.amazon.LSR.model.LockerType;

@SuppressWarnings("deprecation")
@Repository("lockerRepository")
public class LockerTypeRepositoryImpl extends RepositoryUtil implements LockerTypeRepository {

	public LockerTypeRepositoryImpl() {
		// Default
	}

	@SuppressWarnings("rawtypes")
	public LockerType getLockerType(String lockerTypeId) throws Exception
	{

		try {
			begin();// begining the txn from super class.
			Query q = getSession().createQuery("from LockerType where lockerTypeId= :lockerTypeId");
			q.setString("lockerTypeId", lockerTypeId);
			LockerType lockerType1 = (LockerType) q.uniqueResult();
			commit();
			return lockerType1;
		} catch (HibernateException he) {
			rollback();
			System.out.println("Excpetion in retrieving lockerType data for lockerTypeId " + lockerTypeId);
			throw new Exception();
		}
	}

	public LockerType setLockerType(LockerType lockerType1) throws Exception {

		try {
			begin();

			List<Locker> lockerList = new ArrayList<Locker>();

			LockerType lockerType2 = new LockerType(lockerType1.getLockerTypeId(), lockerType1.getSlotSize());

			for (int i = 0; i < lockerType1.getLockerList().size(); i++) {

				Locker locker = new Locker();
				locker.setLockerId(lockerType1.getLockerList().get(i).getLockerId());
				locker.setActivationDate(lockerType1.getLockerList().get(i).getActivationDate());

				locker.setLockerType(lockerType2);

				lockerList.add(locker);
			}

			lockerType2.setLockerList(lockerList);

			getSession().save(lockerType2);

			commit();

			return lockerType2;
		} catch (HibernateException he) {
			rollback();
			System.out.println("Excpetion in saving lockerType data for lockerTypeId " + lockerType1.getLockerTypeId());
			throw new Exception();
		}

	}

}
