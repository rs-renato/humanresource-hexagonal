package br.com.hrs.core.validator;

public interface Validator<E> {
    void validate(E entity);
}
