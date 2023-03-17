#### Hexlet tests and linter status:
[![Actions Status](https://github.com/a-oselkov/java-project-78/workflows/hexlet-check/badge.svg)](https://github.com/a-oselkov/java-project-78/actions)
![Java CI](https://github.com/a-oselkov/java-project-78/workflows/Java%20CI/badge.svg)
[![Maintainability](https://api.codeclimate.com/v1/badges/7b897c1d4ed0d14acd77/maintainability)](https://codeclimate.com/github/a-oselkov/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/7b897c1d4ed0d14acd77/test_coverage)](https://codeclimate.com/github/a-oselkov/java-project-78/test_coverage)


### Data validator:

"Data validator" is a library with which you can check the correctness of data, for example, the data of forms filled in by users.
Implemented the ability to check strings, numbers and Map type objects.


#### Validation of strings:

- required() - any non-empty string.
- minLength(number) - the string is equal to or longer than the specified number.
- contains(substring) - the string contains a specific substring.

Filters accumulate - each subsequent one does not cancel the previous one.

```sh
Validator v = new Validator();

StringSchema schema = v.string();

// As long as the required() method is called, null and an empty string are considered valid
schema.isValid(""); // true
schema.isValid(null); // true

schema.required();

schema.isValid(""); // false
schema.isValid(null); // false
schema.isValid("5"); // false

schema.isValid("what does the fox say"); // true

schema.contains("what").isValid("what does the fox say"); // true
schema.contains("whatthe").isValid("what does the fox say"); // false

schema.isValid("what does the fox say"); // false - since another contains("whatthe") check has been added
```


#### Validation of numbers:

- required() – any number including zero.
- positive() is a positive number.
- range(x, y) – the range in which the numbers should fall, including the boundaries of [x, y].

Filters accumulate - each subsequent one does not cancel the previous one.

```sh
Validator v = new Validator();

NumberSchema schema = v.number();

// As long as the required() method is called, null are considered valid

schema.positive().isValid(null); // true

schema.required();

schema.isValid(null); // false
schema.isValid(10) // true
schema.isValid("5"); // false
schema.isValid(-10); // false

schema.range(5, 10);

schema.isValid(5); // true
schema.isValid(11); // false
```


#### Validation of Map type objects:

- required() – требуется тип данных Map.
- sizeof(number) – количество пар ключ-значений в объекте Map должно быть равно заданному.

Filters accumulate - each subsequent one does not cancel the previous one.

```sh
Validator v = new Validator();

MapSchema schema = v.map();

// As long as the required() method is called, null are considered valid
schema.isValid(null); // true

schema.required();

schema.isValid(null) // false

Map<String, String> data = new HashMap<>();
data.put("key1", "value1");
schema.isValid(data); // true

schema.sizeof(2);

schema.isValid(data);  // false
data.put("key2", "value2");
schema.isValid(data); // true
```

#### Nested validation:

- shape(schemas) - allows you to describe validation for Map object values by keys.

```sh
Map<String, BaseSchema> schemas = new HashMap<>();
schemas.put("name", v.string().required());
schemas.put("age", v.number().positive());
schema.shape(schemas);

Map<String, Object> human1 = new HashMap<>();
human1.put("name", "Kolya");
human1.put("age", 100);
schema.isValid(human1); // true

Map<String, Object> human2 = new HashMap<>();
human2.put("name", "Maya");
human2.put("age", null);
schema.isValid(human2); // true - the required() method was not applied, so null is considered valid.

Map<String, Object> human3 = new HashMap<>();
human3.put("name", "");
human3.put("age", null);
schema.isValid(human3); // false - the required() method is defined for the "name" key, so an empty string is not considered valid

Map<String, Object> human4 = new HashMap<>();
human4.put("name", "Valya");
human4.put("age", -5);
schema.isValid(human4); // false
```
