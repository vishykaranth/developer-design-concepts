package com.javabydeveloper;

import java.util.List;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.cfg.Environment;
import org.hibernate.query.Query;

import com.javabydeveloper.domain.Student;
import com.javabydeveloper.util.HibernateUtilCustomConfigName;

public class App {

	public static void main(String[] args) {
		
		Properties properties = Environment.getProperties();
		System.out.println(properties);

		Session session = HibernateUtilCustomConfigName.getSessionFactory().openSession();
		session.beginTransaction();

		Student student = new Student();
		student.setFirstName("John");
		student.setLastName("Bloch");
		student.setContactNo("+1-408-575-1317");

		session.save(student);
		session.getTransaction().commit();

		Query<Student> q = session.createQuery("From Student", Student.class);

		List<Student> resultList = q.list();
		System.out.println("total sudents:" + resultList.size());
		
		for (Student s : resultList) {
			System.out.println("student : " + s);
		}
	}
}
