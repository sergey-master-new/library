package com.example.library.sevice.impl;

import com.example.library.dto.PublicationDto;
import com.example.library.entity.*;
import com.example.library.entity.ref.AuthorRef;
import com.example.library.entity.ref.PublisherRef;
import com.example.library.entity.ref.ThemeRef;
import com.example.library.entity.ref.TypeRef;
import com.example.library.exception.DateFormatException;
import com.example.library.exception.NotFoundException;
import com.example.library.mapper.PublicationMapper;
import com.example.library.repository.*;
import com.example.library.sevice.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PublicationServiceImpl implements PublicationService {

    @Autowired
    private PublicationRepository publicationRepository;
    @Autowired
    private PublicationMapper mapper;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private ThemeRepository themeRepository;
    @Autowired
    private TypeRepository typeRepository;

    @Override
    public List<PublicationDto> getAllPublicationDto() {
        List<Publication> publicationList = (List<Publication>) publicationRepository.findAll();
        List<PublicationDto> publicationDtoList = new ArrayList<>();
        convertToPublicationDtoList(publicationList, publicationDtoList);
        return publicationDtoList;
    }

    @Override
    public PublicationDto savePublicationDto(PublicationDto publicationDto) {
        Publication publicationSaved = null;
        try {
            Publication publication = mapper.toPublication(publicationDto);
            addAuthorsToPublication(publicationDto, publication);
            addPublisherToPublication(publicationDto, publication);
            addThemeToPublication(publicationDto, publication);
            addTypeToPublication(publicationDto, publication);
            publicationSaved = publicationRepository.save(publication);
        } catch (DbActionExecutionException e) {
            throw new NotFoundException("Saving error. Fill all fields correctly.");
        }
        PublicationDto publicationDtoSaved = mapper.toPublicationDto(publicationSaved);
        addAuthorsPublisherThemeTypeToPublicationDto(publicationSaved, publicationDtoSaved);
        return publicationDtoSaved;
    }

    @Override
    public void deletePublication(Long id) {
        publicationRepository.deleteById(id);
    }

    @Override
    public PublicationDto getPublicationById(Long id) {
        try {
            Publication publication = publicationRepository.findById(id).get();
            PublicationDto publicationDto = mapper.toPublicationDto(publication);
            addAuthorsPublisherThemeTypeToPublicationDto(publication, publicationDto);
            return publicationDto;
        } catch (NoSuchElementException e) {
            throw new NotFoundException("This publication was not found");
        }
    }

    @Override
    public List<PublicationDto> getPublicationsByType(Long id) {
        List<PublicationDto> publicationDtoList = getAllPublicationDto();
        List<PublicationDto> publicationDtoListFilteredByType =
                publicationDtoList.stream().filter((x) -> x.getType().getId() == id)
                        .collect(Collectors.toList());
        return publicationDtoListFilteredByType;
    }

    @Override
    public List<PublicationDto> getPublicationsByTheme(Long id) {
        List<PublicationDto> publicationDtoList = getAllPublicationDto();
        List<PublicationDto> publicationDtoListFilteredByTheme =
                publicationDtoList.stream().filter((x) -> x.getTheme().getId() == id).collect(Collectors.toList());
        return publicationDtoListFilteredByTheme;
    }

    @Override
    public List<PublicationDto> getPublicationsByAuthor(Long id) {
        List<PublicationDto> publicationDtoList = getAllPublicationDto();
        List<PublicationDto> publicationDtoListByAuthor = new ArrayList<>();
        for (PublicationDto pDto : publicationDtoList) {
            for (Author author : pDto.getAuthors()) {
                if (id == author.getId()) {
                    publicationDtoListByAuthor.add(pDto);
                    break;
                }
            }
        }
        return publicationDtoListByAuthor;
    }

    @Override
    public List<PublicationDto> getPublicationsByDate(String dateStr) {
        List<PublicationDto> publicationDtoListFilteredByDate =
                null;
        try {
            LocalDate localDate = LocalDate.parse(dateStr);
            List<PublicationDto> publicationDtoList = getAllPublicationDto();
            publicationDtoListFilteredByDate = publicationDtoList.stream().filter((x) -> x.getDate().equals(localDate)).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            throw new DateFormatException("Date format is not valid");
        }
        return publicationDtoListFilteredByDate;
    }

    @Override
    public List<PublicationDto> getPublicationsByPublisher(Long id) {
        List<PublicationDto> publicationDtoList = getAllPublicationDto();
        List<PublicationDto> publicationDtoListFilteredByPublisher =
                publicationDtoList.stream().filter((x) -> x.getPublisher().getId() == id).collect(Collectors.toList());
        return publicationDtoListFilteredByPublisher;
    }

    private void convertToPublicationDtoList(List<Publication> publicationList,
                                             List<PublicationDto> publicationDtoList) {
        for (Publication publication : publicationList) {
            PublicationDto publicationDto = mapper.toPublicationDto(publication);
            addAuthorsPublisherThemeTypeToPublicationDto(publication, publicationDto);
            publicationDtoList.add(publicationDto);
        }
    }

    private void addAuthorsPublisherThemeTypeToPublicationDto(Publication publication,
                                                              PublicationDto publicationDto) {
        publicationDto.setAuthors(getAuthorsByIds(publication.getAuthors()));
        publicationDto.setPublisher(getPublisherById(publication.getPublisherRef()));
        publicationDto.setTheme(getThemeById(publication.getThemeRef()));
        publicationDto.setType(getTypeById(publication.getTypeRef()));
    }

    private List<Author> getAuthorsByIds(Set<AuthorRef> authorRefs) {
        List<Long> idAuthors = new ArrayList<>();
        for (AuthorRef authorRef : authorRefs) {
            idAuthors.add(authorRef.getAuthorId());
        }
        return (List<Author>) authorRepository.findAllById(idAuthors);
    }

    private Publisher getPublisherById(PublisherRef publisherRef) {
        Publisher publisher = publisherRepository.findById(publisherRef.getPublisherId()).get();
        return publisher;
    }

    private Theme getThemeById(ThemeRef themeRef) {
        Theme theme = themeRepository.findById(themeRef.getThemeId()).get();
        return theme;
    }

    private Type getTypeById(TypeRef typeRef) {
        Type type = typeRepository.findById(typeRef.getTypeId()).get();
        return type;
    }

    private void addAuthorsToPublication(PublicationDto publicationDto, Publication publication) {
        Set<AuthorRef> authorRefSet = new HashSet<>();
        for (Author author : publicationDto.getAuthors()) {
            authorRefSet.add(new AuthorRef(author.getId()));
        }
        publication.setAuthors(authorRefSet);
    }

    private void addPublisherToPublication(PublicationDto publicationDto, Publication publication) {
        PublisherRef publisherRef = new PublisherRef();
        publisherRef.setPublisherId(publicationDto.getPublisher().getId());
        publication.setPublisherRef(publisherRef);
    }

    private void addThemeToPublication(PublicationDto publicationDto, Publication publication) {
        ThemeRef themeRef = new ThemeRef();
        themeRef.setThemeId(publicationDto.getTheme().getId());
        publication.setThemeRef(themeRef);
    }

    private void addTypeToPublication(PublicationDto publicationDto, Publication publication) {
        TypeRef typeRef = new TypeRef();
        typeRef.setTypeId(publicationDto.getType().getId());
        publication.setTypeRef(typeRef);
    }
}
