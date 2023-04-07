package hexlet.code.schemas;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class MapSchema extends BaseSchema {
    private final String required = "required";
    private final String sizeof = "sizeof";
    private final String shape = "shape";

    public MapSchema() {
        addValidation(required, v -> v instanceof Map);
    }

    public final MapSchema required() {
        requiredEnable();
        return this;
    }

    public final MapSchema sizeof(int size) {
        ObjectMapper mapper = new ObjectMapper();
        addValidation(sizeof, v -> mapper.convertValue(v, Map.class).size() == size);
        return this;
    }

    public final MapSchema shape(Map<String, BaseSchema> map) {
        addValidation(shape, v -> isShapedMapValid(map, (Map) v));
        return this;
    }

    private boolean isShapedMapValid(Map<String, BaseSchema> shapeMap, Map<String, Object> shapedMap) {
        return shapeMap.entrySet().stream()
                .allMatch(m -> m.getValue().isValid(shapedMap.get(m.getKey())));
    }
}



