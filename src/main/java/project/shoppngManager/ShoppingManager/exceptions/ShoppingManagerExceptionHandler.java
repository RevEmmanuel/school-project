package project.shoppngManager.ShoppingManager.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ShoppingManagerExceptionHandler {

    @ExceptionHandler(ShoppingManagerException.class)
    public ResponseEntity<ShoppingManagerExceptionResponse> handleStockAPIApplicationException(ShoppingManagerException e){
        var res = new ShoppingManagerExceptionResponse(e.getMessage(), e.getStatus());
        return new ResponseEntity<>(res, e.getStatus());
    }
}
