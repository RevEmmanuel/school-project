package project.shoppngManager.ShoppingManager.models;

public class Electronics extends Product {

    private String brand;
    private String warrantyPeriod;

    public Electronics(String productId, String productName, int availableItems, double price,
                       String brand, String warrantyPeriod, String info) {
        super(productId, productName, availableItems, price, ProductType.ELECTRONICS, info);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(String warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }
}
