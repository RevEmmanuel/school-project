package project.shoppngManager.ShoppingManager.exceptions;

import org.springframework.http.HttpStatus;

public class OutOfStockException extends ShoppingManagerException {

    public OutOfStockException() {
        this("Product is out of stock!");
    }

    public OutOfStockException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

}
