package com.javabydeveloper.util;


import java.util.List;
import java.util.stream.Collectors;

import com.javabydeveloper.model.User;

public class BatchQueryBuilder {

	public static String buildUpdateQuery(List<User> users) {
		
		StringBuffer query = new StringBuffer("UPDATE `USER` SET USERNAME = CASE ");
		StringBuffer setUserName = new StringBuffer();
		StringBuffer setUpdatedTime = new StringBuffer();
		
		users.forEach(u -> {
			setUserName.append(String.format("WHEN ID=%d THEN '%s' ", u.getId(), u.getUserName().toLowerCase()));
			setUpdatedTime.append(String.format("WHEN ID=%d THEN NOW() ", u.getId()));
			});
		
		query.append(setUserName)
		.append("END, PASSWORD = CASE ")
		.append(setUpdatedTime)
		.append(String.format("END WHERE ID IN (%s)", 
				String.join(",", users.stream().map(u -> u.getId()+"").collect(Collectors.toList()))));
		
		return query.toString();
	}
}
