package com.pluralsight.NorthwindTradersSpringBoot;

import java.util.List;

public interface ProductDao {
    void addProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(int productId);
    void deleteProduct(int productId);
    void updateProduct(Product updatedProduct);
}