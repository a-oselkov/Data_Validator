package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema {
    private final List<Predicate> filters = new ArrayList<>();

    public void addFilter(Predicate<Object> p) {
        filters.add(p);
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
