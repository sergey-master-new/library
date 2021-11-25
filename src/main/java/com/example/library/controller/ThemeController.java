package com.example.library.controller;

import com.example.library.dto.PublisherDto;
import com.example.library.dto.ThemeDto;
import com.example.library.sevice.PublisherService;
import com.example.library.sevice.ThemeService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/theme")
@Log
public class ThemeController {

    @Autowired
    private ThemeService themeService;

    @GetMapping("/get-all")
    public List<ThemeDto> getAllDto(){
        log.info("Handling get all themes request");
        return themeService.getAllDto();
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Handling delete theme request: " + id);
        themeService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/save")
    public ThemeDto saveDto(@Valid @RequestBody ThemeDto themeDto) {
        log.info("Handling save theme: " + themeDto);
        return themeService.saveDto(themeDto);
    }

    @GetMapping("/{id}")
    public ThemeDto findById(@PathVariable Long id) {
        log.info("Handling get theme by id request: " + id);
        ThemeDto themeDto = themeService.findById(id);
       return themeDto;
    }
}
