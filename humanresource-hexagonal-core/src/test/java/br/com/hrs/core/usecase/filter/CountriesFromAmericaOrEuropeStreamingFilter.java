package br.com.hrs.core.usecase.filter;

import br.com.hrs.core.model.Country;
import br.com.hrs.core.repository.filter.Filter;

import java.util.function.Predicate;

public class CountriesFromAmericaOrEuropeStreamingFilter implements Filter<Country> {

    @Override
    public Predicate<Country> filterToPredicate() {
        return (country) ->
                country.getRegion().getId().equals(4) ||    // America
                country.getRegion().getId().equals(1);      // Europe
    }
}
