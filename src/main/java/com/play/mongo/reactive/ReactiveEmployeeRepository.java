package com.play.mongo.reactive;

import com.play.mongo.utils.domain.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactiveEmployeeRepository extends ReactiveMongoRepository<Employee, String> {

}
