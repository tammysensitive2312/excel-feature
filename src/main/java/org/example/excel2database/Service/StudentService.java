package org.example.excel2database.Service;

import org.springframework.web.multipart.MultipartFile;

public interface StudentService {
    void saveStudent(MultipartFile file);
}
