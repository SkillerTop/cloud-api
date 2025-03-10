package com.Gryshin.cloud_api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class FileController {

    @GetMapping("/files")
    public String listFiles(Model model) {
        model.addAttribute("files", List.of("file1.txt", "file2.jpg", "file3.pdf"));
        return "files";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") String file, Model model) {
        model.addAttribute("message", "Файл завантажено: " + file);
        return "files";
    }
}
