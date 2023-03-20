package hexlet.code.schemas;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class MapSchema extends BaseSchema {
    public final MapSchema required() {
        addFilter(e -> e instanceof Map);
        return this;
    }

    public final MapSchema sizeof(int size) {
        ObjectMapper mapper = new ObjectMapper();
        addFilter(e -> mapper.convertValue(e, Map.class).size() == size);
        return this;
    }

    public final MapSchema shape(Map<String, BaseSchema> map) {
        addFilter(e -> isShapedMapValid(map, (Map) e));
        return this;
    }

    private boolean isShapedMapValid(Map<String, BaseSchema> shapeMap, Map<String, Object> shapedMap) {
        return shapeMap.entrySet().stream()
                .allMatch(shape -> shape.getValue().isValid(shapedMap.get(shape.getKey())));
    }
}



