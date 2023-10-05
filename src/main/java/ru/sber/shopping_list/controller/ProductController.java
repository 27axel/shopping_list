package ru.sber.shopping_list.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sber.shopping_list.model.Product;
import ru.sber.shopping_list.service.ProductService;

import java.util.Collection;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Retrieve a collection of all products.
     *
     * @return A ResponseEntity containing a collection of products and an HTTP status code.
     */
    @GetMapping("/all")
    public ResponseEntity<Collection<Product>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    /**
     * Retrieve a product by its unique identifier.
     *
     * @param id The unique identifier of the product.
     * @return A ResponseEntity containing the requested product and an HTTP status code.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    /**
     * Add a new product.
     *
     * @param product The product to be added.
     * @return A ResponseEntity containing the added product and an HTTP status code.
     */
    @PostMapping("/add")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.addProduct(product));
    }

    /**
     * Update an existing product by its unique identifier.
     *
     * @param id      The unique identifier of the product to be updated.
     * @param product The updated product information.
     * @return A ResponseEntity containing the updated product and an HTTP status code.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    /**
     * Delete a product by its unique identifier.
     *
     * @param id The unique identifier of the product to be deleted.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

}
