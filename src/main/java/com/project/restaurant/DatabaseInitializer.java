package com.project.restaurant;

import com.project.restaurant.user.repositories.UserRepository;
import com.project.restaurant.user.entities.Role;
import com.project.restaurant.user.entities.User;
import com.project.restaurant.user.models.ERole;
import com.project.restaurant.user.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Database Initializer
 *
 * @author KhanhBD
 * @since 2023-10-03
 */
@Component
public class DatabaseInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private final List<ERole> roles = List.of(ERole.ROLE_ADMIN, ERole.ROLE_USER);

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        roles.forEach(this::initRole);
        initAdminUser();// Call method to create admin account
    }

    /**
     * Create admin account
     */
    private void initAdminUser() {
        Optional<User> adminUser = userRepository.findByEmail("admin@gmail.com");

        // If the admin account does not exist, create it
        if (adminUser.isEmpty()) {

            User admin = new User();
            admin.setEmail("admin@gmail.com");
            admin.setFullName("admin");
            admin.setPassword(passwordEncoder.encode("admin")); // Encrypt password
            admin.setCreatedAt(LocalDateTime.now());
            admin.setModifiedAt(LocalDateTime.now());

            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(roleRepository.findByName(ERole.ROLE_ADMIN.toString()).orElseThrow());

            admin.setRoles(adminRoles);

            userRepository.save(admin);
        }
    }

    /**
     * init Role
     */
    private void initRole(ERole eRole) {
        switch (eRole) {
            case ROLE_USER -> {
                saveRole(ERole.ROLE_USER.toString());
            }
            case ROLE_ADMIN -> {
                saveRole(ERole.ROLE_ADMIN.toString());
            }
        }
    }

    /**
     * save Role user
     */
    private void saveRole(String name) {
        Optional<Role> role = roleRepository.findByName(name);
        if (role.isEmpty()) {
            Role userRole = new Role(name);
            roleRepository.save(userRole);
        }
    }
}
