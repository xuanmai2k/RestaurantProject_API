package com.project.restaurant.cuisine.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cuisine_image")
public class CuisineImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuisine_id", nullable = false)
    private Cuisine cuisine;

    @Column(name = "name", nullable = false)
    private String nameImage;

    @Column(name = "imageUrl", nullable = false)
    private String imageUrl;


}
