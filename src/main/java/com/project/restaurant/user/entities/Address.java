package com.project.restaurant.user.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the Address
 *
 * @author xuanmai
 * @since 2023-11-22
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    private String province;

    private String district;

    private String ward;

    private String label;

    @Column(name = "is_default")
    private boolean isDefault;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public boolean isDefault() {
        return isDefault;
    }
}

