package com.flexsolution.kmb.kmbvsemenuk.controller;

import com.flexsolution.kmb.kmbvsemenuk.dto.DepartmentDto;
import com.flexsolution.kmb.kmbvsemenuk.dto.EmployeeDto;
import com.flexsolution.kmb.kmbvsemenuk.mapper.DepartmentMapper;
import com.flexsolution.kmb.kmbvsemenuk.service.DepartmentService;
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
@RequestMapping("api/department")
public class DepartmentController {

    private DepartmentService departmentService;
    private DepartmentMapper departmentMapper;
    private EmployeeService employeeService;

    @GetMapping("all")
    public ResponseEntity<Page<DepartmentDto>> findAll(@PageableDefault(sort = {"id"},
            direction = Direction.ASC) Pageable pageable) {
        return new ResponseEntity<>(departmentService.findAll(pageable, departmentMapper::mapToDto), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<DepartmentDto> find(@PathVariable("id") Long id) {
        return new ResponseEntity<>(departmentService.findById(id, departmentMapper::mapToDto), HttpStatus.OK);
    }

    @GetMapping("{id}/employee")
    public ResponseEntity<Page<EmployeeDto>> findAllEmployeeById(
            @PathVariable("id") Long id,
            @PageableDefault(sort = {"id"}, direction = Direction.ASC) Pageable pageable) {

        return new ResponseEntity<>(
                employeeService.findAllEmployeeByDepartment(departmentService.findById(id), pageable), HttpStatus.OK);
    }

    @GetMapping("search")
    public ResponseEntity<Page<DepartmentDto>> search(
            @PageableDefault(sort = {"id"}, direction = Direction.ASC) Pageable pageable,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "value") Object value) {

        return new ResponseEntity<>(departmentService.findAllBySpecification(
                pageable,
                new SearchCriteria(name, value)),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DepartmentDto> create(@RequestBody @Valid DepartmentDto departmentDTO) {
        return new ResponseEntity<>(departmentService.save(departmentDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<DepartmentDto> update(@RequestBody @Valid DepartmentDto departmentDTO) {
        return new ResponseEntity<>(departmentService.save(departmentDTO), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        departmentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
