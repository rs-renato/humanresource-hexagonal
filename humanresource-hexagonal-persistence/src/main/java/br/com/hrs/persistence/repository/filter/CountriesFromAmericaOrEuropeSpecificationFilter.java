package br.com.hrs.persistence.repository.filter;

import br.com.hrs.core.model.Country;
import br.com.hrs.core.repository.filter.Filter;
import br.com.hrs.core.repository.filter.QueryFilter;
import br.com.hrs.core.repository.specification.SearchOperation;
import br.com.hrs.core.repository.specification.SpecSearchCriteria;

public class CountriesFromAmericaOrEuropeSpecificationFilter implements Filter<Country> {

    @Override
    public SpecSearchCriteria[] filterToCriteria() {

        return new SpecSearchCriteria[] {
                new SpecSearchCriteria("region.id", SearchOperation.EQUALITY, 1),
                new SpecSearchCriteria("'","region.id", SearchOperation.EQUALITY, 4),
        };
    }

    @Override
    public QueryFilter<Country> filterToQuery() {
        return new QueryFilter<Country>() {
            @Override
            protected void applyFilter() {
                addClause(" REGION_ID = ? ", 1);
                addClause(" OR REGION_ID = ? ", 4);
            }
        };
    }
}
