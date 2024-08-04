package com.marteldelfer.teststore.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDto {

    @NotEmpty(message = "Must not be empty")
    private String firstName;

    @NotEmpty(message = "Must not be empty")
    private String lastName;

    @NotEmpty
    @Email(message = "Must be a proper email adress")
    private String email;

    @NotEmpty(message = "Must not be empty")
    @Size(min = 6, message = "Password must have at least 6 characters")
    private String password;

    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
