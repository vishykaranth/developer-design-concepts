package com.javabydeveloper.demo.embedded.collection;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.javabydeveloper.base.BaseTest;
import com.javabydeveloper.util.AddressType;
import com.javabydeveloper.util.UserType;

@SpringBootTest
public class EmbeddedCollectionMappingTest extends BaseTest{

	@Autowired
	private UserEembeddableCollectionTestRepository userTestRepository;

	@Test
	@DisplayName("Embedded Collection Mapping Test")
	@Sql(scripts = "/embeded_collection_mapping.sql")
	void embeddedMappingTest() {

		User user = new User();
		user.setUserName("mike");
		user.setPassword("password");
		user.setCreatedTime(new Date());
		user.setUserType(UserType.EMPLOYEE);
		user.setAddress(getAddressList());

		// Creating contacts List
		List<Contact> contactList = new ArrayList<>();
		contactList.add(new Contact("(817) 569-8221"));
		contactList.add(new Contact("(817) 569-8281"));
		// Setting contacts to User
		user.setContacts(contactList);

		User createdUser = userTestRepository.save(user);
		System.err.println(createdUser);
		Assert.assertTrue(createdUser != null);
	}

	public Set<ContactAddress> getAddressList() {

		Set<ContactAddress> adresses = new HashSet<ContactAddress>();

		// Setting Contact Address1
		ContactAddress address = new ContactAddress();
		address.setStreet("Clock Tower, Mahbub College Campus");
		address.setCity("Secunderabad");
		address.setState("Andhrapradesh");
		address.setAreaCode("500003");
		address.setAddressType(AddressType.PERMANENT);

		// Setting Contact Address2
		ContactAddress currentAddress = new ContactAddress();
		currentAddress.setStreet("FN 501, Vamseekuteer");
		currentAddress.setCity("Secunderabad");
		currentAddress.setState("Andhrapradesh");
		currentAddress.setAreaCode("500003");
		currentAddress.setAddressType(AddressType.RESIDENCE);

		adresses.add(address);
		adresses.add(currentAddress);

		return adresses;
	}

}
