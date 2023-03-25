package hexlet.code.schemas;

public class StringSchema extends BaseSchema {

    public StringSchema() {
        addValidation("required", v -> v instanceof String && !v.equals(""));
    }

    public final StringSchema required() {
        setRequiredStatus(true);
        return this;
    }

    public final StringSchema minLength(int stringLength) {
        addValidation("minLength", v -> v.toString().length() >= stringLength);
        return this;
    }

    public final StringSchema contains(String substring) {
        addValidation("contains", v -> v.toString().contains(substring));
        return this;
    }
}


