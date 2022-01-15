package com.javabydeveloper;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.javabydeveloper.model.User;
import com.javabydeveloper.repository.UserRepository;
import com.javabydeveloper.util.UserType;

@SpringBootApplication
public class MySpringBootApplication {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(MySpringBootApplication.class, args);
	}

	@PostConstruct
	private void initDb() {
		User user = new User();
		user.setUserType(UserType.STUDENT);
		user.setUserName("PeterM");
		user.setPassword("ABC123abc*");
		user.setDateofBirth(new Date());
		user.setCreationTime(new Date());

		userRepository.save(user);
	}

}
