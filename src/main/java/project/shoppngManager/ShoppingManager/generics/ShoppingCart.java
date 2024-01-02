package project.shoppngManager.ShoppingManager.generics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.shoppngManager.ShoppingManager.exceptions.OutOfStockException;
import project.shoppngManager.ShoppingManager.models.CartBreakDown;
import project.shoppngManager.ShoppingManager.models.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShoppingCart {

    private static final List<Product> products = new ArrayList<>();
    private static final Map<String, Integer> cart = new HashMap<>();
    private final ShoppingManager shoppingManager;

    @Autowired
    public ShoppingCart(ShoppingManager shoppingManager) {
        this.shoppingManager = shoppingManager;
    }

    public Map<Product, Integer> addProduct(Product product) {
        if (product.getAvailableItems() == 0) {
            throw new OutOfStockException("Product is out of stock");
        }
        Integer quantity = 0;
        if (cart.containsKey(product.getProductId())) {
            quantity = cart.get(product.getProductId());
            cart.remove(product.getProductId());
        }
        cart.put(product.getProductId(), quantity + 1);
        products.add(product);
        return convertCartToProductList();
    }

    public double calculateTotalCost() {
        double totalCost = 0;
        for (Product product : products) {
            totalCost += product.getPrice();
        }
        return totalCost;
    }

    public CartBreakDown getCartBreakdown() {
        CartBreakDown checkCart = new CartBreakDown();
        checkCart.setCart(convertCartToProductList());
        checkCart.setDiscountApplied(checkIfDiscountApplies());
        checkCart.setTotalPrice(calculateTotalCost());
        checkCart.setDiscount(20.00 * checkCart.getTotalPrice() / 100.00);
        checkCart.setAmountToBePaid(checkCart.getTotalPrice() - checkCart.getDiscount());
        return checkCart;
    }

    private Map<Product, Integer> convertCartToProductList() {
        Map<Product, Integer> newCart = new HashMap<>();
        for (String productId : cart.keySet()) {
            Product foundProduct = shoppingManager.findProductById(productId);
            if (foundProduct != null) {
                newCart.put(foundProduct, cart.get(productId));
            }
        }
        return newCart;
    }

    private boolean checkIfDiscountApplies() {
        for (String productId : cart.keySet()) {
            if (cart.get(productId) > 1) return true;
        }
        return false;
    }
}
