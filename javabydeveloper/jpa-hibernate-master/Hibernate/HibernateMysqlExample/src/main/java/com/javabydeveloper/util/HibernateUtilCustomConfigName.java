package com.javabydeveloper.util;
  
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
  
public class HibernateUtilCustomConfigName {
  
    private static final SessionFactory sessionFactory = buildSessionFactory();
  
    private static SessionFactory buildSessionFactory() {
        try {
           
            return new Configuration().configure("hibernate-dev.cfg.xml").buildSessionFactory();
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
