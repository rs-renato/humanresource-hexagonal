package br.com.hrs.core.validator;

import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.util.GenericTypeUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public interface Validator<E> {
    Logger logger = LogManager.getLogger(Validator.class);

    default void validate(Optional<E> optional){

        if (!optional.isPresent()) {
            Error.of(GenericTypeUtil.getGenericName(optional.getClass())).when(FIELD.MANDATORY).trows();
        }

        validate(optional.get());
    }

    void validate(E entity);
}
