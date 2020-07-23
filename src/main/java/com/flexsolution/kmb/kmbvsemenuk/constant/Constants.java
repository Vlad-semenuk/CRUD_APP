package com.flexsolution.kmb.kmbvsemenuk.constant;

public final class Constants {

    private Constants() {
    }

    public static final String FILTER_REGEXP = "([<>=])";

    public static final String ENTITY_NOT_FOUND_EXCEPTION_MSG = "Entity (id = %s) not found";
    public static final String INVALID_SEARCH_REQUEST_EXCEPTION_MSG = "Incorrect request " +
            "filters should consist of these symbols '=><'";
    public static final String EMPLOYEE_BY_EMAIL_ALREADY_EXISTS_EXCEPTION_MSG =
            "Employee by email (%s) is already exists";
    public static final String DEPARTMENT_IS_ALREADY_EXISTS_EXCEPTION_MSG =
            "Department by that address (%s) and by that name (%s) already exists";
}
