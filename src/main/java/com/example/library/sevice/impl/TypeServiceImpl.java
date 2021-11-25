package com.example.library.sevice.impl;

import com.example.library.dto.TypeDto;
import com.example.library.entity.Type;
import com.example.library.exception.NoDeletionException;
import com.example.library.exception.NotFoundException;
import com.example.library.mapper.TypeMapper;
import com.example.library.repository.TypeRepository;
import com.example.library.sevice.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public List<TypeDto> getAllDto() {
        List<Type> typeList = (List<Type>) typeRepository.findAll();
        return typeMapper.mapToTypeDtoList(typeList);
    }

    @Override
    public void delete(Long id) {
        try {
            typeRepository.deleteById(id);
        } catch (DbActionExecutionException e) {
            throw new NoDeletionException("No deletion. " +
                    "This type of publication is listed in a publication that is in the library.");
        }
    }

    @Override
    public TypeDto saveDto(TypeDto typeDto) {
        Type type = typeMapper.toType(typeDto);
        Type typeSaved = typeRepository.save(type);
        return typeMapper.toTypeDto(typeSaved);
    }

    @Override
    public TypeDto findById(Long id) {
        try {
            Type type = typeRepository.findById(id).get();
            return typeMapper.toTypeDto(type);
        } catch (NoSuchElementException e) {
            throw new NotFoundException("This type was not found");
        }
    }
}
