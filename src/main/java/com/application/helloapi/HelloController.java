package com.application.helloapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

@RestController("")
@CrossOrigin(origins = "http://localhost:4200")
public class HelloController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/api/login")
public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

    var user = userRepository.findByUsername(request.getUsername());

    if (user.isPresent() &&
        user.get().getPassword().equals(request.getPassword())) {

        String token = jwtUtil.generateToken(user.get().getUsername());

        LoginResponse response =
                new LoginResponse(token, "Login successful");

        return ResponseEntity.ok(response);
    }

    return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(new LoginResponse(null, "Invalid credentials"));
}

    // @GetMapping(value = "/api/getHelloWorld")
    // public String getHelloWorld() {
    //     return "Hello World";
    // }

    @GetMapping("/api/getHelloWorld")
    public String getHelloWorld() {
    Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();
    return "Hello " + username;
    }

    // @PostMapping("/api/login")
    // public String login(@RequestBody LoginRequest request) {

    //     if ("admin".equals(request.getUsername()) &&
    //         "1234".equals(request.getPassword())) {
    //         return "User logged in successfully";
    //     }

    //     if ("test".equals(request.getUsername()) &&
    //         "    ".equals(request.getPassword())) {
    //         return "User logged in successfully";
    //     }

    //     return "Invalid credentials";
    // }
}
