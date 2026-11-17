package com.soalab.driverservice.service;

import com.soalab.driverservice.dto.LoginRequest;
import com.soalab.driverservice.dto.RegisterRequest;
import com.soalab.driverservice.model.Driver;
import com.soalab.driverservice.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    public Driver register(RegisterRequest request) {
        if (driverRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered: " + request.getEmail());
        }
        Driver driver = new Driver(
            request.getEmail(),
            request.getPassword(),  // In production, hash this password
            request.getName(),
            request.getPhone(),
            request.getLicenseNumber()
        );
        return driverRepository.save(driver);
    }

    public Driver login(LoginRequest request) {
        Optional<Driver> driverOpt = driverRepository.findByEmail(request.getEmail());
        if (driverOpt.isEmpty()) {
            throw new RuntimeException("Driver not found with email: " + request.getEmail());
        }
        Driver driver = driverOpt.get();
        // In production, compare hashed passwords
        if (!driver.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return driver;
    }
}
