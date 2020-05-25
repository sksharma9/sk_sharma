package com.amazon.LSR.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.amazon.LSR.model.Property;
import com.amazon.LSR.model.Unit;

@SuppressWarnings("deprecation")
@Repository("propertyRepository")
public class PropertyRepositoryImpl extends RepositoryUtil implements PropertyRepository, Serializable {

	private static final long serialVersionUID = 1L;

	public PropertyRepositoryImpl() 
	{

		// default...
	}

	public List<Property> findSimilarProperties(int unitSize, int windowSize) throws Exception {

		try {
			begin();
			/*
			 * CriteriaBuilder cb = getSession().getCriteriaBuilder();
			 * CriteriaQuery<Property> cq = cb.createQuery(Property.class); Root<Property>
			 * rootEntry = cq.from(Property.class); CriteriaQuery<Property> all =
			 * cq.select(rootEntry); TypedQuery<Property> allQuery =
			 * getSession().createQuery(all); List<Property> pl = allQuery.getResultList();
			 */
			TypedQuery<Property> query = getSession().createQuery("select p from Property p", Property.class);

			List<Property> similarList = query.getResultStream()
					.filter(p -> p.getUnitList().size() > unitSize - windowSize
							&& p.getUnitList().size() < unitSize + windowSize)
					.collect(Collectors.toList());

			commit();

			return similarList;

		} catch (HibernateException e) {

			rollback();
			System.out.println("Excpetion in retreiving similar property data");
			throw new Exception();

		}

	}

	@SuppressWarnings("deprecation")
	public Property getProperty(String propertyId) throws Exception {

		try {

			begin();// begining the txn from super class.
			@SuppressWarnings("rawtypes")
			Query q = getSession().createQuery("from Property where propertyId= :propertyId");
			q.setString("propertyId", propertyId);
			Property property = (Property) q.uniqueResult();
			commit();
			return property;

		} catch (HibernateException he) {
			rollback();
			System.out.println("Excpetion in retreiving property data for propertyID " + propertyId);
			throw new Exception();
		}

	}

	public Property setProperty(Property property) throws Exception {
		try {
			begin();

			List<Unit> unitList = new ArrayList<Unit>();

			Property property2 = new Property(property.getPropertyId(), property.getPropertyState());

			for (int i = 0; i < property.getUnitList().size(); i++) {

				Unit unit = new Unit();

				unit.setUnitId(property.getUnitList().get(i).getUnitId());

				unit.setAddressId(property.getUnitList().get(i).getAddressId());

				unit.setKioskId(property.getUnitList().get(i).getKioskId());

				unit.setProperty(property2);

				unitList.add(unit);
			}

			property2.setUnitList(unitList);

			getSession().save(property2);

			commit();

			return property2;

		} catch (HibernateException he) {
			rollback();
			System.out.println("Excpetion in saving property data for propertyId " + property.getPropertyId());
			throw new Exception();
		}

	}

}
