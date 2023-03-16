package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class MapSchema extends BaseSchema {
    public MapSchema required() {
        addFilter(x -> (x instanceof Map<?, ?>));
        return this;
    }

    public MapSchema sizeof(int size) {
        ObjectMapper mapper = new ObjectMapper();
        addFilter(x -> (mapper.convertValue(x, Map.class).size() == size));
        return this;
    }
}



