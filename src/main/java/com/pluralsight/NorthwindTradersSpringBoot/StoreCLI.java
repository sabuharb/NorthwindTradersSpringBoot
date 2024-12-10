package com.pluralsight.NorthwindTradersSpringBoot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class StoreCLI {

    private final ProductDao productDao;

    @Autowired
    public StoreCLI(ProductDao productDao) {
        this.productDao = productDao;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== Store Management System ===");
            System.out.println("1. List Products");
            System.out.println("2. Add Product");
            System.out.println("3. Search Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Update Product");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    listProducts();
                    break;
                case 2:
                    addProduct(scanner);
                    break;
                case 3:
                    searchProduct(scanner);
                    break;
                case 4:
                    deleteProduct(scanner);
                    break;
                case 5:
                    updateProduct(scanner);
                    break;
                case 6:
                    System.out.println("Exiting Store Management System. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void listProducts() {
        System.out.println("\n=== Product List ===");
        for (Product product : productDao.getAllProducts()) {
            System.out.println(product);
        }
        System.out.println();
    }

    private void addProduct(Scanner scanner) {
        System.out.print("Enter Product ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Product Category: ");
        String category = scanner.nextLine();

        System.out.print("Enter Product Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        Product product = new Product(id, name, category, price);
        productDao.addProduct(product);

        System.out.println("Product added successfully!\n");
    }

    private void searchProduct(Scanner scanner) {
        System.out.print("Enter Product ID to search: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Product product = productDao.getProductById(id);
        if (product != null) {
            System.out.println("Product Found: " + product);
        } else {
            System.out.println("Product not found.");
        }
    }

    private void deleteProduct(Scanner scanner) {
        System.out.print("Enter Product ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Product product = productDao.getProductById(id);
        if (product != null) {
            productDao.deleteProduct(id);
            System.out.println("Product deleted successfully!");
        } else {
            System.out.println("Product not found.");
        }
    }

    private void updateProduct(Scanner scanner) {
        System.out.print("Enter Product ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Product existingProduct = productDao.getProductById(id);
        if (existingProduct != null) {
            System.out.print("Enter New Name (current: " + existingProduct.getName() + "): ");
            String name = scanner.nextLine();

            System.out.print("Enter New Category (current: " + existingProduct.getCategory() + "): ");
            String category = scanner.nextLine();

            System.out.print("Enter New Price (current: " + existingProduct.getPrice() + "): ");
            double price = scanner.nextDouble();
            scanner.nextLine();

            Product updatedProduct = new Product(id, name, category, price);
            productDao.updateProduct(updatedProduct);
            System.out.println("Product updated successfully!");
        } else {
            System.out.println("Product not found.");
        }
    }
}