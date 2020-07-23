package com.flexsolution.kmb.kmbvsemenuk.service;

import com.flexsolution.kmb.kmbvsemenuk.constant.Constants;
import com.flexsolution.kmb.kmbvsemenuk.domain.Department;
import com.flexsolution.kmb.kmbvsemenuk.domain.Employee;
import com.flexsolution.kmb.kmbvsemenuk.dto.EmployeeDto;
import com.flexsolution.kmb.kmbvsemenuk.exception.EntityAlreadyExistException;
import com.flexsolution.kmb.kmbvsemenuk.mapper.EmployeeMapper;
import com.flexsolution.kmb.kmbvsemenuk.repository.EmployeeRepository;
import com.flexsolution.kmb.kmbvsemenuk.specification.EmployeeSalarySpecification;
import com.flexsolution.kmb.kmbvsemenuk.specification.FieldSearchSpecification;
import com.flexsolution.kmb.kmbvsemenuk.specification.SearchCriteria;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

import static java.lang.String.format;

@Slf4j
@Service
@Setter(onMethod = @__({@Autowired}))
public class EmployeeService extends AbstractService<Employee, EmployeeRepository> {

    private EmployeeMapper mapper;

    public Page<EmployeeDto> findAllEmployeeByDepartment(Department department, Pageable pageable) {
        return repository.findAllEmployeeByDepartment(department, pageable)
                .map(employee -> mapper.mapToDto(employee));
    }

    public EmployeeDto save(EmployeeDto employeeDTO, Function<EmployeeDto, Employee> map) {
        if (repository.existsByEmail(employeeDTO.getEmail())) {
            throw new EntityAlreadyExistException(
                    format(Constants.EMPLOYEE_BY_EMAIL_ALREADY_EXISTS_EXCEPTION_MSG, employeeDTO.getEmail()));
        }

        Employee employee = save(map.apply(employeeDTO));
        return mapper.mapToDto(employee);
    }

    public Integer countEmployeesByDepartment(Long departmentId) {
        return repository.countByDepartmentId(departmentId);
    }

    public Page<EmployeeDto> findAllBySpecification(Pageable pageable, SearchCriteria searchCriteria) {
        log.debug("Search criteria {}", searchCriteria);
        return repository.findAll(new FieldSearchSpecification<>(searchCriteria), pageable)
                .map(employee -> mapper.mapToDto(employee));
    }

    public Page<EmployeeDto> findAllBySalarySpecification(Pageable pageable, SearchCriteria searchCriteria) {
        log.debug("Search criteria {}", searchCriteria);
        return repository.findAll(new EmployeeSalarySpecification(searchCriteria), pageable)
                .map(employee -> mapper.mapToDto(employee));
    }

}
