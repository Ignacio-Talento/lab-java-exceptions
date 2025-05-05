package com.ironhack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonsListTest {

    private Person person;
    private PersonsList personsList;

    @BeforeEach
    public void setUp() {
        // Create a sample person and a persons list for testing
        person = new Person(1, "John Doe", 30, "Developer");
        personsList = new PersonsList();
        personsList.addPerson(person);
        personsList.addPerson(new Person(2, "Jane Smith", 25, "Designer"));
    }

    //    Test case 2: Test the findByName method to ensure that it properly finds and returns the correct Person object when given a properly formatted name
    @Test
    public void testFindByNameWithValidName() {
        // Find a person by name
        Person foundPerson = personsList.findByName("John Doe");

        // Verify the found person has the correct properties
        assertEquals(1, foundPerson.getId());
        assertEquals("John Doe", foundPerson.getName());
        assertEquals(30, foundPerson.getAge());
        assertEquals("Developer", foundPerson.getOccupation());
    }

    //    Test case 3: Test the findByName method to ensure that it throws an exception if the name parameter is not properly formatted
    @Test
    public void testFindByNameWithInvalidFormat() {
        // Test with various improperly formatted names
        String[] invalidNames = {
                "JohnDoe",      // No space
                "John",         // Only first name
                "John Doe Jr",  // More than firstName lastName
                "123 456",      // Numbers, not letters
                " John Doe",    // Leading space
                "John Doe "     // Trailing space
        };

        for (String invalidName : invalidNames) {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                personsList.findByName(invalidName);
            });

            String expectedMessage = "Name must be formatted as 'firstName lastName'";
            assertTrue(exception.getMessage().contains(expectedMessage));
        }
    }

    //    Test case 3 extension: Test that findByName throws an exception when no matching person exists
    @Test
    public void testFindByNameWithNonExistentName() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            personsList.findByName("Alice Johnson");
        });

        String expectedMessage = "No person found with name: Alice Johnson";
        assertEquals(expectedMessage, exception.getMessage());
    }

    //    Test case 4: Test the clone method to ensure that it creates a new Person object with the same properties as the original, except with a new id
    @Test
    public void testClone() {
        // Clone the person with a new ID
        int newId = 99;
        Person clonedPerson = personsList.clone(person, newId);

        // Verify the cloned person has the same properties except ID
        assertEquals(newId, clonedPerson.getId());
        assertEquals(person.getName(), clonedPerson.getName());
        assertEquals(person.getAge(), clonedPerson.getAge());
        assertEquals(person.getOccupation(), clonedPerson.getOccupation());

        // Verify it's a different object instance
        assertNotSame(person, clonedPerson);

        // Verify equals method works correctly (should return true since it ignores ID)
        assertTrue(person.equals(clonedPerson));
    }


}