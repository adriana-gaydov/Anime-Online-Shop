package bg.softuni.onlineshop.model.view;

import java.util.List;

public class UserViewModel {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;

    private String email;

    private boolean isActive;

    private List<OrderViewModel> orders;

    public String getFirstName() {
        return firstName;
    }

    public UserViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getMiddleName() {
        return middleName;
    }

    public UserViewModel setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<OrderViewModel> getOrders() {
        return orders;
    }

    public UserViewModel setOrders(List<OrderViewModel> orders) {
        this.orders = orders;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public UserViewModel setActive(boolean active) {
        isActive = active;
        return this;
    }

    public String getFullName() {
        return firstName + (middleName == null ? "" : " "+middleName) + " " + lastName;
    }
}
