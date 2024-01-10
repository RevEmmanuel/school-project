package project.shoppngManager.ShoppingManager.generics;

import jakarta.validation.constraints.NotBlank;

public class SearchCriteria {

    @NotBlank(message = "id cannot be blank")
    private String productId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

}