package com.ironhack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {

    private Person person;
    private PersonsList personsList;

    @BeforeEach
    public void setUp() {
        // Create a sample person and a persons list
        person = new Person(1, "John Doe", 30, "Developer");
        personsList = new PersonsList();
        personsList.addPerson(person);
        personsList.addPerson(new Person(2, "Jane Smith", 25, "Designer"));
    }

    // Test case 1: Test the setAge method to ensure that it throws an error if the age is less than 0
    @Test
    public void testSetAgeWithNegativeValue() {
        // Attempt to set a negative age
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            person.setAge(-5);
        });

        // Verify the exception message
        String expectedMessage = "Age cannot be negative";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    //    Test case 1 extension: Verify that valid age updates work correctly
    @Test
    public void testSetAgeWithValidValue() {
        // Set a valid age
        person.setAge(35);

        // Verify the age was updated
        assertEquals(35, person.getAge());
    }
}