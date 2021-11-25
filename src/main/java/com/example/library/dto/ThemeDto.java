package com.example.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThemeDto {

    private long id;

    @NotBlank(message = "Theme is mandatory")
    @Size(min = 2, max = 100, message = "Theme should be between 2 and 100 characters")
    private String name;

}