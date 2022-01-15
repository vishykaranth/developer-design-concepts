package com.javabydeveloper.demo.onetoone;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOneToOneTestRepository extends CrudRepository<User, Long>{

}
