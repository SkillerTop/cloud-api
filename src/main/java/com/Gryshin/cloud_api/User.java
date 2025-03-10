package com.Gryshin.cloud_api;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class User {

    @NotBlank(message = "Ім'я користувача не може бути порожнім")
    private String username;

    @Size(min = 6, message = "Пароль має містити принаймні 6 символів")
    private String password;

    @Email(message = "Некоректний email")
    private String email;

    // Геттери та сеттери
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
