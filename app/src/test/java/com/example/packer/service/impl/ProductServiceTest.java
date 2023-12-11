package com.example.packer.service.impl;

import com.example.packer.core.ProductUtils;
import com.example.packer.domain.Product;
import com.example.packer.repository.ProductRepository;
import com.example.packer.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks // auto inject
    private ProductService productService = new ProductServiceImpl(productRepository);

    @Test
    void test_empty_list() {
        List<Product> products = new ArrayList<>();
        double maxWeight = 10.0;

        List<Product> result = productService.pack(maxWeight, products);

        assertTrue(result.isEmpty());
    }


    @Test
    void test_behaviour_a() {
        double maxWeight = 95;
        List<Product> products = ProductUtils.convertStringToProducts(
                "(1,23.9,$45) (2,80.5,$83) (3,62.48,$53)"
        );
        List<Product> result = productService.pack(maxWeight, products);

        Assertions.assertIterableEquals(ProductUtils.convertStringToProducts("(1,23.9,$45) (3,62.48,$53)"), result);
    }


    @Test
    void test_behaviour_b() {
        double maxWeight = 81;
        List<Product> products = ProductUtils.convertStringToProducts(
                "(1,53.38,$45) (2,88.62,$98) (3,78.48,$3) (4,72.30,$76) (5,30.18,$9) (6,46.34,$48)"
        );
        List<Product> result = productService.pack(maxWeight, products);

        Assertions.assertIterableEquals(List.of(ProductUtils.convertStringToProduct("(4,72.30,$76)")), result);
    }


    @Test
    void test_behaviour_c() {
        double maxWeight = 8;
        List<Product> products = ProductUtils.convertStringToProducts(
                "(1,15.3,$34)"
        );
        List<Product> result = productService.pack(maxWeight, products);

        assertTrue(result.isEmpty());
    }


    @Test
    void test_behaviour_d() {
        double maxWeight = 75;
        List<Product> products = ProductUtils.convertStringToProducts(
                "(1,85.31,$29) (2,14.55,$74) (3,3.98,$16) (4,26.24,$55) (5,63.69,$52) (6,76.25,$75) (7,60.02,$74) (8,93.18,$35) (9,89.95,$78)"
        );
        List<Product> result = productService.pack(maxWeight, products);

        Assertions.assertIterableEquals(ProductUtils.convertStringToProducts("(2,14.55,$74) (7,60.02,$74)"), result);
    }


    @Test
    void test_behaviour_e() {
        double maxWeight = 56;
        List<Product> products = ProductUtils.convertStringToProducts(
                "(1,90.72,$13) (2,33.80,$40) (3,43.15,$10) (4,37.97,$16) (5,46.81,$36) (6,48.77,$79) (7,81.80,$45) (8,19.36,$79) (9,6.76,$64)"
        );
        List<Product> result = productService.pack(maxWeight, products);

        Assertions.assertIterableEquals(ProductUtils.convertStringToProducts("(8,19.36,$79) (9,6.76,$64)"), result);
    }

}