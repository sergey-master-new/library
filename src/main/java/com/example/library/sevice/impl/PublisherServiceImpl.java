package com.example.library.sevice.impl;

import com.example.library.dto.PublisherDto;
import com.example.library.entity.Publisher;
import com.example.library.exception.NoDeletionException;
import com.example.library.exception.NotFoundException;
import com.example.library.mapper.PublisherMapper;
import com.example.library.repository.PublisherRepository;
import com.example.library.sevice.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private PublisherMapper publisherMapper;

    @Override
    public List<PublisherDto> getAllDto() {
        List<Publisher> publisherList = (List<Publisher>) publisherRepository.findAll();
        return publisherMapper.mapToPublesherDtoList(publisherList);
    }

    @Override
    public void delete(Long id) {
        try {
            publisherRepository.deleteById(id);
        } catch (DbActionExecutionException e) {
            throw new NoDeletionException("No deletion. " +
                    "This publisher is listed in a publication that is in the library.");
        }
    }

    @Override
    public PublisherDto saveDto(PublisherDto publisherDto) {
        Publisher publisher = publisherMapper.toPublisher(publisherDto);
        Publisher publisherSaved = publisherRepository.save(publisher);
        return publisherMapper.toPublisherDto(publisherSaved);
    }

    @Override
    public PublisherDto findById(Long id) {
        try {
            Publisher publisher = publisherRepository.findById(id).get();
            return publisherMapper.toPublisherDto(publisher);
        } catch (NoSuchElementException e) {
            throw new NotFoundException("This publisher was not found");
        }
    }

}
