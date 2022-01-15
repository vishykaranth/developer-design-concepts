package com.javabydeveloper.demo;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.javabydeveloper.model.User;
import com.javabydeveloper.repository.UserRepository;
import com.javabydeveloper.util.UserType;

@SpringBootTest
public class Spring_boot_H2_test {

	@Autowired
	private UserRepository userRepository;
	
	@Test
	@DisplayName("Create User Test ")
	void createUserTest() {
		
		User created = userRepository.save(getUser());
		
		assertTrue(created != null);
	}
	
	private User getUser() {
		
		User user = new User();
		user.setUserType(UserType.STUDENT);
		user.setUserName("PhilipX");
		user.setPassword("ABC123abc*");
		user.setDateofBirth(new Date());
		user.setCreationTime(new Date());
		
		return user;
	}
}
