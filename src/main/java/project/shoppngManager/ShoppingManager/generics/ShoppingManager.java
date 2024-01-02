package project.shoppngManager.ShoppingManager.generics;

import project.shoppngManager.ShoppingManager.models.Product;
import project.shoppngManager.ShoppingManager.models.ProductType;

import java.util.List;

public interface ShoppingManager {
    void addProduct(Product product);

    void removeProduct(Product product);

    void removeProduct(String productId);

    void printProductList();

    void saveProductsToFile();

    List<Product> getAllProducts();

    List<Product> getAllProducts(ProductType type);

    Product findProductById(String productId);

    List<String> loadExistingIds();
}
