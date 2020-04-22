package br.com.hrs.core.exception;

public class HrsMandatoryException extends HrsBusinessException {

    public HrsMandatoryException(String message) {
        super(message);
    }

    public HrsMandatoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
