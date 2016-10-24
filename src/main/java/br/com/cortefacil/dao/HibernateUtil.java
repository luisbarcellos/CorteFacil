package br.com.cortefacil.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {

	private static final SessionFactory SESSION_FACTORY;

	static {
		try {
			
			SESSION_FACTORY = new AnnotationConfiguration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		System.out.println(SESSION_FACTORY);
		return SESSION_FACTORY;
	}

}
