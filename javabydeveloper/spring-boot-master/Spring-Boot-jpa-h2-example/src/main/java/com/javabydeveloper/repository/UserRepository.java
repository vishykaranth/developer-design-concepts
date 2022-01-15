package com.javabydeveloper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javabydeveloper.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
}
