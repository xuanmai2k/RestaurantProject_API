package com.project.restaurant.user.dtos;

import com.project.restaurant.user.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

/**
 * Represents jwt response
 *
 * @author xuanmai
 * @since 2023-11-22
 */
@Data
@AllArgsConstructor
public class JwtResponseDTO {
    private String token;
    private String type;
    private String email;
    private Set<Role> roles;
}