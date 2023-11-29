package com.project.restaurant.product.entities;

import com.project.restaurant.category.entities.Category;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "product_name", nullable = false)
    private String productName;


    @Column(name = "product_condition", nullable = false)
    private String productCondition;


    @Column(name = "description", nullable = false)
    private String description;


    @ManyToOne
    @JoinColumn(name = "manufacturer_id", nullable = false)
    private Category manufacturer;


    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;


    @ElementCollection
    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image_url")
    private List<String> images;

}
