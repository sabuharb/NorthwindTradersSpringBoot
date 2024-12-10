package com.pluralsight.NorthwindTradersSpringBoot;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class SimpleProductDao implements ProductDao {

    private final List<Product> products = new ArrayList<>();

    public SimpleProductDao() {
        products.add(new Product(1, "Laptop", "Electronics", 1200.00));
        products.add(new Product(2, "Headphones", "Accessories", 150.00));
    }

    @Override
    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public Product getProductById(int productId) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void deleteProduct(int productId) {
        products.removeIf(product -> product.getProductId() == productId);
    }

    @Override
    public void updateProduct(Product updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId() == updatedProduct.getProductId()) {
                products.set(i, updatedProduct);
                break;
            }
        }
    }
}