package br.com.hrs.core.exception.error;

import br.com.hrs.core.exception.HrsBusinessException;
import br.com.hrs.core.exception.HrsMandatoryException;
import br.com.hrs.core.exception.HrsNotFoundException;

import java.lang.reflect.InvocationTargetException;

public enum FIELD {

    MANDATORY("is mandatory!", HrsMandatoryException.class),
    NOT_FOUND("not found!", HrsNotFoundException.class);

    private String desc;
    private Class<? extends HrsBusinessException> clazz;

    FIELD(String desc, Class<? extends HrsBusinessException> clazz) {
        this.desc = desc;
        this.clazz = clazz;
    }

    public String getDesc() {
        return desc;
    }

    public Class<? extends HrsBusinessException> getClazz() {
        return clazz;
    }

    void throwsError(String message) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        HrsBusinessException exception = getClazz().getDeclaredConstructor(String.class).newInstance(message);
        throw exception;
    }
}