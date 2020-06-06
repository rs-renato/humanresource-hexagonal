package br.com.hrs.persistence.exception;

import br.com.hrs.core.exception.HrsRuntimeException;

public class HrsJpaException extends HrsRuntimeException {

    public HrsJpaException(String message) {
        super(message);
    }

    public HrsJpaException(Throwable cause) {
        super(cause);
    }

    public HrsJpaException(String message, Throwable cause) {
        super(message, cause);
    }
}
