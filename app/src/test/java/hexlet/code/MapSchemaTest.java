package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MapSchemaTest {
    Validator v = new Validator();
    MapSchema schema = v.map();

    @Test
    void required() {
        assertTrue(schema.isValid(null));

        schema.required();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid("Hello"));
    }

    @Test
    void sizeof() {
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");

        schema.sizeof(2);

        assertFalse(schema.isValid(data));
        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }

    @Test
    void isValid() {
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");

        assertTrue(schema
                .required()
                .sizeof(1)
                .isValid(data));
    }
}
