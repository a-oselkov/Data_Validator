package hexlet.code.schemas;

public class StringSchema extends BaseSchema {
    private final String required = "required";
    private final String minLength = "minLength";
    private final String contains = "contains";

    public StringSchema() {
        addValidation(required, value -> value instanceof String && !value.equals(""));
    }

    public final StringSchema required() {
        requiredEnable();
        return this;
    }

    public final StringSchema minLength(int stringLength) {
        addValidation(minLength, value -> value.toString().length() >= stringLength);
        return this;
    }

    public final StringSchema contains(String substring) {
        addValidation(contains, value -> value.toString().contains(substring));
        return this;
    }
}


