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
        addFilter(e -> isShapedValid(map, (Map<String, Object>) e));
        return this;
    }

    private boolean isShapedValid(Map<String, BaseSchema> shapeMap, Map<String, Object> shapedMap) {
        for (Map.Entry<String, BaseSchema> shape : shapeMap.entrySet()) {
            Object data = shapedMap.get(shape.getKey());
            if (!shape.getValue().isValid(data)) {
                return false;
            }
        }
        return true;
    }
}




