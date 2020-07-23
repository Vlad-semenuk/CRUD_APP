package com.flexsolution.kmb.kmbvsemenuk.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;


@Data
@NoArgsConstructor
public class EmployeeDto implements Serializable {

    private Long id;

    @NotBlank(message = "Please provide a first name")
    private String firstName;

    @NotBlank(message = "Please provide a second name")
    private String secondName;

    @NotBlank(message = "Please provide a email")
    @Email(message = "Email is not correct")
    private String email;

    private DepartmentDto department;

    @NotNull(message = "Please provide a salary")
    @Min(value = 0, message = "Salary should be more than 0")
    private BigDecimal salary;

}
