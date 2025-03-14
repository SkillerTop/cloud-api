package com.Gryshin.cloud_api.aspect;

import com.Gryshin.cloud_api.model.FileEntity;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class FileAccessAspect {

    @Before("execution(* com.Gryshin.cloud_api.repository.FileRepository.save(..)) && args(fileEntity)")
    public void setPrivateAccessLevel(FileEntity fileEntity) {
        fileEntity.setAccessLevel("private");
        System.out.println("📌 Файл " + fileEntity.getFileName() + " автоматично отримав рівень доступу: private");
    }
}
