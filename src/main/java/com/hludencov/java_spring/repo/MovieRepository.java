package com.hludencov.java_spring.repo;

import com.hludencov.java_spring.models.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Long> {

    List<Movie> findByNameContains(String title);
    List<Movie> findByName(String title);

}
