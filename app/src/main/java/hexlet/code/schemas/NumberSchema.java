package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {
    private final String required = "required";
    private final String positive = "positive";
    private final String range = "range";

    public NumberSchema() {
        addValidation(required, v -> v instanceof Integer);
    }

    public final NumberSchema required() {
        requiredEnable();
        return this;
    }

    public final NumberSchema positive() {
        addValidation(positive, v -> v == null || v instanceof Integer && (int) v > 0);
        return this;
    }

    public final NumberSchema range(int begin, int end) {
        addValidation(range, v -> (int) v >= begin && (int) v <= end);
        return this;
    }
}
