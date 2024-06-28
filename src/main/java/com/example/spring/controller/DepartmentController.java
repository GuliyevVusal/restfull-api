package com.example.spring.controller;

import com.example.spring.dto.DepartmentDTO;
import com.example.spring.dto.DepartmentMapper;
import com.example.spring.dto.common.CommonDTO;
import com.example.spring.entity.Department;
import com.example.spring.repo.DepartmentRepo;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentRepo dataRepo;

    public DepartmentController(DepartmentRepo dataRepo) {
        this.dataRepo = dataRepo;
    }

    @GetMapping("/client")
    public List<DepartmentDTO> getAllDepartmentsForClient() {
        List<DepartmentDTO> departmentDTOS = dataRepo.findAll()
                .stream().map(DepartmentMapper.MAPPER::convertToDepartmentDto)
                .collect(Collectors.toList());
        return departmentDTOS;
    }

    @GetMapping("/v2")
    public List<Department> getAll() {
        return dataRepo.findAll();
    }

    @GetMapping("/departments")
    public CommonDTO getAllDepartments() {
        List<DepartmentDTO> departmentDTOS = dataRepo.findAll()
                .stream().map(DepartmentMapper.MAPPER::convertToDepartmentDto)
                .collect(Collectors.toList());

        return new CommonDTO()
                .setObj(departmentDTOS)
                .setDateTime(LocalDateTime.now())
                .setMessage("fetched successfully");
    }

    @PostMapping()
    public CommonDTO insert(@RequestBody DepartmentDTO departmentDTO) {
        Department department = DepartmentMapper.MAPPER.convertToDepartmentEntity(departmentDTO);
        department.getEmployees().forEach(employee -> employee.setDepartment(department));
        Department savedDepartment = dataRepo.save(department);

        return new CommonDTO()
                .setObj(savedDepartment)
                .setDateTime(LocalDateTime.now())
                .setMessage("Department inserted successfully");
    }



    @PutMapping("/{id}")
    public CommonDTO update(@PathVariable Integer id, @RequestBody DepartmentDTO updateDepartmentDTO) {
        Department department = DepartmentMapper.MAPPER.convertToDepartmentEntity(updateDepartmentDTO);
        department.setId(id);
        department.getEmployees().forEach(employee -> employee.setDepartment(department));
        Department savedDepartment = dataRepo.save(department);
        return new CommonDTO()
                .setObj(savedDepartment)
                .setDateTime(LocalDateTime.now())
                .setMessage("Department updated successfully");
    }

    @DeleteMapping("/{id}")
    public CommonDTO delete(@PathVariable Integer id) {
        dataRepo.deleteById(id);
        return new CommonDTO()
                .setDateTime(LocalDateTime.now())
                .setMessage("Department deleted successfully");
    }
}


