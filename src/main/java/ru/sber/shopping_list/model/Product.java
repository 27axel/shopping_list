package ru.sber.shopping_list.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Product {
    /**
     * The unique identifier for the product.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * The name of the product.
     */
    private String name;

    /**
     * The cost or price of the product.
     */
    private double cost;
}
