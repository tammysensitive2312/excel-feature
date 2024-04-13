package org.example.excel2database.Controller;

import lombok.AllArgsConstructor;
import org.example.excel2database.Entity.Student;
import org.example.excel2database.Service.Impl.StudentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/students")
public class StudentController {
    private StudentServiceImpl studentService;
    // http://localhost:8081/api/v1/students/upload
    @PostMapping("/upload")
    public ResponseEntity<?> uploadStudentData(@RequestParam("file")MultipartFile file) {
        this.studentService.saveStudent(file);
        return ResponseEntity
                .ok(Map.of("Message" , " Customers data uploaded and saved to database successfully"));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getStudentData() {
        return new ResponseEntity<>(studentService.getStudents(), HttpStatus.FOUND);
    }
}
