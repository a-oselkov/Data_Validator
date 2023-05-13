package hexlet.code.schemas;

public class StringSchema extends BaseSchema {
    private static final String MIN_LENGTH = "minLength";
    private static final String CONTAINS = "contains";

    public StringSchema() {
        addValidation(REQUIRED, value -> value instanceof String && !value.equals(""));
    }

    public final StringSchema required() {
        super.required();
        return this;
    }

    public final StringSchema minLength(int stringLength) {
        addValidation(MIN_LENGTH, value -> value.toString().length() >= stringLength);
        return this;
    }

    public final StringSchema contains(String substring) {
        addValidation(CONTAINS, value -> value.toString().contains(substring));
        return this;
    }
}


