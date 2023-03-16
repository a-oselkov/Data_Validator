package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class NumberSchema {
    private final List<Predicate> filters = new ArrayList<>();

    public NumberSchema required() {
        Predicate<Object> requiredIsOn = x -> (x instanceof Integer);
        filters.add(requiredIsOn);
        return this;
    }

    public NumberSchema positive() {
        Predicate<Object> isPositive = x -> (x == null || (x instanceof Integer && (Integer) x > 0));
        filters.add(isPositive);
        return this;
    }

    public NumberSchema range(int begin, int end) {
        Predicate<Integer> inRange = x -> (x >= begin && x <= end);
        filters.add(inRange);
        return this;
    }

    public boolean isValid(Object numberToCheck) {
        for (Predicate filter : filters) {
            if (!filter.test(numberToCheck)) {
                return false;
            }
        }
        return true;
    }
}
