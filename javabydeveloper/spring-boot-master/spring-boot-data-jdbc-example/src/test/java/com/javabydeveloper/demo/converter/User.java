package com.javabydeveloper.demo.converter;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;

import com.javabydeveloper.model.ContactAddress;
import com.javabydeveloper.util.UserType;

import lombok.Data;

@Data
public class User {

	@Id
    private UserId id;
    
	private String userName;
    private String password;
    private Date createdTime;
    private Date updatedTime;
    
    @Embedded.Nullable
    private ContactAddress address;
    
    private UserType userType;
}
