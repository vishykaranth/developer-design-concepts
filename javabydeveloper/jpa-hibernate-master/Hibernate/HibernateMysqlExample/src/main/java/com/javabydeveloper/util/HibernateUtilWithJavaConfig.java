package com.javabydeveloper.util;
  
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.javabydeveloper.domain.Student;
  
public class HibernateUtilWithJavaConfig {
  
    private static final SessionFactory sessionFactory = buildSessionFactory();
  
    private static SessionFactory buildSessionFactory() {
        try {
        	
        	Properties properties = new Properties();
            properties.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
            properties.put(Environment.URL, "jdbc:mysql://localhost:3306/jpa_jbd?serverTimezone=UTC&useSSL=false");
            properties.put(Environment.USER, "root");
            properties.put(Environment.PASS, "password");
            properties.put(Environment.FORMAT_SQL, "true");
            properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
            properties.put(Environment.SHOW_SQL, "true");
            properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            properties.put(Environment.HBM2DDL_AUTO, "create");
            properties.put(Environment.POOL_SIZE, "5");
           
            return new Configuration()
            		.setProperties(properties)
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
