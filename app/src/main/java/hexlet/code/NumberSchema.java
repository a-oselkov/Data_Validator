package hexlet.code;

public class NumberSchema extends BaseSchema{

    public NumberSchema required() {
        addFilter(x -> (x instanceof Integer));
        return this;
    }

    public NumberSchema positive() {
        addFilter(x -> (x == null || (x instanceof Integer && (Integer) x > 0)));
        return this;
    }

    public NumberSchema range(int begin, int end) {
        addFilter(x -> ((Integer) x >= begin && (Integer) x <= end));
        return this;
    }
}
