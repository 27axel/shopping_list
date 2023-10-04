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

    /**
     * Add a new product to the database.
     *
     * @param product The product to be added.
     * @return The added product.
     */
    public Product addProduct(Product product) {
        logger.info("Was invoked method for adding product");
        return productRepository.save(product);
    }

    /**
     * Retrieve a collection of all products.
     *
     * @return A collection of all products stored in the database.
     */
    public Collection<Product> getProducts() {
        logger.info("Was invoked method for getting all products");
        return productRepository.findAll();
    }

    /**
     * Retrieve a product by its unique identifier.
     *
     * @param id The unique identifier of the product.
     * @return The product with the specified ID if found, or throw a RuntimeException if not found.
     */
    public Product getProductById(long id) {
        logger.info("Was invoked method for getting product by id");
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        }
        throw new RuntimeException("This product not found");
    }

    /**
     * Update an existing product with new information.
     *
     * @param id      The unique identifier of the product to be updated.
     * @param product The updated product information.
     * @return The updated product.
     */
    public Product updateProduct(long id, Product product) {
        logger.info("Was invoked method for updating product by id");
        Product newProduct = getProductById(id);
        newProduct.setName(product.getName());
        newProduct.setCost(product.getCost());
        productRepository.save(newProduct);
        return newProduct;
    }

    /**
     * Delete a product by its unique identifier.
     *
     * @param id The unique identifier of the product to be deleted.
     */
    public void deleteProduct(long id) {
        logger.info("Was invoked method for deleting product by id");
        productRepository.deleteById(id);
    }
}
