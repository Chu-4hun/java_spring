package com.hludencov.java_spring.repo;

import com.hludencov.java_spring.models.Preparation_program;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface Preparation_programRepository extends CrudRepository<Preparation_program, Long> {
    List<Preparation_program> findByNameContains(String name);
    List<Preparation_program> findByName(String title);
}
