package com.example.library.sevice.impl;

import com.example.library.dto.AuthorDto;
import com.example.library.entity.Author;
import com.example.library.exception.NoDeletionException;
import com.example.library.exception.NotFoundException;
import com.example.library.mapper.AuthorMapper;
import com.example.library.repository.AuthorRepository;
import com.example.library.sevice.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;

    @Override
    public List<AuthorDto> getAllDto() {
        List<Author> authorList = (List<Author>) authorRepository.findAll();
        return authorMapper.mapToAuthorDtoList(authorList);
    }

    @Override
    public void delete(Long id) {
        try {
            authorRepository.deleteById(id);
        } catch (DbActionExecutionException e) {
            throw new NoDeletionException("No deletion. " +
                    "This author is listed in a publication that is in the library.");
        }
    }

    @Override
    public AuthorDto saveDto(AuthorDto authorDto) {
        Author author = authorMapper.toAuthor(authorDto);
        Author authorSaved = authorRepository.save(author);
        return authorMapper.toAuthorDto(authorSaved);
    }

    @Override
    public AuthorDto findById(Long id) {
        try {
            Author author = authorRepository.findById(id).get();
            return authorMapper.toAuthorDto(author);
        } catch (NoSuchElementException e) {
            throw new NotFoundException("This author was not found");
        }
    }
}
