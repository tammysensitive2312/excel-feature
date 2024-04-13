package org.example.excel2database.Service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.excel2database.Entity.Student;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ExcelService {
    public static boolean isValidExcelFile(MultipartFile file) {
        // Chuỗi "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" là kiểu MIME chính xác cho tệp Excel .xlsx
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public static List<Student> getStudentDataFromExcel(InputStream is) {
        List<Student> students = new ArrayList<>();

        try (XSSFWorkbook workbook = new XSSFWorkbook(is)) {
            // tên của sheet đang xét trong file excel
            XSSFSheet sheet = workbook.getSheet("students");
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet 'students' does not exist");
            }

            for (Row row : sheet) {
                // bỏ qua hàng đầu tiên
                if (row.getRowNum() == 0) {
                    continue;
                }
                Student student = parseRowToStudent(row);
                students.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }

    private static Student parseRowToStudent(Row row) {
        Student student = new Student();
        for (Cell cell : row) {
            /*
             duyệt theo từng hàng đối ô là số thì dùng NumericCell(phải ép veefeveef đúng định dạng), ô chữ là StringCell
             */
            switch (cell.getColumnIndex()) {
                case 0:
                    student.setId((long) cell.getNumericCellValue());
                    break;
                case 1:
                    student.setName(cell.getStringCellValue());
                    break;
                case 2:
                    student.setEmail(cell.getStringCellValue());
                    break;
                case 3:
                    student.setPhone(String.valueOf(cell.getNumericCellValue()));
                    break;
                default:
                    break;
            }
        }
        return student;
    }
}
