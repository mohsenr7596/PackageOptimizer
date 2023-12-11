package com.example.packer.service;

import com.example.packer.domain.Product;

import java.util.List;

public interface ProductService {

    void saveInDB(List<Product> products);

    double avgPrice(double minWeight, double maxWeight);

    List<Product> pack(double maxWeight, List<Product> products);
}
