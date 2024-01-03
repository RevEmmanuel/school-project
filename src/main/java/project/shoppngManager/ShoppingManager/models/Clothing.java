package project.shoppngManager.ShoppingManager.models;

import java.io.Serializable;

public class Clothing extends Product {

    private String size;
    private String color;

    public Clothing(String productId, String productName, int availableItems, double price,
                    String size, String color, String info) {
        super(productId, productName, availableItems, price, ProductType.CLOTHING, info);
        this.size = size;
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
