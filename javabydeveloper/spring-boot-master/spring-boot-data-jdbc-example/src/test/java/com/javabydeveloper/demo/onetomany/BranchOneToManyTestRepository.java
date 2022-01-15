package com.javabydeveloper.demo.onetomany;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchOneToManyTestRepository extends CrudRepository<Branch, Long>{

}
