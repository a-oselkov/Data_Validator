package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema {
    private final String required = "required";
    private final Map<String, Predicate> validations = new HashMap<>();
    private boolean isRequired = false;


    public final void addValidation(String name, Predicate validation) {
        validations.put(name, validation);
    }

    public final void requiredEnable() {
        isRequired = true;
    }

    public final boolean isValid(Object data) {
        if (!isRequired) {
            Predicate validation = validations.get(required);
            if (!validation.test(data)) {
                return true;
            }
        }
        return validations.values().stream().allMatch(value -> value.test(data));
    }
}
