package com.hludencov.java_spring.repo;

import com.hludencov.java_spring.models.Document;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DocumentRepository extends CrudRepository<Document, Long> {
    List<Document> findByFileNameContains(String name);
    List<Document> findByFileName(String name);

}
