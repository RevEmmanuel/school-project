package project.shoppngManager.ShoppingManager.exceptions;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends ShoppingManagerException {

    public ProductNotFoundException() {
        this("Product with that ID not found!");
    }

    public ProductNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

}