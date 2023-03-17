package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema {
    private final List<Predicate> filters = new ArrayList<>();

    public final void addFilter(Predicate<Object> p) {
        filters.add(p);
    }

    public final boolean isValid(Object obj) {
        for (Predicate filter : filters) {
            if (!filter.test(obj)) {
                return false;
            }
        }
        return true;
    }
}
