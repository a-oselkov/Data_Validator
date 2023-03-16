package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberSchemaTest {
    Validator v = new Validator();
    NumberSchema schema = v.number();

    @Test
    void requiredTest() {
        assertTrue(schema.isValid(null));
        assertTrue(schema.positive().isValid(null));

        schema.required();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid("5"));
    }

    @Test
    void positive() {
        schema.positive();
        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(-1));
        assertTrue(schema.isValid(1));
    }

    @Test
    void range() {
        schema.range(5, 10);
        assertFalse(schema.isValid(4));
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));

    }

    @Test
    void isValid() {
        assertTrue(schema.required()
                .positive()
                .range(5, 10)
                .isValid(6));
    }
}
