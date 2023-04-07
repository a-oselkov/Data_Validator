package hexlet.code.schemas;

public class StringSchema extends BaseSchema {
    private final String required = "required";
    private final String minLength = "minLength";
    private final String contains = "contains";

    public StringSchema() {
        addValidation(required, v -> v instanceof String && !v.equals(""));
    }

    public final StringSchema required() {
        requiredEnable();
        return this;
    }

    public final StringSchema minLength(int stringLength) {
        addValidation(minLength, v -> v.toString().length() >= stringLength);
        return this;
    }

    public final StringSchema contains(String substring) {
        addValidation(contains, v -> v.toString().contains(substring));
        return this;
    }
}


