package com.example.library.sevice;

import com.example.library.dto.PublisherDto;

import java.util.List;

public interface PublisherService {

    List<PublisherDto> getAllDto();

    void delete(Long id);

    PublisherDto saveDto(PublisherDto publisherDto);

    PublisherDto findById(Long id);
}
