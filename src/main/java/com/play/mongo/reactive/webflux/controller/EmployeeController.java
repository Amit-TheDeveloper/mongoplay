package com.play.mongo.reactive.webflux.controller;

import com.play.mongo.reactive.service.ReactiveEmployeeService;
import com.play.mongo.utils.domain.Employee;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class EmployeeController {

    ReactiveEmployeeService employeeService;

    EmployeeController(ReactiveEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("api/v1/employees/{id}")
    public Mono<Employee> findById(String id) {
        return employeeService.findById(id);
    }

    @GetMapping("api/v1/employees")
    public Flux<Employee> findAll() {
        return employeeService.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("api/v1/employees/create")
    public Mono<Void> create(@RequestBody Publisher<Employee> employees) {
        return employeeService.create(employees);
    }
}
