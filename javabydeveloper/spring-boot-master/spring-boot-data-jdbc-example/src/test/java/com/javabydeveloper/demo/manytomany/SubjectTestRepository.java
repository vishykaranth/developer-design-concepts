package com.javabydeveloper.demo.manytomany;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectTestRepository extends CrudRepository<Subject, Long>{

}
