package br.com.hrs.core.repository.specification;

import org.springframework.util.StringUtils;

public enum SearchOperation {
    EQUALITY, NEGATION, GREATER_THAN, GREATER_THAN_EQUALS, LESS_THAN, LESS_THAN_EQUALS, LIKE, STARTS_WITH, ENDS_WITH, CONTAINS;

    public static final String[] SIMPLE_OPERATION_SET = { ":", "!", ">", "<", "~" };

    public static final String OR_PREDICATE_FLAG = "'";

    public static final String ZERO_OR_MORE_REGEX = "*";

    public static final String OR_OPERATOR = "OR";

    public static final String AND_OPERATOR = "AND";

    public static final String LEFT_PARANTHESIS = "(";

    public static final String RIGHT_PARANTHESIS = ")";

    public static SearchOperation getOperation(String input) {
        return isSimpleOperation(input) ? getSimpleOperation(input.charAt(0)) : getComposedOperation(input);
    }

    public static SearchOperation getOperation(String operation, String prefix, String suffix) {

        SearchOperation op;

        if (SearchOperation.isSimpleOperation(operation)) {

            op = SearchOperation.getSimpleOperation(operation.charAt(0));

            if (op == SearchOperation.EQUALITY) { // the operation may be complex operation
                final boolean startWithAsterisk = prefix != null && prefix.contains(SearchOperation.ZERO_OR_MORE_REGEX);
                final boolean endWithAsterisk = suffix != null && suffix.contains(SearchOperation.ZERO_OR_MORE_REGEX);

                if (startWithAsterisk && endWithAsterisk) {
                    op = SearchOperation.CONTAINS;
                } else if (startWithAsterisk) {
                    op = SearchOperation.ENDS_WITH;
                } else if (endWithAsterisk) {
                    op = SearchOperation.STARTS_WITH;
                }
            }
        }else{
            op = SearchOperation.getComposedOperation(operation);
        }

        return op;
    }

    public static SearchOperation getSimpleOperation(final char operation) {
        switch (operation) {
            case ':':
                return EQUALITY;
            case '!':
                return NEGATION;
            case '>':
                return GREATER_THAN;
            case '<':
                return LESS_THAN;
            case '~':
                return LIKE;
            default:
                return null;
        }
    }

    public static SearchOperation getComposedOperation(String operation){
        switch (operation){
            case ">:" : return GREATER_THAN_EQUALS;
            case "<:" : return LESS_THAN_EQUALS;
            default: return null;
        }
    }

    public static boolean isSimpleOperation(String operation) {
        return !StringUtils.isEmpty(operation) && operation.length() == 1
                && getSimpleOperation(operation.charAt(0)) != null;
    }
}

