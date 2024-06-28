package com.example.spring.dto;

import com.example.spring.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = EmployeeMapper.class)
public interface DepartmentMapper {

    DepartmentMapper MAPPER = Mappers.getMapper(DepartmentMapper.class);
    @Mapping(target = "employees", qualifiedByName = "withoutDepartment")
    DepartmentDTO convertToDepartmentDto(Department department);

    Department convertToDepartmentEntity(DepartmentDTO departmentDTO);
}
