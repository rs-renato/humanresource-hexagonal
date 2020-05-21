package br.com.hrs.core.exception;

public class HrsRuntimeException extends RuntimeException {
    
    public HrsRuntimeException(String message) {
        super(message);
    }

    public HrsRuntimeException(Throwable cause) {
        super(cause);
    }

    public HrsRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
