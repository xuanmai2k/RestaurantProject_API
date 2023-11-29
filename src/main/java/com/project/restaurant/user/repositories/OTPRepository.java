package com.project.restaurant.user.repositories;

import com.project.restaurant.user.entities.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Represents otp repository
 *
 * @author xuanmai
 * @since 2023-11-22
 */
@Repository
public interface OTPRepository extends JpaRepository<Otp, Long> {
    Otp findByEmailAndOtpCode(String email, String otpCode);
    Otp findByEmail(String email);
    void deleteByExpirationTimeBefore(Date currentTime);
}
