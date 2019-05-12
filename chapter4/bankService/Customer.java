package bankService;

/**
 * Facilitates storing, retrieving, and updating customer information.
 * @author Tariq King
 */
public class Customer {

    private String name;
    private String address;
    private String email;

    public Customer(String name, String address, String email) {
        this.name = name;
        this.address = address;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void updateAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void updateEmail(String email) {
        this.email = email;
    }

}