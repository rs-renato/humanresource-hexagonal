package br.com.hrs.persistence.repository.filter;

import br.com.hrs.core.repository.filter.Filter;
import br.com.hrs.core.repository.specification.SearchOperation;
import br.com.hrs.core.repository.specification.SpecSearchCriteria;
import br.com.hrs.persistence.repository.jpa.specification.GenericSpecificationBuilder;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpecificationFilter<T> implements Filter<T> {

    private String search;

    public SpecificationFilter(String search) {
        this.search = search;
    }

    @Override
    public SpecSearchCriteria[] filterToCriteria() {

        String operationSetExper = String.join("|",SearchOperation.SIMPLE_OPERATION_SET);
//        String regex = "((?:\\w+\\.?)+)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),"; working without orPredicate
        String regex = "(\\p{Punct}?)((?:\\w+\\.?)+)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(search + ",");

        GenericSpecificationBuilder builder = new GenericSpecificationBuilder();

        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(5), matcher.group(4), matcher.group(6));
        }

        List<SpecSearchCriteria> criterias = builder.getParams();

        return criterias.toArray(new SpecSearchCriteria[criterias.size()]);
    }
}
