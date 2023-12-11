package com.example.packer.controller;

import com.example.packer.core.ProductUtils;
import com.example.packer.domain.Package;
import com.example.packer.domain.Product;
import com.example.packer.service.PackageService;
import com.example.packer.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final PackageService packageService;

    @PostMapping(value = "/saveInDB", consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity convertToProducts(@RequestBody String input) {
        List<Product> products = ProductUtils.convertStringToProducts(input);

        packageService.save(new Package(products));
        productService.saveInDB(products);

        return ResponseEntity.accepted().build();
    }

    @GetMapping("/avgPrice")
    public double avgPrice(@RequestParam double minWeight, @RequestParam double maxWeight) {
        // Calculate and return the average price within the given weight range
        return productService.avgPrice(minWeight, maxWeight);
    }

}
