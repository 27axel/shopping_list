package ru.sber.shopping_list.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.sber.shopping_list.model.Product;
import ru.sber.shopping_list.repository.ProductRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    Logger logger = LoggerFactory.getLogger(ProductService.class);

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product) {
        logger.info("Was invoked method for adding product");
        return productRepository.save(product);
    }

    public Collection<Product> getProducts() {
        logger.info("Was invoked method for getting all products");
        return productRepository.findAll();
    }

    public Product getProductById(long id) {
        logger.info("Was invoked method for getting product by id");
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        }
        throw new RuntimeException("This product not found");
    }

    public Product updateProduct(long id, Product product) {
        logger.info("Was invoked method for updating product by id");
        Product newProduct = getProductById(id);
        newProduct.setName(product.getName());
        newProduct.setCost(product.getCost());
        productRepository.save(newProduct);
        return newProduct;
    }

    public void deleteProduct(long id) {
        logger.info("Was invoked method for deleting product by id");
        productRepository.deleteById(id);
    }
}
