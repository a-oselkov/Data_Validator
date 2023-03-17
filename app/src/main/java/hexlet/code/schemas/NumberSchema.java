package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {

    public final NumberSchema required() {
        addFilter(e -> (e instanceof Integer));
        return this;
    }

    public final NumberSchema positive() {
        addFilter(e -> (e == null || (e instanceof Integer && (int) e > 0)));
        return this;
    }

    public final NumberSchema range(int begin, int end) {
        addFilter(e -> ((int) e >= begin && (int) e <= end));
        return this;
    }
}
