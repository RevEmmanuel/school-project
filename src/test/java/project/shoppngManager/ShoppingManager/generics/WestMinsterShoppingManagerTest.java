package project.shoppngManager.ShoppingManager.generics;

import org.junit.Test;
import project.shoppngManager.ShoppingManager.exceptions.ProductNotFoundException;
import project.shoppngManager.ShoppingManager.models.Clothing;
import project.shoppngManager.ShoppingManager.models.Product;
import project.shoppngManager.ShoppingManager.models.ProductType;
import java.io.File;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class WestMinsterShoppingManagerTest {

    private final ShoppingManager shoppingManager = new WestMinsterShoppingManager();

    @Test
    public void testAddProduct() {
        // Add a product
        Clothing newClothing = new Clothing("C001", "Dress", 5, 29.99, "S", "Red", "Elegant dress for occasions");
        shoppingManager.addProduct(newClothing);

        // Retrieve the added product
        Product retrievedProduct = shoppingManager.findProductById("C001");

        // Check if the retrieved product matches the added product
        assertEquals(newClothing, retrievedProduct);
    }

    @Test
    public void testRemoveProduct() {
        // Remove an existing product
        shoppingManager.removeProduct("B001");

        // Try to retrieve the removed product (should throw an exception)
        assertThrows(ProductNotFoundException.class, () -> shoppingManager.findProductById("B009"));
    }

    @Test
    public void testGetAllProducts() {
        // Get all products
        List<Product> allProducts = shoppingManager.getAllProducts();
        assertEquals(6, allProducts.size());
    }

    @Test
    public void testGetAllProductsOfType() {
        // Get all products of type Electronics
        List<Product> electronicsProducts = shoppingManager.getAllProducts(ProductType.ELECTRONICS);
        // Ensure the number of electronics products matches the loaded ones
        assertEquals(3, electronicsProducts.size());
    }

    @Test
    public void testLoadExistingIds() {
        List<String> existingIds = shoppingManager.loadExistingIds();

        // Check if the loaded IDs contain specific IDs
        assertTrue(existingIds.contains("A001"));
        assertTrue(existingIds.contains("B003"));
    }

    @Test
    public void testSaveProductsToFile() {
        // Save products to a file
        shoppingManager.saveProductsToFile();

        // Check if the file exists
        File file = new File("productsList");
        assertTrue(file.exists());
    }

}
