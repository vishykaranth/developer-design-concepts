package com.javabydeveloper.demo.collection.map;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCollectionMapTestRepository extends CrudRepository<User, Long>{

}
