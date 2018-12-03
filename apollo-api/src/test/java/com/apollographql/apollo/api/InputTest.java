package com.apollographql.apollo.api;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public final class InputTest {


    @Test
    public void testInputEqualsOnNotNullValue() {
        String value = "Hello world!";
        Input<String> stringInput = Input.fromNullable(value);
        Input<String> anotherStringInput = Input.fromNullable(value);

        assertEquals(stringInput, anotherStringInput);
    }

    @Test
    public void testInputNotEqualsOnDifferentValues() {
        String value = "Hello world!";
        String value2 = "Bye world!";
        Input<String> stringInput = Input.fromNullable(value);
        Input<String> anotherStringInput = Input.fromNullable(value2);

        assertNotEquals(stringInput, anotherStringInput);
    }

    @Test
    public void testInputEqualsOnNullValue() {
        Input<String> stringInput = Input.fromNullable(null);
        Input<String> anotherStringInput = Input.fromNullable(null);

        assertEquals(stringInput, anotherStringInput);
    }

    @Test
    public void testInputEqualsOnNotNullObjects() {
        TestObject object = new TestObject("Hello world!");
        Input<TestObject> aInput = Input.fromNullable(object);
        Input<TestObject> anotherInput = Input.fromNullable(object);

        assertEquals(aInput, anotherInput);
    }

    @Test
    public void testInputNotEqualsOnDifferentObjects() {
        TestObject object = new TestObject("Hello world!");
        TestObject anotherObject = new TestObject("Bye world!");
        Input<TestObject> aInput = Input.fromNullable(object);
        Input<TestObject> anotherInput = Input.fromNullable(anotherObject);

        assertNotEquals(aInput, anotherInput);
    }

    @Test
    public void testInputEqualsOnObjectsWithNullValue() {
        TestObject object = new TestObject(null);
        Input<TestObject> aInput = Input.fromNullable(object);
        Input<TestObject> anotherInput = Input.fromNullable(object);

        assertEquals(aInput, anotherInput);
    }

    @Test
    public void testInputNotEqualsWhenAnObjectIsNull() {
        TestObject object = new TestObject(null);
        Input<TestObject> aInput = Input.fromNullable(object);
        Input<TestObject> anotherInput = Input.fromNullable(null);

        assertNotEquals(aInput, anotherInput);
    }
    //==================================================================
    //==================================================================
    class TestObject {

        private String value;

        TestObject(String  value) {
            this.value = value;
        }
    }
}
