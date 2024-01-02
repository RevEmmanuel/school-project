package project.shoppngManager.ShoppingManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.shoppngManager.ShoppingManager.generics.ShoppingCart;
import project.shoppngManager.ShoppingManager.generics.ShoppingManager;
import project.shoppngManager.ShoppingManager.models.CartBreakDown;
import project.shoppngManager.ShoppingManager.models.Product;
import project.shoppngManager.ShoppingManager.models.ProductType;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ClientController {

    private final ShoppingManager shoppingManager;
    private final ShoppingCart shoppingCart;

    @Autowired
    public ClientController(ShoppingManager shoppingManager, ShoppingCart shoppingCart) {
        this.shoppingManager = shoppingManager;
        this.shoppingCart = shoppingCart;
    }

    @GetMapping("/allProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(shoppingManager.getAllProducts());
    }

    @GetMapping("/electronicsProducts")
    public ResponseEntity<List<Product>> getElectronicProducts() {
        return ResponseEntity.ok(shoppingManager.getAllProducts(ProductType.ELECTRONICS));
    }

    @GetMapping("/clothingProducts")
    public ResponseEntity<List<Product>> getClothingProducts() {
        return ResponseEntity.ok(shoppingManager.getAllProducts(ProductType.CLOTHING));
    }

    @GetMapping("/allProducts/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable String productId) {
        return ResponseEntity.ok(shoppingManager.findProductById(productId));
    }

    @PostMapping("/add/allProducts/{productId}")
    public ResponseEntity<Map<Product, Integer>> addProductToCart(@PathVariable String productId) {
        return ResponseEntity.ok(shoppingCart.addProduct(shoppingManager.findProductById(productId)));
    }

    @GetMapping("/shoppingCart")
    public ResponseEntity<CartBreakDown> getCart() {
        return ResponseEntity.ok(shoppingCart.getCartBreakdown());
    }

}
