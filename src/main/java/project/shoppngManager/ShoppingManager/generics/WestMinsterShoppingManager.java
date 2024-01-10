package project.shoppngManager.ShoppingManager.generics;

import org.springframework.stereotype.Service;
import project.shoppngManager.ShoppingManager.exceptions.ProductNotFoundException;
import project.shoppngManager.ShoppingManager.models.Clothing;
import project.shoppngManager.ShoppingManager.models.Electronics;
import project.shoppngManager.ShoppingManager.models.Product;
import project.shoppngManager.ShoppingManager.models.ProductType;
import java.io.*;
import java.util.*;

@Service
public class WestMinsterShoppingManager implements ShoppingManager {

    private static final int MAX_PRODUCTS = 50;
    private static final List<Product> productList = readProductsFromFile();

    @Override
    public void addProduct(Product product) {
        if (productList.size() < MAX_PRODUCTS) {
            productList.add(product);
            System.out.println("Product added successfully!");
        } else {
            System.out.println("Maximum product limit reached.");
        }
    }

    @Override
    public void removeProduct(String productId) {
        Iterator<Product> iterator = productList.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getProductId().equals(productId)) {
                System.out.println("Product deleted: " + product.getProductName() +
                        " (" + (product instanceof Electronics ? "Electronics" : "Clothing") + ")");
                iterator.remove();
                break;
            }
        }
        System.out.println("Total products left: " + productList.size());
    }

    @Override
    public void printProductList() {
        productList.sort(Comparator.comparing(Product::getProductId));
        for (Product product : productList) {
            System.out.println(product instanceof Electronics ? "Electronics:" : "Clothing:");
            System.out.println("Product ID: " + product.getProductId());
            System.out.println("Product Name: " + product.getProductName());
            System.out.println("Product Info: " + product.getInfo());
            if (product instanceof Electronics) {
                System.out.println("Brand: " + ((Electronics) product).getBrand());
                System.out.println("Warranty Period: " + ((Electronics) product).getWarrantyPeriod());
            } else if (product instanceof Clothing) {
                System.out.println("Size: " + ((Clothing) product).getSize());
                System.out.println("Color: " + ((Clothing) product).getColor());
            }
            System.out.println();
        }
    }

    @Override
    public void saveProductsToFile() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("productsList"))) {
            outputStream.writeObject(productList);
            System.out.println("Product list saved to file: productsList");
        } catch (IOException e) {
            System.out.println("Error while saving products to file: " + e.getMessage());
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return productList;
    }

    @Override
    public List<Product> getAllProducts(ProductType type) {
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : productList) {
            if (product.getType() == type) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }


    @SuppressWarnings("unchecked")
    public static List<Product> readProductsFromFile() {
        System.out.println("Product list loading from file: productsList");
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("productsList"))) {
            return (List<Product>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error while reading products from file: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    private static void sortProductsByName(List<Product> productList) {
        productList.sort(Comparator.comparing(Product::getProductName));
    }

    public Product findProductById(String productId) {
        for (Product product : productList) {
            System.out.println(product.getProductId());
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        throw new ProductNotFoundException();
    }

    @Override
    public List<String> loadExistingIds() {
        List<String> existingIds = new ArrayList<>();
        for (Product product : productList) {
            existingIds.add(product.getProductId());
        }
        return existingIds;
    }

    public void loadFile() {
        Clothing clothing1 = new Clothing("B001", "T-Shirt", 7, 19.99, "M", "Blue", "Comfortable cotton t-shirt for everyday wear");
        Clothing clothing2 = new Clothing("B002", "Jeans", 3, 49.99, "32", "Black", "Classic denim jeans for a stylish look");
        Clothing clothing3 = new Clothing("B003", "Jacket", 10, 89.99, "L", "Gray", "Warm and stylish jacket for the winter season");
        Clothing clothing4 = new Clothing("B004", "TurtleNeck", 1, 65.99, "L", "White", "Round collar that fits perfectly");
        Electronics electronics1 = new Electronics("A001", "Smartphone", 10, 499.99, "TechCo", "1 month", "High-performance smartphone with advanced features");
        Electronics electronics2 = new Electronics("A002", "Laptop", 8, 1099.99, "ElectroTech", "3 months", "Powerful laptop for professional use");
        Electronics electronics3 = new Electronics("A003", "Headphones", 12, 79.99, "AudioTech", "6 months", "Over-ear headphones with noise cancellation");
        productList.add(clothing1);
        productList.add(clothing2);
        productList.add(clothing3);
        productList.add(clothing4);
        productList.add(electronics1);
        productList.add(electronics2);
        productList.add(electronics3);
    }

}
