package com.project.restaurant.user.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Represents the Otp
 *
 * @author xuanmai
 * @since 2023-11-22
 */
@Entity
@Table(name = "otp")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Otp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "otp_code")
    private String otpCode;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "expiration_time")
    private Date expirationTime;

}
