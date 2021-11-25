package com.example.library.sevice.impl;

import com.example.library.dto.ThemeDto;
import com.example.library.entity.Theme;
import com.example.library.exception.NoDeletionException;
import com.example.library.exception.NotFoundException;
import com.example.library.mapper.ThemeMapper;
import com.example.library.repository.ThemeRepository;
import com.example.library.sevice.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ThemeServiceImpl implements ThemeService {

    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private ThemeMapper themeMapper;

    @Override
    public List<ThemeDto> getAllDto() {
        List<Theme> themeList = (List<Theme>) themeRepository.findAll();
        return themeMapper.mapToThemeDtoList(themeList);
    }

    @Override
    public void delete(Long id) {
        try {
            themeRepository.deleteById(id);
        } catch (DbActionExecutionException e) {
            throw new NoDeletionException("No deletion. " +
                    "This theme of publication is listed in a publication that is in the library.");
        }
    }

    @Override
    public ThemeDto saveDto(ThemeDto themeDto) {
        Theme theme = themeMapper.toTheme(themeDto);
        Theme themeSaved = themeRepository.save(theme);
        return themeMapper.toThemeDto(themeSaved);
    }

    @Override
    public ThemeDto findById(Long id) {
        try {
            Theme theme = themeRepository.findById(id).get();
            return themeMapper.toThemeDto(theme);
        } catch (NoSuchElementException e) {
            throw new NotFoundException("This theme was not found");
        }
    }
}
