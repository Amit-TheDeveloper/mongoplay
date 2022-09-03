package com.play.mongo.reactive.service;


import com.play.mongo.reactive.repository.ReactiveEmployeeRepository;
import com.play.mongo.utils.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ReactiveEmployeeService {

    @Autowired
    ReactiveEmployeeRepository reactiveEmployeeRepository;

    public Mono<Employee> findByLastName(String lastName) {
        return reactiveEmployeeRepository.findByLastName(lastName);
    }

    public void save(Employee employee) {
        reactiveEmployeeRepository.save(employee).block();
    }

}
