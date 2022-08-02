package bg.softuni.onlineshop.model.view;

import bg.softuni.onlineshop.model.dto.RoleDTO;
import bg.softuni.onlineshop.model.entity.RoleEntity;

import java.util.List;
import java.util.Set;

public class UserViewModel {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;

    private String email;

    private boolean isActive;

    private List<OrderViewModel> orders;

    private List<RoleDTO> roles;

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

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public UserViewModel setRoles(List<RoleDTO> roles) {
        this.roles = roles;
        return this;
    }
}
