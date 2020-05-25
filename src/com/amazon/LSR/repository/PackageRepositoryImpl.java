package com.amazon.LSR.repository;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.amazon.LSR.model.Package;
import com.amazon.LSR.model.PackageState;
import com.amazon.LSR.model.Property;

@SuppressWarnings("deprecation")
@Repository("packageRepository")
public class PackageRepositoryImpl extends RepositoryUtil implements PackageRepository {

	public PackageRepositoryImpl() {
		// Default
	}

	public List<Package> findWeekly3pPackages(Date d1, Date d2, List<Property> similarPropList)  throws Exception
	{
		begin();
		try {
		TypedQuery<Package> query = getSession().createQuery("select p from Package p", Package.class);

		Set<String> simPropertySet = similarPropList.stream() // finding the similar property IDs and putting in a set
				.map(Property::getPropertyId).collect(Collectors.toSet());

		List<Package> packageWeekly = query.getResultStream().
				filter(p -> p.getPackageState().getDateStart().after(d1) && p.getPackageState().getDateStart().before(d2))
				.filter(p -> simPropertySet.contains(p.getResidentId())).collect(Collectors.toList());

		// filtering is not neat and clean to understand.

		commit();

		return packageWeekly;
		}
		catch (HibernateException he) {
			rollback();
			System.out.println("Excpetion in retreiving weekly package data for similar properties bw date " + d1 + " to "+d2);
			throw new Exception();
		}
	}

	@SuppressWarnings("rawtypes")
	public Package getPackage(String packageId) throws Exception {

		try {
			begin();// begining the txn from super class.
			Query q = getSession().createQuery("from Package where packageId= :packageId");
			q.setString("packageId", packageId);
			Package package1 = (Package) q.uniqueResult();
			commit();
			return package1;
		} catch (HibernateException he) {
			rollback();
			System.out.println("Excpetion in retreiving package data for packageId " + packageId);
			throw new Exception();
		}
	}

	public Package setPackage(Package package1) throws Exception {

		try {
			begin();// begining the txn from super class.

			PackageState packageState = new PackageState(package1.getPackageState().getStateId(),
					package1.getPackageState().getDateStart(), package1.getPackageState().getDateStop(),
					package1.getPackageState().getPackage_State());// i doubt this will work or not...this
																	// contructor...may work

			Package package2 = new Package(package1.getPackageId(), package1.getTrackingId(), package1.getLockerId(),
					package1.getCarrierId(), package1.getResidentId());

			packageState.setPackage1(package2);

			package2.setPackageState(packageState); // binding done between packagestate and package...

			getSession().saveOrUpdate(package2);

			commit();

			return package2;

		} catch (HibernateException he) {
			rollback();
			System.out.println("Excpetion in saving package data for packageId " + package1.getPackageId());
			throw new Exception();
		}

	}

}
