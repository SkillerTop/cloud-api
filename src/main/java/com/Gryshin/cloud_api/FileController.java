package com.Gryshin.cloud_api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.List;

@RestController
@RequestMapping("/api/files")
public class FileController {

    // Завантаження файлу до хмари
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") String file) {
        // Заглушка для завантаження файлу
        return "Файл завантажено: " + file;
    }

    // Перегляд списку файлів користувача
    @GetMapping("/list")
    public List<String> listFiles() {
        // Заглушка для повернення списку файлів
        return List.of("file1.txt", "file2.jpg", "file3.pdf");
    }

    // Видалення файлу
    @DeleteMapping("/delete")
    public String deleteFile(@RequestParam("file") String file) {
        // Заглушка для видалення файлу
        return "Файл видалено: " + file;
    }
}
