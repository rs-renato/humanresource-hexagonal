package br.com.hrs.persistence.repository.jpa.specification;

import br.com.hrs.core.repository.specification.SpecSearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class GenericSpecification<T> implements Specification<T> {

    private SpecSearchCriteria criteria;

    public GenericSpecification(final SpecSearchCriteria criteria) {
        super();
        this.criteria = criteria;
    }

    public SpecSearchCriteria getCriteria() {
        return criteria;
    }

    @Override
    public Predicate toPredicate(final Root<T> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) {

        Path<String> path = parseProperty(root, criteria.getKey());
        Object value = criteria.getValue();

        switch (criteria.getOperation()) {
            case EQUALITY:
                return builder.equal(path, value);
            case NEGATION:
                return builder.notEqual(path, value);
            case GREATER_THAN:
                return builder.greaterThan(path, value.toString());
            case GREATER_THAN_EQUALS:
                return builder.greaterThanOrEqualTo(path, value.toString());
            case LESS_THAN:
                return builder.lessThan(path, value.toString());
            case LESS_THAN_EQUALS:
                return builder.lessThanOrEqualTo(path, value.toString());
            case LIKE:
                return builder.like(builder.upper(path), value.toString().toUpperCase());
            case STARTS_WITH:
                return builder.like(path, value + "%");
            case ENDS_WITH:
                return builder.like(path, "%" + value);
            case CONTAINS:
                return builder.like(builder.upper(path), "%" + value.toString().toUpperCase() + "%");
            default:
                return null;
        }
    }

    private Path<String> parseProperty(Root<T> root, String key) {

        Path<String> path;
        if (key.contains(".")) {
            // Nested properties
            String[] pathSteps = key.split("\\.");
            String step = pathSteps[0];
            path = root.get(step);

            for (int i = 1; i <= pathSteps.length - 1; i++) {
                path = path.get(pathSteps[i]);
            }
        } else {
            path = root.get(key);
        }
        return path;
    }
}
