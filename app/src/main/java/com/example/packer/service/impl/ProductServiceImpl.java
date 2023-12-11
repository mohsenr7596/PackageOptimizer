package com.example.packer.service.impl;

import com.example.packer.domain.Product;
import com.example.packer.repository.ProductRepository;
import com.example.packer.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public void saveInDB(List<Product> products) {
        productRepository.saveAll(products);
    }

    @Override
    public double avgPrice(double minWeight, double maxWeight) {
        return 0;
    }

    @Override
    public List<Product> pack(double maxWeight, List<Product> products) {
        // sort product base on price/weight
        products.sort((p1, p2) -> Double.compare(p2.getPrice() / p2.getWeight(), p1.getPrice() / p1.getWeight()));

        List<Product> selectedProducts = new ArrayList<>();
        double currentWeight = 0;

        // choose product base on weight
        for (var product : products) {
            if (currentWeight + product.getWeight() <= maxWeight) {
                selectedProducts.add(product);
                currentWeight += product.getWeight();
            }
        }

        return selectedProducts;
    }
}
