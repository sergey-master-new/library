package com.example.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {

    private long id;

    @NotBlank(message = "Last name is mandatory")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String lastName;

    private String firstName;

    private String patronymic;
}