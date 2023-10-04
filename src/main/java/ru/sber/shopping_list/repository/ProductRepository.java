package ru.sber.shopping_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sber.shopping_list.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
