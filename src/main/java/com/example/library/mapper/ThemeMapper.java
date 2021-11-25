package com.example.library.mapper;

import com.example.library.dto.ThemeDto;
import com.example.library.entity.Theme;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ThemeMapper {

    @Named(value = "themeDto")
    ThemeDto toThemeDto (Theme theme);

    @Named(value = "theme")
    Theme toTheme (ThemeDto themeDto);

    @IterableMapping(qualifiedByName = "theme")
    List<Theme> mapToThemeList (List<ThemeDto> themeDtoList);

    @IterableMapping(qualifiedByName = "themeDto")
    List<ThemeDto> mapToThemeDtoList (List<Theme> themeList);
}
