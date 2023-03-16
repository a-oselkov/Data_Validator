package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
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

    public static void main(String[] args) {
        Validator v = new Validator();

        MapSchema schema = v.map();

        System.out.println(schema.isValid(null)); // true

        schema.required();

        System.out.println(schema.isValid(null)); // false
        System.out.println(schema.isValid(1)); // false
        System.out.println(schema.isValid(new HashMap())); // true
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        System.out.println(schema.isValid(data)); // true

        schema.sizeof(2);

        System.out.println(schema.isValid(data));  // false
        data.put("key2", "value2");
        System.out.println(schema.isValid(data)); // true
    }
}

