package com.hludencov.java_spring.repo;

import com.hludencov.java_spring.models.Group;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<Group, Long> {
}