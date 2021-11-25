package com.example.library.controller;

import com.example.library.dto.AuthorDto;
import com.example.library.dto.PublicationDto;
import com.example.library.sevice.AuthorService;
import com.example.library.sevice.PublicationService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/author")
@Log
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/get-all")
    public List<AuthorDto> getAllDto(){
        log.info("Handling get all authors request");
        return authorService.getAllDto();
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Handling delete author request: " + id);
        authorService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/save")
    public AuthorDto saveDto(@Valid @RequestBody AuthorDto authorDto) {
        log.info("Handling save publication: " + authorDto);
        return authorService.saveDto(authorDto);
    }

    @GetMapping("/{id}")
    public AuthorDto findById(@PathVariable Long id) {
        log.info("Handling get author by id request: " + id);
        AuthorDto authorDto = authorService.findById(id);
       return authorDto;
    }
}
