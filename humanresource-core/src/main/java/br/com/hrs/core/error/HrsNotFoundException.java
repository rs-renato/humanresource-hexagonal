package br.com.hrs.core.error;

public class HrsNotFoundException extends HrsBusinessException {

    public HrsNotFoundException(String message) {
        super(message);
    }

    public HrsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
