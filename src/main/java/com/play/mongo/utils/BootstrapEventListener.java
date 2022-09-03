package com.play.mongo.utils;

import com.play.mongo.nonreactive.EmployeeRepository;
import com.play.mongo.reactive.ReactiveEmployeeRepository;
import com.play.mongo.utils.domain.Employee;
import com.play.mongo.nonreactive.EmployeeRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BootstrapEventListener {

    ReactiveEmployeeRepository reactiveEmployeeRepository;
    EmployeeRepository nonReactiveEmployeeRepository;
    BootstrapEventListener(ReactiveEmployeeRepository reactiveEmployeeRepository, EmployeeRepository nonReactiveEmployeeRepository) {
        this.nonReactiveEmployeeRepository = nonReactiveEmployeeRepository;
        this.reactiveEmployeeRepository = reactiveEmployeeRepository;
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
        employeeA.setId("1000");
        employeeA.setFirstName("reactive catalog - S");
        employeeA.setLastName("reactive catalogX");

        Employee employeeB = new Employee();
        employeeB.setId("1001");
        employeeB.setFirstName("reactive catalog - M");
        employeeB.setLastName("reactive catalogY");

        Employee employeeC = new Employee();
        employeeC.setId("1002");
        employeeC.setFirstName("reactive catalog - G");
        employeeC.setLastName("reactive catalogZ");


        reactiveEmployeeRepository.save(employeeA).block();
        reactiveEmployeeRepository.save(employeeB).block();
        reactiveEmployeeRepository.save(employeeC).block();

    }


}
