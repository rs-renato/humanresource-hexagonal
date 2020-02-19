package br.com.hrs.core.error;

public class HrsBusinessException extends RuntimeException {

    public HrsBusinessException(String message) {
        super(message);
    }

    public HrsBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
