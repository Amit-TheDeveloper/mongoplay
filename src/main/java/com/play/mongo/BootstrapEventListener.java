package com.play.mongo;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BootstrapEventListener {

    EmployeeRepo nonReactiveEmployeeRepository;
    BootstrapEventListener(EmployeeRepo nonReactiveEmployeeRepository) {
        this.nonReactiveEmployeeRepository = nonReactiveEmployeeRepository;
    }

    @EventListener
    public void handledEvent(ContextRefreshedEvent ctxRefEvent) {
        saveEmployee();
    }

    private void saveEmployee() {
        Employee employeeA = new Employee();
        employeeA.setId("343");
        employeeA.setFirstName("catalog - Q");
        employeeA.setLastName("catalogX");

        Employee employeeB = new Employee();
        employeeB.setId("567");
        employeeB.setFirstName("catalog - P");
        employeeB.setLastName("catalogY");

        Employee employeeC = new Employee();
        employeeC.setId("789");
        employeeC.setFirstName("catalog - K");
        employeeC.setLastName("catalogZ");


        nonReactiveEmployeeRepository.save(employeeA);
        nonReactiveEmployeeRepository.save(employeeB);
        nonReactiveEmployeeRepository.save(employeeC);

    }


}
