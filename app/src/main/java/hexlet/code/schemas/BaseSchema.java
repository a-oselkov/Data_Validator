package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema {
    private final Map<String, Predicate> validations = new HashMap<>();
    private boolean requiredEnable = false;

    public final void addValidation(String name, Predicate check) {
        validations.put(name, check);
    }

    public final void setRequiredStatus(boolean b) {
        requiredEnable = b;
    }

    public final boolean isValid(Object data) {
        Predicate required = validations.get("required");

        if (!required.test(data) && !requiredEnable) {
            return true;
        }
        return validations.values().stream().allMatch(v -> v.test(data));
    }
}
