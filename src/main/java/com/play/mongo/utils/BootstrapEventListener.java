package com.play.mongo.utils;

import com.play.mongo.utils.domain.Employee;
import com.play.mongo.nonreactive.EmployeeRepo;
import org.springframework.context.event.ContextRefreshedEvent;
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
        employeeA.setId("900");
        employeeA.setFirstName("catalog - S");
        employeeA.setLastName("catalogX");

        Employee employeeB = new Employee();
        employeeB.setId("901");
        employeeB.setFirstName("catalog - M");
        employeeB.setLastName("catalogY");

        Employee employeeC = new Employee();
        employeeC.setId("902");
        employeeC.setFirstName("catalog - G");
        employeeC.setLastName("catalogZ");


        nonReactiveEmployeeRepository.save(employeeA);
        nonReactiveEmployeeRepository.save(employeeB);
        nonReactiveEmployeeRepository.save(employeeC);

    }


}
