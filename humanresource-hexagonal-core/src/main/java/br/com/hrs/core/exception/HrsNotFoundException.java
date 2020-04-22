package br.com.hrs.core.exception;

public class HrsNotFoundException extends HrsBusinessException {

    public HrsNotFoundException(String message) {
        super(message);
    }

    public HrsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
