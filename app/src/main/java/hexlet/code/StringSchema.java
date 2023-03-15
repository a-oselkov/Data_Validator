package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class StringSchema {

    private boolean requriedStatus = false;
    private final List<Predicate> filters = new ArrayList<>();

    public StringSchema required() {
        requriedStatus = true;
//        Predicate<Object> requiredIsOn = s -> (!"".equals(s));
//        filters.add(requiredIsOn);
        return this;
    }

    public StringSchema minLength(int stringLength) {
        Predicate<String> moreThan = i -> (i.length() >= stringLength);
        filters.add(moreThan);
        return this;
    }

    public StringSchema contains(String part) {
        Predicate<String> isContains = s -> s.contains(part);
        filters.add(isContains);
        return this;
    }

    public boolean isValid(Object lineToCheck) {
        if (!requriedStatus && lineToCheck == null) {
            return true;
        }
        if (!(lineToCheck instanceof String)) {
            return false;
        }
        if (requriedStatus && ((String) lineToCheck).isEmpty()) {
            return false;
        }
        for (Predicate filter : filters) {
            if (!filter.test(lineToCheck)) {
                return false;
            }
        }
        return true;
    }
}

