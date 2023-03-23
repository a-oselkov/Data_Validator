package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private final Map<String, Predicate> checks = new HashMap<>();
    private boolean requiredEnable = false;

    public final void addCheck(String name, Predicate check) {
        checks.put(name, check);
    }

    public final void setRequiredStatus(boolean b) {
        requiredEnable = b;
    }

    public abstract BaseSchema required();

    public final boolean isValid(Object schema) {
        Predicate required = checks.get("required");

        if (!required.test(schema) && !requiredEnable) {
            return true;
        }
        return checks.values().stream().allMatch(check -> check.test(schema));
    }
}
