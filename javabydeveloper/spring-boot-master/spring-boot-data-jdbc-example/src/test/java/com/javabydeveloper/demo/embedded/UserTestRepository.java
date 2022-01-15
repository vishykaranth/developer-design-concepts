package com.javabydeveloper.demo.embedded;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTestRepository extends CrudRepository<User, Long>{

}
