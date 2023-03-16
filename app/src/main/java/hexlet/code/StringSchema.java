package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class StringSchema {

    private final List<Predicate> filters = new ArrayList<>();

    public StringSchema required() {
        Predicate<Object> requiredIsOn = x -> (x instanceof String && !"".equals(x));
        filters.add(requiredIsOn);
        return this;
    }

    public StringSchema minLength(int stringLength) {
        Predicate<String> moreThan = x -> (x.length() >= stringLength);
        filters.add(moreThan);
        return this;
    }

    public StringSchema contains(String part) {
        Predicate<String> isContains = x -> x.contains(part);
        filters.add(isContains);
        return this;
    }

    public boolean isValid(Object lineToCheck) {
        for (Predicate filter : filters) {
            if (!filter.test(lineToCheck)) {
                return false;
            }
        }
        return true;
    }
}

