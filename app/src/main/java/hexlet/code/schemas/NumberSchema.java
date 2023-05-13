package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {
    private static final String POSITIVE = "positive";
    private static final String RANGE = "range";

    public NumberSchema() {
        addValidation(REQUIRED, value -> value instanceof Integer);
    }

    @Override
    public final NumberSchema required() {
        super.required();
        return this;
    }

    public final NumberSchema positive() {
        addValidation(POSITIVE, value -> value == null || value instanceof Integer && (int) value > 0);
        return this;
    }

    public final NumberSchema range(int begin, int end) {
        addValidation(RANGE, value -> (int) value >= begin && (int) value <= end);
        return this;
    }
}
