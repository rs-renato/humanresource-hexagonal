package br.com.hrs.core.exception;

public class HrsBusinessException extends RuntimeException {

    public HrsBusinessException(String message) {
        super(message);
    }

    public HrsBusinessException(Throwable cause) {
        super(cause);
    }

    public HrsBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
