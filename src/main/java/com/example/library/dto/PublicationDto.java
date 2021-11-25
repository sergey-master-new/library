package com.example.library.dto;

import com.example.library.entity.Author;
import com.example.library.entity.Publisher;
import com.example.library.entity.Theme;
import com.example.library.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicationDto {

    private long id;

    @NotNull
    private Type type;

    @NotNull
    private Theme theme;

    @NotBlank(message = "Name of publication is mandatory")
    @Size(min = 2, max = 40, message = "Name of publication should be between 2 and 40 characters")
    private String name;

    private List<Author> authors;

    private LocalDate date;

    private Publisher publisher;

    private int pageCount;
}
