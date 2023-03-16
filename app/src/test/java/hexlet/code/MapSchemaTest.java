package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MapSchemaTest {
    private final Validator v = new Validator();
    private final MapSchema schema = v.map();

    @Test
    void required() {
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid("Hello"));

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

    @Test
    void isShapeValidTest() {
        Validator v1 = new Validator();
        MapSchema schema1 = v.map();

        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v1.string().required());
        schemas.put("age", v1.number().positive());

        schema1.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Vasya");
        human1.put("age", 100);
        assertTrue(schema1.isValid(human1));

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertTrue(schema1.isValid(human2));

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertFalse(schema1.isValid(human3));

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        assertFalse(schema1.isValid(human4));
    }
}
