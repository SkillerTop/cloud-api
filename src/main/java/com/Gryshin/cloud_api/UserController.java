package com.Gryshin.cloud_api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/api/users")
public class UserController {

    // Створення нового облікового запису
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        // Заглушка для реєстрації користувача
        return "Користувач зареєстрований: " + user.getUsername();
    }

    // Авторизація користувача
    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {
        // Заглушка для авторизації користувача
        return "Користувач авторизований: " + user.getUsername();
    }

    // Видалення облікового запису користувача
    @DeleteMapping("/delete")
    public String deleteUser(@RequestBody User user) {
        // Заглушка для видалення користувача
        return "Користувач видалений: " + user.getUsername();
    }
}
