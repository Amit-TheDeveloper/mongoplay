package com.play.mongo.reactive.webflux.controller;

import com.play.mongo.reactive.repository.ReactiveEmployeeRepository;
import com.play.mongo.reactive.service.ReactiveEmployeeService;
import com.play.mongo.utils.domain.Employee;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static org.mockito.ArgumentMatchers.any;

public class EmployeeControllerTest {

    WebTestClient webTestClient;
    ReactiveEmployeeService employeeService;
    ReactiveEmployeeRepository mockRepository;
    EmployeeController employeeController;

    @Before
    public void setUp() {
        this.mockRepository = Mockito.mock(ReactiveEmployeeRepository.class);
        employeeController = new EmployeeController(new ReactiveEmployeeService(mockRepository));
        webTestClient = WebTestClient.bindToController(employeeController).build();
    }

    @Test
    public void testGetAllEmployees() {
        BDDMockito.given(mockRepository.findAll()).willReturn(Flux.fromIterable(getEmployees(2)));
        System.out.println("Listing results..........");
        webTestClient.get()
                  .uri("/api/v1/employees/")
                  .exchange()
                  .expectBodyList(Employee.class)
                  .returnResult()
                  .getResponseBody()
                  .forEach(System.out::println );
    }

    @Test
    public void testCreateEmployees() {
        BDDMockito.given(mockRepository.saveAll(any(Publisher.class)))
                    .willReturn(Flux.just(new Employee()));
        Mono<Employee> employeesToSave = Mono.just(getEmployeesForCreate());

        webTestClient.post()
                .uri("/api/v1/employees/create")
                .body(employeesToSave, Employee.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    // Use list as Flux
    private List<Employee> getEmployees(int count) {
        Employee e1;
        List<Employee> emps = new ArrayList<>();
        for(int i=0; i < count; i++) {
            String id = String.valueOf(Math.random());
            e1 = new Employee();
            e1.setId("ID_AAAA_" + i + id);
            e1.setFirstName("First_AAAA_" + i);
            e1.setLastName("Last_AAAA_" + i);
            emps.add(e1);
        }

        return  emps;
    }

    // Use list as Flux
    private Employee getEmployeesForCreate() {
            Employee e1 = new Employee();
            String id = String.valueOf(Math.random());
            System.out.print("ID is ..>>"+ id);

            e1.setId("ID_AAAA_Create_" + id);
            e1.setFirstName("First_AAAA_Create_" + id);
            e1.setLastName("Last_AAAA_Create_" + id);

            return e1;
        }

}