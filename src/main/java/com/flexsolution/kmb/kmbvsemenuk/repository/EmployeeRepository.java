package com.flexsolution.kmb.kmbvsemenuk.repository;

import com.flexsolution.kmb.kmbvsemenuk.domain.Department;
import com.flexsolution.kmb.kmbvsemenuk.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends BaseRepository<Employee> {

    Page<Employee> findAll(Pageable pageable);

    Page<Employee> findAllEmployeeByDepartment(Department department, Pageable pageable);

    Integer countByDepartmentId(Long departmentId);

    boolean existsByEmail(String email);

}
