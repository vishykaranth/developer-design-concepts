package com.javabydeveloper;

import org.hibernate.cfg.Configuration;

public class AppJustBuildSessionFactoryTest {

	public static void main(String[] args) {

		new Configuration().configure().buildSessionFactory();

	}
}
