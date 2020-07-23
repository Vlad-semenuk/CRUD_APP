package com.flexsolution.kmb.kmbvsemenuk.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Data
@NoArgsConstructor
public class DepartmentDto implements Serializable {

    private Long id;

    @NotBlank(message = "Please provide a name")
    @Size(min = 4, max = 32, message = "Name should contain more than 4 symbols and less 32 symbols")
    private String name;

    @NotBlank(message = "Please provide an address")
    private String address;

    @NotBlank(message = "Please provide a name of manager")
    private String manager;

    private int countEmployee;

}
