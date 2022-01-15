package com.javabydeveloper.demo.converter;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJdbcConverterTestRepository extends CrudRepository<User, UserId>{

}
