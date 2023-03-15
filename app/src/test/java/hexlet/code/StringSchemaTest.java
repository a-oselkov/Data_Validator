package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringSchemaTest {
    Validator v = new Validator();

    StringSchema schema = v.string();

    @Test
    void requiredTest() {
        assertFalse(schema.isValid(1));
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));

        schema.required();

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
        schema.contains("wh");
        assertTrue(schema.isValid("what does the fox say"));

        schema.contains("Q");
        assertFalse(schema.isValid("what does the fox say"));
    }

    @Test
    void isValidTest() {
        assertTrue(schema.required()
                .minLength(10)
                .contains("what")
                .isValid("what does the fox say"));
    }
}