package org.example.excel2database.DTO;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link org.example.excel2database.Entity.Student}
 */
@Value
public class StudentDto implements Serializable {
    String name;
    String email;
    String phone;
}