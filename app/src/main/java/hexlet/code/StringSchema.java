package hexlet.code;

public class StringSchema extends BaseSchema {

    public StringSchema required() {
        addFilter(x -> (x instanceof String && !"".equals(x)));
        return this;
    }

    public StringSchema minLength(int stringLength) {
        addFilter(x -> (x.toString().length() >= stringLength));
        return this;
    }

    public StringSchema contains(String part) {
        addFilter(x -> x.toString().contains(part));
        return this;
    }
}

