package com.wolverhampton.campushub.config;

import com.wolverhampton.campushub.entity.Role;
import com.wolverhampton.campushub.entity.User;
import com.wolverhampton.campushub.repository.RoleRepository;
import com.wolverhampton.campushub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Create roles if not exist
        if (roleRepository.count() == 0) {
            Role studentRole = new Role();
            studentRole.setName(Role.RoleName.ROLE_STUDENT);
            roleRepository.save(studentRole);

            Role adminRole = new Role();
            adminRole.setName(Role.RoleName.ROLE_ADMIN);
            roleRepository.save(adminRole);
        }

        // Create default admin if not exist
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@wlv.ac.uk");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setFirstName("System");
            admin.setLastName("Administrator");

            Role adminRole = roleRepository.findByName(Role.RoleName.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Admin role not found"));
            admin.setRoles(Set.of(adminRole));
            userRepository.save(admin);

            System.out.println("Default admin created: admin / admin123");
        }
    }
}
