package config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateManager {
	private static SessionFactory factory;
	static {
		try {
			StandardServiceRegistry standardRegistry 
			= new StandardServiceRegistryBuilder().configure("hibernate.xml").build();
			Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
			factory = metaData.getSessionFactoryBuilder().build();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SessionFactory getFactory() {
		return factory;
	}
}
