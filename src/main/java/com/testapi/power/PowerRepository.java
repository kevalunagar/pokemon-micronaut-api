package com.testapi.power;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface PowerRepository extends CrudRepository<Power,Integer> {

}
