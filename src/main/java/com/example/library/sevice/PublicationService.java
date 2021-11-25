package com.example.library.sevice;

import com.example.library.dto.PublicationDto;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

public interface PublicationService {

    List<PublicationDto> getAllPublicationDto();

    void deletePublication(Long id);

    PublicationDto savePublicationDto(PublicationDto publicationDto);

    PublicationDto getPublicationById(Long id);

    List <PublicationDto> getPublicationsByType(Long id);

    List <PublicationDto> getPublicationsByTheme(Long id);

    List<PublicationDto> getPublicationsByPublisher(Long id);

    List<PublicationDto> getPublicationsByAuthor(Long id);

    List<PublicationDto> getPublicationsByDate(String dateStr);
}
