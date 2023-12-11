package com.example.packer.service.impl;

import com.example.packer.domain.Product;
import com.example.packer.repository.ProductRepository;
import com.example.packer.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        Double avgPrice = productRepository.getAvgPrice(minWeight, maxWeight);
        if (avgPrice != null) {
            return avgPrice;
        }
        return 0;
    }

    @Override
    public List<Product> pack(double maxWeight, List<Product> products) {
        var calculator = new PackingCalculator(maxWeight, products);
        return calculator.pack();
    }


    @RequiredArgsConstructor
    static class PackingCalculator {
        private final double maxWeight;
        private final List<Product> products;
        private List<Product> bestCombination;

        public List<Product> pack() {
            bestCombination = new ArrayList<>();
            findBestCombination(new ArrayList<>(), maxWeight, products, 0);
            return bestCombination;
        }

        private void findBestCombination(List<Product> currentCombination, double remainingWeight, List<Product> remainingProducts, int index) {
            if (index == remainingProducts.size()) {
                // Reached the end of the products list, check if the current combination is better
                if (calculateTotalValue(currentCombination) > calculateTotalValue(bestCombination)) {
                    bestCombination = new ArrayList<>(currentCombination);
                }
                return;
            }

            // Include the current product and recurse
            Product currentProduct = remainingProducts.get(index);
            if (currentProduct.getWeight() <= remainingWeight) {
                currentCombination.add(currentProduct);
                findBestCombination(currentCombination, remainingWeight - currentProduct.getWeight(), remainingProducts, index + 1);
                currentCombination.removeLast();  // Backtrack
            }

            // Exclude the current product and recurse
            findBestCombination(currentCombination, remainingWeight, remainingProducts, index + 1);
        }

        private double calculateTotalValue(List<Product> products) {
            double totalValue = 0;
            for (Product product : products) {
                totalValue += product.getPrice();
            }
            return totalValue;
        }
    }
}
