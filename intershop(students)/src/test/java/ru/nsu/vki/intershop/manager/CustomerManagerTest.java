package ru.nsu.vki.intershop.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import ru.nsu.vki.intershop.database.IDBService;
import ru.nsu.vki.intershop.database.data.CustomerRepo;

import java.util.UUID;

import static org.mockito.Mockito.*;

class CustomerManagerTest {
    private IDBService dbService;
    private CustomerManager customerManager;
    private CustomerRepo createCustomerInput;
    private static final String passSize = "length should be more or equal to 6 symbols and less or equal to 12 symbols.";
    private static final String symbolsSize = "length should be more or equal to 2 symbols and less or equal to 12 symbols.";
    private static final String logSize = "length should be more or equal to 6 symbols and less or equal to 30 symbols.";

    @BeforeEach
    void init() {
        // create stubs for the test's class
        dbService = mock(IDBService.class);
        Logger logger = mock(Logger.class);

        // create the test's class
        customerManager = new CustomerManager(dbService, logger);
        createCustomerInput = new CustomerRepo();
        createCustomerInput.setFirstName("George");
        createCustomerInput.setLastName("Anderson");
        createCustomerInput.setLogin("george_anderson@gmail.com");
        createCustomerInput.setPass("Ab@i1tSk");
        createCustomerInput.setBalance(0);
    }
    /*
    @Test
    void testCreateCustomer() {
        // настраиваем mock.

        CustomerRepo createCustomerOutput = new CustomerRepo();
        createCustomerOutput.setId(UUID.randomUUID());
        createCustomerOutput.setFirstName("George");
        createCustomerOutput.setLastName("Anderson");
        createCustomerOutput.setLogin("george_anderson@gmail.com");
        createCustomerOutput.setPass("Ab@i1tSk");
        createCustomerOutput.setBalance(0);

        when(dbService.createCustomer(createCustomerInput)).thenReturn(createCustomerOutput);

        // Вызываем метод, который хотим протестировать
        CustomerRepo customer = customerManager.createCustomer(createCustomerInput);

        // Проверяем результат выполенния метода
        assertEquals(customer.getId(), createCustomerOutput.getId());

        // Проверяем, что метод по созданию Customer был вызван ровно 1 раз с определенными аргументами
        verify(dbService, times(1)).createCustomer(createCustomerInput);

        // Проверяем, что другие методы не вызывались...
        verify(dbService, times(0)).getCustomers();
    }

    // Как не надо писать тест...
    // Используйте expected exception аннотации или expected exception rule...

    @Test
    void testCreateCustomerWithNullArgument_Wrong() {
        try {
            customerManager.createCustomer(null);
        } catch (IllegalArgumentException ex) {
            assertEquals("Argument 'customerData' is null.", ex.getMessage());
        }
    }

    @Test
    void testCreateCustomerWithNullArgument_Right() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                customerManager.createCustomer(null));
        assertEquals("Argument 'customerData' is null.", exception.getMessage());
    }

    @Test
    void testCreateCustomerWithShortPassword() {
        createCustomerInput.setPass("123qwe");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> customerManager.createCustomer(createCustomerInput));
        assertEquals("Password is easy.", exception.getMessage());
    }
    @Test
    void testCreateCustomerWithWrongPassword() {
        createCustomerInput.setPass("1");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> customerManager.createCustomer(createCustomerInput));
        assertEquals(String.format("Password's %s ", passSize), exception.getMessage());
    }
    */

    /** #1 **/
    @Test
    void testCreateCustomerWithPasswordWithoutNumbers(){
        createCustomerInput.setPass("Ab@itSk");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> customerManager.createCustomer(createCustomerInput));
        assertEquals("Password should have lowercase, uppercase letters, numbers & special characters and not whitespace.", exception.getMessage());
    }

    /** #2 **/
    @Test
    void testCreateCustomerWithPasswordWithoutUpperCaseLetters(){
        createCustomerInput.setPass("ab@i1tsk");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> customerManager.createCustomer(createCustomerInput));
        assertEquals("Password should have lowercase, uppercase letters, numbers & special characters and not whitespace.", exception.getMessage());
    }

    /** #3 **/
    @Test
    void testCreateCustomerWithPasswordWithoutLowerCaseLetters(){
        createCustomerInput.setPass("AB@I1TSK");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> customerManager.createCustomer(createCustomerInput));
        assertEquals("Password should have lowercase, uppercase letters, numbers & special characters and not whitespace.", exception.getMessage());
    }

    /** #4 **/
    @Test
    void testCreateCustomerWithPasswordWithoutSpecialCharacters(){
        createCustomerInput.setPass("Abi1tSk");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> customerManager.createCustomer(createCustomerInput));
        assertEquals("Password should have lowercase, uppercase letters, numbers & special characters and not whitespace.", exception.getMessage());
    }

    /** #5 **/
    @Test
    void testCreateCustomerWithPasswordContainsLogin(){
        createCustomerInput.setLogin("Absasu");
        createCustomerInput.setPass("Absasu@i1tSk");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> customerManager.createCustomer(createCustomerInput));
        assertEquals("Password must not match Login.", exception.getMessage());
    }

    /** #6 **/
    @Test
    void testCreateCustomerWithPasswordContainsFirstName(){
        createCustomerInput.setFirstName("Abs");
        createCustomerInput.setPass("Abs@i1tSk");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> customerManager.createCustomer(createCustomerInput));
        assertEquals("Password must not match FirstName.", exception.getMessage());
    }

    /** #7 **/
    @Test
    void testCreateCustomerWithPasswordContainsLastName(){
        createCustomerInput.setLastName("Abs");
        createCustomerInput.setPass("Abs@i1tSk");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> customerManager.createCustomer(createCustomerInput));
        assertEquals("Password must not match LastName.", exception.getMessage());
    }

    /** #8 **/
    @Test
    void testCreateCustomerWithWrongLogin(){
        createCustomerInput.setLogin("wrongemail@googlecom");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> customerManager.createCustomer(createCustomerInput));
        assertEquals("Login must be a valid email address.", exception.getMessage());
    }

    /** #9 **/
    @Test
    void testCreateCustomerWithPasswordContainsWhitespace(){
        createCustomerInput.setPass("Ab@i 1tSk");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> customerManager.createCustomer(createCustomerInput));
        assertEquals("Password should have lowercase, uppercase letters, numbers & special characters and not whitespace.", exception.getMessage());
    }

    /** #10 **/
    @Test
    void testCreateCustomerWithWrongFirstName(){
        createCustomerInput.setFirstName("SebasTian");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> customerManager.createCustomer(createCustomerInput));
        assertEquals("FirstName must start with uppercase letters and the rest characters are lowercase letters.", exception.getMessage());
    }

    /** #11 **/
    @Test
    void testCreateCustomerWithWrongLastName(){
        createCustomerInput.setLastName("ForsEn");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> customerManager.createCustomer(createCustomerInput));
        assertEquals("LastName must start with uppercase letters and the rest characters are lowercase letters.", exception.getMessage());
    }

    /** #12 **/
    //@Test
    //void testPasswordValidationCheck() {
    //    createCustomerInput.setPass("fD%1283jfd");
    //
    //    Exception exception = assertThrows(IllegalArgumentException.class, () -> customerManager.createCustomer(createCustomerInput));
    //    assertEquals(String.format("Password's length should be more or equal 6 symbols and less or equal 12 symbols. %s ", passSize), exception.getMessage());
    //}

    /** #13 **/
    //Негативный тест проверяющий работу валидации имени//
    //@Test
    //void testNameValidationСheck() {
    //    createCustomerInput.setFirstName("Rarara");
    //
    //    Exception exception = assertThrows(IllegalArgumentException.class, () -> customerManager.createCustomer(createCustomerInput));
    //    assertEquals(String.format("FirstName's length should be more or equal 2 symbols and less or equal 12 symbols. %s ", symbolsSize), exception.getMessage());
    //}

    /** #14 **/
    //Негативный тест проверяющий работу валидации фамилии
    //@Test
    //void testLastNameValidationСheck() {
    //    createCustomerInput.setLastName("Емельянов");
    //
    //    Exception exception = assertThrows(IllegalArgumentException.class, () -> customerManager.createCustomer(createCustomerInput));
    //    assertEquals(String.format("LastName's length should be more or equal 2 symbols and less or equal 12 symbols. %s ", symbolsSize), exception.getMessage());
    //}

    /** #15 **/
    //Негативный тест проверяющий работу валидации логина
    //@Test
    //void testLoginValidationCheck() {
    //    createCustomerInput.setLogin("jojo@mail.ru");
    //
    //    Exception exception = assertThrows(IllegalArgumentException.class, () -> customerManager.createCustomer(createCustomerInput));
    //    assertEquals(String.format("The login length must be greater than or equal to 6 characters and less than or equal to 30 characters. %s ", logSize), exception.getMessage());
    //}

    /** #16 **/
    //Позитивный тест создание пустого пароля
    @Test
    void CreateUserWithoutPassword() {
        createCustomerInput.setPass(null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> customerManager.createCustomer(createCustomerInput));
        assertEquals("Password is null", exception.getMessage());
    }

    /** #17 **/
    //Позитивный тест, проверка на пустоту
    @Test
    void CreateUserWithoutLogin() {
        createCustomerInput.setLogin(null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> customerManager.createCustomer(createCustomerInput));
        assertEquals("Login is null", exception.getMessage());
    }

    /** #18 **/
    //позитивный тест, проверка ограничение на длину пароля
    @Test
    void TestPasswordValidationCheck() {
        createCustomerInput.setPass("1");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> customerManager.createCustomer(createCustomerInput));
        assertEquals(String.format("Password's length should be more or equal 6 symbols and less or equal 12 symbols. %s ", passSize), exception.getMessage());
    }

    /** #19 **/
    //Позитивный тест проверяющий работу валидации имени
    @Test
    void TestNameValidationСheck() {
        createCustomerInput.setFirstName("V");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> customerManager.createCustomer(createCustomerInput));
        assertEquals(String.format("FirstName's length should be more or equal 2 symbols and less or equal 12 symbols. %s ", symbolsSize), exception.getMessage());
    }

    /** #20 **/
    //Позитивный тест проверяющий работу валидации фамилии
    @Test
    void TestLastNameValidationСheck() {
        createCustomerInput.setLastName("Е");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> customerManager.createCustomer(createCustomerInput));
        assertEquals(String.format("LastName's length should be more or equal 2 symbols and less or equal 12 symbols. %s ", symbolsSize), exception.getMessage());
    }

    /** #21 **/
    //Позитивный тест проверяющий работу валидации логина
    @Test
    void TestLoginValidationCheck() {
        createCustomerInput.setLogin("12345");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> customerManager.createCustomer(createCustomerInput));
        assertEquals(String.format("The login length must be greater than or equal to 6 characters and less than or equal to 30 characters. %s ", logSize), exception.getMessage());
    }

    /** #22 **/
    //Позитивный тест, проверка на пустое имя
    @Test
    void CreateUserWithoutFirstName() {
        createCustomerInput.setFirstName(null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> customerManager.createCustomer(createCustomerInput));
        assertEquals("FirstName is null", exception.getMessage());
    }

    /** #23 **/
    @Test
    void CreateUserWithoutLastName() {
        createCustomerInput.setLastName(null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> customerManager.createCustomer(createCustomerInput));
        assertEquals("LastName is null", exception.getMessage());
    }

}
