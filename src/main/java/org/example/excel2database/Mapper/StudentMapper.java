package org.example.excel2database.Mapper;

import org.example.excel2database.DTO.StudentDto;
import org.example.excel2database.Entity.Student;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentMapper {
    Student toEntity(StudentDto studentDto);

    StudentDto toDto(Student student);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Student partialUpdate(StudentDto studentDto, @MappingTarget Student student);
}