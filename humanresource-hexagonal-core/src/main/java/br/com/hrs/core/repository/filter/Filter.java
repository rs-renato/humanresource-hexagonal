package br.com.hrs.core.repository.filter;

import br.com.hrs.core.repository.specification.SpecSearchCriteria;

import java.util.function.Predicate;

public interface Filter<T> {


    default Predicate<T> filterToPredicate(){
        return t -> true;
    }

    default QueryFilter<T> filterToQuery(){
        return new QueryFilter<T>() {
            @Override
            protected void applyFilter() {
                // Does nothing by default
            }
        };
    }

    default SpecSearchCriteria[] filterToCriteria(){
        return new SpecSearchCriteria[]{

        };
    }
}