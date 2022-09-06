package com.play.mongo.reactive.service;


import com.play.mongo.reactive.repository.ReactiveEmployeeRepository;
import com.play.mongo.utils.domain.Employee;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveEmployeeService {


    ReactiveEmployeeRepository reactiveEmployeeRepository;

    public ReactiveEmployeeService(ReactiveEmployeeRepository reactiveEmployeeRepository) {
        this.reactiveEmployeeRepository = reactiveEmployeeRepository;
    }

    public Mono<Employee> findByLastName(String lastName) {
        return reactiveEmployeeRepository.findByLastName(lastName);
    }

    public void save(Employee employee) {
        reactiveEmployeeRepository.save(employee).block();
    }

    public Mono<Employee> findById(String id) {
        return reactiveEmployeeRepository.findById(id);
    }

    public Flux<Employee> findAll() {
        return reactiveEmployeeRepository.findAll();
    }

    public Mono<Void> create(Publisher<Employee> employees) {
        return reactiveEmployeeRepository.saveAll(employees).then();
    }
}
