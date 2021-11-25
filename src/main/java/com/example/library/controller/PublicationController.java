package com.example.library.controller;

import com.example.library.dto.PublicationDto;
import com.example.library.sevice.PublicationService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/publication")
@Log
public class PublicationController {

    @Autowired
    private PublicationService publicationService;

    @GetMapping("/get-all")
    public List<PublicationDto> getAllPublications() {
        log.info("Handling get all publications request");
        return publicationService.getAllPublicationDto();
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Void> deletePublication(@PathVariable Long id) {
        log.info("Handling delete publication request: " + id);
        publicationService.deletePublication(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/save")
    public PublicationDto savePublicationDto(@Valid @RequestBody PublicationDto publicationDto) {
        log.info("Handling save publication: " + publicationDto);
        return publicationService.savePublicationDto(publicationDto);
    }

    @GetMapping("/{id}")
    public PublicationDto getPublicationById(@PathVariable Long id) {
        log.info("Handling get publication by id request: " + id);
        PublicationDto publicationDto = publicationService.getPublicationById(id);
        return publicationDto;
    }

    @GetMapping("/by-type/{id}")
    public List <PublicationDto> getPublicationsByType(@PathVariable Long id) {
        log.info("Handling get publications by id of type request: " + id);
        List <PublicationDto> publicationDtos = publicationService.getPublicationsByType(id);
        return publicationDtos;
    }

    @GetMapping("/by-theme/{id}")
    public List <PublicationDto> getPublicationsByTheme(@PathVariable Long id) {
        log.info("Handling get publications by id of theme request: " + id);
        List <PublicationDto> publicationDtos = publicationService.getPublicationsByTheme(id);
        return publicationDtos;
    }

    @GetMapping("/by-publisher/{id}")
    public List <PublicationDto> getPublicationsByPublisher(@PathVariable Long id) {
        log.info("Handling get publications by id of publisher request: " + id);
        List <PublicationDto> publicationDtos = publicationService.getPublicationsByPublisher(id);
        return publicationDtos;
    }

    @PostMapping("/by-date")
    @ApiOperation("Enter date in format: yyyy-MM-dd. Example: 2020-01-01")
    public List<PublicationDto> getPublicationsByDate(@RequestBody String dateStr) {
        log.info("Handling get publications by date request: " + dateStr);
        List <PublicationDto> publicationDtos = publicationService.getPublicationsByDate(dateStr);
        return publicationDtos;
    }

    @GetMapping("/by-author/{id}")
    public List <PublicationDto> getPublicationsByAuthor(@PathVariable Long id) {
        log.info("Handling get publications by id of author request: " + id);
        List <PublicationDto> publicationDtos = publicationService.getPublicationsByAuthor(id);
        return publicationDtos;
    }
}


