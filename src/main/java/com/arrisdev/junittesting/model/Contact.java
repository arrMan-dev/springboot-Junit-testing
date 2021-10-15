package com.arrisdev.junittesting.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

    private String username;
    private String email;
    private String password;
    private String phoneNumber;

    public void validateUsername() {
        if (this.username.isBlank())
            throw new RuntimeException("username cannot be null or empty.");
    }

    public void validateEmail() {
        if (this.email.isBlank())
            throw new RuntimeException("Email cannot be null or empty.");
    }

    public void validatePassword() {
        if (this.password.isBlank()) {
            throw new RuntimeException("Password cannot be null or empty.");
        }
    }

    public void validatePhoneNumber() {
        if (this.password.isBlank()) {
            throw new RuntimeException("Phone Number cannot be null or empty.");
        }

        if (this.phoneNumber.length() != 10){
            throw new RuntimeException("Phone number should be 10 digits Long.");
        }

        if ( !this.phoneNumber.matches("\\d+")){
            throw new RuntimeException("Phone number Contain only digits.");
        }

        if (!this.phoneNumber.startsWith("0")){
            throw new RuntimeException("Phone Number Should not start with 0.");
        }
    }

}
