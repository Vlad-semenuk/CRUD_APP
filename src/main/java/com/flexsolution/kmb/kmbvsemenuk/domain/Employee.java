package com.flexsolution.kmb.kmbvsemenuk.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
@DynamicUpdate
public class Employee extends AbstractEntity {

    @Column(name = "first_name")
    @NotBlank(message = "Please provide a first name")
    private String firstName;

    @Column(name = "second_name")
    @NotBlank(message = "Please provide a second name")
    private String secondName;

    @Column(name = "email", unique = true)
    @Email(message = "Email is not correct")
    @NotBlank(message = "Please provide a email")
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "salary")
    @NotNull(message = "Please provide a salary")
    @Min(value = 0, message = "Salary should be more than 0")
    private BigDecimal salary;

}
