package project.shoppngManager.ShoppingManager.models;

import java.io.Serializable;

public abstract class Product implements Serializable {
    private String productId;
    private String productName;
    private int availableItems;
    private double price;
    private final ProductType type;

    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Product(String productId, String productName, int availableItems, double price, ProductType type, String info) {
        this.productId = productId;
        this.productName = productName;
        this.availableItems = availableItems;
        this.price = price;
        this.type = type;
        this.info = info;
    }

    public ProductType getType() {
        return type;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getAvailableItems() {
        return availableItems;
    }

    public void setAvailableItems(int availableItems) {
        this.availableItems = availableItems;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
