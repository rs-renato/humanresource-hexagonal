package br.com.hrs.core.repository.filter;

import java.util.function.Predicate;

public interface Filter<T> {

    default T getModel(){
        return null;
    }

    default Predicate<T> filterToPredicate(){
        return t -> true;
    }

    default QueryFilter<T> filterToQuery(){
        return new QueryFilter<T>(null) {
            @Override
            protected void applyFilter(T type) {
                // Does nothing by default
            }
        };
    }
}