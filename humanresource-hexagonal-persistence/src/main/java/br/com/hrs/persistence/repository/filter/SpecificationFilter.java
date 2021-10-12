package br.com.hrs.persistence.repository.filter;

import br.com.hrs.core.repository.filter.Filter;
import br.com.hrs.core.repository.specification.SearchOperation;
import br.com.hrs.core.repository.specification.SpecSearchCriteria;
import br.com.hrs.persistence.repository.jpa.specification.GenericSpecificationBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpecificationFilter<T> implements Filter<T> {

    private String search;
    private Map<String, String> translator;

    public SpecificationFilter(String search) {
        this(search,  new HashMap<>());
    }

    public SpecificationFilter(String search, Map<String, String> translator) {
        this.search = search;
        this.translator = translator;
    }

    @Override
    public SpecSearchCriteria[] filterToCriteria() {

        String operationSetExper = String.join("|",SearchOperation.SIMPLE_OPERATION_SET);
        String regex = "(\\p{Punct}?)((?:\\w+\\.?)+)((?:" + operationSetExper + "){1,2})(\\p{Punct}?)(\\w+?)(\\p{Punct}?),";
//                      (\\p{Punct}?)((?:\\w+\\.?)+)((?:         :|!|>|<|~       ){1,2})(\\p{Punct}?)(\\w+?)(\\p{Punct}?),

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(search + ",");

        GenericSpecificationBuilder builder = new GenericSpecificationBuilder();

        String key = null;

        while (matcher.find()) {

            key = matcher.group(2);

            builder.with(matcher.group(1), translator.containsKey(key) ? translator.get(key) : key, matcher.group(3), matcher.group(5), matcher.group(4), matcher.group(6));
        }

        List<SpecSearchCriteria> criterias = builder.getParams();

        return criterias.toArray(new SpecSearchCriteria[criterias.size()]);
    }
}
