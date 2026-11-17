package com.soalab.driverservice.controller;

import com.soalab.driverservice.dto.ApiResponse;
import com.soalab.driverservice.dto.LoginRequest;
import com.soalab.driverservice.dto.RegisterRequest;
import com.soalab.driverservice.model.Driver;
import com.soalab.driverservice.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/drivers")
@CrossOrigin(origins = "*")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody RegisterRequest request) {
        try {
            Driver driver = driverService.register(request);
            // Hide password in response
            driver.setPassword(null);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse(true, "Driver registered successfully", driver));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest request) {
        try {
            Driver driver = driverService.login(request);
            driver.setPassword(null);
            return ResponseEntity.ok(new ApiResponse(true, "Login successful", driver));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse(false, e.getMessage()));
        }
    }

    @GetMapping("/health")
    public ResponseEntity<ApiResponse> health() {
        return ResponseEntity.ok(new ApiResponse(true, "Driver Service is running on port 8001"));
    }
}
