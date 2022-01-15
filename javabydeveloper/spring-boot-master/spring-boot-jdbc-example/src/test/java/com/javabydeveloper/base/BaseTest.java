package com.javabydeveloper.base;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
	
	@BeforeAll
	public static void startH2Server() throws SQLException {
		// to access H2 console while debug tests
		Server h2server = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082");
		h2server.start();
	}
}
