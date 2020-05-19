package br.com.hrs.core.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface Validator<E> {
    Logger logger = LogManager.getLogger(Validator.class);
    void validate(E entity);
}
