package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {
    private final String required = "required";
    private final String positive = "positive";
    private final String range = "range";

    public NumberSchema() {
        addValidation(required, value -> value instanceof Integer);
    }

    public final NumberSchema required() {
        requiredEnable();
        return this;
    }

    public final NumberSchema positive() {
        addValidation(positive, value -> value == null || value instanceof Integer && (int) value > 0);
        return this;
    }

    public final NumberSchema range(int begin, int end) {
        addValidation(range, value -> (int) value >= begin && (int) value <= end);
        return this;
    }
}
