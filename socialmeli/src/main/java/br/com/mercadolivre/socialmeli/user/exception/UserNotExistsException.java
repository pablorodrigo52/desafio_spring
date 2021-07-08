package br.com.mercadolivre.socialmeli.user.exception;

public class UserNotExistsException extends RuntimeException{
    
    private static final long serialVersionUID = 1L;
    public static final String USER_NOT_FOUND_MSG = "User not found";

    public UserNotExistsException(String exception){
        super(exception);
    }
}
