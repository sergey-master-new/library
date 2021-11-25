package com.example.library.sevice;

import com.example.library.dto.PublisherDto;
import com.example.library.dto.TypeDto;

import java.util.List;

public interface TypeService {

    List<TypeDto> getAllDto();

    void delete(Long id);

    TypeDto saveDto(TypeDto typeDto);

    TypeDto findById(Long id);
}
