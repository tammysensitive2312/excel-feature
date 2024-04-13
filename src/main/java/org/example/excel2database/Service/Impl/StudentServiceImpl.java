package org.example.excel2database.Service.Impl;

import lombok.AllArgsConstructor;
import org.example.excel2database.Entity.Student;
import org.example.excel2database.Repository.StudentRepository;
import org.example.excel2database.Service.ExcelService;
import org.example.excel2database.Service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private StudentRepository repository;

    /* ở đây trong file excel bỏ cột id vì đã đặt là tự động tăng trong entity
     */

    @Override
    public void saveStudent(MultipartFile file) {
        if (ExcelService.isValidExcelFile(file)) {
            try {
                List<Student> students = ExcelService.getStudentDataFromExcel(file.getInputStream());
                this.repository.saveAll(students);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /*
    nếu làm chuẩn chỉ phải convert từ entity sang dto để giấu id
    **/
    public List<Student> getStudents() {
        return repository.findAll();
    }
}
