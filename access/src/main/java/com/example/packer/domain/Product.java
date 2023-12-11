package com.example.packer.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "number")
    private Short number;

    @Column(name = "weight", nullable = false)
    private double weight;

    @Column(name = "price", nullable = false)
    private int price;

    @Transient
    private transient Double ratio;


    public Product(double weight, int price) {
        this.weight = weight;
        this.price = price;
    }

    public Product(Short number, double weight, int price) {
        this.number = number;
        this.weight = weight;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;

        if (Double.compare(weight, product.weight) != 0) return false;
        return price == product.price;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(weight);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + price;
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
               "number=" + number +
               ", weight=" + weight +
               ", price=" + price +
               '}';
    }

    @Transient
    public double getRatio() {
        if (ratio == null) {
            ratio = price / weight;
        }
        return ratio;
    }
}
