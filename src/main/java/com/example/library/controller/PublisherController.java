package com.example.library.controller;

import com.example.library.dto.PublisherDto;
import com.example.library.sevice.PublisherService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/publisher")
@Log
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping("/get-all")
    public List<PublisherDto> getAllDto(){
        log.info("Handling get all publishers request");
        return publisherService.getAllDto();
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Handling delete publisher request: " + id);
        publisherService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/save")
    public PublisherDto saveDto(@Valid @RequestBody PublisherDto publisherDto) {
        log.info("Handling save publisher: " + publisherDto);
        return publisherService.saveDto(publisherDto);
    }

    @GetMapping("/{id}")
    public PublisherDto findById(@PathVariable Long id) {
        log.info("Handling get publisher by id request: " + id);
        PublisherDto publisherDto = publisherService.findById(id);
       return publisherDto;
    }
}
