package com.hludencov.java_spring.repo;

import com.hludencov.java_spring.models.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {

    List<Post> findByTitleContains(String title);

}
