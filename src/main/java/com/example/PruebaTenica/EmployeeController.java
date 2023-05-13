package com.example.PruebaTenica;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class EmployeeController {

    private final EmployeeDAL employeeDAL;
    private final EmployeeBusiness employeeBusiness;

    public EmployeeController(EmployeeDAL employeeDAL, EmployeeBusiness employeeBusiness) {
        this.employeeDAL = employeeDAL;
        this.employeeBusiness = employeeBusiness;
    }

    /**
     * Displays employee information based on the provided employeeId parameter.
     * If employeeId is not provided, displays information for all employees.
     *
     * @param employeeId the employee ID parameter (optional)
     * @param model      the model to hold the data for the view
     * @return the name of the view to render
     */
    @GetMapping("/employee")
    public String showEmployeeInformation(@RequestParam(name = "employeeId", required = false) Integer employeeId, Model model) {
        if (employeeId != null) {
            Employee employee = employeeDAL.getEmployeeById(employeeId);
            model.addAttribute("employees", Arrays.asList(employee));
        } else {
            List<Employee> employees = employeeDAL.getAllEmployees();
            model.addAttribute("employees", employees);
        }
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("employeeBusiness", employeeBusiness);
        return "employee";
    }
}


