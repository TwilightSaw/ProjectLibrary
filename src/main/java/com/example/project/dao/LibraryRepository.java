package com.example.project.dao;


import com.example.project.entity.Library;
import org.springframework.data.repository.CrudRepository;

public interface LibraryRepository extends CrudRepository<Library, Long> {
    // Add custom query methods if needed
}