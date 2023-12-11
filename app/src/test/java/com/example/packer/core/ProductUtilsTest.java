package com.example.packer.core;

import com.example.packer.domain.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductUtilsTest {

    @Test
    public void test_convert_valid_input_string_to_list_of_products() {
        String input = "(1,85.31,$29) (2,14.55,$74) (3,3.98,$16)";
        List<Product> expected = new ArrayList<>();
        expected.add(new Product(85.31, 29));
        expected.add(new Product(14.55, 74));
        expected.add(new Product(3.98, 16));

        List<Product> actual = ProductUtils.convertStringToProducts(input);


        Assertions.assertIterableEquals(expected, actual);
    }

    // Should correctly parse a string input with weight and price attributes separated by a comma
    @Test
    void test_parse_string_with_weight_and_price_attributes() {
        String input = "(1, 2.5, $10.99)";
        Product expectedProduct = new Product();
        expectedProduct.setWeight(2.5);
        expectedProduct.setPrice(10.99);

        Product actualProduct = ProductUtils.convertStringToProduct(input);

        assertEquals(expectedProduct.getWeight(), actualProduct.getWeight(), 0.01);
        assertEquals(expectedProduct.getPrice(), actualProduct.getPrice(), 0.01);
    }

    // Should correctly parse a string input with a weight attribute as a decimal number
    @Test
    void test_parse_string_with_decimal_weight_attribute() {
        String input = "(1, 2.5, $10.99)";
        double expectedWeight = 2.5;

        double actualWeight = ProductUtils.convertStringToProduct(input).getWeight();

        assertEquals(expectedWeight, actualWeight, 0.01);
    }

    // Should raise an exception if the input string is empty
    @Test
    void test_raise_exception_for_empty_input_string() {
        String input = "";

        assertThrows(Exception.class, () -> {
            ProductUtils.convertStringToProduct(input);
        });
    }

    // Should raise an exception if the input string does not contain two attributes separated by a comma
    @Test
    void test_raise_exception_for_invalid_input_string() {
        String input = "(1, 2.5)";

        assertThrows(Exception.class, () -> {
            ProductUtils.convertStringToProduct(input);
        });
    }

    // Should raise an exception if the weight attribute cannot be parsed as a decimal number
    @Test
    void test_raise_exception_for_invalid_weight_attribute() {
        String input = "(1, abc, $10.99)";

        assertThrows(Exception.class, () -> {
            ProductUtils.convertStringToProduct(input);
        });
    }
}