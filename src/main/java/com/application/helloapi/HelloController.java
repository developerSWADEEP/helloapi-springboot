package com.application.helloapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("")
public class HelloController {

    @GetMapping(value = "/api/getHelloWorld")
    public String getHelloWorld() {
        return "Hello World";
    }

    @PostMapping("/api/login")
    public String login(@RequestBody LoginRequest request) {

        if ("admin".equals(request.getUsername()) &&
            "1234".equals(request.getPassword())) {
            return "User logged in successfully";
        }

        if ("test".equals(request.getUsername()) &&
            "    ".equals(request.getPassword())) {
            return "User logged in successfully";
        }

        return "Invalid credentials";
    }
}
