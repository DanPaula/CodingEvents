package org.launchcode.bookshop.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserRole extends AbstractEntity{


    @NotBlank(message = "role is required")
    @Size(min = 3, max = 50, message = "role must be between 3 and 50 characters")
    private String role;

    @OneToMany(mappedBy = "userRole")
    private final List<Users> users = new ArrayList<>();

    public UserRole(@NotBlank(message = "role is required") @Size(min = 3, max = 50, message = "role must be between 3 and 50 characters") String role) {
        this.role = role;
    }

    public UserRole(){}

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Users> getUsers() {
        return users;
    }
}
