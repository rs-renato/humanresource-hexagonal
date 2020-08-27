package br.com.hrs.core.repository.filter;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class QueryFilter<T>{

    private StringBuilder sql = new StringBuilder();
    private Object[] values;

    private List<Object[]> filters =  new ArrayList<>();

    protected QueryFilter() {
        applyFilter();
        buildFilter();
    }

    protected abstract void applyFilter();

    public String getSql() {
        return sql.toString();
    }
    public Object[] getValues() {
        return values;
    }

    protected void addClause(String fragment, Object ...fragmentValues){

        Object[] fragmentPair = new Object[2];

        fragmentPair[0] = fragment;
        fragmentPair[1] = fragmentValues;

        filters.add(fragmentPair);
    }

    private void buildFilter() {

        List<Object> filterValues = new ArrayList<>();
        //iterate and build sql
        filters.forEach(fragment -> {

            //fragment[0] -> sqlFragment
            //fragment[1] -> sqlFragmentValue (array)
            Object[] sqlFragmentValues = (Object[]) fragment[1];

            // if fragment value is not empty (is necessary look only the first element of sqlFragmentValue array)
            if (!StringUtils.isEmpty(sqlFragmentValues[0])) {

                this.sql.append(fragment[0]);

                // append sql fragment value
                for(int i = 0; i < sqlFragmentValues.length; i++){
                    filterValues.add(sqlFragmentValues[i]);
                }
            }
        });
        // retrieve values array
        this.values = filterValues.toArray();
    }
}