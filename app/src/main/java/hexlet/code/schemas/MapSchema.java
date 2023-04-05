package hexlet.code.schemas;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class MapSchema extends BaseSchema {

    public MapSchema() {
        addValidation("required", v -> v instanceof Map);
    }

    public final MapSchema required() {
        super.required();
        return this;
    }

    public final MapSchema sizeof(int size) {
        ObjectMapper mapper = new ObjectMapper();
        addValidation("sizeof", v -> mapper.convertValue(v, Map.class).size() == size);
        return this;
    }

    public final MapSchema shape(Map<String, BaseSchema> map) {
        addValidation("shape", v -> isShapedMapValid(map, (Map) v));
        return this;
    }

    private boolean isShapedMapValid(Map<String, BaseSchema> shapeMap, Map<String, Object> shapedMap) {
        return shapeMap.entrySet().stream()
                .allMatch(shape -> shape.getValue().isValid(shapedMap.get(shape.getKey())));
    }
}



