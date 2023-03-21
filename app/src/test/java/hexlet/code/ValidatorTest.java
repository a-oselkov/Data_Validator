package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {
    private final Validator v = new Validator();

    @Test
    void stringSchemaRequiredTest() {
        StringSchema schema = v.string();

        assertTrue(schema.isValid(1));
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));

        schema.required();

        assertFalse(schema.isValid(1));
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
    }

    @Test
    void stringSchemaMinLengthTest() {
        StringSchema schema = v.string();

        schema.minLength(5);

        assertFalse(schema.isValid("abc"));
        assertTrue(schema.isValid("abcde"));
    }

    @Test
    void stringSchemaContainsTest() {
        StringSchema schema = v.string();

        schema.contains("what");

        assertTrue(schema.isValid("what does the fox say"));

        schema.contains("?");

        assertFalse(schema.isValid("what does the fox say"));
    }

    @Test
    void stringSchemaFluentTest() {
        StringSchema schema = v.string();

        assertTrue(schema
                .required()
                .minLength(10)
                .contains("fox")
                .isValid("what does the fox say"));
    }

    @Test
    void numberSchemaRequiredTest() {
        NumberSchema schema = v.number();

        assertTrue(schema.isValid("5"));
        assertTrue(schema.isValid(null));
        assertTrue(schema.positive().isValid(null));

        schema.required();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid("5"));
    }

    @Test
    void numberSchemaPositiveTest() {
        NumberSchema schema = v.number();

        schema.positive();

        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(-1));
        assertTrue(schema.isValid(1));
    }

    @Test
    void numberSchemaRangeTest() {
        NumberSchema schema = v.number();

        schema.range(5, 10);

        assertFalse(schema.isValid(4));
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
    }

    @Test
    void numberSchemaFluentTest() {
        NumberSchema schema = v.number();

        assertTrue(schema
                .required()
                .positive()
                .range(2, 5)
                .isValid(3));
    }

    @Test
    void mapSchemaRequiredTest() {
        MapSchema schema = v.map();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid("Hello"));

        schema.required();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid("Hello"));
    }

    @Test
    void mapSchemaSizeOfTest() {
        MapSchema schema = v.map();

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");

        schema.sizeof(2);

        assertFalse(schema.isValid(data));
        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }

    @Test
    void mapSchemaFluentTest() {
        MapSchema schema = v.map();

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");

        assertTrue(schema
                .required()
                .sizeof(1)
                .isValid(data));
    }

    @Test
    void mapSchemaShapeTest() {
        MapSchema schema = v.map();

        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());

        schema.shape(schemas);
        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Vasya");
        human1.put("age", 100);
        assertTrue(schema.isValid(human1));

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertTrue(schema.isValid(human2));

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertFalse(schema.isValid(human3));

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        assertFalse(schema.isValid(human4));
    }
}

