package com.example.PruebaTenica;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.*;

@Component
public class EmployeeDAL {

    @Value("${api.baseurl}")
    private String apiUrl;

    private final RestTemplate restTemplate;
    @Autowired
    public EmployeeDAL(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Retrieves information for all employees from the API.
     *
     * @return a list of employees
     */
    public List<Employee> getAllEmployees() {
        String url = apiUrl + "/employees";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        System.out.println(response);
        if (response.getStatusCode() == HttpStatus.OK) {
            MediaType contentType = response.getHeaders().getContentType();
            if (contentType != null && contentType.includes(MediaType.APPLICATION_JSON)) {
                String responseBody = response.getBody();

                List<Employee> employees = parseJsonResponse(responseBody);
                return employees;
            }

        }
        return Collections.emptyList();
    }

    /**
     * Retrieves information for an employee by their ID from the API.
     *
     * @param id the ID of the employee
     * @return the employee object, or null if not found
     */
    public Employee getEmployeeById(int id) {
        String url = apiUrl + "/employee/" + id;
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            MediaType contentType = response.getHeaders().getContentType();
            if (contentType != null && contentType.includes(MediaType.APPLICATION_JSON)) {
                String responseBody = response.getBody();
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode jsonNode = objectMapper.readTree(responseBody);

                    String status = jsonNode.get("status").asText();
                    if ("success".equals(status)) {
                        JsonNode dataNode = jsonNode.get("data");
                        if (dataNode != null && dataNode.isObject()) {
                            Employee employee = objectMapper.readValue(dataNode.toString(), Employee.class);
                            return employee;
                        }
                    }
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    /**
     * Parses the JSON response and returns a list of employees.
     *
     * @param responseBody the JSON response body
     * @return a list of employees
     */
    private List<Employee> parseJsonResponse(String responseBody) {
        try {
            // Use ObjectMapper to perform JSON parsing
            ObjectMapper objectMapper = new ObjectMapper();

            // Parse the JSON and get the root object
            JsonNode root = objectMapper.readTree(responseBody);

            // Get the "data" node from the root object
            JsonNode dataNode = root.get("data");

            // Use TypeReference to indicate the list type of employees
            TypeReference<List<Employee>> typeReference = new TypeReference<List<Employee>>() {};

            // Parse the "data" node and get the list of employees
            List<Employee> employees = objectMapper.readValue(dataNode.traverse(), typeReference);

            return employees;
        } catch (Exception e) {
            e.printStackTrace();
            // Handle JSON parsing error according to your needs (e.g., throw an exception or return an empty list)
            return Collections.emptyList();
        }
    }
    /**
     * Creates an HTTP entity with any required headers.
     *
     * @return the HTTP entity
     */
    private HttpEntity<?> getHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        // Add any required headers
        return new HttpEntity<>(headers);
    }
}
