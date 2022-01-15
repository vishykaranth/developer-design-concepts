package com.javabydeveloper.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javabydeveloper.model.College;

@Repository
public interface CollegeRepository extends CrudRepository<College, Long>{
	
}
