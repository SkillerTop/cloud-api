package com.Gryshin.cloud_api.repository;

import com.Gryshin.cloud_api.model.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
    void deleteByFileName(String fileName);
}
