package project.shoppngManager.ShoppingManager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.shoppngManager.ShoppingManager.generics.CartItem;
import project.shoppngManager.ShoppingManager.generics.SearchCriteria;
import project.shoppngManager.ShoppingManager.generics.ShoppingCart;
import project.shoppngManager.ShoppingManager.generics.ShoppingManager;
import project.shoppngManager.ShoppingManager.models.CartBreakDown;
import project.shoppngManager.ShoppingManager.models.Product;
import project.shoppngManager.ShoppingManager.models.ProductType;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class ClientController {

    private final ShoppingManager shoppingManager;
    private final ShoppingCart shoppingCart;

    @Autowired
    public ClientController(ShoppingManager shoppingManager, ShoppingCart shoppingCart) {
        this.shoppingManager = shoppingManager;
        this.shoppingCart = shoppingCart;
    }

    @Operation(summary = "Get all products",
            description = "Returns a Response entity containing all existing products in a List.")
    @GetMapping("/allProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(shoppingManager.getAllProducts());
    }

    @Operation(summary = "Get all electronic products",
            description = "Returns a Response entity containing all existing products of type ELECTRONICS in a List.")
    @GetMapping("/electronicsProducts")
    public ResponseEntity<List<Product>> getElectronicProducts() {
        return ResponseEntity.ok(shoppingManager.getAllProducts(ProductType.ELECTRONICS));
    }

    @Operation(summary = "Get all clothing products",
            description = "Returns a Response entity containing all existing products of type CLOTHING in a List.")
    @GetMapping("/clothingProducts")
    public ResponseEntity<List<Product>> getClothingProducts() {
        return ResponseEntity.ok(shoppingManager.getAllProducts(ProductType.CLOTHING));
    }

    @Operation(summary = "Get a particular product by the product's id",
            description = "Returns a Response entity containing the requested product and HTTP status code. If the product is not found, an exception is thrown.")
    @GetMapping("/allProducts/id")
    public ResponseEntity<Product> getProductById(@RequestBody @Valid SearchCriteria searchCriteria) {
        return ResponseEntity.ok(shoppingManager.findProductById(searchCriteria.getProductId()));
    }


    @Operation(summary = "Add a particular product to cart by the product's id",
            description = "Returns a Response entity containing the cart")
    @PostMapping("/add/allProducts")
    public ResponseEntity<List<CartItem>> addProductToCart(@RequestBody @Valid SearchCriteria searchCriteria) {
        return ResponseEntity.ok(shoppingCart.addProduct(shoppingManager.findProductById(searchCriteria.getProductId())));
    }

    @Operation(summary = "Get total cart for customer",
            description = "Returns a Response entity containing the requested cart and it's details.")
    @GetMapping("/shoppingCart")
    public ResponseEntity<CartBreakDown> getCart() {
        return ResponseEntity.ok(shoppingCart.getCartBreakdown());
    }

    @Operation(summary = "Clear entire cart",
            description = "Returns a string response")
    @DeleteMapping("/shoppingCart")
    public ResponseEntity<String> clearCart() {
        return ResponseEntity.ok(shoppingCart.clearCart());
    }

}
