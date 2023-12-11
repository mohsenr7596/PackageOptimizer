package com.example.packer.repository;

import com.example.packer.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Nullable
    @Query("SELECT AVG(p.price) FROM Product p WHERE p.weight BETWEEN :minWeight AND :maxWeight")
    Double getAvgPrice(@Param("minWeight") double minWeight, @Param("maxWeight") double maxWeight);

}