package hexlet.code.schemas;

public class StringSchema extends BaseSchema {

    public StringSchema() {
        addCheck("required", e -> e instanceof String && !e.equals(""));
    }

    public final StringSchema required() {
        setRequiredStatus();
        return this;
    }

    public final StringSchema minLength(int stringLength) {
        addCheck("minLength", e -> e.toString().length() >= stringLength);
        return this;
    }

    public final StringSchema contains(String substring) {
        addCheck("contains", e -> e.toString().contains(substring));
        return this;
    }
}


