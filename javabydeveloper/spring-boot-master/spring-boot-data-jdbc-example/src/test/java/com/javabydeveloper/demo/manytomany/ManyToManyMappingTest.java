package com.javabydeveloper.demo.manytomany;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.javabydeveloper.base.BaseTest;

@SpringBootTest
public class ManyToManyMappingTest extends BaseTest {

	@Autowired
	private BranchTestRepository branchRepository;

	@Autowired
	private SubjectTestRepository subjectRepository;

	@Test
	@DisplayName("many-to-many-mapping-test")
	@Sql(scripts = "/many-to-many-mapping.sql")
	void embeddedMappingTest() {

		Subject subj1 = subjectRepository.save(getSubject1());
		Subject subj2 = subjectRepository.save(getSubject2());
		Subject subj3 = subjectRepository.save(getSubject3());

		Branch branch1 = getBranch1();
		branch1.addSubject(subj1);
		branch1.addSubject(subj2);

		Branch createdBranch1 = branchRepository.save(branch1);
		Assert.assertTrue(createdBranch1 != null);

		Branch branch2 = getBranch2();
		branch2.addSubject(subj1);
		branch2.addSubject(subj3);

		Branch createdBranch2 = branchRepository.save(branch2);
		Assert.assertTrue(createdBranch2 != null);

		branchRepository.delete(branch1);
		Assert.assertTrue(!branchRepository.existsById(branch1.getBranchId()));

		branchRepository.findAll().forEach(b -> System.err.println(b));
	}

	private static Subject getSubject1() {
		Subject subject = new Subject();
		subject.setSubjectName("Software Engineering");
		subject.setSubjectDesc(
				"Apply key aspects of software engineering processes for the development of a complex software system");

		return subject;
	}

	private static Subject getSubject2() {
		Subject subject = new Subject();
		subject.setSubjectName("Distributed System");
		subject.setSubjectDesc("Explore recent advances in distributed computing systems");

		return subject;
	}

	private static Subject getSubject3() {
		Subject subject = new Subject();
		subject.setSubjectName("Business Analysis and Optimization");
		subject.setSubjectDesc("understand the Internal and external factors that impact the business strategy");

		return subject;
	}

	private static Branch getBranch1() {
		Branch branch = new Branch();
		branch.setBranchName("Computer Science and Engineering");
		branch.setBranchShortName("CSE");
		branch.setDescription(
				"CSE department offers courses under ambitious curricula in computer science and computer engineering..");

		return branch;
	}

	private static Branch getBranch2() {
		Branch branch = new Branch();
		branch.setBranchName("Information Technology");
		branch.setBranchShortName("IT");
		branch.setDescription(
				"IT is the business side of computers - usually dealing with databases, business, and accounting");

		return branch;
	}
}
