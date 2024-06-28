package com.example.spring.dto;

import com.example.spring.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper MAPPER = Mappers.getMapper(EmployeeMapper.class);
    @Named("withoutDepartment")
    @Mapping(target = "department", ignore = true)
    EmployeeDTO convertToEmployeeDto(Employee employee);

    Employee convertToEmployeeEntity(EmployeeDTO employeeDTO);

}
