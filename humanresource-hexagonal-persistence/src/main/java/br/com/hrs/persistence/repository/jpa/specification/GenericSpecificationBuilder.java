package br.com.hrs.persistence.repository.jpa.specification;

import br.com.hrs.core.repository.specification.SearchOperation;
import br.com.hrs.core.repository.specification.SpecSearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class GenericSpecificationBuilder<T> {

    private final List<SpecSearchCriteria> params;

    public GenericSpecificationBuilder() {
        params = new ArrayList<>();
    }

    // API

    public final GenericSpecificationBuilder<T> with(final String key, final String operation, final Object value, final String prefix, final String suffix) {
        return with(null, key, operation, value, prefix, suffix);
    }

    public final GenericSpecificationBuilder<T> with(final String orPredicate, final String key, final String operation, final Object value, final String prefix, final String suffix) {
        params.add(new SpecSearchCriteria(orPredicate, key, operation, prefix, value, suffix));
        return this;
    }

    public Specification<T> build() {
        if (params.size() == 0)
            return null;

        Specification<T> result = new GenericSpecification(params.get(0));

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i).isOrPredicate()
                    ? Specification.where(result).or(new GenericSpecification(params.get(i)))
                    : Specification.where(result).and(new GenericSpecification(params.get(i)));
        }

        return result;
    }


    public final GenericSpecificationBuilder<T> with(GenericSpecification spec) {
        params.add(spec.getCriteria());
        return this;
    }

    public final GenericSpecificationBuilder<T> with(SpecSearchCriteria criteria) {
        params.add(criteria);
        return this;
    }

    public List<SpecSearchCriteria> getParams() {
        return params;
    }
}
