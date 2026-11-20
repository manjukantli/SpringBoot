package net.engineeringdigest.journalApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //component(Bean)
public class HealthCheck {

    @GetMapping("/health-Check")
    public String healthCheck(){
        return "Ok";
    }
}
