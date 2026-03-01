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

        // Create or fix admin user
        Role adminRole = roleRepository.findByName(Role.RoleName.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Admin role not found"));

        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@wlv.ac.uk");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setFirstName("System");
            admin.setLastName("Administrator");
            admin.setRoles(Set.of(adminRole));
            userRepository.save(admin);
            System.out.println("Default admin created: admin / admin123");
        } else {
            // Force-fix the password in case it was saved without BCrypt encoding
            User admin = userRepository.findByUsername("admin")
                    .orElseThrow(() -> new RuntimeException("Admin not found"));
            String currentPassword = admin.getPassword();
            if (!currentPassword.startsWith("$2a$") && !currentPassword.startsWith("$2b$")) {
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRoles(Set.of(adminRole));
                userRepository.save(admin);
                System.out.println("Admin password re-encoded with BCrypt.");
            } else {
                System.out.println("Admin user already exists with valid BCrypt password.");
            }
        }
    }
}
