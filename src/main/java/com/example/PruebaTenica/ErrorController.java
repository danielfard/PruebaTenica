package com.example.PruebaTenica;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        // Perform any error handling logic you desire here

        return "error"; // Provide the name of the error template you want to use here
    }
}