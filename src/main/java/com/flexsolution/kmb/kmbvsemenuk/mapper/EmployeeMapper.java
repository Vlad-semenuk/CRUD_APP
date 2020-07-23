package com.flexsolution.kmb.kmbvsemenuk.mapper;

import com.flexsolution.kmb.kmbvsemenuk.domain.Employee;
import com.flexsolution.kmb.kmbvsemenuk.dto.EmployeeDto;
import com.flexsolution.kmb.kmbvsemenuk.service.DepartmentService;
import com.flexsolution.kmb.kmbvsemenuk.service.EmployeeService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Setter(onMethod = @__({@Autowired}))
@Component
public class EmployeeMapper {

    private DepartmentService departmentService;
    private EmployeeService employeeService;
    private DepartmentMapper departmentMapper;

    public Employee mapToEntity(EmployeeDto employeeDto) {
        Employee employee = employeeDto.getId() == null
                ? new Employee() : employeeService.findById(employeeDto.getId());

        employee.setFirstName(employeeDto.getFirstName());
        employee.setSecondName(employeeDto.getSecondName());
        employee.setEmail(employeeDto.getEmail());
        employee.setSalary(employeeDto.getSalary());
        employee.setDepartment(employeeDto.getDepartment() == null
                ? null : departmentService.findById(employeeDto.getDepartment().getId()));
        return employee;
    }

    public EmployeeDto mapToDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setSecondName(employee.getSecondName());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setSalary(employee.getSalary());
        employeeDto.setDepartment(employee.getDepartment() == null
                ? null :departmentMapper.mapToDto(employee.getDepartment()));
        return employeeDto;
    }

    public Employee mapToEntityOnlyDepartment(EmployeeDto employeeDto){
        Employee employee = employeeDto.getId() == null
                ? new Employee() : employeeService.findById(employeeDto.getId());
        employee.setDepartment(employeeDto.getDepartment() == null
                ? null : departmentService.findById(employeeDto.getDepartment().getId()));
        return employee;
    }
}
