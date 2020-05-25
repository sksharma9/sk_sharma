package com.amazon.LSR.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class RepositoryUtil {

	private static final ThreadLocal<Session> sessionThread = new ThreadLocal<Session>();

	private static final SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
			.buildSessionFactory();

	public RepositoryUtil() {
		/*
		 * Configuration config= new Configuration().configure("hibernate.cfg.xml");
		 * 
		 * StandardServiceRegistryBuilder standardServiceRegBuilder = new
		 * StandardServiceRegistryBuilder();
		 * 
		 * standardServiceRegBuilder.applySettings(config.getProperties());
		 * 
		 * StandardServiceRegistry registry = standardServiceRegBuilder.build();
		 * 
		 * sessionFactory= config.buildSessionFactory(registry);
		 */
	}

	public static Session getSession() {
		Session session = (Session) sessionThread.get();// getting existing session (if any)

		if (session == null)// otherwise getting new session from factory.
		{
			session = sessionFactory.openSession();
			sessionThread.set(session);
		}
		
		return session;
	}

	public static void begin() {
		{
			getSession().beginTransaction();
		}
	}

	public static void commit() {
		try {
			getSession().getTransaction().commit();
		} catch (HibernateException he) {
			System.out.println("getting Exception while commiting to the DB");
		}
	}

	public static void rollback() {
		try {
			getSession().getTransaction().rollback();
		} catch (HibernateException he) {
			System.out.println("getting Exception while rolling back from the DB");
		}

		try {
			getSession().close();
		} catch (HibernateException he) {
			System.out.println("getting Exception while closing the Session");
		}

		sessionThread.set(null);
	}

	public static void close() {
		getSession().close();//closing the session 
		sessionThread.set(null); //setting current session to null
	}
}
