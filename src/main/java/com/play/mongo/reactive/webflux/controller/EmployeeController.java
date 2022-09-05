package com.play.mongo.reactive.webflux.controller;

import com.play.mongo.reactive.service.ReactiveEmployeeService;
import com.play.mongo.utils.domain.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
