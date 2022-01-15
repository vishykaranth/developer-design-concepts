package com.javabydeveloper.demo.onetomany;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.javabydeveloper.base.BaseTest;

@SpringBootTest
public class OneToManyMappingTest extends BaseTest {

	@Autowired
	private BranchOneToManyTestRepository testRepository;

	@Test
	@DisplayName("One-to-Many Mapping Test")
	@Sql(scripts = "/one-to-many-mapping.sql")
	void embeddedMappingTest() {

		Branch branch = new Branch();
		branch.setBranchShortName("CSE");
		branch.setBranchName("Computer Science and Engineering");
		branch.setDescription("CSE department offers courses under ambitious curriculam in computer science and computer engineering..");
		Set<Student> students = new HashSet<>();
		students.add(getStudent1());
		students.add(getStudent2());
		branch.setStudents(students);

		Branch createdBranch = testRepository.save(branch);
		System.err.println(createdBranch);
		Assert.assertTrue(createdBranch != null);
	}

	private static Student getStudent1() {
		Student student = new Student();
		student.setFirstName("Rosy");
		student.setLastName("Larsen");
		student.setContactNo("+1-408-575-1317");

		return student;
	}

	private static Student getStudent2() {
		Student student = new Student();
		student.setFirstName("Rosy");
		student.setLastName("Larsen");
		student.setContactNo("+1-408-575-1219");

		return student;
	}
}
