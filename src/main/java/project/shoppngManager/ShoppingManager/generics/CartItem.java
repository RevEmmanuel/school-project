package project.shoppngManager.ShoppingManager.generics;

import project.shoppngManager.ShoppingManager.models.Product;

public class CartItem {

    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity(int quantity) {
        this.quantity = this.quantity + quantity;
    }

    public void increaseQuantity() {
        this.increaseQuantity(1);
    }
}
