package com.Gryshin.cloud_api.controller;

import com.Gryshin.cloud_api.model.FileEntity;
import com.Gryshin.cloud_api.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class FileController {

    @Autowired
    private FileRepository fileRepository;

    // Головна сторінка з переліком файлів
    @GetMapping("/files")
    public String listFiles(Model model) {
        List<FileEntity> files = fileRepository.findAll(); // Отримуємо файли з бази даних
        model.addAttribute("files", files);
        return "files"; // Назва вашого HTML-шаблону
    }

    // Завантаження файлу
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("error", "Файл не вибрано.");
            return "redirect:/files?error=FileNotSelected";
        }

        try {
            // Зберігаємо файл в директорії uploads
            Path path = Paths.get("uploads", file.getOriginalFilename());
            Files.copy(file.getInputStream(), path);

            // Створюємо об'єкт FileEntity та зберігаємо його в базі даних
            FileEntity fileEntity = new FileEntity(file.getOriginalFilename(), path.toString());
            fileRepository.save(fileEntity);

            model.addAttribute("success", "Файл успішно завантажено.");
        } catch (IOException e) {
            model.addAttribute("error", "Сталася помилка при завантаженні файлу: " + e.getMessage());
        }

        return "redirect:/files?success=true";
    }

    // Видалення файлу
    @Transactional  // Додаємо транзакційність до цього методу
    @PostMapping("/delete")
    public String deleteFile(@RequestParam String filename, Model model) {
        File file = new File("uploads", filename);

        System.out.println("Починаємо видалення файлу: " + filename);

        if (file.exists() && file.isFile()) {
            boolean fileDeleted = file.delete();

            if (fileDeleted) {
                System.out.println("Файл видалено з файлової системи: " + filename);
                fileRepository.deleteByFileName(filename); // Видаляємо файл з бази даних
                model.addAttribute("success", "Файл успішно видалено.");
            } else {
                System.out.println("Не вдалося видалити файл: " + filename);
                model.addAttribute("error", "Не вдалося видалити фізичний файл.");
            }
        } else {
            System.out.println("Файл не знайдено: " + filename);
            model.addAttribute("error", "Файл не знайдено.");
        }

        return "redirect:/files?success=true";
    }

    // Завантаження файлу для перегляду
    @GetMapping("/download/{filename}")
    public String downloadFile(@PathVariable String filename, Model model) {
        File file = new File("uploads", filename);
        if (!file.exists()) {
            model.addAttribute("error", "Файл не знайдено.");
            return "redirect:/files?error=FileNotFound";
        }

        model.addAttribute("file", file);
        return "download"; // Сторінка для скачування файлів
    }
}
