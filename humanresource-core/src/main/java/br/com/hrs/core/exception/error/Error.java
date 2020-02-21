package br.com.hrs.core.exception.error;

import br.com.hrs.core.exception.HrsBusinessException;

import java.lang.reflect.InvocationTargetException;

public class Error {

    private String message;
    private FIELD field;

    private Error(String message){
        this.message = message;
    }

    public static Error of(String attribute){
        return new Error(attribute);
    }

    public Error when(FIELD field) {
        this.message = String.format("%s %s", this.message, field.getDesc());
        this.field = field;
        return this;
    }

    public void trows() {
        try {
            this.field.throwsError(this.message);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            throw new HrsBusinessException(this.message);
        }
    }

    public void because(String reason) {
        this.message = String.format("%s. %s", this.message, reason);
        try {
            this.field.throwsError(this.message);
        } catch (Exception e) {
            throw new HrsBusinessException(this.message);
        }
    }
}