package hexlet.code.schemas;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class MapSchema extends BaseSchema {
    public final MapSchema required() {
        addFilter(e -> (e instanceof Map<?, ?>));
        return this;
    }

    public final MapSchema sizeof(int size) {
        ObjectMapper mapper = new ObjectMapper();
        addFilter(e -> (mapper.convertValue(e, Map.class).size() == size));
        return this;
    }

    public final MapSchema shape(Map<String, BaseSchema> map) {
        addFilter(e -> isShapeValid(map, (Map<String, Object>) e));
        return this;
    }

    private boolean isShapeValid(Map<String, BaseSchema> filterMap, Map<String, Object> filtredMap) {
        for (Map.Entry<String, BaseSchema> filter : filterMap.entrySet()) {
            Object data = filtredMap.get(filter.getKey());
            if (!filter.getValue().isValid(data)) {
                return false;
            }
        }
        return true;
    }
}




