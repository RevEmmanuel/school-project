package project.shoppngManager.ShoppingManager.exceptions;

import org.springframework.http.HttpStatus;

public class ShoppingManagerException extends RuntimeException {

    private HttpStatus status = HttpStatus.BAD_REQUEST;


    public HttpStatus getStatus() {
        return status;
    }

    public ShoppingManagerException() {
        this("An error occurred!");
    }

    public ShoppingManagerException(String message) {
        super(message);
    }

    public ShoppingManagerException(String message, HttpStatus status) {
        this(message);
        this.status = status;
    }
}
