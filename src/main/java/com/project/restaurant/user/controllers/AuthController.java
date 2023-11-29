package com.project.restaurant.user.controllers;

import com.project.restaurant.dtos.ResponseDTO;
import com.project.restaurant.security.JwtTokenUtil;
import com.project.restaurant.user.dtos.AuthDTO;
import com.project.restaurant.user.dtos.JwtResponseDTO;
import com.project.restaurant.user.entities.User;
import com.project.restaurant.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Represents auth controller
 *
 * @author xuanmai
 * @since 2023-11-22
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("${user.user}")
public class AuthController {
    private final AuthenticationManager authManager;
    @Autowired
    JwtTokenUtil jwtUtil;

    /**
     * Logging in Spring Boot
     */
    Logger logger = LoggerFactory.getLogger(AuthController.class);

    /**
     * Login handling
     *
     * @param authDTO This authentication dto
     * @return ResponseEntity
     */
    @PostMapping("${user.login}")
    public ResponseEntity<?> login(@RequestBody AuthDTO authDTO) {
        try {
            var authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authDTO.getEmail(), authDTO.getPassword()));

            var user = (User) authentication.getPrincipal();

            var token = jwtUtil.generateAccessToken(authentication);
            var jwtResponse = new JwtResponseDTO(token, Constants.AUTHORIZATION_TYPE, user.getEmail(), user.getRoles());

            // Successfully
            return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
        } catch (BadCredentialsException ex) {
            logger.info(ex.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}