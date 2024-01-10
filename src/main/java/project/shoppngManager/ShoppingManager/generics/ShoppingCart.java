package project.shoppngManager.ShoppingManager.generics;


import org.springframework.stereotype.Service;
import project.shoppngManager.ShoppingManager.exceptions.OutOfStockException;
import project.shoppngManager.ShoppingManager.models.CartBreakDown;
import project.shoppngManager.ShoppingManager.models.Product;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCart {

    private static final List<CartItem> cart = new ArrayList<>();

    public List<CartItem> addProduct(Product product) {
        if (product.getAvailableItems() == 0) {
            throw new OutOfStockException("Product is out of stock");
        }
        if (checkIfProductIsAlreadyInCart(product)) {
            for (CartItem cartItem: cart) {
                if (cartItem.getProduct().getProductId().equals(product.getProductId())) {
                    cartItem.increaseQuantity();
                }
            }
        } else {
            cart.add(new CartItem(product, 1));
        }
        return cart;
    }

    public double calculateTotalCost() {
        double totalCost = 0;
        for (CartItem cartItem : cart) {
            double amount = cartItem.getProduct().getPrice() * cartItem.getQuantity();
            totalCost += amount;
        }
        return totalCost;
    }

    public CartBreakDown getCartBreakdown() {
        CartBreakDown checkCart = new CartBreakDown();
        checkCart.setCart(cart);
        checkCart.setDiscountApplied(checkIfDiscountApplies());
        BigDecimal totalPrice = BigDecimal.valueOf(calculateTotalCost()).setScale(2, RoundingMode.HALF_UP);
        checkCart.setTotalPrice(totalPrice.doubleValue());

        BigDecimal discount = totalPrice.multiply(BigDecimal.valueOf(0.2)).setScale(2, RoundingMode.HALF_UP);
        checkCart.setDiscount(discount.doubleValue());
        BigDecimal amountToBePaid = totalPrice.subtract(discount);
        checkCart.setAmountToBePaid(amountToBePaid.doubleValue());
        return checkCart;
    }

    public String clearCart() {
        cart.clear();
        return "SUCCESSFUL!";
    }

    private boolean checkIfDiscountApplies() {
        for (CartItem cartItem: cart) {
            if (cartItem.getQuantity() > 1) {
                return true;
            }
        }
        return false;
    }

    private boolean checkIfProductIsAlreadyInCart(Product product) {
        for (CartItem cartItem: cart) {
            if (cartItem.getProduct().getProductId().equals(product.getProductId())) {
                return true;
            }
        }
        return false;
    }

}
