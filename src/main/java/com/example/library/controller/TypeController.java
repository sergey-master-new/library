package com.example.library.controller;

import com.example.library.dto.TypeDto;
import com.example.library.sevice.TypeService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/type")
@Log
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/get-all")
    public List<TypeDto> getAllDto(){
        log.info("Handling get all types request");
        return typeService.getAllDto();
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Handling delete type request: " + id);
        typeService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/save")
    public TypeDto saveDto(@Valid @RequestBody TypeDto typeDto) {
        log.info("Handling save type: " + typeDto);
        return typeService.saveDto(typeDto);
    }

    @GetMapping("/{id}")
    public TypeDto findById(@PathVariable Long id) {
        log.info("Handling get theme by id request: " + id);
        TypeDto typeDto = typeService.findById(id);
       return typeDto;
    }
}
