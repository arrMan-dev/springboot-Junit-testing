package com.arrisdev.junittesting.model;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS) //instantiate the class test only once
class ContactManagerTest {

    ContactManager contactManager;

    /*@BeforeAll method lifecycle
        must be declared static and once
         */
    @BeforeAll
    public void setupAll(){
        System.out.println("Should print Before All test.");
    }

    /*BeforeEach method that object(s) that
    are repeated in each method test
     */

    @BeforeEach
    public void setup(){
        contactManager = new ContactManager();
    }

    @Test
    public void shouldCreateContact(){
        //ContactManager contactManager = new ContactManager();
        contactManager.addContact("arrisdev", "arrisdev@test.com", "Bonjour", "0053450986");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        //make sure that only one element is present
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
        Assertions.assertTrue(contactManager.getAllContacts().stream()
                .filter(contact -> contact.getUsername().equals("arrisdev") &&
                        contact.getEmail().equals("arrisdev@test.com") && contact.getPassword().equals("Bonjour") &&
                        contact.getPhoneNumber().equals("0053450986"))
                .findAny()
                .isPresent());
    }

    @Test
    @DisplayName("Should not create Contact when username is null")
    public void shouldThrowRuntimeExceptionWhenUsernameIsNull(){
        //ContactManager contactManager = new ContactManager();
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact(null, "arrisdev@test.com", "bonjour", "0053450986");
        });
    }

    @Test
    @DisplayName("Should not create Contact when email is null")
    public void shouldThrowRuntimeExceptionWhenEmailIsNull(){
        //ContactManager contactManager = new ContactManager();
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("arrisdev", null, "bonjour", "0053450986");
        });
    }

    @Test
    @DisplayName("Should not create Contact when phoneNumber is null")
    public void shouldThrowRuntimeExceptionWhenPhoneNumberIsNull(){
        //ContactManager contactManager = new ContactManager();
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("arrisdev", "arrisdev@test.com", "bonjour", null);
        });
    }

    @AfterEach
    public void tearDown(){
        System.out.println("Should Exceute After Each Test.");
    }

    @AfterAll
    public void tearDownAll(){
        System.out.println("Should be executed at the end of the Test.");
    }

    @Test
    @DisplayName("Should create contact only on Windows")
    //this test will be enabled only on Windows
    @DisabledOnOs(value = OS.WINDOWS, disabledReason = "Enabled only on Windows OS")
    public void shouldCreateContactOnlyOnWindows(){
        contactManager.addContact("arrisdev", "arrisdev@test.com", "Bonjour", "0053450986");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        //make sure that only one element is present
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
        Assertions.assertTrue(contactManager.getAllContacts().stream()
                .filter(contact -> contact.getUsername().equals("arrisdev") &&
                        contact.getEmail().equals("arrisdev@test.com") && contact.getPassword().equals("Bonjour") &&
                        contact.getPhoneNumber().equals("0053450986"))
                .findAny()
                .isPresent());
    }

    @Test
    @DisplayName("Should create contact only on Windows")
    //this test will be enabled only on Mac
    @EnabledOnOs(value = OS.MAC, disabledReason = "Enabled only on Mac")
    public void shouldCreateContactOnlyOnMac(){
        contactManager.addContact("arrisdev", "arrisdev@test.com", "Bonjour", "0053450986");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        //make sure that only one element is present
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
        Assertions.assertTrue(contactManager.getAllContacts().stream()
                .filter(contact -> contact.getUsername().equals("arrisdev") &&
                        contact.getEmail().equals("arrisdev@test.com") && contact.getPassword().equals("Bonjour") &&
                        contact.getPhoneNumber().equals("0053450986"))
                .findAny()
                .isPresent());
    }

    //assumption.getProperty
    @Test
    @DisplayName("Should create contact only on Developer machine")
    //this test will be enabled only on Windows
    @EnabledOnOs(value = OS.WINDOWS, disabledReason = "Enabled only on Windowws OS")
    public void shouldCreateContactOnlyOnDeveloperMachine(){
        Assumptions.assumeTrue("DEV".equals(System.getProperty("ENV")));
        contactManager.addContact("arrisdev", "arrisdev@test.com", "Bonjour", "0053450986");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        //make sure that only one element is present
        Assertions.assertEquals(1, contactManager.getAllContacts().size());

    }

    //repeated test to substitute test
    @DisplayName("Should test contact creation 5 times")
    @RepeatedTest(value = 5,
                    name = "Repeating Contact Creation Test {curentRepetition} of {totalRepetition}")
    public void shouldCreateContactRepeatedly(){
        contactManager.addContact("arrisdev", "arrisdev@test.com", "Bonjour", "0053450986");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        //make sure that only one element is present
        Assertions.assertEquals(1, contactManager.getAllContacts().size());

    }

    //@dataSource annotation
    @DisplayName("Should test contact creation with different inputs")
    @ParameterizedTest
    @ValueSource(strings = {"0123456789", "0234567890", "0234567891"})
    public void shouldCreateContactUsingValueSource(String phoneNumber){
        contactManager.addContact("arrisdev", "arrisdev@test.com", "Bonjour", phoneNumber);
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        //make sure that only one element is present
        Assertions.assertEquals(1, contactManager.getAllContacts().size());

    }

    //@MethodSourceAnnotation
    /* first create a list of string
     */
    @DisplayName("Should test contact creation with different inputs")
    @ParameterizedTest
    @MethodSource("phoneNumberList")//injecting the method to the parameter slot
    public void shouldTestPhoneNumberUsingMethodSource(String phoneNumber){
        contactManager.addContact("arrisdev", "arrisdev@test.com", "Bonjour", phoneNumber);
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        //make sure that only one element is present
        Assertions.assertEquals(1, contactManager.getAllContacts().size());

    }

    private static List<String> phoneNumberList(){
        return Arrays.asList("0123456789", "0876365485", "0345678932");

    }

    @DisplayName("Should test contact creation with different inputs")
    @ParameterizedTest
    @CsvSource({"0123456789", "0876365485", "0345678932"})//injecting the method to the parameter slot
    public void shouldTestPhoneNumberUsingCSVSource(String phoneNumber){
        contactManager.addContact("arrisdev", "arrisdev@test.com", "Bonjour", phoneNumber);
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        //make sure that only one element is present
        Assertions.assertEquals(1, contactManager.getAllContacts().size());

    }

    @DisplayName("Should test contact creation with different inputs")
    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")//injecting the method to the parameter slot
    public void shouldTestPhoneNumberUsingCSVFileSource(String phoneNumber){
        contactManager.addContact("arrisdev", "arrisdev@test.com", "Bonjour", phoneNumber);
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        //make sure that only one element is present
        Assertions.assertEquals(1, contactManager.getAllContacts().size());

    }
}