package com.example.library.mapper;

import com.example.library.dto.TypeDto;
import com.example.library.entity.Type;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TypeMapper {

    @Named(value = "typeDto")
    TypeDto toTypeDto (Type type);

    @Named(value = "type")
    Type toType (TypeDto typeDto);

    @IterableMapping(qualifiedByName = "type")
    List<Type> mapToTypeList (List<TypeDto> typeDtoList);

    @IterableMapping(qualifiedByName = "typeDto")
    List<TypeDto> mapToTypeDtoList (List<Type> typeList);
}
