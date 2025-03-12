package com.Gryshin.cloud_api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class CloudApiApplication implements CommandLineRunner {

	@Value("${upload.dir}")
	private String uploadDir;

	public static void main(String[] args) {
		SpringApplication.run(CloudApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Створення директорії для завантажених файлів, якщо її немає
		File dir = new File(uploadDir);
		if (!dir.exists()) {
			boolean created = dir.mkdirs();  // Створення директорії, якщо її немає
			if (created) {
				System.out.println("Directory created: " + uploadDir);
			} else {
				System.out.println("Failed to create directory: " + uploadDir);
			}
		} else {
			System.out.println("Directory already exists: " + uploadDir);
		}
	}
}
