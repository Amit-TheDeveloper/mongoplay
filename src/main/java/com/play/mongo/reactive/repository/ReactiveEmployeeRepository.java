package com.play.mongo.reactive.repository;

import com.play.mongo.utils.domain.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ReactiveEmployeeRepository extends ReactiveMongoRepository<Employee, String> {

    Mono<Employee> findByLastName(String lastName);

}
