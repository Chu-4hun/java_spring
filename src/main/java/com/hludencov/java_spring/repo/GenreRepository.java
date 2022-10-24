package com.hludencov.java_spring.repo;

import com.hludencov.java_spring.models.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository  extends CrudRepository<Genre, Long> {
}