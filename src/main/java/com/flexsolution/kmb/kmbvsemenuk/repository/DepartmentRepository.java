package com.flexsolution.kmb.kmbvsemenuk.repository;

import com.flexsolution.kmb.kmbvsemenuk.domain.Department;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends BaseRepository<Department> {

    boolean existsByAddressOrName(String address, String name);
}



