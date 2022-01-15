package com.javabydeveloper.demo.embedded.collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEembeddableCollectionTestRepository extends CrudRepository<User, Long>{

}
