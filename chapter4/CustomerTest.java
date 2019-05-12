import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CustomerTest {

    /**
     *  The system should be able to store information on a new customer using valid data.
     *  Scenario:
     *  1. Given I create a new customer named Mickey Mouse
     *  2. When I initialize the customer object with valid details.
     *  3. Then Mickey's information should be stored in the system.
     */
    @Test
    public void creatingCustomerWithValidData_StoresSpecifiedData() {

        // Given and When
        Customer customer = new Customer("Mickey Mouse", "Disneyland", "Mickey@Disneyland.com");

        // Then
        assertNotNull(customer);
        assertEquals(customer.getName(), "Mickey Mouse");
        assertEquals(customer.getAddress(), "Disneyland");
        assertEquals(customer.getEmail(), "Mickey@Disneyland.com");
    }

    /**
     *  The system should be able to update customer information using valid data.
     *  Scenario:
     *  1. Given I create a new customer named Mickey Mouse.
     *  2. When I update Mickey's customer information with Minnie's information.
     *  3. Then Minnie's information should be stored in the system.
     */
    @Test
    public void updatingCustomerDataWithDifferentValidValues_StoresNewValues() {

        // Given
        Customer customer = new Customer("Mickey", "Disneyland", "Mickey@Disneyland.com");

        // When
        customer.updateName("Minnie Mouse");
        customer.updateAddress("Disney World");
        customer.updateEmail("Minnie@Disneyworld.com");

        // Then
        assertEquals(customer.getName(), "Minnie Mouse");
        assertEquals(customer.getAddress(), "Disney World");
        assertEquals(customer.getEmail(), "Minnie@Disneyworld.com");

    }
}