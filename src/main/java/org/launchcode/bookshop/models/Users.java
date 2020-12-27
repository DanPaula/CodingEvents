package org.launchcode.bookshop.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class Users extends AbstractEntity{

    @NotBlank(message = "username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @NotBlank(message = "password is required")
    @Size(min = 3, message = "Password must be between 3 and 50 characters")
    private String password;

    @NotBlank(message = "name is required")
    @Size(min = 3, max = 50, message = "name must be between 3 and 50 characters")
    private String name;

    @ManyToOne
    private UserRole userRole;

    private boolean enabled;

    public Users(@NotBlank(message = "username is required") @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters") String username, @NotBlank(message = "password is required") @Size(min = 3, message = "Password must be between 3 and 50 characters") String password, @NotBlank(message = "name is required") @Size(min = 3, max = 50, message = "name must be between 3 and 50 characters") String name, UserRole userRole, boolean enabled) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.userRole = userRole;
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Users() {

    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
