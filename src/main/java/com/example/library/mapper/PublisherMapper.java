package com.example.library.mapper;

import com.example.library.dto.PublisherDto;
import com.example.library.entity.Publisher;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PublisherMapper {

    @Named(value = "publisherDto")
    PublisherDto toPublisherDto (Publisher publisher);
    @Named(value = "publisher")
    Publisher toPublisher (PublisherDto publisherDto);

    @IterableMapping(qualifiedByName = "publisher")
    List<Publisher> mapToPublisherList (List<PublisherDto> publisherDtoList);

    @IterableMapping(qualifiedByName = "publisherDto")
    List<PublisherDto> mapToPublesherDtoList (List<Publisher> publisherList);
}
