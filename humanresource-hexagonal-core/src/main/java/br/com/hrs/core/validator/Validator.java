package br.com.hrs.core.validator;

import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import org.springframework.core.GenericTypeResolver;

import java.util.Optional;

public interface Validator<E> {

    default void validate(Optional<E> optional){

        if (!optional.isPresent()) {
            Class<E> clazz = (Class<E>) GenericTypeResolver.resolveTypeArgument(getClass(), Validator.class);
            Error.of(clazz.getSimpleName()).when(FIELD.NOT_FOUND).trows();
        }

        validate(optional.get());
    }

    void validate(E entity);
}