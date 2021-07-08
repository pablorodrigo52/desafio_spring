package br.com.mercadolivre.socialmeli.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CantFollowHandler {
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CantFollowException.class)
    public ResponseEntity<?> handlerCantFollowException(CantFollowException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
