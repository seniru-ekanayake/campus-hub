package com.wolverhampton.campushub;

import com.wolverhampton.campushub.entity.Role;
import com.wolverhampton.campushub.entity.User;
import com.wolverhampton.campushub.repository.RoleRepository;
import com.wolverhampton.campushub.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(RoleRepository roleRepository,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Create roles if they don't exist
        Role studentRole = roleRepository.findByName(Role.RoleName.ROLE_STUDENT)
                .orElseGet(() -> {
                    Role r = new Role();
                    r.setName(Role.RoleName.ROLE_STUDENT);
                    return roleRepository.save(r);
                });

        Role adminRole = roleRepository.findByName(Role.RoleName.ROLE_ADMIN)
                .orElseGet(() -> {
                    Role r = new Role();
                    r.setName(Role.RoleName.ROLE_ADMIN);
                    return roleRepository.save(r);
                });

        // Create admin user if it doesn't exist
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@campushub.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setFirstName("Admin");
            admin.setLastName("User");
            admin.setActive(true);
            admin.setRoles(Set.of(adminRole));
            userRepository.save(admin);
            System.out.println("✅ Admin user created: username=admin, password=admin123");
        } else {
            System.out.println("ℹ️ Admin user already exists, skipping.");
        }
    }
}
