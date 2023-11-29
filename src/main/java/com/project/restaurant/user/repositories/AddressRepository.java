package com.project.restaurant.user.repositories;

import com.project.restaurant.user.entities.Address;
import com.project.restaurant.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Represents an Address Repository all crud database methods
 *
 * @author xuanmai
 * @since 2023-11-22
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByUser(User user);

    Optional<List<Address>> findByUserIdAndIsDefaultTrue(Long userId);

    List<Address> findByUserId(Long userId);
}
