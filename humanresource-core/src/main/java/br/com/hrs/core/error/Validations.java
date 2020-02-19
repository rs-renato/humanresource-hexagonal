package br.com.hrs.core.error;

public class Validations {

    // eros genéricos
    public static void mandatory(String atribute) {
        throw new HrsMandatoryException(String.format("%s is mandatory!", atribute));
    }

    public static void notFound(String atribute) {
        throw new HrsNotFoundException(String.format("%s not found!", atribute));
    }
}