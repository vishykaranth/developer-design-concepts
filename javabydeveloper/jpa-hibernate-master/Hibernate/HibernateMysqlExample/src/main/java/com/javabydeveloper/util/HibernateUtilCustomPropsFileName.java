package com.javabydeveloper.util;
  
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
  
public class HibernateUtilCustomPropsFileName {
  
    private static final SessionFactory sessionFactory = buildSessionFactory();
  
    private static SessionFactory buildSessionFactory() {
        try {
            Properties properties = new Properties();
            properties.load(HibernateUtilCustomPropsFileName.class.getClassLoader().getResourceAsStream("hebernate-dev.properties"));
            
            return new Configuration()
            		.addProperties(properties)
            		.addPackage("com.javabydeveloper.domain")
            		.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("build SeesionFactory failed :" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
  
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
  
    public static void close() {
        // Close all cached and active connection pools
        getSessionFactory().close();
    }
  
}
