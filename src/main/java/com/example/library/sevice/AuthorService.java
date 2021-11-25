package com.example.library.sevice;

import com.example.library.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    List<AuthorDto> getAllDto();

    void delete(Long id);

    AuthorDto saveDto(AuthorDto authorDto);

    AuthorDto findById(Long id);
}
