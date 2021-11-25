package com.example.library.sevice;

import com.example.library.dto.ThemeDto;

import java.util.List;

public interface ThemeService {

    List<ThemeDto> getAllDto();

    void delete(Long id);

    ThemeDto saveDto(ThemeDto themeDto);

    ThemeDto findById(Long id);
}
