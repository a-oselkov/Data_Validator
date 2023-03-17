package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema {
    private final List<Predicate> filters = new ArrayList<>();

    public final void addFilter(Predicate<Object> filter) {
        filters.add(filter);
    }

    public final boolean isValid(Object schema) {
        return filters.stream().allMatch(filter -> filter.test(schema));
    }
}
