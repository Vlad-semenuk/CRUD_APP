package com.flexsolution.kmb.kmbvsemenuk.service;

import com.flexsolution.kmb.kmbvsemenuk.constant.Constants;
import com.flexsolution.kmb.kmbvsemenuk.domain.Department;
import com.flexsolution.kmb.kmbvsemenuk.dto.DepartmentDto;
import com.flexsolution.kmb.kmbvsemenuk.exception.EntityAlreadyExistException;
import com.flexsolution.kmb.kmbvsemenuk.mapper.DepartmentMapper;
import com.flexsolution.kmb.kmbvsemenuk.repository.DepartmentRepository;
import com.flexsolution.kmb.kmbvsemenuk.specification.FieldSearchSpecification;
import com.flexsolution.kmb.kmbvsemenuk.specification.SearchCriteria;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Slf4j
@Service
@Setter(onMethod = @__({@Autowired}))
public class DepartmentService extends AbstractService<Department, DepartmentRepository> {

    private DepartmentMapper mapper;

    public DepartmentDto save(DepartmentDto departmentDTO) {
        if (repository.existsByAddressOrName(departmentDTO.getAddress(), departmentDTO.getName())) {
            throw new EntityAlreadyExistException(
                    format(Constants.DEPARTMENT_IS_ALREADY_EXISTS_EXCEPTION_MSG,
                            departmentDTO.getAddress(), departmentDTO.getName()));
        }
        Department department = save(mapper.mapToEntity(departmentDTO));
        return mapper.mapToDto(department);
    }

    public Page<DepartmentDto> findAllBySpecification(Pageable pageable, SearchCriteria searchCriteria) {
        log.debug("Search criteria {}", searchCriteria);
        return repository.findAll(
                new FieldSearchSpecification<>(searchCriteria),
                pageable).map(department -> mapper.mapToDto(department));
    }
}
