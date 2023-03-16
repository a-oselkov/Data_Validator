package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {

    public final NumberSchema required() {
        addFilter(x -> (x instanceof Integer));
        return this;
    }

    public final NumberSchema positive() {
        addFilter(x -> (x == null || (x instanceof Integer && (Integer) x > 0)));
        return this;
    }

    public final NumberSchema range(int begin, int end) {
        addFilter(x -> ((Integer) x >= begin && (Integer) x <= end));
        return this;
    }
}
