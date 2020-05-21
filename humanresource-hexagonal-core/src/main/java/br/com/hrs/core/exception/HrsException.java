package br.com.hrs.core.exception;

public class HrsException extends Exception {
   
    public HrsException(String message) {
        super(message);
    }

    public HrsException(Throwable cause) {
        super(cause);
    }

    public HrsException(String message, Throwable cause) {
        super(message, cause);
    }
}
