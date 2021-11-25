package com.example.library.repository;

import com.example.library.entity.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository <Author, Long>{

}
