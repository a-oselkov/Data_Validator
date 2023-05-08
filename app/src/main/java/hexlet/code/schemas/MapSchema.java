package hexlet.code.schemas;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class MapSchema extends BaseSchema {
    private static final String SIZEOF = "sizeof";
    private static final String SHAPE = "shape";

    public MapSchema() {
        addValidation(REQUIRED, value -> value instanceof Map);
    }

    public final MapSchema required() {
        requiredEnable();
        return this;
    }

    public final MapSchema sizeof(int size) {
        ObjectMapper mapper = new ObjectMapper();
        addValidation(SIZEOF, value -> mapper.convertValue(value, Map.class).size() == size);
        return this;
    }

    public final MapSchema shape(Map<String, BaseSchema> map) {
        addValidation(SHAPE, value -> isShapedMapValid(map, (Map) value));
        return this;
    }

    private boolean isShapedMapValid(Map<String, BaseSchema> shapeMap, Map<String, Object> shapedMap) {
        return shapeMap.entrySet().stream()
                .allMatch(map -> map.getValue().isValid(shapedMap.get(map.getKey())));
    }
}



