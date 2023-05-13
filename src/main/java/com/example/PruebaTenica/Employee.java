package com.example.PruebaTenica;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

// This annotation indicates that any unknown properties in the JSON should be ignored during deserialization
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {
    // This annotation indicates that the "id" property is required and should be present in the JSON
    @JsonProperty(required = true)
    private int id;

    // This annotation indicates that the "employee_name" property is required and should be present in the JSON
    @JsonProperty(required = true)
    private String employee_name;

    // This annotation indicates that the "employee_salary" property is required and should be present in the JSON
    @JsonProperty(required = true)
    private Double employee_salary;

    // This annotation indicates that the "employee_age" property is required and should be present in the JSON
    @JsonProperty(required = true)
    private Integer employee_age;

    // This annotation indicates that the "profile_image" property is required and should be present in the JSON
    @JsonProperty(required = true)
    private String profile_image;

    // Add more properties as needed

    // Default constructor (required for Jackson)
    public Employee() {
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public Double getEmployee_salary() {
        return employee_salary;
    }

    public void setEmployee_salary(Double employee_salary) {
        this.employee_salary = employee_salary;
    }

    public Integer getEmployee_age() {
        return employee_age;
    }

    public void setEmployee_age(Integer employee_age) {
        this.employee_age = employee_age;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }
}


