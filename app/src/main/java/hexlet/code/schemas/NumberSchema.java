package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {

    public NumberSchema() {
        addCheck("required", e -> e instanceof Integer);
    }

    public final NumberSchema required() {
        setRequiredStatus(true);
        return this;
    }

    public final NumberSchema positive() {
        addCheck("positive", e -> e == null || e instanceof Integer && (int) e > 0);
        return this;
    }

    public final NumberSchema range(int begin, int end) {
        addCheck("range", e -> (int) e >= begin && (int) e <= end);
        return this;
    }
}
