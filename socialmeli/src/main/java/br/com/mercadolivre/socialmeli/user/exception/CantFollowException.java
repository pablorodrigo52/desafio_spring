package br.com.mercadolivre.socialmeli.user.exception;

public class CantFollowException extends RuntimeException{
 
    private static final long serialVersionUID = 1L;
    public static final String CANT_FOLLOW_MSG = "Cant follow";

    public CantFollowException(String exception){
        super(exception);
    }
}
