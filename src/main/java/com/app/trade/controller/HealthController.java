package com.app.trade.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

@GetMapping("/health")
    public String greet(HttpServletRequest request) {

        return "Health Check Ok!!";

    }

}
