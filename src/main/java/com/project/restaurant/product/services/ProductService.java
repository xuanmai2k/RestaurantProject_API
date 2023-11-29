package com.project.restaurant.product.services;

import com.project.restaurant.dtos.PageDTO;
import com.project.restaurant.product.dtos.CreateProductDTO;
import com.project.restaurant.product.entities.Property;
import com.project.restaurant.product.entities.Product;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public interface ProductService {
    ArrayList<Property> createProduct(CreateProductDTO createProductDTO) throws IOException;

    String getRandomProductCode(int length);

    Page<Product> getAllProducts(PageDTO pageDTO);

    Optional<Product> getProductById(Long id);

    void updateProperty(Property property);
}
