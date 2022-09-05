package com.play.mongo.reactive.webflux.controller;

import com.play.mongo.reactive.repository.ReactiveEmployeeRepository;
import com.play.mongo.reactive.service.ReactiveEmployeeService;
import com.play.mongo.utils.domain.Employee;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class EmployeeControllerTest {

    WebTestClient testClient;
    ReactiveEmployeeService employeeService;
    ReactiveEmployeeRepository mockRepository;
    EmployeeController employeeController;

    @Before
    public void setUp() {
        this.mockRepository = Mockito.mock(ReactiveEmployeeRepository.class);
        employeeController = new EmployeeController(new ReactiveEmployeeService(mockRepository));
        testClient = WebTestClient.bindToController(employeeController).build();
    }

    @Test
    public void list() {
        BDDMockito.given(mockRepository.findAll()).willReturn(getEmployees());
        System.out.println("Listing results..........");
        testClient.get()
                  .uri("/api/v1/employees/")
                  .exchange()
                  .expectBodyList(Employee.class)
                  .returnResult()
                  .getResponseBody()
                  .forEach(System.out::println );
    }

    private Flux<Employee> getEmployees() {

        Employee e1 = new Employee();
        e1.setId("AAAA");
        e1.setFirstName("First-AAAA");
        e1.setLastName("Last-AAAA");

        Employee e2 = new Employee();
        e2.setId("BBBB");
        e2.setFirstName("First-BBBB");
        e2.setLastName("Last-BBBB");

        return Flux.just(e1, e2);

    }

}