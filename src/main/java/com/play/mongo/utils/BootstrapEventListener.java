package com.play.mongo.utils;

import com.play.mongo.nonreactive.EmployeeRepository;
import com.play.mongo.reactive.service.ReactiveEmployeeService;
import com.play.mongo.utils.domain.Employee;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BootstrapEventListener {

    ReactiveEmployeeService reactiveEmployeeService;
    EmployeeRepository nonReactiveEmployeeRepository;
    BootstrapEventListener(ReactiveEmployeeService reactiveEmployeeService, EmployeeRepository nonReactiveEmployeeRepository) {
        this.nonReactiveEmployeeRepository = nonReactiveEmployeeRepository;
        this.reactiveEmployeeService = reactiveEmployeeService;
    }

    @EventListener
    public void handledEvent(ContextRefreshedEvent ctxRefEvent) {
        saveEmployee();
        saveEmployeeReactive();
    }

    private void saveEmployee() {
        Employee employeeA = new Employee();
        employeeA.setId("333");
        employeeA.setFirstName("catalog - SS");
        employeeA.setLastName("catalogX");

        Employee employeeB = new Employee();
        employeeB.setId("332");
        employeeB.setFirstName("catalog - MM");
        employeeB.setLastName("catalogYY");

        Employee employeeC = new Employee();
        employeeC.setId("331");
        employeeC.setFirstName("catalog GG");
        employeeC.setLastName("catalogZZ");

        nonReactiveEmployeeRepository.save(employeeA);
        nonReactiveEmployeeRepository.save(employeeB);
        nonReactiveEmployeeRepository.save(employeeC);

    }


    private void saveEmployeeReactive() {
        Employee employeeA = new Employee();
        employeeA.setId("2000");
        employeeA.setFirstName("First Name-A");
        employeeA.setLastName("Last Name-A");

        Employee employeeB = new Employee();
        employeeB.setId("2001");
        employeeB.setFirstName("First Name-B");
        employeeB.setLastName("Last Name-B");

        Employee employeeC = new Employee();
        employeeC.setId("2002");
        employeeC.setFirstName("First Name-C");
        employeeC.setLastName("Last Name-C");


        reactiveEmployeeService.save(employeeA);
        reactiveEmployeeService.save(employeeB);
        reactiveEmployeeService.save(employeeC);

       Employee emp = reactiveEmployeeService.findByLastName("Last Name-C").block();
       System.out.println(emp.toString());

    }


}
