package com.flexsolution.kmb.kmbvsemenuk.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "departments")
public class Department extends AbstractEntity implements Serializable {

    @Column(name = "name", unique = true)
    @NotBlank(message = "Please provide a name")
    @Size(min = 4, max = 32, message = "Name should contain more than 4 symbols and less 32 symbols")
    private String name;

    @Column(name = "address", unique = true)
    @NotBlank(message = "Please provide an address")
    private String address;

    @Column(name = "manager")
    @NotBlank(message = "Please provide a name of manager")
    private String manager;

}
