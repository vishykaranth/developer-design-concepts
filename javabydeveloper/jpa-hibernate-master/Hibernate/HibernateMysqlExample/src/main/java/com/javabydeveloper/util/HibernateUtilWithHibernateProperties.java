package com.javabydeveloper.util;
  
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.javabydeveloper.domain.Student;
  
public class HibernateUtilWithHibernateProperties {
  
    private static final SessionFactory sessionFactory = buildSessionFactory();
  
    private static SessionFactory buildSessionFactory() {
        try {
            // Creates the SessionFactory, uses hibernate.cfg.xml/hibernate.properties by default from classpath
            return new Configuration()
            		.addAnnotatedClass(Student.class)
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
