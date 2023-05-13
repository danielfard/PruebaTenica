package com.example.PruebaTenica;

import org.springframework.stereotype.Service;

@Service
public class EmployeeBusiness {

    /**
     * Calculates the annual salary based on the provided monthly salary.
     *
     * @param salary the monthly salary of the employee
     * @return the annual salary
     */
    public double calculateAnnualSalary(Double salary) {
        if (salary != null) {
            return salary * 12;
        } else {
            return 0.0; // or any other default value you want to use
        }
    }
}
