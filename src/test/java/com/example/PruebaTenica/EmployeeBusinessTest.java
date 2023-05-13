package com.example.PruebaTenica;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
public class EmployeeBusinessTest {

    private EmployeeBusiness employeeBusiness;

    @BeforeEach
    public void setUp() {
        employeeBusiness = new EmployeeBusiness();
    }

    @Test
    public void testCalculateAnnualSalary() {
        setUp();
        // Arrange
        Double salary = 50000.0;
        Double expectedAnnualSalary = 600000.0;

        // Act
        Double actualAnnualSalary = employeeBusiness.calculateAnnualSalary(salary);

        // Assert
        Assertions.assertEquals(expectedAnnualSalary, actualAnnualSalary, 0.01);
    }
}

