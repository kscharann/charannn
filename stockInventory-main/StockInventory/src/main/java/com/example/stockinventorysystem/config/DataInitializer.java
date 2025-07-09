package com.example.stockinventorysystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.stockinventorysystem.model.ERole;
import com.example.stockinventorysystem.model.Role;
import com.example.stockinventorysystem.repository.RoleRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        initRoles();
    }

    private void initRoles() {
        try {
            if (roleRepository.count() == 0) {
                Role userRole = new Role();
                userRole.setName(ERole.ROLE_USER);
                roleRepository.save(userRole);

                Role modRole = new Role();
                modRole.setName(ERole.ROLE_MODERATOR);
                roleRepository.save(modRole);

                Role adminRole = new Role();
                adminRole.setName(ERole.ROLE_ADMIN);
                roleRepository.save(adminRole);

                System.out.println("Roles initialized successfully");
            } else {
                System.out.println("Roles are already initialized");
            }
        } catch (Exception e) {
            System.err.println("Error initializing roles: " + e.getMessage());
        }
    }
}
