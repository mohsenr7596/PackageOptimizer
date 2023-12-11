package com.example.packer.core;

import com.example.packer.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductUtils {

    public static List<Product> convertStringToProducts(String input) {
        // Perform the conversion of string list to a product list
        List<Product> products = new ArrayList<>();
        for (var s : input.split(" ")) {
            products.add(convertStringToProduct(s));
        }

        return products;
    }

    public static Product convertStringToProduct(String input) {
        // Remove parentheses from the beginning and end of the string
        input = input.substring(1, input.length() - 1);

        // Split the string into parts using comma as a delimiter
        String[] parts = input.split(",");

        // Extract attributes from the array
        double weight = Double.parseDouble(parts[1].trim());

        // Remove $ from the beginning of the value and parse it to double
        double value = Double.parseDouble(parts[2].trim().substring(1).trim());

        // Create an instance of the Product model
        Product product = new Product();
        product.setWeight(weight);
        product.setPrice(value);

        return product;
    }

}
