package com.example.packer.controller;

import com.example.packer.domain.Package;
import com.example.packer.domain.Product;
import com.example.packer.service.PackageService;
import com.example.packer.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final PackageService packageService;

    @PostMapping("/save")
    public ResponseEntity convertToProducts(@RequestBody List<String> input) {
        // Perform the conversion of string list to product list
        List<Product> products = new ArrayList<>();
        for (var s : input) {
            products.add(convertStringToProduct(s));
        }

        productService.saveInDB(products);
        packageService.save(new Package(products));

        return ResponseEntity.accepted().build();
    }

    private Product convertStringToProduct(String inputString) {
        // Remove parentheses from the beginning and end of the string
        inputString = inputString.substring(1, inputString.length() - 1);

        // Split the string into parts using comma as a delimiter
        String[] parts = inputString.split(",");

        // Extract attributes from the array
        double weight = Double.parseDouble(parts[1].trim());

        // Remove $ from the beginning of the value and parse it to double
        double value = Double.parseDouble(parts[2].substring(1).trim());

        // Create an instance of the Product model
        Product product = new Product();
        product.setWeight(weight);
        product.setPrice(value);

        return product;
    }

}
