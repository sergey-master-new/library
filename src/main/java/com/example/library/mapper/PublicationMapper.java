package com.example.library.mapper;

import com.example.library.dto.PublicationDto;
import com.example.library.entity.Publication;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface PublicationMapper {

    @Named(value = "publicationDto")
    PublicationDto toPublicationDto (Publication publication);

    @Named(value = "publication")
    Publication toPublication (PublicationDto publicationDto);

    @IterableMapping(qualifiedByName = "publication")
    List<Publication> mapToPublicationList(List<PublicationDto> publicationDto);

    @IterableMapping(qualifiedByName = "publicationDto")
    List<PublicationDto> mapToPublicationDtoList(List<Publication> publication);
}
