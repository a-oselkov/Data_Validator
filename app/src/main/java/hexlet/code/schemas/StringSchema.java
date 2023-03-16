package hexlet.code.schemas;

public class StringSchema extends BaseSchema {

    public final StringSchema required() {
        addFilter(e -> (e instanceof String && !"".equals(e)));
        return this;
    }

    public final StringSchema minLength(int stringLength) {
        addFilter(e -> (e.toString().length() >= stringLength));
        return this;
    }

    public final StringSchema contains(String substring) {
        addFilter(e -> e.toString().contains(substring));
        return this;
    }
}

