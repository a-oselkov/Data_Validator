package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringSchemaTest {
    Validator v = new Validator();
    StringSchema schema = v.string();

    @Test
    void requiredTest() {
        assertTrue(schema.isValid(1));
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));

        schema.required();

        assertFalse(schema.isValid(1));
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
    }

    @Test
    void minLengthTest() {

        schema.minLength(5);

        assertFalse(schema.isValid("abc"));
        assertTrue(schema.isValid("abcde"));
    }

    @Test
    void containsTest() {
        schema.contains("what");
        assertTrue(schema.isValid("what does the fox say"));

        schema.contains("Q");
        assertFalse(schema.isValid("what does the fox say"));

        schema.contains("what");
        assertFalse(schema.isValid("what does the fox say"));
    }

    @Test
    void isValidTest() {
        assertTrue(schema
                .required()
                .minLength(10)
                .contains("what")
                .isValid("what does the fox say"));
    }
}
