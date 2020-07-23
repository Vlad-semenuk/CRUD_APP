package com.flexsolution.kmb.kmbvsemenuk.controller;

import com.flexsolution.kmb.kmbvsemenuk.constant.Constants;
import com.flexsolution.kmb.kmbvsemenuk.dto.EmployeeDto;
import com.flexsolution.kmb.kmbvsemenuk.exception.InvalidSearchCriteriaException;
import com.flexsolution.kmb.kmbvsemenuk.mapper.EmployeeMapper;
import com.flexsolution.kmb.kmbvsemenuk.service.EmployeeService;
import com.flexsolution.kmb.kmbvsemenuk.specification.SearchCriteria;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Setter(onMethod = @__({@Autowired}))
@RequestMapping("api/employee")
public class EmployeeController {

    private EmployeeService employeeService;
    private EmployeeMapper employeeMapper;

    @GetMapping("all")
    public ResponseEntity<Page<EmployeeDto>> findAll(
            @PageableDefault(sort = {"id"}, direction = Direction.ASC) Pageable pageable) {
        return new ResponseEntity<>(employeeService.findAll(pageable, employeeMapper::mapToDto), HttpStatus.OK);
    }

    @GetMapping("search")
    public ResponseEntity<Page<EmployeeDto>> search(
            @PageableDefault(sort = {"id"}, direction = Direction.ASC) Pageable pageable,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "value") Object value) {

        return new ResponseEntity<>(employeeService.findAllBySpecification(
                pageable,
                new SearchCriteria(name, value)),
                HttpStatus.OK);
    }

    @GetMapping("search/salary")
    public ResponseEntity<Page<EmployeeDto>> searchBySalary(
            @PageableDefault(sort = {"id"}, direction = Direction.ASC) Pageable pageable,
            @RequestParam(value = "filter") String filter,
            @RequestParam(value = "value") Object value) {

        if (!filter.matches(Constants.FILTER_REGEXP)) {
            throw new InvalidSearchCriteriaException(Constants.INVALID_SEARCH_REQUEST_EXCEPTION_MSG);
        }
        return new ResponseEntity<>(employeeService.findAllBySalarySpecification(
                pageable,
                new SearchCriteria("salary", filter, value)),
                HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> find(@PathVariable("id") Long id) {
        return new ResponseEntity<>(employeeService.findById(id, employeeMapper::mapToDto), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> save(@RequestBody @Valid EmployeeDto employeeDTO) {
        return new ResponseEntity<>(employeeService.save(employeeDTO, employeeMapper::mapToEntity), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<EmployeeDto> update(@RequestBody @Valid EmployeeDto employeeDTO) {
        return new ResponseEntity<>(employeeService.save(employeeDTO, employeeMapper::mapToEntity), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<EmployeeDto> updateDepartment(@RequestBody EmployeeDto employeeDTO) {
        return new ResponseEntity<>(employeeService.save(employeeDTO,
                employeeMapper::mapToEntityOnlyDepartment), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        employeeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
