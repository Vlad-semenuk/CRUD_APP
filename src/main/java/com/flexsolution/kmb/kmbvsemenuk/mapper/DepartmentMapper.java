package com.flexsolution.kmb.kmbvsemenuk.mapper;

import com.flexsolution.kmb.kmbvsemenuk.domain.Department;
import com.flexsolution.kmb.kmbvsemenuk.dto.DepartmentDto;
import com.flexsolution.kmb.kmbvsemenuk.service.DepartmentService;
import com.flexsolution.kmb.kmbvsemenuk.service.EmployeeService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Setter(onMethod = @__({@Autowired}))
@Component
public class DepartmentMapper {

    private EmployeeService employeeService;
    private DepartmentService departmentService;

    public DepartmentDto mapToDto(Department department) {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(department.getId());
        departmentDto.setName(department.getName());
        departmentDto.setManager(department.getManager());
        departmentDto.setAddress(department.getAddress());
        departmentDto.setCountEmployee(employeeService.countEmployeesByDepartment(department.getId()));
        return departmentDto;
    }

    public Department mapToEntity(DepartmentDto departmentDto) {
        Department department = departmentDto.getId() == null
                ? new Department() : departmentService.findById(departmentDto.getId());

        department.setName(departmentDto.getName());
        department.setManager(departmentDto.getManager());
        department.setAddress(departmentDto.getAddress());
        return department;
    }

}
