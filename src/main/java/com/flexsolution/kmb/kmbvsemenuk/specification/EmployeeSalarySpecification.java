package com.flexsolution.kmb.kmbvsemenuk.specification;

import com.flexsolution.kmb.kmbvsemenuk.domain.Employee;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class EmployeeSalarySpecification implements Specification<Employee> {

    private static final String OPERATION_MORE_THAN = ">";
    private static final String OPERATION_LESS_THAN = "<";
    private static final String OPERATION_EQUALS = "=";

    private final SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        if (criteria.getOperation().equalsIgnoreCase(OPERATION_MORE_THAN)) {
            return builder.greaterThanOrEqualTo(
                    root.get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase(OPERATION_LESS_THAN)) {
            return builder.lessThanOrEqualTo(
                    root.get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase(OPERATION_EQUALS)) {
            return builder.equal(
                    root.get(criteria.getKey()), criteria.getValue().toString());
        }
        return null;
    }

}
